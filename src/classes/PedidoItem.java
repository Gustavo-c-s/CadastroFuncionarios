package classes;

import java.util.ArrayList;
import java.util.List;

import classaDAO.CreateDao;
import classaDAO.PedidoItemDao;
import util.Ler;
import views.ConexaoBanco;

public class PedidoItem extends Produto {
	private int idPedido;
	private int quantidade;
	
	
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	

	public PedidoItem() {
		super();
		// TODO Stub de construtor gerado automaticamente
	}
	public PedidoItem(int idPedido, int idProduto,String nome ,int quantidade,double vlUnitario,String categoria) {
		
		this.idPedido = idPedido;
		
		this.quantidade = quantidade;
	}
	public void addPedido(ConexaoBanco con) {
		List<PedidoItem> pedidoItem = new ArrayList<PedidoItem>();
		PedidoItemDao piDao = new PedidoItemDao();
		CreateDao creDao = new CreateDao();
		Pedido pe = new Pedido();
		Cliente cli = new Cliente();
		creDao.verificaOuCriaTabela("produto", con);
		creDao.verificaOuCriaTabela("pedido", con);
		creDao.verificaOuCriaTabela("pedidoitem", con);
		String nome = Ler.verificarString("Informe o nome do cliente");
		creDao.buscarNome("cliente",nome,con);
		int idproduto =Ler.validarInt("Informe o id do produto");
		var idpr =verificarProduto(idproduto,con);
		int qnt = Ler.lerInt("Informe a quantidade");
		double valorTtPro = getVlUnitario()*qnt;
		//int idpedido = pe.getIdPedido();
		
	}
	public int getIdProduto() {
		// TODO Stub de método gerado automaticamente
		return 0;
	}

}
