package com.example.client_service.service;

import com.example.client_service.data.repointer.UserRepoInter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final UserRepoInter userRepoInter;

    public Double minusBalance(Long account, Double sum){
        return userRepoInter.minusBalance(account, sum);
    }
}
