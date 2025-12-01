package co.edu.poli.sneaker_store_back.service;

import co.edu.poli.sneaker_store_back.entity.Brand;
import co.edu.poli.sneaker_store_back.entity.Category;
import co.edu.poli.sneaker_store_back.entity.Sneaker;
import co.edu.poli.sneaker_store_back.repository.SneakerRepository;
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
class SneakerServiceTest {

    @Mock
    private SneakerRepository sneakerRepository;

    @InjectMocks
    private SneakerService sneakerService;

    @Test
    void testGetAll() {
        List<Sneaker> sneakers = Arrays.asList(
                new Sneaker(1L, "Air Max", "Red", 42.0F, 10, 8F, new Brand(), new Category()),
                new Sneaker(2L, "Jordan", "Black", 100F, 5, 9F, new Brand(), new Category())
        );

        when(sneakerRepository.findAll()).thenReturn(sneakers);

        List<Sneaker> result = sneakerService.getAll();

        assertEquals(2, result.size());
        assertEquals("Air Max", result.get(0).getName());
        verify(sneakerRepository).findAll();
    }

    @Test
    void testCreate() {
        Sneaker sneaker = new Sneaker(1L, "Air Max", "Red", 42.0F, 10, 8F, new Brand(), new Category());

        when(sneakerRepository.save(sneaker)).thenReturn(sneaker);

        Sneaker result = sneakerService.create(sneaker);

        assertEquals("Air Max", result.getName());
        verify(sneakerRepository).save(sneaker);
    }

    @Test
    void testUpdateSuccess() {
        Long id = 1L;

        Sneaker existing = new Sneaker(
                id, "Old Name", "Black", 42F, 3, 10F, new Brand(), new Category()
        );

        Sneaker updated = new Sneaker(
                null, "New Name", "Red", 43F, 7, 12F, new Brand(), new Category()
        );

        when(sneakerRepository.findById(id)).thenReturn(Optional.of(existing));
        when(sneakerRepository.save(existing)).thenReturn(existing);

        Sneaker result = sneakerService.update(id, updated);

        assertEquals("New Name", result.getName());
        assertEquals("Red", result.getColor());
        assertEquals(12F, result.getSize());
        assertEquals(7, result.getStock());
        assertEquals(43F, result.getPrice());
        assertNotNull(result.getBrand());

        verify(sneakerRepository).findById(id);
        verify(sneakerRepository).save(existing);
    }

    @Test
    void testUpdateNotFound() {
        Long id = 1L;

        when(sneakerRepository.findById(id)).thenReturn(Optional.empty());

        Sneaker updated = new Sneaker(null, "New Name", "Red", 43F, 7, 10F, new Brand(), new Category());

        assertThrows(RuntimeException.class, () -> sneakerService.update(id, updated));

        verify(sneakerRepository).findById(id);
        verify(sneakerRepository, never()).save(any());
    }

    @Test
    void testDelete() {
        Long id = 1L;

        doNothing().when(sneakerRepository).deleteById(id);

        sneakerService.delete(id);

        verify(sneakerRepository).deleteById(id);
    }
}
