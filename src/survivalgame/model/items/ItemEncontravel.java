/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package survivalgame.model.items;

import java.io.Serializable;
import survivalgame.model.entities.Jogador;
import survivalgame.exceptions.MsgException;

/**
 *
 * @author sheie
 */
public class ItemEncontravel extends Item implements Serializable{
    public static ItemEncontravel MACA = new ItemEncontravel("maca", 10);
    public static ItemEncontravel MADEIRA = new ItemEncontravel("madeira", 15);
    public static ItemEncontravel AGUA_BARRENTA = new ItemEncontravel("agua_barrenta", 25);
    public static ItemEncontravel CARNE_PODRE = new ItemEncontravel("carne_podre", 30);
    public static ItemEncontravel FIBRA = new ItemEncontravel("fibra", 15);
    
    public static ItemEncontravel PELE = new ItemEncontravel("pele", 60);
    public static ItemEncontravel DENTE = new ItemEncontravel("dente", 30);
    public static ItemEncontravel CARNE = new ItemEncontravel("carne", 30);
    
    public static ItemEncontravel AGUA = new ItemEncontravel("agua", 0);
    private int chance;
    
    public ItemEncontravel(String nome, int chance){
        super(nome);
        this.chance = chance;
    
    }
    public ItemEncontravel(){
       
    }
    public int getChance(){
        return this.chance;
    }
    public Item clone() throws CloneNotSupportedException{
        return (Item) super.clone();
    }
    
    public String usar(Jogador jogador){
        switch(this.getName()){
            case "maca":
                jogador.setFome(jogador.getFome()+2);
                jogador.setLife(jogador.getLife()+1);
                return "Voce come a maca, estava muito boa, +2 de fome e +1 de vida";
            case "agua_barrenta":
                jogador.setLife(jogador.getLife()-1);
                jogador.setSede(jogador.getSede()+3);
                return "Voce bebe a agua barrenta, isso nao te faz muito bem, -1 de vida e +3 de sede";
            case "carne_podre":
                jogador.setLife(jogador.getLife()-1);
                jogador.setFome(jogador.getFome()+3);
                return "Voce come a carne podre, isso nao te faz muito bem, -1 de vida e +3 de fome";
            case "carne":
                jogador.setFome(jogador.getFome()+3);
                return "Voce come a carne, estava crua, +3 de fome";
            case "agua":
                jogador.setSede(jogador.getSede()+4);
                return "Voce bebe a agua, ela estava pura, +4 de sede";
            default:
                return null;
        }
     
    } 
}
