
package model.bean;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import view.Formulario;

public class CampoText extends JPanel {
    private CampoInfo obj;
    private JTextField input;
    private Formulario forms;

    public CampoText(CampoInfo obj, Formulario formulario) {
        this.obj = obj;
        this.input = new JTextField();
        this.forms = formulario;
        init();
    }
    
    public final void init() {
        final String FONT_FAMILY = "Arial";
        // <-- Layout -->        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0; 
        
        
        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forms.atualizarForm(obj);
            }
        });
        
        JButton btnApagar = new JButton("Apagar");
        btnApagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                forms.removerForm(obj.getID());
            }
        });
            
        add(btnAtualizar);
        add(btnApagar);
        
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
        input.setMinimumSize(new Dimension(100, 30));
        
        add(input, gbc);
    }

    public CampoInfo getObj() {
        return obj;
    }

    public void setObj(CampoInfo obj) {
        this.obj = obj;
    }

    public JTextField getTextoInput() {
        return input;
    }

    public void setTextoInput(String textoInput) {
        this.input.setText(textoInput);
    }

    public void setForms(Formulario forms) {
        this.forms = forms;
    }
    
    
}
