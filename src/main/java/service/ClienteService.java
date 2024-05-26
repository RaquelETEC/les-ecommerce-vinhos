package service;

import java.util.ArrayList;

import dao.DaoCliente;
import model.entity.BandeiraCartao;
import model.entity.CartaoDeCredito;
import model.entity.Cliente;
import model.entity.Endereco;
//import util.PasswordUtil;
import util.PasswordUtil;

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

			byte[] senhaCriptografada = PasswordUtil.criptografarSenha(cliente.getSenha());  //encriptografa a senha string 			
			cliente.setSenha(new String(senhaCriptografada)); //converte a senha criptografada para uma string

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
		return daoCliente.deletarCliente(cliente);
	}

	public String alterarSenha(Cliente cliente, String senhaAtual, String novaSenha, String repitaSenha) {
	    try {
	    	
			System.out.println("cheguei no alterar senha SERVICE");

	        // Verificar se a nova senha é forte
	        String validationString = PasswordUtil.verificarSenhaForte(novaSenha, repitaSenha);
	        
	        if (!validationString.isEmpty()) {
	            return validationString; // Retorna a mensagem de validação se a nova senha não for forte
	        }
	        
	        System.out.println("A SENHA CADASTRADA : " + cliente.getSenha());
	        
	        if (!PasswordUtil.verificarSenha(senhaAtual, cliente.getSenha())) {
	            return "A senha atual digitada está incorreta.";
	        }
	        
	        // Criptografar a nova senha
	        byte[] novaSenhaCriptografada = PasswordUtil.criptografarSenha(novaSenha);
	        cliente.setSenha(new String(novaSenhaCriptografada));

	        // Alterar a senha no banco de dados
	        String resultado = daoCliente.AlterarSenha(cliente);
	        
	        return resultado;
	    
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Erro ao inesperado ao alterar senha : " + e.getMessage();
	    }
	}
	
}
