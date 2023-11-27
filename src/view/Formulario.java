
package view;

import controller.dao.CampoDao;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import model.bean.CampoInfo;
import model.bean.CampoText;
import model.bean.FormularioInterface;
import model.conn.ConnectionFactory;


public class Formulario extends JFrame implements FormularioInterface{
    JPanel mainPanel;
    JPanel controlPanel;
    private List<CampoText> campos;
    private CampoDao dao;
    private final long ID;

    public Formulario(long ID) throws HeadlessException {
        super("Formulário");
        this.ID = ID;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        prepareUI();
        pack();
    }
    
    private void prepareUI() {
        dao = new CampoDao(new ConnectionFactory().getConnection());
        
        
        final int marginHorizontal = 50;
        
        // <-- Barra Superio --> //
        controlPanel = new JPanel(new GridBagLayout());
        controlPanel.setPreferredSize(new Dimension(800, 50));
        controlPanel.setMaximumSize(new Dimension(800, 70));
        GridBagConstraints gbcControl = new GridBagConstraints();
        gbcControl.gridx = 0;
        gbcControl.gridy = 0;
        gbcControl.anchor = GridBagConstraints.WEST;
        gbcControl.insets = new Insets(5, marginHorizontal, 5, marginHorizontal);
        gbcControl.weightx = 0;
        
        
        JButton btnLogout = new JButton("Sair");
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        
        
        JButton btnAddCampo = new JButton("Adicionar");
        btnAddCampo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                novoForm();
            }
        });
        
        controlPanel.add(btnLogout, gbcControl);
        gbcControl.gridx++;
        controlPanel.add(btnAddCampo, gbcControl);
        
        // <-- Formulário --> //
        mainPanel = new JPanel(new GridBagLayout());
        setLayoutItens();       
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        
        
        // <-- Barra Inferior --> //
        JPanel registroPanel = new JPanel(new GridBagLayout());
        registroPanel.setPreferredSize(new Dimension(100, 50));
        registroPanel.setMaximumSize(new Dimension(100, 20));
        GridBagConstraints gbcRegistro = new GridBagConstraints();
        gbcRegistro.gridx = 0;
        gbcRegistro.gridy = 0;
        gbcRegistro.anchor = GridBagConstraints.FIRST_LINE_START;
        gbcRegistro.insets = new Insets(5, marginHorizontal, 5, marginHorizontal);
        
        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createForm();
            }
        });
        
        registroPanel.add(btnRegistrar);        
        
        add(controlPanel);
        add(scrollPane, BorderLayout.CENTER);
        add(registroPanel);
        setLocationRelativeTo(null);
    }
    
    private void createForm() {
        int total_rows=0;
        for (CampoText cmp : campos) {
            int rows=0;
            total_rows += rows = dao.createForm(cmp.getObj().getID(), ID, cmp.getTextoInput().getText());
            if(rows > 0) {
                JOptionPane.showMessageDialog(this, cmp.getObj().getLabel() + " foi atualizado");
            } 
        }
        
        if(total_rows == 0) {
            JOptionPane.showMessageDialog(this, "Nada registrado");
        }
    }
    
    @Override
    public void novoForm() {
        novoCampo novo = new novoCampo();
        novo.setForm(this);
        novo.setVisible(true);
        setLayoutItens();
    }

    @Override
    public void removerForm(long id) {
        int opt = JOptionPane.showConfirmDialog(this, "Tem certeza de deletar esse campo?", "Apagar campo", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
        if(opt == JOptionPane.YES_OPTION) {
            dao.apagar(id);
            setLayoutItens();
        }
    }

    @Override
    public void atualizarForm(CampoInfo info) {
        novoCampo novo = new novoCampo();
        novo.getjFieldTitulo().setText(info.getLabel());
        novo.getjComboTipo().setSelectedItem(info.getTipo());
        novo.getjTextAreaDesc().setText(info.getDescricao());
        novo.setID(info.getID());        
        novo.setForm(this);
        novo.setVisible(true);
    }

    @Override
    public void setLayoutItens() {
        mainPanel.removeAll();
        campos = dao.buscar(ID, this);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.insets = new Insets(5, 50, 5, 50);

        
        for (CampoText cmp : campos) {
            gbc.gridy++;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            cmp.setForms(this);
            mainPanel.add(cmp, gbc);
        }
        
        mainPanel.revalidate(); 
        mainPanel.repaint();
    }
    
    private void logout() {
        Login login = new Login();
        login.setVisible(true);
        dispose();
    }
}
