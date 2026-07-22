package com.vinicius.soundSave;

import com.vinicius.soundSave.application.Program;
import com.vinicius.soundSave.repository.ArtistaRepository;
import com.vinicius.soundSave.repository.MusicaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoundSaveApplication implements CommandLineRunner {

	private final ArtistaRepository artistaRepository;
	private final MusicaRepository musicaRepository;

    public SoundSaveApplication(ArtistaRepository artistaRepository, MusicaRepository musicaRepository) {
        this.artistaRepository = artistaRepository;
        this.musicaRepository = musicaRepository;
    }

    @Override
	public void run(String... args) throws Exception {
		Program program = new Program(artistaRepository , musicaRepository);
		program.inicializador();
	}

	public static void main(String[] args) {
		SpringApplication.run(SoundSaveApplication.class, args);
	}

}
