package ConectaBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ModificaBBDD {
    public static void main(String[]args){

        try{

            Connection bd= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postgres");

            Statement miStatement=bd.createStatement();

            //String instruccionSql="insert into pruebas.productos(codigo_articulo,seccion,nombre_articulo,precio,fecha,importado,pais_origen) " +
            //        "values ('AR03','FERRETERIA','MARTILLO',85.6,'2022-02-01',false,'NICARAGUA')";

            String instruccionSql="update pruebas.productos set precio=precio*2 where codigo_articulo='AR03'";

            miStatement.executeUpdate(instruccionSql);

            System.out.println("Datos actualizados correctamente");

        }catch (Exception e){

            System.out.println("No conecta!!");
            e.getMessage();
        }


    }

}
