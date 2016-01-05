
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
import javax.swing.event.*;

public class Effects {
    
    /*              BUFFS AND EFFECTS
    *
    *                Data[x][y]
    * X = The ID. Each X value is a unique Buff or Effect.
    * Y = The Data Index. Each Y value is a unique data component.
    *
    *               BUFF VS EFFECT
    *
    * An Effect is a long lasting modifier of stats. Effects will not wear off, and must be removed through specific actions.
    * For example, the Turtle's Defense effect is applied when the player equips both the Turtle Armor and Turtle Shield. This will persist until one of these pieces is unequipped.
    * The Mind Fog effect, however, is sometimes inflicted by the Wolf's attacks. This effect persists until the player is cured by the Chapel healer, as it is a disease.
    *
    * A Buff is a temporary modifier, similar to an Effect. The main difference is that Buffs naturally wear off during combat. Every combat turn, the time left on any active buffs will decay.
    * Buffs can be reapplied, refreshing their duration. For example, if the player has 1 turn remaining on their Mage Armor buff, they can recast Mage Armor to refresh the buff to 5 turns.
    * Buffs only will decay during combat. Visiting shops, accessing menus, and exploring will not cause buffs to wear off quicker.
    *
    * Buffs and Effects both can modify Strength, Intel, Speed, Armor, Damage, Spellpower, current HP, current MP, enemy Gold drops, and EXP gains.
    * Only Effects can modify Body and Will stats. These occupy Data Index 1 for Body and Data Index 2 for Will.
    * For Buffs, Data Index 1 contains the data for Turns Remaining while Data Index 2 contains the Buff Length data.
    *
    *               NOTES
    *
    * A negative stat modifier DECREASES the stat. A positive stat modifier INCREASES the stat.
    * A negative HP Effect value DEALS damage each turn, like poison. A positive HP Effect value HEALS damage each turn, like regeneration.
    * A negative MP Effect value DRAINS mana each turn. A positive MP Effect value RESTORES mana each turn.
    * Buffs and Effects with HP Effect or MP Effect will affect the player any time Buffs and Effects change.
    * This means if you are poisoned and equip an item that grants an Effect, you get hurt. The Turns Remaining will not decrease though, so beware!
    * Bonus Gold Gain represents the percentage of ADDITIONAL gold dropped. This only affects gold dropped by defeated enemies. Bonus Gold is always rounded down, with a minimum bonus of 1 GP.
    * Bonus EXP Gain represents the percentage of ADDITIONAL experience gained. A value of 25 would mean the player receives 125% experience. Bonus EXP is always rounded down, with a minimum bonus of 1 XP.
    * Bonus Run Chance represents the additional bonus/penalty to running away. A vale of 2.5 would grant an additional 2.5% chance of escape, while -2.5 would reduce your chance by 2.5%.
    *
    */
    
    static ArrayList<Object[]> myBuffData = new ArrayList<Object[]>();      //Data for all Buffs that affect the player. These can be applied by the player or by enemies.
    static ArrayList<Object[]> enemyBuffData = new ArrayList<Object[]>();   //Data for all Buffs that affect enemies. These can be applied by the player or by the enemy.
    static ArrayList<Object[]> myEffectData = new ArrayList<Object[]>();    //Data for all Effects obtainable by the player
    
