package classaDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import classes.Cliente;

import views.ConexaoBanco;

public class ClienteDao {
	private static  ConexaoBanco conexao;
	
	public void insertDados(Cliente cliente,ConexaoBanco con) {
    	String insert ="insert into cliente(nome,cpf,endereco,data_nascimento,telefone) values(?,?,?,?,?)";
    	
    	try(Connection conexao = con.conectar();
    		PreparedStatement pstmt = conexao.prepareStatement(insert)) {
    		 pstmt.setString(1, cliente.getNome());
             pstmt.setString(2, cliente.getCpf());
             pstmt.setString(3, cliente.getEndereco());
             pstmt.setDate(4, Date.valueOf(cliente.getDtNascimento()));
             pstmt.setString(5, cliente.getTelefone());
         

             // Executando o comando SQL
             pstmt.executeUpdate();
             System.out.println("Funcionário inserido com sucesso!");
			
		} catch (SQLException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}
    }
	public void alteraTabela(String coluna,String valor,String cpf,ConexaoBanco con) {
    	String altera ="update cliente set " + coluna +" = ? where cpf = ? ";
    	
    	try(Connection conexao = con.conectar();
        	PreparedStatement pstmt = conexao.prepareStatement(altera)) {
			pstmt.setString(1, valor);
			pstmt.setString(2, cpf);
			pstmt.executeUpdate();
			 System.out.println("Alteração realizada com sucesso!");
		    
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
    }
	public static ConexaoBanco  getConexao() {
		return conexao;
	}

	public static void setConexao(ConexaoBanco conexao) {
		ClienteDao.conexao = conexao;
	}
}
