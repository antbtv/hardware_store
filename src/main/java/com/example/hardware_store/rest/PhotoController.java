//package com.example.hardware_store.rest;
//
//import com.example.hardware_store.entity.Photo;
//import com.example.hardware_store.service.entity.PhotoService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/photos")
//@RequiredArgsConstructor
//public class PhotoController {
//
//    private final PhotoService photoService;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Photo> getPhotoById(@PathVariable Long id) {
//        return photoService.findPhotoById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Photo>> getAllPhotos() {
//        List<Photo> photos = photoService.findAllPhoto();
//        if (photos.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(photos);
//    }
//
//    @GetMapping("/product/{productId}")
//    public ResponseEntity<List<Photo>> getPhotosByProductId(@PathVariable Long productId) {
//        List<Photo> photos = photoService.findAllPhotoByProductId(productId);
//        if (photos.isEmpty()) {
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.ok(photos);
//    }
//
//    @PostMapping
//    public ResponseEntity<Photo> createPhoto(@RequestBody Photo photo) {
//        photoService.savePhoto(photo);
//        return ResponseEntity.ok(photo);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Photo> updatePhoto(@PathVariable Long id, @RequestBody Photo photo) {
//        photo.setId(id); // Убедитесь, что ID устанавливается в сущности
//        photoService.updatePhoto(photo);
//        return ResponseEntity.ok(photo);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletePhoto(@PathVariable Long id) {
//        photoService.deletePhotoById(id);
//        return ResponseEntity.noContent().build();
//    }
//}
