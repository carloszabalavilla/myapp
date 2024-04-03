package com.adat.myapp.repository;

import com.adat.myapp.domain.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * The interface Artist repository.
 */
@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long> {
    Set<Artist> findAll();

    /**
     * Find by category set.
     *
     * @param category the category
     * @return the set
     */
    Set<Artist> findByCategory(String category);

    /**
     * Find by name set.
     *
     * @param name the name
     * @return the set
     */
    Set<Artist> findByName(String name);
}