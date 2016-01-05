
package rpg2;

/**
 * @author Bloodcorpse
 * Version 1.2
 */

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Forest {
    
    protected static void eventSelect(){
        Main.zone = "Forest";
        Main.event = Main.gen.nextInt(6)+1;
        switch(Main.event){
            case 1: goblinEvent(); break;
            case 2: wolfEvent(); break;
            case 3: findCampsiteEvent(); break;
            case 4: findGoldEvent(); break;
            case 5: findHerbsEvent(); break;
            case 6: noEvent(); break;
        }
    }
    
    protected static void goblinEvent(){
        Main.output.setText("A goblin bursts from the undergrowth and attacks!");
        Main.output.append("\n" + "\n");
        new Enemy("goblin");
        Combat.combatMenu();
    }
    
    protected static void wolfEvent(){
        Main.output.setText("You hear a loud growling behind you. You turn around just before a wolf attacks you!");
        Main.output.append("\n" + "\n");
        new Enemy("wolf");
        Combat.combatMenu();
    }
    
    protected static void findCampsiteEvent(){
        Main.output.append("You find an abandoned campsite, and search for anything that was left behind." + "\n");
        switch(Main.gen.nextInt(5)){
            case 0:
                Main.output.append("You found a " + Inv.foodName.get(2) + " inside a discarded sack!");
                Inv.foodNum.get(2)[0] += 1;
                break;
            case 1:
                Main.output.append("You found a " + Inv.potionName.get(1) + " in a broken box!");
                Inv.potionNum.get(1)[0] += 1;
                break;
            case 2:
                Main.output.append("You found a " + Inv.generalName.get(1) + " hidden under some old rags!");
                Inv.generalNum.get(1)[0] += 1;
                break;
            case 3:
                stats.lootGP = Main.gen.nextInt(15) + 1;
                Main.output.append("You found a small bag of gold, holding " + stats.lootGP + " gold!");
                stats.myGP[0] += stats.lootGP;
                stats.myGP[1] += stats.lootGP;
                break;
            case 4:
                Main.output.append("You didn't manage to find anything worth taking.");
                break;
        }
        Main.updateLabels();
        Main.output.append("\n" + "\n");
    }
    
    protected static void findGoldEvent(){
        stats.lootGP = Main.gen.nextInt(10)+1;
        Main.output.append("You find a small bag of gold in a hollow log." + "\n"
                + "Opening the bag, you find "+ stats.lootGP + " gold inside!");
        Main.output.append("\n" + "\n");
        stats.myGP[0] += stats.lootGP;
        stats.myGP[1] += stats.lootGP;
        Main.updateLabels();
    }
    
    protected static void findHerbsEvent(){
        Main.output.append("You find a small clearing with various plants growing in it." + "\n");
        switch(Main.gen.nextInt(5)){
            case 0:
                Main.output.append("You find and pick some Heartbloom, an herb commonly used for healing!");
                Inv.generalNum.get(5)[0] += 1;
                break;
            case 1:
                Main.output.append("You find and pick some Mageflower, an herb known for it's magical properties!");
                Inv.generalNum.get(6)[0] += 1;
                break;
            case 2:
                Main.output.append("You find and pick some Tasty Berries!");
                Inv.foodNum.get(3)[0] += 1;
                break;
            case 3:
                Main.output.append("You aren't able to find any special plants here.");
                break;
            case 4:
                Main.output.append("You carefully collect some Venom Vine, a rather dangerous herb.");
                Inv.generalNum.get(7)[0] += 1;
                break;
        }
        Main.output.append("\n" + "\n");
    }
    
    protected static void noEvent(){
        Main.output.append("You walk through the forest, listening to the songbirds for a while." + "\n"
                + "Eventually, you get bored and head back.");
        Main.output.append("\n" + "\n");
    }
    
}
