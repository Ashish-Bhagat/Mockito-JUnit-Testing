package com.xadmin.mockito.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xadmin.mockito.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
