package com.adat.myapp.exception;

/**
 * The type Song not found exception.
 */
public class SongNotFoundException extends RuntimeException {
    /**
     * Instantiates a new Song not found exception.
     */
    public SongNotFoundException() {
        super();
    }

    /**
     * Instantiates a new Song not found exception.
     *
     * @param message the message
     */
    public SongNotFoundException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Song not found exception.
     *
     * @param id the id
     */
    public SongNotFoundException(long id) {
        super("Song not found: " + id);
    }
}
