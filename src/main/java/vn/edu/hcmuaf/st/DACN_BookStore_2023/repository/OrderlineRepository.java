package vn.edu.hcmuaf.st.DACN_BookStore_2023.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.entity.OrderLineEntity;

public interface OrderlineRepository extends JpaRepository<OrderLineEntity, Integer> {
}
