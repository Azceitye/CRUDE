
package model.bean;


public class campoInfo {
    private long ID;
    private String titulo;
    private String descricao;
    private String tipo;
    private int prioridade;
    private boolean eObrigatorio;
    
    
    public campoInfo(long ID, String titulo, String descricao, String tipo, int prioridade, boolean eObrigatorio) {
        this.ID = ID;
        this.titulo = titulo;
        this.descricao = descricao;
        this.tipo = tipo;
        this.prioridade = prioridade;
        this.eObrigatorio = eObrigatorio;
    }

    public campoInfo(long ID, String label, String tipo) {
        this.titulo = label;
        this.tipo = tipo;
    }

    public String getLabel() {
        return titulo;
    }

    public void setLabel(String label) {
        this.titulo = label;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public boolean iseObrigatorio() {
        return eObrigatorio;
    }

    public void seteObrigatorio(boolean eObrigatorio) {
        this.eObrigatorio = eObrigatorio;
    }

    public long getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "campoInfo{" + "ID=" + ID + ", titulo=" + titulo + ", descricao=" + descricao + ", tipo=" + tipo + ", prioridade=" + prioridade + ", eObrigatorio=" + eObrigatorio + '}';
    }
    
    
}
