package cod;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Phantom
 */
public class ConnectionFactory {
    public static Connection getConnection() throws SQLException {
		try { 
			Class.forName("com.mysql.jdbc.Driver");
		System.out.println("Conectando ao banco");
		return DriverManager.getConnection("jdbc:mysql://localhost/jogo", "root", "");
		} catch (ClassNotFoundException e){
                    
			throw new SQLException(e.getMessage()); 
			}
		}    
}
