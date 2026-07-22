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

//        criando novo artista
        Artista artista = new Artista();
        Artista artista1 = new Artista();
        Artista artista2 = new Artista();
        Artista artista3 = new Artista();
        Artista artista4 = new Artista();

        artista.setNome("MC Kevin");
        artista1.setNome("MC Felipe Boladao");
        artista2.setNome("MC Ig");
        artista3.setNome("Racionai's MC");
        artista4.setNome("Michael Jackson");

        artistaRepository.save(artista);
        artistaRepository.save(artista1);
        artistaRepository.save(artista2);
        artistaRepository.save(artista3);
        artistaRepository.save(artista4);

//        instanciano nova musica

        Musica musica = new Musica();
        Musica musica1 = new Musica();
        Musica musica2 = new Musica();
        Musica musica3 = new Musica();
        Musica musica4 = new Musica();

        musica.setArtista(artista);
        musica.setTitulo("Donos da Capital");

        musica1.setArtista(artista1);
        musica1.setTitulo("Bonde do Tony Country");

        musica2.setArtista(artista2);
        musica2.setTitulo("Mente Barulhenta");

        musica3.setArtista(artista3);
        musica3.setTitulo("Homem na Estada");

        musica4.setArtista(artista4);
        musica4.setTitulo("Billie Jean");

        musicaRepository.save(musica);
        musicaRepository.save(musica1);
        musicaRepository.save(musica2);
        musicaRepository.save(musica3);
        musicaRepository.save(musica4);

        System.out.println("cada musica e seu respectivo cantor:");

        List<Musica> musicas = musicaRepository.findAll();
        musicas.forEach(m -> System.out.println(m.getTitulo() + " - " + m.getArtista().getNome()));



//        breve resumo de um cantor

        System.out.println("cantor:" + artista4.getNome() );
        System.out.println(
        GroqIAService.obterResumo(artista4.getNome())
        );


    }


    public static void main(String[] args) {
        System.out.println("OPENAI_API_KEY = " + System.getenv("OPENAI_API_KEY"));
        SpringApplication.run(SoundSaveApplication.class, args);
    }
}