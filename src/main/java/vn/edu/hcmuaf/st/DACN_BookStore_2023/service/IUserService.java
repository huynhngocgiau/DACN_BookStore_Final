package vn.edu.hcmuaf.st.DACN_BookStore_2023.service;

import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.UserDTO;

public interface IUserService {
    public UserDTO findByEmailAndIsEnable(String email);

    public UserDTO sendMail(UserDTO user);

    public String getConfirmCode(int id);

    public UserDTO confirmEmail(int id);

    //change information
    public void changeInformation(UserDTO user);

<<<<<<< Updated upstream
    void processOAuthPostLogin(Object email);
=======
    //change password
    public boolean checkPass(String email, String password);
    public void changePassword(String password, String email);


    public List<UserDTO> findAllUser();

    public UserDTO findByUserId(int id);

    public void deleteByUserId(int id);

    public void save(UserDTO user);
    public void processOAuthPostLogin(String email, String provider);
>>>>>>> Stashed changes

    void processOAuthPostLogin(String email);
}
