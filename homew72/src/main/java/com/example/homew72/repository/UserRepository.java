package com.example.homew72.repository;

import com.example.homew72.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserByMail(String mail);
    boolean existsUserByMail(String mail);

}
