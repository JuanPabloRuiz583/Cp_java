package Service;

import Daos.*;
import Entities.*;
import Factory.VeiculoFactory;
import config.DatabaseConfig;

import java.util.Map;

public class SeguroService {
    private static SeguroService instance;
    private ClienteDAO clienteDAO;
    private CarroDao carroDAO;
    private MotoDao motoDAO;

    private SeguroService(DatabaseConfig dbConfig) {
        clienteDAO = new ClienteDaoImpl(dbConfig);
        carroDAO = new CarroDaoImpl(dbConfig);
        motoDAO = new MotoDaoImpl(dbConfig);
    }

    public static SeguroService getInstance(DatabaseConfig dbConfig) {
        if (instance == null) {
            instance = new SeguroService(dbConfig);
        }
        return instance;
    }

    public void cadastrarCliente(String nome, String documento, int idade) {
        Cliente cliente = new Cliente(nome, documento, idade);
        clienteDAO.salvar(cliente);
    }

    public void cadastrarVeiculo(String tipo, String modelo, String placa, String documentoCliente) {
        Cliente cliente = clienteDAO.buscar(documentoCliente);
        if (cliente != null) {
            Veiculo veiculo = VeiculoFactory.criarVeiculo(tipo, modelo, placa, cliente);
            if (veiculo instanceof Carro) {
                carroDAO.salvar((Carro) veiculo);
            } else if (veiculo instanceof Moto) {
                motoDAO.salvar((Moto) veiculo);
            }
        }
    }

    public double calcularPremioSeguro(String documento, double valor) {
        Cliente cliente = clienteDAO.buscar(documento);
        if (cliente != null) {
            Seguro seguro = new Seguro(cliente, valor);
            return seguro.calcularPremio();
        }
        return 0;
    }

    public Map<String, Cliente> listarClientes() {
        return clienteDAO.listarClientes();
    }

    public Map<String, Carro> listarCarros() {
        return carroDAO.listarCarros();
    }

    public Map<String, Moto> listarMotos() {
        return motoDAO.listarMotos();
    }
}