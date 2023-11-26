
package teste;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.util.List;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;


public class CreateJLabel extends JFrame {
    
    static CreateJLabel frame;
    static int countMe = 0;
    JPanel mainPanel;
    
    public static void init() { 
        frame = new CreateJLabel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.prepareUI();
        frame.pack();
        frame.setTitle("Exemplo de Campo");
        frame.setVisible(true);
    }
    
    
    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            init();
        });
    }
    
    private void prepareUI() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JButton buttonAdd = new JButton("Add subPanel");
        buttonAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.add(new campo("Titulo"));
                frame.pack();
            }
        });
        
        JButton buttonRemoveAll = new JButton("Remove All");
        buttonRemoveAll.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.removeAll();
                frame.pack();
            }
        });

        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(buttonAdd, BorderLayout.PAGE_START);
        getContentPane().add(buttonRemoveAll, BorderLayout.PAGE_END);
    }
    
    
    private class campo extends JPanel {
        campo me;
        
        public campo(String titulo) {
            super();
            me = this;
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            
            JLabel label = new JLabel(titulo);
            
            JTextField field = new JTextField();
            
            field.setPreferredSize(new Dimension(100, 30));
            field.setMaximumSize(new Dimension(500, 30));
            // Add
            add(label);
            add(Box.createRigidArea(new Dimension(0, 10)));
            add(field);
            
        }
    }
    
    /*private class subPanel extends JPanel {
        
        subPanel me;

        public subPanel() {
            super();
            me = this;
            JLabel myLabel = new JLabel("Hello subPanel(): " + countMe++);
            add(myLabel);
            JButton myButtonRemoveMe = new JButton("remove me");
            myButtonRemoveMe.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    me.getParent().remove(me);
                    frame.pack();
                }
            });
            add(myButtonRemoveMe);
        }
    }*/
}
