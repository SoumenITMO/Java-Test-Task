package com.example.fob.Repository;

import com.example.fob.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface Users extends JpaRepository<User, Long> {

    @Query(value = "select id, fname, lname, idcode, password, email, bday  from users where (idcode = ?1 or email = ?2) " +
            "and password = ?3", nativeQuery = true)
    Optional<User> checkUserExists(String idCode, String email, String password);
    User getUsersByIdcodeOrEmail(int idCode, String email);
}
