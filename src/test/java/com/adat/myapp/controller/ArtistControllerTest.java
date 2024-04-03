package com.adat.myapp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ArtistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getArtists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/artists"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getArtist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/artists/{artistId}", 1))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getSongsByArtist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/artists/{artistId}/songs", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void addArtist() throws Exception {
        String artistJson = "{\"name\": \"Nuevo Artista\", \"category\": \"Rock\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/artists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(artistJson))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void modifyArtist() throws Exception {
        String artistJson = "{\"name\": \"Artista Modificado\", \"genre\": \"Pop\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/artists/{artistId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(artistJson))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void deleteArtist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/artists/{artistId}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void handleException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/artists/{artistId}", -1))
                .andExpect(status().isNotFound());
    }
}
