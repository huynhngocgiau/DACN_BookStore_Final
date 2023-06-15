package vn.edu.hcmuaf.st.DACN_BookStore_2023.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.converter.OrderlineConverter;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.OrderlineDTO;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.repository.OrderlineRepository;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.service.IOrderlineService;

@Service
public class OrderLineServiceImp implements IOrderlineService {
    @Autowired
    private OrderlineRepository orderlineRepository;
    @Autowired
    private OrderlineConverter orderlineConverter;

    public void save(OrderlineDTO orderLine) {
        orderlineRepository.save(orderlineConverter.toEntity(orderLine));
    }
}
