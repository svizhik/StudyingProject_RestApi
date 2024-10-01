package svizhik.restapiproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import svizhik.restapiproject.dto.Cat;

import java.util.List;

public interface CatRepository extends JpaRepository <Cat, Long> {
    List<Cat> findCatByAge(Integer age);
    Integer deleteById(Integer id);
    List<Cat> findCatById(Integer id);
    //save() уже есть внутри JpaRepository для insert и update,
    // так же и deleteCatById(), так же и findAll()
}
