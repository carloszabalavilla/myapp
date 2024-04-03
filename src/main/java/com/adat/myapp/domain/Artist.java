package com.adat.myapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The type Artist.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String category;
    @Column
    private int years;
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "artist")
    private List<Song> songs;
}