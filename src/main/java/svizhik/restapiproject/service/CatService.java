package svizhik.restapiproject.service;

import svizhik.restapiproject.dao.CatDAO;
import svizhik.restapiproject.dto.Cat;

import java.sql.*;
import java.util.List;


public class CatService {


    public static List<Cat> getCat(Integer age) {
        return CatDAO.selectCat(age);
    }

    public static boolean addCat(Cat newCat) {
        return CatDAO.insertCat(newCat);
    }

    public static boolean updateCat(int id, Cat newCat) {
        return CatDAO.updateCat(id, newCat);
    }

    public static int deleteCat(Integer id) {
        return CatDAO.deleteCat(id);
    }

}