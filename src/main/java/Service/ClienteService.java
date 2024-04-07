package Service;

import model.entity.Cliente;
import model.entity.Endereco;
import model.entity.CartaoDeCredito;

import java.util.ArrayList;

import Dao.DaoCliente;
import model.entity.BandeiraCartao;

public class ClienteService {

	private DaoCliente daoCliente;

	CartoesService cartoesService = new CartoesService();
	EnderecoService enderecoService = new EnderecoService();

	public ClienteService() {
		this.daoCliente = new DaoCliente();
	}

	public String adicionarCliente(Cliente cliente, Endereco enderecoR, Endereco enderecoE, Endereco enderecoC,
			CartaoDeCredito cartao, BandeiraCartao bandeira) {
		try {
			// Inserir o cliente
			int idCliente = daoCliente.inserirCliente(cliente);
			cliente.setId(idCliente);

			// Adicionar os endereços
			String responseEnderecoR = enderecoService.adicionarEndereco(cliente, enderecoR);
			String responseEnderecoE = enderecoService.adicionarEndereco(cliente, enderecoE);
			String responseEnderecoC = enderecoService.adicionarEndereco(cliente, enderecoC);

			// Adicionar o cartão de crédito
			String responseCartao = cartoesService.adicionarCartao(cliente, cartao, bandeira);

			// Verificar se todos os dados foram inseridos com sucesso
			if (responseEnderecoR.equals("OK") && responseEnderecoE.equals("OK") && responseEnderecoC.equals("OK")
					&& responseCartao.equals("OK")) {
				return "OK";
			} else {
				return "Erro ao adicionar cliente";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao adicionar cliente: " + e.getMessage();
		}
	}

	public ArrayList<Cliente> ListarCliente() {
		return daoCliente.ListarCliente();
	}

	public Cliente selecionarCliente(Cliente cliente) {

		return daoCliente.selecionarCliente(cliente);

	}

	public String alterarCliente(Cliente cliente) {

		return daoCliente.alterarCliente(cliente);
	}

	public String deletarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return daoCliente.deletarCliente(cliente);
	}

}
