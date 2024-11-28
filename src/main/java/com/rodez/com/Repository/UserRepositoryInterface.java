package com.rodez.com.Repository;

import com.rodez.com.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryInterface extends JpaRepository<User, Integer> {






}
