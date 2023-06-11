package vn.edu.hcmuaf.st.DACN_BookStore_2023.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.converter.RoleConverter;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.converter.UserConverter;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.RoleDTO;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.UserDTO;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.entity.RoleEntity;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.entity.UserEntity;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.repository.RoleRepository;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.repository.UsersRepository;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.service.IUserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImp implements IUserService {
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private UsersRepository userRepo;
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private RoleConverter roleConverter;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public UserDTO findByEmailAndIsEnable(String email) {
        //tìm những email đã xác thực (isEnable = true)
        UserEntity userEntity = userRepo.findByEmailIgnoreCaseAndIsEnableAndStatus(email, true, true);
        if (userEntity != null) {
            return userConverter.toDTO(userEntity);
        }
        return null;
    }

    public UserDTO sendMail(UserDTO user) {
        UserEntity userEntity = new UserEntity();
        //neu nhu tai khoan vs email da ton tai thi tra ve null
        UserEntity existedUser = userRepo.findByEmailIgnoreCaseAndIsEnableAndStatus(user.getEmail(), true, true);
        if (existedUser != null) return null;
        else {
            //neu tim dc nhung email da dang ki tai khoan nhung chua xac thuc thi xoa tk do luon
            UserEntity temp = userRepo.findByEmailIgnoreCaseAndIsEnableAndStatus(user.getEmail(), false, true);
            if (temp != null) userRepo.delete(temp);

            //set lai pass da ma hoa
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            //tao confirm token
            user.setConfirmToken(new Random().nextInt(999999) + "");
            user.setCreatedAt(LocalDate.now());
            user.setStatus(true);
            user.setProvider("LOCAL");

            //tạo 1 list để lưu các role của người dùng
            List<RoleDTO> list = new ArrayList<>();
            //sau đó tìm role user thêm vào list
            RoleEntity role = roleRepo.findByName("ROLE_USER");

            list.add(roleConverter.toDTO(role));
            user.setRoles(list);
            //lưu user này rồi thì bên role entity tự động thêm user này vào list role
            userRepo.save(userConverter.toEntity(user));

            //gui confirm token qua mail
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Bookstore - Xác nhận email để tạo tài khoản");
            message.setFrom("bookstore@gmail.com");
            message.setText("Code xác nhận mail của bạn là: " + user.getConfirmToken() + ". Vui lòng nhập code để xác nhận email");
            mailSender.send(message);
            //sau khi luu xong thì lấy user vừa lưu với email đó để gửi cái userID qua bên view confirmCode
            return userConverter.toDTO(userRepo.findByEmailIgnoreCase(user.getEmail()));
        }

    }

    @Override
    public String getConfirmCode(int id) {
        return userRepo.getConfirmTokenById(id);
    }

    @Override
    public UserDTO confirmEmail(int id) {
        UserEntity userEntity = userRepo.findByUserID(id);
        userEntity.setEnable(true);
        userRepo.save(userEntity);
        return userConverter.toDTO(userEntity);
    }
    @Override
    public void changeInformation(UserDTO user) {
        String username = "";
        String fullname = "";
        String phone = "";
        LocalDate birthdate = LocalDate.now();
        //gender: true là nữ, false là nam
        boolean gender = false;
        UserEntity userFromDb = userRepo.findByEmailIgnoreCaseAndIsEnableAndStatus(user.getEmail(), true, true);
        if (user.getUsername() != null) username = user.getUsername();
        if (user.getFullname() != null) fullname = user.getFullname();
        if (user.getBirthdate() != null)
            birthdate = user.getBirthdate();
        if (user.isGender()) gender = user.isGender();
        if (user.getPhone() != null) phone = user.getPhone();
        userRepo.updateUser(userFromDb.getUserID(), username, fullname, birthdate, gender, phone, LocalDate.now());
    }
}
