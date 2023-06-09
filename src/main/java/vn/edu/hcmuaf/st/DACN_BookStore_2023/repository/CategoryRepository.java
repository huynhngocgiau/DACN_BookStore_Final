package vn.edu.hcmuaf.st.DACN_BookStore_2023.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.entity.CategoryEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    public CategoryEntity findByCategoryID(int id);

    public List<CategoryEntity> findFirst10ByOrderByCategoryIDAsc();
}
