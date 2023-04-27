package com.bookstore.api.bookstoreapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bookstore.api.bookstoreapi.model.User;
import com.bookstore.api.bookstoreapi.repository.UserRepository;
import com.bookstore.api.bookstoreapi.service.AuthenticationService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TokenAuthenticationServiceTest {

    @Autowired
    private MockMvc mvc;
    
    @Mock
	private UserRepository userRepository;

	@InjectMocks
	private AuthenticationService authenticationService;

	private User user;

    
    @Test
    public void shouldNotAllowAccessToUnauthenticatedUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/home/unauthorized")).andExpect(status().isUnauthorized());
    }
    

    @Test
    public void shouldAllowAccessToUnauthenticatedUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/home/index")).andExpect(status().isOk());
    }
    
    @BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		user = new User();
		user.setEmail("sa@email.com");
		user.setPassword("123456");
	}

	@Test
	public void loadUserByUsername_ShouldReturnUserDetails_WhenUserIsFound() {
		when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(user));
		UserDetails userDetails = authenticationService.loadUserByUsername(user.getEmail());
					
		assertEquals(user.getEmail(), ((User) userDetails).getEmail());
		assertEquals(user.getPassword(), userDetails.getPassword());
	}

	@Test
	public void loadUserByUsername_ShouldThrowUsernameNotFoundException_WhenUserIsNotFound() {
		when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());
		assertThrows(UsernameNotFoundException.class, () -> {
			authenticationService.loadUserByUsername(user.getEmail());
		});
	}
}