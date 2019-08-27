package br.fipp.entidade;

public class Status {

    private int codigo;
    private String status;
    private String ativo;

    public Status(int codigo, String status, String ativo) {
        this.codigo = codigo;
        this.status = status;
        this.ativo = ativo;
    }

    public Status() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }
    
    
    
}
