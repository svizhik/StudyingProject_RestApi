package svizhik.restapiproject.service;

import svizhik.restapiproject.dto.Cat;
import java.util.ArrayList;
import java.util.List;

public class CatService {

    static List<Cat> cats = new ArrayList<>();

    static {
        initCats();
    }

    public static void initCats(){
        cats.add(new Cat(1, "без породы", 9, 6));
        cats.add(new Cat(2, "сибирский", 3, 6));
        cats.add(new Cat(3, "без породы", 9, 10));
        cats.add(new Cat(4, "сибирский", 2, 7));
        cats.add(new Cat(5, "без породы", 4, 4));
        cats.add(new Cat(6, "британский вислоухий", 4, 6));
        cats.add(new Cat(7, "сибирский", 3, 10));
        cats.add(new Cat(8, "без породы", 7, 5));
        cats.add(new Cat(9, "британский вислоухий", 3, 6));
        cats.add(new Cat(10,"мейн-кун", 8, 10));
        cats.add(new Cat(11,"сибирский", 2, 6));
    }


    public static List<Cat> getCats(Integer age) {
        if (age == null) {
            return cats;
        }
        return cats.stream()
                .filter(c -> c.getAge() == age)
                .toList();
    }

    public static void addCat(Cat newCat) {
        cats.add(newCat);
    }

    public static boolean updateCat(int id, Cat updatedCat) {
        for (Cat cat : cats) {
            if (cat.getId() == id) {
                cat.setName(updatedCat.getName());
                cat.setAge(updatedCat.getAge());
                cat.setWeight(updatedCat.getWeight());
            }
        }
        return true;
    }

    public static int deleteCats(Integer id) {
        int count = cats.size();
        if (id == null) {
            return 0;
        }
        cats.removeIf(c -> c.getId() == id);
        count -= cats.size();
        return count;
    }

}
