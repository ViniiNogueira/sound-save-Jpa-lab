package com.vinicius.soundSave.dto;

import com.vinicius.soundSave.model.Musica;

import java.util.List;

public record ArtistaDTO(Long id,
                         String nome,
                         List<MusicaDTO> musicas) {
}
