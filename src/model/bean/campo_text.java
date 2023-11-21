
package model.bean;

public class campo_text extends campo {
    private String textoInput;

    public campo_text(int ID, String label, String descricao, String tipo, int prioridade, boolean eObrigatorio) {
        super(ID, label, descricao, tipo, prioridade, eObrigatorio);
    }

    public campo_text(int ID, String label, String tipo) {
        super(ID, label, tipo);
    }

    public String getTextoInput() {
        return textoInput;
    }

    public void setTextoInput(String textoInput) {
        this.textoInput = textoInput;
    }
}
