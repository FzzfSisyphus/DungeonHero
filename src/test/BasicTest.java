package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import unsw.dungeon.*;

public class BasicTest {
    // Test constructing dungeon with specified width and height
    @Test
    public void DungeonTest(){
        Dungeon d = new Dungeon(1, 2);
        assertEquals(d.getWidth(), 1);
        assertEquals(d.getHeight(), 2);
    }

    // Test Wall blocking player
    @Test
    public void BasicPlayerTest(){
        Dungeon d = new Dungeon(5,4);
        Wall w1 = new Wall(0,1);
        d.addEntity(w1);
        assertEquals(d.getEntity(0,1), new ArrayList<>(Arrays.asList(w1)));
        Wall w2 = new Wall(0,2);
        d.addEntity(w2);

        Wall w3 = new Wall(0,3);
        d.addEntity(w3);

        Wall w4 = new Wall(1,3);
        d.addEntity(w4);

        Wall w5 = new Wall(2,0);
        d.addEntity(w5);

        Wall w6 = new Wall(3,0);
        d.addEntity(w6);

        Wall w7 = new Wall(4,0);
        d.addEntity(w7);

        Wall w8 = new Wall(4,1);
        d.addEntity(w8);

        Wall w9 = new Wall(4,2);
        d.addEntity(w9);
        assertEquals(d.getEntity(4,2), new ArrayList<>(Arrays.asList(w9)));

        Player p = new Player(d, 0, 0);
        d.setPlayer(p);
        assertEquals(d.getPlayer(), p);

        p.moveLeft();
        assertEquals(p.getX(), 0);
        assertEquals(p.getY(), 0);

        p.moveDown();
        assertEquals(p.getX(), 0);
        assertEquals(p.getY(), 0);

        p.moveRight();
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 0);

        p.moveRight();
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 0);

        p.moveDown();
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 1);

        p.moveRight();
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 1);


    }
    @Test
    public void BasicTreasureTest(){
        // same configuration as BasicPlayerTest
        Dungeon d = new Dungeon(5,4);
        Wall w1 = new Wall(0,1);
        d.addEntity(w1);
        Wall w2 = new Wall(0,2);
        d.addEntity(w2);

        Wall w3 = new Wall(0,3);
        d.addEntity(w3);

        Wall w4 = new Wall(1,3);
        d.addEntity(w4);

        Wall w5 = new Wall(2,0);
        d.addEntity(w5);

        Wall w6 = new Wall(3,0);
        d.addEntity(w6);

        Wall w7 = new Wall(4,0);
        d.addEntity(w7);

        Wall w8 = new Wall(4,1);
        d.addEntity(w8);

        Wall w9 = new Wall(4,2);
        d.addEntity(w9);


        Player p = new Player(d, 1, 2);
        d.setPlayer(p);


        Treasure t = new Treasure(2,2);
        d.addEntity(t);
        Treasure t2 = new Treasure(3,2);
        d.addEntity(t2);
        assertEquals(d.getEntity(2,2), new ArrayList<>(Arrays.asList(t)));

        //Now player should be in (2,2)
        p.moveRight();
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 2); 

        //Now treasure should be gone
        assertEquals(d.getEntity(2,2).isEmpty(), true);

        //now player should have 500 value for treasure 
        assertEquals(p.getTreasure(), 500);

        p.moveRight();
        assertEquals(p.getX(), 3);
        assertEquals(p.getY(), 2);

        //Now treasure should be gone
        assertEquals(d.getEntity(3,2).isEmpty(), true);

        //now player should have 500 value for treasure 
        assertEquals(p.getTreasure(), 1000);



    
    }

    @Test
    public void BasicKeyTest(){
        // same configuration as BasicPlayerTest
        Dungeon d = new Dungeon(5,4);
        Wall w1 = new Wall(0,1);
        d.addEntity(w1);
        Wall w2 = new Wall(0,2);
        d.addEntity(w2);

        Wall w3 = new Wall(0,3);
        d.addEntity(w3);

        Wall w4 = new Wall(1,3);
        d.addEntity(w4);

        Wall w5 = new Wall(2,0);
        d.addEntity(w5);

        Wall w6 = new Wall(3,0);
        d.addEntity(w6);

        Wall w7 = new Wall(4,0);
        d.addEntity(w7);

        Wall w8 = new Wall(4,1);
        d.addEntity(w8);

        Wall w9 = new Wall(4,2);
        d.addEntity(w9);

        //add a player at (0,0)
        Player p = new Player(d, 0, 0);
        d.setPlayer(p);

        //place a door at (2,2)
        Door door001 = new Door(2,2,001);
        d.addEntity(door001);
        //place a key at (1,2)
        Key key001 = new Key(1,2,001);
        key001.setDoor(001);
        d.addEntity(key001);

        p.moveRight();
        p.moveDown();

        //player move to (1,2)
        p.moveDown();

        //player can not move right because the door is blocking
        p.moveRight();
        //Player remain at (1,2)
        assertEquals(p.getX(), 1);
        assertEquals(p.getY(), 2); 

        //player open the door
        p.openDoor();
        //Now player can move to (2,2) 
        p.moveRight();
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 2);

        assertEquals(p.getKey(), null);


    }

    @Test
    public void BasicExitTest(){
        // same configuration as BasicPlayerTest
        Dungeon d = new Dungeon(5,4);
        Wall w1 = new Wall(0,1);
        d.addEntity(w1);
        Wall w2 = new Wall(0,2);
        d.addEntity(w2);

        Wall w3 = new Wall(0,3);
        d.addEntity(w3);

        Wall w4 = new Wall(1,3);
        d.addEntity(w4);

        Wall w5 = new Wall(2,0);
        d.addEntity(w5);

        Wall w6 = new Wall(3,0);
        d.addEntity(w6);

        Wall w7 = new Wall(4,0);
        d.addEntity(w7);

        Wall w8 = new Wall(4,1);
        d.addEntity(w8);

        Wall w9 = new Wall(4,2);
        d.addEntity(w9);

        //add a player at (0,0)
        Player p = new Player(d, 1, 2);
        d.setPlayer(p);

        Exit exit = new Exit(2,2);
        d.addEntity(exit);

        p.moveRight();
        

    
    }

