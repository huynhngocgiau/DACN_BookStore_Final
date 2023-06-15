package vn.edu.hcmuaf.st.DACN_BookStore_2023.converter;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.OrderDTO;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.entity.OrderEntity;

@Component
public class OrderConverter {
    @Autowired
    private ModelMapper modelMapper;

    public OrderEntity toEntity(OrderDTO dto) {
        return modelMapper.map(dto, OrderEntity.class);
    }

    public OrderDTO toDTO(OrderEntity entity) {
        return modelMapper.map(entity, OrderDTO.class);
    }
}
