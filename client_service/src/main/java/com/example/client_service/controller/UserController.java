package com.example.client_service.controller;

import com.example.client_service.dto.request.SetLimit;
import com.example.client_service.dto.response.UserResponse;
import com.example.client_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/user")
public class UserController {

    private final UserService userService;



    @GetMapping("/getRemainingLimit/{id}")
    public ResponseEntity<?> getRemainingLimit(@PathVariable Long id){
        try {
            return userService.getRemainingLimit(id);
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }

    @GetMapping("/getAccountLimit/{id}")
    public ResponseEntity<?> getAccountLimit(@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.getAccountLimit(id));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }

    @GetMapping("/{id}")
    public UserResponse getAccount(@PathVariable Long id){
        try {
            int test = 0;
            return userService.getAccount(id);
        }
        catch (Exception e){
            return new UserResponse();
        }
    }

    @PutMapping("/setLimit/{id}")
    public ResponseEntity<?> setLimit(@PathVariable Long id, @RequestBody SetLimit setLimit){
        try {
            return ResponseEntity.ok(userService.setNewLimit(id, setLimit.getLimit(), setLimit.getCurrency_code()));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }

    @GetMapping(value = "/getAccountLimitTransactions/{id}")
    public ResponseEntity<?> getAccountLimitTransactions(@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.getAccountTransactions(id));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }
}
