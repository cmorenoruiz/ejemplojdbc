/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplojdbc;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author usuario
 */
public class EjemploJDBC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO code application logic here
        try {
            // Cargamos la clase que implementa el Driver
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            // Creamos una nueva conexión al SGBD de XAMPP
            String url = "jdbc:mysql://10.230.109.71:3306/";
            
            // Creamos una nueva conexión a la base de datos 'prueba'
            //String url = "jdbc:mysql://localhost:3306/prueba?serverTimezone=UTC";
            Connection conn = DriverManager.getConnection(url, "root", "");
            // Obtenemos un Statement de la conexión
            Statement st = conn.createStatement();
            String sql = "CREATE DATABASE vendedores";
            int conseguido;
            conseguido=st.executeUpdate(sql);
            System.out.println("Creada la BD: "+conseguido);
            //Voy a crear una tabla
            sql="USE vendedores";
            boolean resultado =st.execute(sql);
            System.out.println("Usando vendedores: "+resultado);
            sql="CREATE TABLE REGISTRATION "+
                    "(id INTEGER not NULL, "+
                    " first VARCHAR(255), "+
                    " last VARCHAR(255), "+
                    " age INTEGER, "+
                    " PRIMARY KEY ( id )  )";
            conseguido=st.executeUpdate(sql);
            System.out.println("Creada la tabla Registration: "+conseguido);

            // Execute a query
            System.out.println("Inserting records into the table...");
            sql = "INSERT INTO Registration VALUES (100, 'Zara', 'Ali', 18)";
            st.executeUpdate(sql);
            sql = "INSERT INTO Registration VALUES (101, 'Mahnaz', 'Fatma', 25)";
            st.executeUpdate(sql);
            sql = "INSERT INTO Registration VALUES (102, 'Zaid', 'Khan', 30)";
            st.executeUpdate(sql);
            sql = "INSERT INTO Registration VALUES(103, 'Sumit', 'Mittal', 28)";
            st.executeUpdate(sql);
            System.out.println("Inserted records into the table...");            
            
            // Ejecutamos una consulta SELECT para obtener la tabla vendedores
            sql = "SELECT id, first, last, age FROM Registration";;
            ResultSet rs = st.executeQuery(sql);
            // Recorremos todo el ResultSet y mostramos sus datos
            while (rs.next()) {
                System.out.print("ID: " + rs.getInt("id"));
                System.out.print(", Age: " + rs.getInt("age"));
                System.out.print(", First: " + rs.getString("first"));
                System.out.println(", Last: " + rs.getString("last"));
            }
            // Cerramos el statement y la conexión
            st.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Excepción SQL");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
