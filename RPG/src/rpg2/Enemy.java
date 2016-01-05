
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

public class Enemy {
    //Enemy Data
    static String enemyName;                //Name of enemy used in text
    static int enemyHP[] = {0, 0};          //Health of enemy, [0] = current health, [1] = max health
    static int[] enemyStrength = {0, 0, 0}; //Strength of enemy
    static int[] enemyIntel = {0, 0, 0};    //Intel of enemy
    static int[] enemySpeed = {0, 0, 0};    //Speed of enemy
    static int enemyDamage = 0;             //Bonus damage of enemy attack
    static int[] enemyArmor = {0, 0, 0};    //Armor of enemy (reduces damage taken)
    static int enemySPower = 0;             //Bonus spelldamage of enemy
    static int enemyGP;                     //Maximum gold awarded for defeating enemy
    static int enemyXP;                     //XP awarded for defeating enemy
    static boolean enemyAlive;              //Is the enemy alive?
    
    /*Enemy Loot Table
    * Value 1: An integer to define the item's array.
    * 0 = no drop 1 = armor, 2 = weapon, 3 = offhand, 4 = tool, 5 = potion, 6 = food, 7 = general
    * Value 2: Index of the item within the appropriate array
    * Value 3: Number of item to be looted.
    */
    static int lootTable[][] = new int[10][3];
    
    /*Enemy Buff Table
    * Value 1: An integer to define the buff's array.
    * 0 = No buff, 1 = player buffs, 2 = player effects, 3 = enemy buffs
    * Value 2: An integer to define the buff's index within the target's buff array.
    * Value 3: An integer to define the buff's application type. 0 = On Hit, 1 = Casted
    *
    * Notes:
    * Casted buffs take up the entire turn, but are 100% chance to apply.
    * On Hit buffs do not take up the entire turn, meaning the enemy still attacks.
    * On Hit buffs must deal damage with their attack in order to apply the buff.
    * Blocking, bouncing off armor, and missing prevent the buff from being applied.
    */
    static int buffTable[][] = new int[5][3];

    public Enemy(String type) {
        Effects.enemyBuffClear();
        if (type.equals("goblin")) {
            goblin();
        } else if (type.equals("wolf")) {
            wolf();
        } else if (type.equals("mugger")) {
            mugger();
        } else if (type.equals("snapping turtle")){
            snapTurtle();
        } else if (type.equals("troll")){
            troll();
        } else if (type.equals("hermit")){
            hermit();
        } else if (type.equals("yeti")){
            yeti();
        } else if (type.equals("sewerRat")){
            sewerRat();
        } else if (type.equals("giantRat")){
            giantRat();
        } else if (type.equals("alligator")){
            alligator();
        } else if (type.equals("vagrant")){
            vagrant();
        } else if (type.equals("drunkenPatron")){
            drunkenPatron();
        } else if (type.equals("shamblingZombie")){
            shamblingZombie();
        } else if (type.equals("skeleton")){
            skeleton();
        } else if (type.equals("restlessSpirit")){
            restlessSpirit();
        } else if (type.equals("graverobber")){
            graverobber();
        } else if (type.equals("scavenger")){
            scavenger();
        } else if (type.equals("necroAdept")){
            necroAdept();
        } else if (type.equals("corpseFiend")){
            corpseFiend();
        } else if (type.equals("darkPriest")){
            darkPriest();
        } else if (type.equals("lich")){
            lich();
        } else if (type.equals("skeletalGuard")){
            skeletalGuard();
        } else if (type.equals("rottingAdventurer")){
            rottingAdventurer();
        } else if (type.equals("tombRaider")){
            tombRaider();
        }
    }

    private void goblin() {
        enemyName = "Goblin";
        enemyHP[0] = 50; enemyHP[1] = 50;
        enemyStrength[0] = 2; enemyStrength[1] = 0; enemyStrength[2] = enemyStrength[0] + enemyStrength[1];
        enemyIntel[0] = 0; enemyIntel[1] = 0; enemyIntel[2] = enemyIntel[0] + enemyIntel[1];
        enemySpeed[0] = 5; enemySpeed[1] = 0; enemySpeed[2] = enemySpeed[0] + enemySpeed[1];
        enemyDamage = 0;
        enemyArmor[0] = 0; enemyArmor[1] = 0; enemyArmor[2] = enemyArmor[0] + enemyArmor[1];
        enemySPower = 0;
        enemyGP = 15;
        enemyXP = 5;
        enemyAlive = true;
        //LOOT
        //100% chance to drop nothing
        lootTable[0][0] = 0; lootTable[0][1] = 0; lootTable[0][2] = 0;
        lootTable[1][0] = 0; lootTable[1][1] = 0; lootTable[1][2] = 0;
        lootTable[2][0] = 0; lootTable[2][1] = 0; lootTable[2][2] = 0;
        lootTable[3][0] = 0; lootTable[3][1] = 0; lootTable[3][2] = 0;
        lootTable[4][0] = 0; lootTable[4][1] = 0; lootTable[4][2] = 0;
        lootTable[5][0] = 0; lootTable[5][1] = 0; lootTable[5][2] = 0;
        lootTable[6][0] = 0; lootTable[6][1] = 0; lootTable[6][2] = 0;
        lootTable[7][0] = 0; lootTable[7][1] = 0; lootTable[7][2] = 0;
        lootTable[8][0] = 0; lootTable[8][1] = 0; lootTable[8][2] = 0;
        lootTable[9][0] = 0; lootTable[9][1] = 0; lootTable[9][2] = 0;
        //BUFFS AND EFFECTS
        buffTable[0][0] = 0; buffTable[0][1] = 0; buffTable[0][2] = 0; //Nothing
        buffTable[1][0] = 0; buffTable[1][1] = 0; buffTable[1][2] = 0; //Nothing
        buffTable[2][0] = 0; buffTable[2][1] = 0; buffTable[2][2] = 0; //Nothing
        buffTable[3][0] = 0; buffTable[3][1] = 0; buffTable[3][2] = 0; //Nothing
        buffTable[4][0] = 0; buffTable[4][1] = 0; buffTable[4][2] = 0; //Nothing
    }
    
    private void wolf() {
        enemyName = "Wolf";
        enemyHP[0] = 75; enemyHP[1] = 75;
        enemyStrength[0] = 3; enemyStrength[1] = 0; enemyStrength[2] = enemyStrength[0] + enemyStrength[1];
        enemyIntel[0] = 0; enemyIntel[1] = 0; enemyIntel[2] = enemyIntel[0] + enemyIntel[1];
        enemySpeed[0] = 10; enemySpeed[1] = 0; enemySpeed[2] = enemySpeed[0] + enemySpeed[1];
        enemyDamage = 0;
        enemyArmor[0] = 0; enemyArmor[1] = 0; enemyArmor[2] = enemyArmor[0] + enemyArmor[1];
        enemyGP = 15;
        enemyXP = 10;
        enemyAlive = true;
        //50% chance to drop 1 Wolf Pelt, 50% chance to drop nothing
        lootTable[0][0] = 0; lootTable[0][1] = 0; lootTable[0][2] = 0;
        lootTable[1][0] = 7; lootTable[1][1] = 0; lootTable[1][2] = 1;
        lootTable[2][0] = 0; lootTable[2][1] = 0; lootTable[2][2] = 0;
        lootTable[3][0] = 7; lootTable[3][1] = 0; lootTable[3][2] = 1;
        lootTable[4][0] = 0; lootTable[4][1] = 0; lootTable[4][2] = 0;
        lootTable[5][0] = 7; lootTable[5][1] = 0; lootTable[5][2] = 1;
        lootTable[6][0] = 0; lootTable[6][1] = 0; lootTable[6][2] = 0;
        lootTable[7][0] = 7; lootTable[7][1] = 0; lootTable[7][2] = 1;
        lootTable[8][0] = 0; lootTable[8][1] = 0; lootTable[8][2] = 0;
        lootTable[9][0] = 7; lootTable[9][1] = 0; lootTable[9][2] = 1;
        //BUFFS AND EFFECTS
        buffTable[0][0] = 2; buffTable[0][1] = 3; buffTable[0][2] = 0; //On Hit: Mind Fog Effect on Player
        buffTable[1][0] = 0; buffTable[1][1] = 0; buffTable[1][2] = 0; //Nothing
        buffTable[2][0] = 0; buffTable[2][1] = 0; buffTable[2][2] = 0; //Nothing
        buffTable[3][0] = 0; buffTable[3][1] = 0; buffTable[3][2] = 0; //Nothing
        buffTable[4][0] = 0; buffTable[4][1] = 0; buffTable[4][2] = 0; //Nothing
    }

