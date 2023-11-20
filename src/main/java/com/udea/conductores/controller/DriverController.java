package com.udea.conductores.controller;

import com.udea.conductores.exceptions.CedulaNotFoundException;
import com.udea.conductores.exceptions.InvalidRating;
import com.udea.conductores.exceptions.ModelNotFoundException;
import com.udea.conductores.exceptions.ModelNotFoundException;
import com.udea.conductores.model.Driver;
import com.udea.conductores.model.User;
import com.udea.conductores.model.Vehicle;
import com.udea.conductores.service.DriverService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/driver")
@CrossOrigin("*")
@Api(value = "Driver Managment System", description = "Operations to Drivers")
public class DriverController {
    @Autowired
    private DriverService driverService;

    @ApiOperation(value = "Add Driver")
    @PostMapping("/save")
    public long save(
            @ApiParam(value = "Driver Object Store in DB table", required = true)
            @RequestBody Driver driver) throws InvalidRating {
        if (driver.getRating() > 5)
            throw new InvalidRating("Id should be less than o equal 5");
        driverService.save(driver);
        return driver.getIdDriver();
    }

    @ApiOperation(value = "View a list of available drivers", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to viwe the resource"),
            @ApiResponse(code = 403, message = "Accessing Resource that you are trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Driver not found")
    })
    @GetMapping("/listAll")
    public Iterable<Driver> listAllDrivers() {
        return driverService.list();
    }

    @ApiOperation(value = "Get Driver by ID")
    @GetMapping("/listById/{id}")
    public Driver listDriverById(@ApiParam(value = "ID of the Driver to be retrieved from DB", required = true)
                                 @PathVariable("id") int id) {
        Optional<Driver> driver = driverService.listId(id);
        if (driver.isPresent()) {
            return driver.get();
        }
        throw new ModelNotFoundException("ID de driver invalido");
    }

    @ApiOperation(value = "Get Driver by Cedula")
    @GetMapping("/listByCedula/{cedula}")
    public Driver listDriverByCedula(@ApiParam(value = "Cedula of the Driver to be retrieved from DB", required = true)
                                         @PathVariable("cedula") String cedula) {
        Optional<Driver> driver = driverService.listCedula(cedula);
        if (driver.isPresent()) {
            return driver.get();
        }
        throw new CedulaNotFoundException("Invalid Driver Cedula");
    }

//    @ApiOperation(value = "Get Driver Vehicle")
//    @GetMapping("/listVehicle/{cedula}")
//    public Vehicle listDriverVehicle(@ApiParam(value = "Cedula of the Driver Vehicle to be retrieved from DB", required = true)
//                                     @PathVariable("cedula") String cedula) {
//        Optional<Driver> driver = driverService.listCedula(cedula);
//        if (driver.isPresent()) {
//            Driver driverVehicle = driver.get();
//            return driverVehicle.getIdVehicle();
//        }
//        throw new CedulaNotFoundException("Invalid Driver Cedula");
//    }

    @ApiOperation(value = "Get Top Driver")
    @GetMapping("/topDrivers")
    public ResponseEntity<List<Driver>> viewBestDrivers() {
        List<Driver> list = driverService.viewBestDriver();
        return new ResponseEntity<List<Driver>>(list, HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "Update Driver")
    @PutMapping("/update")
    public Driver updateService(@RequestBody Driver driver) {
        return driverService.update(driver);
    }


    @ApiOperation(value = "Delete Driver by ID")
    @DeleteMapping("deleteById/{id}")
    public String deleteDriver(@PathVariable long id) {
        return driverService.delete(id);
    }

    @ApiOperation(value = "Delete Driver by Cedula")
    @DeleteMapping("deleteByCedula/{cedula}")
    public String deleteDriverCedula(@PathVariable String cedula) {
        return driverService.deleteCedula(cedula);
    }
}
