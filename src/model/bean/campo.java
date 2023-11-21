
package model.bean;


abstract public class campo {
    private final int ID;
    private String label;
    private String descricao;
    private String tipo;
    private int prioridade;
    private boolean eObrigatorio;
    
    
    public campo(int ID, String label, String descricao, String tipo, int prioridade, boolean eObrigatorio) {
        this.ID = ID;
        this.label = label;
        this.descricao = descricao;
        this.tipo = tipo;
        this.prioridade = prioridade;
        this.eObrigatorio = eObrigatorio;
    }

    public campo(int ID, String label, String tipo) {
        this.ID = ID;
        this.label = label;
        this.tipo = tipo;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
}