    private void mugger() {
        enemyName = "Mugger";
        enemyHP[0] = 75;
        enemyHP[1] = 75;
        enemyStrength[0] = 3; enemyStrength[1] = 0; enemyStrength[2] = enemyStrength[0] + enemyStrength[1];
        enemyIntel[0] = 0; enemyIntel[1] = 0; enemyIntel[2] = enemyIntel[0] + enemyIntel[1];
        enemySpeed[0] = 10; enemySpeed[1] = 0; enemySpeed[2] = enemySpeed[0] + enemySpeed[1];
        enemyDamage = 0;
        enemyArmor[0] = 1; enemyArmor[1] = 0; enemyArmor[2] = enemyArmor[0] + enemyArmor[1];
        enemyGP = 25;
        enemyXP = 15;
        enemyAlive = true;
        //10% chance to drop 1 Knife, 90% chance to drop nothing
        lootTable[0][0] = 0; lootTable[0][1] = 0; lootTable[0][2] = 0;
        lootTable[1][0] = 0; lootTable[1][1] = 0; lootTable[1][2] = 0;
        lootTable[2][0] = 0; lootTable[2][1] = 0; lootTable[2][2] = 0;
        lootTable[3][0] = 0; lootTable[3][1] = 0; lootTable[3][2] = 0;
        lootTable[4][0] = 0; lootTable[4][1] = 0; lootTable[4][2] = 0;
        lootTable[5][0] = 0; lootTable[5][1] = 0; lootTable[5][2] = 0;
        lootTable[6][0] = 0; lootTable[6][1] = 0; lootTable[6][2] = 0;
        lootTable[7][0] = 0; lootTable[7][1] = 0; lootTable[7][2] = 0;
        lootTable[8][0] = 0; lootTable[8][1] = 0; lootTable[8][2] = 0;
        lootTable[9][0] = 2; lootTable[9][1] = 0; lootTable[9][2] = 1;
        //BUFFS AND EFFECTS
        buffTable[0][0] = 0; buffTable[0][1] = 0; buffTable[0][2] = 0; //Nothing
        buffTable[1][0] = 0; buffTable[1][1] = 0; buffTable[1][2] = 0; //Nothing
        buffTable[2][0] = 0; buffTable[2][1] = 0; buffTable[2][2] = 0; //Nothing
        buffTable[3][0] = 0; buffTable[3][1] = 0; buffTable[3][2] = 0; //Nothing
        buffTable[4][0] = 0; buffTable[4][1] = 0; buffTable[4][2] = 0; //Nothing
    }
    
    private void drunkenPatron() {
        enemyName = "Drunken Patron";
        enemyHP[0] = 75;
        enemyHP[1] = 75;
        enemyStrength[0] = 3; enemyStrength[1] = 0; enemyStrength[2] = enemyStrength[0] + enemyStrength[1];
        enemyIntel[0] = 0; enemyIntel[1] = 0; enemyIntel[2] = enemyIntel[0] + enemyIntel[1];
        enemySpeed[0] = 7; enemySpeed[1] = 0; enemySpeed[2] = enemySpeed[0] + enemySpeed[1];
        enemyDamage = 5;
        enemyArmor[0] = 1; enemyArmor[1] = 0; enemyArmor[2] = enemyArmor[0] + enemyArmor[1];
        enemyGP = 30;
        enemyXP = 20;
        enemyAlive = true;
        //10% chance to drop 1 Cheap Wine, 10% chance to drop 1 Mead
        //10% chance to drop 1 Cider, 10% to drop 1 Dragonsbreath Ale
        //10% chance to drop 1 Broken Bottle, 50% chance to drop nothing
        lootTable[0][0] = 6; lootTable[0][1] = 7; lootTable[0][2] = 1;
        lootTable[1][0] = 6; lootTable[1][1] = 5; lootTable[1][2] = 1;
        lootTable[2][0] = 2; lootTable[2][1] = 6; lootTable[2][2] = 1;
        lootTable[3][0] = 6; lootTable[3][1] = 8; lootTable[3][2] = 1;
        lootTable[4][0] = 6; lootTable[4][1] = 6; lootTable[4][2] = 1;
        lootTable[5][0] = 0; lootTable[5][1] = 0; lootTable[5][2] = 0;
        lootTable[6][0] = 0; lootTable[6][1] = 0; lootTable[6][2] = 0;
        lootTable[7][0] = 0; lootTable[7][1] = 0; lootTable[7][2] = 0;
        lootTable[8][0] = 0; lootTable[8][1] = 0; lootTable[8][2] = 0;
        lootTable[9][0] = 0; lootTable[9][1] = 0; lootTable[9][2] = 0;
        //BUFFS AND EFFECTS
        buffTable[0][0] = 0; buffTable[0][1] = 0; buffTable[0][2] = 0; //Nothing
        buffTable[1][0] = 0; buffTable[1][1] = 0; buffTable[1][2] = 0; //Nothing
        buffTable[2][0] = 0; buffTable[2][1] = 0; buffTable[2][2] = 0; //Nothing
        buffTable[3][0] = 0; buffTable[3][1] = 0; buffTable[3][2] = 0; //Nothing
        buffTable[4][0] = 0; buffTable[4][1] = 0; buffTable[4][2] = 0; //Nothing
    }
    
    private void snapTurtle() {
        enemyName = "Snapping Turtle";
        enemyHP[0] = 50;
        enemyHP[1] = 50;
        enemyStrength[0] = 2; enemyStrength[1] = 0; enemyStrength[2] = enemyStrength[0] + enemyStrength[1];
        enemyIntel[0] = 0; enemyIntel[1] = 0; enemyIntel[2] = enemyIntel[0] + enemyIntel[1];
        enemySpeed[0] = 3; enemySpeed[1] = 0; enemySpeed[2] = enemySpeed[0] + enemySpeed[1];
        enemyDamage = 0;
        enemyArmor[0] = 7; enemyArmor[1] = 0; enemyArmor[2] = enemyArmor[0] + enemyArmor[1];
        enemyGP = 25;
        enemyXP = 15;
        enemyAlive = true;
        //50% chance to drop 1 Turtle Shell, 50% chance to drop nothing
        lootTable[0][0] = 7; lootTable[0][1] = 3; lootTable[0][2] = 1;
        lootTable[1][0] = 7; lootTable[1][1] = 3; lootTable[1][2] = 1;
        lootTable[2][0] = 7; lootTable[2][1] = 3; lootTable[2][2] = 1;
        lootTable[3][0] = 7; lootTable[3][1] = 3; lootTable[3][2] = 1;
        lootTable[4][0] = 7; lootTable[4][1] = 3; lootTable[4][2] = 1;
        lootTable[5][0] = 0; lootTable[5][1] = 0; lootTable[5][2] = 0;
        lootTable[6][0] = 0; lootTable[6][1] = 0; lootTable[6][2] = 0;
        lootTable[7][0] = 0; lootTable[7][1] = 0; lootTable[7][2] = 0;
        lootTable[8][0] = 0; lootTable[8][1] = 0; lootTable[8][2] = 0;
        lootTable[9][0] = 0; lootTable[9][1] = 0; lootTable[9][2] = 0;
        //BUFFS AND EFFECTS
        buffTable[0][0] = 3; buffTable[0][1] = 0; buffTable[0][2] = 1; //Casted: Turtle! on Self
        buffTable[1][0] = 0; buffTable[1][1] = 0; buffTable[1][2] = 0; //Nothing
        buffTable[2][0] = 0; buffTable[2][1] = 0; buffTable[2][2] = 0; //Nothing
        buffTable[3][0] = 0; buffTable[3][1] = 0; buffTable[3][2] = 0; //Nothing
        buffTable[4][0] = 0; buffTable[4][1] = 0; buffTable[4][2] = 0; //Nothing
    }
    
