package unsw.dungeon;

public class Portal extends Entity implements Item {
    private Portal pair;
    private Dungeon dungeon;
    private int id;
    private int num;
    public Portal (int x, int y,int id,int num){
        super(x,y);
        this.id = id;
        this.num = num; 
        super.setType("portal");
        this.pair = null;
        super.toremove = false;

    }

    public int getID(){
        return this.id;
    }

    public void findPair(int id){       
        for (Entity e : this.dungeon.getAllEntities() ){
            if (e instanceof Portal ){
                Portal p = (Portal) e ;
                if(p.getID() == id){
                    setPair(p); 
                    p.setPair(this);
                } 
            }
        }    
    }

    public void setDungeon(Dungeon d){
        this.dungeon = d;       
    }

    public void setPair(Portal portal){
        this.pair = portal;
        System.out.println("destny:" + portal.getX() + portal.getY());       
    }

    @Override
    public  boolean ifmeet(int player_x,int player_y){
        if(this.pair == null){
            findPair(id);
        }
    
        if(this.getX() == player_x && this.getY() == player_y){
            System.out.println("Yes" + player_x + player_y);            
            return true;
        }else{
            return false;
        } 
    }
    
    @Override
    public void function(Player p){  
        p.setTextP("Portal can translate player to another portal");
        p.transport(pair.getX()+1,pair.getY());
    } 
}