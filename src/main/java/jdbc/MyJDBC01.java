package jdbc;

import java.sql.*;

public class MyJDBC01 {
    public static void main(String[] args) {

            Connection conexion = null;

            try{

                conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc", "root", "root");
                Statement statement = conexion.createStatement();

                System.out.println("id dni nombre apellido");
                ResultSet mostrarRegistros = statement.executeQuery("SELECT * FROM estudiante;");

                while(mostrarRegistros.next()){
                    System.out.println(mostrarRegistros.getString(1) + " " + mostrarRegistros.getString(2) + " " + mostrarRegistros.getString(3) + " " + mostrarRegistros.getString(4));
                }

            } catch (SQLException sqlException){

                System.out.println(sqlException);
                if(conexion != null) {
                    try {
                        conexion.rollback();
                    } catch (SQLException sqlException1){
                        System.out.println(sqlException1);
                    }
                }

            } finally {

                try {
                    if(conexion != null){
                        conexion.close();
                    }
                } catch (SQLException sqlException){
                    System.out.println(sqlException);
                }

            }

        }
    }

