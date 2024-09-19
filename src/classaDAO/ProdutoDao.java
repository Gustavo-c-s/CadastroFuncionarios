package classaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import views.ConexaoBanco;

public class ProdutoDao {
	
	public int addProduto(ConexaoBanco con) {
		String select ="select * from produto";
		try(Connection conexao = con.conectar();
	    		PreparedStatement pstmt = conexao.prepareStatement(select)) {
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				PedidoItem
			}
	}catch(SQLException e){
		
	}
		return 0;
}
	}
