package com.adat.myapp.service;

import com.adat.myapp.domain.Artist;
import com.adat.myapp.exception.ArtistNotFoundException;
import com.adat.myapp.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * The type Artist service.
 */
@Service
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public Set<Artist> findAll() {
        return artistRepository.findAll();
    }

    @Override
    public Set<Artist> findByCategory(String category) {
        return artistRepository.findByCategory(category);
    }

    @Override
    public Optional<Artist> findById(long id) {
        return artistRepository.findById(id);
    }

    @Override
    public Artist addArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    @Override
    public Artist modifyArtist(long id, Artist newArtist) {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new
                        ArtistNotFoundException(id));
        newArtist.setId(artist.getId());
        return artistRepository.save(newArtist);
    }

    @Override
    public void deleteArtist(long id) {
        artistRepository.findById(id)
                .orElseThrow(() -> new
                        ArtistNotFoundException(id));
        artistRepository.deleteById(id);
    }

}