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

    public List<Cat> getCat(Integer age) {
        return catDao.selectCat(age);
    }

    public boolean addCat(Cat newCat) {
        return catDao.insertCat(newCat);
    }

    public boolean updateCat(Optional<Cat> newCat) {
        return catDao.updateCat(newCat);
    }

    public int deleteCat(Integer id) {
        return catDao.deleteCat(id);
    }

}