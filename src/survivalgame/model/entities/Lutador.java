/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package survivalgame.model.entities;


public class Lutador{

  
    public void setCriticalChance(int criticalChance) {
        this.criticalChance = criticalChance;
    }
    private int life;
    private int maxLife;
    private int ataque;
    private int criticalChance;
    public Lutador(int life, int ataque, int criticalChance){
        this.life = life;
        this.maxLife = life;
        this.ataque = ataque;
        this.criticalChance = criticalChance;
    }
    public Lutador(){
    }
    public int getLife(){
        return this.life;
    }
    public int getMaxLife(){
        return this.maxLife;
    }
    public int getAtaque(){
        return this.ataque;
    }
    public int getCriticalChance(){
        return this.criticalChance;
    }
    public void setLife(int life){
        if(life < 0){
            life = 0;
        }
        if(life > getMaxLife()){
            life = getMaxLife();
        }
        this.life = life;
    }
    public void setAtaque(int ataque){
        this.ataque = ataque;
    }
    public boolean isAlive(){
        boolean isAlive = true;
        if(life <= 0){
            isAlive = false;
        }
        return isAlive;
    }
}