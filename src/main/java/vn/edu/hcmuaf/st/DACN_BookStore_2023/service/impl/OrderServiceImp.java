package vn.edu.hcmuaf.st.DACN_BookStore_2023.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.converter.OrderConverter;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.OrderDTO;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.entity.OrderEntity;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.repository.OrderRepository;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.service.IOrderService;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImp implements IOrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderConverter orderConverter;

    @Override
    public void save(OrderDTO order) {
      orderRepository.save(orderConverter.toEntity(order));
    }

    @Override
    public List<OrderDTO> findAllByUserId(int id) {
        List<OrderEntity> list = orderRepository.findAllByUserUserID(id);
        List<OrderDTO> result = new ArrayList<>();
        for (OrderEntity e : list) {
            result.add(orderConverter.toDTO(e));
        }
        return result;
    }

    @Override
    public List<OrderDTO> findAll() {
        List<OrderEntity> list = orderRepository.findAll();
        List<OrderDTO> result = new ArrayList<>();
        for (OrderEntity e : list) {
            result.add(orderConverter.toDTO(e));
        }
        return result;
    }

    @Override
    public void update(OrderDTO order, int id) {
        orderRepository.updateOrder(id, order.getStatus(), order.getNote(), order.getUpdatedAt());
    }

    @Override
    public OrderDTO findById(int id) {
        return orderConverter.toDTO(orderRepository.findByOrderID(id));
    }

    @Override
    public void deleteById(int id) {
        orderRepository.deleteByOrderID(id);
    }

    @Override
    public OrderDTO findLastSave() {
        return orderConverter.toDTO(orderRepository.findFirstByOrderByOrderIDDesc());
    }
}

