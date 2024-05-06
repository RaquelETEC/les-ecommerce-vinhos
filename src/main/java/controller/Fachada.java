/*package controller;

import java.util.List;

import dao.ClienteDAO;
import dao.IDAO;
import dominio.Cliente;
import dominio.EntidadeDominio;

public class Fachada implements IFachada {

	public String salvar(EntidadeDominio entidade) {
		Cliente cliente = (Cliente)entidade;
		StringBuilder sb = new StringBuilder();
		
				
		validaNull(sb, cliente.validarDados());
		validaNull(sb, cliente.validarDados());
		validaNull(sb, cliente.validarCPF());
		validaNull(sb, cliente.validarCredito());
		validaNull(sb, cliente.validarDependentes());
		
	//	validaNull(sb, validarExistencia());
		
		if(sb.length()==0){
			cliente.complementarDtCadastro();
			IDAO dao = new ClienteDAO();
			dao.salvar(cliente);
			return "CLIENTE SALVO COM SUCESSO!";
		}		
		
		return sb.toString();
	}

	public String alterar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	public String excluir(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void validaNull(StringBuilder sb, String msg){
		if(msg != null){
			sb.append(msg);
		}
	}

}
*/