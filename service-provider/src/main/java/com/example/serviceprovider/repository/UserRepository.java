package com.example.serviceprovider.repository;

import com.example.serviceprovider.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Repository
public interface UserRepository  extends JpaRepository<User,Long> {

    @Query(nativeQuery = true, value = "select * from user where id > 0")
    public List<User> getUserbysql();



}
