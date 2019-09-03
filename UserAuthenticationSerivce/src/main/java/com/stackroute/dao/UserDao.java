package com.stackroute.dao;


import com.stackroute.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserDao extends JpaRepository<Users, Long> {
    Users findByEmail(String email);

    @Modifying
    @Transactional
    void deleteByEmail(String email);



}
