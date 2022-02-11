package controlador;

import modelo.*;
import vista.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControladorBtnConsulta implements ActionListener {

    public ControladorBtnConsulta(MarcoAplicacion elMarco) {

        this.elMarco = elMarco;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        String seleccionSeccion = elMarco.secciones.getSelectedItem().toString();
        String seleccionPais = elMarco.paises.getSelectedItem().toString();

        resultadoConsulta = obj.filtraBBDD(seleccionSeccion, seleccionPais);

        elMarco.resultado.setText("");

        try {
            while (resultadoConsulta.next()) {

                elMarco.resultado.append(resultadoConsulta.getString(1));
                elMarco.resultado.append(", ");
                elMarco.resultado.append(resultadoConsulta.getString(2));
                elMarco.resultado.append(", ");
                elMarco.resultado.append(resultadoConsulta.getString(3));
                elMarco.resultado.append(", ");
                elMarco.resultado.append(resultadoConsulta.getString(4));
                elMarco.resultado.append("\n");

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }

    private MarcoAplicacion elMarco;
    EjecutaConsultas obj = new EjecutaConsultas();
    private ResultSet resultadoConsulta;
}
