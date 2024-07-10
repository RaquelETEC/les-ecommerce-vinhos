package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import dao.DaoCupons;
import model.entity.Cliente;
import model.entity.Cupons;
import model.entity.Notificacoes;

public class CupomService {
	private DaoCupons daoCupom;

	String tituloNotificao = "";
	String descricaoNotificacao = "";
	int idCupom;

	public CupomService() {
		this.daoCupom = new DaoCupons();
	}

	public ArrayList<Cupons> listarCupom(Cliente cliente) {
		return daoCupom.ListarCupons(cliente);
	}

	public String GerarCupom(String tipoCupom, Double valorCupom, int idPedido, int idProduto) {
		if (tipoCupom == null || tipoCupom.isEmpty()) {
			return "ERRO: TIPO CUPOM É OBRIGATÓRIO!";
		} else if (valorCupom == null || valorCupom <= 0) {
			return "ERRO: VALOR DO CUPOM É OBRIGATÓRIO!";
		} else if (tipoCupom.equals("T") && (idPedido == 0)) {
			return "ERRO: PARA CUPONS DE TROCA É OBRIGATÓRIO O ID DO PEDIDO";
		} else {
			String codigo;
			String descricao;
			String img;
			Date validade;

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dataAtual = new Date();

			if (tipoCupom.equals("T") && idProduto == 0) {
				codigo = "CANCELADO#" + idPedido;
				descricao = "Desconto de R$" + valorCupom + ", pelo cancelamento do pedido " + idPedido;
				img = "trocaCupom.png";
				validade = adicionarMeses(dataAtual, 12);

				tituloNotificao = "PEDIDO " + idPedido + " CENCELADO ❌";
				descricaoNotificacao = "Seu cupom e desconto no valor de R$" + valorCupom
						+ ", já esta disponivel para uso. Válido até:" + validade;

			} else if (tipoCupom.equals("T")) {
				codigo = "TROCA#" + idPedido;
				descricao = "Desconto de R$" + valorCupom + " pela troca do item " + idProduto + " no pedido "
						+ idPedido;
				img = "trocaCupom.png";
				validade = adicionarMeses(dataAtual, 12);

				tituloNotificao = "PEDIDO " + idPedido + " TROCADO 🔄";
				descricaoNotificacao = "Seu cupom e desconto no valor de R$" + valorCupom
						+ ", já esta disponivel para uso. Válido até:" + validade;

			} else if (tipoCupom.equals("SALDO")) {
				codigo = "DESCONTO#" + idPedido;
				descricao = "Desconto de R$" + valorCupom + " pela saldo restante do pedido" + idPedido;
				img = "descontoSaldoCupom.png";
				validade = adicionarMeses(dataAtual, 12);

				tituloNotificao = "CUPOM DE SALDO GERADO! 💲";
				descricaoNotificacao = "Seu cupom com o saldo restante do pedido " + idPedido + ", no valor de R$"
						+ valorCupom + ", já esta disponivel para uso. Válido até:" + validade;
				tipoCupom = "T";
			} else {
				codigo = "PROMO#";
				descricao = "Cupom promocional de desconto de R$" + valorCupom;
				img = "trocaCupom.png";
				validade = adicionarMeses(dataAtual, 6);
			}

			// Retornando o resultado
			String retorno = daoCupom.gerarCupom(codigo, descricao, img, tipoCupom, valorCupom, validade, 0);

			if (!retorno.contains("erro")) {
				// idCupom = Integer.parseInt(retorno);
			}
			return retorno;

		}
	}

	// Função para adicionar meses a uma data
	private Date adicionarMeses(Date data, int meses) {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		cal.setTime(data);
		cal.add(java.util.Calendar.MONTH, meses);
		return cal.getTime();
	}

	public String vincularCupomAoCliente(Cupons cupom, Cliente cliente) {
		if (cupom == null || cliente == null) {
			return "Cupom ou cliente não fornecido";
		} else {
			String retorno = daoCupom.vincularCupomAoCliente(cupom, cliente);

			if (retorno.contains("success") && cupom.getId() == idCupom) {

				// Notificacoes notificacao = new Notificacoes();
				// notificacao.setData(new Date());
				// notificacao.setTitulo(tituloNotificao);
				// notificacao.setDescricao(descricaoNotificacao);
				// notificacao.setCliente(cliente);

				// NotificacaoService.gerarNotificacao(notificacao);

			}

			return retorno;
		}
	}

}
