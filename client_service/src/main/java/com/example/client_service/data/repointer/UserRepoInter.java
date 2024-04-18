package com.example.client_service.data.repointer;

import com.example.client_service.model.User;

import java.util.List;

public interface UserRepoInter {

    User insert(User user);

    void change(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    Double getRemainingLimit(Long userId);

    Double getAccountLimit(Long userId);

    void setNewLimit(Long account, Double limit, String currency);
}
