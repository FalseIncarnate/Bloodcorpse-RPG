
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

public class Crafting {

    //Blacksmith crafting recipes
    static int[][] wolfHideArmorRecipe = new int[3][3];
    static int[][] turtleShieldRecipe = new int[3][3];
    static int[][] turtleArmorRecipe = new int[4][3];
    static int[][] trollBiteGauntletRecipe = new int[4][3];
    
    //Magic Shop crafting recipes
    static int[][] voodooDollRecipe = new int[6][3];
    static int[][] dollOfRebirthRecipe = new int[6][3];
    
    //Apothecary crafting recipes
    static int[][] naturesFuryRecipe = new int[3][3];
    static int[][] lingeringHealthPotionRecipe = new int[4][3];
    static int[][] lingeringManaPotionRecipe = new int[4][3];
    
    //Camp crafting recipes
    
    
    static void setRecipes(){
        /*
            All recipes conform to the same format, regardless of how complex their materials are.
            The format of a recipe is itemNameRecipe[i][j]
            [i] is the number of components to the recipe, with a minimum of 3.
            i = 0 will ALWAYS refer to the item(s) crafted by the recipe. 
            i = 1 will ALWAYS refer to the item's crafting fee in GP. Even if the fee is 0, this data is required.
            i = 2+ will refer to a specific item required for the recipe. Each 'i' refers to a different item.
            [j] is the data related to the specified component, and ALL components have 3 data elements.
            j = 0 refers to the type of item. Listed below are the different values and their type.
                0 is the value for Gold (GP). This will always be second component listed (i=1).
                1 is the value for Armor.
                2 is the value for Weapon.
                3 is the value for Offhand.
                4 is the value for Tool.
                5 is the value for Potion.
                6 is the value for Food.
                7 is the value for General.
            j = 1 refers to the index of the item within the arrayList for that type
                If the type is Gold, this value is ALWAYS -1. This will ALWAYS be the second component listed (i=1).
                For all other types, the value is the appropriate index for that item.
            j = 2 refers to the amount of the item.
                For the resulting item (i=0), this will be how many items the recipe produces. This must be at least 1.
                For Gold, this is the amount of gold required. This may be 0.
                For other components, this is the number of that item required. This must be at least 1.
        */
        setBSRecipes();
        setMSRecipes();
        setApocRecipes();
        setCampRecipes();
    }
    
    static void setBSRecipes(){
        //Wolf Hide Armor: 5 Wolf Pelts, 15 GP
        wolfHideArmorRecipe[0][0] = 1; wolfHideArmorRecipe[0][1] = 4; wolfHideArmorRecipe[0][2] = 1;
        wolfHideArmorRecipe[1][0] = 0; wolfHideArmorRecipe[1][1] = -1; wolfHideArmorRecipe[1][2] = 15;
        wolfHideArmorRecipe[2][0] = 7; wolfHideArmorRecipe[2][1] = 0; wolfHideArmorRecipe[2][2] = 5;
        //Turtle Shield: 5 Turtle Shells, 25 GP
        turtleShieldRecipe[0][0] = 3; turtleShieldRecipe[0][1] = 3; turtleShieldRecipe[0][2] = 1;
        turtleShieldRecipe[1][0] = 0; turtleShieldRecipe[1][1] = -1; turtleShieldRecipe[1][2] = 25;
        turtleShieldRecipe[2][0] = 7; turtleShieldRecipe[2][1] = 3; turtleShieldRecipe[2][2] = 5;
        //Turtle Armor: 15 Turtle Shells, 2 Wolf Pelts, 50 GP
        turtleArmorRecipe[0][0] = 1; turtleArmorRecipe[0][1] = 5; turtleArmorRecipe[0][2] = 1;
        turtleArmorRecipe[1][0] = 0; turtleArmorRecipe[1][1] = -1; turtleArmorRecipe[1][2] = 50;
        turtleArmorRecipe[2][0] = 7; turtleArmorRecipe[2][1] = 3; turtleArmorRecipe[2][2] = 15;
        turtleArmorRecipe[3][0] = 7; turtleArmorRecipe[3][1] = 0; turtleArmorRecipe[3][2] = 2;
        //Troll Bite Gauntlet: 10 Troll Teeth, 1 Wolf Pelt, 25 GP
        trollBiteGauntletRecipe[0][0] = 2; trollBiteGauntletRecipe[0][1] = 4; trollBiteGauntletRecipe[0][2] = 1;
        trollBiteGauntletRecipe[1][0] = 0; trollBiteGauntletRecipe[1][1] = -1; trollBiteGauntletRecipe[1][2] = 25;
        trollBiteGauntletRecipe[2][0] = 7; trollBiteGauntletRecipe[2][1] = 4; trollBiteGauntletRecipe[2][2] = 10;
        trollBiteGauntletRecipe[3][0] = 7; trollBiteGauntletRecipe[3][1] = 0; trollBiteGauntletRecipe[3][2] = 10;
    }
    
