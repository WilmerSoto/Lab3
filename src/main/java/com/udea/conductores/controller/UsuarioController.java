package com.udea.conductores.controller;

import com.udea.conductores.exceptions.CedulaNotFoundException;
import com.udea.conductores.exceptions.ModelNotFoundException;
import com.udea.conductores.model.User;
import com.udea.conductores.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@Api(value = "User Managment System", description = "Operations to Users")
public class UsuarioController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "Add Driver")
    @PostMapping("/save")
    public long save(
            @ApiParam(value = "Driver Object Store in DB table", required = true)
            @RequestBody User user) {
        userService.save(user);
        return user.getIdUser();
    }

    @ApiOperation(value = "View a list of available drivers", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to viwe the resource"),
            @ApiResponse(code = 403, message = "Accessing Resource that you are trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Driver not found")
    })
    @GetMapping("/listAll")
    public Iterable<User> listAllUsers() {
        return userService.list();
    }

    @ApiOperation(value = "Get User by ID")
    @GetMapping("/list/{id}")
    public User listUserById(@ApiParam(value = "ID of the User to be retrieved from DB", required = true)
                                 @PathVariable("id") int id) {
        Optional<User> user = userService.listId(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new ModelNotFoundException("Invalid driver ID");
    }

    @ApiOperation(value = "Get User by Cedula")
    @GetMapping("/list/{cedula}")
    public User listUserByCedula(@ApiParam(value = "Cedula of the User to be retrieved from DB", required = true)
                             @PathVariable("cedula") String cedula) {
        Optional<User> user = userService.listCedula(cedula);
        if (user.isPresent()) {
            return user.get();
        }
        throw new CedulaNotFoundException("Invalid User Cedula");
    }

    @ApiOperation(value = "Update User")
    @PutMapping
    public User updateService(@RequestBody User user) {
        return userService.update(user);
    }

    @ApiOperation(value = "Delete User by ID")
    @DeleteMapping("delete/{id}")
    public String deleteUser(@PathVariable long id) {
        return userService.delete(id);
    }

    @ApiOperation(value = "Delete User by Cedula")
    @DeleteMapping("delete/{cedula}")
    public String deleteUserCedula(@PathVariable String cedula) {
        return userService.deleteCedula(cedula);
    }
}
