package jdbc;

import java.sql.*;

public class MyJDBC02 {
    public static void main(String[] args) {
        Connection conexion = null;

        try{

            conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc", "root", "root");
            conexion.setAutoCommit(false);

            Statement statement = conexion.createStatement();

            PreparedStatement consultaPreparada = conexion.prepareStatement("INSERT INTO estudiante (dni, nombre, apellido) VALUES (?, ?, ?);");
            consultaPreparada.setInt(1,43323977);
            consultaPreparada.setString(2,"Nicolas");
            consultaPreparada.setString(3,"Foster");

            consultaPreparada.executeUpdate();
            conexion.commit();

            System.out.println("id nombre apellido");
            ResultSet mostrarRegistros = statement.executeQuery("SELECT * FROM estudiante;");
            while(mostrarRegistros.next()){
                System.out.println(mostrarRegistros.getString(1) + " " + mostrarRegistros.getString(3) + " " + mostrarRegistros.getString(4));
            }

        } catch (SQLException sqlException){

            System.out.println(sqlException);
            if(conexion != null){
                try{
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
