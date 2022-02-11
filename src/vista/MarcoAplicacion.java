package vista;

import controlador.ControladorBtnConsulta;
import controlador.ControladorMenus;

import javax.swing.*;
import java.awt.*;

public class MarcoAplicacion extends JFrame {

    public MarcoAplicacion() {

        setTitle("Consulta BBDD");
        setBounds(500, 300, 400, 400);
        setLayout(new BorderLayout());
        JPanel menu = new JPanel();
        menu.setLayout(new FlowLayout());

        secciones = new JComboBox();
        secciones.setEditable(false);
        secciones.addItem("Todos");

        paises = new JComboBox();
        paises.setEditable(false);
        paises.addItem("Todos");

        resultado = new JTextArea(4, 50);
        resultado.setEditable(false);

        add(resultado);
        menu.add(secciones);
        menu.add(paises);

        add(menu, BorderLayout.NORTH);
        add(resultado, BorderLayout.CENTER);

        JButton btnConsulta = new JButton("Consulta");

        add(btnConsulta, BorderLayout.SOUTH);

        btnConsulta.addActionListener(new ControladorBtnConsulta(this));

        addWindowListener(new ControladorMenus(this));

    }

    public JComboBox secciones, paises;
    public JTextArea resultado;
}
