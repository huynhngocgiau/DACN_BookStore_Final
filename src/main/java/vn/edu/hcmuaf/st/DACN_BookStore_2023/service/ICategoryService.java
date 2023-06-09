package vn.edu.hcmuaf.st.DACN_BookStore_2023.service;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.CategoryDTO;

import java.util.List;

public interface ICategoryService {
    public List<CategoryDTO> findAll();

    public CategoryDTO findById(int id);

    public List<CategoryDTO> findTenCat();
}
