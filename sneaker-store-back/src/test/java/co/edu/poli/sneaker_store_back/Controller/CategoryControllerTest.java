package co.edu.poli.sneaker_store_back.Controller;

import co.edu.poli.sneaker_store_back.controller.CategoryController;
import co.edu.poli.sneaker_store_back.entity.Category;
import co.edu.poli.sneaker_store_back.service.CategoryService;
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

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAll() throws Exception {
        List<Category> categories = Arrays.asList(
                new Category(1L, "Sneakers"),
                new Category(2L, "Boots")
        );

        when(categoryService.getAll()).thenReturn(categories);

        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Sneakers"))
                .andExpect(jsonPath("$[1].name").value("Boots"));

        verify(categoryService, times(1)).getAll();
    }

    @Test
    void testCreate() throws Exception {
        Category category = new Category(1L, "Sneakers");

        when(categoryService.create(any(Category.class))).thenReturn(category);

        mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sneakers"));

        verify(categoryService, times(1)).create(any(Category.class));
    }

    @Test
    void testUpdate() throws Exception {
        Category category = new Category(1L, "Boots");

        when(categoryService.update(eq(1L), any(Category.class))).thenReturn(category);

        mockMvc.perform(put("/categories/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Boots"));

        verify(categoryService, times(1))
                .update(eq(1L), any(Category.class));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(categoryService).delete(1L);

        mockMvc.perform(delete("/categories/1"))
                .andExpect(status().isOk());

        verify(categoryService, times(1)).delete(1L);
    }

}
