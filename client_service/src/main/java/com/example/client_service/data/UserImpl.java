package com.example.client_service.data;

import com.example.client_service.data.repointer.UserRepoInter;
import com.example.client_service.data.repository.UserRepository;
import com.example.client_service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserImpl implements UserRepoInter {

    private final UserRepository userRepository;
    @Override
    public User insert(User user) {
        return userRepository.save(user);
    }

    @Override
    public void change(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findAllById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public Double getAccountLimit(Long userId) {
        return userRepository.getAccountLimit(userId);
    }

    @Override
    public Double getBalance(Long userId) {
        return userRepository.getBalanceByUserId(userId);
    }

    @Override
    public void setNewLimit(Long account, Double limit, String currency) {
        userRepository.setLimit(account, limit, currency, LocalDateTime.now(ZoneId.of(("Asia/Tashkent"))));
    }
}
