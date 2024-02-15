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
public class Roupa extends ItemEquipavel implements Serializable{

	private static final long serialVersionUID = -7492099203142729429L;
	public static Roupa CASACO_DE_PELE = new Roupa("casaco_de_pele", (float)2);
    public static Roupa CASACO_DE_OSSO = new Roupa("casaco_de_osso", (float)2.5);
    public Roupa(String nome, float bonus){
        super(nome, bonus);
    } 
    @Override
    public String usar(Jogador jogador){
        jogador.equiparRoupa(this);
        return this.getName()+" equipado";
    } 
}