    static void setMSRecipes(){
        //Voodoo Doll: 1 Wolf Pelt, 1 Wooden Doll, 1 Mageflower, 1 Venom Vine, 50 GP
        voodooDollRecipe[0][0] = 3; voodooDollRecipe[0][1] = 4; voodooDollRecipe[0][2] = 1;
        voodooDollRecipe[1][0] = 0; voodooDollRecipe[1][1] = -1; voodooDollRecipe[1][2] = 25;
        voodooDollRecipe[2][0] = 7; voodooDollRecipe[2][1] = 0; voodooDollRecipe[2][2] = 1;
        voodooDollRecipe[3][0] = 7; voodooDollRecipe[3][1] = 1; voodooDollRecipe[3][2] = 1;
        voodooDollRecipe[4][0] = 7; voodooDollRecipe[4][1] = 6; voodooDollRecipe[4][2] = 1;
        voodooDollRecipe[5][0] = 7; voodooDollRecipe[5][1] = 7; voodooDollRecipe[5][2] = 1;
        //Doll of Rebirth: 1 Wooden Doll, 25 Heartbloom, 20 Mageflower, 5 Venom Vine, 250 GP
        dollOfRebirthRecipe[0][0] = 4; dollOfRebirthRecipe[0][1] = 3; dollOfRebirthRecipe[0][2] = 1;
        dollOfRebirthRecipe[1][0] = 0; dollOfRebirthRecipe[1][1] = -1; dollOfRebirthRecipe[1][2] = 250;
        dollOfRebirthRecipe[2][0] = 7; dollOfRebirthRecipe[2][1] = 1; dollOfRebirthRecipe[2][2] = 1;
        dollOfRebirthRecipe[3][0] = 7; dollOfRebirthRecipe[3][1] = 5; dollOfRebirthRecipe[3][2] = 25;
        dollOfRebirthRecipe[4][0] = 7; dollOfRebirthRecipe[4][1] = 6; dollOfRebirthRecipe[4][2] = 20;
        dollOfRebirthRecipe[5][0] = 7; dollOfRebirthRecipe[5][1] = 7; dollOfRebirthRecipe[5][2] = 5;
    }
    
    static void setApocRecipes(){
        //Nature's Fury: 5 Venom Vine, 50 GP
        naturesFuryRecipe[0][0] = 5; naturesFuryRecipe[0][1] = 6; naturesFuryRecipe[0][2] = 1;
        naturesFuryRecipe[1][0] = 0; naturesFuryRecipe[1][1] = -1; naturesFuryRecipe[1][2] = 50;
        naturesFuryRecipe[2][0] = 7; naturesFuryRecipe[2][1] = 7; naturesFuryRecipe[2][2] = 5;
        //Lingering Health Potion: 2 Heartbloom, 1 Jar of Honey, 15 GP
        lingeringHealthPotionRecipe[0][0] = 5; lingeringHealthPotionRecipe[0][1] = 8; lingeringHealthPotionRecipe[0][2] = 1;
        lingeringHealthPotionRecipe[1][0] = 0; lingeringHealthPotionRecipe[1][1] = -1; lingeringHealthPotionRecipe[1][2] = 15;
        lingeringHealthPotionRecipe[2][0] = 7; lingeringHealthPotionRecipe[2][1] = 5; lingeringHealthPotionRecipe[2][2] = 2;
        lingeringHealthPotionRecipe[3][0] = 6; lingeringHealthPotionRecipe[3][1] = 2; lingeringHealthPotionRecipe[3][2] = 1;
        //Lingering Mana Potion: 2 Mageflower, 1 Jar of Honey, 15 GP
        lingeringManaPotionRecipe[0][0] = 5; lingeringManaPotionRecipe[0][1] = 9; lingeringManaPotionRecipe[0][2] = 1;
        lingeringManaPotionRecipe[1][0] = 0; lingeringManaPotionRecipe[1][1] = -1; lingeringManaPotionRecipe[1][2] = 15;
        lingeringManaPotionRecipe[2][0] = 7; lingeringManaPotionRecipe[2][1] = 6; lingeringManaPotionRecipe[2][2] = 2;
        lingeringManaPotionRecipe[3][0] = 6; lingeringManaPotionRecipe[3][1] = 2; lingeringManaPotionRecipe[3][2] = 1;
    }
    
