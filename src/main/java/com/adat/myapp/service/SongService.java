package com.adat.myapp.service;

import com.adat.myapp.domain.Song;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The interface Song service.
 */
public interface SongService {
    /**
     * Find all set.
     *
     * @return the set
     */
    Set<Song> findAll();

    /**
     * Find by category set.
     *
     * @param category the category
     * @return the set
     */
    Set<Song> findByCategory(String category);

    /**
     * Find songs by artist list.
     *
     * @param artistId the artist id
     * @return the list
     */
    List<Song> findSongsByArtist(long artistId);

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<Song> findById(long id);

    /**
     * Add song song.
     *
     * @param song the song
     * @return the song
     */
    Song addSong(Song song);

    /**
     * Modify song song.
     *
     * @param id   the id
     * @param song the song
     * @return the song
     */
    Song modifySong(long id, Song song);

    /**
     * Delete song.
     *
     * @param id the id
     */
    void deleteSong(long id);

}
