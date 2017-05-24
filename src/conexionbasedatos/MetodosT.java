/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionbasedatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
            System.out.println("Base de datos abierta exitosamente");
            
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANIA;");
            while(rs.next()){
                Object[] fila=new Object[5];
                fila[0]=rs.getString("dni");
                fila[1]=rs.getString("nombre");
                fila[2]=rs.getInt("edad");
                fila[3]=rs.getString("direcion");
                fila[4]=rs.getFloat("salario");
                modelo.addRow(fila);
                Ventana.jTable1.setModel(modelo);
            }
            rs.close();
            stmt.close();
            c.close();
    }catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
         System.out.println("Base cargada completamente");
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
            System.out.println("Base de datos abierta exitosamente");

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
        
        int linea = Ventana.jTable1.getSelectedRow();
        Object bDni = Ventana.jTable1.getValueAt(linea, 0);
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:prueba.db");
            c.setAutoCommit(false);
            System.out.println("Base de datos abierta exitosamente");

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
    
    }
    
    