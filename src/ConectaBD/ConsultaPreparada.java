package ConectaBD;

import java.sql.*;

public class ConsultaPreparada {

    public static void main(String[]args){

        try {
            Connection bd= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postgres");

            PreparedStatement miSentencia=bd.prepareStatement("select  nombre_articulo, seccion, pais_origen from pruebas.productos " +
                    "where seccion=? and pais_origen=?");

            miSentencia.setString(1,"FERRETERIA");
            miSentencia.setString(2,"ESPAÑA");

            ResultSet rs=miSentencia.executeQuery();

            while (rs.next()){

               System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
            }

            rs.close();

            /**-----------REUTILIZACION----------------------------------------------------------------------------------------*/

            System.out.println("\n"+"Ejecutando segunda consulta"+"\n");

            miSentencia.setString(1,"CONFECCION");
            miSentencia.setString(2,"ITALIA");

            rs=miSentencia.executeQuery();

            while (rs.next()){

                System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3));
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
