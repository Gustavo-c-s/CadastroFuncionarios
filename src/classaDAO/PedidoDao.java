package classaDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import classes.Pedido;
import views.ConexaoBanco;

public class PedidoDao {
	private ConexaoBanco conexao;

	public ConexaoBanco getConexao() {
		return conexao;
	}

	public void setConexao(ConexaoBanco conexao) {
		this.conexao = conexao;
	}
	
	public void insertDados(Pedido pedido,ConexaoBanco con) {
		String insert ="insert into pedido (vltotal,idcliente) values ( ?,?)";
		try(Connection conexao = con.conectar();
	    		PreparedStatement pstmt = conexao.prepareStatement(insert)) {
				pstmt.setDouble(1, pedido.getVlTotal());
	            pstmt.setInt(2, pedido.getIdCLiente());
	             // Executando o comando SQL
	            pstmt.executeUpdate();
	            System.out.println("Funcionário inserido com sucesso!");
				
			} catch (SQLException e) {
				// TODO Bloco catch gerado automaticamente
				e.printStackTrace();
			}
	    }
	
	
}
