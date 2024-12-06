package com.example.hardware_store.service.entity.implementation;

import com.example.hardware_store.entity.Characteristic;
import com.example.hardware_store.repository.CharacteristicRepository;
import com.example.hardware_store.service.entity.CharacteristicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CharacteristicServiceImpl implements CharacteristicService {

    private final CharacteristicRepository characteristicRepository;

    @Override
    public Optional<Characteristic> findCharacteristicById(Long id) {
        return characteristicRepository.findById(id);
    }

    @Override
    public List<Characteristic> findAllCharacteristics() {
        return characteristicRepository.findAll();
    }

    @Override
    public void saveCharacteristic(Characteristic characteristic) {
        characteristicRepository.save(characteristic);
    }

    @Override
    public void updateCharacteristic(Characteristic characteristic) {
        characteristicRepository.saveAndFlush(characteristic);
    }

    @Override
    public void deleteCharacteristicById(Long id) {
        characteristicRepository.deleteById(id);
    }
}
