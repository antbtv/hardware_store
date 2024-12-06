package com.example.hardware_store.service.entity;

import com.example.hardware_store.entity.Characteristic;

import java.util.List;
import java.util.Optional;

public interface CharacteristicService {

    Optional<Characteristic> findCharacteristicById(Long id);

    List<Characteristic> findAllCharacteristics();

    void saveCharacteristic(Characteristic characteristic);

    void updateCharacteristic(Characteristic characteristic);

    void deleteCharacteristicById(Long id);
}