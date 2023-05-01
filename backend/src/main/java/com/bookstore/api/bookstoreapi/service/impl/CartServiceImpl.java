package com.bookstore.api.bookstoreapi.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bookstore.api.bookstoreapi.converter.CartConverter;
import com.bookstore.api.bookstoreapi.model.Book;
import com.bookstore.api.bookstoreapi.model.Cart;
import com.bookstore.api.bookstoreapi.model.CartItem;
import com.bookstore.api.bookstoreapi.model.CartItemKey;
import com.bookstore.api.bookstoreapi.model.User;
import com.bookstore.api.bookstoreapi.model.constants.CartStatusConst;
import com.bookstore.api.bookstoreapi.model.dto.CartDto;
import com.bookstore.api.bookstoreapi.model.dto.CartItemDto;
import com.bookstore.api.bookstoreapi.model.dto.SetBooksDto;
import com.bookstore.api.bookstoreapi.repository.BookRepository;
import com.bookstore.api.bookstoreapi.repository.CartRepository;
import com.bookstore.api.bookstoreapi.repository.DiscountRepository;
import com.bookstore.api.bookstoreapi.repository.UserRepository;
import com.bookstore.api.bookstoreapi.service.CartService;
import com.bookstore.api.bookstoreapi.service.DiscountService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
    private BookRepository bookRepository;
    
	@Autowired
    private CartRepository cartRepository;
    
	@Autowired
	private DiscountRepository discountRepository;
		
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartConverter cartConverter;
 
        
    private User getUserLogged() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User user = userRepository.findByName((String) auth.getPrincipal())
                .orElseThrow(() -> new RuntimeException("User not found"));
    
        return user;
    }
    
   
    private Cart getUserCart() {
   	 User userLogged = getUserLogged();	 
   	 Cart recentCart = cartRepository.findFirstByCartUserAndStatusOrderByIdDesc(userLogged, CartStatusConst.OPEN);
        if (recentCart != null) {
            return recentCart;
        } else {
            //Create a new Cart if it doesn't exists
            CartDto newCartDto = new CartDto();
            newCartDto.setCartUser(userLogged);
            newCartDto.setStatus(CartStatusConst.OPEN);
            Cart newCart = cartConverter.dtoToEntity(newCartDto);
            Cart savedCart = cartRepository.save(newCart);
            return savedCart;
        }
    }
    
    
    @Override
    public ResponseEntity<CartDto> getMyCart() {
    	Cart cart = getUserCart();
    	SetBooksDto discountSet = DiscountService.findBestDiscount(cart.getCartItems(), discountRepository.findAll());
    	
    	return new ResponseEntity<>(cartConverter.entityToDto(cart, discountSet), HttpStatus.CREATED);    	
    }
    
     
    @Override
    public ResponseEntity<CartDto> addItemToCart(CartItemDto cartItemDto) {
        Cart cart = getUserCart();

        Optional<CartItem> optionalCartItem = cart.getCartItems()
                .stream()
                .filter(cartItem -> cartItem.getBook().getId().equals(cartItemDto.getBookId()))
                .findFirst();

        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            cartItem.setQuantity(cartItemDto.getQuantity());
        } else {
        	Book book = bookRepository.findById(cartItemDto.getBookId())
                    .orElseThrow(() -> new RuntimeException("Book not found"));

            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setBook(book);
            cartItem.setQuantity(cartItemDto.getQuantity());
            cartItem.setId(new CartItemKey(cart.getId(), book.getId()));
            cart.getCartItems().add(cartItem);
        }

        cartRepository.save(cart);
       
        SetBooksDto discountSet = DiscountService.findBestDiscount(cart.getCartItems(), discountRepository.findAll());
        

        return new ResponseEntity<>(cartConverter.entityToDto(cart, discountSet), HttpStatus.CREATED);
    }
    
    @Override
    public ResponseEntity<CartDto> removeItemFromCart(CartItemDto cartItemDto) {
        Cart cart = getUserCart();
        boolean itemRemoved = cart.getCartItems()
                .removeIf(cartItem -> cartItem.getBook().getId().equals(cartItemDto.getBookId()));

        if (itemRemoved) {
            cartRepository.save(cart);
        }
        
        SetBooksDto discountSet = DiscountService.findBestDiscount(cart.getCartItems(), discountRepository.findAll());        

        return new ResponseEntity<>(cartConverter.entityToDto(cart, discountSet), HttpStatus.CREATED);
    }
    
    @Override
    public ResponseEntity<CartDto> clearCart() {
        Cart cart = getUserCart();
                
        if (cart.getCartItems().size() > 0) {
            cart.getCartItems().clear();
        	cartRepository.save(cart);
        }
        
        SetBooksDto discountSet = DiscountService.findBestDiscount(cart.getCartItems(), discountRepository.findAll());        

        return new ResponseEntity<>(cartConverter.entityToDto(cart, discountSet), HttpStatus.CREATED);
    }
    
    @Override
    public ResponseEntity<CartDto> checkoutCart() {
        Cart cart = getUserCart();               
        cart.setStatus(CartStatusConst.CHECKOUT);
        
        //TODO: Fake Method just to Save the Current Cart as Finished and Start a New One
        
        return this.getMyCart();
    }

    
}
