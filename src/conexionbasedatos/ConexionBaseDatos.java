/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionbasedatos;

import javax.swing.JOptionPane;

/**
 *
 * @author Adry
 */
public class ConexionBaseDatos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Metodos objCon=new Metodos();
        int opcion=0;
        do{
        opcion=Integer.parseInt(JOptionPane.showInputDialog("1=Insertar datos\n2=Mostrar datos\n3=Borrar datos\n4=Actualizar datos\n5=Crear la tabla"));
        switch(opcion){
            case 1:objCon.insertarDatos();
            break;
            case 2:objCon.mostrarDatos();
            break;
            case 3:objCon.borrarDatos();
            break;
            case 4:objCon.actualizarDatos();
            break;
            case 5:objCon.CrearTabla();
            break;
//            case 6:objCon.borrarTabla();
//            break;
        }
    }while (opcion!=0);
    
}
}
