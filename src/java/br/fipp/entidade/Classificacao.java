package br.fipp.entidade;


public class Classificacao {

    private int codigo;
    private String nome;
    private String ativa;

    public Classificacao() {
    }

    public Classificacao(int codigo, String nome, String ativa) {
        this.codigo = codigo;
        this.nome = nome;
        this.ativa = ativa;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAtiva() {
        return ativa;
    }

    public void setAtiva(String ativa) {
        this.ativa = ativa;
    }
    
    
    
}
