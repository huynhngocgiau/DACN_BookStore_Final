package vn.edu.hcmuaf.st.DACN_BookStore_2023.converter;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.UserDTO;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.entity.UserEntity;

@Component
public class UserConverter {
    @Autowired
    private ModelMapper modelMapper;

    public UserEntity toEntity(UserDTO userDto) {
        return modelMapper.map(userDto, UserEntity.class);
    }

    public UserDTO toDTO(UserEntity userEntity) {
        return modelMapper.map(userEntity, UserDTO.class);
    }
}
