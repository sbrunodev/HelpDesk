package br.fipp.entidade;

import java.sql.Date;

public class Atividade {

    private int codigo;
    private String descricao;
    private Date dtinicio;
    private Date dtfim;
    private int funCodigo;
    private int staCodigo;
    private String solEmail;

    public Atividade() {
    }

    public Atividade(int codigo, String descricao, Date dtinicio, Date dtfim, int funCodigo, int staCodigo, String solEmail) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.dtinicio = dtinicio;
        this.dtfim = dtfim;
        this.funCodigo = funCodigo;
        this.staCodigo = staCodigo;
        this.solEmail = solEmail;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDtinicio() {
        return dtinicio;
    }

    public void setDtinicio(Date dtinicio) {
        this.dtinicio = dtinicio;
    }

    public Date getDtfim() {
        return dtfim;
    }

    public void setDtfim(Date dtfim) {
        this.dtfim = dtfim;
    }

    public int getFunCodigo() {
        return funCodigo;
    }

    public void setFunCodigo(int funCodigo) {
        this.funCodigo = funCodigo;
    }

    public int getStaCodigo() {
        return staCodigo;
    }

    public void setStaCodigo(int staCodigo) {
        this.staCodigo = staCodigo;
    }

    public String getSolEmail() {
        return solEmail;
    }

    public void setSolEmail(String solEmail) {
        this.solEmail = solEmail;
    }
    
    
    
    
    
}
