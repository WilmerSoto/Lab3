package com.udea.conductores.service;

import com.udea.conductores.dao.IDriverDAO;
import com.udea.conductores.exceptions.DriverNotFoundException;
import com.udea.conductores.model.Driver;
import com.udea.conductores.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DriverService {
    @Autowired
    private IDriverDAO dao;

    public void save(Driver t) {
        dao.save(t);
    }

    @Transactional
    public String delete(long id) {
        dao.deleteById(id);
        return "Driver Removed";
    }

    @Transactional
    public String deleteCedula(String cedula) {
        dao.deleteByCedula(cedula);
        return "Driver Removed";
    }

    public Iterable<Driver> list() {
        return dao.findAll();
    }

    public Optional<Driver> listId(long id) {
        return dao.findById(id);
    }

    public Optional<Driver> listCedula(String cedula) {
        return dao.findByCedula(cedula);
    }

    public Driver update(Driver d) {
        Driver existingDriver = dao.findById(d.getIdDriver()).orElse(null);
        existingDriver.setFirstName(d.getFirstName());
        existingDriver.setLastName(d.getLastName());
        existingDriver.setCedula(d.getCedula());
        existingDriver.setEmail(d.getEmail());
        existingDriver.setRating(d.getRating());
        existingDriver.setLicenciaCon(d.getLicenciaCon());
        existingDriver.setAvailable(d.getAvailable());

        return dao.save(existingDriver);
    }

    public List<Driver> viewBestDriver() throws DriverNotFoundException {
        List<Driver> drivers = dao.viewBestDriver();
        if(drivers.size()>0)
            return drivers;
        else throw new DriverNotFoundException("No driver found with rating >=4");

    }


}