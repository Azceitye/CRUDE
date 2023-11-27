
package model.bean;


public interface FormularioInterface {
    void novoForm();
    void removerForm(long id);
    void atualizarForm(CampoInfo info);
    void setLayoutItens();
}
