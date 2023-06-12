package vn.edu.hcmuaf.st.DACN_BookStore_2023.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.converter.BookConverter;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.BookDTO;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.BookImageDTO;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.entity.BookEntity;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.entity.BookImageEntity;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.repository.BookImageRepository;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.repository.BookRepository;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.service.IBookService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImp implements IBookService {
    @Autowired
    private BookRepository bookRepo;
    @Autowired
    private BookConverter bookConverter;
    @Autowired
    private BookImageRepository bookImageRepository;


    @Override
    public List<BookDTO> findByCategoryCode(String categoryCode, Pageable pageable) {
        List<BookDTO> results = new ArrayList<>();
        for (BookEntity b : bookRepo.findByCategoryCode(categoryCode, pageable).getContent()) {
            results.add(bookConverter.toDTO(b));
        }
        return results;
    }

    @Override
    public List<BookDTO> findAllByAuthorCode(String code, Pageable pageable) {
        List<BookDTO> results = new ArrayList<>();
        for (BookEntity b : bookRepo.findAllByAuthorAuthorCode(code, pageable).getContent()) {
            results.add(bookConverter.toDTO(b));
        }
        return results;
    }

    @Override
    public List<BookDTO> findAll(Pageable pageable) {
        List<BookDTO> results = new ArrayList<>();

        //hàm findAll(pageable) sẽ trả về Page<BookEntity>, để chuyển Page thành List thì dùng hàm getContent()
        for (BookEntity b : bookRepo.findAll(pageable).getContent()) {
            results.add(bookConverter.toDTO(b));
        }
        return results;
    }

    @Override
    public List<BookDTO> findAll() {
        List<BookDTO> results = new ArrayList<>();
        for (BookEntity b : bookRepo.findAll()) {
            results.add(bookConverter.toDTO(b));
        }
        return results;
    }


    @Override
    public List<BookDTO> findAllContainTitle(String title, Pageable pageable) {
        List<BookDTO> results = new ArrayList<>();
        for (BookEntity b : bookRepo.findAllByTitleContains(title, pageable).getContent()) {
            results.add(bookConverter.toDTO(b));
        }
        return results;
    }

    @Override
    public int countByCategory(String code) {
        return bookRepo.countAllByCategoryCode(code);
    }

    @Override
    public int countByAuthorCode(String code) {
        return bookRepo.countAllByAuthorAuthorCode(code);
    }

    @Override
    public int countAllByActive(boolean isActive) {
        return bookRepo.countAllByActive(isActive);
    }

    @Override
    public int countAllByTitleContains(String title) {
        return bookRepo.countAllByTitleContains(title);
    }

    @Override
    public List<String> autoCompleteTitle(String title) {
        List<BookEntity> books = bookRepo.findAllByActiveAndTitleContains(true, title);
        List<String> result = new ArrayList<>();
        for (BookEntity b : books) {
            result.add(b.getTitle());
        }
        return result;
    }

    @Override
    public void save(BookDTO book) {
        BookEntity bookEntity = new BookEntity();
        if (book.getId() != 0) {
            bookEntity = bookConverter.fromDtoToEntity(book, bookRepo.findById(book.getId()));
//            bookEntity.setCategory(categoryRepository.findByCategoryID(book.getCategory().getCategoryID()));
//            bookEntity.setAuthor(authorRepository.findByAuthorID(book.getAuthor().getAuthorID()));
        } else
            bookEntity = bookConverter.toEntity(book);
        bookEntity = bookRepo.save(bookEntity);
        for (BookImageDTO i : book.getImages()) {
            BookImageEntity image = new BookImageEntity();
            image.setPath(i.getPath());
            if (book.getId() != 0) image.setBook(bookRepo.findById(book.getId()));
            else image.setBook(bookRepo.findFirstByOrderByIdDesc());
            bookImageRepository.save(image);
        }
    }

    @Override
    public BookDTO findById(int id) {
        return bookConverter.toDTO(bookRepo.findById(id));
    }

    @Override
    public void deleteById(int id) {
        bookRepo.deleteById(id);
    }

}
