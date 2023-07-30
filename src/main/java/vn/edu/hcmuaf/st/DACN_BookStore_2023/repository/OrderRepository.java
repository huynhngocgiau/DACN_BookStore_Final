package vn.edu.hcmuaf.st.DACN_BookStore_2023.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.entity.BookEntity;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.entity.OrderEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    public List<OrderEntity> findAllByUserUserID(int id);

    public OrderEntity findByOrderID(int id);

    @Transactional
    @Modifying
    @Query(value = "update orders set status=:status, note=:note, updated_at=:modified where orderID=:id", nativeQuery = true)
    public void updateOrder(@Param(value = "id") int id,
                            @Param(value = "status") String status,
                            @Param(value = "note") String note,
                            @Param(value = "modified") LocalDate modifiedAt);

    @Transactional
    @Modifying
    public void deleteByOrderID(int id);

    public OrderEntity findFirstByOrderByOrderIDDesc();

}
