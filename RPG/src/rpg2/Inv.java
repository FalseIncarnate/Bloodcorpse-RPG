
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

public class Inv {
    
    //GUI Components
    static JDialog equipWindow;
    static JList armorSelect, weaponSelect, offhandSelect;
    static DefaultListModel armorLM = new DefaultListModel();
    static DefaultListModel weaponLM = new DefaultListModel();
    static DefaultListModel offhandLM = new DefaultListModel();
    static JLabel armorDesc = new JLabel("Armor: "), damageDesc = new JLabel("Damage: "), spellDesc = new JLabel(" ");
    static JButton equipBtn = new JButton("Equip");
    
    //Equipped items
        //Value 1: Equipped Armor
        //Value 2: Equipped Weapon
        //Value 3: Equipped Offhand
    static String equipped[] = {"Nothing", "Nothing", "Nothing"};
    static int equippedNum[] = {-1, -1, -1};
    
    
    /*
    *   Item arraylists now contain multiple pieces of data related to the item.
    *       Format: item.get(i)[j]
    *   i: Refers to the item's index within the arraylist. Every index is a specific item.
    *       For example, item.get(0)[0] and item.get(1)[0] reference different items.
    *       However, both item.get(2)[0] and item.get(2)[1] reference the same item.
    *   j: Refers to the data index for the specific item designated.
    *       j = 0: This value represents how many of the item you possess. This value changes every time you buy, sell, or loot the item.
    *       j = 1: This value represents the base sell value of the item. This value remains constant.
    *       j = 2: This value represents the base buy value of the item. This value remains constant.
    *       j = 3: This value represents the armor value of the item. Only on Armor, Weapons, and Offhand items. This value remains constant.
    *       j = 4: This value represents the physical damage value of the item. Only on Armor, Weapons, and Offhand items. This value remains constant.
    *       j = 5: This value represents the spellpower value of the item. Only on Armor, Weapons, and Offhand items. This value remains constant.
    *
    */
    
    //Armor Items
    static ArrayList<String> armorName = new ArrayList<String>();
    static ArrayList<int[]> armorNum = new ArrayList<int[]>();
    
    //Weapon Items
    static ArrayList<String> weaponName = new ArrayList<String>();
    static ArrayList<int[]> weaponNum = new ArrayList<int[]>();
    
    //Offhand Items
    static ArrayList<String> offhandName = new ArrayList<String>();
    static ArrayList<int[]> offhandNum = new ArrayList<int[]>();
    
    //Tool Items
    static ArrayList<String> toolName = new ArrayList<String>();
    static ArrayList<int[]> toolNum = new ArrayList<int[]>();
    
    //Potion Items
    static ArrayList<String> potionName = new ArrayList<String>();
    static ArrayList<int[]> potionNum = new ArrayList<int[]>();
    
    //Food Items
    static ArrayList<String> foodName = new ArrayList<String>();
    static ArrayList<int[]> foodNum = new ArrayList<int[]>();
    
    //General Items
    static ArrayList<String> generalName = new ArrayList<String>();
    static ArrayList<int[]> generalNum = new ArrayList<int[]>();
    
    static void start(){
        setValuesArmor();
        setValuesWeapon();
        setValuesOffhand();
        setValuesTool();
        setValuesPotion();
        setValuesFood();
        setValuesGeneral();
        fillLists();
        buildGUI();
    }
    
