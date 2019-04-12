package com.wp.cloud.shop.service.user;

import com.wp.cloud.shop.entity.user.StuRelatives;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserService extends JpaRepository<StuRelatives, Long>, JpaSpecificationExecutor {
}

