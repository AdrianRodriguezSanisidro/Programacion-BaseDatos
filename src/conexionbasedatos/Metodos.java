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
                    + "DIRECION        TEXT, "
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
}
