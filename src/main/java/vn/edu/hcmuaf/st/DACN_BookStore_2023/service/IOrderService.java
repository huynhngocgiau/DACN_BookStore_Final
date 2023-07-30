package vn.edu.hcmuaf.st.DACN_BookStore_2023.service;


import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.OrderDTO;

import java.util.List;

public interface IOrderService {
    public void save(OrderDTO order);

    public List<OrderDTO> findAllByUserId(int id);

    public List<OrderDTO> findAll();

    public OrderDTO findById(int id);

    public void update(OrderDTO order, int id);

    public void deleteById(int id);
    public OrderDTO findLastSave();
}
