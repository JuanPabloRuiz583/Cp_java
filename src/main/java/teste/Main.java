package teste;

import Entities.Carro;
import Entities.Cliente;
import Entities.Moto;
import Service.SeguroService;
import config.DatabaseConfig;

public class Main {
    public static void main(String[] args) {
        DatabaseConfig dbConfig = new DatabaseConfig("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "rm557727", "210605");
        SeguroService service = SeguroService.getInstance(dbConfig);

        // Cadastrando clientes
        try {
            System.out.println("Cadastrando clientes...");
            service.cadastrarCliente("João Silva", "123456789", 18);
            service.cadastrarCliente("Maria Oliveira", "987654321", 31);
        } catch (RuntimeException e) {
            System.out.println("Erro ao cadastrar clientes: " + e.getMessage());
        }

        // Cadastrando veículos
        try {
            System.out.println("Cadastrando veículos...");
            service.cadastrarVeiculo("carro", "Fusca", "ABC-1234", "123456789");
            service.cadastrarVeiculo("carro", "Civic", "XYZ-5678", "987654321");
            service.cadastrarVeiculo("moto", "XRE", "MNO-9101", "123456789");
            service.cadastrarVeiculo("moto", "Fazer", "DEF-2345", "987654321");
        } catch (RuntimeException e) {
            System.out.println("Erro ao cadastrar veículos: " + e.getMessage());
        }

        // Calculando prêmio de seguro
        try {
            System.out.println("Calculando prêmio de seguro...");
            double premioJoao = service.calcularPremioSeguro("123456789", 10000);
            System.out.println("Prêmio do seguro de João: R$ " + premioJoao);

            double premioMaria = service.calcularPremioSeguro("987654321", 10000);
            System.out.println("Prêmio do seguro de Maria: R$ " + premioMaria);
        } catch (RuntimeException e) {
            System.out.println("Erro ao calcular prêmio de seguro: " + e.getMessage());
        }

        // Listando clientes
        try {
            System.out.println("Listando clientes cadastrados:");
            for (Cliente cliente : service.listarClientes().values()) {
                System.out.println(cliente.getNome() + " - " + cliente.getDocumento());
            }
        } catch (RuntimeException e) {
            System.out.println("Erro ao listar clientes: " + e.getMessage());
        }

        // Listando carros
        try {
            System.out.println("Listando carros cadastrados:");
            for (Carro carro : service.listarCarros().values()) {
                System.out.println(carro.getModelo() + " - " + carro.getPlaca() + " - " + carro.getDocumentoCliente());
            }
        } catch (RuntimeException e) {
            System.out.println("Erro ao listar carros: " + e.getMessage());
        }

        // Listando motos
        try {
            System.out.println("Listando motos cadastradas:");
            for (Moto moto : service.listarMotos().values()) {
                System.out.println(moto.getModelo() + " - " + moto.getPlaca());
            }
        } catch (RuntimeException e) {
            System.out.println("Erro ao listar motos: " + e.getMessage());
        }
    }
}