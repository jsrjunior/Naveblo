package cod;
import java.sql.SQLException;

import javax.swing.JFrame;


public class ContainerDeJanelas extends JFrame {
	public ContainerDeJanelas() throws SQLException{
		
		add(new Fase());
		setTitle("Naveblo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1024,720);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	public static void main(String[] args) throws SQLException {
		new ContainerDeJanelas();
	}
}
