package com.example.client_service.controller;

import com.example.client_service.dto.request.SetLimit;
import com.example.client_service.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void setLimit_Success() {
        Long id = 1L;

        SetLimit setLimit = new SetLimit("USD", 100.0);

        Double response = (Double) userService.setNewLimit
                (id, setLimit.getLimit(), setLimit.getCurrency_code()).getBody();

        assertEquals(100.0, response);
    }

    @Test
    void setLimit_InternalServerError() {
        Long id = 1L;
        SetLimit setLimit = new SetLimit("USD", 100.0);
        when(userService.setNewLimit(id, setLimit.getLimit(), setLimit.getCurrency_code())).thenThrow(new RuntimeException("Test Exception"));
        ResponseEntity<?> response = userController.setLimit(id, setLimit);
        assertEquals(ResponseEntity.internalServerError().body("Something went wrong"), response);
        verify(userService, times(1)).setNewLimit(id, setLimit.getLimit(), setLimit.getCurrency_code());
    }
}
