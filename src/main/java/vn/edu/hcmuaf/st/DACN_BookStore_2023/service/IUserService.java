package vn.edu.hcmuaf.st.DACN_BookStore_2023.service;

import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.UserDTO;

public interface IUserService {
    public UserDTO findByEmailAndIsEnable(String email);

    public UserDTO sendMail(UserDTO user);

    public String getConfirmCode(int id);

    public UserDTO confirmEmail(int id);

    //change information
    public void changeInformation(UserDTO user);

    void processOAuthPostLogin(Object email);

    void processOAuthPostLogin(String email);
}