/////////enemy test 
    @Test    
    public void BasicEnemy(){
        Dungeon d = new Dungeon(8,8);
        Player p = new Player(d, 2, 2);
        d.setPlayer(p);
    
        Sword s = new Sword(2, 3);
        d.addEntity(s);

        p.moveDown(); // pick up the sword 
        p.swordCheck();

        Enemy e1 = new Enemy(2, 4, d );
        d.addEntity(e1);
        
        
        
        p.wield();

        // enemy is killed
        assertEquals(d.getEntity(e1.getX(),e1.getY()).isEmpty(), true);

        Enemy e2 = new Enemy(5, 5, d );
        d.addEntity(e2);

        
        p.moveUp();
        System.out.println(p.getX() + " " + p.getY());
        System.out.println("after first move enemy is " + e2.getX() + " " + e2.getY());
  
        // enemy meet player and killed player
        //enemy move 2 unit to close player 
        assertEquals(e2.getX(), p.getX()+1);
        assertEquals(e2.getY(), p.getY()+1); // move up
        
        p.moveDown();

        System.out.println(p.getX() + " " + p.getY());
        System.out.println("after second move enemy is " + e2.getX() + " " + e2.getY());

        assertEquals(e2.getX(), p.getX());
        assertEquals(e2.getY(), p.getY());

        // player is die (enemy is still locat at player's postion so move out)
        e2.tracPlayer(p.getX() +1 , p.getY() + 1, d);
        // should be empty cuz player is die 
        assertEquals(d.getEntity(p.getX(),p.getY()).isEmpty(), true);
       
    }

    @Test
    public void PotionTest(){
        Dungeon d = new Dungeon(10,10);
        Player player = new Player(d, 1, 1);
        d.setPlayer(player);
        
        Potion potion = new Potion(1, 2);
        d.addEntity(potion);
        
        
        Enemy e = new Enemy(10, 10, d);
        d.addEntity(e);       

        player.moveDown();        
        assertEquals(player.getPotion().equals(d.getEnemyTime()) , true);
        
        
        player.moveDown();
        assertEquals(d.getEntity(potion.getX(),potion.getY()).isEmpty(), true); // potion dispear 
        
        player.moveDown();
        player.moveDown();
        
        // enemy didn't move due to potion's function         
        assertEquals(e.getX(), 10);
        assertEquals(e.getY(), 10);         
    }
