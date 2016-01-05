
package rpg2;

/**
 * @author Bloodcorpse
 * Version 1.4
 */

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import javax.swing.*;

public class Combat {

    static void combatMenu(){
        Main.screen = "combatMenu";
        Main.btn1.setText("Attack");
        Main.btn2.setText("Block");
        Main.btn3.setText("Run");
        Main.btn4.setText("Offensive Spells");
        Main.btn5.setText("Defensive Spells");
        Main.btn6.setText("Items");
    }

    static void myAttack(){
        Main.output.append("You swing at your foe. ");
        int dmg = Main.gen.nextInt((stats.strength[2] * 2) + 1);
        if (dmg == 0) {
            Main.output.append("You miss!");
        } else {
            dmg += stats.myDamage[2];
            dmg -= Enemy.enemyArmor[2];
            if (dmg <= 0) {
                Main.output.append("Your attack bounces off the enemy!");
                dmg = 0;
            } else {
                Main.output.append("You deal " + dmg + " damage!");
            }
        }
        Main.output.append("\n");
        if (Main.gen.nextInt(100) <= stats.speed[2]) {
            Main.output.append("Your speed allows you to strike a second time! ");
            int dmg2 = Main.gen.nextInt((stats.strength[2] * 2) + 1);
            if (dmg2 == 0) {
                Main.output.append("You miss!");
            } else {
                dmg2 += stats.myDamage[2];
                dmg2 -= Enemy.enemyArmor[2];
                if (dmg2 <= 0) {
                    Main.output.append("Your attack bounces off the enemy!");
                } else {
                    Main.output.append("You deal " + dmg2 + " damage!");
                    dmg += dmg2;
                }
            }
        }
        Main.output.append("\n");
        Enemy.enemyHP[0] -= dmg;
        enemyHealthCheck(false);
    }

    static void myBlock(){
        Main.output.append("You ready yourself to block the next attack.");
        Main.output.append("\n" + "\n");
        enemyHealthCheck(true);
    }

    static void offSpellMenu(){
        Main.screen = "offSpellMenu";
        Main.btn1.setText("Fire Ball (10 MP)");
        Main.btn2.setText("Magic Missile (20 MP)");
        Main.btn3.setText("Nevermind");
        Main.btn4.setText(" ");
        Main.btn5.setText(" ");
        Main.btn6.setText(" ");
    }

    static void defSpellMenu(){
        Main.screen = "defSpellMenu";
        Main.btn1.setText("Mage Armor (10 MP)");
        Main.btn2.setText("Heal (20 MP)");
        Main.btn3.setText("Nevermind");
        Main.btn4.setText(" ");
        Main.btn5.setText(" ");
        Main.btn6.setText(" ");
    }

    static void castSpell(int type, int spell){
        int spellpower = 0;
        if(type == 1){          //Offensive Spells
            switch(spell){
                case 1: //Fireball
                    if(stats.myMP[0] >= 10){
                        stats.myMP[0] -= 10;
                        spellpower = (stats.intel[2] + stats.mySPower[2]);
                        Main.output.append("You blast the enemy with a fireball, dealing " + spellpower + " damage!" + "\n");
                        Enemy.enemyHP[0] -= spellpower;
                        enemyHealthCheck(false);
                    }else{
                        Main.output.append("You don't have enough mana to cast Fireball." + "\n");
                    }
                    break;
                case 2: //Magic Missile
                    if(stats.myMP[0] >= 20){
                        stats.myMP[0] -= 20;
                        spellpower = (15 + stats.intel[2] + stats.mySPower[2]);
                        Main.output.append("You launch a Magic Missile at the enemy, dealing " + spellpower + " damage!" + "\n");
                        Enemy.enemyHP[0] -= spellpower;
                        enemyHealthCheck(false);
                    }else{
                        Main.output.append("You don't have enough mana to cast Magic Missile." + "\n");
                    }
                    break;
            }
        }else if(type == 2){    //Defensive Spells
            switch(spell){
                case 1: //Mage Armor
                    if(stats.myMP[0] >= 10){
                        stats.myMP[0] -= 10;
                        Main.output.append("You conjure forth a magical barrier to bolster your armor!" + "\n");
                        Effects.playerBuffUpdate(true);
                        enemyHealthCheck(false);
                    }else{
                        Main.output.append("You don't have enough mana to cast Mage Armor." + "\n");
                    }
                    break;
                case 2: //Heal
                    if(stats.myMP[0] >= 20){
                        stats.myMP[0] -= 20;
                        spellpower = (25 + stats.intel[2] + stats.mySPower[2]);
                        Main.output.append("Magical energies mend your wounds, healing " + spellpower + " damage!" + "\n");
                        stats.myHP[0] += spellpower;
                        enemyHealthCheck(false);
                    }else{
                        Main.output.append("You don't have enough mana to cast Heal." + "\n");
                    }
                    break;
            }
        }
    }

