package com.example.client_service.data.repository;

import com.example.client_service.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    User findAllById(Long id);


    @Query("select u.limit_sum from User u where u.id = :userId")
    Double getAccountLimit(Long userId);

    @Query("select u.balance from User u where u.id = :userId")
    Double getBalanceByUserId(Long userId);

    @Modifying(clearAutomatically = true)
    @Query("update User u set u.limit_sum = :limit," +
            " u.limit_datetime =  :localDateTime, " +
            " u.limit_currency_shortname = :limit_currency" +
            "  where u.id = :account")
    void setLimit(Long account, Double limit, String limit_currency, LocalDateTime localDateTime);

}
