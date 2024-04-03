package com.adat.myapp.controller;

import com.adat.myapp.domain.Artist;
import com.adat.myapp.domain.Song;
import com.adat.myapp.dto.SongDTO;
import com.adat.myapp.exception.SongNotFoundException;
import com.adat.myapp.repository.ArtistRepository;
import com.adat.myapp.service.SongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

import static com.adat.myapp.controller.Response.NOT_FOUND;

/**
 * The type Song controller.
 */
@RestController
public class SongController {

    private final Logger logger = LoggerFactory.getLogger(SongController.class);

    private final SongService songService;
    private final ArtistRepository artistRepository;


    /**
     * Instantiates a new Song controller.
     *
     * @param songService      the song service
     * @param artistRepository the artist repository
     */
    @Autowired
    public SongController(SongService songService, ArtistRepository artistRepository) {
        this.songService = songService;
        this.artistRepository = artistRepository;
    }

    /**
     * Gets songs.
     *
     * @param category the category
     * @return the songs
     */
    @GetMapping("/songs")
    public ResponseEntity<Set<Song>> getSongs(@RequestParam(value = "category", defaultValue = "") String
                                                      category) {
        logger.info("inicio get songs");
        Set<Song> songs;
        if (category.isEmpty())
            songs = songService.findAll();
        else
            songs = songService.findByCategory(category);
        logger.info("fin get songs");
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    /**
     * Gets song.
     *
     * @param id the id
     * @return the song
     */
    @GetMapping("/songs/{id}")
    public ResponseEntity<Song> getSong(@PathVariable long id) {
        Song song = songService.findById(id)
                .orElseThrow(() -> new SongNotFoundException(id));
        return new ResponseEntity<>(song, HttpStatus.OK);
    }

    /**
     * Add song response entity.
     *
     * @param song the song
     * @return the response entity
     */
    @PostMapping("/songs")
    public ResponseEntity<Song> addSong(@RequestBody SongDTO song) {
        Optional<Artist> artistOptional = artistRepository.findById(song.getArtistId());
        if (artistOptional.isPresent()) {
            Artist artist = artistOptional.get();
            Song newSong = new Song();
            newSong.setTitle(song.getTitle());
            newSong.setCategory(song.getCategory());
            newSong.setDuration(song.getDuration());
            newSong.setArtist(artist);
            Song addedSong = songService.addSong(newSong);
            return new ResponseEntity<>(addedSong, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Modify song response entity.
     *
     * @param id   the id
     * @param song the new song
     * @return the response entity
     */
    @PutMapping("/songs/{id}")
    public ResponseEntity<Song> modifySong(@PathVariable long id, @RequestBody SongDTO song) {
        Optional<Artist> artistOptional = artistRepository.findById(song.getArtistId());
        if (artistOptional.isPresent()) {
            Artist artist = artistOptional.get();
            Song newSong = new Song();
            newSong.setTitle(song.getTitle());
            newSong.setCategory(song.getCategory());
            newSong.setDuration(song.getDuration());
            newSong.setArtist(artist);
            Song songResponse = songService.modifySong(id, newSong);
            return new ResponseEntity<>(songResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete song response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/songs/{id}")
    public ResponseEntity<Response> deleteSong(@PathVariable long id) {
        songService.deleteSong(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    /**
     * Handle exception response entity.
     *
     * @param snfe the snfe
     * @return the response entity
     */
    @ExceptionHandler(SongNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(SongNotFoundException snfe) {
        Response response = Response.errorResonse(NOT_FOUND, snfe.getMessage());
        logger.error(snfe.getMessage(), snfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}