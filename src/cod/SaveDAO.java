package cod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.ResultSet;

public class SaveDAO {
	
	 private Connection connection;
     public SaveDAO() throws SQLException {
	 this.connection = ConnectionFactory.getConnection();
     }
     
     public void salva(Save save) throws SQLException {
         // prepared statement para inserçã;
    	 String sql = "insert into save (nome,score) values (?,?)";
    	 PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sql);
    	 stmt.setString(1, save.getNome());
    	 stmt.setInt(2, save.getScore());
    	  stmt.execute();
          stmt.close();
          System.out.println("Salvo");
     }
     
     public List getlista() throws SQLException{
         PreparedStatement stmt = (PreparedStatement) this.connection.prepareStatement("select * from save order by score DESC");
         ResultSet rs = (ResultSet) stmt.executeQuery();
         List<Save> saves = new ArrayList<>();
         while (rs.next()){
        	 Save save = new Save();
        	 save.setNome(rs.getString("nome"));
        	 save.setScore(rs.getInt("score"));
        	 saves.add(save);
         }         
         return saves;
     }


}
