package StoreProcedure;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.*;

public class ActualizaProducto {

    public static void main(String[]args){

        BigDecimal nPrecio= BigDecimal.valueOf(Double.valueOf(JOptionPane.showInputDialog("Introduce Precio")));

        String nArticulo=JOptionPane.showInputDialog("Introduce nombre articulo");

        try {

            Connection conexion= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postgres");

            CallableStatement sentencia=conexion.prepareCall("{call fn_set_actualizar_precio_producto(?,?)}");

            sentencia.setBigDecimal(1,nPrecio);

            sentencia.setString(2,nArticulo);

            sentencia.execute();

            System.out.println("Actualizacion realizada");

            conexion.close();

        }catch (SQLException e){

            e.getMessage();

        }

    }
}
