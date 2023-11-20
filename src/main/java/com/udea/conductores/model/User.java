package com.udea.conductores.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
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
public class User implements Serializable {
    @ApiModelProperty(notes = "The DB generated ID Driver")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idUser")
    private Long idUser;

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

    @ApiModelProperty(notes = "Cedula")
    @Column(name = "cedula", nullable = false, length = 80)
    private @NonNull String cedula;

    @ApiModelProperty(notes = "Number of total trips")
    @Column(name = "nroViajes", length = 80)
    private long nroViajes;


}
