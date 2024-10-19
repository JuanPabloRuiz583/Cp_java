package Daos;

import Entities.Carro;
import Entities.Moto;
import config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CarroDaoImpl implements CarroDao {
    private DatabaseConfig dbConfig;

    public CarroDaoImpl(DatabaseConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    @Override
    public Map<String, Carro> listarCarros() {
        String sql = "SELECT placa, modelo, documento_cliente FROM carros";
        Map<String, Carro> carros = new HashMap<>();
        try (Connection conn = dbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Carro carro = new Carro(rs.getString("modelo"), rs.getString("placa"), rs.getString("documento_cliente"));
                carros.put(carro.getPlaca(), carro);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar carros", e);
        }
        return carros;
    }


    @Override
    public void salvar(Carro carro) {
        String sql = "INSERT INTO carros (placa, modelo, documento_cliente) VALUES (?, ?, ?)";
        try (Connection conn = dbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, carro.getPlaca());
            stmt.setString(2, carro.getModelo());
            stmt.setString(3, carro.getDocumentoCliente());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar carro", e);
        }
    }

    @Override
    public Carro buscarPorPlaca(String placa) {
        return null;
    }
}