    static void useItem(){

    }

    static void runAway(){
        Main.output.append("You attempt to flee from the " + Enemy.enemyName + "! ");
        double runThreshold = 25.0 + (stats.speed[2] - Enemy.enemySpeed[2]) + stats.bonusRun;
        if(runThreshold > 90.0){
            runThreshold = 90.0;
        }
        int attempt = Main.gen.nextInt(101);
        if(attempt >= (100.0 - runThreshold)){
            Main.output.append("\n" + "You managed to escape from the " + Enemy.enemyName + "!" + "\n");
            stats.timesFled++;
            postCombatMenu();
        }else{
            Main.output.append("You fail to escape from the " + Enemy.enemyName + "!" + "\n\n");
            Main.output.append("Enemy Health: " + Enemy.enemyHP[0] +"/" + Enemy.enemyHP[1] + "\n");
            enemyAttack(false);
        }
    }

    static void enemyHealthCheck(Boolean block){
        Main.output.append("Enemy Health: " + Enemy.enemyHP[0] +"/" + Enemy.enemyHP[1] + "\n");
        if(Enemy.enemyHP[0] > 0){
            enemyAction(block);
        }else{
            Enemy.enemyAlive = false;
            enemyDeath();
        }
    }

    static void enemyAction(Boolean block){
        int action = Main.gen.nextInt(10);
        switch(action){
            case 0: enemyAttack(block); break;      //Normal Attack
            case 1: enemyCast(block, 0); break;     //buffTable[0] buff
            case 2: enemyAttack(block); break;      //Normal Attack
            case 3: enemyCast(block, 1); break;     //buffTable[1] buff
            case 4: enemyAttack(block); break;      //Normal Attack
            case 5: enemyCast(block, 2); break;     //buffTable[2] buff
            case 6: enemyAttack(block); break;      //Normal Attack
            case 7: enemyCast(block, 3); break;     //buffTable[3] buff
            case 8: enemyAttack(block); break;      //Normal Attack
            case 9: enemyCast(block, 4); break;     //buffTable[4] buff
        }
    }

    static void enemyCast(Boolean block, int buff){
        if(Enemy.buffTable[buff][0] == 0){
            //No Buff at this index, attack normally
            enemyAttack(block);
        }else{
            //Buff at this index
            int index = Enemy.buffTable[buff][1];
            if(Enemy.buffTable[buff][0] == 1){
                if(Enemy.buffTable[buff][2] == 1){
                    //Casted Buff on Player
                    Main.output.append(Enemy.enemyName + " inflicts " + Effects.myBuffData.get(index)[0] + " on you!");
                    Main.output.append("\n" + "\n");
                    Effects.myBuffData.get(index)[1] = Effects.myBuffData.get(index)[2];
                    Effects.playerBuffUpdate(false);
                }else{
                    //On Hit Buff on Player
                    enemyAttack(block, 1, index);
                }
            }else if(Enemy.buffTable[buff][0] == 2){
                if(Enemy.buffTable[buff][2] == 1){
                    //Casted Effect on Player
                    Main.output.append(Enemy.enemyName + " inflicts " + Effects.myEffectData.get(index)[0] + " on you!");
                    Main.output.append("\n" + "\n");
                    Effects.myEffectData.get(index)[14] = true;
                    Effects.playerBuffUpdate(false);
                }else{
                    //On Hit Effect on Player
                    enemyAttack(block, 2, index);
                }
            }else if(Enemy.buffTable[buff][0] == 3){
                if(Enemy.buffTable[buff][2] == 1){
                    //Casted Buff on Self
                    Main.output.append(Enemy.enemyName + " gains " + Effects.enemyBuffData.get(index)[0] + "!");
                    Main.output.append("\n" + "\n");
                    Effects.enemyBuffData.get(index)[1] = Effects.enemyBuffData.get(index)[2];
                    Effects.enemyBuffUpdate(false);
                }else{
                    //On Hit Buff on Self
                    enemyAttack(block, 3, index);
                }
            }
        }
    }

