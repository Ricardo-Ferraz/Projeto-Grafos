import java.io.IOException;


public class Main {

    public static void main(String[] args){
        String arqVertice, arqAresta;
        String endereco = "C:\\Users\\ricar\\Desktop\\Projeto-Grafos\\src"; //Colocar o caminho da pasta
        arqVertice = endereco+"\\arquivoVertice.csv"; //Colocar o caminho do arquivo
        arqAresta = endereco+"\\arquivoAresta.csv"; //Colocar o caminho do arquivo
        Grafo grafo = null;
        try {
            grafo = new Grafo(arqVertice, arqAresta);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }

        JanelaPrincipal janela = new JanelaPrincipal(grafo,endereco);

    }
}