    private void troll() {
        enemyName = "Troll";
        enemyHP[0] = 100;
        enemyHP[1] = 100;
        enemyStrength[0] = 4; enemyStrength[1] = 0; enemyStrength[2] = enemyStrength[0] + enemyStrength[1];
        enemyIntel[0] = 0; enemyIntel[1] = 0; enemyIntel[2] = enemyIntel[0] + enemyIntel[1];
        enemySpeed[0] = 10; enemySpeed[1] = 0; enemySpeed[2] = enemySpeed[0] + enemySpeed[1];
        enemyDamage = 0;
        enemyArmor[0] = 2; enemyArmor[1] = 0; enemyArmor[2] = enemyArmor[0] + enemyArmor[1];
        enemyGP = 50;
        enemyXP = 25;
        enemyAlive = true;
        //40% chance to drop 1 Troll Tooth, 10% chance to drop 2 Troll Tooth, 50% chance to drop nothing.
        lootTable[0][0] = 7; lootTable[0][1] = 4; lootTable[0][2] = 1;
        lootTable[1][0] = 0; lootTable[1][1] = 0; lootTable[1][2] = 0;
        lootTable[2][0] = 7; lootTable[2][1] = 4; lootTable[2][2] = 1;
        lootTable[3][0] = 0; lootTable[3][1] = 0; lootTable[3][2] = 0;
        lootTable[4][0] = 7; lootTable[4][1] = 4; lootTable[4][2] = 2;
        lootTable[5][0] = 0; lootTable[5][1] = 0; lootTable[5][2] = 0;
        lootTable[6][0] = 7; lootTable[6][1] = 4; lootTable[6][2] = 1;
        lootTable[7][0] = 0; lootTable[7][1] = 0; lootTable[7][2] = 0;
        lootTable[8][0] = 7; lootTable[8][1] = 4; lootTable[8][2] = 1;
        lootTable[9][0] = 0; lootTable[9][1] = 0; lootTable[9][2] = 0;
        //BUFFS AND EFFECTS
        buffTable[0][0] = 0; buffTable[0][1] = 0; buffTable[0][2] = 0; //Nothing
        buffTable[1][0] = 0; buffTable[1][1] = 0; buffTable[1][2] = 0; //Nothing
        buffTable[2][0] = 0; buffTable[2][1] = 0; buffTable[2][2] = 0; //Nothing
        buffTable[3][0] = 0; buffTable[3][1] = 0; buffTable[3][2] = 0; //Nothing
        buffTable[4][0] = 0; buffTable[4][1] = 0; buffTable[4][2] = 0; //Nothing
    }
    
    private void hermit() {
        enemyName = "Crazy Hermit";
        enemyHP[0] = 110;
        enemyHP[1] = 110;
        enemyStrength[0] = 4; enemyStrength[1] = 0; enemyStrength[2] = enemyStrength[0] + enemyStrength[1];
        enemyIntel[0] = 0; enemyIntel[1] = 0; enemyIntel[2] = enemyIntel[0] + enemyIntel[1];
        enemySpeed[0] = 15; enemySpeed[1] = 0; enemySpeed[2] = enemySpeed[0] + enemySpeed[1];
        enemyDamage = 0;
        enemyArmor[0] = 5; enemyArmor[1] = 0; enemyArmor[2] = enemyArmor[0] + enemyArmor[1];
        enemyGP = 50;
        enemyXP = 30;
        enemyAlive = true;
        //100% chance to drop nothing
        lootTable[0][0] = 0; lootTable[0][1] = 0; lootTable[0][2] = 0;
        lootTable[1][0] = 0; lootTable[1][1] = 0; lootTable[1][2] = 0;
        lootTable[2][0] = 0; lootTable[2][1] = 0; lootTable[2][2] = 0;
        lootTable[3][0] = 0; lootTable[3][1] = 0; lootTable[3][2] = 0;
        lootTable[4][0] = 0; lootTable[4][1] = 0; lootTable[4][2] = 0;
        lootTable[5][0] = 0; lootTable[5][1] = 0; lootTable[5][2] = 0;
        lootTable[6][0] = 0; lootTable[6][1] = 0; lootTable[6][2] = 0;
        lootTable[7][0] = 0; lootTable[7][1] = 0; lootTable[7][2] = 0;
        lootTable[8][0] = 0; lootTable[8][1] = 0; lootTable[8][2] = 0;
        lootTable[9][0] = 0; lootTable[9][1] = 0; lootTable[9][2] = 0;
        //BUFFS AND EFFECTS
        buffTable[0][0] = 0; buffTable[0][1] = 0; buffTable[0][2] = 0; //Nothing
        buffTable[1][0] = 0; buffTable[1][1] = 0; buffTable[1][2] = 0; //Nothing
        buffTable[2][0] = 3; buffTable[2][1] = 2; buffTable[2][2] = 1; //Casted: GERATER PAHWAH! on Self
        buffTable[3][0] = 0; buffTable[3][1] = 0; buffTable[3][2] = 0; //Nothing
        buffTable[4][0] = 0; buffTable[4][1] = 0; buffTable[4][2] = 0; //Nothing
    }
    
    private void yeti(){
        enemyName = "Yeti";
        enemyHP[0] = 125;
        enemyHP[1] = 125;
        enemyStrength[0] = 5; enemyStrength[1] = 0; enemyStrength[2] = enemyStrength[0] + enemyStrength[1];
        enemyIntel[0] = 0; enemyIntel[1] = 0; enemyIntel[2] = enemyIntel[0] + enemyIntel[1];
        enemySpeed[0] = 15; enemySpeed[1] = 0; enemySpeed[2] = enemySpeed[0] + enemySpeed[1];
        enemyDamage = 0;
        enemyArmor[0] = 7; enemyArmor[1] = 0; enemyArmor[2] = enemyArmor[0] + enemyArmor[1];
        enemyGP = 50;
        enemyXP = 25;
        enemyAlive = true;
        //20% chance to drop 1 Yeti Fur, 10% to drop 2 Yeti Fur, 70% chance to drop nothing
        lootTable[0][0] = 7; lootTable[0][1] = 11; lootTable[0][2] = 1;
        lootTable[1][0] = 0; lootTable[1][1] = 0; lootTable[1][2] = 0;
        lootTable[2][0] = 0; lootTable[2][1] = 0; lootTable[2][2] = 0;
        lootTable[3][0] = 7; lootTable[3][1] = 11; lootTable[3][2] = 2;
        lootTable[4][0] = 0; lootTable[4][1] = 0; lootTable[4][2] = 0;
        lootTable[5][0] = 0; lootTable[5][1] = 0; lootTable[5][2] = 0;
        lootTable[6][0] = 7; lootTable[6][1] = 11; lootTable[6][2] = 1;
        lootTable[7][0] = 0; lootTable[7][1] = 0; lootTable[7][2] = 0;
        lootTable[8][0] = 0; lootTable[8][1] = 0; lootTable[8][2] = 0;
        lootTable[9][0] = 0; lootTable[9][1] = 0; lootTable[9][2] = 0;
        //BUFFS AND EFFECTS
        buffTable[0][0] = 1; buffTable[0][1] = 3; buffTable[0][2] = 0; //On Hit: Chilled on Player
        buffTable[1][0] = 0; buffTable[1][1] = 0; buffTable[1][2] = 0; //Nothing
        buffTable[2][0] = 0; buffTable[2][1] = 0; buffTable[2][2] = 0; //Nothing
        buffTable[3][0] = 0; buffTable[3][1] = 0; buffTable[3][2] = 0; //Nothing
        buffTable[4][0] = 1; buffTable[4][1] = 3; buffTable[4][2] = 0; //Nothing
    }
    