    static void buildGUI(){
        //Add action listener to equipBtn
        equipBtn.addActionListener(Main.h);
        //Initialize JLists using matching data lists, set selection mode, add ListSelectionListeners
        armorSelect = new JList(armorLM);
        armorSelect.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        armorSelect.addListSelectionListener(Main.h2);
        weaponSelect = new JList(weaponLM);
        weaponSelect.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        weaponSelect.addListSelectionListener(Main.h2);
        offhandSelect = new JList(offhandLM);
        offhandSelect.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        offhandSelect.addListSelectionListener(Main.h2);
        //Initialize equipWindow, set to modal
        equipWindow = new JDialog(Main.window, "Equipment Select");
        equipWindow.setModal(true);
        //Declare and initialize GridBagConstraints variable
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        //Set equipWindow layout to GridBagLayout
        equipWindow.setLayout(new GridBagLayout());
        //Add armorSelect at grid (0, 0) and armorDesc at grid (1, 0)
        c.gridx = 0; c.gridy = 0; equipWindow.add(armorSelect, c);
        c.gridx = 1; c.gridy = 0; equipWindow.add(armorDesc, c);
        //Add weaponSelect at grid (0, 1) and weaponDesc at grid (1, 1)
        c.gridx = 0; c.gridy = 1; equipWindow.add(weaponSelect, c);
        c.gridx = 1; c.gridy = 1; equipWindow.add(damageDesc, c);
        //Add offhandSelect at grid (0, 2) and offhandDesc at grid (1, 2)
        c.gridx = 0; c.gridy = 2; equipWindow.add(offhandSelect, c);
        c.gridx = 1; c.gridy = 2; equipWindow.add(spellDesc, c);
        //Add equipBtn at grid (0, 3)
        c.gridx = 0; c.gridy = 3; equipWindow.add(equipBtn, c);
        equipWindow.pack();
    }
    
    static void fillLists(){
        //Clear data lists
        armorLM.removeAllElements();
        weaponLM.removeAllElements();
        offhandLM.removeAllElements();

        //Add "nothing" option to data lists
        armorLM.add(0, "Nothing");
        weaponLM.add(0, "Nothing");
        offhandLM.add(0, "Nothing");
        
        //Populate data lists with valid options
        for(int i = 0; i < armorName.size(); i++){
            if(armorName.get(i) != null && armorNum.get(i)[0] > 0){
                armorLM.addElement(armorName.get(i));
            }
        }
        for(int i = 0; i < weaponName.size(); i++){
            if(weaponName.get(i) != null && weaponNum.get(i)[0] > 0){
                weaponLM.addElement(weaponName.get(i));
            }
        }
        for(int i = 0; i < offhandName.size(); i++){
            if(offhandName.get(i) != null && offhandNum.get(i)[0] > 0){
                offhandLM.addElement(offhandName.get(i));
            }
        }
    }
    
    static void resetInv(){
        equipped[0] = "Nothing"; equipped[1] = "Nothing"; equipped[2] = "Nothing";
        for(int i = 0; i < armorName.size(); i++){
            armorNum.get(i)[0] = 0;
        }
        for(int i = 0; i < weaponName.size(); i++){
            weaponNum.get(i)[0] = 0;
        }
        for(int i = 0; i < offhandName.size(); i++){
            offhandNum.get(i)[0] = 0;
        }
        for(int i = 0; i < toolName.size(); i++){
            toolNum.get(i)[0] = 0;
        }
        for(int i = 0; i < potionName.size(); i++){
            potionNum.get(i)[0] = 0;
        }
        for(int i = 0; i < foodName.size(); i++){
            foodNum.get(i)[0] = 0;
        }
        for(int i = 0; i < generalName.size(); i++){
            generalNum.get(i)[0] = 0;
        }
    }
    
    static void setValuesArmor(){
        armorName.add(0, "Leather Armor");          armorNum.add(0, new int[6]);
        armorNum.get(0)[1] = 25;    armorNum.get(0)[2] = 50;    armorNum.get(0)[3] = 2;     armorNum.get(0)[4] = 0; armorNum.get(0)[5] = 0;
        armorName.add(1, "Chainmail Armor");        armorNum.add(1, new int[6]);
        armorNum.get(1)[1] = 50;    armorNum.get(1)[2] = 100;   armorNum.get(1)[3] = 5;     armorNum.get(1)[4] = 0; armorNum.get(1)[5] = 0;
        armorName.add(2, "Plate Armor");           armorNum.add(2, new int[6]);
        armorNum.get(2)[1] = 125;   armorNum.get(2)[2] = 250;   armorNum.get(2)[3] = 10;    armorNum.get(2)[4] = 0; armorNum.get(2)[5] = 0;
        armorName.add(3, "Crystal Armor");         armorNum.add(3, new int[6]);
        armorNum.get(3)[1] = 250;   armorNum.get(3)[2] = 500;   armorNum.get(3)[3] = 15;    armorNum.get(3)[4] = 0; armorNum.get(3)[5] = 0;
        armorName.add(4, "Wolfhide Armor");        armorNum.add(4, new int[6]);
        armorNum.get(4)[1] = 150;   armorNum.get(4)[2] = 0;     armorNum.get(4)[3] = 7;     armorNum.get(4)[4] = 0; armorNum.get(4)[5] = 0;
        armorName.add(5, "Turtle Armor");          armorNum.add(5, new int[6]);
        armorNum.get(5)[1] = 425;   armorNum.get(5)[2] = 0;     armorNum.get(5)[3] = 13;    armorNum.get(5)[4] = 0; armorNum.get(5)[5] = 0;
        armorName.add(6, "Simple Robe");           armorNum.add(6, new int[6]);
        armorNum.get(6)[1] = 20;    armorNum.get(6)[2] = 40;    armorNum.get(6)[3] = 1;     armorNum.get(6)[4] = 0; armorNum.get(6)[5] = 2;
    }
    
