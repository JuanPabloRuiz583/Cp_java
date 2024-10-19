package Factory;

import Entities.Cliente;
import Entities.Seguro;

public class SeguroFactory {
    public static Seguro criarSeguro(Cliente cliente, double valor) {

        return new Seguro(cliente, valor);
    }
}

