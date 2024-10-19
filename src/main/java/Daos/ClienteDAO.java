package Daos;

import Entities.Cliente;

import java.util.Map;

public interface ClienteDAO {
    void salvar(Cliente cliente);
    Cliente buscar(String documento);

    Map<String, Cliente> listarClientes();
}