    static void setValuesWeapon(){
        weaponName.add(0, "Knife");                         weaponNum.add(0, new int[6]);
        weaponNum.get(0)[1] = 3;    weaponNum.get(0)[2] = 5;    weaponNum.get(0)[3] = 0;    weaponNum.get(0)[4] = 3;    weaponNum.get(0)[5] = 0;
        weaponName.add(1, "Spear");                         weaponNum.add(1, new int[6]);
        weaponNum.get(1)[1] = 8;    weaponNum.get(1)[2] = 15;   weaponNum.get(1)[3] = 0;    weaponNum.get(1)[4] = 6;    weaponNum.get(1)[5] = 0;
        weaponName.add(2, "Axe");                           weaponNum.add(2, new int[6]);
        weaponNum.get(2)[1] = 15;   weaponNum.get(2)[2] = 30;   weaponNum.get(2)[3] = 0;    weaponNum.get(2)[4] = 10;   weaponNum.get(2)[5] = 0;
        weaponName.add(3, "Sword");                         weaponNum.add(3, new int[6]);
        weaponNum.get(3)[1] = 25;   weaponNum.get(3)[2] = 50;   weaponNum.get(3)[3] = 0;    weaponNum.get(3)[4] = 15;   weaponNum.get(3)[5] = 0;
        weaponName.add(4, "Troll Bite Gauntlet");           weaponNum.add(4, new int[6]);
        weaponNum.get(4)[1] = 425;  weaponNum.get(4)[2] = 0;    weaponNum.get(4)[3] = 0;    weaponNum.get(4)[4] = 20;   weaponNum.get(4)[5] = 0;
        weaponName.add(5, "Staff");                         weaponNum.add(5, new int[6]);
        weaponNum.get(5)[1] = 5;    weaponNum.get(5)[2] = 10;   weaponNum.get(5)[3] = 0;    weaponNum.get(5)[4] = 1;    weaponNum.get(5)[5] = 2;
        weaponName.add(6, "Broken Bottle");                 weaponNum.add(6, new int[6]);
        weaponNum.get(6)[1] = 5;    weaponNum.get(6)[2] = 10;   weaponNum.get(6)[3] = 0;    weaponNum.get(6)[4] = 5;    weaponNum.get(6)[5] = 0;
    }
    
    static void setValuesOffhand(){
        offhandName.add(0, "Shield");               offhandNum.add(0, new int[6]);
        offhandNum.get(0)[1] = 13;  offhandNum.get(0)[2] = 25;  offhandNum.get(0)[3] = 2;   offhandNum.get(0)[4] = 0;   offhandNum.get(0)[5] = 0;
        offhandName.add(1, "Spell Tome");           offhandNum.add(1, new int[6]);
        offhandNum.get(1)[1] = 13;  offhandNum.get(1)[2] = 25;  offhandNum.get(1)[3] = 0;   offhandNum.get(1)[4] = 0;   offhandNum.get(1)[5] = 10;
        offhandName.add(2, "Off-hand Dagger");      offhandNum.add(2, new int[6]);
        offhandNum.get(2)[1] = 3;   offhandNum.get(2)[2] = 5;   offhandNum.get(2)[3] = 0;   offhandNum.get(2)[4] = 2;   offhandNum.get(2)[5] = 0;
        offhandName.add(3, "Turtle Shield");        offhandNum.add(3, new int[6]);
        offhandNum.get(3)[1] = 145; offhandNum.get(3)[2] = 0;   offhandNum.get(3)[3] = 5;   offhandNum.get(3)[4] = 0;   offhandNum.get(3)[5] = 0;
        offhandName.add(4, "Voodoo Doll");          offhandNum.add(4, new int[6]);
        offhandNum.get(4)[1] = 115; offhandNum.get(4)[2] = 0;   offhandNum.get(4)[3] = 0;   offhandNum.get(4)[4] = 0;   offhandNum.get(4)[5] = 20;
        offhandName.add(5, "Tailwind Scroll");          offhandNum.add(5, new int[6]);
        offhandNum.get(5)[1] = 75; offhandNum.get(5)[2] = 0;   offhandNum.get(5)[3] = 0;   offhandNum.get(5)[4] = 0;   offhandNum.get(5)[5] = 0;
    }
    
