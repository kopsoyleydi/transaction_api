package com.example.client_service.service;

import com.example.client_service.data.repointer.UserRepoInter;
import com.example.client_service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final UserRepoInter userRepoInter;

    public Double minusBalance(Long account, Double sum){
        User user = userRepoInter.getUserById(account);
        user.setBalance(user.getBalance() - sum);
        user.setRemaining_limit(user.getRemaining_limit() - sum);
        userRepoInter.change(user);
        return userRepoInter.getRemainingLimit(account);
    }
}
