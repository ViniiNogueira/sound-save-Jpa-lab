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

    @GetMapping("/{id}")
    public ResponseEntity<ArtistaDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(artistaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ArtistaDTO>> findAllArtistas(){
        return ResponseEntity.ok(artistaService.findAllArtistas());
    }

    @PostMapping
    public ResponseEntity<ArtistaDTO> createArtista(@RequestBody ArtistaDTO artistaDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(artistaService.createArtista(artistaDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ArtistaDTO> deleteArtista(@PathVariable Long id){
        artistaService.deleteArtistaById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/resumo")
    public ResponseEntity<String> getResumoArtista(@PathVariable Long id){
        return ResponseEntity.ok().body(artistaService.getResumoArtista(id));
    }

}