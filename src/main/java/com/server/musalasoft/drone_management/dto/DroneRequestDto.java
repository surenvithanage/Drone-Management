package com.server.musalasoft.drone_management.dto;

import com.server.musalasoft.drone_management.annotation.enums.EnumValue;
import com.server.musalasoft.drone_management.utility.enums.Model;
import com.server.musalasoft.drone_management.utility.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneRequestDto implements Serializable {

    private static final long serialVersionUID = 5219121046690814907L;

    @NotEmpty(message = "Serial number required")
    @Length(max = 100, message = "Maximum characters of the serial number is 100")
    private String serialNumber;

    @NotNull(message = "Model required")
    @EnumValue(enumClass = Model.class, message = "Invalid drone model")
    private String model;

    @Range(min = 1, max = 500, message = "Weight needs to be between 1 to 500 grams")
    private Integer weightLimit;

    @Range(min = 0, max = 100, message = "Battery percentage needs to be between 0 to 100")
    private Integer batteryCapacity;

    @NotNull(message = "State required")
    @EnumValue(enumClass = State.class, message = "Invalid drone state")
    private String state;
}
