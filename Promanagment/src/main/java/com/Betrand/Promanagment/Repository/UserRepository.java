package com.Betrand.Promanagment.Repository;

import com.Betrand.Promanagment.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);


}
