
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

public class Camp {
    
    protected static void campMenu(){
        Main.screen = "campMenu";
        Main.btn1.setText("Explore");
        Main.btn2.setText("Inventory");
        Main.btn3.setText("Rest");
        Main.btn4.setText("Stats");
        Main.btn5.setText("Save/Load");
        Main.btn6.setText("DEBUG");
        Main.output.setText("You are standing in your camp." + "\n");
        Main.output.append("A ring of wooden spikes defend your campfire and bedroll." + "\n");
        Main.output.append("You feel safe here." + "\n");
    }
    
    protected static void specialCampMenu(int textChoice){
        Main.screen = "campMenu";
        Main.btn1.setText("Explore");
        Main.btn2.setText("Inventory");
        Main.btn3.setText("Rest");
        Main.btn4.setText("Stats");
        Main.btn5.setText("Save/Load");
        Main.btn6.setText("DEBUG");
        switch(textChoice){
            case 1: //Blackout Drunk
                Main.output.setText("You wake up in your camp." + "\n");
                Main.output.append("You aren't sure how you got here, and you feel quite hung-over." + "\n");
                Main.output.append("Those drinks must have been stronger than you thought..." + "\n");
                break;
            case 2: //Resurrected
                Main.output.setText("You wake up in your camp." + "\n");
                Main.output.append("The last thing you remember was collapsing from your injuries." + "\n");
                if(Inv.toolNum.get(3)[0] == 0){
                    Main.output.append("Oddly, your " + Inv.toolName.get(3) + " is nowhere to be found..." + "\n");
                }else{
                    Main.output.append("Oddly, you seem to be missing a " + Inv.toolName.get(3) + "..." + "\n");
                }
                Main.output.append("Even stranger, you feel less experienced than you remembered...");
        }
        Main.updateLabels();
    }
    
    protected static void restMenu(){
        Main.screen = "restMenu";
        Main.btn1.setText("Heal");
        Main.btn2.setText("Meditate");
        Main.btn3.setText("Replenish");
        Main.btn4.setText("Nevermind");
        Main.btn5.setText(" ");
        Main.btn6.setText(" ");
    }
    
    protected static void heal(){
        if(stats.myHP[0]<stats.myHP[1]){
            Main.output.append("\n");
            Main.output.append("You tend to your wounds, and return to full health!" + "\n");
            stats.myHP[0] = stats.myHP[1];
            Effects.curePoisons();
        }else{
            Main.output.append("\n");
            Main.output.append("You are already at full health." + "\n");
        }
        Effects.playerBuffUpdate(true);
    }
    
    protected static void meditate(){
        if(stats.myMP[0]<stats.myMP[1]){
            Main.output.append("\n");
            Main.output.append("You focus your mind, allowing your mana to fully restore!" + "\n");
            stats.myMP[0] = stats.myMP[1];
        }else{
            Main.output.append("\n");
            Main.output.append("You are already at full mana." + "\n");
        }
        Effects.playerBuffUpdate(true);
    }
    
    protected static void replenish(){
        Main.output.append("\n");
        Main.output.append("You rest your mind and body, replenishing your health and mana." + "\n");
        stats.myHP[0] = stats.myHP[1];
        stats.myMP[0] = stats.myMP[1];
        Effects.playerBuffUpdate(true);
    }
    
    protected static void statsMenu(){
        Main.screen = "statsMenu";
        Main.btn1.setText("My Stats");
        Main.btn2.setText("My Legacy");
        Main.btn3.setText("Active Buffs");
        Main.btn4.setText("Active Effects");
        Main.btn5.setText("My Progress");
        Main.btn6.setText("Nevermind");
    }
    
    protected static void debugAction(){
        Enemy.enemyName = "DEBUG BUTTON";
        stats.myHP[0]-=10;
        stats.myMP[0] -= 5;
        if(stats.myMP[0] < 0){
            stats.myMP[0] = 0;
        }
        Main.output.append("\n");
        Main.output.append("DEBUG: HP -10 & MP -5" + "\n");
        Inv.armorNum.get(0)[0] += 1;
        Inv.armorNum.get(2)[0] += 1;
        Inv.armorNum.get(5)[0] += 1;
        Inv.weaponNum.get(3)[0] += 1;
        Inv.offhandNum.get(2)[0] += 1;
        if((Boolean)Effects.myEffectData.get(4)[14] == false){
            Effects.myEffectData.get(4)[14] = true;
        }else{
            Effects.myEffectData.get(4)[14] = false;
        }
        Effects.playerBuffUpdate(true);
    }
    
}
