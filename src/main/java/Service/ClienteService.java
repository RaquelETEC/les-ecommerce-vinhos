package Service;

import model.entity.Cliente;
import model.entity.Endereco;
import model.entity.CartaoDeCredito;
import Dao.DaoCliente;
import model.entity.BandeiraCartao;

public class ClienteService {

	private DaoCliente daoCliente;
	
	CartoesService cartoesService = new CartoesService();
	EnderecoService enderecoService = new EnderecoService();
	public ClienteService() {
		this.daoCliente = new DaoCliente();
	}

	public String adicionarCliente(Cliente cliente, Endereco enderecoR, Endereco enderecoE, Endereco enderecoC , CartaoDeCredito cartao, BandeiraCartao bandeira) {

		System.out.println("Cheguei no adiconarCliente Service");
		int id_cliente = daoCliente.inserirCliente(cliente);
		cliente.setId(id_cliente);
		System.out.println("cliente cadastrado com sucesso: " + cliente.getNome() + "ID: " + cliente.getId());

		
		enderecoR.setCliente(cliente);
		enderecoE.setCliente(cliente);
		enderecoC.setCliente(cliente);

		cartao.setCliente(cliente);
		
		 String responseEndeR = enderecoService.adicionarEndereco(cliente, enderecoR);
		 String responseEndeE = enderecoService.adicionarEndereco(cliente, enderecoE);
		 String responseEndeC = enderecoService.adicionarEndereco(cliente, enderecoC);
		 
		 String responsecartao = cartoesService.adicionarCartao(cliente, cartao, bandeira);
		 
		return "OK";
	} 

}