    /*
    //BUFFS
        myBuffData.get()[0]       enemyBuffData.get()[0];       //Name of the buff to be used by output. This is a constant value.
        myBuffData.get()[1]       enemyBuffData.get()[1];       //Number of turns remaining until the buff wears off. This value decreases every combat round while the buff is active.
        myBuffData.get()[2]       enemyBuffData.get()[2];       //Number of turns the buff lasts before wearing off. This is a constant value.
        myBuffData.get()[3]       enemyBuffData.get()[3];       //Number of levels the buff modifies Strength by. This is a constant value.
        myBuffData.get()[4]       enemyBuffData.get()[4];       //Number of levels the buff modifies Intel by. This is a constant value.
        myBuffData.get()[5]       enemyBuffData.get()[5];       //Number of levels the buff modifies Speed by. This is a constant value.
        myBuffData.get()[6]       enemyBuffData.get()[6];       //Amount the buff modifies Armor by. This is a constant value.
        myBuffData.get()[7]       enemyBuffData.get()[7];       //Amount the buff modifies Weapon Damage by. This is a constant value.
        myBuffData.get()[8]       enemyBuffData.get()[8];       //Amount the buff modifies Spellpower by. This is a constant value.
        myBuffData.get()[9]       enemyBuffData.get()[9];       //Amount to adjust the current health by each turn. This is a constant value.
        myBuffData.get()[10]      enemyBuffData.get()[10];      //Amount to adjust the current mana by each turn. This is a constant value.
        myBuffData.get()[11]      //ONLY CAN AFFECT PLAYERS     //Percentage of bonus Gold gained from defeating enemies. This is a constant value.
        myBuffData.get()[12]      //ONLY CAN AFFECT PLAYERS     //Percentage of bonus EXP gained from defeating enemies. This is a constant value.
        myBuffData.get()[13]      //ONLY CAN AFFECT PLAYERS     //Amount the buff modifies run chance by. This is a constant value.
        myBuffData.get()[14]      //ONLY CAN AFFECT PLAYERS     //True if buff is dispellable, otherwise false. This is a constant value.
    //EFFECTS
        myEffectData.get()[0]       //Name of the effect to be used by output. This is a constant value.
        myEffectData.get()[1]       //Number of levels the effect modifies Body by. This is a constant value.
        myEffectData.get()[2]       //Number of levels the effect modifies Will by. This is a constant value.
        myEffectData.get()[3]       //Number of levels the effect modifies Strength by. This is a constant value.
        myEffectData.get()[4]       //Number of levels the effect modifies Intel by. This is a constant value.
        myEffectData.get()[5]       //Number of levels the effect modifies Speed by. This is a constant value.
        myEffectData.get()[6]       //Amount the effect modifies Armor by. This is a constant value.
        myEffectData.get()[7]       //Amount the effect modifies Weapon Damage by. This is a constant value.
        myEffectData.get()[8]       //Amount the effect modifies Spellpower by. This is a constant value.
        myEffectData.get()[9]       //Amount to adjust the current health by each turn. This is a constant value.
        myEffectData.get()[10]      //Amount to adjust the current mana by each turn. This is a constant value.
        myEffectData.get()[11]      //Percentage of bonus Gold gained from defeating enemies. This is a constant value.
        myEffectData.get()[12]      //Percentage of bonus EXP gained from defeating enemies. This is a constant value.
        myEffectData.get()[13]      //Amount the effect modifies run chance by. This is a constant value.
        myEffectData.get()[14]      //True if effect is dispellable, otherwise false. This is a constant value.
        myEffectData.get()[15]      //True if the effect is active, otherwise false.
    */
    
    static void start(){
        setPlayerBuffData();
        setEnemyBuffData();
        setEffectData();
    }
    
    static void pBuffData(int i){
        myBuffData.get(i)[0] = null;
        myBuffData.get(i)[1] = 0;
        myBuffData.get(i)[2] = 0;
        myBuffData.get(i)[3] = 0;
        myBuffData.get(i)[4] = 0;
        myBuffData.get(i)[5] = 0;
        myBuffData.get(i)[6] = 0;
        myBuffData.get(i)[7] = 0;
        myBuffData.get(i)[8] = 0;
        myBuffData.get(i)[9] = 0;
        myBuffData.get(i)[10] = 0;
        myBuffData.get(i)[11] = 0;
        myBuffData.get(i)[12] = 0;
        myBuffData.get(i)[13] = 0;
        myBuffData.get(i)[14] = false;
    }
    
    static void eBuffData(int i){
        enemyBuffData.get(i)[0] = null;
        enemyBuffData.get(i)[1] = 0;
        enemyBuffData.get(i)[2] = 0;
        enemyBuffData.get(i)[3] = 0;
        enemyBuffData.get(i)[4] = 0;
        enemyBuffData.get(i)[5] = 0;
        enemyBuffData.get(i)[6] = 0;
        enemyBuffData.get(i)[7] = 0;
        enemyBuffData.get(i)[8] = 0;
        enemyBuffData.get(i)[9] = 0;
        enemyBuffData.get(i)[10] = 0;
    }
    
    static void pEffectData(int i){
        myEffectData.get(i)[0] = null;
        myEffectData.get(i)[1] = 0;
        myEffectData.get(i)[2] = 0;
        myEffectData.get(i)[3] = 0;
        myEffectData.get(i)[4] = 0;
        myEffectData.get(i)[5] = 0;
        myEffectData.get(i)[6] = 0;
        myEffectData.get(i)[7] = 0;
        myEffectData.get(i)[8] = 0;
        myEffectData.get(i)[9] = 0;
        myEffectData.get(i)[10] = 0;
        myEffectData.get(i)[11] = 0;
        myEffectData.get(i)[12] = 0;
        myEffectData.get(i)[13] = 0;
        myEffectData.get(i)[14] = false;
        myEffectData.get(i)[15] = false;
    }
    
