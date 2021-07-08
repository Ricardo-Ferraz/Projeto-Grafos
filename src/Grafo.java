
import java.io.IOException;
import java.util.*;

public class Grafo {

    LinkedList<Vertice> listaVertices, listaFarmacias;
    LinkedList<Aresta> listaArestas;

    public Grafo(String arqVertice, String arqAresta) throws IOException{
        this.listaVertices= new LinkedList<>();
        this.listaArestas= new LinkedList<>();
        this.listaFarmacias = new LinkedList<>();
        lerArquivos(arqVertice, arqAresta);
    }

    private void lerArquivos(String arqVertice, String arqAresta) throws IOException{
        LeituraArquivo lerA, lerV;
        lerA = new LeituraArquivo(arqAresta, 5);
        lerV = new LeituraArquivo(arqVertice, 3);
        String[] vertice = lerV.divide();
        //vertice[0] = deleteMagic(vertice[0]);
        while(vertice != null){
            adicionaVertice(Integer.parseInt(vertice[0]), vertice[1], vertice[2]);
            if(Integer.parseInt(vertice[0]) > 1500){
                listaFarmacias.add(new Vertice(Integer.parseInt(vertice[0]), vertice[1], vertice[2]));
            }
            vertice= lerV.divide();
        }
        String[] aresta = lerA.divide();
        //aresta[0]= deleteMagic(aresta[0]);
        while(aresta != null){
            adicionaAresta(Integer.parseInt(aresta[0]),Integer.parseInt(aresta[1]),Integer.parseInt(aresta[2]),Integer.parseInt(aresta[3]), Boolean.parseBoolean(aresta[4]));
            /*if(!Boolean.parseBoolean(aresta[3])){
                adicionaAresta(-Integer.parseInt(aresta[0]),Integer.parseInt(aresta[2]),Integer.parseInt(aresta[1]),Boolean.parseBoolean(aresta[3]));
            }*/

            aresta= lerA.divide();
        }
    }
/*
    private String deleteMagic(String s){ //Funcao para deletar o caracter "magico" que aparece em arquivos vindos do excel
        StringBuilder retorno = new StringBuilder();
        for(int j= 1; j < s.length(); j++){
            retorno.append(s.charAt(j));
        }
        return retorno.toString();
    }
*/
    public void adicionaVertice(int id, String nome, String cor){
        if(buscaVertice(id) == -1){
            this.listaVertices.add(new Vertice(id, nome, cor));
        }
        else{
            System.out.println("Vertice de Id "+id+" jÃ¡ existe!\n");
        }
    }

    public void adicionaAresta(int id, int idVerticeOrigem, int idVerticeDestino, int peso, boolean isDirecionado){
        Vertice origem= null;
        Vertice destino= null;

        if(buscaAresta(id) == -1){
            for(int i=0; i < this.listaVertices.size(); i++){
                if(this.listaVertices.get(i).getId() == idVerticeOrigem){
                    origem= this.listaVertices.get(i);
                    break;
                }
            }

            for(int i=0; i < this.listaVertices.size(); i++){
                if(this.listaVertices.get(i).getId() == idVerticeDestino){
                    destino= this.listaVertices.get(i);
                    break;
                }
            }

            if(destino == null || origem == null){
                //LanÃ§a uma exceÃ§Ã£o aqui
            }
            else{
                Aresta aux = new Aresta(id, origem, destino, peso, isDirecionado);
                origem.getArestas().add(aux);
                this.listaArestas.add(new Aresta(id, origem, destino, peso, isDirecionado));
            }
        }
        else{
            System.out.println("Aresta com id "+id+" jÃ¡ existe!\n");
        }
    }

    public void exibeVertice(){
        for(int i=0; i < listaVertices.size(); i++){
            System.out.println(listaVertices.get(i));
        }
    }

    public void exibeAresta(){
        for(int i=0; i < listaArestas.size(); i++){
            System.out.println(listaArestas.get(i));
        }
    }

    public LinkedList<Vertice> getVertices() {
        return listaVertices;
    }

    public LinkedList<Aresta> getArestas() {
        return listaArestas;
    }

