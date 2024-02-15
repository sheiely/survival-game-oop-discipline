package survivalgame;


import survivalgame.model.entities.Inimigo;
import survivalgame.model.entities.Jogador;
import survivalgame.model.entities.Lutador;
import survivalgame.model.items.Item;
import survivalgame.model.items.ItemEncontravel;
import survivalgame.model.items.Craftable;
import survivalgame.model.equipments.Arma;
import survivalgame.model.equipments.Bota;
import survivalgame.model.equipments.Chapeu;
import survivalgame.model.equipments.Roupa;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import survivalgame.exceptions.MsgException;


public class Historia{
    private Map<String, ItemEncontravel> itensEncontraveis;
    private Map<String, Craftable> craftaveis;
    private Map<String, Construction> construcoes;
    private Map<String, Inimigo> inimigosEncontraveis;
    private Jogador jogador;
    private Random rand = new Random();
    private int dias;
    private int deadCreatures;
    public Historia(){
        jogador = new Jogador(15);
        deadCreatures = 0;
        itensEncontraveis = new HashMap<String, ItemEncontravel>();
        itensEncontraveis.put(ItemEncontravel.MACA.getName(), ItemEncontravel.MACA);
        itensEncontraveis.put(ItemEncontravel.MADEIRA.getName(),ItemEncontravel.MADEIRA);
        itensEncontraveis.put(ItemEncontravel.AGUA_BARRENTA.getName(), ItemEncontravel.AGUA_BARRENTA);
        itensEncontraveis.put(ItemEncontravel.CARNE_PODRE.getName(), ItemEncontravel.CARNE_PODRE);
        itensEncontraveis.put(ItemEncontravel.FIBRA.getName(), ItemEncontravel.FIBRA);
        
        craftaveis = new HashMap<String, Craftable>();
        craftaveis.put(Chapeu.CHAPEU_DE_PELE.getName(), (Craftable)Chapeu.CHAPEU_DE_PELE);
        craftaveis.put(Roupa.CASACO_DE_PELE.getName(), (Craftable)Roupa.CASACO_DE_PELE);
        craftaveis.put(Bota.BOTA_DE_PELE.getName(), (Craftable)Bota.BOTA_DE_PELE);
        craftaveis.put(Chapeu.CAPACETE_DE_OSSO.getName(), (Craftable)Chapeu.CAPACETE_DE_OSSO);
        craftaveis.put(Roupa.CASACO_DE_OSSO.getName(), (Craftable)Roupa.CASACO_DE_OSSO);
        craftaveis.put(Bota.BOTA_DE_OSSO.getName(), (Craftable)Bota.BOTA_DE_OSSO);
        craftaveis.put(Arma.FOICE_DE_OSSO.getName(), (Craftable)Arma.FOICE_DE_OSSO);
        craftaveis.put(Arma.LANCA.getName(), (Craftable)Arma.LANCA);
        craftaveis.put(Construction.TENDA.getName(), Construction.TENDA);
        craftaveis.put(Construction.FILTRO.getName(), Construction.FILTRO);
        
        construcoes = new HashMap<String, Construction>();
       
 
        inimigosEncontraveis= new HashMap<String, Inimigo>();
        inimigosEncontraveis.put(Inimigo.LOBO.getName(), Inimigo.LOBO);
        inimigosEncontraveis.put(Inimigo.COELHO.getName(), Inimigo.COELHO);
        inimigosEncontraveis.put(Inimigo.CRIATURA.getName(), Inimigo.CRIATURA);
        dias = 0;
    }
    
    public String craft(Craftable craftable){
        try{
                    
            if(!craftable.craft(jogador)){
                throw new MsgException("Nao tem itens suficientes");
            }
            if(craftable instanceof Construction){
                construcoes.put(craftable.getName(), (Construction) craftable);
                craftaveis.remove(craftable.getName());
               
            }
             return "Item craftado";
        }catch(Exception e){
        	throw new MsgException(e.getMessage());
        }
      
    }

    
    
    public String useItemFromInventory(ArrayList<Item> arrayDoItem){
        Map<String, ArrayList<Item>> inventario = jogador.getInventario();
        ArrayList<Item> itemSelecionado = arrayDoItem;
        if(itemSelecionado.size()<0){
        	throw new MsgException("Voce nao tem esse item");
        }
        String txt = itemSelecionado.get(0).usar(jogador);
        if(txt == null){
            throw new MsgException("Voce tenta, mas nao acha uma utilidade para isso");
        }
        if(itemSelecionado.size() <= 1){
            inventario.remove(itemSelecionado.get(0).getName());
        }else{
        	itemSelecionado.remove(0);
        }
        return txt;
        
    }
    
    
    
