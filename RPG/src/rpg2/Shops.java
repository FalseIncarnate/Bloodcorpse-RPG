
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

public class Shops {
    
    //GUI Components
    static JDialog sellWindow;
    static JList sellSelect = new JList();
    static DefaultListModel sellLM = new DefaultListModel();
    static JLabel itemDesc = new JLabel("Nothing selected.");
    static JLabel itemDesc2 = new JLabel("");
    static JPanel btnPanel = new JPanel();
    static JButton sellOneBtn = new JButton("Sell 1");
    static JButton sellFiveBtn = new JButton("Sell 5");
    static JButton sellTenBtn = new JButton("Sell 10");
    static JButton sellAllBtn = new JButton("Sell All");
    
    static int array = 0;
    static boolean equippedItem = false;
    static int equippedIndex = 0;
    
    //SELL GUI
    protected static void buildGUI(){
        //Initialize sellWindow, set to modal
        sellWindow = new JDialog(Main.window, "Sell Items");
        sellWindow.setModal(true);
        //Declare and initialize GridBagConstraints variable
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        //Set sellWindow layout to GridBagLayout
        sellWindow.setLayout(new GridBagLayout());
        //Add actionListeners to buttons
        sellOneBtn.addActionListener(Main.h);
        sellFiveBtn.addActionListener(Main.h);
        sellTenBtn.addActionListener(Main.h);
        sellAllBtn.addActionListener(Main.h);
        //Add buttons to btnPanel
        btnPanel.add(sellOneBtn);
        btnPanel.add(sellFiveBtn);
        btnPanel.add(sellTenBtn);
        btnPanel.add(sellAllBtn);
        //Set btnPanel layout to BoxLayout (Horizontal)
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        //Add ListSelectionListener to sellSelect
        sellSelect.addListSelectionListener(Main.h2);
        //Add sellSelect to grid (0, 0)
        c.gridx = 0; c.gridy = 0; sellWindow.add(sellSelect, c);
        //Add itemDesc to grid (1, 0) and itemDesc2 to grid (1, 1);
        c.gridx = 1; c.gridy = 0; sellWindow.add(itemDesc, c);
        c.gridx = 1; c.gridy = 1; sellWindow.add(itemDesc2, c);
        //Add btnPanel to grid (0, 2)
        c.gridx = 0; c.gridy = 2; sellWindow.add(btnPanel, c);
        sellWindow.pack();
    }
    
    protected static void fillLists(int category){
        //Clear data list, set equippedItem to false
        sellLM.removeAllElements();
        equippedItem = false;
        
        //Pass category to array
        array = category;
        
        //Select appropriate item category
        switch(array){
            case 1: //Armor
                for(int i = 0; i < Inv.armorName.size(); i++){
                    if(Inv.armorName.get(i) != null && Inv.armorNum.get(i)[0] > 0){
                        if(Inv.equipped[0] == Inv.armorName.get(i)){
                            if(Inv.armorNum.get(i)[0] - 1 > 0){
                                sellLM.addElement(Inv.armorName.get(i));
                            }
                            equippedIndex = i;
                            equippedItem = true;
                        }else{
                            sellLM.addElement(Inv.armorName.get(i));
                        }
                    }
                } break;
            case 2: //Weapon
                for(int i = 0; i < Inv.weaponName.size(); i++){
                    if(Inv.weaponName.get(i) != null && Inv.weaponNum.get(i)[0] > 0){
                        if(Inv.equipped[1] == Inv.weaponName.get(i)){
                            if(Inv.weaponNum.get(i)[0] - 1 > 0){
                                sellLM.addElement(Inv.weaponName.get(i));
                            }
                            equippedIndex = i;
                            equippedItem = true;
                        }else{
                                sellLM.addElement(Inv.weaponName.get(i));
                            }
                    }
                } break;
            case 3: //Offhand
                for(int i = 0; i < Inv.offhandName.size(); i++){
                    if(Inv.offhandName.get(i) != null && Inv.offhandNum.get(i)[0] > 0){
                        if(Inv.equipped[2] == Inv.offhandName.get(i)){
                            if(Inv.offhandNum.get(i)[0] - 1 > 0){
                                sellLM.addElement(Inv.offhandName.get(i));
                            }
                            equippedIndex = i;
                            equippedItem = true;
                        }else{
                            sellLM.addElement(Inv.offhandName.get(i));
                        }
                    }
                } break;
            case 4: //Tool
                for(int i = 0; i < Inv.toolName.size(); i++){
                    if(Inv.toolName.get(i) != null && Inv.toolNum.get(i)[0] > 0){
                        sellLM.addElement(Inv.toolName.get(i));
                    }
                } break;
            case 5: //Potion
                for(int i = 0; i < Inv.potionName.size(); i++){
                    if(Inv.potionName.get(i) != null && Inv.potionNum.get(i)[0] > 0){
                        sellLM.addElement(Inv.potionName.get(i));
                    }
                } break;
            case 6: //Food
                for(int i = 0; i < Inv.foodName.size(); i++){
                    if(Inv.foodName.get(i) != null && Inv.foodNum.get(i)[0] > 0){
                        sellLM.addElement(Inv.foodName.get(i));
                    }
                } break;
            case 7: //General
                for(int i = 0; i < Inv.generalName.size(); i++){
                    if(Inv.generalName.get(i) != null && Inv.generalNum.get(i)[0] > 0){
                        sellLM.addElement(Inv.generalName.get(i));
                    }
                } break;
        }
        if(sellLM.isEmpty()){
            sellLM.addElement("Nothing");
        }
        
    }
    
