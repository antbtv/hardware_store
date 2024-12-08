package com.example.hardware_store.service.entity;

import com.example.hardware_store.entity.Photo;

import java.util.List;
import java.util.Optional;

public interface PhotoService {

    Optional<Photo> findPhotoById(Long id);

    List<Photo> findAllPhoto();

    List<Photo> findAllPhotoByProductId(Long productId);

    void savePhoto(Photo photo);

    void updatePhoto(Photo photo);

    void deletePhotoById(Long id);
}