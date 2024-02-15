/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package survivalgame.model.equipments;

import java.io.Serializable;
import survivalgame.model.items.ItemEquipavel;
import survivalgame.model.entities.Jogador;

public class Bota extends ItemEquipavel implements Serializable{
   
	private static final long serialVersionUID = 1059084567505438118L;
	
	public static Bota BOTA_DE_PELE = new Bota("bota_de_pele", (float)1.5);
    public static Bota BOTA_DE_OSSO = new Bota("bota_de_osso", (float)2);
    public Bota(String nome, float bonus){
        super(nome, bonus);
    } 
    public String usar(Jogador jogador){
        jogador.equiparBota(this);
        return this.getName()+" equipado";
    } 
}

