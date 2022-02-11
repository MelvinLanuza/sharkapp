package ConectaBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConectaPruebas {
    public static void main(String[]args){

        try{

         Connection bd=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postgres");

            Statement miStatement=bd.createStatement();

            ResultSet miResultSet=miStatement.executeQuery("select * from pruebas.productos");

            while (miResultSet.next()){

             System.out.println(miResultSet.getString("codigo_articulo")+" "+miResultSet.getString("nombre_articulo")+" "+
                     miResultSet.getString("precio"));
            }


        }catch (Exception e){

            System.out.println("No conecta!!");
            e.getMessage();
        }


    }

}