    static void setValuesTool(){
        toolName.add(0, "Fishing Pole");        toolNum.add(0, new int[3]);
        toolNum.get(0)[1] = 10;     toolNum.get(0)[2] = 20;
        toolName.add(1, "Pickaxe");             toolNum.add(1, new int[3]);
        toolNum.get(1)[1] = 18;     toolNum.get(1)[2] = 35;
        toolName.add(2, "Shovel");              toolNum.add(2, new int[3]);
        toolNum.get(2)[1] = 25;     toolNum.get(2)[2] = 50;
        toolName.add(3, "Doll of Rebirth");     toolNum.add(3, new int[3]);
        toolNum.get(3)[1] = 600;    toolNum.get(3)[2] = 0;
        toolName.add(4, "Alchemy Kit");         toolNum.add(4, new int[3]);
        toolNum.get(4)[1] = 100;    toolNum.get(4)[2] = 200;
        toolName.add(5, "Empty Bottle");        toolNum.add(5, new int[3]);
        toolNum.get(5)[1] = 5;      toolNum.get(5)[2] = 10;
    }
    
    static void setValuesPotion(){
        potionName.add(0, "Weak Healing Potion");       potionNum.add(0, new int[3]);
        potionNum.get(0)[1] = 13;   potionNum.get(0)[2] = 25;
        potionName.add(1, "Weak Mana Potion");          potionNum.add(1, new int[3]);
        potionNum.get(1)[1] = 13;   potionNum.get(1)[2] = 25;
        potionName.add(2, "Healing Potion");            potionNum.add(2, new int[3]);
        potionNum.get(2)[1] = 25;   potionNum.get(2)[2] = 50;
        potionName.add(3, "Mana Potion");               potionNum.add(3, new int[3]);
        potionNum.get(3)[1] = 25;   potionNum.get(3)[2] = 50;
        potionName.add(4, "Potent Healing Potion");     potionNum.add(4, new int[3]);
        potionNum.get(4)[1] = 50;   potionNum.get(4)[2] = 100;
        potionName.add(5, "Potent Mana Potion");        potionNum.add(5, new int[3]);
        potionNum.get(5)[1] = 50;   potionNum.get(5)[2] = 100;
        potionName.add(6, "Nature's Fury");             potionNum.add(6, new int[3]);
        potionNum.get(6)[1] = 135;  potionNum.get(6)[2] = 0;
        potionName.add(7, "Sewer Sludge");              potionNum.add(7, new int[3]);
        potionNum.get(7)[1] = 30;   potionNum.get(7)[2] = 0;
        potionName.add(8, "Lingering Health Potion");   potionNum.add(8, new int[3]);
        potionNum.get(8)[1] = 25;   potionNum.get(8)[2] = 0;
        potionName.add(9, "Lingering Mana Potion");     potionNum.add(9, new int[3]);
        potionNum.get(9)[1] = 25;      potionNum.get(9)[2] = 0;
    }
    
