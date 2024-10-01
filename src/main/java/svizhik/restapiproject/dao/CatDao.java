package svizhik.restapiproject.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import svizhik.restapiproject.dto.Cat;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
public class CatDao {

    private final CatRepository catRepository;

    public CatDao(CatRepository catRepository) {this.catRepository = catRepository;}

    public List<Cat> selectAllCats() {
        return catRepository.findAll();
    }

    public List<Cat> selectCatId(Integer id) {
        return catRepository.findCatById(id);
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

    public boolean deleteCat(Integer id) {
        if (catRepository.existsById(Long.valueOf(id))) {
            catRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}




