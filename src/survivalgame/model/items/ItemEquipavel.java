/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package survivalgame.model.items;

import survivalgame.model.entities.Jogador;

/**
 *
 * @author sheie
 */
public abstract class ItemEquipavel extends Craftable{
    private float bonus;
    public ItemEquipavel(String nome, float bonus){
        super(nome);
        this.bonus = bonus;
    }
    public float getBonus(){
        return this.bonus;
    }
    public abstract String usar(Jogador jogador);
}

