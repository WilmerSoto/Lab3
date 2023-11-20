package com.udea.conductores.dao;

import com.udea.conductores.model.Driver;
import com.udea.conductores.model.User;
import com.udea.conductores.model.Vehicle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IVehicleDAO extends CrudRepository<Vehicle, Long> {
    Optional<Vehicle> findByIdDriver(Driver IdDriver);

    void deleteByIdDriver(Driver IdDriver);
}