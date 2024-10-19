package Factory;

import Entities.Cliente;

public class ClienteFactory {
    public static Cliente criarCliente(String nome, String documento, int idade) {

        return new Cliente(nome, documento,idade);
    }
}
