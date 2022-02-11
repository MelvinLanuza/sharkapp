package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    Connection conexion=null;

    public Conexion(){


    }

    public Connection getConnection(){

        try {
            conexion= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postgres");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conexion;
    }
}
