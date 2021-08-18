package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }
    Util util = new Util();
    Statement statement;

    {
        try {
            statement = util.getConnection().createStatement();
        } catch (SQLException e) {
            System.out.println("Не удалось устаовить соединение");
        }
    }

    public void createUsersTable() {


        try {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users" +
                       "(id BIGINT auto_increment, " +
                       " name VARCHAR(40) not null, " +
                       " lastName VARCHAR(40) not null, " +
                       " age TINYINT not null, " +
                       " constraint users_pk " +
                       " primary key (id))");
        } catch (SQLException e) {
            System.out.println("Не удалось создать таблицу");
        }

    }

    public void dropUsersTable() {

        try {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            System.out.println("Не удалось удалить таблицу");
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, lastName, age) VALUES (?,?,?)";
        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement(sql)){

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Не удалось добавить пользователя " + name + " в таблицу");
        }
        try {
            statement.execute("INSERT INTO users ('" + name +"', '" + lastName + "', '" +  age + "'");
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Не удалось добавить пользователя " + name + " в таблицу");
        }

    }

    public void removeUserById(long id) {
        try {
            statement.executeUpdate("DELETE FROM users WHERE id=" + id);
        } catch (SQLException e) {
            System.out.println("Не удалось удалить пользователя в id " + id);
        }

    }

    public List<User> getAllUsers() {
        ResultSet resultSet = null;
        List<User> list = new ArrayList<>();
        try {
            resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User user = new User(resultSet.getString("name"),
                        resultSet.getString("lastName"
                        ), resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                list.add(user);
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(list.get(i));
                }

            }
        } catch (SQLException e) {
            System.out.println("Не удалось получить всех пользователей из таблицы");
        }
        return list;
    }

    public void cleanUsersTable() {

        try {
            statement.execute("TRUNCATE TABLE users");
        } catch (SQLException e) {
            System.out.println("Не удалось очистить таблицу");
        }

    }
}
