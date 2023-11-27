
package model.bean;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CampoText extends JPanel {
    private campoInfo obj;
    private JTextField input;
    private final String FONT_FAMILY = "Arial";
    

    public CampoText(campoInfo obj) {
        this.obj = obj;
        this.input = new JTextField();
        init();
    }

    public final void init() {
        // <-- Layout -->        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0; 
        // <-- Obrigatório? -->
        
        // <-- Titulo -->
        int tamanhoTitulo = 4;
        obj.seteObrigatorio(false);
        JLabel titulo = new JLabel(
                "<html><font size='" + tamanhoTitulo + "' color='black'>" + obj.getLabel() + "</font><font size='" + tamanhoTitulo + "' color='red'>" + (obj.iseObrigatorio() ? "*" : "") +  "</font></html>"
        );
        gbc.gridy = 0; 
        gbc.gridwidth = 2; 
        gbc.insets = new Insets(0, 0, 10, 0);
        add(titulo, gbc);
        
        // <-- Descricao -->
        JLabel descricao = new JLabel();
        descricao.setText(obj.getDescricao());
        descricao.setFont(new Font(FONT_FAMILY, Font.PLAIN, 12));
        gbc.gridy = 1; 
        gbc.gridwidth = 1; 
        add(descricao, gbc);
        
        // <-- Campo -->
        gbc.gridy = 2; 
        input.setPreferredSize(new Dimension(500, 30));
        input.setMaximumSize(new Dimension(500, 30));
        add(input, gbc);
    }

    public campoInfo getObj() {
        return obj;
    }

    public void setObj(campoInfo obj) {
        this.obj = obj;
    }

    public JTextField getTextoInput() {
        return input;
    }

    public void setTextoInput(JTextField textoInput) {
        this.input = textoInput;
    }
}