    private void sewerRat(){
        enemyName = "Sewer Rat";
        enemyHP[0] = 50;
        enemyHP[1] = 50;
        enemyStrength[0] = 2; enemyStrength[1] = 0; enemyStrength[2] = enemyStrength[0] + enemyStrength[1];
        enemyIntel[0] = 0; enemyIntel[1] = 0; enemyIntel[2] = enemyIntel[0] + enemyIntel[1];
        enemySpeed[0] = 5; enemySpeed[1] = 0; enemySpeed[2] = enemySpeed[0] + enemySpeed[1];
        enemyDamage = 0;
        enemyArmor[0] = 0; enemyArmor[1] = 0; enemyArmor[2] = enemyArmor[0] + enemyArmor[1];
        enemyGP = 15;
        enemyXP = 5;
        enemyAlive = true;
        //20% chance to drop Rat Tail, 10% chance to drop Cheese, 70% chance to drop nothing
        lootTable[0][0] = 0; lootTable[0][1] = 0; lootTable[0][2] = 0;
        lootTable[1][0] = 7; lootTable[1][1] = 14; lootTable[1][2] = 1;
        lootTable[2][0] = 0; lootTable[2][1] = 0; lootTable[2][2] = 0;
        lootTable[3][0] = 0; lootTable[3][1] = 0; lootTable[3][2] = 0;
        lootTable[4][0] = 6; lootTable[4][1] = 4; lootTable[4][2] = 1;
        lootTable[5][0] = 0; lootTable[5][1] = 0; lootTable[5][2] = 0;
        lootTable[6][0] = 0; lootTable[6][1] = 0; lootTable[6][2] = 0;
        lootTable[7][0] = 0; lootTable[7][1] = 0; lootTable[7][2] = 0;
        lootTable[8][0] = 7; lootTable[8][1] = 12; lootTable[8][2] = 1;
        lootTable[9][0] = 0; lootTable[9][1] = 0; lootTable[9][2] = 0;
        //BUFFS AND EFFECTS
        buffTable[0][0] = 0; buffTable[0][1] = 0; buffTable[0][2] = 0; //Nothing
        buffTable[1][0] = 0; buffTable[1][1] = 0; buffTable[1][2] = 0; //Nothing
        buffTable[2][0] = 0; buffTable[2][1] = 0; buffTable[2][2] = 0; //Nothing
        buffTable[3][0] = 0; buffTable[3][1] = 0; buffTable[3][2] = 0; //Nothing
        buffTable[4][0] = 0; buffTable[4][1] = 0; buffTable[4][2] = 0; //Nothing
    }
    
    private void giantRat(){
        enemyName = "Giant Rat";
        enemyHP[0] = 100;
        enemyHP[1] = 100;
        enemyStrength[0] = 5; enemyStrength[1] = 0; enemyStrength[2] = enemyStrength[0] + enemyStrength[1];
        enemyIntel[0] = 0; enemyIntel[1] = 0; enemyIntel[2] = enemyIntel[0] + enemyIntel[1];
        enemySpeed[0] = 7; enemySpeed[1] = 0; enemySpeed[2] = enemySpeed[0] + enemySpeed[1];
        enemyDamage = 0;
        enemyArmor[0] = 3; enemyArmor[1] = 0; enemyArmor[2] = enemyArmor[0] + enemyArmor[1];
        enemyGP = 75;
        enemyXP = 20;
        enemyAlive = true;
        //40% chance to drop Rat Tail, 20% chance to drop Cheese, 40% chance to drop nothing
        lootTable[0][0] = 7; lootTable[0][1] = 12; lootTable[0][2] = 1;
        lootTable[1][0] = 0; lootTable[1][1] = 0; lootTable[1][2] = 0;
        lootTable[2][0] = 6; lootTable[2][1] = 4; lootTable[2][2] = 1;
        lootTable[3][0] = 0; lootTable[3][1] = 0; lootTable[3][2] = 0;
        lootTable[4][0] = 7; lootTable[4][1] = 12; lootTable[4][2] = 1;
        lootTable[5][0] = 0; lootTable[5][1] = 0; lootTable[5][2] = 0;
        lootTable[6][0] = 6; lootTable[6][1] = 4; lootTable[6][2] = 1;
        lootTable[7][0] = 0; lootTable[7][1] = 0; lootTable[7][2] = 0;
        lootTable[8][0] = 7; lootTable[8][1] = 12; lootTable[8][2] = 1;
        lootTable[9][0] = 7; lootTable[9][1] = 12; lootTable[9][2] = 1;
        //BUFFS AND EFFECTS
        buffTable[0][0] = 0; buffTable[0][1] = 0; buffTable[0][2] = 0; //Nothing
        buffTable[1][0] = 1; buffTable[1][1] = 4; buffTable[1][2] = 0; //On Hit: Infected Bite on Player
        buffTable[2][0] = 0; buffTable[2][1] = 0; buffTable[2][2] = 0; //Nothing
        buffTable[3][0] = 0; buffTable[3][1] = 0; buffTable[3][2] = 0; //Nothing
        buffTable[4][0] = 0; buffTable[4][1] = 0; buffTable[4][2] = 0; //Nothing
    }
    
    private void alligator(){
        enemyName = "Alligator";
        enemyHP[0] = 150;
        enemyHP[1] = 150;
        enemyStrength[0] = 7; enemyStrength[1] = 0; enemyStrength[2] = enemyStrength[0] + enemyStrength[1];
        enemyIntel[0] = 0; enemyIntel[1] = 0; enemyIntel[2] = enemyIntel[0] + enemyIntel[1];
        enemySpeed[0] = 12; enemySpeed[1] = 0; enemySpeed[2] = enemySpeed[0] + enemySpeed[1];
        enemyDamage = 0;
        enemyArmor[0] = 10; enemyArmor[1] = 0; enemyArmor[2] = enemyArmor[0] + enemyArmor[1];
        enemyGP = 75;
        enemyXP = 50;
        enemyAlive = true;
        //20% chance to drop Alligator Tooth, 10% chance to drop Alligator Hide, 70% chance to drop nothing
        lootTable[0][0] = 0; lootTable[0][1] = 0; lootTable[0][2] = 0;
        lootTable[1][0] = 0; lootTable[1][1] = 0; lootTable[1][2] = 0;
        lootTable[2][0] = 0; lootTable[2][1] = 0; lootTable[2][2] = 0;
        lootTable[3][0] = 0; lootTable[3][1] = 0; lootTable[3][2] = 0;
        lootTable[4][0] = 7; lootTable[4][1] = 13; lootTable[4][2] = 1;
        lootTable[5][0] = 0; lootTable[5][1] = 0; lootTable[5][2] = 0;
        lootTable[6][0] = 0; lootTable[6][1] = 0; lootTable[6][2] = 0;
        lootTable[7][0] = 7; lootTable[7][1] = 14; lootTable[7][2] = 1;
        lootTable[8][0] = 7; lootTable[8][1] = 13; lootTable[8][2] = 1;
        lootTable[9][0] = 0; lootTable[9][1] = 0; lootTable[9][2] = 0;
        //BUFFS AND EFFECTS
        buffTable[0][0] = 0; buffTable[0][1] = 0; buffTable[0][2] = 0; //Nothing
        buffTable[1][0] = 0; buffTable[1][1] = 0; buffTable[1][2] = 0; //Nothing
        buffTable[2][0] = 0; buffTable[2][1] = 0; buffTable[2][2] = 0; //Nothing
        buffTable[3][0] = 0; buffTable[3][1] = 0; buffTable[3][2] = 0; //Nothing
        buffTable[4][0] = 0; buffTable[4][1] = 0; buffTable[4][2] = 0; //Nothing
    }
    
