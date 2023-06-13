package vn.edu.hcmuaf.st.DACN_BookStore_2023.converter;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.OrderlineDTO;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.entity.OrderLineEntity;

@Component
public class OrderlineConverter {
    @Autowired
    private ModelMapper modelMapper;

    public OrderLineEntity toEntity(OrderlineDTO dto) {
        return modelMapper.map(dto, OrderLineEntity.class);
    }

    public OrderlineDTO toDTO(OrderLineEntity entity) {
        return modelMapper.map(entity, OrderlineDTO.class);
    }
}
