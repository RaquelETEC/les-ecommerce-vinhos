package model.entity;

public class Troca {
    private int id;
    private int itemId;
    private int quantidadeTrocada;
    private int status;

    public Troca() {
        // Construtor padrão
    }

    public Troca(int id, int itemId, int quantidadeTrocada, int status) {
        this.id = id;
        this.itemId = itemId;
        this.quantidadeTrocada = quantidadeTrocada;
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

    public int getQuantidadeTrocada() {
        return quantidadeTrocada;
    }

    public void setQuantidadeTrocada(int quantidadeTrocada) {
        this.quantidadeTrocada = quantidadeTrocada;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Troca{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", quantidadeTrocada=" + quantidadeTrocada +
                ", status=" + status +
                '}';
    }
}
