package Daos;

import Entities.Cliente;
import config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ClienteDaoImpl implements ClienteDAO {
    private DatabaseConfig dbConfig;

    public ClienteDaoImpl(DatabaseConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    @Override
    public void salvar(Cliente cliente) {
        String sql = "INSERT INTO clientes (nome, documento, idade) VALUES (?, ?, ?)";
        try (Connection conn = dbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getDocumento());
            stmt.setInt(3, cliente.getIdade());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar cliente", e);
        }
    }

    @Override
    public Cliente buscar(String documento) {
        String sql = "SELECT * FROM clientes WHERE documento = ?";
        try (Connection conn = dbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, documento);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(rs.getString("nome"), rs.getString("documento"), rs.getInt("idade"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente", e);
        }
        return null;
    }

    @Override
    public Map<String, Cliente> listarClientes() {
        String sql = "SELECT * FROM clientes";
        Map<String, Cliente> clientes = new HashMap<>();
        try (Connection conn = dbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Cliente cliente = new Cliente(rs.getString("nome"), rs.getString("documento"), rs.getInt("idade"));
                clientes.put(cliente.getDocumento(), cliente);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes", e);
        }
        return clientes;
    }
}