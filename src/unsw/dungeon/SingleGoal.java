package unsw.dungeon;

public class SingleGoal implements Goal {

    private boolean complete;

    private String name;

    public SingleGoal(String name) {
        this.complete = false;
        this.name = name;
        
    }
    

    @Override
    public boolean ifTrue() {
        return this.complete;
    }

    public void setValue(boolean ifcomplete) {
        this.complete = ifcomplete;

    }

    @Override
    public void Complete(String name) {
        if (this.name.equals(name)) {
            this.complete = true;
        }
    }

    @Override
    public void addGoal(Goal goal) {
        

    }

    @Override
    public String nameString(){
        if (!ifTrue()){
            switch (name){
                case "enemies":
                    return ">>   Kill all Enemies";
                case "boulders":
                    return ">>   Push boulders to all switchfloors";
                case "exit":
                    return ">>   Getting to the exit";
                case "treasure":
                    return ">>   Collecting all treasures \n!Killing a dragon gives you bonus if it has eaten any!";
    
    
            }
            return "";

        }else {
            return "";

        }
        
    }


}