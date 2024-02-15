
package survivalgame.model.entities;

import java.io.Serializable;
import survivalgame.model.items.Item;
import survivalgame.model.equipments.Arma;
import survivalgame.model.equipments.Bota;
import survivalgame.model.equipments.Chapeu;
import survivalgame.model.equipments.Roupa;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Jogador extends Lutador implements Serializable{

    private static final long serialVersionUID = -359782232018935750L;
    
    private int fome;
    private int sede;
    private int maxFome;
    private int maxSede;
    
    private Chapeu chapeu;
    private Roupa roupa;
    private Bota bota;
    private Arma arma;
    
    
    private Map<String, ArrayList<Item>> inventario;
    
    public Jogador(int life){
        super(life, 2, 5);
        inventario = new HashMap<String, ArrayList<Item>>();
        maxFome = 20;
        maxSede = 5;
        fome = 20;
        sede = 5;
        
    }
    public Jogador(){
        super();
    }
  
    public int getMaxFome(){
        return this.maxFome;
    }
    public int getMaxSede(){
        return this.maxSede;
    }
    public void setFome(int fome){
        if(fome <= 0){
            this.fome =0;
            setLife(0);
        }
        if(fome < 0){ fome = 0;}
        if(fome > maxFome){ fome = maxFome; }
        this.fome = fome;
    }
    public void setSede(int sede){
        if(sede <= 0){
            this.sede = 0;
  
            setLife(0);
            
        }
        if(sede < 0){ sede = 0;}
        if(sede > maxSede){ sede = maxSede; }
        this.sede = sede;
    }
    public int getFome(){
        return fome;
    }
    public int getSede(){
        return sede;
    }
    public void addItem(Item item){
        if(item != null){
            if(inventario.get(item.getName())!=null){
                inventario.get(item.getName()).add(item);
            }else{
                ArrayList<Item> itemArray = new ArrayList<Item>();
                itemArray.add(item);
                inventario.put(item.getName(), itemArray);
            }
        }
        
    }
    public Map<String, ArrayList<Item>> getInventario(){
        return inventario;
    }
    
    
    @Override
    public int getMaxLife(){
        float newMaxLife = super.getMaxLife();
        if(chapeu!=null){
            newMaxLife *= chapeu.getBonus();
        }
        if(roupa!=null){
            newMaxLife *= roupa.getBonus();
        }
        if(bota!=null){
            newMaxLife *= bota.getBonus();
        }
        return (int) Math.ceil(newMaxLife);
    }
    @Override
    public int getAtaque(){
        float newAtaque = super.getAtaque();
        if(arma!=null){
            newAtaque *= arma.getBonus();
        }
        
        return (int) Math.ceil(newAtaque);
    }
    public int getPureAtaque(){
        return super.getAtaque();
    }
    
    public void equiparChapeu(Chapeu chapeu){
        addItem(this.chapeu);
        this.setChapeu(chapeu);
        
    }
    public void equiparRoupa(Roupa roupa){
        addItem(this.roupa);
        this.setRoupa(roupa);
    }
    public void equiparBota(Bota bota){
        addItem(this.bota);
        this.setBota(bota);
    }
    public void equiparArma(Arma arma){
        addItem(this.arma);
        this.setArma(arma);
    }
    public Chapeu getChapeu(){
        return this.chapeu;
    }
    public Arma getArma(){
        return this.arma;
    }
    public Roupa getRoupa(){
        return this.roupa;
    }
    public Bota getBota(){
        return this.bota;
    }
    public void setChapeu(Chapeu chapeu) {
        this.chapeu = chapeu;
    }
    public void setRoupa(Roupa roupa) {
        this.roupa = roupa;
    }
    public void setBota(Bota bota) {
        this.bota = bota;
    }
    public void setArma(Arma arma) {
        this.arma = arma;
    }

    @Override
    public void setLife(int life){
        if(life <= 0){
            life = 0;
            
        }
        if(life > getMaxLife()){
            life = getMaxLife();
        }
        super.setLife(life);
    }
    public void setInventario(Map<String, ArrayList<Item>> inventario) {
        this.inventario = inventario;
    }
}
