package model.entity;


import java.util.ArrayList;
import java.util.List;

public class Carrinho {
	    private List<CarrinhoItem> itens;

	    public Carrinho() {
	        this.itens = new ArrayList<>();
	    }

	    public List<CarrinhoItem> getItens() {
	        return itens;
	    }

	    public void adicionarItem(CarrinhoItem item) {
	        itens.add(item);
	    }

	    public void removerItem(int index) {
	        if (index >= 0 && index < itens.size()) {
	            itens.remove(index);
	        }
	    }

	    public void limparCarrinho() {
	        itens.clear();
	    }

	    public double calcularTotal() {
	        double total = 0;
	        for (CarrinhoItem item : itens) {
	            total += item.getTotal();
	        }
	        return total;
	    }
}

