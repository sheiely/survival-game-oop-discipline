/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package survivalgame.model.equipments;

import java.io.Serializable;
import survivalgame.model.items.ItemEquipavel;
import survivalgame.model.entities.Jogador;

/**
 *
 * @author sheie
 */
public class Chapeu extends ItemEquipavel implements Serializable{
  
	private static final long serialVersionUID = -4705371965696307990L;
	
	public static Chapeu CHAPEU_DE_PELE = new Chapeu("chapeu_de_pele", (float)1.5);
    public static Chapeu CAPACETE_DE_OSSO = new Chapeu("capacete_de_osso", (float)2);
    public Chapeu(String nome, float bonus){
        super(nome, bonus);
    } 
    @Override
    public String usar(Jogador jogador){
        jogador.equiparChapeu(this);
        return this.getName()+" equipado";
    } 
}