    protected static void updateButtons(int index){
        sellOneBtn.setEnabled(true);
        sellFiveBtn.setEnabled(true);
        sellTenBtn.setEnabled(true);
        sellAllBtn.setEnabled(true);
        int num = 0;
        switch(array){
            case 0: //No Available Items
                sellOneBtn.setEnabled(false);
                sellFiveBtn.setEnabled(false);
                sellTenBtn.setEnabled(false);
                sellAllBtn.setEnabled(false);
                break;
            case 1: //Armor
                num = Inv.armorNum.get(index)[0];
                if(equippedItem == true && index == equippedIndex){
                    num --;
                }
                if(num < 10){
                    sellTenBtn.setEnabled(false);
                }
                if(num < 5){
                    sellFiveBtn.setEnabled(false);
                }
                if(sellSelect.getSelectedValue().toString() == "Nothing"){
                    sellOneBtn.setEnabled(false);
                    sellFiveBtn.setEnabled(false);
                    sellTenBtn.setEnabled(false);
                    sellAllBtn.setEnabled(false);
                }
                break;
            case 2: //Weapons
                num = Inv.weaponNum.get(index)[0];
                if(equippedItem == true && index == equippedIndex){
                    num --;
                }
                if(num < 10){
                    sellTenBtn.setEnabled(false);
                }
                if(num < 5){
                    sellFiveBtn.setEnabled(false);
                }
                if(sellSelect.getSelectedValue().toString() == "Nothing"){
                    sellOneBtn.setEnabled(false);
                    sellFiveBtn.setEnabled(false);
                    sellTenBtn.setEnabled(false);
                    sellAllBtn.setEnabled(false);
                }
                break;
            case 3: //Offhand
                num = Inv.offhandNum.get(index)[0];
                if(equippedItem == true && index == equippedIndex){
                    num --;
                }
                if(num < 10){
                    sellTenBtn.setEnabled(false);
                }
                if(num < 5){
                    sellFiveBtn.setEnabled(false);
                }
                if(sellSelect.getSelectedValue().toString() == "Nothing"){
                    sellOneBtn.setEnabled(false);
                    sellFiveBtn.setEnabled(false);
                    sellTenBtn.setEnabled(false);
                    sellAllBtn.setEnabled(false);
                }
                break;
            case 4: //Tools
                num = Inv.toolNum.get(index)[0];
                if(num < 10){
                    sellTenBtn.setEnabled(false);
                }
                if(num < 5){
                    sellFiveBtn.setEnabled(false);
                }
                if(sellSelect.getSelectedValue().toString() == "Nothing"){
                    sellOneBtn.setEnabled(false);
                    sellFiveBtn.setEnabled(false);
                    sellTenBtn.setEnabled(false);
                    sellAllBtn.setEnabled(false);
                }
                break;
            case 5: //Potions
                num = Inv.potionNum.get(index)[0];
                if(num < 10){
                    sellTenBtn.setEnabled(false);
                }
                if(num < 5){
                    sellFiveBtn.setEnabled(false);
                }
                if(sellSelect.getSelectedValue().toString() == "Nothing"){
                    sellOneBtn.setEnabled(false);
                    sellFiveBtn.setEnabled(false);
                    sellTenBtn.setEnabled(false);
                    sellAllBtn.setEnabled(false);
                }
                break;
            case 6: //Food
                num = Inv.foodNum.get(index)[0];
                if(num < 10){
                    sellTenBtn.setEnabled(false);
                }
                if(num < 5){
                    sellFiveBtn.setEnabled(false);
                }
                if(sellSelect.getSelectedValue().toString() == "Nothing"){
                    sellOneBtn.setEnabled(false);
                    sellFiveBtn.setEnabled(false);
                    sellTenBtn.setEnabled(false);
                    sellAllBtn.setEnabled(false);
                }
                break;
            case 7: //General
                num = Inv.generalNum.get(index)[0];
                if(num < 10){
                    sellTenBtn.setEnabled(false);
                }
                if(num < 5){
                    sellFiveBtn.setEnabled(false);
                }
                if(sellSelect.getSelectedValue().toString() == "Nothing"){
                    sellOneBtn.setEnabled(false);
                    sellFiveBtn.setEnabled(false);
                    sellTenBtn.setEnabled(false);
                    sellAllBtn.setEnabled(false);
                }
                break;
        }
    }
    
    //SHOPS MENU
    
    protected static void shopsMenu(){
        Main.output.setText("You stand in Vendor Row, a street lined with shops." + "\n");
        Main.output.append("Both sides of the road are packed with stores, craftsmen, and shoppers." + "\n");
        Main.output.append("Where would you like to go?" + "\n");
        Main.screen = "shopsMenu";
        Main.btn1.setText("General Store");
        Main.btn2.setText("Blacksmith");
        Main.btn3.setText("Mystic Emporium");
        Main.btn4.setText("Apothecary");
        Main.btn5.setText("Town Center");
        Main.btn6.setText("Return to Camp");
    }
    
    //GENERAL STORE
    
    protected static void genStoreMenu(){
        Main.screen = "genStoreMenu";
        Main.btn1.setText("Buy");
        Main.btn2.setText("Sell");
        Main.btn3.setText("Leave Store");
        Main.btn4.setText(" ");
        Main.btn5.setText(" ");
        Main.btn6.setText(" ");
        Main.output.setText("The shopkeeper greets you as soon as you walk in the door." + "\n");
        Main.output.append("\"Welcome, welcome! Feel free to browse, everything has a price!\" he babbles excitedly." + "\n");
    }
    
