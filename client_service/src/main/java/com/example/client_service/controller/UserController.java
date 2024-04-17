package com.example.client_service.controller;

import com.example.client_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
