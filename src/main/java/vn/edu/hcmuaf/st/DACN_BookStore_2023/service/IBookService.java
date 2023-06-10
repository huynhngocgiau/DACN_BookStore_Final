package vn.edu.hcmuaf.st.DACN_BookStore_2023.service;
import org.springframework.data.domain.Pageable;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.BookDTO;

import java.util.List;

public interface IBookService {
    public List<BookDTO> findByCategoryCode(String categoryCode, Pageable pageable);

    public List<BookDTO> findAllByAuthorCode(String code, Pageable pageable);

    public List<BookDTO> findAll(Pageable pageable);

    public List<BookDTO> findAll();


    public List<BookDTO> findAllContainTitle(String title, Pageable pageable);

    //count
    public int countByCategory(String code);

    public int countByAuthorCode(String code);

    public int countAllByActive(boolean isActive);

    public int countAllByTitleContains(String titles);

    public List<String> autoCompleteTitle(String title);

    public void save(BookDTO book);

    public BookDTO findById(int id);

    public void deleteById(int id);
}
