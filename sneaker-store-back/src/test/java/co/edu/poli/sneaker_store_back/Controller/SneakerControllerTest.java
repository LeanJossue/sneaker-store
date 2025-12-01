package co.edu.poli.sneaker_store_back.Controller;

import co.edu.poli.sneaker_store_back.controller.CategoryController;
import co.edu.poli.sneaker_store_back.controller.SneakerController;
import co.edu.poli.sneaker_store_back.entity.Brand;
import co.edu.poli.sneaker_store_back.entity.Category;
import co.edu.poli.sneaker_store_back.entity.Sneaker;
import co.edu.poli.sneaker_store_back.service.CategoryService;
import co.edu.poli.sneaker_store_back.service.SneakerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SneakerController.class)
public class SneakerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SneakerService sneakerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAll() throws Exception {
        List<Sneaker> sneakers = Arrays.asList(
                new Sneaker(1L, "Airmax 2", "Black", 100.0F, 2, 8.5F, new Brand(), new Category()),
                new Sneaker(2L, "Ultraboost", "White", 100.0F, 2, 8.5F, new Brand(), new Category())
        );

        when(sneakerService.getAll()).thenReturn(sneakers);

        mockMvc.perform(get("/sneakers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Airmax 2"))
                .andExpect(jsonPath("$[1].name").value("Ultraboost"));

        verify(sneakerService, times(1)).getAll();
    }

    @Test
    void testCreate() throws Exception {
        Sneaker sneaker = new Sneaker(1L, "Airmax 2", "Black", 100.0F, 2, 8.5F, new Brand(), new Category());

        when(sneakerService.create(any(Sneaker.class))).thenReturn(sneaker);

        mockMvc.perform(post("/sneakers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sneaker)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Airmax 2"));

        verify(sneakerService, times(1)).create(any(Sneaker.class));
    }

    @Test
    void testUpdate() throws Exception {
        Sneaker sneaker = new Sneaker(1L, "Airmax 2", "Black", 100.0F, 2, 8.5F, new Brand(), new Category());

        when(sneakerService.update(eq(1L), any(Sneaker.class))).thenReturn(sneaker);

        mockMvc.perform(put("/sneakers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sneaker)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Airmax 2"));

        verify(sneakerService, times(1))
                .update(eq(1L), any(Sneaker.class));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(sneakerService).delete(1L);

        mockMvc.perform(delete("/sneakers/1"))
                .andExpect(status().isOk());

        verify(sneakerService, times(1)).delete(1L);
    }

}


