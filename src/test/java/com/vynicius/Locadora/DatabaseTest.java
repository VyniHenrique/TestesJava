package com.vynicius.Locadora;


import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DatabaseTest {

    private static Connection connection;

    @BeforeAll
    static void setUpDatabase() throws Exception{
        connection = DriverManager
                .getConnection("jdbc:h2:mem:testedb", "sa", "");

        connection.createStatement().execute("CREATE TABLE users (id INT, name VARCHAR)");
    }

    @BeforeEach
    void insertUserTest() throws Exception{
        connection.createStatement().execute("insert into users(id, name) values(1, 'teste')");
    }

    @Test
    @Disabled
    void testeUsuarioExiste() throws Exception{
        ResultSet result = connection.createStatement().executeQuery("select * from users where id = 1");

        Assertions.assertTrue(result.next());
    }

    @AfterAll
    static void fechaConexao() throws Exception{
        connection.close();
    }
}
