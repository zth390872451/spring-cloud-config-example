package com.web.repository;

import com.web.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2016/11/23 0023.
 */
public interface UserRepository extends JpaRepository<User,Long>{
}
