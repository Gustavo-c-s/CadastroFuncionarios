package classaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import views.ConexaoBanco;

public class CreateDao {
	
	public boolean tabelaExiste(String nomeTabela,ConexaoBanco con) {
    	String existe = "SELECT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = ?)";
    	
    	try (Connection conexao = con.conectar();
    		PreparedStatement pstmt =  conexao.prepareStatement(existe)){
			
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
    public void criarTabela(String nomeTabela,ConexaoBanco con) {
    	String criaTabela ="";
    	
    	if(nomeTabela.equalsIgnoreCase("funcionario")) {
    		criaTabela = "CREATE TABLE funcionario ("
    				+ "idcliente SERIAL PRIMARY KEY, "
                    + "nome VARCHAR(100) not null, "
                    + "cpf VARCHAR(14)not null, "
                    + "endereco varchar(100) not null,"
                    + "data_nascimento date not null,"
                    + "telefone varchar(14),"
                    + "salario DECIMAL(10,2));";
    	}else if(nomeTabela.equalsIgnoreCase("cliente")) {
    		criaTabela = "CREATE TABLE cliente ("
                    + "idcliente SERIAL PRIMARY KEY, "
                    + "nome VARCHAR(100) not null, "
                    + "cpf VARCHAR(14)not null unique, "
                    + "endereco varchar(100) not null,"
                    + "data_nascimento date not null,"
                    + "telefone varchar(14));";
    	}else if(nomeTabela.equalsIgnoreCase("produto")) {
    		criaTabela ="create table produto("
    				+ "idproduto serial primary key,"
    				+ "nome varchar(100) not null,"
    				+ "vlunitario decimal(10,2) not null check(vlunitario>0),"
    				+ "categoria varchar(100));";
    	}else if(nomeTabela.equalsIgnoreCase("pedido")) {
    		criaTabela ="create table pedido("
    				+ "idpedido serial primary key,"
    				+ "dtemissao timestamp default current_timestamp,"
    				+ "vltotal decimal(10,2) not null,"
    				+ "idcliete int references cliente(idcliente);";
    	}else if(nomeTabela.equalsIgnoreCase("pedidoitem")) {
    		criaTabela ="create table pedidoitem("
    				+ "idpedido int references pedido (idpedido),"
    				+ "idproduto int references produto(idproduto),"
    				+ "quantidade int check (quantidade>0) not null,"
    				+ "vlunitario decimal(10,2) not null check(vlunitario>0)"
    				+ "primary key(idpedido,idproduto));";
    	}
    	else {
    		return;
    	}
    	
    	try (Connection conexao = con.conectar();
                Statement stm = conexao.createStatement()) {
               
               stm.executeUpdate(criaTabela);
               System.out.println("Tabela '" + nomeTabela + "' criada com sucesso!");

               // Inserir dados iniciais
               inserirDadosIniciais(nomeTabela, con);

           } catch (Exception e) {
               e.printStackTrace();
           }
    }  
    public void verificaOuCriaTabela(String nomeTabela,ConexaoBanco conexao) {
    	
    	if(!tabelaExiste(nomeTabela,conexao)) {
    		criarTabela(nomeTabela, conexao);
    	}else {
    		System.out.println("conectando com a tabela '" + nomeTabela + "'");
    	}
    }
    public boolean verificarCPF(String cpf,String nomeTabela,ConexaoBanco con) {
    	String verifi = "select * from "+nomeTabela+" where cpf = ?";
    	
    	try (Connection conexao = con.conectar();
       	     PreparedStatement pstmt = conexao.prepareStatement(verifi)){
    		
    		pstmt.setString(1, cpf);
   			ResultSet resultado = pstmt.executeQuery();
   			
   			if(resultado.next()) {
   				System.out.println("Nome: " + resultado.getString("nome")+
   								"\nCpf: " + resultado.getString("cpf"));
   			return true;
   			}else {
   				System.out.println("Nenhuma resultado encontrado com o CPF: " + cpf);
   			}
   		} catch (SQLException e) {
   			// TODO Bloco catch gerado automaticamente
   			e.printStackTrace();
   		}return false;
    }
    public void alteraTabela(String nomeTabela,String coluna,String valor,String cpf,ConexaoBanco con) {
    	String altera ="update "+nomeTabela+" set " + coluna +" = ? where cpf = ? ";
    	
    	try(Connection conexao = con.conectar();
        	PreparedStatement pstmt =  conexao.prepareStatement(altera)) {
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
    public void consultaTabela(String tabela,ConexaoBanco conexao) {
		String select =" select * from "+tabela.toLowerCase();
		
		try (Connection con = conexao.conectar();
				PreparedStatement pstmt = con.prepareStatement(select);
					ResultSet resultado=pstmt.executeQuery()){
			
			ResultSetMetaData metaData = resultado.getMetaData();
			int coluna =metaData.getColumnCount();
			while(resultado.next()) {
				for(int i =1;i<=coluna;i++) {
					System.out.println(metaData.getColumnName(i)+": "+resultado.getString(i)+".");
				}System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
    public void inserirDadosIniciais(String nomeTabela, ConexaoBanco conexao) {
        String inserirDados = "";
        
        if (nomeTabela.equalsIgnoreCase("funcionario")) {
            inserirDados = "INSERT INTO funcionario (nome, cpf, endereco, data_nascimento, telefone, salario) VALUES "
                    + "('João Silva', '12345678900', 'Rua A, 123', '1980-05-15', '123456789', 3000.00), "
                    + "('Maria Oliveira', '98765432100', 'Rua B, 456', '1990-11-20', '987654321', 3500.00), "
                    + "('Pedro Santos', '11122334466', 'Rua C, 789', '1985-02-28', '222233344', 3200.00), "
                    + "('Lucas Almeida', '44455667788', 'Rua D, 101', '1992-07-14', '333344455', 2900.00), "
                    + "('Juliana Costa', '55566778800', 'Rua E, 202', '1988-10-10', '444455566', 3300.00), "
                    + "('Ana Lima', '66677889911', 'Rua F, 303', '1982-12-25', '555566677', 3100.00), "
                    + "('Marcos Silva', '77788990022', 'Rua G, 404', '1995-06-18', '666677788', 3400.00), "
                    + "('Renata Martins', '88899001133', 'Rua H, 505', '1978-04-12', '777788899', 3500.00), "
                    + "('José Pereira', '99900112244', 'Rua I, 606', '1991-09-21', '888899900', 2800.00), "
                    + "('Fernanda Rocha', '00011223355', 'Rua J, 707', '1987-01-15', '999900011', 3600.00);";
        } else if (nomeTabela.equalsIgnoreCase("cliente")) {
            inserirDados = "INSERT INTO cliente (nome, cpf, endereco, data_nascimento, telefone) VALUES "
                    + "('Carlos Santos', '11122334455', 'Avenida C, 789', '1975-03-10', '555555555'), "
                    + "('Ana Costa', '55566778899', 'Avenida D, 101', '1985-07-22', '666666666'), "
                    + "('Paula Lima', '22233445566', 'Rua K, 808', '1990-08-09', '777777777'), "
                    + "('Ricardo Martins', '33344556677', 'Rua L, 909', '1982-11-11', '888888888'), "
                    + "('Tatiane Almeida', '44455667788', 'Rua M, 010', '1988-05-20', '999999999'), "
                    + "('Fabio Oliveira', '55566778890', 'Rua N, 121', '1991-01-15', '000000000'), "
                    + "('Camila Pereira', '66677889900', 'Rua O, 232', '1994-09-23', '111111111'), "
                    + "('Lucas Fernandes', '77788990011', 'Rua P, 343', '1986-02-28', '222222222'), "
                    + "('Fernanda Costa', '88899001122', 'Rua Q, 454', '1993-12-10', '333333333'), "
                    + "('Roberto Silva', '99900112233', 'Rua R, 565', '1979-06-18', '444444444');";
        } else if (nomeTabela.equalsIgnoreCase("produto")) {
            inserirDados = "INSERT INTO produto (nome, vlunitario, categoria) VALUES "
                    + "('Arroz', 17.00, 'Comida'), "
                    + "('Biscoito', 2.99, 'Comida'), "
                    + "('Tv', 1500.00, 'Eletronico'), "
                    + "('Feijao', 10.00, 'Comida'), "
                    + "('Camisa', 30.00, 'Roupa'), "
                    + "('Cueca', 12.50, 'Roupa'), "
                    + "('Telefone', 890.00, 'Eletronico'), "
                    + "('Frango', 22.50, 'Comida'), "
                    + "('Caneta', 1.99, 'material'), "
                    + "('Meia', 5.00, 'Roupa');";
        } else if (nomeTabela.equalsIgnoreCase("pedido")) {
            // Não inserir dados na tabela pedido se não houver necessidade inicial.
            return;
        } else if (nomeTabela.equalsIgnoreCase("pedidoitem")) {
            // Não inserir dados na tabela pedidoitem se não houver necessidade inicial.
            return;
        }

        try (Connection con = conexao.conectar();
             Statement stm = con.createStatement()) {
            stm.executeUpdate(inserirDados);
            System.out.println("Dados iniciais inseridos na tabela '" + nomeTabela + "'!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void buscarNome(String nomeTabela,Object idT, ConexaoBanco conexao) {
    	String select="";
    	if(idT instanceof Integer) {
    		select="select * from "+nomeTabela+" where idcliente = ? ";
    		
    	}else if( idT instanceof String) {
    		select="select * from "+nomeTabela+"where nome = ? ";
    	}try (Connection con = conexao.conectar();
				PreparedStatement pstmt = con.prepareStatement(select)){
				pstmt.setObject(1, idT);	
    			ResultSet resultado=pstmt.executeQuery();
			
			ResultSetMetaData metaData = resultado.getMetaData();
			int coluna =metaData.getColumnCount();
			while(resultado.next()) {
				for(int i =1;i<=coluna;i++) {
					System.out.println(metaData.getColumnName(i)+": "+resultado.getString(i)+".");
				}System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }

}
