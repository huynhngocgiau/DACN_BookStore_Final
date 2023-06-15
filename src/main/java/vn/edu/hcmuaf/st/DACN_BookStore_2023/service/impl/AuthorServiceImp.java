package vn.edu.hcmuaf.st.DACN_BookStore_2023.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.converter.AuthorConverter;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.AuthorDTO;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.entity.AuthorEntity;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.repository.AuthorRepository;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.service.IAuthorService;

import java.util.ArrayList;
import java.util.List;
@Service
public class AuthorServiceImp implements IAuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorConverter authorConverter;

    @Override
    public List<AuthorDTO> findAll() {
        List<AuthorDTO> result = new ArrayList<>();
        for (AuthorEntity a : authorRepository.findAll()) {
            result.add(authorConverter.toDTO(a));
        }
        return result;
    }

    @Override
    public void save(AuthorDTO author) {
        authorRepository.save(authorConverter.toEntity(author));
    }

    @Override
    public void update(AuthorDTO author, int id) {
        authorRepository.updateAuthor(author.getName(), author.getAuthorCode(), author.getCreatedAt(), author.getUpdatedAt(), id);
    }

    @Override
    public AuthorDTO findById(int id) {
        return authorConverter.toDTO(authorRepository.findByAuthorID(id));
    }

    @Override
    public void delete(int id) {
        authorRepository.deleteByAuthorID(id);
    }
}
