
package br.fipp.entidade;

public class Solicitante {
    
    private String email;
    private String nome;
    private String telefone;
    private String observacao;

    public Solicitante() {
    }

    public Solicitante(String email, String nome, String telefone, String observacao) {
        this.email = email;
        this.nome = nome;
        this.telefone = telefone;
        this.observacao = observacao;
    }

   
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
    
    
    
    
}
