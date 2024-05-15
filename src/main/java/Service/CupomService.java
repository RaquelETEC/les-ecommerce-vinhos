package Service;

import java.util.ArrayList;

import Dao.DaoCupons;
import model.entity.Cliente;
import model.entity.Cupons;

public class CupomService {
	private DaoCupons daoCupom;
	
    public CupomService() {
        this.daoCupom = new DaoCupons();
    }

	public ArrayList<Cupons> listarCupom(Cliente cliente) {
		return daoCupom.ListarCupons(cliente);
	}
	
	public int adicionarCupomTroca(Cliente cliente, Cupons cupom) {
		System.out.println("Cheguei no adicionarCupomTroca Service");

		return daoCupom.inserirCuponsTroca(cliente, cupom);
	}



}