    static void enemyAttack(Boolean block) {
        Main.output.append("The " + Enemy.enemyName + " swings at you. ");
        if (block == true) {
            //Blocked attack, no damage
            Main.output.append("You block the attack!");
        } else {
            int enemyDmg = Main.gen.nextInt((Enemy.enemyStrength[2]*5) + 1);
            if (enemyDmg == 0) {
                //Missed attack, no damage
                Main.output.append("The attack misses!");
            } else {
                enemyDmg += Enemy.enemyDamage;
                enemyDmg -= stats.myArmor[2];
                if (enemyDmg <= 0) {
                    //Armor absorbed attack, no damage
                    Main.output.append("The attack bounces off your armor!");
                } else {
                    //Deals damage
                    Main.output.append("The attack deals " + enemyDmg + " damage!");
                    stats.myHP[0] -= enemyDmg;
                }
            }
        }
        Main.output.append("\n" + "\n");
        Effects.enemyBuffUpdate(false);
        Effects.playerBuffUpdate(false);
    }

    static void enemyAttack(Boolean block, int target, int index) {
        Main.output.append("The " + Enemy.enemyName + " swings at you. ");
        if (block == true) {
            //Blocked attack, no damage, doesn't apply buff
            Main.output.append("You block the attack!");
        } else {
            int enemyDmg = Main.gen.nextInt((Enemy.enemyStrength[2]*5) + 1);
            if (enemyDmg == 0) {
                //Missed attack, no damage, doesn't apply buff
                Main.output.append("The attack misses!");
            } else {
                enemyDmg += Enemy.enemyDamage;
                enemyDmg -= stats.myArmor[2];
                if (enemyDmg <= 0) {
                    //Armor absorbed attack, no damage, doesn't apply buff
                    Main.output.append("The attack bounces off your armor!");
                } else {
                    //Deal damage, does apply buff/effect
                    Main.output.append("The attack deals " + enemyDmg + " damage!" + "\n");
                    switch(target){
                        case 1:
                            Effects.myBuffData.get(index)[1] = Effects.myBuffData.get(index)[2];
                            Main.output.append(Enemy.enemyName + " inflicts " + Effects.myBuffData.get(index)[0] + " on you!");
                            break;
                        case 2:
                            Effects.myEffectData.get(index)[14] = true;
                            Main.output.append(Enemy.enemyName + " inflicts " + Effects.myEffectData.get(index)[0] + " on you!");
                            break;
                        case 3:
                            Effects.enemyBuffData.get(index)[1] = Effects.enemyBuffData.get(index)[2];
                            Main.output.append(Enemy.enemyName + " gains " + Effects.enemyBuffData.get(index)[0] + "!");
                            break;
                    }
                    stats.myHP[0] -= enemyDmg;
                    Main.output.append("\n" + "\n");
                    Effects.enemyBuffUpdate(false);
                    Effects.playerBuffUpdate(false);
                }
            }
        }
    }

