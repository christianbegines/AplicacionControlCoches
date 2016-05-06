/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;




import Modelo.Coche;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sun.security.jgss.GSSUtil.login;

/**
 *
 * @author daw1
 */
public class GestionCoches {

    private Connection conexion;
    private static String bd = "parques";
    private static String login = "root";
    private static String password = "root";
    private static String url = "jdbc:mysql://localhost:3306/" + bd;
    private String matricula = "", marca = "", modelo = "", color = "", linea = "";
    private Integer año = 0, precio = 0;
    

    public boolean crearConexion() throws SQLException {
        boolean establecido = false;
        conexion = DriverManager.getConnection(url, login, password);
        if (conexion != null) {
            establecido = true;
        }

        return establecido;

    }
    
    public boolean crearTablaCoches() throws SQLException {
        PreparedStatement psCreaTabla;
        String crearTabla = "CREATE TABLE datos_coche (matricula varchar(10) NOT NULL,marca varchar(45) DEFAULT NULL,modelo  varchar(45) DEFAULT NULL,color varchar(45) DEFAULT NULL,año int(11) DEFAULT NULL,precio int(11) DEFAULT NULL,PRIMARY KEY (`matricula`)) ENGINE=InnoDB DEFAULT CHARSET=utf8";
        psCreaTabla = conexion.prepareStatement(crearTabla);
        return psCreaTabla.execute();
    }

    public int cargarTablaCoches(File archivo) {
        int numFilas = 0;
        
        PreparedStatement psInsertarCoches;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(":");
                matricula = datos[0];
                marca = datos[1];
                modelo = datos[2];
                color = datos[3];
                año = Integer.parseInt(datos[4]);
                precio = Integer.parseInt(datos[5]);
                try {
                    String insertCoches = "insert into datos_coche(matricula,marca,modelo,color,año,precio) values (?,?,?,?,?,?)";

                    psInsertarCoches = conexion.prepareStatement(insertCoches);
                    psInsertarCoches.setString(1, matricula);
                    psInsertarCoches.setString(2, marca);
                    psInsertarCoches.setString(3, modelo);
                    psInsertarCoches.setString(4, color);
                    psInsertarCoches.setInt(5, año);
                    psInsertarCoches.setInt(6, precio);

                    if (psInsertarCoches.executeUpdate() == 1) {
                        numFilas++;
                    }
                } catch (SQLException ex) {

                }
            }

        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return numFilas;
    }
    
    public List<Coche> mostrarCoches(String sql){
        List<Coche> listaCoches= new ArrayList();
        ResultSet cochesMostrados;
        String buscarTodos="select * from datos_coche";
        PreparedStatement psSelectAll;
        try {
            psSelectAll=conexion.prepareStatement(buscarTodos);
            cochesMostrados=psSelectAll.executeQuery();
            while(cochesMostrados.next()){
                matricula=cochesMostrados.getString("matricula");
                marca=cochesMostrados.getString("marca");
                modelo=cochesMostrados.getString("modelo");
                color=cochesMostrados.getString("color");
                año=cochesMostrados.getInt("año");
                precio=cochesMostrados.getInt("precio");
                Coche c= new Coche(matricula,marca,modelo,color,año,precio);
                listaCoches.add(c);
               
            }
            
        } catch (SQLException ex) {
           
        }
        
        return listaCoches;
    }
}
