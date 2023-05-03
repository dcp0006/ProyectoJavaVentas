package Ventas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
public void eliminarProducto(String tabla) throws SQLException {
	try {
		//Iniciar conexion con mysql
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/"+tabla;
		String user="root";
		String contraseña="";
		Connection connection=DriverManager.getConnection(url,user,contraseña);
		String consulta;
		
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static void registroUsuario(String usuario,String password) throws SQLException {
	//Iniciar conexion con mysql
	try {
		 // Cargar el controlador JDBC
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establecer la conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/prueba";
        String user = "root";
        String contraseña = "";
        Connection conexion = DriverManager.getConnection(url, user, contraseña);
        
        //mete los valores en base de datos
	String consulta="insert into usuarios values('"+usuario+"','"+password+"')";
	Statement statement=conexion.createStatement();
	statement.executeUpdate(consulta);
	} catch (Exception e) {
		System.out.println("Usuario duplicado");
	}
}

//compara los datos de la base e inicia sesion
public static boolean loginUsuario(String usuario,String password) {
	try {
		 // Cargar el controlador JDBC
       Class.forName("com.mysql.cj.jdbc.Driver");

       // Establecer la conexión a la base de datos
       String url = "jdbc:mysql://localhost:3306/prueba";
       String user = "root";
       String contraseña = "";
       Connection conexion = DriverManager.getConnection(url, user, contraseña);
       
       //mete los valores en base de datos
	String consulta="select * from usuarios where id_usuario='"+usuario+"' and contraseña='"+password+"'";
	Statement statement=conexion.createStatement();
	statement.executeQuery(consulta);
	ResultSet resultSet=statement.executeQuery(consulta);
	String user1 = null;
	String password1 = null;
	
	//Recupera la informacion
	while(resultSet.next()) {
		user1=resultSet.getString("id_usuario");
		password1=resultSet.getString("contraseña");
		return true;
	}
	} catch (Exception e) {
		e.printStackTrace();
		
	}
	return false;
}

}
