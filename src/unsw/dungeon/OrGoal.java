package unsw.dungeon;

import java.util.ArrayList;



public class OrGoal implements Goal {

    private ArrayList<Goal> subgoals;

    public OrGoal(ArrayList<Goal> subgoals) {
        this.subgoals = subgoals;
    }

    public OrGoal() {
        this.subgoals = new ArrayList<Goal>();

    }

    public void addGoal(Goal goal) {
        this.subgoals.add(goal);

    }

    @Override
    public boolean ifTrue() {
        for (Goal goal : this.subgoals) {
            if (goal.ifTrue()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void Complete(String name) {
        for (Goal goal : this.subgoals) {
            goal.Complete(name);
        }

    }

    @Override
    public String nameString() {
        String detail = "Complete one of following goals: \n";
        for (Goal goal : this.subgoals) {
            detail += goal.nameString() + "\n";
            
        }
        return detail;
    }


}