package classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import views.ConexaoBanco;

public class Produto {
	private int idProduto;	
	private String nome;
	private double vlUnitario;
	private String categoria;
	
	
	
	public Produto(int idProduto, String nome, double vlUnitario, String categoria) {
		
		this.idProduto = idProduto;
		this.nome = nome;
		this.vlUnitario = vlUnitario;
		this.categoria = categoria;
	}
	public static int verificarProduto(int id,ConexaoBanco con) {
    	String verifi = "select * from produto where idproduto = ?";
    	
    	try (Connection conexao = con.conectar();
       	     PreparedStatement pstmt = conexao.prepareStatement(verifi)){
    		
    		pstmt.setInt(1, id);
   			ResultSet resultado = pstmt.executeQuery();
   			
   			if(resultado.next()) {
   				System.out.println("Nome: " + resultado.getString("nome")+
   								"\nValor unitario: R$"+ resultado.getString("vlunitario"));
   			return resultado.getInt("idproduto");
   			}else {
   				System.out.println("Nenhum produto encontrado com o id: " + id);
   			}
   		} catch (SQLException e) {
   			// TODO Bloco catch gerado automaticamente
   			e.printStackTrace();
   		}return 0;
    }
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getVlUnitario() {
		return vlUnitario;
	}
	public void setVlUnitario(double vlUnitario) {
		this.vlUnitario = vlUnitario;
	}
	public Produto() {
		super();
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
}