    static void setPlayerBuffData(){
        myBuffData.add(new Object[15]);         pBuffData(0);   myBuffData.get(0)[0] = "Mage Armor";
        myBuffData.get(0)[2] = 5;       myBuffData.get(0)[6] = 2;
        myBuffData.add(new Object[15]);         pBuffData(1);   myBuffData.get(1)[0] = "Divine Protection";
        myBuffData.get(1)[2] = 10;      myBuffData.get(1)[6] = 5;
        myBuffData.add(new Object[15]);         pBuffData(2);   myBuffData.get(2)[0] = "Punctured Armor";
        myBuffData.get(2)[2] = 6;       myBuffData.get(2)[6] = -2;
        myBuffData.add(new Object[15]);         pBuffData(3);   myBuffData.get(3)[0] = "Chilled";
        myBuffData.get(3)[2] = 4;       myBuffData.get(3)[5] = -2;
        myBuffData.add(new Object[15]);         pBuffData(4);   myBuffData.get(4)[0] = "Infected Bite";
        myBuffData.get(4)[2] = 10;      myBuffData.get(4)[9] = -2;      myBuffData.get(4)[14] = true;      //Poison
        myBuffData.add(new Object[15]);         pBuffData(5);   myBuffData.get(5)[0] = "Lingering Health Potion";
        myBuffData.get(5)[2] = 11;      myBuffData.get(5)[9] = 5;
        myBuffData.add(new Object[15]);         pBuffData(6);   myBuffData.get(6)[0] = "Lingering Mana Potion";
        myBuffData.get(6)[2] = 11;      myBuffData.get(6)[10] = 5;
        myBuffData.add(new Object[15]);         pBuffData(7);   myBuffData.get(7)[0] = "Enlightened";
        myBuffData.get(7)[2] = 10;      myBuffData.get(7)[12] = 25;
        myBuffData.add(new Object[15]);         pBuffData(8);   myBuffData.get(8)[0] = "Hang-Over";
        myBuffData.get(8)[2] = 15;      myBuffData.get(8)[3] = -2;        myBuffData.get(8)[4] = -2;         myBuffData.get(8)[5] = -2;
    }
    
    static void setEnemyBuffData(){
        enemyBuffData.add(0, new Object[11]);       eBuffData(0);   enemyBuffData.get(0)[0] = "Turtle!";
        enemyBuffData.get(0)[2] = 2;    enemyBuffData.get(0)[6] = 10;
        enemyBuffData.add(1, new Object[11]);       eBuffData(1);   enemyBuffData.get(1)[0] = "GERATER PAHWAH!";
        enemyBuffData.get(1)[2] = 6;    enemyBuffData.get(1)[7] = 10;
    }
    
    static void setEffectData(){
        myEffectData.add(new Object[16]);        pEffectData(0);    myEffectData.get(0)[0] = "Divine Might";
        myEffectData.get(0)[3] = 2;
        myEffectData.add(new Object[16]);        pEffectData(1);    myEffectData.get(1)[0] = "Divine Insight";
        myEffectData.get(1)[4] = 2;
        myEffectData.add(new Object[16]);        pEffectData(2);    myEffectData.get(2)[0] = "Divine Haste";
        myEffectData.get(2)[5] = 2;
        myEffectData.add(new Object[16]);        pEffectData(3);    myEffectData.get(3)[0] = "Mind Fog";
        myEffectData.get(3)[4] = -1;    myEffectData.get(3)[14] = true;     //Disease
        myEffectData.add(new Object[16]);        pEffectData(4);    myEffectData.get(4)[0] = "DEBUG EFFECT";
        myEffectData.get(4)[11] = 50;   myEffectData.get(4)[12] = 50;
    }
    
    static void restart(){
        for(int i = 0; i < myBuffData.size(); i++){
            myBuffData.get(i)[1] = 0;
        }
        for(int i = 0; i < enemyBuffData.size(); i++){
            enemyBuffData.get(i)[1] = 0;
        }
        for(int i = 0; i < myEffectData.size(); i++){
            myEffectData.get(i)[15] = false;
        }
    }
    
