package co.edu.poli.sneaker_store_back.service;

import co.edu.poli.sneaker_store_back.entity.Brand;
import co.edu.poli.sneaker_store_back.repository.BrandRepository;
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
class BrandServiceTest {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandService brandService;

    @Test
    void testGetAll() {
        List<Brand> brands = Arrays.asList(
                new Brand(1L, "Nike", null),
                new Brand(2L, "Adidas", null)
        );

        when(brandRepository.findAll()).thenReturn(brands);

        List<Brand> result = brandService.getAll();

        assertEquals(2, result.size());
        assertEquals("Nike", result.get(0).getName());
        verify(brandRepository, times(1)).findAll();
    }

    @Test
    void testCreate() {
        Brand brand = new Brand(1L, "Puma", null);

        when(brandRepository.save(brand)).thenReturn(brand);

        Brand result = brandService.create(brand);

        assertEquals("Puma", result.getName());
        verify(brandRepository).save(brand);
    }

    @Test
    void testUpdateSuccess() {
        Long id = 1L;
        Brand existing = new Brand(id, "Old Name", null);
        Brand updated = new Brand(null, "New Name", null);

        when(brandRepository.findById(id)).thenReturn(Optional.of(existing));
        when(brandRepository.save(existing)).thenReturn(existing);

        Brand result = brandService.update(id, updated);

        assertEquals("New Name", result.getName());
        verify(brandRepository).findById(id);
        verify(brandRepository).save(existing);
    }

    @Test
    void testUpdateNotFound() {
        Long id = 1L;
        Brand updated = new Brand(null, "New Name", null);

        when(brandRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> brandService.update(id, updated));

        verify(brandRepository).findById(id);
        verify(brandRepository, never()).save(any());
    }

    @Test
    void testDelete() {
        Long id = 1L;

        doNothing().when(brandRepository).deleteById(id);

        brandService.delete(id);

        verify(brandRepository, times(1)).deleteById(id);
    }
}
