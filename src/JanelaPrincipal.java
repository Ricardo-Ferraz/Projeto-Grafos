

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaPrincipal extends JFrame implements ActionListener {


    private Grafo grafo;
    private String endereco;

    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;

    private JTextField text= new JTextField(20);
    private JTextField text2= new JTextField(20);

    Object[] campoFarmacia= {"Digite o id do local onde você se encontra", text};
    Object[] campoMenorCaminho= {"Digite o id do local onde você se encontra",text,"Digite o id do seu destino",text2};

    private JanelaMapa janelaMapa;

    public JanelaPrincipal(Grafo grafo, String endereco){
        this.endereco = endereco;
        this.grafo= grafo;
        this.janelaMapa= new JanelaMapa(endereco);

        this.b1= new JButton("Exibir mapa");
        this.b2= new JButton("Farmácia mais próxima");
        this.b3= new JButton("Menor caminho entre dois pontos");
        this.b4= new JButton("Sair");

        this.setLayout(null);
        this.b1.setBounds(50, 150, 400,75);
        this.b1.addActionListener(this);
        this.b2.setBounds(50,30,400,75);
        this.b2.addActionListener(this);
        this.b3.setBounds(50, 275, 400, 75);
        this.b3.addActionListener(this);
        this.b4.setBounds(150,400, 200,50);
        this.b4.addActionListener(this);

        this.getContentPane().add(b1);
        this.getContentPane().add(b2);
        this.getContentPane().add(b3);
        this.getContentPane().add(b4);

        this.setSize(500,500);
        this.setTitle("UniFarma");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b1){ //Exibir mapa
            this.janelaMapa.setVisible(!this.janelaMapa.isVisible());
        }

        else if(e.getSource() == b2){ //Farmacia mais próxima
            int opcao = JOptionPane.showConfirmDialog(null, this.campoFarmacia, "Atenção!", JOptionPane.OK_CANCEL_OPTION);
            if(opcao == JOptionPane.OK_OPTION){
                JTextArea texto = new JTextArea(grafo.farmaciaMaisProxima(Integer.parseInt(text.getText())));
                JScrollPane scroll = new JScrollPane(texto);
                texto.setEditable(false);
                scroll.setPreferredSize(new Dimension(300,300));
                scroll.setFocusable(false);
                JOptionPane.showMessageDialog(null, scroll, "Menor caminho para a farmácia mais próxima",JOptionPane.INFORMATION_MESSAGE);
            }

            text.setText("");
        }

        else if(e.getSource() == b3){ //Menor caminho entre dois pontos
            int opcao = JOptionPane.showConfirmDialog(null, this.campoMenorCaminho, "Atenção!", JOptionPane.OK_CANCEL_OPTION);
            if(opcao == JOptionPane.OK_OPTION){
                JTextArea texto = new JTextArea(grafo.menorCaminho(Integer.parseInt(text.getText()), Integer.parseInt(text2.getText())));
                JScrollPane scroll = new JScrollPane(texto);
                texto.setEditable(false);
                scroll.setPreferredSize(new Dimension(300,300));
                scroll.setFocusable(false);
                JOptionPane.showMessageDialog(null, scroll, "Menor caminho gerado",JOptionPane.INFORMATION_MESSAGE);
            }
            text.setText("");
            text2.setText("");
        }

        else if(e.getSource() == b4){ //Botão se sair
            System.exit(0);
        }
    }
}
