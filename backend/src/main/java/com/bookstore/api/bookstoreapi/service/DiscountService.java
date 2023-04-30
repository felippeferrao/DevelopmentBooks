package com.bookstore.api.bookstoreapi.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bookstore.api.bookstoreapi.model.Book;
import com.bookstore.api.bookstoreapi.model.CartItem;
import com.bookstore.api.bookstoreapi.model.Discount;
import com.bookstore.api.bookstoreapi.model.dto.SetBooksDto;
import com.bookstore.api.bookstoreapi.model.dto.SetDto;

@Service
public class DiscountService {

	
	public static SetBooksDto findBestDiscount(List<CartItem> cartItems, List<Discount> discounts) {
	    
		Map<Book, Integer> books = new HashMap<>();
		for (CartItem item : cartItems) {
		    books.put(item.getBook(), item.getQuantity());
		}
		
		 List<List<Map<Book, Integer>>> allArrangements = generateArrangements(books);

		 //Get the best Option for the Client
		 SetBooksDto bestSet = null;
		 SetBooksDto currentSet = null;
		 List<List<SetDto>> bookSets = null;
		 BigDecimal discount = BigDecimal.ZERO;
		 
		 for (int i = 0; i < allArrangements.size(); i++) {
	            currentSet = new SetBooksDto(BigDecimal.ZERO, BigDecimal.ZERO, null);
	            bookSets = new ArrayList<List<SetDto>>();
	            
	            for (Map<Book, Integer> arrangement : allArrangements.get(i)) {            	
	            	discount = getDiscountByNumberOfBooks(discounts, arrangement.size());
	            	List<SetDto> listBooks = new ArrayList<>(); 
	            	
	            	for (Book key : arrangement.keySet()) {
	            		Integer quantity = arrangement.get(key);
	            		if (quantity == null) {
	            			quantity = 1;
	            		}
	            		key.setDescription("");
	            		SetDto set = new SetDto(key, quantity, discount);
	            		
	            	    currentSet.setTotalPrice(currentSet.getTotalPrice().add(key.getPrice().multiply(new BigDecimal(quantity))));
	            	    currentSet.setTotalDicount(currentSet.getTotalDicount().add(calculateDiscountPrice(key,discount, quantity)));
	            	    
	            	    listBooks.add(set);   
	            	}
	            	
	            	bookSets.add(listBooks);
	            }
	            
	            currentSet.setBookSets(bookSets);
	            if (bestSet == null || bestSet.getTotalDicount().compareTo(currentSet.getTotalDicount()) < 0) {
	            	bestSet = currentSet;
	            }
	     }
		
		 return bestSet;
		
	}

	
	private static BigDecimal getDiscountByNumberOfBooks(List<Discount> discounts, int numberOfBooks) {
		Optional<Discount> discountOpt = discounts.stream()
	            .filter(discount -> discount.getNumBooks() == numberOfBooks)
	            .findFirst();
		
		if (discountOpt.isPresent()) {
		    Discount discount = discountOpt.get();
		    return discount.getDiscount();
		} else {
		    return new BigDecimal(0);
		}
	}
	
	private static BigDecimal calculateDiscountPrice(Book book, BigDecimal discount, Integer quantity) {
	    BigDecimal totalPrice = BigDecimal.ZERO;
	    totalPrice = totalPrice.add(book.getPrice().multiply(new BigDecimal(quantity)));
		    	        
	    return totalPrice.multiply(discount);
    }
	  
	private static List<List<Map<Book, Integer>>> generateArrangements(Map<Book, Integer> books) {
	    List<List<Map<Book, Integer>>> allArrangements = new ArrayList<>();

        for (int groupSize = books.size(); groupSize > 1; groupSize--) {
            List<Map<Book, Integer>> arrangement = new ArrayList<>();
            Map<Book, Integer> remainingBooks = new HashMap<>(books);
            while (!remainingBooks.isEmpty()) {
                Map<Book, Integer> group = extractGroup(remainingBooks, groupSize);
                if (!group.isEmpty()) {
                    arrangement.add(group);
                }
            }
            allArrangements.add(arrangement);
        }

        if (allArrangements.isEmpty()) {
            List<Map<Book, Integer>> singleGroupArrangement = new ArrayList<>();
            singleGroupArrangement.add(books);
            allArrangements.add(singleGroupArrangement);
        }

        return allArrangements;
    }

	private static Map<Book, Integer> extractGroup(Map<Book, Integer> remainingBooks, int groupSize) {
        Map<Book, Integer> group = new HashMap<>();
        for (Map.Entry<Book, Integer> entry : remainingBooks.entrySet()) {
            if (groupSize == 0) {
                break;
            }
            if (entry.getValue() > 0) {
                group.put(entry.getKey(), 1);
                entry.setValue(entry.getValue() - 1);
                groupSize--;
            }
        }
        remainingBooks.entrySet().removeIf(entry -> entry.getValue() == 0);
        return group;
    }
	
}