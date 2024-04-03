package com.adat.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Song.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "song")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String title;
    @Column
    private String category;
    @Column
    private float duration;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artist_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = "songs")
    private Artist artist;
}