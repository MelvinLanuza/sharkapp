package modelo;


import java.sql.*;

public class EjecutaConsultas {

    public EjecutaConsultas() {

        c = new Conexion();
    }

    public ResultSet filtraBBDD(String seccion, String pais) {

        Connection conecta = c.getConnection();

        resultado = null;
        Statement st;

        try {

            if (!seccion.equals("Todos") && pais.equals("Todos")) {

                script = conecta.prepareStatement(consultaSeccion);
                script.setString(1,seccion);
                resultado=script.executeQuery();

            } else if (seccion.equals("Todos") && !pais.equals("Todos")) {

                script=conecta.prepareStatement(consultaPais);
                script.setString(1,pais);
                resultado=script.executeQuery();

            } else if (!seccion.equals("Todos") && !pais.equals("Todos")) {

                script=conecta.prepareStatement(consultaF);
                script.setString(1,seccion);
                script.setString(2,pais);
                resultado=script.executeQuery();

            } else {

                st = conecta.createStatement();
                resultado = st.executeQuery("select nombre_articulo,seccion,precio,pais_origen from pruebas.productos");

            }

            conecta.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return resultado;
    }

    private Conexion c;
    private ResultSet resultado;
    private PreparedStatement script;
    private final String consultaSeccion = "select nombre_articulo,seccion,precio,pais_origen from pruebas.productos where seccion=?";
    private final String consultaPais = "select nombre_articulo,seccion,precio,pais_origen from pruebas.productos where pais_origen=?";
    private final String consultaF = "select nombre_articulo,seccion,precio,pais_origen from pruebas.productos where seccion=? and pais_origen=?";
}
