package unsw.dungeon;

import java.util.ArrayList;


public class AndGoal implements Goal {
    private ArrayList<Goal> subgoals;

    public AndGoal(ArrayList<Goal> subgoals) {
        this.subgoals = subgoals;
    }

    public AndGoal() {
        this.subgoals = new ArrayList<Goal>();

    }
    
    @Override
    public void addGoal(Goal goal) {
        this.subgoals.add(goal);

    }

    @Override
    public boolean ifTrue() {
        for (Goal goal : this.subgoals) {
            if (!goal.ifTrue()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void Complete(String name) {
        for (Goal goal: this.subgoals){
            goal.Complete(name);
        }

    }

    @Override
    public String nameString(){
        String detail = "Complete all following goals: \n";
        for (Goal goal : this.subgoals) {
            detail += goal.nameString() + "\n";
            
        }
        return detail ;
    }


    
}