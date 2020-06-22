package sp.etec.applista;

import java.io.Serializable;

public class Produto implements Serializable {
    String nome;
    private double valor;
    private int id;

    public String getNome(){
        return this.nome;
    }

    public void setNome(String _nome)
    {
        this.nome = _nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }


    @Override
    public String toString() {
        return nome + " " +String.format(" R$ %.2f",  valor);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
