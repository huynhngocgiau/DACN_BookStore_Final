package vn.edu.hcmuaf.st.DACN_BookStore_2023.service;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.CartDTO;

import java.util.List;

public interface ICartService {
    public CartDTO addProduct(String email, int bookId, int quantity);

    public List<CartDTO> getBooks(String email);

    public List<CartDTO> deleteBook(String email, int bookId);

    public List<CartDTO> updateQuantity(String email, int bookId, int quantity);

    public CartDTO getById(int id);

    public void deleteCart(CartDTO cart);
}
