package vn.edu.hcmuaf.st.DACN_BookStore_2023.service;

import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.UserDTO;

import java.util.List;

public interface IUserService {
    public UserDTO findByEmailAndIsEnable(String email);

    public UserDTO sendMail(UserDTO user);

    public String getConfirmCode(int id);

    public UserDTO confirmEmail(int id);

}
