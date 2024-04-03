package com.adat.myapp.repository;

import com.adat.myapp.domain.Song;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * The interface Song repository.
 */
@Repository
public interface SongRepository extends CrudRepository<Song, Long> {
    Set<Song> findAll();

    /**
     * Find by category set.
     *
     * @param category the category
     * @return the set
     */
    Set<Song> findByCategory(String category);

    /**
     * Find by title set.
     *
     * @param title the title
     * @return the set
     */
    Set<Song> findByTitle(String title);

    /**
     * Find by artist id list.
     *
     * @param artistId the artist id
     * @return the list
     */
    List<Song> findByArtistId(long artistId);
}
