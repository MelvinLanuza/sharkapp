package Transacciones;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TransaccionProducto {

    public static void main(String[]args){

        Connection conexion=null;

        try{

            conexion= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postgres");

            conexion.setAutoCommit(false);

            Statement sentencia= conexion.createStatement();

            boolean ejecutar=ejecutarTransaccion();

            if (ejecutar){

                sentencia.executeUpdate("delete from pruebas.productos where pais_origen='ITALIA'");

                sentencia.executeUpdate("delete from pruebas.productos where precio>300");

                sentencia.executeUpdate("update pruebas.productos set precio=precio*1.15");

                conexion.commit();

                System.out.println("Se ejecuto la transaccion correctamente");

            }else {


                System.out.println("No se realizo ningun cambio en la base de datos.");
            }


        }catch (Exception e){

            try {
                conexion.rollback();

                System.out.println("No se realizo ningun cambio en la base de datos rollback.");
            } catch (SQLException ex) {

                ex.printStackTrace();
            }


        }

    }
    static boolean ejecutarTransaccion() {

        String ejecucion = JOptionPane.showInputDialog("Desea ejecutar la transaccion?");

        if (ejecucion.equals("si")) return true;

        else return false;

    }
}
