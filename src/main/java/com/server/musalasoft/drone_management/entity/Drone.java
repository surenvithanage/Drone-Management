package com.server.musalasoft.drone_management.entity;

import com.server.musalasoft.drone_management.audit.AuditModel;
import com.server.musalasoft.drone_management.utility.enums.Model;
import com.server.musalasoft.drone_management.utility.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "drone")
public class Drone extends AuditModel implements Serializable {
    private static final long serialVersionUID = 8629988295729709760L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serial_number", unique = true, nullable = false)
    @Size(max = 100)
    private String serialNumber;

    @Column(name = "model", nullable = false)
    @Enumerated(EnumType.STRING)
    private Model model;

    @Column(name = "weight_limit", nullable = false)
    private Integer weightLimit;

    @Column(name = "battery_capacity", nullable = false)
    private Integer batteryCapacity;

    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private State state;

}

