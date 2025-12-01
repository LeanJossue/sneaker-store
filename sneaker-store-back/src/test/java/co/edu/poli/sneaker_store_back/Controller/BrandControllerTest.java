package co.edu.poli.sneaker_store_back.Controller;

import co.edu.poli.sneaker_store_back.controller.BrandController;
import co.edu.poli.sneaker_store_back.entity.Brand;
import co.edu.poli.sneaker_store_back.service.BrandService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BrandController.class)
class BrandControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BrandService brandService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAll() throws Exception {
        List<Brand> brands = Arrays.asList(
                new Brand(1L, "Nike"),
                new Brand(2L, "Adidas")
        );

        when(brandService.getAll()).thenReturn(brands);

        mockMvc.perform(get("/brands"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Nike"))
                .andExpect(jsonPath("$[1].name").value("Adidas"));

        verify(brandService, times(1)).getAll();
    }

    @Test
    void testCreate() throws Exception {
        Brand brand = new Brand(1L, "Puma");

        when(brandService.create(any(Brand.class))).thenReturn(brand);

        mockMvc.perform(post("/brands")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(brand)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Puma"));

        verify(brandService, times(1)).create(any(Brand.class));
    }

    @Test
    void testUpdate() throws Exception {
        Brand updated = new Brand(1L, "Reebok");

        when(brandService.update(eq(1L), any(Brand.class))).thenReturn(updated);

        mockMvc.perform(put("/brands/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Reebok"));

        verify(brandService, times(1))
                .update(eq(1L), any(Brand.class));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(brandService).delete(1L);

        mockMvc.perform(delete("/brands/1"))
                .andExpect(status().isOk());

        verify(brandService, times(1)).delete(1L);
    }
}