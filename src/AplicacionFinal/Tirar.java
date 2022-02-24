package AplicacionFinal;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Tirar {

    public static void main(String[]args){

        Marco marco=new Marco();
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setVisible(true);

        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Archivos de texto", "txt");

        chooser.setFileFilter(filter);

        int returnVal = chooser.showOpenDialog(marco);

        if(returnVal == JFileChooser.APPROVE_OPTION) {

            System.out.println("You chose to open this file: " +
                    chooser.getSelectedFile().getAbsolutePath());
        }
    }
}
class Marco extends JFrame{

    public Marco(){

        setBounds(300,300,300,300);
    }
}
