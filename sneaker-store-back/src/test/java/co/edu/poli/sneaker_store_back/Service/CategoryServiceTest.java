package co.edu.poli.sneaker_store_back.service;

import co.edu.poli.sneaker_store_back.entity.Category;
import co.edu.poli.sneaker_store_back.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    void testGetAll() {
        List<Category> categories = Arrays.asList(
                new Category(1L, "Sneakers"),
                new Category(2L, "Boots")
        );

        when(categoryRepository.findAll()).thenReturn(categories);

        List<Category> result = categoryService.getAll();

        assertEquals(2, result.size());
        assertEquals("Sneakers", result.get(0).getName());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void testCreate() {
        Category category = new Category(1L, "Sneakers");

        when(categoryRepository.save(category)).thenReturn(category);

        Category result = categoryService.create(category);

        assertEquals("Sneakers", result.getName());
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    void testUpdateSuccess() {
        Long id = 1L;
        Category existing = new Category(id, "Old Name");
        Category updated = new Category(null, "New Name");

        when(categoryRepository.findById(id)).thenReturn(Optional.of(existing));
        when(categoryRepository.save(existing)).thenReturn(existing);

        Category result = categoryService.update(id, updated);

        assertEquals("New Name", result.getName());
        verify(categoryRepository, times(1)).findById(id);
        verify(categoryRepository, times(1)).save(existing);
    }

    @Test
    void testUpdateNotFound() {
        Long id = 1L;
        Category updated = new Category(null, "New Name");

        when(categoryRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> categoryService.update(id, updated));

        verify(categoryRepository, times(1)).findById(id);
        verify(categoryRepository, never()).save(any());
    }

    @Test
    void testDelete() {
        Long id = 1L;

        doNothing().when(categoryRepository).deleteById(id);

        categoryService.delete(id);

        verify(categoryRepository, times(1)).deleteById(id);
    }
}
