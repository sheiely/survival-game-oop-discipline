
package survivalgame.model.items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import survivalgame.model.entities.Jogador;




public class  Craftable extends Item implements Serializable{
   
	private static final long serialVersionUID = 3826906563620301679L;
	public static Craftable CANTIL = new Craftable("cantil");
    protected ArrayList<ArrayList<Item>> recursos;
    
    public Craftable(String nome){
        
        super(nome);
        this.recursos = new ArrayList<>();
        ArrayList<Item> recursos = new ArrayList<>();
        switch(nome){
            
            case "chapeu_de_pele":
                recursos.add(ItemEncontravel.PELE);
                recursos.add(ItemEncontravel.PELE);
                this.recursos.add(recursos);
                break;
            case "casaco_de_pele":
                recursos.add(ItemEncontravel.PELE);
                recursos.add(ItemEncontravel.PELE);
                recursos.add(ItemEncontravel.PELE);
                recursos.add(ItemEncontravel.PELE);
                this.recursos.add(recursos);
                break;
            case "bota_de_pele":
                recursos.add(ItemEncontravel.PELE);
                recursos.add(ItemEncontravel.PELE);
                this.recursos.add(recursos);
                break;
            case "capacete_de_osso":
                recursos.add(ItemEncontravel.PELE);
                recursos.add(ItemEncontravel.PELE);
                recursos.add(ItemEncontravel.PELE);
                this.recursos.add((ArrayList<Item>) recursos.clone());
                recursos.removeAll(recursos);
                recursos.add(ItemEncontravel.DENTE);
                recursos.add(ItemEncontravel.DENTE);
                recursos.add(ItemEncontravel.DENTE);
                recursos.add(ItemEncontravel.DENTE);
                this.recursos.add(recursos);
                break;
            case "casaco_de_osso":
                recursos.add(ItemEncontravel.PELE);
                recursos.add(ItemEncontravel.PELE);
                recursos.add(ItemEncontravel.PELE);
                recursos.add(ItemEncontravel.PELE);
                recursos.add(ItemEncontravel.PELE);
                this.recursos.add((ArrayList<Item>) recursos.clone());
                recursos.removeAll(recursos);
                recursos.add(ItemEncontravel.DENTE);
                recursos.add(ItemEncontravel.DENTE);
                recursos.add(ItemEncontravel.DENTE);
                recursos.add(ItemEncontravel.DENTE);
                recursos.add(ItemEncontravel.DENTE);
                recursos.add(ItemEncontravel.DENTE);
                recursos.add(ItemEncontravel.DENTE);
                
                this.recursos.add(recursos);
                
                break;
            case "bota_de_osso":
                recursos.add(ItemEncontravel.PELE);
                recursos.add(ItemEncontravel.PELE);
                recursos.add(ItemEncontravel.PELE);
                this.recursos.add((ArrayList<Item>) recursos.clone());
                recursos.removeAll(recursos);
                recursos.add(ItemEncontravel.DENTE);
                recursos.add(ItemEncontravel.DENTE);
                recursos.add(ItemEncontravel.DENTE);
                this.recursos.add((ArrayList<Item>) recursos.clone());
                recursos.removeAll(recursos);
                recursos.add(ItemEncontravel.FIBRA);
                this.recursos.add(recursos);
                break;
            case "lanca":
                recursos.add(ItemEncontravel.MADEIRA);
                recursos.add(ItemEncontravel.MADEIRA);
                recursos.add(ItemEncontravel.MADEIRA);
                this.recursos.add((ArrayList<Item>) recursos.clone());
                recursos.removeAll(recursos);
                recursos.add(ItemEncontravel.FIBRA);
                this.recursos.add(recursos);
                break;
            case "foice_de_osso":
                recursos.add(ItemEncontravel.MADEIRA);
                recursos.add(ItemEncontravel.MADEIRA);
                recursos.add(ItemEncontravel.MADEIRA);
                this.recursos.add((ArrayList<Item>) recursos.clone());
                recursos.removeAll(recursos);
                recursos.add(ItemEncontravel.DENTE);
                recursos.add(ItemEncontravel.DENTE);
                recursos.add(ItemEncontravel.DENTE);
                this.recursos.add((ArrayList<Item>) recursos.clone());
                recursos.removeAll(recursos);
                recursos.add(ItemEncontravel.FIBRA);
                this.recursos.add(recursos);
                break;
            case "tenda":
                recursos.add(ItemEncontravel.MADEIRA);
                recursos.add(ItemEncontravel.MADEIRA);
                this.recursos.add((ArrayList<Item>) recursos.clone());
                recursos.removeAll(recursos);
                recursos.add(ItemEncontravel.FIBRA);
                recursos.add(ItemEncontravel.FIBRA);
                this.recursos.add(recursos);
                break;
            case "filtro":
                recursos.add(ItemEncontravel.MADEIRA);
                recursos.add(ItemEncontravel.MADEIRA);
                recursos.add(ItemEncontravel.MADEIRA);
                this.recursos.add((ArrayList<Item>) recursos.clone());
                recursos.removeAll(recursos);
                recursos.add(ItemEncontravel.FIBRA);
                recursos.add(ItemEncontravel.FIBRA);
                this.recursos.add((ArrayList<Item>) recursos.clone());
                break;

        }
        
    }
    public boolean craft(Jogador jogador) throws CloneNotSupportedException{
        Map<String, ArrayList<Item>> inventario = jogador.getInventario();
        boolean haveItems = true;
        for(ArrayList<Item> recurso : recursos){
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
            jogador.addItem(this.clone());
            return true;
        }else{
            return false;
        }

    }
    
    
    public ArrayList<ArrayList<Item>> getRecursos(){
        return this.recursos;
    }
    public Item clone() throws CloneNotSupportedException{
        return (Item) super.clone();
    }
    
    public String usar(Jogador jogador){
        
        return "Esse objeto nao e usavel";
    } 
   
}

