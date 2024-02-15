
package survivalgame.model.equipments;

import java.io.Serializable;
import survivalgame.model.items.ItemEquipavel;
import survivalgame.model.entities.Jogador;

public class Arma extends ItemEquipavel implements Serializable{
 
	private static final long serialVersionUID = 8238467858820907635L;
	
	public static Arma LANCA = new Arma("lanca", (float)2);
    public static Arma FOICE_DE_OSSO = new Arma("foice_de_osso", (float)3);
    public Arma(String nome, float bonus){
        super(nome, bonus);
    } 
    public String usar(Jogador jogador){
        jogador.equiparArma(this);
        return this.getName()+" equipado";
    } 
}