/*
    @Test
    public void PortalTest(){
        Dungeon d = new Dungeon(5,5);
        Portal p1 = new Portal(1, 2);
        d.addEntity(p1);
        
        Portal p2 = new Portal(4, 4);
        d.addEntity(p2);
        
        p1.setPair(p2); // pair the portal 
        
        Player player = new Player(d, 1, 1);
        d.setPlayer(player);

        player.moveDown();
        
        assertEquals(player.getX(), p2.getX() + 1); // new location is infront of portal 
        assertEquals(player.getY(), p2.getY());
    }
*/
    @Test
    public void SwitchFloorTest(){
        Dungeon dungeon = new Dungeon(5,5);
        Player player = new Player(dungeon, 1, 1);
        dungeon.setPlayer(player);

        Boulder boulder = new Boulder(1, 2, dungeon);
        dungeon.addEntity(boulder);;
        SwitchFloor switchf = new SwitchFloor(1, 3, dungeon);
        dungeon.addEntity(switchf);
        
        Door door = new Door(2, 2, 1);
        dungeon.addEntity(door);
        switchf.addDoor(door);

        assertEquals(door.getState().getClass().toString(),"class unsw.dungeon.DoorCloseState" );
        player.moveDown();
         
        assertEquals(door.getState().getClass().toString(),"class unsw.dungeon.DoorOpenState" );

    }

    @Test
    public void BasicBoulderTest(){
        // same configuration as BasicPlayerTest
        // same configuration as BasicPlayerTest
        Dungeon d = new Dungeon(5,4);
        Wall w1 = new Wall(0,1);
        d.addEntity(w1);
        Wall w2 = new Wall(0,2);
        d.addEntity(w2);

        Wall w3 = new Wall(0,3);
        d.addEntity(w3);

        Wall w4 = new Wall(1,3);
        d.addEntity(w4);

        Wall w5 = new Wall(2,0);
        d.addEntity(w5);

        Wall w6 = new Wall(3,0);
        d.addEntity(w6);

        Wall w7 = new Wall(4,0);
        d.addEntity(w7);

        Wall w8 = new Wall(4,1);
        d.addEntity(w8);

        Wall w9 = new Wall(4,2);
        d.addEntity(w9);

        //add a player at (0,0)
        Player p = new Player(d, 1, 2);
        d.setPlayer(p);

        Boulder boulder = new Boulder(2,2,d);
        d.addEntity(boulder);

        p.moveRight();
        //player should move the boulder as he moves, i.e. push boulder from (2,2) to (3,2)
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 2);

        //now boulder should be in (3,2)
        assertEquals(boulder.getX(), 3);
        assertEquals(boulder.getY(), 2);

        //now player can not move forward because there is a wall at (4,2)
        // so player remain in (2,2)
        p.moveRight();
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 2);

        //boulder remain in (3,2)
        assertEquals(boulder.getX(), 3);
        assertEquals(boulder.getY(), 2);

    
    }
    @Test
    public void AdvancedBoulderTest(){
        // same configuration as BasicPlayerTest
        // same configuration as BasicPlayerTest
        Dungeon d = new Dungeon(5,4);
        Wall w1 = new Wall(0,1);
        d.addEntity(w1);
        Wall w2 = new Wall(0,2);
        d.addEntity(w2);

        Wall w3 = new Wall(0,3);
        d.addEntity(w3);

        Wall w4 = new Wall(1,3);
        d.addEntity(w4);

        Wall w5 = new Wall(2,0);
        d.addEntity(w5);

        Wall w6 = new Wall(3,0);
        d.addEntity(w6);

        Wall w7 = new Wall(4,0);
        d.addEntity(w7);

        Wall w8 = new Wall(4,1);
        d.addEntity(w8);

        Wall w9 = new Wall(4,2);
        d.addEntity(w9);

        //add a player at (0,0)
        Player p = new Player(d, 1, 2);
        d.setPlayer(p);

        Boulder boulder = new Boulder(2,2,d);
        d.addEntity(boulder);

        p.moveRight();
        //player should move the boulder as he moves, i.e. push boulder from (2,2) to (3,2)
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 2);

        //now boulder should be in (3,2)
        assertEquals(boulder.getX(), 3);
        assertEquals(boulder.getY(), 2);

        //now player can not move forward because there is a wall at (4,2)
        // so player remain in (2,2)
        p.moveRight();
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 2);

        //boulder remain in (3,2)
        assertEquals(boulder.getX(), 3);
        assertEquals(boulder.getY(), 2);

        //Player move up to (2,1)
        p.moveUp();
        assertEquals(p.getX(), 2);
        assertEquals(p.getY(), 1);

        //Player move up to (3,1)
        p.moveRight();
        assertEquals(p.getX(), 3);
        assertEquals(p.getY(), 1);

        //boulder remain in (3,2)
        assertEquals(boulder.getX(), 3);
        assertEquals(boulder.getY(), 2);

        //now player should push the boulder to (3,3)
        p.moveDown();
        assertEquals(p.getX(), 3);
        assertEquals(p.getY(), 2);

        assertEquals(boulder.getX(), 3);
        assertEquals(boulder.getY(), 3);
    }

    @Test
    public void SimpleSwordtest(){

        Dungeon d = new Dungeon(8,8);
        Player p = new Player(d, 2, 2);
        d.setPlayer(p);

        // player move enemy should follow and trac 
        Sword s = new Sword(4,2);
        d.addEntity(s);
        
        p.moveRight();
        p.moveRight();
        assertEquals(p.getX(), 4);
        assertEquals(p.getY(), 2);

        assertEquals(d.getEntity(4, 2).isEmpty(), true);

        assertEquals(p.getSword(), s);
        assertEquals(s.available(), 5);

        p.wield();
        assertEquals(s.available(), 4);
        assertEquals(p.getSword(), s);

        p.wield();
        assertEquals(s.available(), 3);
        assertEquals(p.getSword(), s);

        p.wield();
        assertEquals(s.available(), 2);
        assertEquals(p.getSword(), s);

        p.wield();
        assertEquals(s.available(), 1);
        assertEquals(p.getSword(), s);

        p.wield();
        assertEquals(s.available(), 0);
        assertEquals(p.getSword(), null);




    }
}