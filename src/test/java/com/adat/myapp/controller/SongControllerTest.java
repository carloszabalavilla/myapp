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
class SongControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void getSongs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/songs"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void getSong() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/songs/{songId}", 1))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void addSong() throws Exception {
        String songJson = "{\"title\": \"Nueva Canción\", \"category\": \"Rock\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/songs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(songJson))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void modifySong() throws Exception {
        String songJson = "{\"title\": \"Canción Modificada\", \"category\": \"Pop\"}";

        mockMvc.perform(MockMvcRequestBuilders.put("/songs/{songId}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(songJson))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void deleteSong() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/songs/{songId}", 1))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void handleException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/songs/{songId}", -1))
                .andExpect(status().isNotFound());
    }
}
