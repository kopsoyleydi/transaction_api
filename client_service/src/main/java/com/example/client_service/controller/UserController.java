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



    @GetMapping("/limit/account/{id}")
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

    @GetMapping(value = "/limit/list/bad/{id}")
    public ResponseEntity<?> getAccountLimitTransactions(@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.getAccountTransactions(id, false));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }

    @GetMapping(value = "/limit/list/ok/{id}")
    public ResponseEntity<?> getAccountTransactions(@PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.getAccountTransactions(id, true));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }


}
