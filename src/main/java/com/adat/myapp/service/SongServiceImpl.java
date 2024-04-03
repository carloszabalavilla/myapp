package com.adat.myapp.service;

import com.adat.myapp.domain.Song;
import com.adat.myapp.exception.SongNotFoundException;
import com.adat.myapp.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The type Song service.
 */
@Service
public class SongServiceImpl implements SongService {
    private final SongRepository songRepository;

    /**
     * Instantiates a new Song service.
     *
     * @param songRepository the song repository
     */
    public SongServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public Set<Song> findAll() {
        return songRepository.findAll();
    }

    public Set<Song> findByCategory(String category) {
        return songRepository.findByCategory(category);
    }

    public List<Song> findSongsByArtist(long artistId) {
        return songRepository.findByArtistId(artistId);
    }

    public Optional<Song> findById(long id) {
        return songRepository.findById(id);
    }

    public Song addSong(Song song) {
        return songRepository.save(song);
    }

    public Song modifySong(long id, Song song) {
        if (songRepository.existsById(id)) {
            song.setId(id);
            return songRepository.save(song);
        } else {
            throw new SongNotFoundException(id);
        }
    }

    public void deleteSong(long id) {
        if (songRepository.existsById(id)) {
            songRepository.deleteById(id);
        } else {
            throw new SongNotFoundException(id);
        }
    }


}
