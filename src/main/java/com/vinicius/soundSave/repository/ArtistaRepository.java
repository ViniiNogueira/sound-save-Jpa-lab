package com.vinicius.soundSave.repository;

import com.vinicius.soundSave.model.Artista;
import com.vinicius.soundSave.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistaRepository extends JpaRepository<Artista , Long> {

    List<Artista> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT m FROM Artista a JOIN a.musicas m WHERE LOWER(a.nome) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Musica> findMusicaByArtista(@Param("name") String nome);
}