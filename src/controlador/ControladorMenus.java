package controlador;

import modelo.*;
import vista.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ControladorMenus extends WindowAdapter {

    public ControladorMenus(MarcoAplicacion elmarco){

        this.elmarco=elmarco;
    }

    public void windowOpened(WindowEvent e){

        obj.ejecutaConsulta();

        try {

            while (obj.rs.next()){

                elmarco.secciones.addItem(obj.rs.getString(1));
            }

            while (obj.rs2.next()){

                elmarco.paises.addItem(obj.rs2.getString(1));
            }

        }catch (Exception e2){

            e2.getMessage();
        }

    }

    CargaMenus obj=new CargaMenus();
    private MarcoAplicacion elmarco;
}
