
import java.util.LinkedList;

public class Vertice {

    private int id, distancia;
    private String nome;
    private String cor;
    private LinkedList<Aresta> arestas;
    private Vertice anterior;

    public Vertice(int id, String nome, String cor){
        this.id= id;
        this.nome= nome;
        this.cor= cor;
        this.arestas= new LinkedList<>();
        this.anterior = null;
        this.distancia = Integer.MAX_VALUE/2;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCor() {
        return cor;
    }

    public LinkedList<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(LinkedList<Aresta> arestas) {
        this.arestas = arestas;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setAnterior(Vertice v){
        this.anterior = v;
    }

    public Vertice getAnterior(){
        return this.anterior;
    }

    public void setDistancia(int distancia){
        this.distancia = distancia;
    }

    public int getDistancia(){
        return this.distancia;
    }

    private String verticesConectados(){
        StringBuffer aux = new StringBuffer();
        for(int i=0; i < arestas.size(); i++){
            aux.append(arestas.get(i).getVerticeDestino().getId()).append(" ");
        }
        return aux.toString();
    }

    @Override
    public String toString(){
        return "ID: "+this.id+" \n"+
                "Nome: "+this.nome+" \n"+
                "Cor: "+this.cor+"\n" +
                "Vertice ligados: "+this.verticesConectados()+"\n";
    }


}