    static void setValuesFood(){
        foodName.add(0, "Trail Mix");           foodNum.add(0, new int[3]);
        foodNum.get(0)[1] = 3;  foodNum.get(0)[2] = 5;
        foodName.add(1, "Cooked Fish");         foodNum.add(1, new int[3]);
        foodNum.get(1)[1] = 5;  foodNum.get(1)[2] = 10;
        foodName.add(2, "Jar of Honey");        foodNum.add(2, new int[3]);
        foodNum.get(2)[1] = 5;  foodNum.get(2)[2] = 0;
        foodName.add(3, "Tasty Berries");       foodNum.add(3, new int[3]);
        foodNum.get(3)[1] = 5;  foodNum.get(3)[2] = 0;
        foodName.add(4, "Cheese");              foodNum.add(4, new int[3]);
        foodNum.get(4)[1] = 10; foodNum.get(4)[2] = 20;
        foodName.add(5, "Cheap Wine");          foodNum.add(5, new int[3]);
        foodNum.get(5)[1] = 8;  foodNum.get(5)[2] = 15;
        foodName.add(6, "Mead");                foodNum.add(6, new int[3]);
        foodNum.get(6)[1] = 10; foodNum.get(6)[2] = 20;
        foodName.add(7, "Cider");               foodNum.add(7, new int[3]);
        foodNum.get(7)[1] = 13; foodNum.get(7)[2] = 25;
        foodName.add(8, "Dragonsbreath Ale");   foodNum.add(8, new int[3]);
        foodNum.get(8)[1] = 18; foodNum.get(8)[2] = 35;
        
    }
    
    static void setValuesGeneral(){
        generalName.add(0, "Wolf Pelt");            generalNum.add(0, new int[3]);
        generalNum.get(0)[1] = 25;  generalNum.get(0)[2] = 0;
        generalName.add(1, "Wooden Doll");          generalNum.add(1, new int[3]);
        generalNum.get(1)[1] = 10;  generalNum.get(1)[2] = 0;
        generalName.add(2, "Soggy Boot");           generalNum.add(2, new int[3]);
        generalNum.get(2)[1] = 10;  generalNum.get(2)[2] = 0;
        generalName.add(3, "Turtle Shell");         generalNum.add(3, new int[3]);
        generalNum.get(3)[1] = 20;  generalNum.get(3)[2] = 0;
        generalName.add(4, "Troll Tooth");          generalNum.add(4, new int[3]);
        generalNum.get(4)[1] = 30;  generalNum.get(4)[2] = 0;
        generalName.add(5, "Heartbloom");           generalNum.add(5, new int[3]);
        generalNum.get(5)[1] = 5;   generalNum.get(5)[2] = 0;
        generalName.add(6, "Mageflower");           generalNum.add(6, new int[3]);
        generalNum.get(6)[1] = 5;   generalNum.get(6)[2] = 0;
        generalName.add(7, "Venom Vine");           generalNum.add(7, new int[3]);
        generalNum.get(7)[1] = 15;  generalNum.get(7)[2] = 0;
        generalName.add(8, "Metallite Ore");        generalNum.add(8, new int[3]);
        generalNum.get(8)[1] = 10;  generalNum.get(8)[2] = 0;
        generalName.add(9, "Crystal Shard");        generalNum.add(9, new int[3]);
        generalNum.get(9)[1] = 40;  generalNum.get(9)[2] = 0;
        generalName.add(10, "Shiny Rocks");         generalNum.add(10, new int[3]);
        generalNum.get(10)[1] = 5;  generalNum.get(10)[2] = 0;
        generalName.add(11, "Yeti Fur");            generalNum.add(11, new int[3]);
        generalNum.get(11)[1] = 5;  generalNum.get(11)[2] = 0;
        generalName.add(12, "Rat Tail");            generalNum.add(12, new int[3]);
        generalNum.get(12)[1] = 5;  generalNum.get(12)[2] = 0;
        generalName.add(13, "Alligator Tooth");     generalNum.add(13, new int[3]);
        generalNum.get(13)[1] = 25; generalNum.get(13)[2] = 0;
        generalName.add(14, "Alligator Hide");      generalNum.add(14, new int[3]);
        generalNum.get(14)[1] = 65; generalNum.get(14)[2] = 0;
        generalName.add(15, "Ruby");      generalNum.add(15, new int[3]);
        generalNum.get(15)[1] = 75; generalNum.get(15)[2] = 0;
        generalName.add(16, "Emerald");      generalNum.add(16, new int[3]);
        generalNum.get(16)[1] = 85; generalNum.get(16)[2] = 0;
        generalName.add(17, "Saphire");      generalNum.add(17, new int[3]);
        generalNum.get(17)[1] = 95; generalNum.get(17)[2] = 0;
        generalName.add(18, "Diamond");      generalNum.add(18, new int[3]);
        generalNum.get(18)[1] = 125; generalNum.get(18)[2] = 0;
        generalName.add(19, "Blank Scroll");      generalNum.add(19, new int[3]);
        generalNum.get(19)[1] = 25; generalNum.get(19)[2] = 30;
        generalName.add(20, "Mage's Ink");      generalNum.add(20, new int[3]);
        generalNum.get(20)[1] = 25; generalNum.get(20)[2] = 0;
        generalName.add(21, "Looted Jewels");      generalNum.add(21, new int[3]);
        generalNum.get(21)[1] = 65; generalNum.get(21)[2] = 0;
    }
    
