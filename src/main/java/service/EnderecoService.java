package service;

import model.entity.Cliente;
import model.entity.Endereco;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;
import dao.DaoEndereco;

public class EnderecoService {

	private DaoEndereco daoEndereco;

	public EnderecoService() {
		this.daoEndereco = new DaoEndereco();
	}

	public String adicionarEndereco(Cliente cliente, Endereco endereco) {
		System.out.println("adicionarEndereco Service");

		if (StringUtils.isAnyBlank(
			    endereco.getNome(),
			    endereco.getTipoResidencia(),
			    endereco.getTipoLogradouro(),
			    endereco.getLogradouro(),
			    endereco.getNumero(),
			    endereco.getBairro(),
			    endereco.getCep(),
			    endereco.getCidade(),
			    endereco.getEstado(),
			    endereco.getPais())) {
			    return "Falha: Todos os campos obrigatórios devem ser preenchidos";
			}
		// Chamada ao DAO para inserir o endere�o
		return daoEndereco.inserirEndereco(cliente, endereco);
	}

	public Endereco selecionarEndereco(Cliente cliente, Endereco endereco) {
		System.out.println("Cheguei no selecionarEndereco Service");
		// Chamada ao DAO para inserir o endere�o
		return daoEndereco.selecionarEndereco(cliente, endereco);
	}

	// Listar endereços do cliente
	public ArrayList<Endereco> listarEnderecos(Cliente cliente) {
		return daoEndereco.ListarEnderecos(cliente);
	}

	public String EditarEndereco(Cliente cliente, Endereco endereco) {

		return daoEndereco.EditarEndereco(cliente, endereco);
	}

	public String ExcluirEndereco(Cliente cliente, Endereco endereco) {
		// Valida��o dos dados se necess�rio
		System.out.println("Cheguei no adicionarEndereco Service");

		// Chamada ao DAO para inserir o endere�o
		return daoEndereco.ExcluirEndereco(cliente, endereco);
	}

	public ArrayList<Endereco> listarEnderecosEntrega(Cliente cliente) {
		return daoEndereco.ListarEnderecosEntrega(cliente);
	}
}
