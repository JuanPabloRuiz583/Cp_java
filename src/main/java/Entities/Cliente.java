package Entities;


public class Cliente {
    private String nome;
    private String documento;
    private int idade;

    public Cliente(String nome, String documento,int idade) {
        if (idade < 18) {
            throw new IllegalArgumentException("O cliente deve ser maior de idade. Idade fornecida: " + idade);
        }
        this.nome = nome;
        this.documento = documento;
        this.idade= idade;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public int getIdade() {
        return idade;
    }

    public void atualizarNome(String novoNome) {
        validarNome(novoNome);
        this.nome = novoNome;
    }

    // Método de validação para nome
    private void validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        if (nome.length() < 3) {
            throw new IllegalArgumentException("O nome deve ter pelo menos 3 caracteres.");
        }

    }


    }
