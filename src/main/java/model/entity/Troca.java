package model.entity;

public class Troca {
    private int id;
    private int itemId;
    private int quantidadeSolicitada;
    private TiposStatusItensPedido status;

    public Troca() {
        // Construtor padrão
    }

    public Troca(int id, int itemId, int quantidadeSolicitada, TiposStatusItensPedido status) {
        this.id = id;
        this.itemId = itemId;
        this.quantidadeSolicitada = quantidadeSolicitada;
        this.status = status;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantidadeSolicitada() {
        return quantidadeSolicitada;
    }

    public void setQuantidadeSolicitada(int quantidadeSolicitada) {
        this.quantidadeSolicitada = quantidadeSolicitada;
    }

    public TiposStatusItensPedido getStatus() {
		return status;
	}

	public void setStatus(TiposStatusItensPedido status) {
		this.status = status;
	}

	@Override
    public String toString() {
        return "Troca{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", quantidadeTrocada=" + quantidadeSolicitada +
                ", status=" + status +
                '}';
    }
}
