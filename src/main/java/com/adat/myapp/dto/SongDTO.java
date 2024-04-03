package com.adat.myapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Song dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongDTO {
    private String title;
    private String category;
    private float duration;
    private long artistId;
}