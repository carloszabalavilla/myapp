package com.adat.myapp.dto;

import com.adat.myapp.domain.Song;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The type Artist dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDTO {
    private String name;
    private String description;
    private String category;
    private int years;
    private List<Song> songs;
}