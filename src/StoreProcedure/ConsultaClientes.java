package StoreProcedure;

import java.sql.*;

public class ConsultaClientes {

    public static void main(String[]args){

        try {

            Connection conexion= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postgres");

            CallableStatement sentencia=conexion.prepareCall("{call fn_get_muestra_clientes}");

            //Statement

            ResultSet rs=sentencia.executeQuery();

            while (rs.next()){

                System.out.println(rs.getString(1)+", "+rs.getString(2)+", "+rs.getString(3));
            }
            rs.close();
            conexion.close();

        }catch (SQLException e){

            e.getMessage();

        }
    }
}
