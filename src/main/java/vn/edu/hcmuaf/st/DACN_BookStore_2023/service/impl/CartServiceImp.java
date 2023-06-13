package vn.edu.hcmuaf.st.DACN_BookStore_2023.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.converter.CartConverter;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.BookDTO;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.CartDTO;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.dto.UserDTO;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.entity.CartEntity;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.repository.CartRepository;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.service.IBookService;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.service.ICartService;
import vn.edu.hcmuaf.st.DACN_BookStore_2023.service.IUserService;

import java.util.ArrayList;
import java.util.List;


@Service
public class CartServiceImp implements ICartService {
    @Autowired
    private CartRepository cartRepo;
    @Autowired
    private IUserService userService;
    @Autowired
    private IBookService bookService;
    @Autowired
    private CartConverter cartConverter;

    @Override
    public CartDTO addProduct(String email, int bookId, int quantity) {
        //tim user dang dang nhap
        UserDTO currentUser = userService.findByEmailAndIsEnable(email);
        //tim xem user co cart chua
        CartEntity cart = cartRepo.findByUserUserIDAndBookId(currentUser.getUserID(), bookId);
        //neu chua thi tao moi cart, them sach vua bam vo
        CartDTO newCart = new CartDTO();
        BookDTO book = bookService.findById(bookId);
        if (cart == null) {
            newCart.setUser(currentUser);
            newCart.setQuantity(1);
            newCart.setBook(book);
        } else {
            //neu cart da ton tai thi lay id cart set lai cho cartdto
            newCart.setCartID(cart.getCartID());
            newCart.setUser(currentUser);
            newCart.setBook(book);
            newCart.setQuantity(cart.getQuantity() + quantity);
        }
        cartRepo.save(cartConverter.toEntity(newCart));
        return newCart;
    }

    @Override
    public List<CartDTO> getBooks(String email) {
        //tim tất cả cart có thuộc user
        List<CartEntity> booksEntity = cartRepo.findAllByUserUserID(userService.findByEmailAndIsEnable(email).getUserID());
        List<CartDTO> result = new ArrayList<>();
        for (CartEntity cartEntity : booksEntity) {
            result.add(cartConverter.toDTO(cartEntity));
        }
        return result;
    }

    @Override
    public List<CartDTO> deleteBook(String email, int bookId) {
        //tim cart cua user, chua book
        CartEntity userCart = cartRepo.findByUserUserIDAndBookId(userService.findByEmailAndIsEnable(email).getUserID(), bookId);
        cartRepo.delete(userCart);
        return getBooks(email);

    }

    @Override
    public List<CartDTO> updateQuantity(String email, int bookId, int quantity) {
        CartEntity cartDb = cartRepo.findByUserUserIDAndBookId(userService.findByEmailAndIsEnable(email).getUserID(), bookId);
        cartDb.setQuantity(quantity);
        cartRepo.save(cartDb);
        return getBooks(email);
    }

    @Override
    public CartDTO getById(int id) {
        return cartConverter.toDTO(cartRepo.getByCartID(id));
    }

    @Override
    public void deleteCart(CartDTO cart) {
        cartRepo.delete(cartConverter.toEntity(cart));
    }
}
