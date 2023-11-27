
package view;

import controller.dao.CampoDao;
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
import model.bean.CampoText;
import model.conn.ConnectionFactory;


public class CreateJLabel extends JFrame {
    
    JPanel mainPanel;
    private List<CampoText> campos;
    private CampoDao dao;
    private final long ID;

    public CreateJLabel(long ID) throws HeadlessException {
        super("Formulário");
        this.ID = ID;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        prepareUI();
        pack();
    }
    
    private void prepareUI() {
        dao = new CampoDao(new ConnectionFactory().getConnection());
        campos = dao.buscar();

        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 80, 5, 80);

        for (CampoText cmp : campos) {
            gbc.gridy++;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            mainPanel.add(cmp, gbc);
        }

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (CampoText cmp : campos) {
                    dao.createForm(cmp.getObj().getID(), ID, cmp.getTextoInput().getText());
                }
            }
        });
        gbc.gridy++;
        mainPanel.add(btnRegistrar, gbc);

        add(mainPanel);
    }
}