    protected static void genBuyMenu(){
        Main.output.setText("\"Looking to buy? What can I interest you in?\"" + "\n");
        Main.output.append("Trail Mix: 5 GP" + "\n");
        Main.output.append("Cooked Fish: 10 GP" + "\n");
        Main.output.append("Fishing Pole: 20 GP" + "\n");
        Main.output.append("Pickaxe: 35 GP" + "\n");
        Main.output.append("Shovel: 50 GP" + "\n");
        Main.screen = "genBuyMenu";
        Main.btn1.setText("Trail Mix");
        Main.btn2.setText("Cooked Fish");
        Main.btn3.setText("Fishing Pole");
        Main.btn4.setText("Pickaxe");
        Main.btn5.setText("Shovel");
        Main.btn6.setText("Nothing");
    }
    
    protected static void genSellMenu(){
        Main.output.setText("\"Looking to sell? Let's see what you've got then...\"" + "\n");
        Main.output.append("Pick a category to sell from." + "\n");
        Main.screen = "genSellMenu";
        Main.btn1.setText("Armor");
        Main.btn2.setText("Weapons");
        Main.btn3.setText("Offhand");
        Main.btn4.setText("Tools");
        Main.btn5.setText("More Choices");
        Main.btn6.setText("Nothing");
    }
    
    protected static void genSellMenu2(){
        Main.screen = "genSellMenu2";
        Main.btn1.setText("Potions");
        Main.btn2.setText("Food");
        Main.btn3.setText("General");
        Main.btn4.setText("Less Choices");
        Main.btn5.setText("Nothing");
        Main.btn6.setText(" ");
    }
    
