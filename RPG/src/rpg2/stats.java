
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

public class stats {
    
    //General Data
    static boolean isFemale = false;
    static String name;
    static int level = 1;
    static int myEXP = 0;
    
    //Attributes
        //Value 1 = Base value (increased by level-up)
        //Value 2 = Bonus value (sum of all bonuses, can be negative)
        //Value 3 = Adjusted value (cannot be less than 0)
    static int strength[] = {10, 0, 10};    //Physical Attack Damage
    static int intel[] = {10, 0, 10};       //Spellpower
    static int body[] = {10, 0, 10};        //Max Health
    static int will[] = {10, 0, 10};        //Max Mana
    static int speed[] = {10, 0, 10};       //Double Strike Chance, Run Chance
    
    //Health and Mana
        // Value 1 = Current value
        // Value 2 = Maximum value
    static int myHP[] = {0, 0};
    static int myMP[] = {0, 0};
    
    //Combat Data
        //Value 1 = Gear value
        //Value 2 = Bonus value (sum of all bonuses, can be negative)
        //Value 3 = Adjusted value (cannot be less than 0)
    static int myArmor[] = {0, 0, 0};       //Damage Reduction
    static int myDamage[] = {0, 0, 0};      //Bonus Damage
    static int mySPower[] = {0, 0, 0};      //Bonus Spellpower
    
    //Legacy Character Data
    static int myGP[] = {100, 100};     //Value 1 is current gold, Value 2 is lifetime gold
    static int myKills = 0;
    static int timesFled = 0;
    static int tollsPaid = 0;
    static int wagersPlaced = 0;
    static int timesRezzed = 0;
    static int gravesRobbed = 0;
    static int cryptDepth = 0;
        
    //Bonuses Data
    static int bonusGold = 0;       //Percentage to increase gold drops by
    static int bonusXP = 0;         //Percentage to increase EXP gains by
    static double bonusRun = 0.0;   //Bonus chance to run away
    
    //Exploration Data
    static boolean bridgeFound = false;
    static boolean cryptFound = false;
    
    //Misc Data
    static int lootGP = 0;
    static int lvlPoints = 0;
    
    static void resetStats(){
        //reset level and exp
        level = 1; myEXP = 0;
        //reset stats
        strength[0] = 10; strength[1] = 0; strength[2] = 10;
        intel[0] = 10; intel[1] = 0; intel[2] = 10;
        body[0] = 10; body[1] = 0; body[2] = 10;
        will[0] = 10; will[1] = 0; will[2] = 10;
        speed[0] = 10; speed[1] = 0; speed[2] = 10;
        //reset HP and MP
        myHP[1] = body[2]*10; myHP[0] = myHP[1];
        myMP[1] = will[2]*5; myMP[0] = myMP[1];
        //reset gender (default male)
        isFemale = false;
        //reset gold
        myGP[0] = 100; myGP[1] = 100;
        //reset bonuses
        bonusGold = 0; bonusXP = 0; bonusRun = 0.0;
        //reset legacy stats
        myKills = 0; timesFled = 0;
        tollsPaid = 0; wagersPlaced = 0;
        timesRezzed = 0; gravesRobbed = 0;
        cryptDepth = 0;
        //reset exploration progress
        resetProgress();
    }
    
    static void resetProgress(){
        bridgeFound = false;
        cryptFound = false;
    }
    
    static void healthCheck() {
        if(myHP[0] <= 0){
            if(Inv.toolNum.get(3)[0] >= 1){
                rez(1);
            }else{
            Main.gameOver();
            }
        }
    }
    
    static void rez(int method){
        switch(method){
            case 1: //Doll of Rebirth
                Main.output.setText("You collapse to the ground from your injuries." + "\n");
                Main.output.append("It seems as though the story of " + stats.name + " ends here." + "\n");
                Main.output.append("You black out, and your " + Inv.toolName.get(3) + " begin to glow brightly..." + "\n");
                Inv.toolNum.get(3)[0] -= 1;
                myEXP = 0; myHP[0] = 1; myMP[0] = 0;
                timesRezzed += 1; Camp.specialCampMenu(2); break;
        }
    }
    
    static void updateStats(){
        strength[2] = strength[0] + strength[1];
        if(strength[2] < 0){
            strength[2] = 0;
        }
        intel[2] = intel[0] + intel[1];
        if(intel[2] < 0){
            intel[2] = 0;
        }
        body[2] = body[0] + body[1];
        if(body[2] < 0){
            body[2] = 0;
        }
        will[2] = will[0] + will[1];
        if(will[2] < 0){
            will[2] = 0;
        }
        speed[2] = speed[0] + speed[1];
        if(speed[2] < 0){
            speed[2] = 0;
        }
        myHP[1] = body[2]*10;
        myMP[1] = will[2]*5;
        myArmor[2] = myArmor[0] + myArmor[1];
        if(myArmor[2] < 0){
            myArmor[2] = 0;
        }
        myDamage[2] = myDamage[0] + myDamage[1];
        if(myDamage[2] < 0){
            myDamage[2] = 0;
        }
    }
    
