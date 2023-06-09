package vn.edu.hcmuaf.st.DACN_BookStore_2023.converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.CategoryDTO;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.entity.CategoryEntity;

@Component
public class CategoryConverter {
    @Autowired
    private ModelMapper modelMapper;

    //chuyển từ entity sang dto bằng modelmapper
    public CategoryDTO toDTO(CategoryEntity catEntity) {
        return modelMapper.map(catEntity, CategoryDTO.class);
    }

    //chuển từ dto sang entity
    public CategoryEntity toEntity(CategoryDTO catDTO) {
        return modelMapper.map(catDTO, CategoryEntity.class);
    }
}
