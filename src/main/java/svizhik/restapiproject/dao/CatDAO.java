package svizhik.restapiproject.dao;

import lombok.extern.slf4j.Slf4j;
import svizhik.restapiproject.dto.Cat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CatDAO {

    private static final String selectAll = "SELECT * FROM cats";
    private static final String selectByAge = "SELECT * FROM cats WHERE age = ?";
    private static final String insertStmt = "INSERT INTO cats (name, age, weight) VALUES (?, ?, ?)";
    private static final String updateStmt = "UPDATE cats SET name = ?, age = ?, weight = ? WHERE id = ?";
    private static final String deleteById = "DELETE FROM cats WHERE id = ?";

    private static final Connection connection = getConnection();

    public static Connection getConnection() {
        String url = "jdbc:postgresql://localhost:5432/VetClinicHotel_Cats";
        String username = "postgres";
        String password = "7";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static List<Cat> selectCat(Integer age) {
        try {
            Statement statement = connection.createStatement();
            if (age == null) {
                log.info("sqlQuery: {}", selectAll);
                ResultSet resultSet = statement.executeQuery(selectAll);
                return mapToCats(resultSet);
            } else {
                PreparedStatement stmt = connection.prepareStatement(selectByAge);
                stmt.setInt(1, age);
                log.info("sqlQuery: {}", stmt);
                ResultSet resultSet = stmt.executeQuery();
                return mapToCats(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    private static List<Cat> mapToCats(ResultSet resultSet) throws SQLException {
        List<Cat> cats = new ArrayList<>();
        try (resultSet) {
            while (resultSet.next()) {
                cats.add(
                        new Cat(
                                resultSet.getInt("id"),
                                resultSet.getString("name"),
                                resultSet.getInt("age"),
                                resultSet.getInt("weight")
                        )
                );
            }
            return cats;
        }
    }

    public static boolean insertCat(Cat newCat) {
        try (PreparedStatement stmt = connection.prepareStatement(insertStmt)){
            stmt.setString(1, newCat.getName());
            stmt.setInt(2, newCat.getAge());
            stmt.setInt(3, newCat.getWeight());
            log.info("sqlQuery: {}", insertStmt);
            stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean updateCat(int id, Cat newCat) {
        try (PreparedStatement stmt = connection.prepareStatement(updateStmt)){
            stmt.setString(1, newCat.getName());
            stmt.setInt(2, newCat.getAge());
            stmt.setInt(3, newCat.getWeight());
            stmt.setInt(4, id);
            log.info("sqlQuery: {}", updateStmt);
            stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static int deleteCat(Integer id) {
        try (PreparedStatement stmt = connection.prepareStatement(deleteById)){
            stmt.setInt(1, id);
            log.info("sqlQuery: {}", deleteById);
            stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}