    protected static void showStats(){
        Main.output.setText("Stats for " + name + ":" + "\n");
        Main.output.append("=======================" + "\n");
        if(isFemale == true){
            Main.output.append("Gender: Female" + "\n");
        }else{
            Main.output.append("Gender: Male" + "\n");
        }
        Main.output.append("Level: " + level + "\n");
        Main.output.append("Saved Level Points: " + lvlPoints + "\n");
        Main.output.append("Experience: " + myEXP + "/" + (level * 100) + " (" + (100 * (myEXP/(level*100))) + "%)" + "\n");
        Main.output.append("\n");
        Main.output.append("Strength: " + strength[0] + " + (" + strength[1] + ") = " + strength[2] + "\n");
        Main.output.append("Intel: " + intel[0] + " + (" + intel[1] + ") = " + intel[2] + "\n");
        Main.output.append("Speed: " + speed[0] + " + (" + speed[1] + ") = " + speed[2] + "\n");
        Main.output.append("Body: " + body[0] + " + (" + body[1] + ") = " + body[2] + "\n");
        Main.output.append("Will: " + will[0] + " + (" + will[1] + ") = " + will[2] + "\n");
        Main.output.append("\n");
        Main.output.append("Armor: " + myArmor[0] + " + (" + myArmor[1] + ") = " + myArmor[2] + "\n");
        Main.output.append("Physical Damage: " + myDamage[0] + " + (" + myDamage[1] + ") = " + myDamage[2] + "\n");
        Main.output.append("Spellpower: " + mySPower[0] + " + (" + mySPower[1] + ") = " + mySPower[2] + "\n");
        Main.output.append("\n");
        Main.output.append("Bonus Gold from Enemies: +" + bonusGold + "%" + "\n");
        Main.output.append("Bonus Experience from Enemies: +" + bonusXP + "%" + "\n");
    }
    
    protected static void showLegacy(){
        Main.output.setText("The Continuing Legacy of " + name + "\n");
        Main.output.append("=======================" + "\n");
        Main.output.append("Gold in Pocket: " + myGP[0] + " GP" + "\n");
        Main.output.append("Lifetime Gold: " + myGP[1] + " GP" + "\n");
        Main.output.append("\n");
        Main.output.append("Enemies Slain: " + myKills + "\n");
        Main.output.append("Fights Fled: " + timesFled + "\n");
        Main.output.append("Passages Bought: " + tollsPaid + "\n");
        Main.output.append("Wagers Placed: " + wagersPlaced + "\n");
        Main.output.append("Times Resurrected: " + timesRezzed + "\n");
        Main.output.append("Graves Robbed: " + gravesRobbed + "\n");
        Main.output.append("Deepest Crypt Room Reached: " + cryptDepth + "\n");
    }
    
    protected static void showActiveBuffs(){
        Main.output.setText("Active Buffs on " + name + "\n");
        int count = 0;
        for(int i = 0; i< Effects.myBuffData.size(); i++){
            if((Integer)Effects.myBuffData.get(i)[1] > 0){
                Main.output.append(Effects.myBuffData.get(i)[0] + " (" + ((Integer)Effects.myBuffData.get(i)[1]) + " Turns Remaining)" + "\n");
                count ++;
            }
        }
        if(count == 0){
            Main.output.append("No Active Buffs.");
        }
    }
    
    protected static void showActiveEffects(){
        Main.output.setText("Active Effects on " + name + "\n");
        int count = 0;
        for(int i = 0; i< Effects.myEffectData.size(); i++){
            if((Boolean)Effects.myEffectData.get(i)[14] == true){
                Main.output.append(Effects.myEffectData.get(i)[0] + "\n");
                count ++;
            }
        }
        if(count == 0){
            Main.output.append("No Active Effects.");
        }
    }
    
    protected static void showProgress(){
        Main.output.setText("The Accomplisments of " + name + "\n");
        Main.output.append("=======================" + "\n");
        if(bridgeFound == true){
            Main.output.append("Located the Troll Inc. Bridge." + "\n");
        }
        if(cryptFound == true){
            Main.output.append("Discovered the Defiled Crypt's entrance." + "\n");
        }
    }
    
    protected static void expCheck(){
        if(myEXP >= (level*100)){
            myEXP -= (level*100);
            level ++;
            lvlPoints += 3;
            levelUpMenu();
        }else{
            Combat.postCombatMenu();
        }
    }
    
    protected static void levelUpMenu(){
        Main.output.setText("Congratulations! You've reached level " + level + "!" + "\n");
        Main.output.append("You have " + lvlPoints + " to spend on increasing stats!" + "\n");
        Main.output.append("Strength increases physical damage from attacks." + "\n");
        Main.output.append("Body increases maximum health." + "\n");
        Main.output.append("Speed increases the chance of double attacks." + "\n");
        Main.output.append("Intel increases spell damage and healing." + "\n");
        Main.output.append("Will increases maximum mana." + "\n");
        Main.output.append("You can also save any remaining points until your next level-up." + "\n");
        Main.screen = "levelUpMenu";
        Main.btn1.setText("Strength");
        Main.btn2.setText("Body");
        Main.btn3.setText("Speed");
        Main.btn4.setText("Intel");
        Main.btn5.setText("Will");
        Main.btn6.setText("Save Points");
    }
    
    protected static void levelUp(int choice){
        boolean skip = false;
        switch(choice){
            case 1: //Strength
                strength[0] ++;
                lvlPoints --;
                break;
            case 2: //Body
                body[0] ++;
                lvlPoints --;
                break;
            case 3: //Speed
                speed[0] ++;
                lvlPoints --;
                break;
            case 4: //Intel
                intel[0] ++;
                lvlPoints --;
                break;
            case 5: //Will
                will[0] ++;
                lvlPoints --;
                break;
            case 6: //Save Points
                int confirm = JOptionPane.showConfirmDialog(Main.window, "Are you sure you wish to save all remaining points?" + "\n" + "You won't be able to increase any more stats this level!", null, JOptionPane.YES_NO_OPTION);
                if(confirm == JOptionPane.YES_OPTION){
                    skip = true;
                }
                break;
        }
        updateStats();
        myHP[0] = myHP[1];
        myMP[0] = myMP[1];
        Main.updateLabels();
        if(lvlPoints > 0 && skip == false){
            levelUpMenu();
        }else{
            Combat.postCombatMenu();
        }
    }
    
}
