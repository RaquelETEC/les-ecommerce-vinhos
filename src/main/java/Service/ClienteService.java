package Service;

import model.entity.Cliente;
import model.entity.Endereco;
import Dao.DaoCliente;

public class ClienteService {

	private DaoCliente daoCliente;

	
	EnderecoService enderecoService = new EnderecoService();
	public ClienteService() {
		this.daoCliente = new DaoCliente();
	}

	public String adicionarCliente(Cliente cliente, Endereco enderecoR, Endereco enderecoE, Endereco enderecoC) {

		System.out.println("Cheguei no adiconarCliente Service");
		int id_cliente = daoCliente.inserirCliente(cliente);
		cliente.setId(id_cliente);
		System.out.println("cliente cadastrado com sucesso: " + cliente.getNome() + "ID: " + cliente.getId());


		enderecoR.setCliente(cliente);
		enderecoE.setCliente(cliente);
		enderecoC.setCliente(cliente);
		
		 String responseEndeR = enderecoService.adicionarEndereco(cliente, enderecoR);
		 String responseEndeE = enderecoService.adicionarEndereco(cliente, enderecoE);
		 String responseEndeC = enderecoService.adicionarEndereco(cliente, enderecoC);
		 
		return "OK";
	}

}