    static void playerBuffUpdate(Boolean check){
        int modBody = 0; int modWill = 0;
        int modStrength = 0; int modIntel = 0; int modSpeed = 0;
        int modArmor = 0; int modDamage = 0; int modSPower = 0;
        int modHP = 0; int modMP = 0;
        int modGold = 0; int modXP = 0;
        double modRun = 0.0;
        for(int i = 0; i < myBuffData.size(); i++){
            if((Integer)myBuffData.get(i)[1] > 0){
                modStrength += (Integer)myBuffData.get(i)[3];
                modIntel += (Integer)myBuffData.get(i)[4];
                modSpeed += (Integer)myBuffData.get(i)[5];
                modArmor += (Integer)myBuffData.get(i)[6];
                modDamage += (Integer)myBuffData.get(i)[7];
                modSPower += (Integer)myBuffData.get(i)[8];
                modHP += (Integer)myBuffData.get(i)[9];
                modMP += (Integer)myBuffData.get(i)[10];
                modGold += (Integer)myBuffData.get(i)[11];
                modXP += (Integer)myBuffData.get(i)[12];
                modRun += (Double)myBuffData.get(i)[13];
                if(check == false){
                    myBuffData.get(i)[1] = ((Integer)myBuffData.get(i)[1] - 1);
                    if((Integer)myBuffData.get(i)[1] == 0){
                        Main.output.append(myBuffData.get(i)[0] + " wore off!" + "\n");
                    }
                }
            }
        }
        for(int i = 0; i < myEffectData.size(); i++){
            if((Boolean)myEffectData.get(i)[15] == true){
                modBody += (Integer)myEffectData.get(i)[1];
                modWill += (Integer)myEffectData.get(i)[2];
                modStrength += (Integer)myEffectData.get(i)[3];
                modIntel += (Integer)myEffectData.get(i)[4];
                modSpeed += (Integer)myEffectData.get(i)[5];
                modArmor += (Integer)myEffectData.get(i)[6];
                modDamage += (Integer)myEffectData.get(i)[7];
                modSPower += (Integer)myEffectData.get(i)[8];
                modHP += (Integer)myEffectData.get(i)[9];
                modMP += (Integer)myEffectData.get(i)[10];
                modGold += (Integer)myEffectData.get(i)[11];
                modXP += (Integer)myEffectData.get(i)[12];
                modRun += (Integer)myEffectData.get(i)[13];
            }
        }
        if(check==false){
                stats.myHP[0] += modHP; stats.myMP[0] += modMP;
        }
        stats.body[1] = modBody; stats.will[1] = modWill;
        stats.strength[1] = modStrength; stats.intel[1] = modIntel; stats.speed[1] = modSpeed;
        stats.myArmor[1] = modArmor; stats.myDamage[1] = modDamage; stats.mySPower[1] = modSPower;
        stats.bonusGold = modGold; stats.bonusXP = modXP; stats.bonusRun = modRun;
        Main.updateLabels();
    }
    
    static void enemyBuffUpdate(boolean check){
        int modStrength = 0; int modIntel = 0; int modSpeed = 0;
        int modArmor = 0; int modDamage = 0; int modSPower = 0;
        int modHP = 0;
        for(int i = 0; i < enemyBuffData.size(); i++){
            if((Integer)enemyBuffData.get(i)[1] > 0){
                modStrength += (Integer)enemyBuffData.get(i)[3];
                modIntel += (Integer)enemyBuffData.get(i)[4];
                modSpeed += (Integer)enemyBuffData.get(i)[5];
                modArmor += (Integer)enemyBuffData.get(i)[6];
                modDamage += (Integer)enemyBuffData.get(i)[7];
                modSPower += (Integer)enemyBuffData.get(i)[8];
                modHP += (Integer)enemyBuffData.get(i)[9];
                if(check == false){
                    enemyBuffData.get(i)[1] = ((Integer)enemyBuffData.get(i)[1] - 1);
                    if((Integer)enemyBuffData.get(i)[1] == 0){
                        Main.output.append(enemyBuffData.get(i)[0] + " wore off of " + Enemy.enemyName + "!" + "\n");
                    }
                }
            }
        }
        Enemy.enemyStrength[1] = modStrength; Enemy.enemyIntel[1] = modIntel; Enemy.enemySpeed[1] = modSpeed;
        Enemy.enemyArmor[1] = modArmor; Enemy.enemyDamage = modDamage; Enemy.enemySPower = modSPower;
        Enemy.enemyHP[0] += modHP;
    }
    
    static void enemyBuffClear(){
        for(int i = 0; i < enemyBuffData.size(); i++){
            enemyBuffData.get(i)[1] = 0;
        }
    }
    
    static void curePoisons(){
        Main.output.append("You are cured of any active poison effects!" + "\n");
        for(int i = 0; i < myBuffData.size(); i++){
            if((Boolean)myBuffData.get(i)[14] == true){
                myBuffData.get(i)[1] = 0;
            }
        }
        playerBuffUpdate(true);
    }
    
    static void cureAll(){
        Main.output.append("You are cleansed of all ailments, curses, and poisons!" + "\n");
        for(int i = 0; i < myBuffData.size(); i++){
            if((Boolean)myBuffData.get(i)[14] == true){
                myBuffData.get(i)[1] = 0;
            }
        }
        for(int i = 0; i < myEffectData.size(); i++){
            if((Boolean)myEffectData.get(i)[14] == true){
                myEffectData.get(i)[15] = false;
            }
        }
        playerBuffUpdate(true);
    }
    
}
