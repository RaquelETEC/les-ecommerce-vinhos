package Service;

import java.util.ArrayList;

import Dao.DaoCupons;
import model.entity.Cliente;
import model.entity.Cupons;

public class CuponsService {

	private DaoCupons daoCupons;

	public CuponsService() {
		this.daoCupons = new DaoCupons();
	}

	public ArrayList<Cupons> listarCupom(Cliente cliente) {
		return daoCupons.ListarCuponsCliente(cliente);
	}

}