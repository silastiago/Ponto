package pcrn.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private String url = "jdbc:postgresql://localhost:5432/ponto";
	private String usuario = "postgres";
	private String senha = "postgres";
	String driver = "org.postgresql.Driver";
	Connection conexao;

/** Metodo getConnection retorna uma conexao
 *
 * @return Connection retorna uma conexao
 */
	public Connection getConnection(){
		System.out.println("Iniciando conexao ");
		try{
			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
			conexao = DriverManager.getConnection(url, usuario, senha);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			return conexao;
		}

	/**	Metodo fecharConexao como o proprio nome já diz, ele fecha uma conexao após o termino de uma conexao.
	 *
	 */
	public void fecharConexao(){
		try {
			conexao.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
