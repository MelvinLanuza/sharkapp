package AplicacionFinal;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class AplicacionUniversal {

    public static void main(String[] args) {

        MarcoBD marco = new MarcoBD();

        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        marco.setVisible(true);

    }
}

class MarcoBD extends JFrame {

    public MarcoBD() {

        setBounds(300, 300, 700, 700);
        setTitle("Tablas y sus registros");
        LaminaBD lamina = new LaminaBD();
        add(lamina);

    }

}

class LaminaBD extends JPanel {

    public LaminaBD() {

        setLayout(new BorderLayout());
        comboTablas = new JComboBox();

        conectarBD();
        obtenerTablas();

        comboTablas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String tabla = comboTablas.getSelectedItem().toString();

                mostrarInfoTabla(tabla);

            }
        });

        areaInformacion = new JTextArea();
        add(comboTablas, BorderLayout.NORTH);
        add(areaInformacion, BorderLayout.CENTER);


    }

    public void conectarBD() {

        conexion = null;

        String[] datos = new String[3];

        try {

            entrada = new FileReader("C:/Users/mrrodriguez/IdeaProjects/DataSource.txt");

        } catch (IOException e) {

            JOptionPane.showMessageDialog(this, "No se encontro el archivo DataSource.txt");

            JFileChooser chooser = new JFileChooser();

            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Archivos de texto", "txt");

            chooser.setFileFilter(filter);

            int returnVal = chooser.showOpenDialog(this);

            if(returnVal == JFileChooser.APPROVE_OPTION) {

                try {
                    entrada=new FileReader(chooser.getSelectedFile().getAbsolutePath());

                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }

        try {

            BufferedReader buffer = new BufferedReader(entrada);

            for (int i = 0; i <= 2; i++) {

                datos[i] = buffer.readLine();
            }

            conexion = DriverManager.getConnection(datos[0], datos[1], datos[2]);

            entrada.close();

            //conexion= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","postgres");

            //DatabaseMetaData datosBD=conexion.getMetaData();


        }catch(Exception e){

            e.printStackTrace();
        }

    }

    public void obtenerTablas() {

        ResultSet rs = null;

        try {
            DatabaseMetaData datosBD = conexion.getMetaData();

            rs = datosBD.getTables(null, "pruebas", null, new String[]{"TABLE"});

            while (rs.next()) {

                comboTablas.addItem(rs.getString("TABLE_NAME"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void mostrarInfoTabla(String nTabla) {

        ArrayList<String> campos = new ArrayList<>();

        String consulta = "select * from pruebas." + nTabla;

        try {

            areaInformacion.setText("");

            Statement sentencia = conexion.createStatement();

            ResultSet rs = sentencia.executeQuery(consulta);

            ResultSetMetaData rsMetaData = rs.getMetaData();

            for (int i = 1; i <= rsMetaData.getColumnCount(); i++) {

                campos.add(rsMetaData.getColumnLabel(i));
            }

            while (rs.next()) {

                for (String nCampo : campos) {

                    areaInformacion.append(rs.getString(nCampo) + " ");
                }
                areaInformacion.append("\n");
            }

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    private JComboBox comboTablas;
    private JTextArea areaInformacion;
    private Connection conexion;
    private FileReader entrada;
}
