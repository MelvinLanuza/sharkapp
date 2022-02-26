package Transacciones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertaClientesPedidos {

    public static void main(String[]args){

        Connection conexion=null;

        try{

            conexion= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postgres");

            conexion.setAutoCommit(false);

            Statement sentencia= conexion.createStatement();

            sentencia.executeUpdate("INSERT INTO pruebas.clientes (codigo_cliente,empresa,direccion,ciudad,telefono,contacto) values('C003','PEPSI','C. NORTE','MANAGUA',22459631,'MANUEL PICADO')");

            sentencia.executeUpdate("INSERT INTO pruebas.pedidos (numero_pedido,codigo_cliente,fecha,forma_pago,descuento,enviado) values(1,'C003','2022-02-15','CONTADO',10,true)");

            conexion.commit();

            System.out.println("Datos Insertados Correctamente");

        }catch (Exception e){

            System.out.println("Error Insertando datos");

            try {
                conexion.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            e.getMessage();
        }
    }
}
