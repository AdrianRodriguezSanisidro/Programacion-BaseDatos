/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionbasedatos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Adry
 */
public class Metodos {

    public void conectarBD() {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:prueba.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Base de datos abierta exitosamente");
    }

    public void CrearTabla() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:prueba.db");
            System.out.println("Base de datos abierta exitosamente");

            stmt = c.createStatement();
            String sql = "CREATE TABLE COMPANIA "
                    + "(DNI TEXT PRIMARY KEY     NOT NULL,"
                    + "NOMBRE           TEXT    NOT NULL,"
                    + "EDAD            INT     NOT NULL, "
                    + "DIRECCION        TEXT, "
                    + "SALARIO        REAL)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Tabla creada con exito");
    }

    public void insertarDatos() {
        String tDni = JOptionPane.showInputDialog("Dni");
        String tNome = JOptionPane.showInputDialog("Nombre");
        int tEdad = Integer.parseInt(JOptionPane.showInputDialog("Edad"));
        String tDireccion = JOptionPane.showInputDialog("Direccion");
        float tSalario = Float.parseFloat(JOptionPane.showInputDialog("Salario"));

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:prueba.db");
            c.setAutoCommit(false);
            System.out.println("Base de datos abierta exitosamente");

            stmt = c.createStatement();
            String sql = "INSERT INTO COMPANIA (DNI,NOMBRE,EDAD,DIRECION,SALARIO) " + "VALUES (" + "'" + tDni + "'," + "'" + tNome + "'," + tEdad + ",'" + tDireccion + "'," + tSalario + ");";
            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Insercion realizada con exito");
    }

    public void mostrarDatos() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:prueba.db");
            c.setAutoCommit(false);
            System.out.println("Base de datos abierta exitosamente");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANIA;");
            while (rs.next()) {
                String mDni = rs.getString("dni");
                String mNombre = rs.getString("nombre");
                int mEdad = rs.getInt("edad");
                String mDireccion = rs.getString("direcion");
                float mSalario = rs.getFloat("salario");
                System.out.println("--------------------------------------------------------------------");
                System.out.println("DNI: " + mDni);
                System.out.println("Nombre: " + mNombre);
                System.out.println("Edad: " + mEdad);
                System.out.println("Direccion: " + mDireccion);
                System.out.println("Salario: " + mSalario);
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Tablas cargadas con exito");
    }

    public void borrarDatos() {
        String bDni = JOptionPane.showInputDialog("Dni que quieres borrar");
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:prueba.db");
            c.setAutoCommit(false);
            System.out.println("Base de datos abierta exitosamente");

            stmt = c.createStatement();
            String sql = "DELETE from COMPANIA where DNI=" + bDni + ";";
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Borrado realizado con exito");
    }

    public void actualizarDatos() {
        String eleccion = null;
        String cDni = JOptionPane.showInputDialog("Dni de la persona que quieres actualizar");
        int elegir = Integer.parseInt(JOptionPane.showInputDialog("1=Cambiar nombre\n2=Cambiar edad\n3=Cambiar direccion\n4=Cambiar salario"));
        String cambio = JOptionPane.showInputDialog("Nuevos datos");
        if (elegir == 1) {
            eleccion = "nombre";
            cambio = "'" + cambio + "'";
        } else if (elegir == 2) {
            eleccion = "edad";
            Integer.parseInt(cambio);
        } else if (elegir == 3) {
            eleccion = "direcion";
            cambio = "'" + cambio + "'";
        } else if (elegir == 4) {
            eleccion = "salario";
            Float.parseFloat(cambio);
        } else {
            System.out.println("No hiciste una eleccion valida");
        }
        Connection c = null;
        Statement stmt = null;
        if (elegir == 1) {

        }
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:prueba.db");
            c.setAutoCommit(false);
            System.out.println("Base de datos abierta exitosamente");

            stmt = c.createStatement();
            String sql = "UPDATE COMPANIA set " + eleccion + " = " + cambio + " where dni='" + cDni + "';";
            stmt.executeUpdate(sql);
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Actualizacion realizada con exito");
    }
//    public void borrarTabla() throws ClassNotFoundException{
//         Connection c = null;
//         Statement stmt = null;
//        try {
//            Class.forName("org.sqlite.JDBC");
//            c = DriverManager.getConnection("jdbc:sqlite:prueba.db");
//            c.setAutoCommit(false);
//            System.out.println("Base de datos abierta exitosamente");
//            stmt=c.createStatement();
//            stmt.executeUpdate("Drop table COMPANIA");
//        } catch (SQLException ex) {
//            Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
//        }
//          
//    }
}
