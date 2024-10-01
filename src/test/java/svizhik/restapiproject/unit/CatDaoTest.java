package svizhik.restapiproject.unit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import svizhik.restapiproject.dao.CatDao;
import svizhik.restapiproject.dto.Cat;
import svizhik.restapiproject.dao.CatRepository;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CatDaoTest {

    @Autowired
    private CatDao catDao;

    @MockBean
    private CatRepository catRepository;


    @Test
    public void testSelectAllCats() {
        Cat cat = new Cat(1, "Tom", 7, 10);
        when(catRepository.findAll()).thenReturn(List.of(cat));

        List<Cat> cats = catDao.selectAllCats();

        // todo ИСПРАВИТЬ, ЧТОБЫ ПРОВЕРЯЛ СПИСОК
        assertEquals(1, cats.size(), "Expected one cat in the list");
        assertEquals(cat, cats.get(0),
                "The returned cat should be Tom");

        System.out.println("Returned cats: " + cats);
    }

    @Test
    public void testSelectCatByAge() {
        Cat cat = new Cat(1, "Tom", 7, 10);
        when(catRepository.findCatByAge(7)).thenReturn(List.of(cat));

        List<Cat> cats = catDao.selectCat(7);

        assertEquals(1, cats.size(), "Expected one cat in the list");
        assertEquals(cat, cats.get(0), "The returned cat should match the search criteria");
    }

    @Test
    public void testInsertCat() {
        Cat cat = new Cat(1, "Tom", 7, 10);
        when(catRepository.save(cat)).thenReturn(cat);

        boolean result = catDao.insertCat(cat);

        assertTrue(result, "The cat should be inserted successfully");
    }

    @Test
    public void testUpdateCat() {
        Cat cat = new Cat(1, "Tom", 7, 10);
        when(catRepository.save(any(Cat.class))).thenReturn(cat);

        boolean result = catDao.updateCat(Optional.of(cat));

        assertTrue(result, "The cat should be updated successfully");
    }

    @Test
    public void testDeleteCat() {
        when(catRepository.existsById(1L)).thenReturn(true);
        when(catRepository.deleteById(1)).thenReturn(1);

        boolean result = catDao.deleteCat(1);

        assertTrue(result, "The cat deleted successfully");
    }
}