    protected static void resetInventory(){
        equipped[0] = "Nothing";
        equipped[1] = "Nothing";
        equipped[2] = "Nothing";
        for(int i = 0; i < armorName.size(); i++){
            armorNum.get(i)[0] = 0;
        }
        for(int i = 0; i < weaponName.size(); i++){
            weaponNum.get(i)[0] = 0;
        }
        for(int i = 0; i < offhandName.size(); i++){
            offhandNum.get(i)[0] = 0;
        }
        for(int i = 0; i < toolName.size(); i++){
            toolNum.get(i)[0] = 0;
        }
        for(int i = 0; i < potionName.size(); i++){
            potionNum.get(i)[0] = 0;
        }
        for(int i = 0; i < foodName.size(); i++){
            foodNum.get(i)[0] = 0;
        }
        for(int i = 0; i < generalName.size(); i++){
            generalNum.get(i)[0] = 0;
        }
    }
   
    protected static void invMenu(){
        Main.screen = "invMenu";
        
        Main.output.setText("Which category of items do you want to look at?" + "\n");
        Main.output.append("\n");
        Main.output.append("Currently Equipped:" +"\n");
        Main.output.append("Armor: " + equipped[0] +"\n");
        Main.output.append("Weapon: " + equipped[1] +"\n");
        Main.output.append("Offhand: " + equipped[2] +"\n");
        Main.output.append("\n");
        Main.output.append("Equipment Armor Value: " + stats.myArmor[0] + "\n");
        Main.output.append("Equipment Weapon Damage: " + stats.myDamage[0] + "\n");
        Main.output.append("Equipment Spellpower: " + stats.mySPower[0] + "\n");
        
        Main.btn1.setText("Armor");
        Main.btn2.setText("Weapons");
        Main.btn3.setText("Offhand");
        Main.btn4.setText("Tools");
        Main.btn5.setText("More Choices");
        Main.btn6.setText("Close Inventory");
    }
    
    protected static void invMenu2(){
        Main.screen = "invMenu2";
        
        Main.btn1.setText("Potions");
        Main.btn2.setText("Food");
        Main.btn3.setText("General");
        Main.btn4.setText("Crafting");
        Main.btn5.setText("Less Choices");
        Main.btn6.setText("Close Inventory");
    }
    
    protected static void invArmor(){
        Main.screen = "invArmor";
        Main.output.setText("Armor Items:" + "\n");
        for(int i = 0; i < armorName.size(); i++){
            if(armorName.get(i)!= null && armorNum.get(i)[0]>0){
                Main.output.append(armorName.get(i) + " x" + armorNum.get(i)[0]);
                if(equipped[0] == armorName.get(i)){
                    Main.output.append(" (EQUIPPED!)");
                }
                Main.output.append("\n");
            }
        }
        Main.btn1.setText("Equip");
        Main.btn2.setText("Back to Inventory");
        Main.btn3.setText("Close Inventory");
        Main.btn4.setText(" ");
        Main.btn5.setText(" ");
        Main.btn6.setText(" ");
    }
    
    protected static void invWeapon(){
        Main.screen = "invWeapon";
        Main.output.setText("Weapon Items:" + "\n");
        for(int i = 0; i < weaponName.size(); i++){
            if(weaponName.get(i) != null && weaponNum.get(i)[0]>0){
                Main.output.append(weaponName.get(i) + " x" + weaponNum.get(i)[0]);
                if(equipped[1] == weaponName.get(i)){
                    Main.output.append(" (EQUIPPED!)");
                }
                Main.output.append("\n");
            }
        }
        Main.btn1.setText("Equip");
        Main.btn2.setText("Back to Inventory");
        Main.btn3.setText("Close Inventory");
        Main.btn4.setText(" ");
        Main.btn5.setText(" ");
        Main.btn6.setText(" ");
    }
    
