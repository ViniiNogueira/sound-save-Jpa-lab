package com.vinicius.soundSave.repository;

import com.vinicius.soundSave.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicaRepository extends JpaRepository<Musica, Long> {
}