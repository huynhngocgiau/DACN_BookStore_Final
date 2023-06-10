package vn.edu.hcmuaf.st.DACN_BookStore_2023.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.CategoryDTO;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.service.ICategoryService;

import java.nio.file.Path;
import java.nio.file.Paths;
@RestController
@RequestMapping("/admin-page")
public class AdminController {
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));

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

}
