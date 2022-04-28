package com.axeman.auth.repositories;

import com.axeman.auth.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
