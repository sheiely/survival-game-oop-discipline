
package survivalgame.model.entities;

import survivalgame.model.items.Item;
import survivalgame.model.items.ItemEncontravel;
import java.util.ArrayList;
import java.util.Random;

public class Inimigo extends Lutador implements Cloneable{
    public static Inimigo LOBO = new Inimigo("lobo", 72, 20,2, 10);
    public static Inimigo COELHO = new Inimigo("coelho", 20, 5,1, 10);
    public static Inimigo CRIATURA = new Inimigo("criatura",8,50, 15, 25);
    private ArrayList<ItemEncontravel> drops = new ArrayList<>();
    private String nome;
    private int chance;
    
    public Inimigo(String nome, int chance, int life, int ataque, int criticalChance){
        super(life, ataque, criticalChance);
        this.nome = nome;
        this.chance = chance;
        switch(nome){
            case "lobo":
                drops.add(ItemEncontravel.PELE);
                drops.add(ItemEncontravel.DENTE);
                break;
            case "coelho":
                drops.add(ItemEncontravel.PELE);
                drops.add(ItemEncontravel.CARNE);
                break;
        }
    }
    public String getName(){
        return this.nome;
    }
    public int getChance(){
        return this.chance;
    }
    
    @Override
    public Inimigo clone() throws CloneNotSupportedException{
        return (Inimigo) super.clone();
    }
    public Item gerarDrop() throws CloneNotSupportedException{
        Random rand = new Random();
        int totalChance = 100;
        int[] probabilidade = new int[totalChance];
        int probabilidadeIndex =0;
        for(int i = 0; i <totalChance; i++){
            probabilidade[i] = -1;
        }
        for(int i = 0; i <drops.size(); i++){
            for(int f = 0; f <drops.get(i).getChance(); f++){
                probabilidade[probabilidadeIndex] = i;
                probabilidadeIndex++;
            }
        }
        int randomico = probabilidade[rand.nextInt(totalChance)];
        if(randomico == -1){
            return null;
        }
        return drops.get(randomico).clone();
    }
}