    static void setCampRecipes(){
        //No recipes yet
    }
    
    static void campCraftingMenu(){
        JOptionPane.showMessageDialog(Main.window, "This feature is currently under development. It will be reactivated in Version 1.4");
    }
    
    static void bsCraftingMenu(){
        Main.screen="bsCraftingMenu";
        Main.output.setText("\"Oi, ye want something special? Bring me the materials and I can make it fer ye!\" he boasts." + "\n");
        Main.output.append("The blacksmith retrieves a manual of plans for custom-crafted armor and weapons and hands it to you." + "\n");
        Main.output.append("Each page lists the materials and crafting fees associated with a different plan." + "\n");
        Main.output.append("Wolf Hide Armor: 5 Wolf Pelts, 15 GP" + "\n");
        Main.output.append("Turtle Shield: 5 Turtle Shells, 25 GP" + "\n");
        Main.output.append("Turtle Armor: 15 Turtle Shells, 2 Wolf Pelts, 50 GP" + "\n");
        Main.output.append("Troll Bite Gauntlet: 10 Troll Teeth, 1 Wolf Pelt, 25 GP");
        Main.btn1.setText("Wolf Hide Armor");
        Main.btn2.setText("Turtle Shield");
        Main.btn3.setText("Turtle Armor");
        Main.btn4.setText("Troll Bite Gauntlet");
        Main.btn5.setText(" ");
        Main.btn6.setText("Nevermind");
    }
    
    static void msCraftingMenu(){
        Main.screen="msCraftingMenu";
        Main.output.setText("\"Looking for something with a bit more kick, huh? Great! Let me show you what I can make!\"" + "\n");
        Main.output.append("The wizard seems extremely excited to show you a tome of different magical items." + "\n");
        Main.output.append("Each page lists the materials and crafting fees associated with a different object." + "\n");
        Main.output.append("Voodoo Doll: 1 Wolf Pelt, 1 Wooden Doll, 1 Mageflower, 1 Venom Vine, 50 GP" + "\n");
        Main.output.append("Doll of Rebirth: 1 Wooden Doll, 25 Heartbloom, 20 Mageflower, 5 Venom Vine, 250 GP");
        Main.btn1.setText("Voodoo Doll");
        Main.btn2.setText("Doll of Rebirth");
        Main.btn3.setText(" ");
        Main.btn4.setText(" ");
        Main.btn5.setText(" ");
        Main.btn6.setText("Nevermind");
    }
    
    protected static void apocCraftingMenu(){
        Main.screen="apocCraftingMenu";
        Main.output.setText("\"Ah, you're interested in my specialty concoctions?\" The apothecary replies with a smile." + "\n");
        Main.output.append("The apothecary hands you a book of recipes she can brew for you." + "\n");
        Main.output.append("Each page lists the materials and crafting fees associated with a different recipe." + "\n");
        Main.output.append("Nature's Fury: 5 Venom Vine, 50 GP" + "\n");
        Main.output.append("Lingering Health Potion: 2 Heartbloom, 1 Jar of Honey, 15 GP" + "\n");
        Main.output.append("Lingering Mana Potion: 2 Mageflower, 1 Jar of Honey, 15 GP");
        Main.btn1.setText("Nature's Fury");
        Main.btn2.setText("Lingering Health Potion");
        Main.btn3.setText("Lingering Mana Potion");
        Main.btn4.setText(" ");
        Main.btn5.setText(" ");
        Main.btn6.setText("Nevermind");
    }
    
