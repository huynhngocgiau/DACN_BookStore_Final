package vn.edu.hcmuaf.st.DACN_BookStore_2023.service;


import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.OrderlineDTO;

public interface IOrderlineService {
    public void save(OrderlineDTO orderLine);
}
