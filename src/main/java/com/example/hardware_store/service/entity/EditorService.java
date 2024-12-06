package com.example.hardware_store.service.entity;

import com.example.hardware_store.entity.Editor;

import java.util.List;
import java.util.Optional;

public interface EditorService {

    Optional<Editor> findEditorById(Long id);

    List<Editor> findAllEditors();

    void saveEditor(Editor editor);

    void updateEditor(Editor editor);

    void deleteEditorById(Long id);
}