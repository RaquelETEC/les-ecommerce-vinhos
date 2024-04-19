package Service;

import java.util.ArrayList;

import Dao.DAOCupons;
import model.entity.Cliente;
import model.entity.Cupons;

public class CupomService {
	
	private DAOCupons daoCupons;
	
	public CupomService() {
		
	}

	public ArrayList<Cupons> listarCupom(Cliente cliente) {
		return daoCupons.ListarCupons(cliente);
	}

}
