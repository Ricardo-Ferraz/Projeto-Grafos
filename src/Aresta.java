
public class Aresta {

    private int id, peso;
    private Vertice verticeOrigem;
    private Vertice verticeDestino;
    private boolean isDirecionado;

    public Aresta(int id, Vertice verticeOrigem, Vertice verticeDestino, int peso, boolean isDirecionado){
        this.id= id;
        this.verticeOrigem= verticeOrigem;
        this.verticeDestino= verticeDestino;
        this.isDirecionado= isDirecionado;
        this.peso = peso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vertice getVerticeOrigem() {
        return verticeOrigem;
    }

    public void setVerticeOrigem(Vertice verticeOrigem) {
        this.verticeOrigem = verticeOrigem;
    }

    public Vertice getVerticeDestino() {
        return verticeDestino;
    }

    public void setVerticeDestino(Vertice verticeDestino) {
        this.verticeDestino = verticeDestino;
    }

    public boolean isDirecionado() {
        return isDirecionado;
    }

    public void setDirecionado(boolean direcionado) {
        isDirecionado = direcionado;
    }

    public int getPeso(){
        return this.peso;
    }

    public void setPeso(int peso){
        this.peso = peso;
    }

    @Override
    public String toString(){
        return "ID: "+this.id+" \n"+
                "Vertice de Origem: "+this.verticeOrigem.getId()+" ("+this.getVerticeOrigem().getNome()+")\n"+
                "Vertice de Destino: "+this.verticeDestino.getId()+" ("+this.getVerticeDestino().getNome()+")\n"+
                "É direcionado? "+this.isDirecionado+"\n";
    }

}
