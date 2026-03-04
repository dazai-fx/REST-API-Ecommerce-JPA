package org.iesvdm.restapiecommercejpa.repository;

import org.iesvdm.restapiecommercejpa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // siempre que extendemos de JpaRepository en los dos genericos va la clase del repository
    // y el tipo de dato del id
}
