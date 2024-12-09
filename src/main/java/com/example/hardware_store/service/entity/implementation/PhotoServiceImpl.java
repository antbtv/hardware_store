package com.example.hardware_store.service.entity.implementation;


import com.example.hardware_store.entity.Photo;
import com.example.hardware_store.repository.PhotoRepository;
import com.example.hardware_store.service.entity.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;

    @Override
    public Optional<Photo> findPhotoById(Long id) {
        return photoRepository.findById(id);
    }

    @Override
    public List<Photo> findAllPhoto() {
        return photoRepository.findAll();
    }

    @Override
    public List<Photo> findAllPhotoByProductId(Long productId) {
        return photoRepository.findAllByProductId(productId);
    }

    @Override
    public void savePhoto(Photo photo) {
        photoRepository.save(photo);
    }

    @Override
    public void updatePhoto(Photo photo) {
        photoRepository.saveAndFlush(photo);
    }

    @Override
    public void deletePhotoById(Long id) {
        photoRepository.deleteById(id);
    }

}
