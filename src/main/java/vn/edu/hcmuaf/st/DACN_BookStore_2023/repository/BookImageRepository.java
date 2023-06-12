package vn.edu.hcmuaf.st.DACN_BookStore_2023.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.entity.BookImageEntity;

public interface BookImageRepository extends JpaRepository<BookImageEntity, Integer> {
}
