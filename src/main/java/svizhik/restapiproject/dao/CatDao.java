package svizhik.restapiproject.dao;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import svizhik.restapiproject.dto.Cat;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class CatDao {

    private final CatRepository catRepository;

    public CatDao(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    //todo: Как написать, чтобы выводил весь список, если нет аргументов?
    public List<Cat> selectAllCats() {
        return catRepository.findAll();
    }

    public List<Cat> selectCat(Integer age) {
        return catRepository.findCatByAge(age);
    }

    public boolean insertCat(Cat newCat) {
        try {
            catRepository.save(newCat);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateCat(Optional<Cat> opt) {
        if(opt.isPresent()){
          opt.ifPresent(catRepository::save);
          return true;
        }
        return false;
    }

    public int deleteCat(Integer id) {
        return catRepository.deleteById(id);
    }

}




