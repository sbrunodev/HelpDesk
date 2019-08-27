package br.fipp.entidade;

/**
 *
 * @author Bruno
 */
public class AtividadeClassificacao {

    private int atiCodigo;
    private int claCodigo;

    public AtividadeClassificacao() {
    }

    public AtividadeClassificacao(int atiCodigo, int claCodigo) {
        this.atiCodigo = atiCodigo;
        this.claCodigo = claCodigo;
    }

    public int getAtiCodigo() {
        return atiCodigo;
    }

    public void setAtiCodigo(int atiCodigo) {
        this.atiCodigo = atiCodigo;
    }

    public int getClaCodigo() {
        return claCodigo;
    }

    public void setClaCodigo(int claCodigo) {
        this.claCodigo = claCodigo;
    }
    
    
    
            
}
