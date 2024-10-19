package Daos;

import Entities.Cliente;
import Entities.Moto;
import config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MotoDaoImpl implements MotoDao {
    private DatabaseConfig dbConfig;

    public MotoDaoImpl(DatabaseConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    @Override
    public void salvar(Moto moto) {
        String sql = "INSERT INTO motos (placa, modelo, documento_cliente) VALUES (?, ?, ?)";
        try (Connection conn = dbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, moto.getPlaca());
            stmt.setString(2, moto.getModelo());
            stmt.setString(3, moto.getDocumentoCliente());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar moto", e);
        }
    }



    @Override
    public Map<String, Moto> listarMotos() {
        String sql = "SELECT placa, modelo, documento_cliente FROM motos";
        Map<String, Moto> motos = new HashMap<>();
        try (Connection conn = dbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Moto moto = new Moto(rs.getString("modelo"), rs.getString("placa"), rs.getString("documento_cliente"));
                motos.put(moto.getPlaca(), moto);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar motos", e);
        }
        return motos;
    }

    @Override
    public Moto buscarPorPlaca(String placa) {
        String sql = "SELECT placa, modelo, documento_cliente FROM motos WHERE placa = ?";
        try (Connection conn = dbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, placa);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Moto(rs.getString("modelo"), rs.getString("placa"), rs.getString("documento_cliente"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar moto por placa", e);
        }
        return null;
    }
}