package Service;

import java.util.ArrayList;

import Dao.DaoCartoes;
import model.entity.CartaoDeCredito;
import model.entity.Cliente;
import model.entity.Endereco;
import model.entity.BandeiraCartao;

public class CartoesService {

	private DaoCartoes daoCartoes;

	public CartoesService() {
		this.daoCartoes = new DaoCartoes();
	}

	public String adicionarCartao(Cliente cliente, CartaoDeCredito cartao, BandeiraCartao bandeira) {
		// Valida��o dos dados se necess�rio
		System.out.println("Cheguei no adicionarCartao Service");

		// Chamada ao DAO para inserir o endere�o
		return daoCartoes.inserirCartao(cliente, cartao, bandeira);
	}

	public CartaoDeCredito selecionarCartao(Cliente cliente, CartaoDeCredito cartao, BandeiraCartao bandeira) {
		System.out.println("Cheguei no selecionarEndereco Service");

		// Chamada ao DAO para inserir o endere�o
		return daoCartoes.selecionarCartao(cliente, cartao, bandeira);
	}

	public ArrayList<CartaoDeCredito> listarCartoes(Cliente cliente) {
		return daoCartoes.ListarCartoes(cliente);
	}

	public CartaoDeCredito editarCartao(Cliente cliente, CartaoDeCredito cartao, BandeiraCartao bandeira) {

		return daoCartoes.EditarCartao(cliente, cartao, bandeira);
	}
}