    private int buscaVertice(int id){
        for(int i=0; i < listaVertices.size(); i++){
            if(listaVertices.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }

    private int buscaAresta(int id){
        for(int i=0; i < listaArestas.size(); i++){
            if(listaArestas.get(i).getId() == id){
                return i;
            }
        }
        return -1;
    }

    private Vertice procuraBranco(){
        for(int i=0; i < listaVertices.size(); i++){
            if(listaVertices.get(i).getCor().equalsIgnoreCase("Branco")){
                return listaVertices.get(i);
            }
        }
        return null;
    }

    public Vertice buscaLargura(int id, int idInicial){
        int posInicial = buscaVertice(idInicial);
        if(posInicial == -1){
            return null; //lanÃ§ar exception?
        }
        for(int i = 0; i < listaVertices.size(); i++){
            listaVertices.get(i).setCor("branco");
        }
        Deque<Vertice> fila = new ArrayDeque<>();
        Vertice inicial = listaVertices.get(posInicial);
        boolean check=false;

        volta:
        while(true) {
            Vertice u, tmp, aux;
            StringBuilder build;
            inicial.setCor("cinza");
            fila.addFirst(inicial);
            while (!fila.isEmpty()) {
                u = fila.removeFirst();
                if (u.getId() == id) {
                    if(check){
                        System.out.println("Iniciando na posiÃ§Ã£o "+idInicial+" nÃ£o Ã© possÃ­vel alcanÃ§ar o objetivo("+id+")\n");
                        return u;
                    }
                    else{
                        int cont = -1;
                        aux = u;
                        build = new StringBuilder();
                        do{
                            build.insert(0, aux.getId()+" ");
                            aux = aux.getAnterior();
                            cont++;
                        }while(aux != null);
                        System.out.println("Seguindo o caminho: "+build.toString());
                        System.out.println("iniciando na posiÃ§Ã£o "+idInicial+", alcanÃ§ou o objetivo("+id+") em "+cont+" passos!\n");
                        return u;
                    }
                }
                for (int i = 0; i < u.getArestas().size(); i++) {
                    tmp = u.getArestas().get(i).getVerticeDestino();
                    if (tmp.getCor().equalsIgnoreCase("branco")) {
                        tmp.setCor("cinza");
                        fila.addLast(tmp);
                        tmp.setAnterior(u);
                    }
                }
                u.setCor("preto");
            }
            aux = procuraBranco();
            if (aux != null) {
                inicial = aux;
                check= true;
                continue volta;
            }
            return null;
        }
    }

    public String menorCaminho(int idInicial, int idFim){
        int posInicial = buscaVertice(idInicial);
        StringBuilder build = new StringBuilder();
        if(posInicial == -1){
            //System.out.println("Vértice inicial não encontrado");
            build.append("Vértice inicial não encontrado.");
        }else if(idInicial == idFim){
            //System.out.println("O vértice inicial é o mesmo final, ditancia do caminho mais curto é 0");
            build.append("O vértice inicial é o mesmo vértice final, ditancia do caminho mais curto é 0");
        }else{
            Vertice auxV;
            for(int i = 0; i < listaVertices.size(); i++){
                auxV = listaVertices.get(i);
                auxV.setDistancia(Integer.MAX_VALUE/2);
                auxV.setAnterior(null);
                auxV.setCor("branco");
            }
            List<Vertice> abertos = new LinkedList<>();
            //Deque<Vertice> fila = new ArrayDeque<>();
            Vertice inicial = listaVertices.get(posInicial);
            inicial.setDistancia(0);
            Vertice current = inicial;
            abertos.add(current);
            Iterator<Aresta> iterAresta;
            Iterator<Vertice> iterVertice;
            Aresta tempA;
            int valor;
            do{
                current.setCor("preto");
                abertos.remove(current);
                iterAresta = current.getArestas().iterator();
                while(iterAresta.hasNext()){
                    tempA = iterAresta.next();
                    auxV = tempA.getVerticeDestino();
                    valor = current.getDistancia() + tempA.getPeso();
                    if(auxV.getDistancia() > valor){
                        auxV.setDistancia(valor);
                        auxV.setAnterior(current);
                    }
                    if(auxV.getCor().equalsIgnoreCase("branco")){
                        abertos.add(auxV);
                        auxV.setCor("cinza");
                    }
                }
                iterVertice = abertos.iterator();
                current = null;
                while(iterVertice.hasNext()){
                    auxV = iterVertice.next();
                    if(current == null || auxV.getDistancia() < current.getDistancia()){
                        current = auxV;
                    }
                }
            }while(abertos.size() > 0 && current.getId() != idFim);   //caso de nao encontrar o destino?
            if(current != null && current.getId() == idFim){
                //System.out.println("Destino alcançado em um percurso de "+current.getDistancia()+" metros");
                String prev;
                StringBuilder verticesPercorridos = new StringBuilder();
                auxV = current;
                build.insert(0, auxV.getNome());
                verticesPercorridos.insert(0, auxV.getId());
                while(auxV.getAnterior() != null){
                    prev = auxV.getNome();
                    auxV = auxV.getAnterior();
                    verticesPercorridos.insert(0, auxV.getId()+", ");
                    if(!auxV.getNome().equalsIgnoreCase(prev)){
                        build.insert(0, auxV.getNome()+", \n");
                    }
                }
                //System.out.println("Segue o caminho percorrido: \n"+build.toString());
                build.insert(0, "Destino alcançado em um percurso de "+current.getDistancia()+" metros. \nSegue o caminho percorrido: \n");
                build.append("\nSegue os vértices percorridos:"+"\n"+verticesPercorridos.toString());
            }else{
                //System.out.println("Não foi possível chegar no endereço desejado por qualquer caminho conhecido.");
                build.insert(0, "Não foi possível chegar no endereço desejado por qualquer caminho conhecido.");
            }
        }
        return build.toString();
    }

    public boolean isFarmacia(int id){
        Iterator<Vertice> iter = listaFarmacias.iterator();
        while(iter.hasNext()){
            if(iter.next().getId() == id){
                return true;
            }
        }
        return false;
    }

    public String farmaciaMaisProxima(int idInicial){
        StringBuilder build = new StringBuilder();
        String msg = "";
        int posInicial = buscaVertice(idInicial);
        if(posInicial == -1) {
            //System.out.println("Vértice inicial não encontrado");
            build.append("Vértice inicial não encontrado");
        }else {
            if(isFarmacia(idInicial)){
            msg = "O vértice inicial já é uma farmácia, mas assumindo que você queira outra, segue: \n";
            }
        
            Vertice auxV;
            for(int i = 0; i < listaVertices.size(); i++){
                auxV = listaVertices.get(i);
                auxV.setDistancia(Integer.MAX_VALUE/2);
                auxV.setAnterior(null);
                auxV.setCor("branco");
            }
            List<Vertice> abertos = new LinkedList<>();
            //Deque<Vertice> fila = new ArrayDeque<>();
            Vertice inicial = listaVertices.get(posInicial);
            inicial.setDistancia(0);
            Vertice current = inicial;
            abertos.add(current);
            Iterator<Aresta> iterAresta;
            Iterator<Vertice> iterVertice;
            Aresta tempA;
            int valor;
            do{
                current.setCor("preto");
                abertos.remove(current);
                iterAresta = current.getArestas().iterator();
                while(iterAresta.hasNext()){
                    tempA = iterAresta.next();
                    auxV = tempA.getVerticeDestino();
                    valor = current.getDistancia() + tempA.getPeso();
                    if(auxV.getDistancia() > valor){
                        auxV.setDistancia(valor);
                        auxV.setAnterior(current);
                    }
                    if(auxV.getCor().equalsIgnoreCase("branco")){
                        abertos.add(auxV);
                        auxV.setCor("cinza");
                    }
                }
                iterVertice = abertos.iterator();
                current = null;
                while(iterVertice.hasNext()){
                    auxV = iterVertice.next();
                    if(current == null || auxV.getDistancia() < current.getDistancia()){
                        current = auxV;
                    }
                }
            }while(abertos.size() > 0 && !isFarmacia(current.getId()));   //caso de nao encontrar o destino?
            if(current != null && isFarmacia(current.getId())){
                //System.out.println("Farmácia encontrada em um percurso de "+current.getDistancia()+" metros");
                String prev;
                StringBuilder verticesPercorridos = new StringBuilder();
                auxV = current;
                build.insert(0, auxV.getNome());
                verticesPercorridos.insert(0, auxV.getId());
                while(auxV.getAnterior() != null){
                    prev = auxV.getNome();
                    auxV = auxV.getAnterior();
                    verticesPercorridos.insert(0, auxV.getId()+", ");
                    if(!auxV.getNome().equalsIgnoreCase(prev)){
                        build.insert(0, auxV.getNome()+", \n");
                    }
                }
                //System.out.println("Segue o caminho percorrido: \n"+build.toString());
                build.insert(0, "Farmácia encontrada em um percurso de "+current.getDistancia()+" metros. \nSegue o caminho percorrido: \n");
                build.append("\nSegue os vértices percorridos:"+"\n"+verticesPercorridos.toString());
            }else{
                //System.out.println("Não foi possível encontrar uma farmácia por qualquer caminho conhecido.");
                build.insert(0, "Não foi possível encontrar uma farmácia por qualquer caminho conhecido. \n");
            }
        }
        return msg+build.toString();
    }

    @Override
    public String toString(){
        String retorno = "";
        boolean check;
        for (int i=0; i < listaVertices.size(); i++) {
            retorno = retorno + listaVertices.get(i).getNome() +"("+listaVertices.get(i).getId()+", "+listaVertices.get(i).getCor()+")"+ " = {";
            check = false;
            for (int j=0; j < listaArestas.size(); j++) {
                if(listaArestas.get(j).getVerticeOrigem().getId() == listaVertices.get(i).getId()){
                    if(check) {
                        retorno += "; ";
                    }
                    else{
                        check = true;
                    }
                    retorno = retorno + listaArestas.get(j).getVerticeDestino().getNome() +"("+listaArestas.get(j).getId()/*getVerticeDestino().getId()*/+", "+listaArestas.get(j).isDirecionado()+")";
                }
            }
            retorno = retorno + "} \n";
        }
        return retorno;
    }
}
