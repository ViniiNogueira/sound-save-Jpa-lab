package com.vinicius.soundSave.application;

import com.vinicius.soundSave.SoundSaveApplication;
import com.vinicius.soundSave.model.Artista;
import com.vinicius.soundSave.model.Musica;
import com.vinicius.soundSave.repository.ArtistaRepository;
import com.vinicius.soundSave.repository.MusicaRepository;
import com.vinicius.soundSave.service.GroqIAService;
import org.springframework.boot.SpringApplication;

import java.util.List;

public class Program {

    private final  ArtistaRepository artistaRepository;
    private final MusicaRepository musicaRepository;

    public Program(ArtistaRepository artistaRepository, MusicaRepository musicaRepository) {
        this.artistaRepository = artistaRepository;
        this.musicaRepository = musicaRepository;
    }

    public void inicializador() {

//

        System.out.println("cada musica e seu respectivo cantor:");

        List<Musica> musicas = musicaRepository.findAll();
        musicas.forEach(m -> System.out.println(m.getTitulo() + " - " + m.getArtista().getNome()));



//        breve resumo de um cantor

//        GroqIAService.obterResumo(); <- para pegar o resumo do cantor


    }
}