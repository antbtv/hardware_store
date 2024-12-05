package com.example.hardware_store.repository;

import com.example.hardware_store.entity.Editor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditorRepository extends JpaRepository<Editor, Long> {
}