    protected static void materialCheck(int[][] recipe){
        String itemName = "";
        switch(recipe[0][0]){
                case 1:
                    itemName = Inv.armorName.get(recipe[0][1]); break;
                case 2:
                    itemName = Inv.weaponName.get(recipe[0][1]); break;
                case 3:
                    itemName = Inv.offhandName.get(recipe[0][1]); break;
                case 4:
                    itemName = Inv.toolName.get(recipe[0][1]); break;
                case 5:
                    itemName = Inv.potionName.get(recipe[0][1]); break;
                case 6:
                    itemName = Inv.foodName.get(recipe[0][1]); break;
                case 7:
                    itemName = Inv.generalName.get(recipe[0][1]); break;
            }
        boolean canCraft = true;
        if(stats.myGP[0] < recipe[1][2]){
            canCraft = false;
        }
        for(int i = 2; i < recipe.length; i++){
            switch(recipe[i][0]){
                case 1:
                    if(Inv.armorNum.get(recipe[i][1])[0] < recipe[i][2]){
                        canCraft = false;
                    } break;
                case 2:
                    if(Inv.weaponNum.get(recipe[i][1])[0] < recipe[i][2]){
                        canCraft = false;
                    } break;
                case 3:
                    if(Inv.offhandNum.get(recipe[i][1])[0] < recipe[i][2]){
                        canCraft = false;
                    } break;
                case 4:
                    if(Inv.toolNum.get(recipe[i][1])[0] < recipe[i][2]){
                        canCraft = false;
                    } break;
                case 5:
                    if(Inv.potionNum.get(recipe[i][1])[0] < recipe[i][2]){
                        canCraft = false;
                    } break;
                case 6:
                    if(Inv.foodNum.get(recipe[i][1])[0] < recipe[i][2]){
                        canCraft = false;
                    } break;
                case 7:
                    if(Inv.generalNum.get(recipe[i][1])[0] < recipe[i][2]){
                        canCraft = false;
                    } break;
            }
        }
        if(canCraft == true){
            craftItem(recipe, itemName);
        }else{
            Main.output.append("\n" + "\n");
            Main.output.append("You don't have the required materials for crafting " + itemName + ".");
        }
    }
    
    static void craftItem(int[][] recipe, String itemName){
        stats.myGP[0] -= recipe[1][2];
        for(int i = 2; i < recipe.length; i++){
            switch(recipe[i][0]){
                case 1:
                    Inv.armorNum.get(recipe[i][1])[0] -= recipe[i][2]; break;
                case 2:
                    Inv.weaponNum.get(recipe[i][1])[0] -= recipe[i][2]; break;
                case 3:
                    Inv.offhandNum.get(recipe[i][1])[0] -= recipe[i][2]; break;
                case 4:
                    Inv.toolNum.get(recipe[i][1])[0] -= recipe[i][2]; break;
                case 5:
                    Inv.potionNum.get(recipe[i][1])[0] -= recipe[i][2]; break;
                case 6:
                    Inv.foodNum.get(recipe[i][1])[0] -= recipe[i][2]; break;
                case 7:
                    Inv.generalNum.get(recipe[i][1])[0] -= recipe[i][2]; break;
            }
        }
        switch(recipe[0][0]){
            case 1:
                Inv.armorNum.get(recipe[0][1])[0] += recipe[0][2]; break;
            case 2:
                Inv.weaponNum.get(recipe[0][1])[0] += recipe[0][2]; break;
            case 3:
                Inv.offhandNum.get(recipe[0][1])[0] += recipe[0][2]; break;
            case 4:
                Inv.toolNum.get(recipe[0][1])[0] += recipe[0][2]; break;
            case 5:
                Inv.potionNum.get(recipe[0][1])[0] += recipe[0][2]; break;
            case 6:
                Inv.foodNum.get(recipe[0][1])[0] += recipe[0][2]; break;
            case 7:
                Inv.generalNum.get(recipe[0][1])[0] += recipe[0][2]; break;
        }
        Main.output.append("\n" + "\n");
        Main.output.append(itemName + " x" + recipe[0][2] + " was successfully crafted and added to your inventory!");
        Main.updateLabels();
    }
    
}
