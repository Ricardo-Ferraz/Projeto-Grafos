

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LeituraArquivo {
    private String[] array;
    private BufferedReader buffer;

    public LeituraArquivo(String arquivo, int qtdColunas) throws FileNotFoundException{
        buffer = new BufferedReader(new FileReader(arquivo));
        array = new String[qtdColunas];
    }

    public String lerLinha() throws IOException{
        return buffer.readLine();
    }

    public String[] divide() throws IOException{
        String aux= lerLinha();
        if(aux == null){
            return null;
        }
        int cont = 0;
        for(int j = 0; j < array.length; j++){
            array[j] = "";
            for(int i=cont; i < aux.length(); i++){
                if(aux.charAt(i) == ';'){
                    cont++;
                    break;
                }
                else{
                    cont++;
                    array[j]= array[j]+aux.charAt(i);
                }
            }
        }
        return array; //retorno[0]= id da aresta ; retorno[1]= id do vertice de origem ; retorno[2]= id do vertice de destino; retorno[3]= true ou false do direcionado
    }
}
