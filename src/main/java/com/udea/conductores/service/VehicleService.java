package com.udea.conductores.service;

import com.udea.conductores.dao.IDriverDAO;
import com.udea.conductores.dao.IVehicleDAO;
import com.udea.conductores.exceptions.DriverNotFoundException;
import com.udea.conductores.model.Driver;
import com.udea.conductores.model.User;
import com.udea.conductores.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    @Autowired
    private IVehicleDAO dao;
    @Autowired
    private IDriverDAO driverDAO;

    public void save(Vehicle t) {
        dao.save(t);
    }

    @Transactional
    public String delete(long id) {
        dao.deleteById(id);
        return "Vehicle Removed";
    }

    @Transactional
    public String deleteCedula(String cedula) {
        Optional<Driver> driverOpt = driverDAO.findByCedula(cedula);
        if (driverOpt.isPresent()){
            dao.deleteByIdDriver(driverOpt.get());
            return "Vehicule with "+cedula+" deleted";
        } throw new DriverNotFoundException("No driver found");
    }

    public Iterable<Vehicle> list() {
        return dao.findAll();
    }

    public Optional<Vehicle> listId(long id) {
        return dao.findById(id);
    }

    public Optional<Vehicle> listDriver(String cedula) {
        Optional<Driver> driverOptional = driverDAO.findByCedula(cedula);
        if (driverOptional.isPresent()){
            return dao.findByIdDriver(driverOptional.get());
        } throw new DriverNotFoundException("No driver found");
    }

    public Vehicle update(Vehicle d) {
        Vehicle existingVehicle = dao.findById(d.getIdVehicle()).orElse(null);
        existingVehicle.setIdVehicle(d.getIdVehicle()); // Assuming 'idVehicle' is the ID field
        existingVehicle.setIdDriver(d.getIdDriver());
        existingVehicle.setManufacturer(d.getManufacturer());
        existingVehicle.setModel(d.getModel());
        existingVehicle.setDescription(d.getDescription());
        existingVehicle.setLicensePlate(d.getLicensePlate());
        existingVehicle.setTechnicalExpirationDate(d.getTechnicalExpirationDate());
        existingVehicle.setInsuranceExpirationDate(d.getInsuranceExpirationDate());
        return dao.save(existingVehicle);
    }


}