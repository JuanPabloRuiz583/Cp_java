package Entities;

public class Moto implements Veiculo {
    private String modelo;
    private String placa;
    private String documentoCliente;

    public Moto(String modelo, String placa, String documentoCliente) {
        this.modelo = modelo;
        this.placa = placa;
        this.documentoCliente = documentoCliente;
    }

    @Override
    public String getModelo() {
        return modelo;
    }

    @Override
    public String getPlaca() {
        return placa;
    }

    @Override
    public Cliente getCliente() {
        return null;
    }

    public String getDocumentoCliente() {
        return documentoCliente;
    }
}