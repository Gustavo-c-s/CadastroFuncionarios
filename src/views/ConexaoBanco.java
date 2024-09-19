package views;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.JobImpressions;

import classes.Funcionario;

public class ConexaoBanco {
        String url = "jdbc:postgresql://localhost:5432/CadastroFuncionario";
        String usuario = "postgres"; 
        String senha = "135790";     
        Connection con;
        private Statement statment;
    public Connection conectar(){
	System.out.println("Conectando ao bando.");
	    	
	    	try {
	    		con = DriverManager.getConnection(url,usuario,senha);
	    		if(con!=null) {
	    			System.out.println("Conectado!");
	    		}else {
	    			System.err.println("Não foi possível!");
	    		}
	    	}catch(SQLException e) {
	    		System.err.println("Driver não econtrado!");
	    		return null;
	    	}return con;
    }  

    public double pegaValor(String cpf) {
    	String valor = "select salario from funcionario where cpf = ? ";
    	
    	try (Connection conn = conectar();
          	PreparedStatement pstmt = conn.prepareStatement(valor)) {
			pstmt.setString(1,cpf);
			ResultSet resultado = pstmt.executeQuery();
			if(resultado.next()) {
			
				return resultado.getDouble("salario");	
			}
			 
		} catch (SQLException e) {
			
			
		}
		return 0;    	
    }
    public String qntasColunas(String nomeTabela){
    	String tabela = "select * from "+nomeTabela.toLowerCase();
    	String tabelaNova ="(";	
    	try (Connection con = conectar();
    			PreparedStatement pstmt = con.prepareStatement(tabela);
    			ResultSet resultado = pstmt.executeQuery()){
    		
			ResultSetMetaData metaData =resultado.getMetaData();
			int coluna = metaData.getColumnCount();
			
			if(resultado.next()) {
				  
				for(int j = 1;j<=coluna;j++) {
					if(j>1 && j<=coluna) {
						tabelaNova+="?";
						if(j>1 && j<coluna) {
							tabelaNova+=",";
						}
					}					
				}
				tabelaNova+=")";
//				System.out.println(tabelaNova);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return tabelaNova;
    }    
    public String sqlPronto(String nomeTabela) {
		String qntsColunas = qntasColunas(nomeTabela);
		String nomeColunas = addNamenasColunas(nomeTabela);
		String insert = "insert into " + nomeTabela.toLowerCase()+nomeColunas+" values "+ qntsColunas;
    	System.out.println(insert);
		return insert;
    }
    public String addNamenasColunas(String nomeTabela) {
    	String tabela = "select * from "+nomeTabela.toLowerCase();
    	String tabelaNova ="(";	
    	try (Connection con = conectar();
    			PreparedStatement pstmt = con.prepareStatement(tabela);
    			ResultSet resultado = pstmt.executeQuery()){
    		
			ResultSetMetaData metaData =resultado.getMetaData();
			int coluna = metaData.getColumnCount();
			
			if(resultado.next()) {
				  
				for(int j = 1;j<=coluna;j++) {
					if(j>1 && j<=coluna) {
						tabelaNova+=metaData.getColumnName(j);
						if(j>1 && j<coluna) {
							tabelaNova+=",";
						}
					}					
				}
				tabelaNova+=")";
//				System.out.println(tabelaNova);
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
    	return tabelaNova;
    }
    public void insertTabela(String nomeTabela,List<?>listaObjetos) {
    	String insert =sqlPronto(nomeTabela);
    	String qnt = addNamenasColunas(nomeTabela);
    	try(Connection conn = conectar();
    		PreparedStatement pstmt = conn.prepareStatement(insert)){
    		
    		ResultSet resultado = pstmt.executeQuery();
    		
    		
    		for (Object objeto : listaObjetos) {
                // Usa reflection para acessar os campos (atributos) da classe
                Field[] campos = objeto.getClass().getDeclaredFields();

                // Para cada campo, adiciona seu valor à PreparedStatement
                int index = 1;
                for (Field campo : campos) {
                    campo.setAccessible(true);  // Garante acesso aos campos privados

                    Object valorCampo = campo.get(objeto); // Obtém o valor do campo
                    
                    // Aqui você pode tratar o tipo do campo conforme necessário
                    if (valorCampo instanceof String) {
                        pstmt.setString(index, (String) valorCampo);
                    } else if (valorCampo instanceof Integer) {
                        pstmt.setInt(index, (Integer) valorCampo);
                    } else if (valorCampo instanceof Double) {
                        pstmt.setDouble(index, (Double) valorCampo);
                    } else if (valorCampo != null) {
                        pstmt.setObject(index, valorCampo); // Para outros tipos
                    } else {
                        pstmt.setNull(index, java.sql.Types.NULL);
                    }
                    index++;
                }

                // Executa o comando de inserção para o objeto atual
                pstmt.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }    	
    }
    public String addValues(String nomeTabela) {
    	String insert = "insert into " + addNamenasColunas(nomeTabela)
;    	
    	
    	
    	
    	return nomeTabela;
    }
	
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
	public Statement getStatment() {
		return statment;
	}
	public void setStatment(Statement statment) {
		this.statment = statment;
	}
	




} 



//    public void inserirDados(String nomeTabela ) {
//    	
//    	String insertDds = "insert into ? ("+j")values("+j+")";
//    	
//    	try (Connection con = conectar();
//    			PreparedStatement pstmt = con.prepareStatement(insertDds);
//    			ResultSet resultado = pstmt.executeQuery()){
//    		
//    		pstmt.setString(1, nomeTabela);
//			ResultSetMetaData metaData =resultado.getMetaData();
//			int coluna = metaData.getColumnCount();
//			
//			while(resultado.next()) {
//				
//				for (int i = 1;i<=coluna;i++) {
//					pstmt.setString(i,metaData.getColumnName(i));
//				}
//				for(int h = 1; h<=coluna; h++) {
//					pstmt.setObject(h,nomeTabela.getClass());
//				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//    
//    
//    
//    }
        

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
//    public List<String> pegarNomedasColunas(String nomeTabela) {
//    	List<String> nomeColunas = new ArrayList<>();
//    	
//    	// Variável de controle para garantir que o código execute pelo menos uma vez
//    	boolean hasRows = false;
//    	try {
//			String sql ="Select * from "+ nomeTabela.toLowerCase();
//			PreparedStatement stmt = con.prepareStatement(sql);
//			ResultSet rs = stmt.executeQuery();
//			ResultSetMetaData rsmd = rs.getMetaData();  // Obter os metadados do resultado
//			rs.next();
//			int qntColunas=rsmd.getColumnCount();
//			
//			if(rs.next()) {
//				hasRows = true;// Marca que há pelo menos uma linha
//                // Retorna o ponteiro para a primeira linha
//				rs.beforeFirst();
//			}
//			for(int i = 1; i<=qntColunas;i++) {
//				String nomeColuna = rsmd.getColumnName(i);
//				nomeColunas.add(nomeColuna);
//			}
//			if (!hasRows) {
//                // Código para quando não há linhas
//                System.out.println("Tabela está vazia ou não retornou nenhuma linha.");
//            }
//				
//		} catch (Exception e) {
//			e.printStackTrace();;
//		}
//    	
//    	return nomeColunas;	
//    }