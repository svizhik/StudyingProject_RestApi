package svizhik.restapiproject.unit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import svizhik.restapiproject.dao.CatDao;
import svizhik.restapiproject.dto.Cat;
import svizhik.restapiproject.service.CatService;
import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CatServiceTest {

    @Autowired
    private CatService catService;

    @MockBean
    private CatDao catDao;

    @Test
    public void testGetCat() {
        Cat cat = new Cat(1, "Tom", 7, 10);
        when(catDao.selectCat(anyInt())).thenReturn(List.of(cat));

        List<Cat> cats = catService.getCat(7);

        assertEquals(1, cats.size(), "Expected one cat in the list");
        assertEquals(cat, cats.get(0), "The returned cat should be the one we prepared");

        System.out.println("Returned cats: " + cats);
    }

    @Test
    public void testAddCat() {
        Cat newCat = new Cat(7, "Tom", 3, 5);
        when(catDao.insertCat(any(Cat.class))).thenReturn(true);

        boolean result = catService.addCat(newCat);

        assertEquals(true, result, "The cat should be added successfully");

        System.out.println("Add cat result: " + result);
    }

    @Test
    public void testUpdateCat() {
        Cat updatedCat = new Cat(1, "Tom", 4, 6);
        when(catDao.updateCat(any(Optional.class))).thenReturn(true);

        boolean result = catService.updateCat(Optional.of(updatedCat));

        assertEquals(true, result, "The cat should be updated successfully");

        System.out.println("Update cat result: " + result);
    }

    @Test
    public void testDeleteCat() {
        when(catDao.deleteCat(eq(1))).thenReturn(true);
        when(catDao.deleteCat(eq(99))).thenReturn(false);

        boolean resultSuccessfulDeletion = catService.deleteCat(1);
        assertEquals(true, resultSuccessfulDeletion, "The cat should be deleted successfully");

        boolean resultUnsuccessfulDeletion = catService.deleteCat(99);
        assertEquals(false, resultUnsuccessfulDeletion, "The cat with ID 99 should not be found");

        System.out.println("Successful deletion result: " + resultSuccessfulDeletion);
        System.out.println("Unsuccessful deletion result: " + resultUnsuccessfulDeletion);
    }
}