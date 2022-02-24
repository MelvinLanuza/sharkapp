package metaDatos;

import org.omg.CORBA.SetOverrideType;

import java.sql.*;

public class InfoMetaDatos {

    public static void  main(String[]args){

        //mostrarInfoBD();

        mostrarInfoTablas();


    }
    static void mostrarInfoBD(){

        Connection conexion=null;

        try {

            conexion= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postgres");

            DatabaseMetaData datoBD=conexion.getMetaData();

            System.out.println("Gestor de base de datos: "+datoBD.getDatabaseProductName());

            System.out.println("Version del gestor: "+datoBD.getDatabaseProductVersion());

            System.out.println("Nombre del driver: "+datoBD.getDriverName());

            System.out.println("Version del driver: "+datoBD.getDriverVersion());


        } catch (SQLException e) {

            e.printStackTrace();

        }finally{

            try {
                conexion.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static void mostrarInfoTablas(){

        Connection conexion=null;

        ResultSet rs;

        try {

            conexion= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postgres");

            DatabaseMetaData datoBD=conexion.getMetaData();

            System.out.println("Lista de tablas");

            rs=datoBD.getTables(null,"pruebas",null, new String[]{"TABLE"});

            while (rs.next()){

                System.out.println(rs.getString("TABLE_NAME"));

                //System.out.println(rs.getString("TYPE_NAME")+"*");
            }

            System.out.println(" ");
            System.out.println("Campos de Productos");

            rs=datoBD.getColumns(null,"pruebas","clientes",null);
            while (rs.next()){

                System.out.println(rs.getString("COLUMN_NAME"));

                //System.out.println(rs.getString("TYPE_NAME")+"*");
            }

        } catch (SQLException e) {

            e.printStackTrace();

        }finally{

            try {
                conexion.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