    private void vagrant(){
        enemyName = "Vagrant";
        enemyHP[0] = 125;
        enemyHP[1] = 125;
        enemyStrength[0] = 6; enemyStrength[1] = 0; enemyStrength[2] = enemyStrength[0] + enemyStrength[1];
        enemyIntel[0] = 0; enemyIntel[1] = 0; enemyIntel[2] = enemyIntel[0] + enemyIntel[1];
        enemySpeed[0] = 10; enemySpeed[1] = 0; enemySpeed[2] = enemySpeed[0] + enemySpeed[1];
        enemyDamage = 0;
        enemyArmor[0] = 5; enemyArmor[1] = 0; enemyArmor[2] = enemyArmor[0] + enemyArmor[1];
        enemyGP = 35;
        enemyXP = 30;
        enemyAlive = true;
        //10% chance to drop Cheap Wine, 40% chance to drop Empty Bottle
        //10% chance to drop 2 Empty Bottle, 10% chance to drop 5 Empty Bottle
        //30% chance to drop nothing
        lootTable[0][0] = 0; lootTable[0][1] = 0; lootTable[0][2] = 0;
        lootTable[1][0] = 4; lootTable[1][1] = 5; lootTable[1][2] = 1;
        lootTable[2][0] = 4; lootTable[2][1] = 5; lootTable[2][2] = 5;
        lootTable[3][0] = 4; lootTable[3][1] = 5; lootTable[3][2] = 1;
        lootTable[4][0] = 0; lootTable[4][1] = 0; lootTable[4][2] = 0;
        lootTable[5][0] = 4; lootTable[5][1] = 5; lootTable[5][2] = 1;
        lootTable[6][0] = 4; lootTable[6][1] = 5; lootTable[6][2] = 2;
        lootTable[7][0] = 4; lootTable[7][1] = 5; lootTable[7][2] = 1;
        lootTable[8][0] = 0; lootTable[8][1] = 0; lootTable[8][2] = 0;
        lootTable[9][0] = 6; lootTable[9][1] = 5; lootTable[9][2] = 1;
        //BUFFS AND EFFECTS
        buffTable[0][0] = 0; buffTable[0][1] = 0; buffTable[0][2] = 0; //Nothing
        buffTable[1][0] = 0; buffTable[1][1] = 0; buffTable[1][2] = 0; //Nothing
        buffTable[2][0] = 0; buffTable[2][1] = 0; buffTable[2][2] = 0; //Nothing
        buffTable[3][0] = 0; buffTable[3][1] = 0; buffTable[3][2] = 0; //Nothing
        buffTable[4][0] = 0; buffTable[4][1] = 0; buffTable[4][2] = 0; //Nothing
    }
    
    private void shamblingZombie() {
        enemyName = "Shambling Zombie";
        enemyHP[0] = 175; enemyHP[1] = 175;
        enemyStrength[0] = 13; enemyStrength[1] = 0; enemyStrength[2] = enemyStrength[0] + enemyStrength[1];
        enemyIntel[0] = 0; enemyIntel[1] = 0; enemyIntel[2] = enemyIntel[0] + enemyIntel[1];
        enemySpeed[0] = 3; enemySpeed[1] = 0; enemySpeed[2] = enemySpeed[0] + enemySpeed[1];
        enemyDamage = 0;
        enemyArmor[0] = 10; enemyArmor[1] = 0; enemyArmor[2] = enemyArmor[0] + enemyArmor[1];
        enemySPower = 0;
        enemyGP = 75;
        enemyXP = 75;
        enemyAlive = true;
        //LOOT
        //100% chance to drop nothing
        lootTable[0][0] = 0; lootTable[0][1] = 0; lootTable[0][2] = 0;
        lootTable[1][0] = 0; lootTable[1][1] = 0; lootTable[1][2] = 0;
        lootTable[2][0] = 0; lootTable[2][1] = 0; lootTable[2][2] = 0;
        lootTable[3][0] = 0; lootTable[3][1] = 0; lootTable[3][2] = 0;
        lootTable[4][0] = 0; lootTable[4][1] = 0; lootTable[4][2] = 0;
        lootTable[5][0] = 0; lootTable[5][1] = 0; lootTable[5][2] = 0;
        lootTable[6][0] = 0; lootTable[6][1] = 0; lootTable[6][2] = 0;
        lootTable[7][0] = 0; lootTable[7][1] = 0; lootTable[7][2] = 0;
        lootTable[8][0] = 0; lootTable[8][1] = 0; lootTable[8][2] = 0;
        lootTable[9][0] = 0; lootTable[9][1] = 0; lootTable[9][2] = 0;
        //BUFFS AND EFFECTS
        buffTable[0][0] = 0; buffTable[0][1] = 0; buffTable[0][2] = 0; //Nothing
        buffTable[1][0] = 0; buffTable[1][1] = 0; buffTable[1][2] = 0; //Nothing
        buffTable[2][0] = 0; buffTable[2][1] = 0; buffTable[2][2] = 0; //Nothing
        buffTable[3][0] = 0; buffTable[3][1] = 0; buffTable[3][2] = 0; //Nothing
        buffTable[4][0] = 0; buffTable[4][1] = 0; buffTable[4][2] = 0; //Nothing 
    }
    
    private void skeleton() {
        enemyName = "Skeleton";
        enemyHP[0] = 175; enemyHP[1] = 175;
        enemyStrength[0] = 7; enemyStrength[1] = 0; enemyStrength[2] = enemyStrength[0] + enemyStrength[1];
        enemyIntel[0] = 0; enemyIntel[1] = 0; enemyIntel[2] = enemyIntel[0] + enemyIntel[1];
        enemySpeed[0] = 10; enemySpeed[1] = 0; enemySpeed[2] = enemySpeed[0] + enemySpeed[1];
        enemyDamage = 0;
        enemyArmor[0] = 5; enemyArmor[1] = 0; enemyArmor[2] = enemyArmor[0] + enemyArmor[1];
        enemySPower = 0;
        enemyGP = 75;
        enemyXP = 75;
        enemyAlive = true;
        //LOOT
        //100% chance to drop nothing
        lootTable[0][0] = 0; lootTable[0][1] = 0; lootTable[0][2] = 0;
        lootTable[1][0] = 0; lootTable[1][1] = 0; lootTable[1][2] = 0;
        lootTable[2][0] = 0; lootTable[2][1] = 0; lootTable[2][2] = 0;
        lootTable[3][0] = 0; lootTable[3][1] = 0; lootTable[3][2] = 0;
        lootTable[4][0] = 0; lootTable[4][1] = 0; lootTable[4][2] = 0;
        lootTable[5][0] = 0; lootTable[5][1] = 0; lootTable[5][2] = 0;
        lootTable[6][0] = 0; lootTable[6][1] = 0; lootTable[6][2] = 0;
        lootTable[7][0] = 0; lootTable[7][1] = 0; lootTable[7][2] = 0;
        lootTable[8][0] = 0; lootTable[8][1] = 0; lootTable[8][2] = 0;
        lootTable[9][0] = 0; lootTable[9][1] = 0; lootTable[9][2] = 0;
        //BUFFS AND EFFECTS
        buffTable[0][0] = 0; buffTable[0][1] = 0; buffTable[0][2] = 0; //Nothing
        buffTable[1][0] = 0; buffTable[1][1] = 0; buffTable[1][2] = 0; //Nothing
        buffTable[2][0] = 0; buffTable[2][1] = 0; buffTable[2][2] = 0; //Nothing
        buffTable[3][0] = 0; buffTable[3][1] = 0; buffTable[3][2] = 0; //Nothing
        buffTable[4][0] = 0; buffTable[4][1] = 0; buffTable[4][2] = 0; //Nothing 
    }
    
