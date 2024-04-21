package Service;

import java.util.ArrayList;

import Dao.DAOCupons;
import model.entity.Cliente;
import model.entity.Cupons;

public class CuponsService {

	private DAOCupons daoCupons;

	public CuponsService() {
		this.daoCupons = new DAOCupons();
	}

	public ArrayList<Cupons> listarCupom(Cliente cliente) {
		return daoCupons.ListarCupons(cliente);
	}

}