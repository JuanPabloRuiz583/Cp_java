package Daos;

import Entities.Moto;
import java.util.Map;

public interface MotoDao {
    void salvar(Moto moto);
    Map<String, Moto> listarMotos();
    Moto buscarPorPlaca(String placa);
}