    private void graverobber() {
        enemyName = "Graverobber";
        enemyHP[0] = 150; enemyHP[1] = 150;
        enemyStrength[0] = 10; enemyStrength[1] = 0; enemyStrength[2] = enemyStrength[0] + enemyStrength[1];
        enemyIntel[0] = 5; enemyIntel[1] = 0; enemyIntel[2] = enemyIntel[0] + enemyIntel[1];
        enemySpeed[0] = 5; enemySpeed[1] = 0; enemySpeed[2] = enemySpeed[0] + enemySpeed[1];
        enemyDamage = 0;
        enemyArmor[0] = 7; enemyArmor[1] = 0; enemyArmor[2] = enemyArmor[0] + enemyArmor[1];
        enemySPower = 0;
        enemyGP = 125;
        enemyXP = 65;
        enemyAlive = true;
        //LOOT
        //10% chance to drop 1 Shovel, 10% chance to drop 1 Looted Jewels
        //80% chance to drop nothing
        lootTable[0][0] = 4; lootTable[0][1] = 2; lootTable[0][2] = 1;
        lootTable[1][0] = 0; lootTable[1][1] = 0; lootTable[1][2] = 0;
        lootTable[2][0] = 0; lootTable[2][1] = 0; lootTable[2][2] = 0;
        lootTable[3][0] = 0; lootTable[3][1] = 0; lootTable[3][2] = 0;
        lootTable[4][0] = 0; lootTable[4][1] = 0; lootTable[4][2] = 0;
        lootTable[5][0] = 0; lootTable[5][1] = 0; lootTable[5][2] = 0;
        lootTable[6][0] = 0; lootTable[6][1] = 0; lootTable[6][2] = 0;
        lootTable[7][0] = 0; lootTable[7][1] = 0; lootTable[7][2] = 0;
        lootTable[8][0] = 0; lootTable[8][1] = 0; lootTable[8][2] = 0;
        lootTable[9][0] = 7; lootTable[9][1] = 21; lootTable[9][2] = 1;
        //BUFFS AND EFFECTS
        buffTable[0][0] = 0; buffTable[0][1] = 0; buffTable[0][2] = 0; //Nothing
        buffTable[1][0] = 0; buffTable[1][1] = 0; buffTable[1][2] = 0; //Nothing
        buffTable[2][0] = 0; buffTable[2][1] = 0; buffTable[2][2] = 0; //Nothing
        buffTable[3][0] = 0; buffTable[3][1] = 0; buffTable[3][2] = 0; //Nothing
        buffTable[4][0] = 0; buffTable[4][1] = 0; buffTable[4][2] = 0; //Nothing 
    }
    
    private void restlessSpirit() {
        enemyName = "Restless Spirit";
        enemyHP[0] = 0; enemyHP[1] = 0;
        enemyStrength[0] = 0; enemyStrength[1] = 0; enemyStrength[2] = enemyStrength[0] + enemyStrength[1];
        enemyIntel[0] = 0; enemyIntel[1] = 0; enemyIntel[2] = enemyIntel[0] + enemyIntel[1];
        enemySpeed[0] = 0; enemySpeed[1] = 0; enemySpeed[2] = enemySpeed[0] + enemySpeed[1];
        enemyDamage = 0;
        enemyArmor[0] = 0; enemyArmor[1] = 0; enemyArmor[2] = enemyArmor[0] + enemyArmor[1];
        enemySPower = 0;
        enemyGP = 0;
        enemyXP = 0;
        enemyAlive = true;
        //LOOT
        //100% chance to drop nothing
        lootTable[0][0] = 0; lootTable[0][1] = 0; lootTable[0][2] = 0;
        lootTable[1][0] = 0; lootTable[1][1] = 0; lootTable[1][2] = 0;
        lootTable[2][0] = 0; lootTable[2][1] = 0; lootTable[2][2] = 0;
        lootTable[3][0] = 0; lootTable[3][1] = 0; lootTable[3][2] = 0;
        lootTable[4][0] = 0; lootTable[4][1] = 0; lootTable[4][2] = 0;
        lootTable[5][0] = 0; lootTable[5][1] = 0; lootTable[5][2] = 0;
        lootTable[6][0] = 0; lootTable[6][1] = 0; lootTable[6][2] = 0;
        lootTable[7][0] = 0; lootTable[7][1] = 0; lootTable[7][2] = 0;
        lootTable[8][0] = 0; lootTable[8][1] = 0; lootTable[8][2] = 0;
        lootTable[9][0] = 0; lootTable[9][1] = 0; lootTable[9][2] = 0;
        //BUFFS AND EFFECTS
        buffTable[0][0] = 0; buffTable[0][1] = 0; buffTable[0][2] = 0; //Nothing
        buffTable[1][0] = 0; buffTable[1][1] = 0; buffTable[1][2] = 0; //Nothing
        buffTable[2][0] = 0; buffTable[2][1] = 0; buffTable[2][2] = 0; //Nothing
        buffTable[3][0] = 0; buffTable[3][1] = 0; buffTable[3][2] = 0; //Nothing
        buffTable[4][0] = 0; buffTable[4][1] = 0; buffTable[4][2] = 0; //Nothing 
    }
    
    private void scavenger() {
        enemyName = "Scavenger";
        enemyHP[0] = 0; enemyHP[1] = 0;
        enemyStrength[0] = 0; enemyStrength[1] = 0; enemyStrength[2] = enemyStrength[0] + enemyStrength[1];
        enemyIntel[0] = 0; enemyIntel[1] = 0; enemyIntel[2] = enemyIntel[0] + enemyIntel[1];
        enemySpeed[0] = 0; enemySpeed[1] = 0; enemySpeed[2] = enemySpeed[0] + enemySpeed[1];
        enemyDamage = 0;
        enemyArmor[0] = 0; enemyArmor[1] = 0; enemyArmor[2] = enemyArmor[0] + enemyArmor[1];
        enemySPower = 0;
        enemyGP = 0;
        enemyXP = 0;
        enemyAlive = true;
        //LOOT
        //100% chance to drop nothing
        lootTable[0][0] = 0; lootTable[0][1] = 0; lootTable[0][2] = 0;
        lootTable[1][0] = 0; lootTable[1][1] = 0; lootTable[1][2] = 0;
        lootTable[2][0] = 0; lootTable[2][1] = 0; lootTable[2][2] = 0;
        lootTable[3][0] = 0; lootTable[3][1] = 0; lootTable[3][2] = 0;
        lootTable[4][0] = 0; lootTable[4][1] = 0; lootTable[4][2] = 0;
        lootTable[5][0] = 0; lootTable[5][1] = 0; lootTable[5][2] = 0;
        lootTable[6][0] = 0; lootTable[6][1] = 0; lootTable[6][2] = 0;
        lootTable[7][0] = 0; lootTable[7][1] = 0; lootTable[7][2] = 0;
        lootTable[8][0] = 0; lootTable[8][1] = 0; lootTable[8][2] = 0;
        lootTable[9][0] = 0; lootTable[9][1] = 0; lootTable[9][2] = 0;
        //BUFFS AND EFFECTS
        buffTable[0][0] = 0; buffTable[0][1] = 0; buffTable[0][2] = 0; //Nothing
        buffTable[1][0] = 0; buffTable[1][1] = 0; buffTable[1][2] = 0; //Nothing
        buffTable[2][0] = 0; buffTable[2][1] = 0; buffTable[2][2] = 0; //Nothing
        buffTable[3][0] = 0; buffTable[3][1] = 0; buffTable[3][2] = 0; //Nothing
        buffTable[4][0] = 0; buffTable[4][1] = 0; buffTable[4][2] = 0; //Nothing 
    }
    
    private void rottingAdventurer() {
        enemyName = "Rotting Adventurer";
        enemyHP[0] = 0; enemyHP[1] = 0;
        enemyStrength[0] = 0; enemyStrength[1] = 0; enemyStrength[2] = enemyStrength[0] + enemyStrength[1];
        enemyIntel[0] = 0; enemyIntel[1] = 0; enemyIntel[2] = enemyIntel[0] + enemyIntel[1];
        enemySpeed[0] = 0; enemySpeed[1] = 0; enemySpeed[2] = enemySpeed[0] + enemySpeed[1];
        enemyDamage = 0;
        enemyArmor[0] = 0; enemyArmor[1] = 0; enemyArmor[2] = enemyArmor[0] + enemyArmor[1];
        enemySPower = 0;
        enemyGP = 0;
        enemyXP = 0;
        enemyAlive = true;
        //LOOT
        //100% chance to drop nothing
        lootTable[0][0] = 0; lootTable[0][1] = 0; lootTable[0][2] = 0;
        lootTable[1][0] = 0; lootTable[1][1] = 0; lootTable[1][2] = 0;
        lootTable[2][0] = 0; lootTable[2][1] = 0; lootTable[2][2] = 0;
        lootTable[3][0] = 0; lootTable[3][1] = 0; lootTable[3][2] = 0;
        lootTable[4][0] = 0; lootTable[4][1] = 0; lootTable[4][2] = 0;
        lootTable[5][0] = 0; lootTable[5][1] = 0; lootTable[5][2] = 0;
        lootTable[6][0] = 0; lootTable[6][1] = 0; lootTable[6][2] = 0;
        lootTable[7][0] = 0; lootTable[7][1] = 0; lootTable[7][2] = 0;
        lootTable[8][0] = 0; lootTable[8][1] = 0; lootTable[8][2] = 0;
        lootTable[9][0] = 0; lootTable[9][1] = 0; lootTable[9][2] = 0;
        //BUFFS AND EFFECTS
        buffTable[0][0] = 0; buffTable[0][1] = 0; buffTable[0][2] = 0; //Nothing
        buffTable[1][0] = 0; buffTable[1][1] = 0; buffTable[1][2] = 0; //Nothing
        buffTable[2][0] = 0; buffTable[2][1] = 0; buffTable[2][2] = 0; //Nothing
        buffTable[3][0] = 0; buffTable[3][1] = 0; buffTable[3][2] = 0; //Nothing
        buffTable[4][0] = 0; buffTable[4][1] = 0; buffTable[4][2] = 0; //Nothing 
    }
    
