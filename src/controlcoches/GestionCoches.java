/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlcoches;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sun.security.jgss.GSSUtil.login;

/**
 *
 * @author daw1
 */
public class GestionCoches {
    private Connection conexion;private static String bd = "parques";
    private static String login = "root";
    private static String password = "root";
    private static String url = "jdbc:mysql://localhost:3306/" + bd;
    private PreparedStatement psCreaTabla;
    private PreparedStatement psInsertarCoches;
    
    public boolean crearConexion() throws SQLException {
       boolean establecido= false;
            conexion = DriverManager.getConnection(url, login, password);
            if (conexion != null) {
                    establecido=true;
            }
               
         return establecido;  
       
    }
    
    public boolean crearTablaCoches() throws SQLException{
        String crearTabla="CREATE TABLE datos_coche (matricula varchar(10) NOT NULL,marca varchar(45) DEFAULT NULL,modelo  varchar(45) DEFAULT NULL,color varchar(45) DEFAULT NULL,año int(11) DEFAULT NULL,precio int(11) DEFAULT NULL,PRIMARY KEY (`matricula`)) ENGINE=InnoDB DEFAULT CHARSET=utf8";
        psCreaTabla=conexion.prepareStatement(crearTabla);             
        return psCreaTabla.execute();
    }
    
    public String cargarTablaCoches(File archivo) {
        String matricula= "",marca="", modelo="", color="",linea="" ;
        Integer año=0, precio=0;
        
        try(BufferedReader br = new BufferedReader(new FileReader(archivo))){
            while((linea=br.readLine())!=null){
                String [] datos = linea.split(":");
                matricula=datos[0];
                marca=datos[1];
                modelo=datos[2];
                color=datos[3];
                año=Integer.parseInt(datos[4]);
                precio=Integer.parseInt(datos[5]);
                
        }
        String insertCoches= "insert into datos_coche(matricula,marca,modelo,color,año,precio) values (?,?,?,?,?,?)";
            try {
                psInsertarCoches=conexion.prepareStatement(insertCoches);
                psInsertarCoches.setString(1,matricula);
                psInsertarCoches.setString(2,marca);
                psInsertarCoches.setString(3,modelo);
                psInsertarCoches.setString(4,color);
            } catch (SQLException ex) {
               
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
           throw new RuntimeException(ex);     
        }
     return "a";
    }
}
