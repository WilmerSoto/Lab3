package com.udea.conductores.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Max;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@ApiModel(description = "All details about driver")
public class Driver implements Serializable {
    @ApiModelProperty(notes = "The DB generated ID Driver")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idDriver")
    private Long idDriver;

    @OneToOne(mappedBy = "idDriver")
    @JsonIgnoreProperties("idDriver")
    private Vehicle vehicle;

    @ApiModelProperty(notes = "First Name")
    @Column(name = "firstName", nullable = false, length = 80)
    private @NonNull String firstName;

    @ApiModelProperty(notes = "Last Name")
    @Column(name = "lastName", nullable = false, length = 80)
    private @NonNull String lastName;

    @ApiModelProperty(notes = "Email")
    @Column(name = "email", nullable = false, length = 80)
    private @NonNull String email;

    @ApiModelProperty(notes = "Cell Phone")
    @Column(name = "celular", nullable = false, length = 80)
    private @NonNull int celular;

    @ApiModelProperty(notes = "Driver Rating")
    @Column(name = "rating", nullable = false, length = 80)
    @Min(value = 1, message = "id should be more or than equal 1")
    @Max(value = 5, message = "id should be more or than equal 5")
    private @NonNull int rating;

    @ApiModelProperty(notes = "Cedula")
    @Column(name = "cedula", nullable = false, length = 80)
    private @NonNull String cedula;

    @ApiModelProperty(notes = "Drivers license Number")
    @Column(name = "licenciaCon", nullable = false, length = 80)
    private @NonNull String licenciaCon;

    @ApiModelProperty(notes = "Available")
    private Boolean available;


}
