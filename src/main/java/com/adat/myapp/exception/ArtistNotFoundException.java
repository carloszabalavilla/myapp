package com.adat.myapp.exception;

/**
 * The type Artist not found exception.
 */
public class ArtistNotFoundException extends RuntimeException {
    /**
     * Instantiates a new Artist not found exception.
     */
    public ArtistNotFoundException() {
        super();
    }

    /**
     * Instantiates a new Artist not found exception.
     *
     * @param message the message
     */
    public ArtistNotFoundException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Artist not found exception.
     *
     * @param id the id
     */
    public ArtistNotFoundException(long id) {
        super("Artist not found: " + id);
    }
}