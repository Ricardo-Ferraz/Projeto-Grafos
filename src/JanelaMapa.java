import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.*;

public class JanelaMapa extends JFrame {

    private JPanel painel;
    private JLabel img;

    public JanelaMapa(String endereco){
       JScrollPane scroll = new JScrollPane(painel);
       this.getContentPane().add(scroll);
       painel= new JPanel(new FlowLayout(FlowLayout.CENTER));
       painel.setLayout(new GridLayout());
       img= new JLabel(new ImageIcon(endereco+"\\mapa.jpg"));
       JScrollPane scrollImg = new JScrollPane(img);
       painel.add(scrollImg);

       this.add(painel);
       this.setTitle("Mapa - Graças");
       this.setResizable(true);
       this.setSize(1300, 930);
    }


}
