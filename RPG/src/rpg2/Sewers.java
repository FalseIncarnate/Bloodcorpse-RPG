
package rpg2;

/**
 * @author Bloodcorpse
 * Version 1.3
 */

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Sewers {
    
    protected static void sewerMenu(){
        Main.zone = "Sewers";
        Main.output.setText("You have climbed down into the sewers. It's dark, damp, and rather smelly down here." + "\n");
        Main.output.append("What would you like to do?" + "\n");
        Main.screen = "sewerMenu";
        Main.btn1.setText("Explore");
        Main.btn2.setText("Return to Town");
        Main.btn3.setText(" ");
        Main.btn4.setText(" ");
        Main.btn5.setText(" ");
        Main.btn6.setText(" ");
    }
    
    protected static void specialSewerMenu(int textChoice){
        Main.screen = "sewerMenu";
        Main.btn1.setText("Explore");
        Main.btn2.setText("Return to Town");
        Main.btn3.setText(" ");
        Main.btn4.setText(" ");
        Main.btn5.setText(" ");
        Main.btn6.setText(" ");
        switch(textChoice){
            case 1: //Blackout Drunk
                Main.zone = "Sewers";
                Main.output.setText("You wake up in the sewers. It's dark, damp, and rather smelly down here." + "\n");
                Main.output.append("You don't remember why you came down here, and you feel hung-over." + "\n");
                Main.output.append("Those drinks must have been stronger than you thought..." + "\n");
                Main.output.append("What would you like to do?" + "\n");
                break;
        }
        Main.updateLabels();
    }
    
    protected static void eventSelect(){
        Main.event = Main.gen.nextInt(8)+1;
        switch(Main.event){
            case 1: TMNTEvent(); break;
            case 2: sewerRatEvent(); break;
            case 3: giantRatEvent(); break;
            case 4: alligatorEvent(); break;
            case 5: vagrantEvent(); break;
            case 6: toxicGasEvent(); break;
            case 7: findSludgeEvent(); break;
            case 8: noEvent(); break;
        }
    }
    
    protected static void TMNTEvent(){
        Main.output.append("\n");
        Main.output.append("You find a couple empty boxes labelled \"Pizza\", surrounded by 4 sets of footprints." + "\n");
        Main.output.append("From deeper in the sewer tunnels, you hear the echoes of voices shouting \"COWABUNGA!\"" + "\n");
        Main.output.append("How strange...");
        Main.output.append("\n" + "\n");
    }
    
    protected static void sewerRatEvent(){
        Main.output.append("\n");
        Main.output.append("You feel something brush past your leg, and spot a nasty Sewer Rat!");
        new Enemy("sewerRat");
        Main.output.append("\n" + "\n");
        Combat.combatMenu();
    }
    
    protected static void giantRatEvent(){
        Main.output.append("\n");
        Main.output.append("You hear loud scurrying behind you. You turn around and are face to face with a Giant Rat!");
        new Enemy("giantRat");
        Main.output.append("\n" + "\n");
        Combat.combatMenu();
    }
    
    protected static void alligatorEvent(){
        Main.output.append("\n");
        Main.output.append("You something swimming through the filthy water ahead of you." + "\n");
        Main.output.append("Squinting, you manage to spot an Alligator coming right for you! The urban legends were true!");
        new Enemy("alligator");
        Main.output.append("\n" + "\n");
        Combat.combatMenu();
    }
    
    protected static void vagrantEvent(){
        Main.output.append("\n");
        Main.output.append("You nearly trip over someone sprawled out, face down on the ground." + "\n");
        if(stats.isFemale == true){
            Main.output.append("\"You *hic* shure got shome nerve, misshy!\" The woman yells as she staggers to her feet." + "\n");
        }else{
            Main.output.append("\"You *hic* shure got shome nerve, mishter!\" The man yells as he staggers to his feet." + "\n");
        }
        Main.output.append("You can smell the booze on the Vagrant's breath, and it seems they are looking for a fight!");
        new Enemy("vagrant");
        Main.output.append("\n" + "\n");
        Combat.combatMenu();
    }
    
    protected static void toxicGasEvent(){
        Main.output.append("\n");
        Main.output.append("While exploring, you slip on the slimy floor and fall off the walkway!" + "\n");
        Main.output.append("Luckily, you landed on a lower ledge and not in the water. However, the air seems heavier down here..." + "\n");
        Enemy.enemyName = "Sewer Gas Cloud";
        stats.myHP[0] -= 35;
        Main.output.append("You scramble as quickly as you can back to the upper walkway, but accidentally inhale some of the gas cloud!" + "\n");
        Main.output.append("You cough violently while your lungs burn from the toxic fumes! You suffer 35 damage!");
        Main.output.append("\n" + "\n");
        Effects.playerBuffUpdate(true);
    }
    
    protected static void findSludgeEvent(){
        Main.output.append("\n");
        Main.output.append("While exploring, you notice a small pile of a strange, glowing goo." + "\n");
        if(Inv.toolNum.get(5)[0]>=1){
            Main.output.append("You fill your " + Inv.toolName.get(5) + " with a sample of " + Inv.potionName.get(7) + ".");
            Inv.toolNum.get(5)[0] -= 1;
            Inv.potionNum.get(7)[0] += 1;
        }else{
            Main.output.append("You don't have anything to collect a sample in. Perhaps if you had an Empty Bottle...");
        }
        Main.output.append("\n" + "\n");
    }
    
    protected static void noEvent(){
        Main.output.append("\n");
        Main.output.append("You carefully wind through the sewer tunnels in search of adventure." + "\n");
        Main.output.append("After a while, the stench makes you light headed, so you head back emptyhanded.");
        Main.output.append("\n" + "\n");
    }
    
}
