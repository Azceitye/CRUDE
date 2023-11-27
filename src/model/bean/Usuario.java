
package model.bean;


public class Usuario {
    private final int ID;
    private String apelido;

    public Usuario(int ID, String apelido) {
        this.ID = ID;
        this.apelido = apelido;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public int getID() {
        return ID;
    }
    
    
}
