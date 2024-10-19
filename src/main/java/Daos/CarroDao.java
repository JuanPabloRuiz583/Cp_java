package Daos;

import Entities.Carro;

import java.util.Map;

public interface CarroDao {
    Map<String, Carro> listarCarros();

    void salvar(Carro carro);
    Carro buscarPorPlaca(String placa);
}
