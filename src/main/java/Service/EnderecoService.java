package Service;


import model.entity.Cliente;
import model.entity.Endereco;
import Dao.DaoEndereco;

public class EnderecoService {

	private DaoEndereco daoEndereco;

    public EnderecoService() {
    	this.daoEndereco = new DaoEndereco();
    }	

    public String adicionarEndereco(Cliente cliente, Endereco endereco) {
        // Validação dos dados se necessário
		System.out.println("Cheguei no adicionarEndereco Service");
		
	
		System.out.println("cliente cadastrado com sucesso: " + endereco.getTipos() + "ID: " + endereco.getId());


        // Chamada ao DAO para inserir o endereço
        return daoEndereco.inserirEndereco(cliente, endereco);
    }
}