    static void enemyDeath(){
        if(Enemy.enemyAlive == false){
            Main.output.append("\n" + "The " + Enemy.enemyName + " has been defeated!" + "\n");
            lootEnemy();
            int lootXP = Enemy.enemyXP;
            if(stats.bonusXP >0 ){
                double bonus = Math.floor(lootXP * ((double)stats.bonusXP / 100.0));
                if(bonus < 1){
                    bonus = 1;
                }
                lootXP += (int)bonus;
            }
            Main.output.append("You earn " + lootXP + " experience from your battle!" + "\n");
            stats.myEXP += lootXP;
            stats.myKills++;
            Main.updateLabels();
            stats.expCheck();
        }else{
            JOptionPane.showMessageDialog(Main.window, ">> REPORT ME! <<" + "\n" + "enemyDeath() was called, but the enemy is alive!");
            postCombatMenu();
        }
    }
    
    static void postCombatMenu(){
        Main.screen = "postCombat";
        if(Main.zone == "Town"){
            Main.btn1.setText("Stay in Town");
            Main.btn2.setText("Return to Camp");
            Main.btn3.setText(" ");
            Main.btn4.setText(" ");
            Main.btn5.setText(" ");
            Main.btn6.setText(" ");
        }else if(Main.zone == "Sewers"){
            Main.btn1.setText("Stay in Sewers");
            Main.btn2.setText("Return to Camp");
            Main.btn3.setText(" ");
            Main.btn4.setText(" ");
            Main.btn5.setText(" ");
            Main.btn6.setText(" ");
        }else if(Main.zone == "River Bridge"){
            if(Enemy.enemyAlive == false){
                River.postCombatTrollMenu();
            }else{
                Main.btn1.setText("Continue Exploring");
                Main.btn2.setText("Return to Camp");
                Main.btn3.setText(" ");
                Main.btn4.setText(" ");
                Main.btn5.setText(" ");
                Main.btn6.setText(" ");
            }
        }else{
            Main.btn1.setText("Continue Exploring");
            Main.btn2.setText("Return to Camp");
            Main.btn3.setText(" ");
            Main.btn4.setText(" ");
            Main.btn5.setText(" ");
            Main.btn6.setText(" ");
        }
    }

    static void lootEnemy(){
        //Base Gold Loot
        stats.lootGP = Main.gen.nextInt(Enemy.enemyGP) + 1; //Generates gold loot amount
        //Bonus Gold Loot
        if(stats.bonusGold > 0){
            double bonus = Math.floor(stats.lootGP * ((double)stats.bonusGold / 100.0));
            if(bonus < 1){
                bonus = 1;
            }
            stats.lootGP += (int)bonus;
        }
        Main.output.append("You loot " + stats.lootGP + " gold pieces from the " + Enemy.enemyName + "'s corpse!" + "\n");
        stats.myGP[0] += stats.lootGP;  //Adds gold to current gold
        stats.myGP[1] += stats.lootGP;  //Adds gold to lifetime gold

        //Item Loot
        int i = Main.gen.nextInt(10);   //Randomly selects a loot table entry
        if(Enemy.lootTable[i][0] == 0){
            //No Item Loot
        }else{
            //Item Loot
            int lootType = Enemy.lootTable[i][0];
            int lootIndex = Enemy.lootTable[i][1];
            int lootNum = Enemy.lootTable[i][2];
            String lootName = null;
            switch(lootType){
                case 1: Inv.armorNum.get(lootIndex)[0] += lootNum; lootName = Inv.armorName.get(lootIndex); break;
                case 2: Inv.weaponNum.get(lootIndex)[0] += lootNum; lootName = Inv.weaponName.get(lootIndex); break;
                case 3: Inv.offhandNum.get(lootIndex)[0] += lootNum; lootName = Inv.offhandName.get(lootIndex); break;
                case 4: Inv.toolNum.get(lootIndex)[0] += lootNum; lootName = Inv.toolName.get(lootIndex); break;
                case 5: Inv.potionNum.get(lootIndex)[0] += lootNum; lootName = Inv.potionName.get(lootIndex); break;
                case 6: Inv.foodNum.get(lootIndex)[0] += lootNum; lootName = Inv.foodName.get(lootIndex); break;
                case 7: Inv.generalNum.get(lootIndex)[0] += lootNum; lootName = Inv.generalName.get(lootIndex); break;
            }
            Main.output.append("You loot " + lootNum + " " + lootName + " from the " + Enemy.enemyName + "'s corpse!" + "\n");
        }
    }

}
