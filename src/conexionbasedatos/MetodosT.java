/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionbasedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Adry
 */
public class MetodosT {
    public static void mostrarDatosT(){
         Connection c = null;
         Statement stmt = null;
         DefaultTableModel modelo=(DefaultTableModel) Ventana.jTable1.getModel();
         try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:prueba.db");
            c.setAutoCommit(false);
            
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANIA;");
            while(rs.next()){
                Object[] linea=new Object[5];
                linea[0]=rs.getString("dni");
                linea[1]=rs.getString("nombre");
                linea[2]=rs.getInt("edad");
                linea[3]=rs.getString("direcion");
                linea[4]=rs.getFloat("salario");
                modelo.addRow(linea);
                Ventana.jTable1.setModel(modelo);
            }
            rs.close();
            stmt.close();
            c.close();
    }catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
}
    public static void añadirT(){
        String tDni =Ventana.dniText.getText();
        String tNombre =Ventana.nombreText.getText();
        int tEdad =Integer.parseInt(Ventana.edadBox.getSelectedItem().toString());
        String tDireccion =Ventana.direcionText.getText();
        float tSalario =Float.parseFloat(Ventana.salarioText.getText());

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:prueba.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "INSERT INTO COMPANIA (DNI,NOMBRE,EDAD,DIRECION,SALARIO) " + "VALUES (" + "'" + tDni + "'," + "'" + tNombre + "'," + tEdad + ",'" + tDireccion + "'," + tSalario + ");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        DefaultTableModel modelo=(DefaultTableModel) Ventana.jTable1.getModel();
         for (int i = 0; i < Ventana.jTable1.getRowCount(); i++) {
           modelo.removeRow(i);
           i-=1;
       }
        MetodosT.mostrarDatosT();
        System.out.println("Insercion realizada con exito");
    }
    public static void eliminarFila(){
        Connection c = null;
        Statement stmt = null;
        
        String bDni = (String) Ventana.jTable1.getValueAt(Ventana.jTable1.getSelectedRow(), 0);
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:prueba.db");
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "DELETE from COMPANIA where DNI='" + bDni + "';";
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        DefaultTableModel modelo=(DefaultTableModel) Ventana.jTable1.getModel();
        modelo.removeRow(Ventana.jTable1.getSelectedRow());
        System.out.println("Borrado realizado con exito");
    }
    public static void actualizarCambios(){
            Connection c = null;
            Statement stmt = null;
            try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:prueba.db");
            c.setAutoCommit(false);
            
            String bDni=Ventana.dniText.getText();
            stmt = c.createStatement();
            String sql = "DELETE from COMPANIA where DNI='" + bDni + "';";
            stmt.executeUpdate(sql);
            c.commit();
            MetodosT.añadirT();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        }
    public static void seleccionarLinea(){
        if(Ventana.jTable1.getSelectedRow()!=-1){
            Ventana.dniText.setText( (String) Ventana.jTable1.getValueAt(Ventana.jTable1.getSelectedRow(), 0));
            Ventana.nombreText.setText((String) Ventana.jTable1.getValueAt(Ventana.jTable1.getSelectedRow(), 1));
            Ventana.edadBox.setSelectedIndex(((int)(Ventana.jTable1.getValueAt(Ventana.jTable1.getSelectedRow(), 2)))-18);
            Ventana.direcionText.setText((String) Ventana.jTable1.getValueAt(Ventana.jTable1.getSelectedRow(), 3));
            Ventana.salarioText.setText(""+Ventana.jTable1.getValueAt(Ventana.jTable1.getSelectedRow(), 4));
        }
    }
    }
    
    
    