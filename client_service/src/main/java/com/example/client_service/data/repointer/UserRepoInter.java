package com.example.client_service.data.repointer;

import com.example.client_service.model.User;

import java.util.List;

public interface UserRepoInter {

    User insert(User user);

    User change(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    Double getRemainingLimit(Long userId);

    Double getAccountLimit(Long userId);

    Double minusBalance(Long account, Double sum);

    Double setNewLimit(Long account, Double limit, String currency);
}
