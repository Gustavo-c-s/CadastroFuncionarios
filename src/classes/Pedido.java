package classes;

import java.time.LocalDate;

public class Pedido extends Produto{
	private int idPedido;
	private LocalDate dtEmissao;
	private int idCLiente;
	private double vlTotal;
	
	public LocalDate getDtEmissao() {
		return dtEmissao;
	}
	public void setDtEmissao(LocalDate dtEmissao) {
		this.dtEmissao = dtEmissao;
	}
	public int getIdCLiente() {
		return idCLiente;
	}
	public void setIdCLiente(int idCLiente) {
		this.idCLiente = idCLiente;
	}
	public double getVlTotal() {
		return vlTotal;
	}
	public void setVlTotal(PedidoItem pI) {
		this.vlTotal = pI.getVlUnitario()*pI.getQuantidade();
	}
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	
}
