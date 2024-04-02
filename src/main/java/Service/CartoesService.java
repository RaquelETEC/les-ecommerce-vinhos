package Service;

import java.util.ArrayList;

import Dao.DaoCartoes;
import model.entity.CartaoDeCredito;
import model.entity.Cliente;
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
   
    public ArrayList<CartaoDeCredito> listarCartoes(Cliente cliente) {
        return daoCartoes.ListarCartoes(cliente);
    }
}