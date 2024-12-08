//package com.example.hardware_store.rest;
//
//import com.example.hardware_store.entity.Characteristic;
//import com.example.hardware_store.service.entity.CharacteristicService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/characteristics")
//@RequiredArgsConstructor
//public class CharacteristicController {
//
//    private final CharacteristicService characteristicService;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Characteristic> getCharacteristicById(@PathVariable Long id) {
//        return characteristicService.findCharacteristicById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Characteristic>> getAllCharacteristics() {
//        List<Characteristic> characteristics = characteristicService.findAllCharacteristics();
//        if (characteristics.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(characteristics);
//    }
//
//    @GetMapping("/product/{productId}")
//    public ResponseEntity<List<Characteristic>> getCharacteristicsByProductId(@PathVariable Long productId) {
//        List<Characteristic> characteristics = characteristicService.findAllCharacteristicByProductId(productId);
//        return ResponseEntity.ok(characteristics);
//    }
//
//    @PostMapping
//    public ResponseEntity<Characteristic> createCharacteristic(@RequestBody Characteristic characteristic) {
//        characteristicService.saveCharacteristic(characteristic);
//        return ResponseEntity.ok(characteristic);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Characteristic> updateCharacteristic(@PathVariable Long id, @RequestBody Characteristic characteristic) {
//        characteristic.setId(id);
//        characteristicService.updateCharacteristic(characteristic);
//        return ResponseEntity.ok(characteristic);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteCharacteristic(@PathVariable Long id) {
//        characteristicService.deleteCharacteristicById(id);
//        return ResponseEntity.noContent().build();
//    }
//}
