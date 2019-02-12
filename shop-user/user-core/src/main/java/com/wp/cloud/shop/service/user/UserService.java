package com.wp.cloud.shop.service.user;

import com.wp.cloud.shop.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService extends JpaRepository<User, Long>, JpaSpecificationExecutor {
}

