package com.rodez.com.Repository.users;

import com.rodez.com.Entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryInterface extends JpaRepository<User, Integer> {

    @Query(value="SELECT * FROM \"USER\"  WHERE \"roleUser\" <> 'personnel' ",nativeQuery = true)
    List<User> getAllIntervenant();

    List<User> findAll(Sort sort);

    @Query(value="SELECT * FROM \"USER\" WHERE ? = registration ", nativeQuery = true)
    User getByRegistration(String registration);
}
