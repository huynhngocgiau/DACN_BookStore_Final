package vn.edu.hcmuaf.st.DACN_BookStore_2023.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.entity.BookEntity;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    //tìm tất cả saách theo danh mục, phân trang
    public Page<BookEntity> findByCategoryCode(String code, Pageable pageable);

    public Page<BookEntity> findAllByAuthorAuthorCode(String code, Pageable pageable);

    //tìm tất cả sách, có phân trang
    public Page<BookEntity> findAll(Pageable pageable);

    public Page<BookEntity> findAllByTitleContains(String title, Pageable pageable);

    List<BookEntity> findAllByActiveAndTitleContains(boolean isActive, String title);


    //đếm số sách theo danh mục
    public int countAllByCategoryCode(String code);

    public int countAllByAuthorAuthorCode(String code);

    //đếm tất cả sách
    public int countAllByActive(boolean isActive);

    public int countAllByTitleContains(String title);

    public BookEntity findById(int id);

    public BookEntity findFirstByOrderByIdDesc();


