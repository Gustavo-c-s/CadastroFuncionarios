package views;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoBanco {
        String url = "jdbc:postgresql://localhost:5432/CadastroFuncionario";
        String usuario = "postgres"; // Substitua pelo seu nome de usuário do PostgreSQL
        String senha = "Senha"; // Substitua pela sua senha    
    
    private Connection conectar()throws SQLException {
        return DriverManager.getConnection(url, usuario, senha);
    }       
    public boolean tabelaExiste(String nomeTabela) {
    	String existe = "SELECT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = ?)";
    	
    	try (Connection con = conectar();
    		PreparedStatement pstmt = con.prepareStatement(existe)){
			
    		pstmt.setString(1, nomeTabela.toLowerCase());
    		ResultSet resultado = pstmt.executeQuery();
    		if(resultado.next()) {
    			return resultado.getBoolean(1);
    		}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}return false;
    	
    }
    public void criarTabela(String nomeTabela) {
    	String criaTabela ="";
    	
    	if(nomeTabela.equalsIgnoreCase("funcionario")) {
    		criaTabela = "CREATE TABLE funcionario ("
                    + "codigo SERIAL PRIMARY KEY, "
                    + "nome VARCHAR(100), "
                    + "cpf VARCHAR(14), "
                    + "salario DECIMAL)";
    	}else {
    		return;
    	}
    	
    	try (Connection conn = conectar();
             Statement stm = conn.createStatement()){
			
    		stm.executeUpdate(criaTabela);
    		System.out.println("Tabela '"+nomeTabela+"' criada com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
    }  
    public void verificaOuCriaTabela(String nomeTabela) {
    	
    	if(!tabelaExiste(nomeTabela)) {
    		criarTabela(nomeTabela);
    	}else {
    		System.out.println("conectando com a tabela '" + nomeTabela + "'");
    	}
    }
    public void alteraTabela(String nomeTabela,String coluna,String valor,String cpf) {
    	String altera ="update "+nomeTabela.toLowerCase() +
    					" set " + coluna+" = ? where cpf = ?";
    	try(Connection conn = conectar();
        	PreparedStatement pstmt = conn.prepareStatement(altera)) {
			pstmt.setString(1, valor);
			pstmt.setString(2, cpf);
			
			int linhasAfetadas = pstmt.executeUpdate();
			 if (linhasAfetadas > 0) {
		         System.out.println("Alteração realizada com sucesso!");
		     } else {
		         System.out.println("Nenhum funcionário encontrado com o CPF especificado.");
		     }
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    public void insertDados(Funcionario funcionario) {
    	String insert ="insert into funcionario(nome,cpf,salario) values(?,?,?)";
    	
    	try(Connection conn = conectar();
    		PreparedStatement pstmt = conn.prepareStatement(insert)) {
    		 pstmt.setString(1, funcionario.getNome());
             pstmt.setString(2, funcionario.getCpf());
             pstmt.setDouble(3, funcionario.getSalario());

             // Executando o comando SQL
             pstmt.executeUpdate();
             System.out.println("Funcionário inserido com sucesso!");
			
		} catch (SQLException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}
    }
    public void consultaDados(String nomeTabela) {
    	String select = "select * from "+nomeTabela.toLowerCase();
    	
    	try (Connection conn = conectar();
    	     PreparedStatement pstmt = conn.prepareStatement(select)){
			
			ResultSet resultado = pstmt.executeQuery();
			while ( resultado.next()) {
				System.out.println("codigo: " + resultado.getInt("codigo")+
								"\nNome: " + resultado.getString("nome")+
								"\nCpf: " + resultado.getString("cpf")+
								"\nSalraio: R$"+resultado.getDouble("salario"));
			}
		} catch (SQLException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}
    }  
    public boolean verificarCPF(String cpf) {
    	String verifi = "select * from funcionario where cpf = ?";
    	
    	try (Connection conn = conectar();
       	     PreparedStatement pstmt = conn.prepareStatement(verifi)){
    		
    		pstmt.setString(1, cpf);
   			ResultSet resultado = pstmt.executeQuery();
   			if(resultado.next()) {
   				System.out.println("Nome: " + resultado.getString("nome")+
   								"\nCpf: " + resultado.getString("cpf")+
   								"\nSalraio: R$"+resultado.getDouble("salario"));
   			return resultado.getBoolean(1);
   			}else {
   				System.out.println("Nenhum funcionário encontrado com o CPF: " + cpf);
   			}
   		} catch (SQLException e) {
   			// TODO Bloco catch gerado automaticamente
   			e.printStackTrace();
   		}return false;
    }
}
//    static void criarTabela(Statement stm) {
//    	String create ="create table funcionario ( codigo serial primary key,nome varchar(100) not null,cpf varchar(14)not null unique,salario decimal not null)";
//    	
//    	try {
//			stm.executeUpdate(create);
//		} catch (SQLException e) {
//			// TODO Bloco catch gerado automaticamente
//			System.out.println("Erro ao criar tabela!");
//			e.printStackTrace();
//		}
//    }
//    
// try {
//            // Registrar o driver PostgreSQL (opcional para versões mais recentes do Java)
//            Class.forName("org.postgresql.Driver");
//
//            // Tentar estabelecer a conexão
//            Connection conexao = DriverManager.getConnection(url, usuario, senha);
//            System.out.println("Conexão bem-sucedida!");
//
//            Statement stm = conexao.createStatement();
//            criarTabela(stm);
//            insertDados(stm);            
//            consultaDados(stm);
//            conexao.close();
//        } catch (SQLException e) {
//            System.out.println("Falha na conexão!");
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            System.out.println("Driver não encontrado!");
//            e.printStackTrace();
//        }
//    }