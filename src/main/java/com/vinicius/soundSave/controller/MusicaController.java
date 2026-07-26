package com.vinicius.soundSave.controller;

import com.vinicius.soundSave.dto.MusicaDTO;
import com.vinicius.soundSave.service.MusicaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/musicas")
public class MusicaController {

    private final MusicaService musicaService;

    public MusicaController(MusicaService musicaService) {
        this.musicaService = musicaService;
    }

//    findAll
    @GetMapping
    public ResponseEntity<List<MusicaDTO>> findAll() {
        return ResponseEntity.ok().body(musicaService.findAll());
    }

//    FindById
    @GetMapping("/{id}")
    public  ResponseEntity<MusicaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(musicaService.findById(id));
    }

    @GetMapping("/busca")
    public ResponseEntity<List<MusicaDTO>> findAllByArtista(@RequestParam String nome) {
        return ResponseEntity.ok(musicaService.findMusicasByArtista(nome));
    }

//    post
    @PostMapping
    public ResponseEntity<MusicaDTO> createMusica(@RequestBody MusicaDTO musicaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(musicaService.createMusica(musicaDTO));
    }

//    Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<MusicaDTO> deleteMusica(@PathVariable Long id) {
        musicaService.deleteMusica(id);
        return ResponseEntity.noContent().build();
    }
}
