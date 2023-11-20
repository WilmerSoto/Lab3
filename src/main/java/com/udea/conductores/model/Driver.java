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
public class Driver implements Serializable {
    @ApiModelProperty(notes = "The DB generated ID Driver")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idDriver")
    private Long idDriver;

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
    private @NonNull long cedula;

    @ApiModelProperty(notes = "Licencia de Conduccion")
    @Column(name = "licenciaCon", nullable = false, length = 80)
    private @NonNull String licenciaCon;

    @ApiModelProperty(notes = "Available")
    private Boolean available;

    public Long getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(Long idDriver) {
        this.idDriver = idDriver;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public String getLicenciaCon() {
        return licenciaCon;
    }

    public void setLicenciaCon(String licenciaCon) {
        this.licenciaCon = licenciaCon;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
