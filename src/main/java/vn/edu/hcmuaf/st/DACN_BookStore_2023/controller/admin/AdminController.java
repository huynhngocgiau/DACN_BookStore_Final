package vn.edu.hcmuaf.st.DACN_BookStore_2023.controller.admin;

import org.springframework.security.core.Authentication;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.api.input.BookInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.BookDTO;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.BookImageDTO;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.UserDTO;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.oauth2.CustomOAuth2User;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.service.IBookService;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.CategoryDTO;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.service.ICategoryService;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.service.IUserService;

@RestController
@RequestMapping("/admin-page")
public class AdminController {
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    @Autowired
    private IBookService bookService;
    @Autowired
    private IUserService userService;

    //books
    @GetMapping("/book-management")
    public ModelAndView listBook() {
        ModelAndView mav = new ModelAndView("admin/book-management/books");
        mav.addObject("list", bookService.findAll());
        return mav;
    }

    @GetMapping("/add-book-page")
    public ModelAndView addBookPage() {
        ModelAndView mav = new ModelAndView("admin/book-management/addBook");
//        mav.addObject("categories", categoryService.findAll());
//        mav.addObject("authors", authorService.findAll());
        return mav;
    }

    @PostMapping("/add-book")
    public ModelAndView addBook(@ModelAttribute("bookInput") BookInput input) {
        Path staticPath = Paths.get("src/main/resources/static");
        Path imagePath = Paths.get("admin/img/bookupload");
        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
            try {
                Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Path file = CURRENT_FOLDER.resolve(staticPath)
                .resolve(imagePath).resolve(input.getImages().getOriginalFilename());
        if (!Files.exists(file)) {
            try (OutputStream os = Files.newOutputStream(file)) {
                os.write(input.getImages().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ModelAndView mav = new ModelAndView("redirect:/admin-page/book-management");
        BookDTO newBook = new BookDTO();
        if (input.getId() != 0) newBook.setId(input.getId());
        newBook.setTitle(input.getTitle());
        newBook.setDescription(input.getDescription());
        newBook.setYear_public(input.getYear_public());
        if (input.getQuantitySold() != 0) newBook.setQuantitySold(input.getQuantitySold());
        newBook.setPrice(input.getPrice());
        if (input.getDiscount_percent() != 0) newBook.setDiscountPercent(input.getDiscount_percent());
        if (input.getPublisher() != null) newBook.setPublisher(input.getPublisher());
        if (input.getTotal_page() != 0) newBook.setTotal_page(input.getTotal_page());
        newBook.setNews(input.isNews());
        newBook.setActive(true);
        newBook.setHot(input.isHot());
//        newBook.setCategory(categoryService.findById(input.getCategoryId()));
//        newBook.setAuthor(authorService.findById(input.getAuthorId()));

        //load hình
        if (!input.getImages().isEmpty()) {
            List<BookImageDTO> imageList = new ArrayList<>();
            BookImageDTO img = new BookImageDTO();
            StringTokenizer stringTokenizer = new StringTokenizer(imagePath.resolve(input.getImages().getOriginalFilename()).toString(), "\\");
            String s = "";
            while (stringTokenizer.hasMoreTokens()) {
                s += stringTokenizer.nextToken() + "/";
            }
            img.setPath(s.substring(0, s.length() - 1));
            imageList.add(img);
            newBook.setImages(imageList);
        }
        bookService.save(newBook);
        return mav;
    }

    @GetMapping("/edit-book-page")
    public ModelAndView editBookPage(@RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView("admin/book-management/editBook");
        List<Integer> years = new ArrayList<>();
        for (int i = 1940; i <= 2023; i++)
            years.add(i);
        mav.addObject("years", years);
        mav.addObject("book", bookService.findById(id));
//        mav.addObject("categories", categoryService.findAll());
//        mav.addObject("authors", authorService.findAll());
        return mav;
    }

    @GetMapping("/detail-book")
    public ModelAndView detail(@RequestParam(name = "id") int id) {
        ModelAndView mav = new ModelAndView("admin/book-management/detail");
        mav.addObject("book", bookService.findById(id));
        return mav;
    }

    @GetMapping("/delete-book")
    public ModelAndView deleteBook(@RequestParam("id") int id) {
        Path staticPath = Paths.get("src/main/resources/static");
        List<BookImageDTO> images = bookService.findById(id).getImages();
        for (BookImageDTO i : images) {
            Path imgPath = CURRENT_FOLDER.resolve(staticPath).resolve(i.getPath());
            try {
                if (Files.exists(imgPath))
                    Files.deleteIfExists(imgPath);
                else break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        bookService.deleteById(id);
        ModelAndView mav = new ModelAndView("redirect:/admin-page/book-management");
        return mav;
    }


    @Autowired
    private ICategoryService categoryService;

    //amdin category
    @GetMapping("/category-management")
    public ModelAndView listCategory() {
        ModelAndView mav = new ModelAndView("admin/category-management/categories");
        mav.addObject("cats", categoryService.findAll());
        return mav;
    }

    @GetMapping("/add-category-page")
    public ModelAndView addCategoryPage() {
        ModelAndView mav = new ModelAndView("admin/category-management/addCategory");
        return mav;
    }

    @PostMapping("/add-category")
    public ModelAndView addCategory(@ModelAttribute("cat") CategoryDTO cat) {
        categoryService.save(cat);
        ModelAndView mav = new ModelAndView("redirect:/admin-page/category-management");
        return mav;
    }

    @GetMapping("/edit-category-page")
    public ModelAndView editCategoryPage(@RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView("admin/category-management/editCategory");
        mav.addObject("category", categoryService.findById(id));
        return mav;
    }

    @PostMapping("/edit-category")
    public ModelAndView editCategory(@ModelAttribute("cat") CategoryDTO cat) {
        categoryService.updateCat(cat);
        ModelAndView mav = new ModelAndView("redirect:/admin-page/category-management");
        return mav;
    }

    @GetMapping("/delete-category")
    public ModelAndView deleteCat(@RequestParam("id") int id) {
        categoryService.deleteByCatId(id);
        ModelAndView mav = new ModelAndView("redirect:/admin-page/category-management");
        return mav;
    }

    @GetMapping("getAdmin")
    public UserDTO getAdmin(Authentication authentication) {
        if (authentication == null) return new UserDTO();
        else {
            String userEmail = "";
            if (authentication.getPrincipal() instanceof CustomOAuth2User) {
                CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
                userEmail = oAuth2User.getAttribute("email");
            } else userEmail = authentication.getName();
            return userService.findByEmailAndIsEnable(userEmail);
        }
    }
}

