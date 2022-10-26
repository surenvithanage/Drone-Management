package com.server.musalasoft.drone_management.service;

import com.server.musalasoft.drone_management.annotation.loggable.LoggableService;
import com.server.musalasoft.drone_management.bean.keyvalue.KeyValueBean;
import com.server.musalasoft.drone_management.bean.response.ResponseBean;
import com.server.musalasoft.drone_management.dto.DroneRequestDto;
import com.server.musalasoft.drone_management.entity.Drone;
import com.server.musalasoft.drone_management.entity.DroneMedication;
import com.server.musalasoft.drone_management.entity.Medication;
import com.server.musalasoft.drone_management.handler.InvalidArgumentException;
import com.server.musalasoft.drone_management.handler.UnavailableException;
import com.server.musalasoft.drone_management.repository.DroneMedicationRepository;
import com.server.musalasoft.drone_management.repository.DroneRepository;
import com.server.musalasoft.drone_management.utility.enums.State;
import com.server.musalasoft.drone_management.utility.varlist.CodeVarList;
import com.server.musalasoft.drone_management.utility.varlist.MessageVarList;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Log4j2
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;

    private final ModelMapper modelMapper;

    private final DroneMedicationRepository droneMedicationRepository;

    @Override
    @LoggableService
    @Transactional
    public ResponseBean registerDrone(DroneRequestDto droneRequestDto) {
        ResponseBean responseBean = new ResponseBean();
        try {
            // check if serial number exists
            Drone drone = Optional.ofNullable(droneRepository.findBySerialNumber(droneRequestDto.getSerialNumber())).orElse(new Drone());

            if (!Objects.isNull(drone.getSerialNumber())) {
                responseBean.setRequestOk(false);
                responseBean.setMessageType(CodeVarList.ERROR);
                responseBean.setMessage(MessageVarList.DRONE_REGISTRATION_DUPLICATE);
                return responseBean;
            }

            // convert dto to entity
            drone = modelMapper.map(droneRequestDto, Drone.class);

            Drone response = droneRepository.save(drone);

            responseBean.setRequestOk(true);
            responseBean.setMessageType(CodeVarList.SUCCESS);
            responseBean.setMessage(MessageVarList.DRONE_REGISTRATION_SUCCESSFUL);
            KeyValueBean keyValueBean = new KeyValueBean(CodeVarList.DRONE_REGISTRATION_OBJECT, response);
            responseBean.setData(keyValueBean);
        } catch (DuplicateKeyException dx) {
            responseBean.setRequestOk(false);
            responseBean.setMessageType(CodeVarList.ERROR);
            responseBean.setMessage(MessageVarList.DRONE_REGISTRATION_DUPLICATE);
        } catch (Exception e) {
            throw e;
        }
        return responseBean;
    }

    @Override
    @LoggableService
    public ResponseBean loadDroneWithMedications(String serialNumber) {
        ResponseBean responseBean = new ResponseBean();
        try {
            Drone drone = Optional.ofNullable(droneRepository.findBySerialNumber(serialNumber))
                    .orElseThrow(() -> new EntityNotFoundException("Drone not found"));

            List<DroneMedication> droneMedicationList = droneMedicationRepository.findAllByDrone(drone);

            responseBean.setRequestOk(true);
            responseBean.setMessageType(CodeVarList.SUCCESS);
            responseBean.setMessage(MessageVarList.DRONE_MEDICATION_LIST_SUCCESSFUL);
            KeyValueBean keyValueBean = new KeyValueBean(CodeVarList.DRONE_MEDICATION_LIST_OBJECT, droneMedicationList);
            responseBean.setData(keyValueBean);
        } catch (Exception e) {
            throw e;
        }
        return responseBean;
    }

    @Override
    @LoggableService
    public ResponseBean checkLoadedMedicationItems(String serialNumber) {
        ResponseBean responseBean = new ResponseBean();
        try {
            // check if drone exists
            Drone drone = Optional.ofNullable(droneRepository.findBySerialNumber(serialNumber))
                    .orElseThrow(() -> new EntityNotFoundException(MessageVarList.DRONE_NOT_FOUND));

            // drone battery check
            if (drone.getBatteryCapacity() < CodeVarList.MIN_DRONE_BATTERY_PERCENTAGE) {
                throw new InvalidArgumentException(MessageVarList.DRONE_BATTERY_LEVEL_LOW);
            }

            // check drone is idle
            if (drone.getState() != State.IDLE) {
                throw new UnavailableException(MessageVarList.DRONE_UNAVAILABLE);
            }

            // assuming loading all the drones are in the loading state
            List<DroneMedication> droneMedicationList = droneMedicationRepository.findAllByDrone(drone);

            // obtaining the total weight loaded into the drone
            double totalWeight = droneMedicationList.get(0).getMedication().stream().mapToDouble(Medication::getWeight).sum();

            if (totalWeight > drone.getWeightLimit())
                throw new InvalidArgumentException(MessageVarList.DRONE_WEIGHT_EXCEEDED);

            responseBean.setRequestOk(true);
            responseBean.setMessageType(CodeVarList.SUCCESS);
            responseBean.setMessage(MessageVarList.DRONE_MEDICATION_WEIGHT_CHECK);
            KeyValueBean keyValueBean = new KeyValueBean(CodeVarList.DRONE_MEDICATION_LIST_OBJECT, droneMedicationList);
            responseBean.setData(keyValueBean);
        } catch (Exception e) {
            throw e;
        }
        return responseBean;
    }

    @Override
    @LoggableService
    public ResponseBean checkDroneAvailability() {
        ResponseBean responseBean = new ResponseBean();
        try {
            List<Drone> droneList = droneRepository.findByBatteryCapacityGreaterThan(CodeVarList.MIN_DRONE_BATTERY_PERCENTAGE);

            responseBean.setRequestOk(true);
            responseBean.setMessageType(CodeVarList.SUCCESS);
            responseBean.setMessage(MessageVarList.DRONE_AVAILABLE_LIST);
            KeyValueBean keyValueBean = new KeyValueBean(CodeVarList.DRONE_AVAILABLE_LIST_OBJECT, droneList);
            responseBean.setData(keyValueBean);
        } catch (Exception e) {
            throw e;
        }
        return responseBean;
    }

    @Override
    @LoggableService
    public ResponseBean obtainDroneBatteryLevel(String serialNumber) {
        ResponseBean responseBean = new ResponseBean();
        try {
            Drone drone = Optional.ofNullable(droneRepository.findBySerialNumber(serialNumber))
                    .orElseThrow(() -> new EntityNotFoundException("Drone not found"));

            responseBean.setRequestOk(true);
            responseBean.setMessageType(CodeVarList.SUCCESS);
            responseBean.setMessage(MessageVarList.DRONE_BATTERY_LEVEL);
            KeyValueBean keyValueBean = new KeyValueBean(CodeVarList.DRONE_BATTERY_LEVEL_OBJECT, drone.getBatteryCapacity());
            responseBean.setData(keyValueBean);
        } catch (Exception e) {
            throw e;
        }
        return responseBean;
    }
}
