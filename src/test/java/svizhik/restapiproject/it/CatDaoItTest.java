package svizhik.restapiproject.it;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import svizhik.restapiproject.dao.CatDao;
import svizhik.restapiproject.dto.Cat;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Import(CatDao.class)
public class CatDaoItTest {

    @Autowired
    private CatDao catDao;

    @Test
    public void testSelectAllCats() {
        List<Cat> cats = catDao.selectAllCats();

        Cat foundCat = cats.get(0);
        Cat foundCat2 = cats.get(1);

        assertEquals("Пушок", foundCat.getName());
        assertEquals("Барсик", foundCat2.getName());

        System.out.println("Returned cats: " + cats);
    }

    @Test
    public void testSelectCatByAge() {
        List<Cat> cats = catDao.selectCat(7);

        assertEquals(3, cats.size());

        System.out.println("Размер списка котов с возрастом 7: " + cats.size() + ". Список котов: " + cats + ".");

        assertEquals("Тигруша", cats.get(0).getName());
        assertEquals("Мурзик", cats.get(1).getName());
        assertEquals("Морковка", cats.get(2).getName());
    }

    @Test
    public void testInsertCat() {
        boolean result = catDao.insertCat(
                new Cat(
                        Integer.MAX_VALUE-1,
                        "Том",
                        7,
                        10
                )
        );

        assertTrue(result, "Кот успешно добавлен");

        // переписать на select по id
        List<Cat> cats = catDao.selectAllCats();
        assertEquals("Том", cats.get(cats.size() - 1).getName());
    }

    @Test
    public void testUpdateCat() {
        Cat cUpdated = new Cat(27, "Рысь", 4, 9);
        boolean result = catDao.updateCat(Optional.of(cUpdated));

        assertTrue(result, "Кот успешно обновлен");

        List<Cat> cats = catDao.selectAllCats();
        assertEquals(4, cats.get(27).getAge(), "Возраст кота - 4");
    }

    @Test
    public void testDeleteCat() {
        boolean result = catDao.deleteCat(28);

        assertTrue(result, "Кот успешно удален");

        List<Cat> cats = catDao.selectAllCats();
        assertEquals(27, cats.size());
        System.out.println("Ожидается: Рысь. Последнего кота в списке зовут: " + cats.get(26).getName() + ".");
        assertEquals("Рысь", cats.get(26).getName());
    }

}