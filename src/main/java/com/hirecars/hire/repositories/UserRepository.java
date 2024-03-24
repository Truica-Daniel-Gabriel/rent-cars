package com.hirecars.hire.repositories;

import com.hirecars.hire.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
