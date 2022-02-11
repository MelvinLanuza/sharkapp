package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CargaMenus {

    public CargaMenus(){

        miConexion=new Conexion();
    }

    public String ejecutaConsulta(){

        Productos miProducto=null;

        Connection accesoBBDD=miConexion.getConnection();

        try {

            Statement secciones=accesoBBDD.createStatement();
            Statement paises=accesoBBDD.createStatement();

            rs=secciones.executeQuery("select distinct seccion from pruebas.productos");
            rs2=paises.executeQuery("select distinct pais_origen from pruebas.productos");

            miProducto=new Productos();
            miProducto.setSeccion(rs.getString(1));
            miProducto.setpOrigen(rs2.getString(1));

            rs.close();
            rs2.close();

            accesoBBDD.close();

        }catch (Exception e){

            e.getMessage();
        }

        return miProducto.getSeccion();

    }

    public Conexion miConexion;
    public ResultSet rs;
    public ResultSet rs2;
}
