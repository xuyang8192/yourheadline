package com.yourheadline.dao;

import com.yourheadline.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByUserName(String name);
    List<UserEntity> findByUserId(Integer userId);
}
