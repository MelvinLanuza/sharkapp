package ConectaBD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AplicacionConsulta {

    public static void main(String[]args){

        JFrame miMarco=new MarcoAplicacion();
        miMarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        miMarco.setVisible(true);
    }
}

class MarcoAplicacion extends JFrame{

    public MarcoAplicacion(){

        setTitle("Consulta BBDD");
        setBounds(1500,300,400,400);
        setLayout(new BorderLayout());
        JPanel menu=new JPanel();
        menu.setLayout(new FlowLayout());
        secciones=new JComboBox();
        secciones.setEditable(false);
        secciones.addItem("Todos");

        paises=new JComboBox();
        paises.setEditable(false);
        paises.addItem("Todos");

        resultado=new JTextArea(4,50);
        resultado.setEditable(false);

        add(resultado);
        menu.add(secciones);
        menu.add(paises);

        add(menu,BorderLayout.NORTH);
        add(resultado,BorderLayout.CENTER);

        JButton btnConsulta=new JButton("Consulta");
        btnConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ejecutaConsulta();
            }
        });
        add(btnConsulta,BorderLayout.SOUTH);


        /**-----------CONECTANDO CON LA BASE DE DATOS---------------------------------------*/

        try{

            bd= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postgres");

            Statement sentencia=bd.createStatement();

            String consulta="select distinct seccion from pruebas.productos";

            ResultSet rs=sentencia.executeQuery(consulta);

            while (rs.next()){

                secciones.addItem(rs.getString(1));

            }

            consulta="select distinct pais_origen from pruebas.productos";

            rs=sentencia.executeQuery(consulta);

            while (rs.next()){

                paises.addItem(rs.getString(1));

            }

        }catch (Exception e){

            e.getMessage();
        }


    }

    private void ejecutaConsulta(){

        ResultSet rs;
        try {
            String seccion=secciones.getSelectedItem().toString();
            String pais=paises.getSelectedItem().toString();

            if (!seccion.equals("Todos") && pais.equals("Todos")){

                enviaConsulta=bd.prepareStatement(consultaSeccion);
                enviaConsulta.setString(1,seccion);
                rs=enviaConsulta.executeQuery();

            }else if (seccion.equals("Todos") && !pais.equals("Todos")){

                enviaConsulta=bd.prepareStatement(consultaPais);
                enviaConsulta.setString(1,pais);
                rs=enviaConsulta.executeQuery();

            }else  if(!seccion.equals("Todos") && !pais.equals("Todos")){

                enviaConsulta=bd.prepareStatement(consultaF);
                enviaConsulta.setString(1,seccion);
                enviaConsulta.setString(2,pais);
                rs=enviaConsulta.executeQuery();

            }else {

                Statement st=bd.createStatement();
                rs=st.executeQuery("select nombre_articulo,seccion,precio,pais_origen from pruebas.productos");
            }


            resultado.setText("");

            while (rs.next()){

                resultado.append(rs.getString(1));
                resultado.append(", ");
                resultado.append(rs.getString(2));
                resultado.append(", ");
                resultado.append(rs.getString(3));
                resultado.append(", ");
                resultado.append(rs.getString(4));
                resultado.append("\n");
            }

        }catch (Exception e){

            e.getMessage();
        }

    }

    private JComboBox secciones, paises;
    private JTextArea resultado;
    private Connection bd;
    private PreparedStatement enviaConsulta;
    private final String consultaSeccion="select nombre_articulo,seccion,precio,pais_origen from pruebas.productos where seccion=?";
    private final String consultaPais="select nombre_articulo,seccion,precio,pais_origen from pruebas.productos where pais_origen=?";
    private final String consultaF="select nombre_articulo,seccion,precio,pais_origen from pruebas.productos where seccion=? and pais_origen=?";

}