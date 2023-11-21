package com.udea.conductores.controller;

import com.udea.conductores.exceptions.CedulaNotFoundException;
import com.udea.conductores.exceptions.InvalidRating;
import com.udea.conductores.exceptions.ModelNotFoundException;
import com.udea.conductores.model.Driver;
import com.udea.conductores.model.Vehicle;
import com.udea.conductores.service.VehicleService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicle")
@CrossOrigin("*")
@Api(value = "Driver Managment System", description = "Operations to Vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @ApiOperation(value = "Add Vehicle")
    @PostMapping("/save")
    public long save(
            @ApiParam(value = "Vehicle Object Store in DB table", required = true)
            @RequestBody Vehicle vehicle) {
        vehicleService.save(vehicle);
        return vehicle.getIdVehicle();
    }

    @ApiOperation(value = "View a list of available Vehicles", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to viwe the resource"),
            @ApiResponse(code = 403, message = "Accessing Resource that you are trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "Driver not found")
    })
    @GetMapping("/listAll")
    public Iterable<Vehicle> listAllVehicles() {
        return vehicleService.list();
    }

    @ApiOperation(value = "Get Vehicle by ID")
    @GetMapping("/listById/{id}")
    public Vehicle listVehicleById(@ApiParam(value = "ID of the Vehicle to be retrieved from DB", required = true)
                                 @PathVariable("id") long id) {
        Optional<Vehicle> vehicle = vehicleService.listId(id);
        if (vehicle.isPresent()) {
            return vehicle.get();
        }
        throw new ModelNotFoundException("Invalid Vehicle ID");
    }

    @ApiOperation(value = "Get Vehicle by Cedula of the Driver")
    @GetMapping("/listByCedula/{cedula}")
    public Vehicle listDriverByCedula(@ApiParam(value = "Cedula of the Vehicle owner to be retrieved from DB", required = true)
                                     @PathVariable("cedula") String cedula) {
        Optional<Vehicle> vehicle = vehicleService.listDriver(cedula);
        if (vehicle.isPresent()) {
            return vehicle.get();
        }
        throw new CedulaNotFoundException("No vehicle found");
    }

    @ApiOperation(value = "Update Vehicle")
    @PutMapping("/update")
    public Vehicle updateService(@RequestBody Vehicle vehicle) {
        return vehicleService.update(vehicle);
    }


    @ApiOperation(value = "Delete Vehicle by ID")
    @DeleteMapping("deleteById/{id}")
    public String deleteVehicle(@PathVariable long id) {
        return vehicleService.delete(id);
    }

    @ApiOperation(value = "Delete Vehicule using Driver Cedula")
    @DeleteMapping("deleteByCedula/{cedula}")
    public String deleteDriverCedula(@PathVariable String cedula) {
        return vehicleService.deleteCedula(cedula);
    }
}
