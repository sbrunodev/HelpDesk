
package br.fipp.entidade;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class Funcionario {
    
    private int codigo;
    private String nome;
    private Date dtcontratacao;
    private Date dtdemissao;
    private String ativo;
    private String senha;
    private String tipo;

    public Funcionario() {
    }

    public Funcionario(int codigo, String nome, Date dtcontratacao, Date dtdemissao, String ativo, String senha, String tipo) {
        this.codigo = codigo;
        this.nome = nome;
        this.dtcontratacao = dtcontratacao;
        this.dtdemissao = dtdemissao;
        this.ativo = ativo;
        this.senha = senha;
        this.tipo = tipo;
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

    public Date getDtcontratacao() {
        return dtcontratacao;
    }

    public void setDtcontratacao(Date dtcontratacao) {
        this.dtcontratacao = dtcontratacao;
    }

    public Date getDtdemissao() {
        return dtdemissao;
    }

    public void setDtdemissao(Date dtdemissao) {
        this.dtdemissao = dtdemissao;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
    
}
