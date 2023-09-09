package unsw.dungeon;

import javafx.beans.property.StringProperty;

public interface Goal {

    public boolean ifTrue();

    public void Complete(String name);

    public void addGoal(Goal goal);

    public String nameString();


}