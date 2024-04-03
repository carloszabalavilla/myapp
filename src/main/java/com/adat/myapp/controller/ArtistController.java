package com.adat.myapp.controller;

import com.adat.myapp.domain.Artist;
import com.adat.myapp.domain.Song;
import com.adat.myapp.exception.ArtistNotFoundException;
import com.adat.myapp.service.ArtistService;
import com.adat.myapp.service.SongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

import static com.adat.myapp.controller.Response.NOT_FOUND;

/**
 * The type Artist controller.
 */
@RestController
public class ArtistController {

    private final Logger logger = LoggerFactory.getLogger(ArtistController.class);

    private final ArtistService artistService;

    private final SongService songService;

    /**
     * Instantiates a new Artist controller.
     *
     * @param songService   the song service
     * @param artistService the artist service
     */
    @Autowired
    public ArtistController(SongService songService, ArtistService artistService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    /**
     * Gets artists.
     *
     * @param category the category
     * @return the artists
     */
    @GetMapping("/artists")
    public ResponseEntity<Set<Artist>> getArtists(@RequestParam(value = "category", defaultValue = "") String
                                                          category) {
        logger.info("inicio get artists");

        Set<Artist> artists;
        if (category.isEmpty())
            artists = artistService.findAll();
        else
            artists = artistService.findByCategory(category);
        logger.info("fin get artists");
        return new ResponseEntity<>(artists, HttpStatus.OK);
    }

    /**
     * Gets artist.
     *
     * @param id the id
     * @return the artist
     */
    @GetMapping("/artists/{id}")
    public ResponseEntity<Artist> getArtist(@PathVariable long id) {
        Artist artist = artistService.findById(id)
                .orElseThrow(() -> new ArtistNotFoundException(id));
        return new ResponseEntity<>(artist, HttpStatus.OK);
    }

    /**
     * Gets songs by artist.
     *
     * @param artistId the artist id
     * @return the songs by artist
     */
    @GetMapping("/artists/{artistId}/songs")
    public List<Song> getSongsByArtist(@PathVariable long artistId) {
        return songService.findSongsByArtist(artistId);
    }

    /**
     * Add artist response entity.
     *
     * @param artist the artist
     * @return the response entity
     */
    @PostMapping("/artists")
    public ResponseEntity<Artist> addArtist(@RequestBody Artist artist) {
        Artist addedArtist = artistService.addArtist(artist);
        return new ResponseEntity<>(addedArtist, HttpStatus.CREATED);
    }

    /**
     * Modify artist response entity.
     *
     * @param id        the id
     * @param newArtist the new artist
     * @return the response entity
     */
    @PutMapping("/artists/{id}")
    public ResponseEntity<Artist> modifyArtist(@PathVariable long id, @RequestBody Artist newArtist) {
        Artist artist = artistService.modifyArtist(id, newArtist);
        return new ResponseEntity<>(artist, HttpStatus.OK);
    }

    /**
     * Delete artist response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/artists/{id}")
    public ResponseEntity<Response> deleteArtist(@PathVariable long id) {
        artistService.deleteArtist(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    /**
     * Handle exception response entity.
     *
     * @param anfe the anfe
     * @return the response entity
     */
    @ExceptionHandler(ArtistNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(ArtistNotFoundException anfe) {
        Response response = Response.errorResonse(NOT_FOUND, anfe.getMessage());
        logger.error(anfe.getMessage(), anfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}