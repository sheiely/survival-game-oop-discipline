package survivalgame;

import java.io.Serializable;
import survivalgame.model.entities.Jogador;
import survivalgame.model.items.Item;
import survivalgame.model.items.Craftable;
import java.util.ArrayList;
import java.util.Map;
import survivalgame.model.items.ItemEncontravel;


public class Construction extends Craftable implements Serializable{
   
	private static final long serialVersionUID = 6583646896695961199L;
	
	public static Construction TENDA = new Construction("tenda");
    public static Construction FILTRO = new Construction("filtro");
    
    public Construction(String nome){
        super(nome);
    }
    @Override
    public boolean craft(Jogador jogador){
        Map<String, ArrayList<Item>> inventario = jogador.getInventario();
        boolean haveItems = true;
        for(ArrayList<Item> recurso : getRecursos()){
            if(haveItems == true){
                haveItems = false;
                if(inventario.get(recurso.get(0).getName())!=null && inventario.get(recurso.get(0).getName()).size()>= recurso.size()){
                    haveItems = true;
                }
            }
        }
        if(haveItems == true){
            for(ArrayList<Item> recurso : recursos){
                if(recurso.size()< inventario.get(recurso.get(0).getName()).size()){
                    for(int i = 0; i<recurso.size(); i++){
                        inventario.get(recurso.get(0).getName()).remove(0);
                    }
                }else{
                    inventario.remove(recurso.get(0).getName());
                }
                
            }
            return true;
            
        }
        return false;

    }
    
    public String usar(Historia historia) throws CloneNotSupportedException{
        switch(getName()){
            case "tenda":
                int vidaAumentada = (int) (historia.getJogador().getMaxLife() * ((float)0.2));
                historia.getJogador().setLife(historia.getJogador().getLife()+vidaAumentada);
                historia.passarDias();
                return "voce dormiu e recuperou um pouco dos machucados, +"+vidaAumentada+" de vida";
            case "filtro":
                if(historia.getJogador().getInventario().get("agua_barrenta")==null){
                    return "Voce nao tem agua barrenta para filtrar";
                }
                Map<String, ArrayList<Item>> inventario = historia.getJogador().getInventario();
                if(inventario.get("agua_barrenta").size()>1){
                        inventario.get("agua_barrenta").remove(0);

                }else{
                    inventario.remove("agua_barrenta");
                }
                historia.getJogador().addItem(ItemEncontravel.AGUA.clone());
                return "Voce filtrou uma agua";

            }
        return "construcao nao achada";
    }
    @Override
    public Construction clone() throws CloneNotSupportedException{
        return (Construction) super.clone();
    }
}
