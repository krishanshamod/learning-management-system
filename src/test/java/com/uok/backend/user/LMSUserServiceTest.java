package com.uok.backend.user;

import com.uok.backend.security.TokenValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {LMSUserService.class})
@ExtendWith(MockitoExtension.class)
class LMSUserServiceTest {

    @Autowired
    private LMSUserService underTest;

    @Mock
    private UserRepository userRepository;
    @Mock
    private TokenValidator tokenValidator;

    @BeforeEach
    void setUp() {
        underTest = new LMSUserService(userRepository, tokenValidator);
    }

    @Test
    void shouldAddUserWhenDoesNotExist() {

        //given
        User user = new User("pasandevin@gmail.com", "Pasan", "Jayawardene", "student");

        //when
        when(userRepository.findById(anyString())).thenReturn(Optional.empty());
        when(this.tokenValidator.getEmailFromToken((String) any())).thenReturn(user.getEmail());
        when(this.tokenValidator.getFirstNameFromToken((String) any())).thenReturn(user.getFirstName());
        when(this.tokenValidator.getLastNameFromToken((String) any())).thenReturn(user.getLastName());
        when(this.tokenValidator.getRoleFromToken((String) any())).thenReturn(user.getRole());
        ResponseEntity response = underTest.addUser();

        //then
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualToComparingFieldByField(user);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldNotAddUserWhenExists() {
        //given
        User user = new User("pasandevin@gmail.com", "Pasan", "Jayawardene", "student");
        given(userRepository.findById(any())).willReturn(Optional.of(user));

        //when
        ResponseEntity response = underTest.addUser();

        //then
        verify(userRepository, never()).save(any());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldReturnAUserWhenGetTokenUserCalled() {

        //given
        User user = new User("pasandevin@gmail.com", "Pasan", "Jayawardene", "student");

        //when
        when(this.tokenValidator.getEmailFromToken((String) any())).thenReturn(user.getEmail());
        when(this.tokenValidator.getFirstNameFromToken((String) any())).thenReturn(user.getFirstName());
        when(this.tokenValidator.getLastNameFromToken((String) any())).thenReturn(user.getLastName());
        when(this.tokenValidator.getRoleFromToken((String) any())).thenReturn(user.getRole());

        //then
        User actualTokenUser = underTest.getTokenUser();
        assertThat(actualTokenUser).isEqualToComparingFieldByField(user);
    }
}