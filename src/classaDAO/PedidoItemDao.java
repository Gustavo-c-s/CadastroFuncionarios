package classaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import classes.PedidoItem;
import views.ConexaoBanco;

public class PedidoItemDao {
	
	public void insertDados(ConexaoBanco con,PedidoItem pi) {
		
		String insert ="insert into pedido (idpedido,idproduto,quantidade,vlunitario) values ( ?,?,?,?)";
		try(Connection conexao = con.conectar();
	    		PreparedStatement pstmt = conexao.prepareStatement(insert)) {
				pstmt.setInt(1, pi.getIdPedido());
	            pstmt.setInt(2, pi.getIdProduto());
	            pstmt.setInt(3, pi.getQuantidade());
	            pstmt.setDouble(4, pi.getVlUnitario());
	             // Executando o comando SQL
	            pstmt.executeUpdate();
	            System.out.println("Funcionário inserido com sucesso!");
				
			} catch (SQLException e) {
				// TODO Bloco catch gerado automaticamente
				e.printStackTrace();
			}
	}
	    
}
