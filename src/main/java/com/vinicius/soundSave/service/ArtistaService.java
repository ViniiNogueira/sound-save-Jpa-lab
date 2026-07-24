package com.vinicius.soundSave.service;

import com.vinicius.soundSave.dto.ArtistaDTO;
import com.vinicius.soundSave.dto.MusicaDTO;
import com.vinicius.soundSave.model.Artista;
import com.vinicius.soundSave.repository.ArtistaRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistaService {

    private final ArtistaRepository artistaRepository;

    public ArtistaService(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public ArtistaDTO createArtista(ArtistaDTO artistaDTO) {
        Artista artista = new Artista();
        artista.setNome(artistaDTO.nome());

        Artista artistaSalvo = artistaRepository.save(artista);
        return new ArtistaDTO(artistaSalvo.getId(), artistaSalvo.getNome(), Collections.emptyList());
    }

    public List<ArtistaDTO> getArtistas(){
        return artistaRepository.findAll()
                .stream()
                .map(this::conversorArtistaDTO)
                .collect(Collectors.toList());
    }

    private ArtistaDTO conversorArtistaDTO(Artista artista){
        List<MusicaDTO> musicas = artista.getMusicas().stream()
                .map(m -> new MusicaDTO(m.getId(), m.getTitulo()))
                .collect(Collectors.toList());
        return new ArtistaDTO(artista.getId(), artista.getNome(), musicas);
    }
}
