package com.server.musalasoft.drone_management.entity;

import com.server.musalasoft.drone_management.audit.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "drone_medication")
public class DroneMedication extends AuditModel implements Serializable {
    private static final long serialVersionUID = -7609690230711313973L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drone_id", nullable = false)
    private Drone drone;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "drone_medication_info",
            joinColumns = @JoinColumn(name = "drone_medication_id"),
            inverseJoinColumns = @JoinColumn(name = "medication_id", nullable = false)
    )
    private List<Medication> medication;
}
