package com.udea.conductores.service;

import com.udea.conductores.dao.IUserDAO;
import com.udea.conductores.exceptions.DriverNotFoundException;
import com.udea.conductores.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private IUserDAO dao;

    public void save(User t) {
        dao.save(t);
    }

    public String delete(long id) {
        dao.deleteById(id);
        return "User Removed";
    }

    public String deleteCedula(String cedula) {
        dao.deleteByCedula(cedula);
        return "User Removed";
    }

    public Iterable<User> list() {
        return dao.findAll();
    }

    public Optional<User> listId(long id) {
        return dao.findById(id);
    }

    public Optional<User> listCedula(String cedula) {
        return dao.findByCedula(cedula);
    }

    public User update(User t) {
        User existingUser = dao.findById(t.getIdUser()).orElse(null);
        existingUser.setFirstName(t.getFirstName());
        existingUser.setLastName(t.getLastName());
        existingUser.setCedula(t.getCedula());
        existingUser.setEmail(t.getEmail());
        existingUser.setCelular(t.getCelular());
        existingUser.setNroViajes(t.getNroViajes());
        return dao.save(existingUser);
    }

}