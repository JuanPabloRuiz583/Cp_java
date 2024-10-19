package Entities;

public class Seguro {
    private Cliente cliente;
    private double valor;

    public Seguro(Cliente cliente, double valor) {
        this.cliente = cliente;
        this.valor = valor;
    }

    public double calcularPremio() {
        int idade = cliente.getIdade();
        if (idade > 30){
            return valor * 0.05;
        }
        else {
            return valor * 0.08;
        }

    }
}




