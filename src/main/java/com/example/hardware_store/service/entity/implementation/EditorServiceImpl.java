package com.example.hardware_store.service.entity.implementation;

import com.example.hardware_store.entity.Editor;
import com.example.hardware_store.repository.EditorRepository;
import com.example.hardware_store.service.entity.EditorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EditorServiceImpl implements EditorService {

    private final EditorRepository editorRepository;

    @Override
    public Optional<Editor> findEditorById(Long id) {
        return editorRepository.findById(id);
    }

    @Override
    public List<Editor> findAllEditors() {
        return List.of();
    }

    @Override
    public void saveEditor(Editor editor) {
        editorRepository.save(editor);
    }

    @Override
    public void updateEditor(Editor editor) {
        editorRepository.saveAndFlush(editor);
    }

    @Override
    public void deleteEditorById(Long id) {
        editorRepository.deleteById(id);
    }
}
