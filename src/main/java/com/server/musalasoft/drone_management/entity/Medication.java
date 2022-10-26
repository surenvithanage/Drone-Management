package com.server.musalasoft.drone_management.entity;

import com.server.musalasoft.drone_management.audit.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medication")
public class Medication extends AuditModel implements Serializable {
    private static final long serialVersionUID = -1776917631333335370L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "Name must contain only alphanumerics with hyphen and underscore.")
    private String name;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @Column(name = "code", nullable = false)
    @Pattern(regexp = "^[A-Z0-9]+$", message = "Name must contain only uppercase letters and numbers.")
    private String code;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;


}
