/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package survivalgame.model.items;

import java.io.Serializable;
import java.util.ArrayList;
import survivalgame.model.entities.Jogador;

/**
 *
 * @author aluno
 */



public abstract class  Item implements Cloneable, Serializable{
     
    protected String nome;
    
    public Item(String nome){
        this.nome = nome;
       
    }
    public Item(){
    }
    public String getName(){
        return this.nome;
    }
    
    public Item clone() throws CloneNotSupportedException{
        return (Item) super.clone();
    }
    public abstract String usar(Jogador jogador);
}

