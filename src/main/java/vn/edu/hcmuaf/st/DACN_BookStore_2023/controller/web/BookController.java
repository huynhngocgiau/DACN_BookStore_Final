package vn.edu.hcmuaf.st.DACN_BookStore_2023.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.form.BookOutput;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.service.IBookService;

import java.util.List;

@RestController
public class BookController {
    @Autowired
    private IBookService bookService;

    //?category=truyen-tranh&page=1&size=8&sort=price&order=asc
    //xu li cho request ajax
    @GetMapping("/danh-sach-san-pham")
    public BookOutput findAll(
            @RequestParam(name = "category", required = false, defaultValue = "null") String category,
            @RequestParam(name = "author", required = false, defaultValue = "null") String author,
            @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(name = "title", required = false, defaultValue = "null") String title

    ) {
        //mặc định có 9 sản phẩm mỗi trang
        final int SIZE = 9;
        BookOutput output = new BookOutput();

        Pageable pageable = PageRequest.of(page - 1, SIZE);
        if (!title.equalsIgnoreCase("null")) {
            output.setResult(bookService.findAllContainTitle(title, pageable));
            output.setPage(page);
            output.setTotalPage((int) Math.round(Math.ceil((double) (bookService.countAllByTitleContains(title)) / SIZE)));

        } else if (!category.equalsIgnoreCase("null")) {
            output.setResult(bookService.findByCategoryCode(category, pageable));
            output.setPage(page);
            output.setTotalPage((int) Math.round(Math.ceil((double) (bookService.countByCategory(category)) / SIZE)));

        } else if (!author.equalsIgnoreCase("null")) {
            output.setResult(bookService.findAllByAuthorCode(author, pageable));
            output.setPage(page);
            output.setTotalPage((int) Math.round(Math.ceil((double) (bookService.countByAuthorCode(author)) / SIZE)));

        } else {
            output.setResult(bookService.findAll(pageable));
            output.setPage(page);
            output.setTotalPage((int) Math.round(Math.ceil((double) (bookService.countAllByActive(true)) / SIZE)));
        }
        return output;
    }

    //dieu huong sang trang san pham
    @GetMapping("/san-pham")
    public ModelAndView shop() {
        ModelAndView mav = new ModelAndView("web/shop.html");
        return mav;
    }

    @GetMapping("/autocomplete")
    public List<String> autoCompleteTitle(@RequestParam("title") String title) {
        return bookService.autoCompleteTitle(title);
    }
}
