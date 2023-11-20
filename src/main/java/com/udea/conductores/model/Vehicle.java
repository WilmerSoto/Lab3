package com.udea.conductores.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@ApiModel(description = "All details about the vehicule of the driver")
public class Vehicle implements Serializable {
    @ApiModelProperty(notes = "The DB generated ID Driver")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idVehicle")
    private Long idVehicle;

    @OneToOne
    @JsonIgnoreProperties("vehicle")
    @JoinColumn(name = "fkIdDriver", referencedColumnName = "idDriver")
    private Driver idDriver;

    @ApiModelProperty(notes = "Manufacturer")
    @Column(name = "manufacturer", nullable = false, length = 80)
    private @NonNull String manufacturer;

    @ApiModelProperty(notes = "Model of the car")
    @Column(name = "model", nullable = false, length = 80)
    private @NonNull String model;

    @ApiModelProperty(notes = "Description of the vehicle")
    @Column(name = "Description", nullable = false, length = 1000)
    private @NonNull String description;

    @ApiModelProperty(notes = "License plate")
    @Column(name = "license_plate", nullable = false, length = 80)
    private @NonNull String licensePlate;

    @ApiModelProperty(notes = "Technical inspection expiration date")
    @Column(name = "technicalInspectionExpirationDate")
    private @NonNull Date technicalExpirationDate;

    @ApiModelProperty(notes = "Insurance expiration date")
    @Column(name = "insuranceExpirationDate")
    private @NonNull Date insuranceExpirationDate;
}
