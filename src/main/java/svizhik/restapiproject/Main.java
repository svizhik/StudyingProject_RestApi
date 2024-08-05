package svizhik.restapiproject;

import svizhik.restapiproject.dto.Cat;
import svizhik.restapiproject.controller.MainController;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    List<Cat> cats = MainController.getCats;

    List<Cat> cats2 = cats.stream()
            .filter(c -> c.getAge() > 7)
            .collect(Collectors.toList());

        for(int i = 0; i < cats2.size(); i++){
        System.out.println(cats2.get(i));
    }


}
