package com.udea.conductores.service;

import com.udea.conductores.dao.IDriverDAO;
import com.udea.conductores.exceptions.DriverNotFoundException;
import com.udea.conductores.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverService {
    @Autowired
    private IDriverDAO dao;

    public Driver save(Driver t) {
        return dao.save(t);
    }

    public String delete(long id) {
        dao.deleteById(id);
        return "Driver Removed";
    }

    public Iterable<Driver> list() {
        return dao.findAll();
    }

    public Optional<Driver> listId(long id) {
        return dao.findById(id);
    }

    public Driver update(Driver t) {
        Driver existingDriver = dao.findById(t.getIdDriver()).orElse(null);
        existingDriver.setFirstName(t.getFirstName());
        existingDriver.setLastName(t.getLastName());
        existingDriver.setCedula(t.getCedula());
        existingDriver.setEmail(t.getEmail());
        existingDriver.setRating(t.getRating());
        existingDriver.setLicenciaCon(t.getLicenciaCon());
        existingDriver.setAvailable(t.getAvailable());

        return dao.save(existingDriver);
    }

    public List<Driver> viewBestDriver() throws DriverNotFoundException {
        List<Driver> drivers = dao.viewBestDriver();
        if(drivers.size()>0)
            return drivers;
        else throw new DriverNotFoundException("No driver found with rating >=4");

    }

}