    protected static void invOffhand(){
        Main.screen = "invOffhand";
        Main.output.setText("Offhand Items:" + "\n");
        for(int i = 0; i < offhandName.size(); i++){
            if(offhandName.get(i) != null && offhandNum.get(i)[0]>0){
                Main.output.append(offhandName.get(i) + " x" + offhandNum.get(i)[0]);
                if(equipped[2] == offhandName.get(i)){
                    Main.output.append(" (EQUIPPED!)");
                }
                Main.output.append("\n");
            }
        }
        Main.btn1.setText("Equip");
        Main.btn2.setText("Back to Inventory");
        Main.btn3.setText("Close Inventory");
        Main.btn4.setText(" ");
        Main.btn5.setText(" ");
        Main.btn6.setText(" ");
    }
    
    protected static void invTool(){
        Main.screen = "invTool";
        Main.output.setText("Tool Items:" + "\n");
        for(int i = 0; i < toolName.size(); i++){
            if(toolName.get(i) != null && toolNum.get(i)[0]>0){
                Main.output.append(toolName.get(i) + " x" + toolNum.get(i)[0] + "\n");
            }
        }
        Main.btn1.setText("Back to Inventory");
        Main.btn2.setText("Close Inventory");
        Main.btn3.setText(" ");
        Main.btn4.setText(" ");
        Main.btn5.setText(" ");
        Main.btn6.setText(" ");
    }
    
    protected static void invPotion(){
        Main.screen = "invPotion";
        Main.output.setText("Potions:" + "\n");
        for(int i = 0; i < potionName.size(); i++){
            if(potionName.get(i) != null && potionNum.get(i)[0]>0){
                Main.output.append(potionName.get(i) + " x" + potionNum.get(i)[0] + "\n");
            }
        }
        Main.btn1.setText("Back to Inventory");
        Main.btn2.setText("Close Inventory");
        Main.btn3.setText(" ");
        Main.btn4.setText(" ");
        Main.btn5.setText(" ");
        Main.btn6.setText(" ");
    }
    
    protected static void invFood(){
        Main.screen = "invFood";
        Main.output.setText("Food Items:" + "\n");
        for(int i = 0; i < foodName.size(); i++){
            if(foodName.get(i) != null && foodNum.get(i)[0]>0){
                Main.output.append(foodName.get(i) + " x" + foodNum.get(i)[0] + "\n");
            }
        }
        Main.btn1.setText("Back to Inventory");
        Main.btn2.setText("Close Inventory");
        Main.btn3.setText(" ");
        Main.btn4.setText(" ");
        Main.btn5.setText(" ");
        Main.btn6.setText(" ");
    }
    
    protected static void invGeneral(){
        Main.screen = "invGeneral";
        Main.output.setText("General Items:" + "\n");
        for(int i = 0; i < generalName.size(); i++){
            if(generalName.get(i) != null && generalNum.get(i)[0]>0){
                Main.output.append(generalName.get(i) + " x" + generalNum.get(i)[0] + "\n");
            }
        }
        Main.btn1.setText("Back to Inventory");
        Main.btn2.setText("Close Inventory");
        Main.btn3.setText(" ");
        Main.btn4.setText(" ");
        Main.btn5.setText(" ");
        Main.btn6.setText(" ");
    }
    
    protected static void equip(){
        fillLists();
        armorSelect.setModel(armorLM); armorSelect.setSelectedIndex(0);
        weaponSelect.setModel(weaponLM); weaponSelect.setSelectedIndex(0);
        offhandSelect.setModel(offhandLM); offhandSelect.setSelectedIndex(0);
        equipWindow.pack();
        equipWindow.setVisible(true);
    }
    
