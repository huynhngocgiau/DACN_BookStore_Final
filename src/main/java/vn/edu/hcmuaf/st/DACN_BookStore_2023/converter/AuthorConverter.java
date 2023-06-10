package vn.edu.hcmuaf.st.DACN_BookStore_2023.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.AuthorDTO;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.entity.AuthorEntity;

@Component
public class AuthorConverter {
    @Autowired
    private ModelMapper modelMapper;

    public AuthorEntity toEntity(AuthorDTO dto) {
        return modelMapper.map(dto, AuthorEntity.class);
    }

    public AuthorDTO toDTO(AuthorEntity entity) {
        return modelMapper.map(entity, AuthorDTO.class);
    }
}
