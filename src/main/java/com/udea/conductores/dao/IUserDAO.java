package com.udea.conductores.dao;

import com.udea.conductores.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface IUserDAO extends CrudRepository<User, Long> {
    Optional<User> findByCedula(String cedula);
    void deleteByCedula(String cedula);
}