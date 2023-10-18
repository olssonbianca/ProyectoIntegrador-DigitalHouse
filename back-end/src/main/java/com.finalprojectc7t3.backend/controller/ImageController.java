package com.finalprojectc7t3.backend.controller;

import com.finalprojectc7t3.backend.dto.ImageDTO;
import com.finalprojectc7t3.backend.service.impl.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/v1/image")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:80", "http://localhost", "http://localhost:5173"})

public class ImageController {
    private final ImageService service;

    @Autowired
    public ImageController(ImageService service){
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ImageDTO imageDto)  {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(imageDto));
    }

    @PostMapping("/upload")
    public ResponseEntity<?> createS3(@RequestParam("imagen") MultipartFile imagen) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.savedImageS3(imagen));
    }
    @PostMapping("/upload/bulk")
    public ResponseEntity<?> crearS3Bulk(@RequestParam("imagen") List<MultipartFile> imagen) {
        List<ImageDTO> lista = new ArrayList<>();
        imagen.forEach(i -> {
            try {
                lista.add(service.savedImageS3(i));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return ResponseEntity.status(HttpStatus.CREATED).body(lista);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ImageDTO imageDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(imageDto));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/desactivar/{key}")
    public ResponseEntity<?> deleteS3(@PathVariable String key) {
        service.deleteImageS3(key);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
