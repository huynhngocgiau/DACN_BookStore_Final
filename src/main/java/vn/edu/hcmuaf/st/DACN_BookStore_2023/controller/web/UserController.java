package vn.edu.hcmuaf.st.DACN_BookStore_2023.controller.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.UserDTO;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.service.IUserService;

import java.security.Principal;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/check-mail")
    public String findByEmailAndIsEnable(@RequestParam(name = "email", required = false) String email) {
        UserDTO user = userService.findByEmailAndIsEnable(email);
        String result = "";
        if (user != null) {
            user.setPassword("");
            result = user.getEmail();
        }
        return result;
    }

    @PostMapping("/dang-ky")
    public ModelAndView postRegister(@ModelAttribute("User") UserDTO user) {
        ModelAndView mav = new ModelAndView("web/confirmCode.html");
        UserDTO userDTO = userService.sendMail(user);
        if (userDTO != null) {
            //neu sendmail thanh cong thi gui id de xac dinh confirm token
            mav.addObject("userId", userDTO.getUserID());
        }
        return mav;
    }

    @PostMapping("/confirm-account")
    public ModelAndView confirmAccount(@RequestParam(name = "confirmCode") String code, @RequestParam(name = "userId") int id) {
        ModelAndView mav = null;
        //request sẽ gồm code người dùng nhập vào và id dc gửi qua
        //lấy code đó so sánh với code được lấy ra từ user tìm dc theo id
        //nếu giống nhau thì set enable lại r trả về view sign in
        if (code.equalsIgnoreCase(userService.getConfirmCode(id))) {
            UserDTO userDTO = userService.confirmEmail(id);
            mav = new ModelAndView("redirect:/dang-nhap");
        }else {
            mav = new ModelAndView("web/confirmCode.html");
            mav.addObject("userId", id);
            mav.addObject("message", "Mã không trùng khớp. Vui lòng kiểm tra mail và thử lại");
        }
        return mav;
    }


}