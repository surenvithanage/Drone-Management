package com.server.musalasoft.drone_management.repository;

import com.server.musalasoft.drone_management.entity.Drone;
import com.server.musalasoft.drone_management.entity.DroneMedication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneMedicationRepository extends JpaRepository<DroneMedication, Long> {
    List<DroneMedication> findAllByDrone(Drone drone);
}
