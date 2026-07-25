package com.vinicius.soundSave.service;

import com.vinicius.soundSave.dto.MusicaDTO;
import com.vinicius.soundSave.model.Artista;
import com.vinicius.soundSave.model.Musica;
import com.vinicius.soundSave.repository.ArtistaRepository;
import com.vinicius.soundSave.repository.MusicaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicaService {

    private final ArtistaRepository artistaRepository;
    private final MusicaRepository musicaRepository;

    public MusicaService(ArtistaRepository artistaRepository, MusicaRepository musicaRepository) {
        this.artistaRepository = artistaRepository;
        this.musicaRepository = musicaRepository;
    }

//    findAll
    public List<MusicaDTO> findAll() {
        return musicaRepository.findAll()
                .stream()
                .map(this::toMusicaDTO)
                .collect(Collectors.toList());
    }

//    FindByID
    public MusicaDTO findById(Long id) {
         Musica musica = musicaRepository.findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, " musica nao encontrado, id: " + id));

         return toMusicaDTO(musica);
    }

//    create
    public MusicaDTO createMusica(MusicaDTO musicaDTO) {
        Artista artista = artistaRepository.findById(musicaDTO.artistaId())
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "artista nao encontrado " ));

        Musica musica = new Musica();
        musica.setTitulo(musicaDTO.titulo());
        musica.setArtista(artista);
        return  toMusicaDTO(musicaRepository.save(musica));
    }

//    Delete
    public void deleteMusica(Long id) {

        if (!musicaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, " musica nao encontrado, id: " + id );
        } else {
            musicaRepository.deleteById(id);
        }
    }

//    conversor
    private MusicaDTO toMusicaDTO(Musica musica) {
        return new MusicaDTO(musica.getId() , musica.getTitulo() , musica.getArtista().getId());
    }
}
