package com.vinicius.soundSave.service;

import com.vinicius.soundSave.dto.ArtistaDTO;
import com.vinicius.soundSave.dto.MusicaDTO;
import com.vinicius.soundSave.model.Artista;
import com.vinicius.soundSave.model.Musica;
import com.vinicius.soundSave.repository.ArtistaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArtistaService {

    private final GroqIAService groqIAService;
    private final ArtistaRepository artistaRepository;

    public ArtistaService(GroqIAService groqIAService, ArtistaRepository artistaRepository) {
        this.groqIAService = groqIAService;
        this.artistaRepository = artistaRepository;
    }

//    POST
    public ArtistaDTO createArtista(ArtistaDTO artistaDTO) {
        Artista artista = new Artista();
        artista.setNome(artistaDTO.nome());

        Artista artistaSalvo = artistaRepository.save(artista);
        return new ArtistaDTO(artistaSalvo.getId(), artistaSalvo.getNome(), Collections.emptyList());
    }

//    FindAll
    public List<ArtistaDTO> findAllArtistas(){
        return artistaRepository.findAll()
                .stream()
                .map(this::toArtistaDTO)
                .collect(Collectors.toList());
    }

//    findById
    public ArtistaDTO findById(Long id){
        Artista artista = artistaRepository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, " artista nao encontrado, id: " + id ));
        return toArtistaDTO(artista);
    }

//    Delete
    public void deleteArtistaById(Long id){
        if (artistaRepository.findById(id).isPresent()) {
            artistaRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " artista nao encontrado, id: " + id );
        }
    }

    public String getResumoArtista(Long id){
        Artista artista = artistaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "artista nao encontrado, id: " + id));

        String musicas = artista.getMusicas()
                .stream()
                .map(Musica::getTitulo)
                .collect(Collectors.joining(", "));


        return groqIAService.obterResumo(artista.getNome() , musicas);
    }

    private ArtistaDTO toArtistaDTO(Artista artista){
        List<MusicaDTO> musicas = artista.getMusicas().stream()
                .map(m -> new MusicaDTO(m.getId(), m.getTitulo() , m.getArtista().getId()))
                .collect(Collectors.toList());
        return new ArtistaDTO(artista.getId(), artista.getNome(), musicas);
    }
}
