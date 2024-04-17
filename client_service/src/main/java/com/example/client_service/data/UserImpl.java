package com.example.client_service.data;

import com.example.client_service.data.repointer.UserRepoInter;
import com.example.client_service.data.repository.UserRepository;
import com.example.client_service.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
    public User change(User user) {
        return userRepository.save(user);
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
    public Double getRemainingLimit(Long userId) {
        return userRepository.getRemainingLimit(userId);
    }

    @Override
    public Double getAccountLimit(Long userId) {
        return userRepository.getAccountLimit(userId);
    }

    @Override
    public Double minusBalance(Long account, Double sum) {
        return userRepository.minusBalance(account, sum);
    }
}
