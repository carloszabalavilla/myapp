package com.adat.myapp;

import com.adat.myapp.domain.Artist;
import com.adat.myapp.domain.Song;
import com.adat.myapp.repository.ArtistRepository;
import com.adat.myapp.repository.SongRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("demo")
public class DemoData implements CommandLineRunner {
    private final ArtistRepository artists;
    private final SongRepository songs;
    public DemoData(ArtistRepository artists, SongRepository songs) {
        this.artists = artists;
        this.songs = songs;
    }
    @Override
    public void run(String... args) {
        Artist artist = new Artist();
        artist.setName("Demo Ensemble");
        artist.setDescription("Fictional artist for the local demonstration");
        artist.setCategory("Jazz");
        artist.setYears(3);
        artists.save(artist);
        Song song = new Song();
        song.setTitle("Local Session");
        song.setCategory("Jazz");
        song.setDuration(3.5f);
        song.setArtist(artist);
        songs.save(song);
    }
}
