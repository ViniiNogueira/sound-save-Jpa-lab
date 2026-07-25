package com.vinicius.soundSave.dto;

import java.util.List;

public record ArtistaDTO(Long id,
                         String nome,
                         List<MusicaDTO> musicas) {
}
