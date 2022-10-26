package com.server.musalasoft.drone_management;

import com.server.musalasoft.drone_management.entity.Drone;
import com.server.musalasoft.drone_management.entity.DroneMedication;
import com.server.musalasoft.drone_management.entity.Medication;
import com.server.musalasoft.drone_management.repository.DroneMedicationRepository;
import com.server.musalasoft.drone_management.repository.DroneRepository;
import com.server.musalasoft.drone_management.utility.enums.Model;
import com.server.musalasoft.drone_management.utility.enums.State;
import com.server.musalasoft.drone_management.utility.varlist.CodeVarList;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@SpringBootTest(properties = {
        "command.line.runner.enabled=false"})
class DroneManagementApplicationTests {

    @MockBean
    private DroneRepository droneRepository;

    @MockBean
    private DroneMedicationRepository droneMedicationRepository;

    @Test
    @Order(1)
    void registerDroneTest() {
        Drone drone = new Drone(1L, "1DE0460M01", Model.Cruiserweight, 500, 75, State.IDLE);
        when(droneRepository.save(drone)).thenReturn(drone);
        assertEquals(drone.getSerialNumber(), "1DE0460M01");
    }

    @Test
    @Order(2)
    void loadDroneWithMedicationsTest() {
        List<Medication> medicationList = new ArrayList<>();

        Drone drone = new Drone(1L, "1DE0460M01", Model.Cruiserweight, 500, 75, State.IDLE);

        Medication medication = new Medication(1L, "Medicine", 50.0, "MEDI", "url");
        medicationList.add(medication);

        DroneMedication droneMedication = new DroneMedication(1, drone, medicationList);
        when(droneMedicationRepository.save(droneMedication)).thenReturn(droneMedication);

        when(droneRepository.findBySerialNumber("1DE0460M01")).thenReturn(drone);
        assertEquals(drone.getSerialNumber(), "1DE0460M01");

        when(droneMedicationRepository.findAllByDrone(drone)).thenReturn(Stream.of(new DroneMedication(1, new Drone(1L, "1DE0460M01", Model.Cruiserweight, 500, 75, State.IDLE), medicationList)).collect(Collectors.toList()));
    }

    @Test
    @Order(3)
    void checkLoadedMedicationItemsTest() {
        List<Medication> medicationList = new ArrayList<>();

        Drone drone = new Drone(1L, "1DE0460M01", Model.Cruiserweight, 500, 75, State.IDLE);

        Medication medication = new Medication(1L, "Medicine", 50.0, "MEDI", "url");
        medicationList.add(medication);

        DroneMedication droneMedication = new DroneMedication(1, drone, medicationList);

        when(droneMedicationRepository.save(droneMedication)).thenReturn(droneMedication);

        assertEquals(50.0, medicationList.stream().mapToDouble(Medication::getWeight).sum());
    }

    @Test
    @Order(4)
    void checkDroneAvailabilityTest() {
        List<Drone> droneList = new ArrayList<>();

        Drone drone = new Drone(1L, "1DE0460M01", Model.Cruiserweight, 500, 75, State.IDLE);
        droneList.add(drone);

        when(droneRepository.findByBatteryCapacityGreaterThan(CodeVarList.MIN_DRONE_BATTERY_PERCENTAGE)).thenReturn(droneList);

        assertEquals(1, droneList.size());
    }

    @Test
    @Order(5)
    void obtainDroneBatteryLevelTest() {
        Drone drone = new Drone(1L, "1DE0460M01", Model.Cruiserweight, 500, 75, State.IDLE);

        when(droneRepository.findBySerialNumber(drone.getSerialNumber())).thenReturn(drone);

        assertEquals(java.util.Optional.of(75), Optional.of(drone.getBatteryCapacity()));
    }

}
