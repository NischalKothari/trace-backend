package trace.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import trace.dto.NoteResponse;
import trace.entity.User;
import trace.security.CustomUserDetailsService;
import trace.security.JwtAuthenticationFilter;
import trace.service.interfaces.JwtService;
import trace.service.interfaces.NoteService;

import java.util.HashSet;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;


@WebMvcTest(NoteController.class)
@AutoConfigureMockMvc(addFilters = false)
public class NoteControllerTest{
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private NoteService noteService;
    @MockitoBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @MockitoBean
    private JwtService jwtService;
    @MockitoBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    @WithMockUser // creates authentication principal user instead of user entity
    void shouldReturnNoteById() throws Exception{

        User user = new User();

        NoteResponse response = new NoteResponse(
                1L,
                "Shayri",
                "Aaj kamainga to kal ghar baith ke khainga",
                new HashSet<>(),
                null,
                null
        );

        when(noteService.getNoteById(eq(1L), any()))
                .thenReturn(response);

        mockMvc.perform(get("/api/notes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Shayri"))
                .andExpect(jsonPath("$.content")
                        .value("Aaj kamainga to kal ghar baith ke khainga"))
                .andDo(print());

    }
}
