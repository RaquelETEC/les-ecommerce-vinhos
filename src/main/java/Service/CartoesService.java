package Service;

import java.util.ArrayList;

import Dao.DaoCartoes;
import model.entity.CartaoDeCredito;
import model.entity.Cliente;

public class CartoesService {

    private DaoCartoes daoCartoes;

    public CartoesService() {
        this.daoCartoes = new DaoCartoes();
    }

    public ArrayList<CartaoDeCredito> listarCartoes(Cliente cliente) {
        return daoCartoes.ListarCartoes(cliente);
    }
}