package classaDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import classes.Funcionario;
import views.ConexaoBanco;

public class FuncionarioDao {
	private static ConexaoBanco conexao;
	
	public void insertDados(Funcionario funcionario,ConexaoBanco con) {
    	String insert ="insert into funcionario(nome,cpf,endereco,data_nascimento,telefone,salario) values(?,?,?,?,?,?)";
    	
    	try(Connection conexao = con.conectar();
    		PreparedStatement pstmt = conexao.prepareStatement(insert)) {
    		 pstmt.setString(1, funcionario.getNome());
             pstmt.setString(2, funcionario.getCpf());
             pstmt.setString(3, funcionario.getEndereco());
             pstmt.setDate(4, Date.valueOf(funcionario.getDtNascimento()));
             pstmt.setString(5, funcionario.getTelefone());
             pstmt.setDouble(6, funcionario.getSalario());

             // Executando o comando SQL
             pstmt.executeUpdate();
             System.out.println("Funcionário inserido com sucesso!");
			
		} catch (SQLException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}
    }
	public void alteraTabela(String coluna,String valor,String cpf,ConexaoBanco con) {
    	String altera ="update funcionario set " + coluna +" = ? where cpf = ? ";
    	
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
    public void alteraSalario(double valor,String cpf,ConexaoBanco con) {
    	String altera ="update funcionario set salario = ? where cpf = ? ";
		try(Connection conexao = con.conectar();
	        	PreparedStatement pstmt = conexao.prepareStatement(altera)) {
			pstmt.setDouble(1, valor);
			pstmt.setString(2, cpf);
			pstmt.executeUpdate();
			
		    System.out.println("Alteração realizada com sucesso!");

		} catch (Exception e) {
			// TODO: handle exception
		}
    }
	public static ConexaoBanco getConexao() {
		return conexao;
	}
	public static void setConexao(ConexaoBanco conexao) {
		FuncionarioDao.conexao = conexao;
	}
	
	

}