    private void skeletalGuard() {
        enemyName = "Skeletal Guard";
        enemyHP[0] = 0; enemyHP[1] = 0;
        enemyStrength[0] = 0; enemyStrength[1] = 0; enemyStrength[2] = enemyStrength[0] + enemyStrength[1];
        enemyIntel[0] = 0; enemyIntel[1] = 0; enemyIntel[2] = enemyIntel[0] + enemyIntel[1];
        enemySpeed[0] = 0; enemySpeed[1] = 0; enemySpeed[2] = enemySpeed[0] + enemySpeed[1];
        enemyDamage = 0;
        enemyArmor[0] = 0; enemyArmor[1] = 0; enemyArmor[2] = enemyArmor[0] + enemyArmor[1];
        enemySPower = 0;
        enemyGP = 0;
        enemyXP = 0;
        enemyAlive = true;
        //LOOT
        //100% chance to drop nothing
        lootTable[0][0] = 0; lootTable[0][1] = 0; lootTable[0][2] = 0;
        lootTable[1][0] = 0; lootTable[1][1] = 0; lootTable[1][2] = 0;
        lootTable[2][0] = 0; lootTable[2][1] = 0; lootTable[2][2] = 0;
        lootTable[3][0] = 0; lootTable[3][1] = 0; lootTable[3][2] = 0;
        lootTable[4][0] = 0; lootTable[4][1] = 0; lootTable[4][2] = 0;
        lootTable[5][0] = 0; lootTable[5][1] = 0; lootTable[5][2] = 0;
        lootTable[6][0] = 0; lootTable[6][1] = 0; lootTable[6][2] = 0;
        lootTable[7][0] = 0; lootTable[7][1] = 0; lootTable[7][2] = 0;
        lootTable[8][0] = 0; lootTable[8][1] = 0; lootTable[8][2] = 0;
        lootTable[9][0] = 0; lootTable[9][1] = 0; lootTable[9][2] = 0;
        //BUFFS AND EFFECTS
        buffTable[0][0] = 0; buffTable[0][1] = 0; buffTable[0][2] = 0; //Nothing
        buffTable[1][0] = 0; buffTable[1][1] = 0; buffTable[1][2] = 0; //Nothing
        buffTable[2][0] = 0; buffTable[2][1] = 0; buffTable[2][2] = 0; //Nothing
        buffTable[3][0] = 0; buffTable[3][1] = 0; buffTable[3][2] = 0; //Nothing
        buffTable[4][0] = 0; buffTable[4][1] = 0; buffTable[4][2] = 0; //Nothing 
    }
    
    private void tombRaider() {
        enemyName = "Tomb Raider";
        enemyHP[0] = 0; enemyHP[1] = 0;
        enemyStrength[0] = 0; enemyStrength[1] = 0; enemyStrength[2] = enemyStrength[0] + enemyStrength[1];
        enemyIntel[0] = 0; enemyIntel[1] = 0; enemyIntel[2] = enemyIntel[0] + enemyIntel[1];
        enemySpeed[0] = 0; enemySpeed[1] = 0; enemySpeed[2] = enemySpeed[0] + enemySpeed[1];
        enemyDamage = 0;
        enemyArmor[0] = 0; enemyArmor[1] = 0; enemyArmor[2] = enemyArmor[0] + enemyArmor[1];
        enemySPower = 0;
        enemyGP = 0;
        enemyXP = 0;
        enemyAlive = true;
        //LOOT
        //100% chance to drop nothing
        lootTable[0][0] = 0; lootTable[0][1] = 0; lootTable[0][2] = 0;
        lootTable[1][0] = 0; lootTable[1][1] = 0; lootTable[1][2] = 0;
        lootTable[2][0] = 0; lootTable[2][1] = 0; lootTable[2][2] = 0;
        lootTable[3][0] = 0; lootTable[3][1] = 0; lootTable[3][2] = 0;
        lootTable[4][0] = 0; lootTable[4][1] = 0; lootTable[4][2] = 0;
        lootTable[5][0] = 0; lootTable[5][1] = 0; lootTable[5][2] = 0;
        lootTable[6][0] = 0; lootTable[6][1] = 0; lootTable[6][2] = 0;
        lootTable[7][0] = 0; lootTable[7][1] = 0; lootTable[7][2] = 0;
        lootTable[8][0] = 0; lootTable[8][1] = 0; lootTable[8][2] = 0;
        lootTable[9][0] = 0; lootTable[9][1] = 0; lootTable[9][2] = 0;
        //BUFFS AND EFFECTS
        buffTable[0][0] = 0; buffTable[0][1] = 0; buffTable[0][2] = 0; //Nothing
        buffTable[1][0] = 0; buffTable[1][1] = 0; buffTable[1][2] = 0; //Nothing
        buffTable[2][0] = 0; buffTable[2][1] = 0; buffTable[2][2] = 0; //Nothing
        buffTable[3][0] = 0; buffTable[3][1] = 0; buffTable[3][2] = 0; //Nothing
        buffTable[4][0] = 0; buffTable[4][1] = 0; buffTable[4][2] = 0; //Nothing 
    }
    
    private void necroAdept() {
        enemyName = "Necromancer Adept";
        enemyHP[0] = 0; enemyHP[1] = 0;
        enemyStrength[0] = 0; enemyStrength[1] = 0; enemyStrength[2] = enemyStrength[0] + enemyStrength[1];
        enemyIntel[0] = 0; enemyIntel[1] = 0; enemyIntel[2] = enemyIntel[0] + enemyIntel[1];
        enemySpeed[0] = 0; enemySpeed[1] = 0; enemySpeed[2] = enemySpeed[0] + enemySpeed[1];
        enemyDamage = 0;
        enemyArmor[0] = 0; enemyArmor[1] = 0; enemyArmor[2] = enemyArmor[0] + enemyArmor[1];
        enemySPower = 0;
        enemyGP = 0;
        enemyXP = 0;
        enemyAlive = true;
        //LOOT
        //100% chance to drop nothing
        lootTable[0][0] = 0; lootTable[0][1] = 0; lootTable[0][2] = 0;
        lootTable[1][0] = 0; lootTable[1][1] = 0; lootTable[1][2] = 0;
        lootTable[2][0] = 0; lootTable[2][1] = 0; lootTable[2][2] = 0;
        lootTable[3][0] = 0; lootTable[3][1] = 0; lootTable[3][2] = 0;
        lootTable[4][0] = 0; lootTable[4][1] = 0; lootTable[4][2] = 0;
        lootTable[5][0] = 0; lootTable[5][1] = 0; lootTable[5][2] = 0;
        lootTable[6][0] = 0; lootTable[6][1] = 0; lootTable[6][2] = 0;
        lootTable[7][0] = 0; lootTable[7][1] = 0; lootTable[7][2] = 0;
        lootTable[8][0] = 0; lootTable[8][1] = 0; lootTable[8][2] = 0;
        lootTable[9][0] = 0; lootTable[9][1] = 0; lootTable[9][2] = 0;
        //BUFFS AND EFFECTS
        buffTable[0][0] = 0; buffTable[0][1] = 0; buffTable[0][2] = 0; //Nothing
        buffTable[1][0] = 0; buffTable[1][1] = 0; buffTable[1][2] = 0; //Nothing
        buffTable[2][0] = 0; buffTable[2][1] = 0; buffTable[2][2] = 0; //Nothing
        buffTable[3][0] = 0; buffTable[3][1] = 0; buffTable[3][2] = 0; //Nothing
        buffTable[4][0] = 0; buffTable[4][1] = 0; buffTable[4][2] = 0; //Nothing 
    }
    
