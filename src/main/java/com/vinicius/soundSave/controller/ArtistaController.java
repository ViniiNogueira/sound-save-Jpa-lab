package com.vinicius.soundSave.controller;

import com.vinicius.soundSave.dto.ArtistaDTO;
import com.vinicius.soundSave.service.ArtistaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artistas")
public class ArtistaController {

    private final ArtistaService artistaService;

    public ArtistaController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @PostMapping
    public ResponseEntity<ArtistaDTO> createArtista(@RequestBody ArtistaDTO artistaDTO){
            return ResponseEntity.status(HttpStatus.CREATED).body(artistaService.createArtista(artistaDTO));
    }

    @GetMapping
    public ResponseEntity<List<ArtistaDTO>> getArtista(){
        return ResponseEntity.ok(artistaService.getArtistas());
    }


}