    public ArrayList<String> explore() throws CloneNotSupportedException{
    	ArrayList<String> events = new ArrayList<>();
        events.add("Voce caminha pela floresta...");
        for(int i = 0; i<3; i++){
            if(!jogador.isAlive()){
            	events.add("Voce Morreu e nunca voltou para o acampamento");
            	return events;
            }
            if(rand.nextInt(100) <= 70){
            	Item item = generateFindableItem();
                jogador.addItem(item);
                events.add("Voce encontrou uma "+item.getName());
            }else{
                events.add("gerateFightCode");

            }    
        }
        if(!jogador.isAlive()){
        	events.add("Voce Morreu e nunca voltou para o acampamento");
        	return events;
        }  
        
        events.add("Voce volta em segurança para seu acampamento");

        return events;
        
    }
    
    
    
    
    public Item generateFindableItem() throws CloneNotSupportedException{
        int totalChance = 0;
        for(ItemEncontravel i: itensEncontraveis.values()){
            totalChance += i.getChance();
        }
        String[] probabilidade = new String[totalChance];
        int probabilidadeIndex =0;
       
        for(ItemEncontravel i: itensEncontraveis.values()){
            for(int f = 0; f <i.getChance(); f++){
                probabilidade[probabilidadeIndex] = i.getName();
                probabilidadeIndex++;
            }
            
        }
        int randomico = rand.nextInt(totalChance);
        return itensEncontraveis.get(probabilidade[randomico]).clone();
    }
    public Inimigo generateEnemy() throws CloneNotSupportedException{
        
        int totalChance = 0;
        for(Inimigo i: inimigosEncontraveis.values()){
            totalChance += i.getChance();
        }
        String[] probabilidade = new String[totalChance];
        int probabilidadeIndex =0;
        for(Inimigo i: inimigosEncontraveis.values()){
            for(int f = 0; f <i.getChance(); f++){
                probabilidade[probabilidadeIndex] = i.getName();
                probabilidadeIndex++;
            }
        }
        int randomico = rand.nextInt(totalChance);
        return inimigosEncontraveis.get(probabilidade[randomico]).clone();
    }
    
    
    
    public void lutar(Inimigo inimigo){
        int danoJogador = gerarDano(jogador);
        int danoInimigo = gerarDano(inimigo);
       
        inimigo.setLife(inimigo.getLife()-danoJogador);
        jogador.setLife(jogador.getLife() -danoInimigo);
     
    }
   public int gerarDano(Lutador lutador){
       int dano = lutador.getAtaque();
       if(rand.nextInt(101)<= lutador.getCriticalChance()){
            dano = (int) Math.ceil(dano * 1.5);
        }       
       return dano;
   }
    public Jogador getJogador(){
        return this.jogador;
    }
    public void setJogador(Jogador jogador){
        this.jogador = jogador;
    }
    public int getDias(){
        return this.dias;
    }
    public void setDias(int dias){
        this.dias = dias;
    }
    public void passarDias(){
    	
       
        
        jogador.setFome(jogador.getFome()-1);
        jogador.setSede(jogador.getSede()-1);
        dias++;
        if(!jogador.isAlive()) {
    		System.exit(0);
    		return;
    	}
        try{
            ArrayList<Object> salvar = new ArrayList<>();
            salvar.add(getJogador().getInventario());
            salvar.add(getJogador().getArma());
            salvar.add(getJogador().getBota());
            salvar.add(getJogador().getChapeu());
            salvar.add(getJogador().getRoupa());
            salvar.add(getJogador().getPureAtaque());
            salvar.add(getJogador().getFome());
            salvar.add(getJogador().getSede());
            salvar.add(getJogador().getLife());
            salvar.add(getJogador().getCriticalChance());  
            salvar.add(getConstrucoes());
            salvar.add(getDias());
            salvar.add(this.getDeadCreatures());
            Savealize.gravarArquivoBinario(salvar, "save.dat");
        }catch(Exception e){
            System.out.println("Erro ao salvar jogo: "+e.getMessage());
        }
        
    }
    public Map<String, Construction> getConstrucoes(){
        return this.construcoes;
    }
    public Map<String, Craftable> getCraftables(){
        return this.craftaveis;
    }
    public void setConstrucoes(Map<String, Construction> construcoes){
        this.construcoes = construcoes;
    }

    public int getDeadCreatures(){
        return this.deadCreatures;
    }
    public void setDeadCreatures(int deadCreatures){
        this.deadCreatures = deadCreatures;
    }
}