    private void corpseFiend() {
        enemyName = "Corpse Fiend";
        enemyHP[0] = 0; enemyHP[1] = 0;
        enemyStrength[0] = 0; enemyStrength[1] = 0; enemyStrength[2] = enemyStrength[0] + enemyStrength[1];
        enemyIntel[0] = 0; enemyIntel[1] = 0; enemyIntel[2] = enemyIntel[0] + enemyIntel[1];
        enemySpeed[0] = 0; enemySpeed[1] = 0; enemySpeed[2] = enemySpeed[0] + enemySpeed[1];
        enemyDamage = 0;
        enemyArmor[0] = 0; enemyArmor[1] = 0; enemyArmor[2] = enemyArmor[0] + enemyArmor[1];
        enemySPower = 0;
        enemyGP = 0;
        enemyXP = 0;
        enemyAlive = true;
        //LOOT
        //100% chance to drop nothing
        lootTable[0][0] = 0; lootTable[0][1] = 0; lootTable[0][2] = 0;
        lootTable[1][0] = 0; lootTable[1][1] = 0; lootTable[1][2] = 0;
        lootTable[2][0] = 0; lootTable[2][1] = 0; lootTable[2][2] = 0;
        lootTable[3][0] = 0; lootTable[3][1] = 0; lootTable[3][2] = 0;
        lootTable[4][0] = 0; lootTable[4][1] = 0; lootTable[4][2] = 0;
        lootTable[5][0] = 0; lootTable[5][1] = 0; lootTable[5][2] = 0;
        lootTable[6][0] = 0; lootTable[6][1] = 0; lootTable[6][2] = 0;
        lootTable[7][0] = 0; lootTable[7][1] = 0; lootTable[7][2] = 0;
        lootTable[8][0] = 0; lootTable[8][1] = 0; lootTable[8][2] = 0;
        lootTable[9][0] = 0; lootTable[9][1] = 0; lootTable[9][2] = 0;
        //BUFFS AND EFFECTS
        buffTable[0][0] = 0; buffTable[0][1] = 0; buffTable[0][2] = 0; //Nothing
        buffTable[1][0] = 0; buffTable[1][1] = 0; buffTable[1][2] = 0; //Nothing
        buffTable[2][0] = 0; buffTable[2][1] = 0; buffTable[2][2] = 0; //Nothing
        buffTable[3][0] = 0; buffTable[3][1] = 0; buffTable[3][2] = 0; //Nothing
        buffTable[4][0] = 0; buffTable[4][1] = 0; buffTable[4][2] = 0; //Nothing 
    }
    
    private void darkPriest() {
        enemyName = "Dark Priest";
        enemyHP[0] = 0; enemyHP[1] = 0;
        enemyStrength[0] = 0; enemyStrength[1] = 0; enemyStrength[2] = enemyStrength[0] + enemyStrength[1];
        enemyIntel[0] = 0; enemyIntel[1] = 0; enemyIntel[2] = enemyIntel[0] + enemyIntel[1];
        enemySpeed[0] = 0; enemySpeed[1] = 0; enemySpeed[2] = enemySpeed[0] + enemySpeed[1];
        enemyDamage = 0;
        enemyArmor[0] = 0; enemyArmor[1] = 0; enemyArmor[2] = enemyArmor[0] + enemyArmor[1];
        enemySPower = 0;
        enemyGP = 0;
        enemyXP = 0;
        enemyAlive = true;
        //LOOT
        //100% chance to drop nothing
        lootTable[0][0] = 0; lootTable[0][1] = 0; lootTable[0][2] = 0;
        lootTable[1][0] = 0; lootTable[1][1] = 0; lootTable[1][2] = 0;
        lootTable[2][0] = 0; lootTable[2][1] = 0; lootTable[2][2] = 0;
        lootTable[3][0] = 0; lootTable[3][1] = 0; lootTable[3][2] = 0;
        lootTable[4][0] = 0; lootTable[4][1] = 0; lootTable[4][2] = 0;
        lootTable[5][0] = 0; lootTable[5][1] = 0; lootTable[5][2] = 0;
        lootTable[6][0] = 0; lootTable[6][1] = 0; lootTable[6][2] = 0;
        lootTable[7][0] = 0; lootTable[7][1] = 0; lootTable[7][2] = 0;
        lootTable[8][0] = 0; lootTable[8][1] = 0; lootTable[8][2] = 0;
        lootTable[9][0] = 0; lootTable[9][1] = 0; lootTable[9][2] = 0;
        //BUFFS AND EFFECTS
        buffTable[0][0] = 0; buffTable[0][1] = 0; buffTable[0][2] = 0; //Nothing
        buffTable[1][0] = 0; buffTable[1][1] = 0; buffTable[1][2] = 0; //Nothing
        buffTable[2][0] = 0; buffTable[2][1] = 0; buffTable[2][2] = 0; //Nothing
        buffTable[3][0] = 0; buffTable[3][1] = 0; buffTable[3][2] = 0; //Nothing
        buffTable[4][0] = 0; buffTable[4][1] = 0; buffTable[4][2] = 0; //Nothing 
    }
    
    private void lich() {
        enemyName = "Lich";
        enemyHP[0] = 0; enemyHP[1] = 0;
        enemyStrength[0] = 0; enemyStrength[1] = 0; enemyStrength[2] = enemyStrength[0] + enemyStrength[1];
        enemyIntel[0] = 0; enemyIntel[1] = 0; enemyIntel[2] = enemyIntel[0] + enemyIntel[1];
        enemySpeed[0] = 0; enemySpeed[1] = 0; enemySpeed[2] = enemySpeed[0] + enemySpeed[1];
        enemyDamage = 0;
        enemyArmor[0] = 0; enemyArmor[1] = 0; enemyArmor[2] = enemyArmor[0] + enemyArmor[1];
        enemySPower = 0;
        enemyGP = 0;
        enemyXP = 0;
        enemyAlive = true;
        //LOOT
        //100% chance to drop nothing
        lootTable[0][0] = 0; lootTable[0][1] = 0; lootTable[0][2] = 0;
        lootTable[1][0] = 0; lootTable[1][1] = 0; lootTable[1][2] = 0;
        lootTable[2][0] = 0; lootTable[2][1] = 0; lootTable[2][2] = 0;
        lootTable[3][0] = 0; lootTable[3][1] = 0; lootTable[3][2] = 0;
        lootTable[4][0] = 0; lootTable[4][1] = 0; lootTable[4][2] = 0;
        lootTable[5][0] = 0; lootTable[5][1] = 0; lootTable[5][2] = 0;
        lootTable[6][0] = 0; lootTable[6][1] = 0; lootTable[6][2] = 0;
        lootTable[7][0] = 0; lootTable[7][1] = 0; lootTable[7][2] = 0;
        lootTable[8][0] = 0; lootTable[8][1] = 0; lootTable[8][2] = 0;
        lootTable[9][0] = 0; lootTable[9][1] = 0; lootTable[9][2] = 0;
        //BUFFS AND EFFECTS
        buffTable[0][0] = 0; buffTable[0][1] = 0; buffTable[0][2] = 0; //Nothing
        buffTable[1][0] = 0; buffTable[1][1] = 0; buffTable[1][2] = 0; //Nothing
        buffTable[2][0] = 0; buffTable[2][1] = 0; buffTable[2][2] = 0; //Nothing
        buffTable[3][0] = 0; buffTable[3][1] = 0; buffTable[3][2] = 0; //Nothing
        buffTable[4][0] = 0; buffTable[4][1] = 0; buffTable[4][2] = 0; //Nothing 
    }
    
}
