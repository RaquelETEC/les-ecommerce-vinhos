package dto;
import model.entity.Cupons;

import java.util.List;

import model.entity.CartaoDeCredito;

//classe para mapear a estrutura do JSON que irei receber

public class PedidoVendaDTO {
	  private int idCliente;
	    private double totalPedido;
	    private double totalDesconto;
	    private double totalPagamento;
	    private double totalFrete;
	    private double totalSaldo;
	    private int idEndereco;
	    private List<CartaoDeCredito> cartoes;
	    private List<Cupons> cupons;
		
	    
		public int getIdCliente() {
			return idCliente;
		}


		public void setIdCliente(int idCliente) {
			this.idCliente = idCliente;
		}


		public double getTotalPedido() {
			return totalPedido;
		}


		public void setTotalPedido(double totalPedido) {
			this.totalPedido = totalPedido;
		}


		public double getTotalDesconto() {
			return totalDesconto;
		}


		public void setTotalDesconto(double totalDesconto) {
			this.totalDesconto = totalDesconto;
		}


		public double getTotalPagamento() {
			return totalPagamento;
		}


		public void setTotalPagamento(double totalPagamento) {
			this.totalPagamento = totalPagamento;
		}


		public double getTotalFrete() {
			return totalFrete;
		}


		public void setTotalFrete(double totalFrete) {
			this.totalFrete = totalFrete;
		}

		public Double getTotalSaldo() {
			return totalSaldo;
		}

		public void setTotalSaldo(Double totalSaldo) {
			this.totalSaldo = totalSaldo;
		}

		public int getIdEndereco() {
			return idEndereco;
		}


		public void setIdEndereco(int idEndereco) {
			this.idEndereco = idEndereco;
		}


		public List<CartaoDeCredito> getCartoes() {
			return cartoes;
		}


		public void setCartoes(List<CartaoDeCredito> cartoes) {
			this.cartoes = cartoes;
		}


		public List<Cupons> getCupons() {
			return cupons;
		}


		public void setCupons(List<Cupons> cupons) {
			this.cupons = cupons;
		}
	    
	    
	    
	    
}
