
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

public class Handler2 implements ListSelectionListener {
    
    static int[] armorVal = {0,0,0};
    static int[] damageVal = {0,0,0};
    static int[] spellVal = {0,0,0};
    
    public void valueChanged(ListSelectionEvent e){
        if(e.getSource() == Inv.armorSelect){
            updateEquipWindow(e);
        }else if(e.getSource() == Inv.weaponSelect){
            updateEquipWindow(e);
        }else if(e.getSource() == Inv.offhandSelect){
            updateEquipWindow(e);
        }else if(e.getSource() == Shops.sellSelect){
            updateSellWindow(e);
        }
        
    }
    
    void updateEquipWindow(ListSelectionEvent e){
        armorVal[0] = 0; armorVal[1] = 0; armorVal[2] = 0;
        damageVal[0] = 0; damageVal[1] = 0; damageVal[2] = 0;;
        spellVal[0] = 0; spellVal[1] = 0; spellVal[2] = 0;
        
        if(Inv.armorSelect.isSelectionEmpty() == false){
            if(Inv.armorSelect.getSelectedIndex() == 0){
                armorVal[0] = 0;
                damageVal[0] = 0;
                spellVal[0] = 0;
            }else{
                int i = Inv.getIndex(Inv.armorSelect.getSelectedValue().toString(), 1);
                armorVal[0] = Inv.armorNum.get(i)[3];
                damageVal[0] = Inv.armorNum.get(i)[4];
                spellVal[0] = Inv.armorNum.get(i)[5];
            }
        }
        if(Inv.weaponSelect.isSelectionEmpty() == false){
            if(Inv.weaponSelect.getSelectedIndex() == 0){
                armorVal[1] = 0;
                damageVal[1] = 0;
                spellVal[1] = 0;
            }else{
                int j = Inv.getIndex(Inv.weaponSelect.getSelectedValue().toString(), 2);
                armorVal[1] = Inv.weaponNum.get(j)[3];
                damageVal[1] = Inv.weaponNum.get(j)[4];
                spellVal[1] = Inv.weaponNum.get(j)[5];
            }
        }
        if(Inv.offhandSelect.isSelectionEmpty() == false){
            if(Inv.offhandSelect.getSelectedIndex() == 0){
                armorVal[2] = 0;
                damageVal[2] = 0;
                spellVal[2] = 0;
            }else{
                int k = Inv.getIndex(Inv.offhandSelect.getSelectedValue().toString(), 3);
                armorVal[2] = Inv.offhandNum.get(k)[3];
                damageVal[2] = Inv.offhandNum.get(k)[4];
                spellVal[2] = Inv.offhandNum.get(k)[5];
            }
        }
        Inv.armorDesc.setText("Armor: " + (armorVal[0] + armorVal[1] + armorVal[2]));
        Inv.damageDesc.setText("Damage: " + (damageVal[0] + damageVal[1] + damageVal[2]));
        Inv.spellDesc.setText("Spellpower: " + (spellVal[0] + spellVal[1] + spellVal[2]));
        Inv.equipWindow.pack();
    }
    
    void updateSellWindow(ListSelectionEvent e){
        if(e.getSource() == Shops.sellSelect && Shops.sellSelect.isSelectionEmpty() == false){
                if(Shops.sellSelect.getSelectedValue().toString() == "Nothing"){
                    Shops.itemDesc.setText("You don't have anything to sell.");
                    Shops.itemDesc2.setText("");
                    if(Shops.equippedItem == true){
                        Shops.itemDesc2.setText("Equipped Items cannot be sold.");
                    }
                    Shops.updateButtons(0);
                }else{
                    int i = Inv.getIndex(Shops.sellSelect.getSelectedValue().toString(), Shops.array);
                    switch(Shops.array){
                        case 1:
                            Shops.itemDesc.setText("Value per item: " + Inv.armorNum.get(i)[1] + " GP");
                            Shops.itemDesc2.setText("Number available: " + Inv.armorNum.get(i)[0]);
                            if(Shops.equippedIndex == i){
                                Shops.itemDesc2.setText("Number available: " + (Inv.armorNum.get(i)[0] - 1));
                            }
                            break;
                        case 2:
                            Shops.itemDesc.setText("Value per item: " + Inv.weaponNum.get(i)[1] + " GP");
                            Shops.itemDesc2.setText("Number available: " + Inv.weaponNum.get(i)[0]);
                            if(Shops.equippedIndex == i){
                                Shops.itemDesc2.setText("Number available: " + (Inv.weaponNum.get(i)[0] - 1));
                            }
                            break;
                        case 3:
                            Shops.itemDesc.setText("Value per item: " + Inv.offhandNum.get(i)[1] + " GP");
                            Shops.itemDesc2.setText("Number available: " + Inv.offhandNum.get(i)[0]);
                            if(Shops.equippedIndex == i){
                                Shops.itemDesc2.setText("Number available: " + (Inv.offhandNum.get(i)[0] - 1));
                            }
                            break;
                        case 4:
                            Shops.itemDesc.setText("Value per item: " + Inv.toolNum.get(i)[1] + " GP");
                            Shops.itemDesc2.setText("Number available: " + Inv.toolNum.get(i)[0]);
                            break;
                        case 5:
                            Shops.itemDesc.setText("Value per item: " + Inv.potionNum.get(i)[1] + " GP");
                            Shops.itemDesc2.setText("Number available: " + Inv.potionNum.get(i)[0]);
                            break;
                        case 6:
                            Shops.itemDesc.setText("Value per item: " + Inv.foodNum.get(i)[1] + " GP");
                            Shops.itemDesc2.setText("Number available: " + Inv.foodNum.get(i)[0]);
                            break;
                        case 7:
                            Shops.itemDesc.setText("Value per item: " + Inv.generalNum.get(i)[1] + " GP");
                            Shops.itemDesc2.setText("Number available: " + Inv.generalNum.get(i)[0]);
                            break;
                    }
                    Shops.updateButtons(i);
                }
            }
        }
    
}
