package vn.edu.hcmuaf.st.DACN_BookStore_2023.service;

import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.AuthorDTO;

import java.util.List;

public interface IAuthorService {
    public List<AuthorDTO> findAll();

    public void save(AuthorDTO author);

    public void update(AuthorDTO author, int id);

    public AuthorDTO findById(int id);

    public void delete(int id);
}
