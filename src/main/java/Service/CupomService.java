package Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

	public String GerarCupom(String tipoCupom, Double valorCupom, int idPedido, int idProduto) {
        if (tipoCupom == null || tipoCupom.isEmpty()) {
            return "TIPO CUPOM É OBRIGATÓRIO!";
        } else if (valorCupom == null || valorCupom <= 0) {
            return "VALOR DO CUPOM É OBRIGATÓRIO!";
        } else if (tipoCupom.equals("T") && (idPedido == 0 )) {
            return "PARA CUPONS DE TROCA É OBRIGATÓRIO O ID DO PEDIDO";
        } else {
            String codigo;
            String descricao;
            String img;
            Date validade;

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataAtual = new Date();

            
           if(tipoCupom.equals("T") &&  idProduto == 0 ) {
				codigo = "CANCELADO#" + idPedido;
				descricao = "Desconto de R$" + valorCupom + ", pelo cancelamento do pedido " + idPedido;
				img = "trocaCupom.png";
				validade = adicionarMeses(dataAtual, 12);         
            }else if (tipoCupom.equals("T")) { 
                codigo = "TROCA#" + idPedido;
                descricao = "Desconto de R$" + valorCupom + " pela troca do item " + idProduto + " no pedido " + idPedido;
                img = "trocaCupom.png";
                validade = adicionarMeses(dataAtual, 12);
          
            }
            else { 
                codigo = "PROMO#"; 
                descricao = "Cupom promocional de desconto de R$" + valorCupom;
                img = "trocaCupom.png";
                validade = adicionarMeses(dataAtual, 6); //validade de 6 meses
            }

            // Retornando o resultado
            return ""+daoCupom.gerarCupom(codigo, descricao, img, tipoCupom, valorCupom, validade, 0);
        }
    }

    // Função para adicionar meses a uma data
    private Date adicionarMeses(Date data, int meses) {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.setTime(data);
        cal.add(java.util.Calendar.MONTH, meses);
        return cal.getTime();
    }

	public String vincularCupomAoCliente(Cupons cupom, Cliente cliente) {
	   if (cupom == null || cliente == null) {
	        return "Cupom ou cliente não fornecido";
	    }
	   else {
		return daoCupom.vincularCupomAoCliente(cupom, cliente);
	   }
	}

}