    protected static void updateEquipped(){
        stats.myArmor[0] = 0;
        stats.myDamage[0] = 0;
        stats.mySPower[0] = 0;
        if(armorSelect.getSelectedValue().toString().equals("Nothing")){
            equipped[0] = "Nothing";
            equippedNum[0] = -1;
        }else{
            equippedNum[0] = getIndex(armorSelect.getSelectedValue().toString(), 1);
            equipped[0] = armorName.get(equippedNum[0]); stats.myArmor[0] += armorNum.get(equippedNum[0])[3]; stats.myDamage[0] += armorNum.get(equippedNum[0])[4]; stats.mySPower[0] += armorNum.get(equippedNum[0])[5];
        }
        if(weaponSelect.getSelectedValue().toString().equals("Nothing")){
            equipped[1] = "Nothing";
            equippedNum[1] = -1;
        }else{
            equippedNum[1] = getIndex(weaponSelect.getSelectedValue().toString(), 2);
            equipped[1] = weaponName.get(equippedNum[1]); stats.myArmor[0] += weaponNum.get(equippedNum[1])[3]; stats.myDamage[0] += weaponNum.get(equippedNum[1])[4]; stats.mySPower[0] += weaponNum.get(equippedNum[1])[5];
        }
        if(offhandSelect.getSelectedValue().toString().equals("Nothing")){
            equipped[2] = "Nothing";
            equippedNum[2] = -1;
        }else{
            equippedNum[2] = getIndex(offhandSelect.getSelectedValue().toString(), 3);
            equipped[2] = offhandName.get(equippedNum[2]); stats.myArmor[0] += offhandNum.get(equippedNum[2])[3]; stats.myDamage[0] += offhandNum.get(equippedNum[2])[4]; stats.mySPower[0] += offhandNum.get(equippedNum[2])[5];
        }
        equipWindow.setVisible(false);
        Main.updateLabels();
        invMenu();
    }
    
    protected static void loadEquipped(){
        stats.myArmor[0] = 0;
        stats.myDamage[0] = 0;
        stats.mySPower[0] = 0;
        if(equippedNum[0] == -1){
            equipped[0] = "Nothing";
        }else{
            equipped[0] = armorName.get(equippedNum[0]); stats.myArmor[0] += armorNum.get(equippedNum[0])[3]; stats.myDamage[0] += armorNum.get(equippedNum[0])[4]; stats.mySPower[0] += armorNum.get(equippedNum[0])[5];
        }
        if(equippedNum[1] == -1){
            equippedNum[1] = -1;
        }else{
            equipped[1] = weaponName.get(equippedNum[1]); stats.myArmor[0] += weaponNum.get(equippedNum[1])[3]; stats.myDamage[0] += weaponNum.get(equippedNum[1])[4]; stats.mySPower[0] += weaponNum.get(equippedNum[1])[5];
        }
        if(equippedNum[2] == -1){
            equippedNum[2] = -1;
        }else{
            equipped[2] = offhandName.get(equippedNum[2]); stats.myArmor[0] += offhandNum.get(equippedNum[2])[3]; stats.myDamage[0] += offhandNum.get(equippedNum[2])[4]; stats.mySPower[0] += offhandNum.get(equippedNum[2])[5];
        }
    }
    
    static int getIndex(String key, int array){
        int index = -1;
        switch(array){
            case 1: //Armor
                for(int i = 0; i < armorName.size(); i++){
                    if(armorName.get(i) == key.toString()){
                        index = i;
                    }
                } break;
            case 2: //Weapon
                for(int i = 0; i < weaponName.size(); i++){
                    if(weaponName.get(i) == key.toString()){
                        index = i;
                    }
                } break;
            case 3: //Offhand
                for(int i = 0; i < offhandName.size(); i++){
                    if(offhandName.get(i) == key.toString()){
                        index = i;
                    }
                } break;
            case 4: //Tool
                for(int i = 0; i < toolName.size(); i++){
                    if(toolName.get(i) == key.toString()){
                        index = i;
                    }
                } break;
            case 5: //Potion
                for(int i = 0; i < potionName.size(); i++){
                    if(potionName.get(i) == key.toString()){
                        index = i;
                    }
                } break;
            case 6: //Food
                for(int i = 0; i < foodName.size(); i++){
                    if(foodName.get(i) == key.toString()){
                        index = i;
                    }
                } break;
            case 7: //General
                for(int i = 0; i < generalName.size(); i++){
                    if(generalName.get(i) == key.toString()){
                        index = i;
                    }
                } break;
        }
        return index;
    }
    
}
