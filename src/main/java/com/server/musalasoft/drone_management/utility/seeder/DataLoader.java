package com.server.musalasoft.drone_management.utility.seeder;

import com.server.musalasoft.drone_management.entity.Drone;
import com.server.musalasoft.drone_management.entity.DroneMedication;
import com.server.musalasoft.drone_management.entity.Medication;
import com.server.musalasoft.drone_management.repository.DroneMedicationRepository;
import com.server.musalasoft.drone_management.repository.DroneRepository;
import com.server.musalasoft.drone_management.repository.MedicationRepository;
import com.server.musalasoft.drone_management.utility.enums.Model;
import com.server.musalasoft.drone_management.utility.enums.State;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Profile("!test")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DataLoader implements CommandLineRunner {

    private final DroneRepository droneRepository;

    private final MedicationRepository medicationRepository;

    private final DroneMedicationRepository droneMedicationRepository;

    @Override
    public void run(String... args) {
        insertDroneData();
        insertMedication();
        insertDroneMedications();
    }

    private void insertDroneData() {
        if (droneRepository.count() == 0) {
            Drone drone1 = new Drone((long) 1, "4CE0460D0G", Model.Lightweight, 250, 20, State.IDLE);
            droneRepository.save(drone1);

            Drone drone2 = new Drone((long) 2, "7RE0460E0V", Model.Cruiserweight, 450, 25, State.IDLE);
            droneRepository.save(drone2);

            Drone drone3 = new Drone((long) 3, "2TE0460F0U", Model.Heavyweight, 350, 30, State.IDLE);
            droneRepository.save(drone3);

            Drone drone4 = new Drone((long) 4, "1WE0460G0T", Model.Middleweight, 500, 40, State.IDLE);
            droneRepository.save(drone4);

            Drone drone5 = new Drone((long) 5, "8ME0460H0S", Model.Lightweight, 200, 50, State.IDLE);
            droneRepository.save(drone5);

            Drone drone6 = new Drone((long) 6, "5UE0460I0R", Model.Cruiserweight, 400, 100, State.IDLE);
            droneRepository.save(drone6);

            Drone drone7 = new Drone((long) 7, "3YE0460J0Q", Model.Middleweight, 500, 90, State.IDLE);
            droneRepository.save(drone7);

            Drone drone8 = new Drone((long) 8, "9GE0460K0P", Model.Lightweight, 500, 60, State.IDLE);
            droneRepository.save(drone8);

            Drone drone9 = new Drone((long) 9, "0XE0460L0O", Model.Heavyweight, 500, 80, State.IDLE);
            droneRepository.save(drone9);

            Drone drone10 = new Drone((long) 10, "1DE0460M0N", Model.Heavyweight, 500, 10, State.IDLE);
            droneRepository.save(drone10);
        }
    }

    private void insertMedication() {
        try {
            if (medicationRepository.count() == 0) {
                Medication medication1 = new Medication((long) 1, "Medicine_01", 450.0, "MED01", "https://cdn1.vectorstock.com/i/1000x1000/08/45/alternative-medicine-icon-flat-design-vector-6910845.jpg");
                medicationRepository.save(medication1);

                Medication medication2 = new Medication((long) 2, "Medicine_02", 120.0, "MED02", "https://cdn1.vectorstock.com/i/1000x1000/08/45/alternative-medicine-icon-flat-design-vector-6910845.jpg");
                medicationRepository.save(medication2);

                Medication medication3 = new Medication((long) 3, "Medicine_03", 25.0, "MED03", "https://cdn1.vectorstock.com/i/1000x1000/08/45/alternative-medicine-icon-flat-design-vector-6910845.jpg");
                medicationRepository.save(medication3);

                Medication medication4 = new Medication((long) 4, "Medicine_04", 75.0, "MED04", "https://cdn1.vectorstock.com/i/1000x1000/08/45/alternative-medicine-icon-flat-design-vector-6910845.jpg");
                medicationRepository.save(medication4);

                Medication medication5 = new Medication((long) 5, "Medicine_05", 100.0, "MED05", "https://cdn1.vectorstock.com/i/1000x1000/08/45/alternative-medicine-icon-flat-design-vector-6910845.jpg");
                medicationRepository.save(medication5);
            }
        } catch (ConstraintViolationException ex) {
            throw ex;
        }
    }

    private void insertDroneMedications() {
        if (droneMedicationRepository.count() == 0) {
            List<Medication> medicationList1 = new ArrayList<>();
            List<Medication> medicationList2 = new ArrayList<>();

            Drone drone1 = droneRepository.findBySerialNumber("4CE0460D0G");
            Drone drone2 = droneRepository.findBySerialNumber("5UE0460I0R");

            Medication medication1 = medicationRepository.findById(1L).orElse(null);
            if (Objects.nonNull(medication1)) {
                medicationList1.add(medication1);
                medicationList2.add(medication1);
            }
            Medication medication2 = medicationRepository.findById(2L).orElse(null);
            if (Objects.nonNull(medication2)) {
                medicationList1.add(medication2);
                medicationList2.add(medication2);
            }
            Medication medication3 = medicationRepository.findById(3L).orElse(null);
            if (Objects.nonNull(medication3)) {
                medicationList1.add(medication3);
                medicationList2.add(medication3);
            }
            Medication medication4 = medicationRepository.findById(4L).orElse(null);
            if (Objects.nonNull(medication4)) {
                medicationList2.add(medication4);
            }

            DroneMedication droneMedication1 = new DroneMedication(1, drone1, medicationList1);
            droneMedicationRepository.save(droneMedication1);

            DroneMedication droneMedication2 = new DroneMedication(2, drone2, medicationList2);
            droneMedicationRepository.save(droneMedication2);
        }
    }
}
