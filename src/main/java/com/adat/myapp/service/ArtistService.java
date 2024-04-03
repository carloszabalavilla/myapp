package com.adat.myapp.service;

import com.adat.myapp.domain.Artist;

import java.util.Optional;
import java.util.Set;

/**
 * The interface Artist service.
 */
public interface ArtistService {
    /**
     * Find all set.
     *
     * @return the set
     */
    Set<Artist> findAll();

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    Optional<Artist> findById(long id);

    /**
     * Add artist artist.
     *
     * @param artist the artist
     * @return the artist
     */
    Artist addArtist(Artist artist);

    /**
     * Modify artist artist.
     *
     * @param id        the id
     * @param newArtist the new artist
     * @return the artist
     */
    Artist modifyArtist(long id, Artist newArtist);

    /**
     * Delete artist.
     *
     * @param id the id
     */
    void deleteArtist(long id);

    /**
     * Find by category set.
     *
     * @param category the category
     * @return the set
     */
    Set<Artist> findByCategory(String category);
}

