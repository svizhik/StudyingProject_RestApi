package svizhik.restapiproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import svizhik.restapiproject.dao.CatDao;
import svizhik.restapiproject.dto.Cat;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatService {

    private final CatDao catDao;

    public List<Cat> getAllCats() {
        return catDao.selectAllCats();
    }

    public List<Cat> getCat(Integer age) {
        return catDao.selectCat(age);
    }

    public List<Cat> getCatId(Integer id) {
        return catDao.selectCatId(id);
    }

    public boolean addCat(Cat newCat) {
        return catDao.insertCat(newCat);
    }

    public boolean updateCat(Optional<Cat> newCat) {
        return catDao.updateCat(newCat);
    }

    public boolean deleteCat(Integer id) {
        return catDao.deleteCat(id);
    }

}