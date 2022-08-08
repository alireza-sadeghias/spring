package com.alireza.sadeghi.springboot.repository;

import com.alireza.sadeghi.springboot.domain.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users,Long> {

    Users findByUsername(String username);

}