    protected static void genBuy(int choice){
        Main.output.append("\n");
        switch(choice){
            case 1: //Trail Mix
                if(stats.myGP[0] >= Inv.foodNum.get(0)[2]){
                    Main.output.append("You purchase a " + Inv.foodName.get(0) + "." + "\n");
                    stats.myGP[0] -= Inv.foodNum.get(0)[2];
                    Inv.foodNum.get(0)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.foodName.get(0) + ": You can't afford this." + "\n");
                } break;
            case 2: //Cooked Fish
                if(stats.myGP[0] >= Inv.foodNum.get(1)[2]){
                    Main.output.append("You purchase a " + Inv.foodName.get(1) + "." + "\n");
                    stats.myGP[0] -= Inv.foodNum.get(1)[2];
                    Inv.foodNum.get(1)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.foodName.get(1) + ": You can't afford this." + "\n");
                } break;
            case 3: //Fishing Pole
                if(stats.myGP[0] >= Inv.toolNum.get(0)[2]){
                    Main.output.append("You purchase a " + Inv.toolName.get(0) + "." + "\n");
                    stats.myGP[0] -= Inv.toolNum.get(0)[2];
                    Inv.toolNum.get(0)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.toolName.get(0) + ": You can't afford this." + "\n");
                } break;
            case 4: //Pickaxe
                if(stats.myGP[0] >= Inv.toolNum.get(1)[2]){
                    Main.output.append("You purchase a " + Inv.toolName.get(1) + "." + "\n");
                    stats.myGP[0] -= Inv.toolNum.get(1)[2];
                    Inv.toolNum.get(1)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.toolName.get(1) + ": You can't afford this." + "\n");
                } break;
            case 5: //Shovel
                if(stats.myGP[0] >= Inv.toolNum.get(2)[2]){
                    Main.output.append("You purchase a " + Inv.toolName.get(2) + "." + "\n");
                    stats.myGP[0] -= Inv.toolNum.get(2)[2];
                    Inv.toolNum.get(2)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.toolName.get(2) + ": You can't afford this." + "\n");
                } break;
        }
    }
    
    protected static void sell(int array){
        fillLists(array);
        sellSelect.setModel(sellLM); sellSelect.setSelectedIndex(0);
        sellWindow.pack();
        sellWindow.setVisible(true);
    }
    
    //BLACKSMITH
    
    protected static void blacksmithMenu(){
        Main.output.setText("The blacksmith looks up from his anvil at you expectantly." + "\n");
        if(stats.isFemale == true){
            Main.output.append("\"What can aye do ye fer, wee lassie?\" he asks in his outrageously thick accent." + "\n");
        }else{
            Main.output.append("\"What can aye do ye fer, me boy o'?\" he asks in his outrageously thick accent." + "\n");
        }
        Main.screen = "bsMenu";
        Main.btn1.setText("Buy Armor");
        Main.btn2.setText("Buy Weapon");
        Main.btn3.setText("Crafting");
        Main.btn4.setText("Leave");
        Main.btn5.setText(" ");
        Main.btn6.setText(" ");
    }
    
    protected static void bsArmorBuyMenu(){
        Main.output.setText("\"Without me armor, ye look no scarier than me Nanny!\"" + "\n");
        Main.output.append("Shield: 25 GP" + "\n");
        Main.output.append("Leather Armor: 50 GP" + "\n");
        Main.output.append("Chainmail Armor: 100 GP" + "\n");
        Main.output.append("Plate Armor: 250 GP" + "\n");
        Main.output.append("Crystal Armor: 500 GP" + "\n");
        Main.screen = "bsArmorBuyMenu";
        Main.btn1.setText("Shield");
        Main.btn2.setText("Leather Armor");
        Main.btn3.setText("Chainmail Armor");
        Main.btn4.setText("Plate Armor");
        Main.btn5.setText("Crystal Armor");
        Main.btn6.setText("Nothing");
    }
    
    protected static void bsWeaponBuyMenu(){
        Main.output.setText("\"You ne'er hurt anyone wieldin' anything other than one o' me weap'ns!\"" + "\n");
        Main.output.append("Knife: 5 GP" + "\n");
        Main.output.append("Spear: 15 GP" + "\n");
        Main.output.append("Axe: 30 GP" + "\n");
        Main.output.append("Sword: 50 GP" + "\n");
        Main.output.append("Off-hand Dagger: 5 GP" + "\n");
        Main.screen = "bsWeaponBuyMenu";
        Main.btn1.setText("Knife");
        Main.btn2.setText("Spear");
        Main.btn3.setText("Axe");
        Main.btn4.setText("Sword");
        Main.btn5.setText("Off-hand Dagger");
        Main.btn6.setText("Nothing");
    }
    
    protected static void bsArmorBuy(int choice){
        Main.output.append("\n");
        switch(choice){
            case 1: //Shield
                if(stats.myGP[0] >= Inv.offhandNum.get(0)[2]){
                    Main.output.append("You purchase a " + Inv.offhandName.get(0) + "." + "\n");
                    stats.myGP[0] -= Inv.offhandNum.get(0)[2];
                    Inv.offhandNum.get(0)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.offhandName.get(0) + ": You can't afford this." + "\n");
                } break;
            case 2: //Leather Armor
                if(stats.myGP[0] >= Inv.armorNum.get(0)[2]){
                    Main.output.append("You purchase a " + Inv.armorName.get(0) + "." + "\n");
                    stats.myGP[0] -= Inv.armorNum.get(0)[2];
                    Inv.armorNum.get(0)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.armorName.get(0) + ": You can't afford this." + "\n");
                } break;
            case 3: //Chainmail Armor
                if(stats.myGP[0] >= Inv.armorNum.get(1)[2]){
                    Main.output.append("You purchase a " + Inv.armorName.get(1) + "." + "\n");
                    stats.myGP[0] -= Inv.armorNum.get(1)[2];
                    Inv.armorNum.get(1)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.armorName.get(1) + ": You can't afford this." + "\n");
                } break;
            case 4: //Plate Armor
                if(stats.myGP[0] >= Inv.armorNum.get(2)[2]){
                    Main.output.append("You purchase a " + Inv.armorName.get(2) + "." + "\n");
                    stats.myGP[0] -= Inv.armorNum.get(2)[2];
                    Inv.armorNum.get(2)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.armorName.get(2) + ": You can't afford this." + "\n");
                } break;
            case 5: //Crystal Armor
                if(stats.myGP[0] >= Inv.armorNum.get(3)[2]){
                    Main.output.append("You purchase a " + Inv.armorName.get(3) + "." + "\n");
                    stats.myGP[0] -= Inv.armorNum.get(3)[2];
                    Inv.armorNum.get(3)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.armorName.get(3) + ": You can't afford this." + "\n");
                } break;
        }
    }
    
    protected static void bsWeaponBuy(int choice){
        Main.output.append("\n");
        switch(choice){
            case 1: //Knife
                if(stats.myGP[0] >= Inv.weaponNum.get(0)[2]){
                    Main.output.append("You purchase a " + Inv.weaponName.get(0) + "." + "\n");
                    stats.myGP[0] -= Inv.weaponNum.get(0)[2];
                    Inv.weaponNum.get(0)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.weaponName.get(0) + ": You can't afford this." + "\n");
                } break;
            case 2: //Spear
                if(stats.myGP[0] >= Inv.weaponNum.get(1)[2]){
                    Main.output.append("You purchase a " + Inv.weaponName.get(1) + "." + "\n");
                    stats.myGP[0] -= Inv.weaponNum.get(1)[2];
                    Inv.weaponNum.get(1)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.weaponName.get(1) + ": You can't afford this." + "\n");
                } break;
            case 3: //Axe
                if(stats.myGP[0] >= Inv.weaponNum.get(2)[2]){
                    Main.output.append("You purchase a " + Inv.weaponName.get(2) + "." + "\n");
                    stats.myGP[0] -= Inv.weaponNum.get(2)[2];
                    Inv.weaponNum.get(2)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.weaponName.get(2) + ": You can't afford this." + "\n");
                } break;
            case 4: //Sword
                if(stats.myGP[0] >= Inv.weaponNum.get(3)[2]){
                    Main.output.append("You purchase a " + Inv.weaponName.get(3) + "." + "\n");
                    stats.myGP[0] -= Inv.weaponNum.get(3)[2];
                    Inv.weaponNum.get(3)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.weaponName.get(3) + ": You can't afford this." + "\n");
                } break;
            case 5: //Off-hand Dagger
                if(stats.myGP[0] >= Inv.offhandNum.get(2)[2]){
                    Main.output.append("You purchase a " + Inv.offhandName.get(2) + "." + "\n");
                    stats.myGP[0] -= Inv.offhandNum.get(2)[2];
                    Inv.offhandNum.get(2)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.offhandName.get(2) + ": You can't afford this." + "\n");
                } break;
        }
    }
    
    //MAGIC SHOP
    
    protected static void magicStoreMenu(){
        Main.output.setText("You walk into the cluttered shop, and are greeted by a bearded man in a robe." + "\n");
        Main.output.append("\"Welcome to the Mystic Emporium! Quality goods for the discerning mage!\" The wizard claims." + "\n");
        Main.output.append("\"I also sell spell training, if you've got the mind for it...\" He adds with a wink." + "\n");
        Main.screen = "msMenu";
        Main.btn1.setText("Buy Items");
        Main.btn2.setText("Buy Spells");
        Main.btn3.setText("Crafting");
        Main.btn4.setText("Leave");
        Main.btn5.setText(" ");
        Main.btn6.setText(" ");
    }
    
    protected static void msItemBuyMenu(){
        if(stats.isFemale == true){
            Main.output.setText("\"Ah, I had a hunch you were a spellcaster kind of gal...\"" + "\n");
        }else{
            Main.output.setText("\"Ah, I had a hunch you were a spellcaster kind of guy...\"" + "\n");
        }
        Main.output.append("Staff: " + Inv.weaponNum.get(5)[2] + " GP" + "\n");
        Main.output.append("Spell Tome: " + Inv.offhandNum.get(1)[2] + " GP" + "\n");
        Main.output.append("Simple Robe: " + Inv.armorNum.get(6)[2] + " GP" + "\n");
        Main.output.append("Blank Scroll: " + Inv.generalNum.get(19)[2] + " GP" + "\n");
        Main.screen = "msItemBuyMenu";
        Main.btn1.setText("Staff");
        Main.btn2.setText("Spell Tome");
        Main.btn3.setText("Simple Robe");
        Main.btn4.setText("Blank Scroll");
        Main.btn5.setText("Nothing");
        Main.btn6.setText(" ");
    }
    
    protected static void msSpellBuyMenu(){
        JOptionPane.showMessageDialog(Main.window, "This feature is currently under development, and will be activated in a later version.");
    }
    
    protected static void msItemBuy(int choice){
        Main.output.append("\n");
        switch(choice){
            case 1: //Staff
                if(stats.myGP[0] >= Inv.weaponNum.get(5)[2]){
                    Main.output.append("You purchase a " + Inv.weaponName.get(5) + "." + "\n");
                    stats.myGP[0] -= Inv.weaponNum.get(5)[2];
                    Inv.weaponNum.get(5)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.weaponName.get(5) + ": You can't afford this." + "\n");
                } break;
            case 2: //Spell Tome
                if(stats.myGP[0] >= Inv.offhandNum.get(1)[2]){
                    Main.output.append("You purchase a " + Inv.offhandName.get(1) + "." + "\n");
                    stats.myGP[0] -= Inv.offhandNum.get(1)[2];
                    Inv.offhandNum.get(1)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.offhandName.get(1) + ": You can't afford this." + "\n");
                } break;
            case 3: //Simple Robe
                if(stats.myGP[0] >= Inv.armorNum.get(6)[2]){
                    Main.output.append("You purchase a " + Inv.armorName.get(6) + "." + "\n");
                    stats.myGP[0] -= Inv.armorNum.get(6)[2];
                    Inv.armorNum.get(6)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.armorName.get(6) + ": You can't afford this." + "\n");
                } break;
            case 4: //Blank Scroll
                if(stats.myGP[0] >= Inv.generalNum.get(19)[2]){
                    Main.output.append("You purchase a " + Inv.generalName.get(19) + "." + "\n");
                    stats.myGP[0] -= Inv.generalNum.get(19)[2];
                    Inv.generalNum.get(19)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.generalName.get(19) + ": You can't afford this." + "\n");
                } break;
        }
    }
    
    //APOTHECARY
    
    protected static void apothecaryMenu(){
        Main.output.setText("You step inside the Apothecary's shop and are greeted by the fragrances of exotic herbs." + "\n");
        Main.output.append("The apothecary steps out of a back room, wiping her hands on a rag." + "\n");
        Main.output.append("\"Hi there! I offer only quality potions and elixers! Fresh out of Love Potions though, sorry.\"" + "\n");
        Main.output.append("\"I also sell equipment and reagents for home-brewing, or I can brew special potions for you too!\"" + "\n");
        Main.screen = "apocMenu";
        Main.btn1.setText("Buy Potion");
        Main.btn2.setText("Buy Items");
        Main.btn3.setText("Crafting");
        Main.btn4.setText("Leave");
        Main.btn5.setText(" ");
        Main.btn6.setText(" ");
    }
    
    protected static void apocBuyPotionMenu(){
        Main.output.setText("\"I've got the best potions in town for sale! They work every time!\"" + "\n");
        Main.output.append("Weak Healing Potion: 25 GP" + "\n");
        Main.output.append("Weak Mana Potion: 25 GP" + "\n");
        Main.output.append("Healing Potion: 50 GP" + "\n");
        Main.output.append("Mana Potion: 50 GP" + "\n");
        Main.output.append("Potent Healing Potion: 100 GP" + "\n");
        Main.output.append("Potent Mana Potion: 100 GP" + "\n");
        Main.screen = "apocBuyPotionMenu";
        Main.btn1.setText("Weak Healing Potion");
        Main.btn2.setText("Weak Mana Potion");
        Main.btn3.setText("Healing Potion");
        Main.btn4.setText("Mana Potion");
        Main.btn5.setText("More Choices");
        Main.btn6.setText("Nothing");
    }
    
    protected static void apocBuyPotionMenu2(){
        Main.screen = "apocBuyPotionMenu2";
        Main.btn1.setText("Potent Healing Potion");
        Main.btn2.setText("Potent Mana Potion");
        Main.btn3.setText("Less Choices");
        Main.btn4.setText("Nothing");
        Main.btn5.setText(" ");
        Main.btn6.setText(" ");
    }
    
    protected static void apocBuyPotion(int choice){
        Main.output.append("\n");
        switch(choice){
            case 1: //Weak Healing Potion
                if(stats.myGP[0] >= Inv.potionNum.get(0)[2]){
                    Main.output.append("You purchase a " + Inv.potionName.get(0) + "." + "\n");
                    stats.myGP[0] -= Inv.potionNum.get(0)[2];
                    Inv.potionNum.get(0)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.potionName.get(0) + ": You can't afford this." + "\n");
                } break;
            case 2: //Weak Mana Potion
                if(stats.myGP[0] >= Inv.potionNum.get(1)[2]){
                    Main.output.append("You purchase a " + Inv.potionName.get(1) + "." + "\n");
                    stats.myGP[0] -= Inv.potionNum.get(1)[2];
                    Inv.potionNum.get(1)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.potionName.get(1) + ": You can't afford this." + "\n");
                } break;
            case 3: //Healing Potion
                if(stats.myGP[0] >= Inv.potionNum.get(2)[2]){
                    Main.output.append("You purchase a " + Inv.potionName.get(2) + "." + "\n");
                    stats.myGP[0] -= Inv.potionNum.get(2)[2];
                    Inv.potionNum.get(2)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.potionName.get(2) + ": You can't afford this." + "\n");
                } break;
            case 4: //Mana Potion
                if(stats.myGP[0] >= Inv.potionNum.get(3)[2]){
                    Main.output.append("You purchase a " + Inv.potionName.get(3) + "." + "\n");
                    stats.myGP[0] -= Inv.potionNum.get(3)[2];
                    Inv.potionNum.get(3)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.potionName.get(3) + ": You can't afford this." + "\n");
                } break;
            case 5: //Potent Healing Potion
                if(stats.myGP[0] >= Inv.potionNum.get(4)[2]){
                    Main.output.append("You purchase a " + Inv.potionName.get(4) + "." + "\n");
                    stats.myGP[0] -= Inv.potionNum.get(4)[2];
                    Inv.potionNum.get(4)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.potionName.get(4) + ": You can't afford this." + "\n");
                } break;
            case 6: //Potent Mana Potion
                if(stats.myGP[0] >= Inv.potionNum.get(5)[2]){
                    Main.output.append("You purchase a " + Inv.potionName.get(5) + "." + "\n");
                    stats.myGP[0] -= Inv.potionNum.get(5)[2];
                    Inv.potionNum.get(5)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.potionName.get(5) + ": You can't afford this." + "\n");
                } break;
        }
    }
    
    protected static void apocBuyItemMenu(){
        Main.output.setText("\"Looking to do some brewing of your own? I've got just the thing!\"" + "\n");
        Main.output.append("\n");
        Main.output.append("Alchemy Kit: 200 GP" + "\n");
        Main.output.append("Empty Bottle: 10 GP" + "\n");
        Main.output.append("Empty Bottle x5: 50 GP" + "\n");
        Main.screen = "apocBuyItemMenu";
        Main.btn1.setText("Alchemy Kit");
        Main.btn2.setText("Empty Bottle");
        Main.btn3.setText("Empty Bottle x5");
        Main.btn4.setText("Nothing");
        Main.btn5.setText(" ");
        Main.btn6.setText(" ");
    }
    
    protected static void apocBuyItem(int choice){
        switch(choice){
            case 1: //Alchemy Kit
                if(stats.myGP[0] >= Inv.toolNum.get(4)[2]){
                    Main.output.append("You purchase an " + Inv.toolName.get(4) + "." + "\n");
                    stats.myGP[0] -= Inv.toolNum.get(4)[2];
                    Inv.toolNum.get(4)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.toolName.get(4) + ": You can't afford this." + "\n");
                } break;
            case 2: //Empty Bottle
                if(stats.myGP[0] >= Inv.toolNum.get(5)[2]){
                    Main.output.append("You purchase an " + Inv.toolName.get(5) + "." + "\n");
                    stats.myGP[0] -= Inv.toolNum.get(5)[2];
                    Inv.toolNum.get(5)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.toolName.get(5) + ": You can't afford this." + "\n");
                } break;
            case 3: //Empty Bottle
                if(stats.myGP[0] >= (Inv.toolNum.get(5)[2] * 5)){
                    Main.output.append("You purchase " + Inv.toolName.get(5) + " x5." + "\n");
                    stats.myGP[0] -= (Inv.toolNum.get(5)[2] * 5);
                    Inv.toolNum.get(5)[0] += 5;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.toolName.get(5) + " x5: You can't afford this." + "\n");
                } break;
        }
    }
    
    //SELL METHODS
    
    protected static void sellOne(){
        int i = Inv.getIndex(sellSelect.getSelectedValue().toString(), array);
        switch(array){
            case 1: //Armor
                stats.myGP[0] += Inv.armorNum.get(i)[1];
                stats.myGP[1] += Inv.armorNum.get(i)[1];
                Inv.armorNum.get(i)[0] -= 1;
                Main.output.append("\n");
                Main.output.append("Sold 1 " + Inv.armorName.get(i) + " for " + Inv.armorNum.get(i)[1] + " GP." + "\n");
                break;
            case 2: //Weapons
                stats.myGP[0] += Inv.weaponNum.get(i)[1];
                stats.myGP[1] += Inv.weaponNum.get(i)[1];
                Inv.weaponNum.get(i)[0] -= 1;
                Main.output.append("\n");
                Main.output.append("Sold 1 " + Inv.weaponName.get(i) + " for " + Inv.weaponNum.get(i)[1] + " GP." + "\n");
                break;
            case 3: //Offhand
                stats.myGP[0] += Inv.offhandNum.get(i)[1];
                stats.myGP[1] += Inv.offhandNum.get(i)[1];
                Inv.offhandNum.get(i)[0] -= 1;
                Main.output.append("\n");
                Main.output.append("Sold 1 " + Inv.offhandName.get(i) + " for " + Inv.offhandNum.get(i)[1] + " GP." + "\n");
                break;
            case 4: //Tools
                stats.myGP[0] += Inv.toolNum.get(i)[1];
                stats.myGP[1] += Inv.toolNum.get(i)[1];
                Inv.toolNum.get(i)[0] -= 1;
                Main.output.append("\n");
                Main.output.append("Sold 1 " + Inv.toolName.get(i) + " for " + Inv.toolNum.get(i)[1] + " GP." + "\n");
                break;
            case 5: //Potions
                stats.myGP[0] += Inv.potionNum.get(i)[1];
                stats.myGP[1] += Inv.potionNum.get(i)[1];
                Inv.potionNum.get(i)[0] -= 1;
                Main.output.append("\n");
                Main.output.append("Sold 1 " + Inv.potionName.get(i) + " for " + Inv.potionNum.get(i)[1] + " GP." + "\n");
                break;
            case 6: //Food
                stats.myGP[0] += Inv.foodNum.get(i)[1];
                stats.myGP[1] += Inv.foodNum.get(i)[1];
                Inv.foodNum.get(i)[0] -= 1;
                Main.output.append("\n");
                Main.output.append("Sold 1 " + Inv.foodName.get(i) + " for " + Inv.foodNum.get(i)[1] + " GP." + "\n");
                break;
            case 7: //General
                stats.myGP[0] += Inv.generalNum.get(i)[1];
                stats.myGP[1] += Inv.generalNum.get(i)[1];
                Inv.generalNum.get(i)[0] -= 1;
                Main.output.append("\n");
                Main.output.append("Sold 1 " + Inv.generalName.get(i) + " for " + Inv.generalNum.get(i)[1] + " GP." + "\n");
                break;
        }
        Shops.sellWindow.setVisible(false);
        Main.updateLabels();
    }
    
    protected static void sellFive(){
        int i = Inv.getIndex(sellSelect.getSelectedValue().toString(), array);
        switch(array){
            case 1: //Armor
                stats.myGP[0] += (Inv.armorNum.get(i)[1] * 5);
                stats.myGP[1] += (Inv.armorNum.get(i)[1] * 5);
                Inv.armorNum.get(i)[0] -= 5;
                Main.output.append("\n");
                Main.output.append("Sold 5 " + Inv.armorName.get(i) + " for " + (Inv.armorNum.get(i)[1] * 5) + " GP." + "\n");
                break;
            case 2: //Weapons
                stats.myGP[0] += (Inv.weaponNum.get(i)[1] * 5);
                stats.myGP[1] += (Inv.weaponNum.get(i)[1] * 5);
                Inv.weaponNum.get(i)[0] -= 5;
                Main.output.append("\n");
                Main.output.append("Sold 5 " + Inv.weaponName.get(i) + " for " + (Inv.weaponNum.get(i)[1] * 5) + " GP." + "\n");
                break;
            case 3: //Offhand
                stats.myGP[0] += (Inv.offhandNum.get(i)[1] * 5);
                stats.myGP[1] += (Inv.offhandNum.get(i)[1] * 5);
                Inv.offhandNum.get(i)[0] -= 5;
                Main.output.append("\n");
                Main.output.append("Sold 5 " + Inv.offhandName.get(i) + " for " + (Inv.offhandNum.get(i)[1] * 5) + " GP." + "\n");
                break;
            case 4: //Tools
                stats.myGP[0] += (Inv.toolNum.get(i)[1] * 5);
                stats.myGP[1] += (Inv.toolNum.get(i)[1] * 5);
                Inv.toolNum.get(i)[0] -= 5;
                Main.output.append("\n");
                Main.output.append("Sold 5 " + Inv.toolName.get(i) + " for " + (Inv.toolNum.get(i)[1] * 5) + " GP." + "\n");
                break;
            case 5: //Potions
                stats.myGP[0] += (Inv.potionNum.get(i)[1] * 5);
                stats.myGP[1] += (Inv.potionNum.get(i)[1] * 5);
                Inv.potionNum.get(i)[0] -= 5;
                Main.output.append("\n");
                Main.output.append("Sold 5 " + Inv.potionName.get(i) + " for " + (Inv.potionNum.get(i)[1] * 5) + " GP." + "\n");
                break;
            case 6: //Food
                stats.myGP[0] += (Inv.foodNum.get(i)[1] * 5);
                stats.myGP[1] += (Inv.foodNum.get(i)[1] * 5);
                Inv.foodNum.get(i)[0] -= 5;
                Main.output.append("\n");
                Main.output.append("Sold 5 " + Inv.foodName.get(i) + " for " + (Inv.foodNum.get(i)[1] * 5) + " GP." + "\n");
                break;
            case 7: //General
                stats.myGP[0] += (Inv.generalNum.get(i)[1] * 5);
                stats.myGP[1] += (Inv.generalNum.get(i)[1] * 5);
                Inv.generalNum.get(i)[0] -= 5;
                Main.output.append("\n");
                Main.output.append("Sold 5 " + Inv.generalName.get(i) + " for " + (Inv.generalNum.get(i)[1] * 5) + " GP." + "\n");
                break;
        }
        Shops.sellWindow.setVisible(false);
        Main.updateLabels();
    }
    
    protected static void sellTen(){
        int i = Inv.getIndex(sellSelect.getSelectedValue().toString(), array);
        switch(array){
            case 1: //Armor
                stats.myGP[0] += (Inv.armorNum.get(i)[1] * 10);
                stats.myGP[1] += (Inv.armorNum.get(i)[1] * 10);
                Inv.armorNum.get(i)[0] -= 10;
                Main.output.append("\n");
                Main.output.append("Sold 10 " + Inv.armorName.get(i) + " for " + (Inv.armorNum.get(i)[1] * 10) + " GP." + "\n");
                break;
            case 2: //Weapons
                stats.myGP[0] += (Inv.weaponNum.get(i)[1] * 10);
                stats.myGP[1] += (Inv.weaponNum.get(i)[1] * 10);
                Inv.weaponNum.get(i)[0] -= 10;
                Main.output.append("\n");
                Main.output.append("Sold 10 " + Inv.weaponName.get(i) + " for " + (Inv.weaponNum.get(i)[1] * 10) + " GP." + "\n");
                break;
            case 3: //Offhand
                stats.myGP[0] += (Inv.offhandNum.get(i)[1] * 10);
                stats.myGP[1] += (Inv.offhandNum.get(i)[1] * 10);
                Inv.offhandNum.get(i)[0] -= 10;
                Main.output.append("\n");
                Main.output.append("Sold 10 " + Inv.offhandName.get(i) + " for " + (Inv.offhandNum.get(i)[1] * 10) + " GP." + "\n");
                break;
            case 4: //Tools
                stats.myGP[0] += (Inv.toolNum.get(i)[1] * 10);
                stats.myGP[1] += (Inv.toolNum.get(i)[1] * 10);
                Inv.toolNum.get(i)[0] -= 10;
                Main.output.append("\n");
                Main.output.append("Sold 10 " + Inv.toolName.get(i) + " for " + (Inv.toolNum.get(i)[1] * 10) + " GP." + "\n");
                break;
            case 5: //Potions
                stats.myGP[0] += (Inv.potionNum.get(i)[1] * 10);
                stats.myGP[1] += (Inv.potionNum.get(i)[1] * 10);
                Inv.potionNum.get(i)[0] -= 10;
                Main.output.append("\n");
                Main.output.append("Sold 10 " + Inv.potionName.get(i) + " for " + (Inv.potionNum.get(i)[1] * 10) + " GP." + "\n");
                break;
            case 6: //Food
                stats.myGP[0] += (Inv.foodNum.get(i)[1] * 10);
                stats.myGP[1] += (Inv.foodNum.get(i)[1] * 10);
                Inv.foodNum.get(i)[0] -= 10;
                Main.output.append("\n");
                Main.output.append("Sold 10 " + Inv.foodName.get(i) + " for " + (Inv.foodNum.get(i)[1] * 10) + " GP." + "\n");
                break;
            case 7: //General
                stats.myGP[0] += (Inv.generalNum.get(i)[1] * 10);
                stats.myGP[1] += (Inv.generalNum.get(i)[1] * 10);
                Inv.generalNum.get(i)[0] -= 10;
                Main.output.append("\n");
                Main.output.append("Sold 10 " + Inv.generalName.get(i) + " for " + (Inv.generalNum.get(i)[1] * 10) + " GP." + "\n");
                break;
        }
        Shops.sellWindow.setVisible(false);
        Main.updateLabels();
    }
    
    protected static void sellAll(){
        int i = Inv.getIndex(sellSelect.getSelectedValue().toString(), array);
        int num = 0;
        switch(array){
            case 1: //Armor
                num = Inv.armorNum.get(i)[0];
                if(equippedItem == true && i == equippedIndex){
                    num --;
                }
                stats.myGP[0] += (Inv.armorNum.get(i)[1] * num);
                stats.myGP[1] += (Inv.armorNum.get(i)[1] * num);
                Inv.armorNum.get(i)[0] -= num;
                Main.output.append("\n");
                Main.output.append("Sold " + num + " " + Inv.armorName.get(i) + " for " + (Inv.armorNum.get(i)[1] * num) + " GP." + "\n");
                break;
            case 2: //Weapons
                num = Inv.weaponNum.get(i)[0];
                if(equippedItem == true && i == equippedIndex){
                    num --;
                }
                stats.myGP[0] += (Inv.weaponNum.get(i)[1] * num);
                stats.myGP[1] += (Inv.weaponNum.get(i)[1] * num);
                Inv.weaponNum.get(i)[0] -= num;
                Main.output.append("\n");
                Main.output.append("Sold " + num + " " + Inv.weaponName.get(i) + " for " + (Inv.weaponNum.get(i)[1] * num) + " GP." + "\n");
                break;
            case 3: //Offhand
                num = Inv.offhandNum.get(i)[0];
                if(equippedItem == true && i == equippedIndex){
                    num --;
                }
                stats.myGP[0] += (Inv.offhandNum.get(i)[1] * num);
                stats.myGP[1] += (Inv.offhandNum.get(i)[1] * num);
                Inv.offhandNum.get(i)[0] -= num;
                Main.output.append("\n");
                Main.output.append("Sold " + num + " " + Inv.offhandName.get(i) + " for " + (Inv.offhandNum.get(i)[1] * num) + " GP." + "\n");
                break;
            case 4: //Tools
                num = Inv.toolNum.get(i)[0];
                stats.myGP[0] += (Inv.toolNum.get(i)[1] * num);
                stats.myGP[1] += (Inv.toolNum.get(i)[1] * num);
                Inv.toolNum.get(i)[0] -= num;
                Main.output.append("\n");
                Main.output.append("Sold " + num + " " + Inv.toolName.get(i) + " for " + (Inv.toolNum.get(i)[1] * num) + " GP." + "\n");
                break;
            case 5: //Potions
                num = Inv.potionNum.get(i)[0];
                stats.myGP[0] += (Inv.potionNum.get(i)[1] * num);
                stats.myGP[1] += (Inv.potionNum.get(i)[1] * num);
                Inv.potionNum.get(i)[0] -= num;
                Main.output.append("\n");
                Main.output.append("Sold " + num + " " + Inv.potionName.get(i) + " for " + (Inv.potionNum.get(i)[1] * num) + " GP." + "\n");
                break;
            case 6: //Food
                num = Inv.foodNum.get(i)[0];
                stats.myGP[0] += (Inv.foodNum.get(i)[1] * num);
                stats.myGP[1] += (Inv.foodNum.get(i)[1] * num);
                Inv.foodNum.get(i)[0] -= num;
                Main.output.append("\n");
                Main.output.append("Sold " + num + " " + Inv.foodName.get(i) + " for " + (Inv.foodNum.get(i)[1] * num) + " GP." + "\n");
                break;
            case 7: //General
                num = Inv.generalNum.get(i)[0];
                stats.myGP[0] += (Inv.generalNum.get(i)[1] * num);
                stats.myGP[1] += (Inv.generalNum.get(i)[1] * num);
                Inv.generalNum.get(i)[0] -= num;
                Main.output.append("\n");
                Main.output.append("Sold " + num + " " + Inv.generalName.get(i) + " for " + (Inv.generalNum.get(i)[1] * num) + " GP." + "\n");
                break;
        }
        Shops.sellWindow.setVisible(false);
        Main.updateLabels();
    }
    
}
