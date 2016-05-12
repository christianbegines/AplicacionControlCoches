/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Modelo.Coche;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static sun.security.jgss.GSSUtil.login;

/**
 * Clase que ayuda a la gestion en la base de datos coches.
 *
 * @author christian begines
 * @since 2016
 *
 */
public class GestionCoches {

    private Connection conexion;
    private static String bd = "coches";
    private static String login = "root";
    private static String password = "root";
    private static String url = "jdbc:mysql://localhost:3306/" + bd;
    private String matricula = "", marca = "", modelo = "", color = "", linea = "";
    private Integer año = 0, precio = 0;

    /**
     * Establece la conexion en la base de datos de mySQL.
     *
     * @return establecido un boolean, que es true al conectarse sino quedara en
     * false
     * @throws SQLException si la conexion no se realiza se capturara la
     * excepcion
     */
    public boolean crearConexion() throws SQLException {
        boolean establecido = false;
        conexion = DriverManager.getConnection(url, login, password);
        if (conexion != null) {
            establecido = true;
        }

        return establecido;

    }

    /**
     * Crea la tabla datos_coche en la base de datos coches.
     *
     * @return Retorna un boolean producido por la ejecucion del
     * psCreaTabla.execute();
     * @throws SQLException La excepcion sera capturada cuando nose de un error
     * el create del sql.
     */
    public boolean crearTablaCoches() throws SQLException {
        boolean respuesta;
        PreparedStatement psCreaTabla;
        String crearTabla = "CREATE TABLE if not exists datos_coche (matricula varchar(10) NOT NULL,marca varchar(45) DEFAULT NULL,modelo  varchar(45) DEFAULT NULL,color varchar(45) DEFAULT NULL,año int(11) DEFAULT NULL,precio int(11) DEFAULT NULL,PRIMARY KEY (`matricula`)) ENGINE=InnoDB DEFAULT CHARSET=utf8";
        psCreaTabla = conexion.prepareStatement(crearTabla);
        respuesta = psCreaTabla.execute();
        return respuesta;
    }

    /**
     * Cargara la tabla recojida por un BufferedReader y lo cargara en la base
     * de datos.
     *
     * @param archivo El archivo leido por el BufferedReader de el cual obtendra
     * los datos a introducir en la base de datos.
     * @return numFilas Retorna el numero de filas introducidas correctamente.
     */
    public int cargarTablaCoches(File archivo) {
        int numFilas = 0;
        PreparedStatement psInsertarCoches;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            //recorre lo leido y lo asigna a los atributos
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(":");
                matricula = datos[0];
                marca = datos[1];
                modelo = datos[2];
                color = datos[3];
                año = Integer.parseInt(datos[4]);
                precio = Integer.parseInt(datos[5]);
                try {
                    //carga en el select los datos, para ser introducidos en la base de datos.
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

    /**
     * Muestra en la table de la aplicacion todos los registros de los coches,
     * obrenidos por el select buscarTodos.
     *
     * @return listaCoches Retorna la lista de los coches mostrados y los carga
     * en la tabla.
     */
    public List<Coche> mostrarCoches() {
        List<Coche> listaCoches = new ArrayList();
        ResultSet cochesMostrados;
        String buscarTodos = "select * from datos_coche";
        PreparedStatement psSelectAll;
        try {
            psSelectAll = conexion.prepareStatement(buscarTodos);
            cochesMostrados = psSelectAll.executeQuery();
            while (cochesMostrados.next()) {
                matricula = cochesMostrados.getString("matricula");
                marca = cochesMostrados.getString("marca");
                modelo = cochesMostrados.getString("modelo");
                color = cochesMostrados.getString("color");
                año = cochesMostrados.getInt("año");
                precio = cochesMostrados.getInt("precio");
                Coche c = new Coche(matricula, marca, modelo, color, año, precio);
                listaCoches.add(c);

            }

        } catch (SQLException ex) {

        }

        return listaCoches;
    }

    public boolean borrarTabla() throws SQLException {
        boolean respuesta;
        String borrar = "drop table  if exists datos_coche";
        PreparedStatement psBorrar;
        psBorrar = conexion.prepareStatement(borrar);
        respuesta = psBorrar.execute();
        return respuesta;
    }

    public boolean generarCatalogo(TableModel datosCoche, Path archivo) throws Exception {
        boolean res = false;
        int cuentaCoches = 1;
        Integer numFilas = datosCoche.getRowCount();
        Integer numColumnas = datosCoche.getColumnCount();

        try (BufferedWriter catalogoCreado = new BufferedWriter(new FileWriter(archivo.toFile(), false))) {

            for (int filasContadas = 0; filasContadas < numFilas; filasContadas++) {
                catalogoCreado.newLine();
                catalogoCreado.write("Coches nº" + cuentaCoches);
                catalogoCreado.newLine();
                for (int columnasContadas = 0; columnasContadas < numColumnas; columnasContadas++) {
                    
                    catalogoCreado.write(datosCoche.getColumnName(columnasContadas) + "......." + datosCoche.getValueAt(filasContadas, columnasContadas));
                    catalogoCreado.newLine();
                    res = true;

                }
                cuentaCoches++;
            }
        } catch (IOException ex) {
            throw new Exception(ex);
        }

        return res;
    }
}
