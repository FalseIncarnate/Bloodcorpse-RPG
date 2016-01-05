
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

public class Mountain {
    
    protected static void eventSelect(){
        Main.zone = "Mountain";
        Main.event = Main.gen.nextInt(4)+1;
        switch(Main.event){
            case 1: yeti(); break;
            case 2: landslideEvent(); break;
            case 3: crazyHermit(); break;
            case 4: findOre(); break;
        }
    }
    
    protected static void yeti(){
        Main.output.setText("You encounter a fearsome Yeti while exploring the snow-capped mountains.");
        Main.output.append("\n" + "\n");
        new Enemy("yeti");
        Combat.combatMenu();
    }
    
    protected static void landslideEvent(){
        Main.output.append("While hiking through the mountain passes, you hear a growing rumble from high above you." + "\n");
        Main.output.append("You scramble back just in time to avoid getting buried beneath the landslide that pours over the cliff." + "\n");
        Main.output.append("You decide to head back as the landslide continues." + "\n");
        Main.output.append("\n");
    }
    
    protected static void crazyHermit(){
        Main.output.append("You stumble across a small cave in the mountainside." + "\n");
        Main.output.append("Upon further investigation, you discover the cave houses a Hermit! He seems a bit off..." + "\n");
        Main.event = Main.gen.nextInt(6);
        switch(Main.event){
            case 0:     //Meditating
                Main.output.append("The Hermit appears to be meditating when you enter. He shakes his fist at you, and you quietly leave.");
                Main.output.append("\n" + "\n");
                break;
            case 1:     //Buff
                Main.output.append("The Hermit welcomes you in, and beckons for you to sit and meditate with him." + "\n");
                Main.output.append("You meditate for a while, and you thank the Hermit for his assistance after you are done.");
                Main.output.append("As you leave, you feel " + Effects.myBuffData.get(7)[0] + "!");
                Main.output.append("\n" + "\n");
                Effects.myBuffData.get(7)[1] = Effects.myBuffData.get(7)[2];
                break;
            case 2:     //Advice
                Main.output.append("You approach the Hermit and ask for him to impart some of his wisdom to aid you on your adventures." + "\n");
                Main.output.append("He strokes his beard thoughtfully as he decides on what advice to grant you." + "\n");
                Main.output.append("\"Armor may stop physical blows, but spells will strike true.\" He says with a sagely nod."+ "\n");
                Main.output.append("You thank the Hermit for his advice, and bid him farewell.");
                Main.output.append("\n" + "\n");
                break;
            case 3:     //Fight
                Main.output.setText("The Hermit seems to have gone mad, and attacks you in a crazed fit of rage!");
                Main.output.append("\n" + "\n");
                new Enemy("hermit");
                Combat.combatMenu();
                break;
            case 4:     //Gold
                Main.output.append("The Hermit welcomes you in, and hands you a small bag containing 50 gold." + "\n");
                Main.output.append("\"May this aid you on your quest, adventurer. Use it well.\"");
                Main.output.append("\n" + "\n");
                stats.myGP[0] += 50;
                stats.myGP[1] += 50;
                Main.updateLabels();
                break;
            case 5:     //Advice
                Main.output.append("You approach the Hermit and ask for him to impart some of his wisdom to aid you on your adventures." + "\n");
                Main.output.append("He furrows his brow as he decides on what advice to grant you." + "\n");
                Main.output.append("\"Dolls can be powerful tools, capable of strengthening your spells... or saving your life.\"" + "\n");
                Main.output.append("You thank the Hermit for his advice, and bid him farewell.");
                Main.output.append("\n" + "\n");
                break;
        }
    }
    
    protected static void findOre(){
        Main.output.append("You stumble upon what appears to be a rather prominent ore vein!" + "\n");
        if(Inv.toolNum.get(1)[0] > 0){
            Main.event = Main.gen.nextInt(4);
            switch(Main.event){
                case 0:     //Nothing
                    Main.output.append("You accidentally pulverize the ore beyond use. You walk away empty-handed.");
                    break;
                case 1:     //Crystal Shard
                    Main.output.append("You carefully extract a pristine " + Inv.generalName.get(9) + " from the earth.");
                    Inv.generalNum.get(9)[0] += 1;
                    break;
                case 2:     //Shiny Rocks
                    Main.output.append("You managed to mine some " + Inv.generalName.get(10) + "... They might be worth something?");
                    Inv.generalNum.get(10)[0] += 1;
                    break;
                case 3:     //Metallite Ore
                    Main.output.append("You harvest a chunk of " + Inv.generalName.get(8) + " from the ore vein.");
                    Inv.generalNum.get(8)[0] += 1;
                    break;
            }
        }else{
            Main.output.append("If only you had a " + Inv.toolName.get(1) + " to mine with...");
        }
        Main.output.append("\n" + "\n");
    }
    
}
