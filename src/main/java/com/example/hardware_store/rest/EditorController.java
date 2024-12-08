//package com.example.hardware_store.rest;
//
//import com.example.hardware_store.entity.Editor;
//import com.example.hardware_store.service.entity.EditorService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RequestMapping;
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/editor")
//public class EditorController {
//
//    private final EditorService editorService;
//
//    @GetMapping
//    public ResponseEntity<Editor> getEditorById(@RequestParam("id") Long id) {
//        Editor editor = editorService.findEditorById(id)
//                .orElseThrow(() -> new RuntimeException("editor not found"));
//        if (editor != null) {
//            return ResponseEntity.ok(editor);
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    @GetMapping("/editors")
//    public List<Editor> getAllEditors() {
//        return editorService.findAllEditors();
//    }
//
//    @PostMapping
//    public ResponseEntity<Editor> createEditor(@RequestBody Editor editor) {
//        editorService.addEditor(editor);
//        return ResponseEntity.ok(editor);
//    }
//
//    @PutMapping
//    public ResponseEntity<Editor> updateEditor(@RequestParam("id") Long id, @RequestBody Editor editor) {
//        editorService.updateEditor(id, editor);
//        return ResponseEntity.ok(editor);
//    }
//
//    @DeleteMapping
//    public ResponseEntity<Void> deleteEditor(@RequestParam("id") Long id) {
//        editorService.deleteEditorById(id);
//        return ResponseEntity.noContent().build();
//    }
//}