package com.api.user_api.repository;

import com.api.user_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface extends the JpaRepository interface and provides the CRUD operations for the User entity
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
