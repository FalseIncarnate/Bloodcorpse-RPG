
package rpg2;

/**
 * @author Bloodcorpse
 * Version 1.4
 */

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class River {
    
    protected static void riverMenu(){
        if(stats.bridgeFound == true){
            Main.zone = "River";
            Main.screen = "riverMenu";
            Main.output.append("Now that you've discovered the bridge, you may travel to it directly or explore randomly." + "\n");
            Main.output.append("If you explore randomly, you may still encounter the bridge." + "\n");
            Main.output.append("What would you like to do?");
            Main.output.append("\n" + "\n");
            Main.btn1.setText("Go to Bridge");
            Main.btn2.setText("Explore River");
            Main.btn3.setText("Return to Crossroads");
            Main.btn4.setText("Return to Camp");
            Main.btn5.setText(" ");
            Main.btn6.setText(" ");
        }else{
            eventSelect();
        }
    }
    
    protected static void eventSelect(){
        Main.zone = "River";
        Main.event = Main.gen.nextInt(3)+1;
        switch(Main.event){
            case 1: goFishing(); break;
            case 2: snappingTurtle(); break;
            case 3: trollToll(); break;
        }
    }
    
    protected static void goFishing(){
        Main.output.append("You find what appears to be an exceptional fishing spot!" + "\n");
        if(Inv.toolNum.get(0)[0]>0){
            Main.output.append("You cast your line and attempt to catch something..." + "\n");
            Main.event = Main.gen.nextInt(3);
            if(Main.event==0){
                Main.output.append("You hook something, but it slips free before you manage to reel it in.");
            }else if(Main.event==1){
                Main.output.append("You catch a tasty looking fish, and quickly build a fire to cook it with.");
                Inv.foodNum.get(1)[0] += 1;
            }else if(Main.event==2){
                Main.output.append("You reel in a " + Inv.generalName.get(2) + "... It doesn't look wearable.");
                Inv.generalNum.get(2)[0] += 1;
            }
        }else{
            Main.output.append("You don't have a " + Inv.toolName.get(0) + " to make use of this find.");
        }
        Main.output.append("\n" + "\n");
    }
    
    protected static void snappingTurtle(){
        Main.output.setText("A large snapping sound catches your attention as a Snapping Turtle attempting to bite you!");
        Main.output.append("\n" + "\n");
        new Enemy("snapping turtle");
        Combat.combatMenu();
    }
    
    protected static void trollToll(){
        stats.bridgeFound = true;
        Main.output.append("You come across a rather nice bridge across the river." + "\n");
        Main.output.append("There is a small tollbooth blocking access to the bridge." + "\n");
        Main.output.append("Upon approaching the tollbooth, you notice a large troll inside." + "\n");
        Main.output.append("\"Trolls Inc own bridge. You pay toll, or you no cross.\" The troll states." + "\n");
        Main.output.append("A sign above the window informs you the toll is 50 GP. Outrageous!");
        Main.output.append("\n" + "\n");
        Main.screen = "trollMenu";
        Main.btn1.setText("Pay Toll (50 GP)");
        Main.btn2.setText("Fight Troll");
        Main.btn3.setText("Talk to Troll");
        Main.btn4.setText("Turn Back");
        Main.btn5.setText(" ");
        Main.btn6.setText(" ");
    }
    
    protected static void trollToll(int choice){
        Main.zone = "River Bridge";
        Main.output.append("\n");
        switch(choice){
            case 1: //Pay Toll
                if(stats.myGP[0] >= 50){
                    Main.output.append("You pay the Troll his 50 gold toll." + "\n");
                    Main.output.append("\"Trolls Inc thank you for patronage.\" The troll says with a grin." + "\n");
                    Main.output.append("The troll steps aside, allowing you to cross the bridge." + "\n");
                    Main.output.append("As you pass, you overhear the troll talking to himself. \"Maybe boss give me promotion for coins this time!\"" + "\n");
                    Main.output.append("You reflect on how when you were younger, trolls were solitary and unincorporated. Those were the days...");
                    stats.myGP[0] -= 50;
                    stats.tollsPaid += 1;
                    Main.updateLabels();
                    Explore.exploreMenu2();
                }else if(stats.myGP[0] == 0){
                    Main.output.append("You try to explain to the troll that you have no money. He stares at you blankly." + "\n");
                    Main.output.append("The troll doesn't seem to understand, so you show him your empty coinpurse." + "\n");
                    Main.output.append("\"Me no want bag. Give me coins!\" The troll shouts at you. \"Me no get promotion unless me get coins!\"" + "\n");
                    Main.output.append("The troll becomes so enraged that he attacks!" + "\n");
                    new Enemy("troll");
                }else{
                    Main.output.append("You offer " + stats.myGP[0] + " gold to the troll. The troll snatches them and slowly counts the coins." + "\n");
                    Main.output.append("The troll seems confused. \"Toll is more coins. You no give more coins. Where more coins?\"");
                    Main.output.append("You explain that was the last of your gold, and show him your empty coinpurse." + " \n");
                    Main.output.append("The troll thinks for a minute. \"You get more, then pay toll! Me so smart. Me get promotion for sure!\"");
                    Main.output.append("The troll takes your gold, but doesn't move. Unable to get past, you turn back.");
                    stats.myGP[0] = 0;
                    Main.updateLabels();
                    return;
                }
                break;
            case 2: //Fight
                new Enemy("troll");
                Combat.combatMenu();
                break;
            case 3: //Talk
                Main.output.append("The troll doesn't seem like a very good conversationalist." + "\n");
                Main.output.append("He keeps grinning creepily and saying \"Problem?\" It's rather annoying." + "\n");
                break;
        }
    }
    
    protected static void postCombatTrollMenu(){
        Main.screen = "postCombatTrollMenu";
        Main.btn1.setText("Cross Bridge");
        Main.btn2.setText("Continue Exploring");
        Main.btn3.setText("Return to Camp");
        Main.btn4.setText(" ");
        Main.btn5.setText(" ");
        Main.btn6.setText(" ");
    }
    
}
