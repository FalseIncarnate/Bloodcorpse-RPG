
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

public class Handler implements ActionListener {
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == Main.btn1){
            Main.option = 1;
            screenCheck();
        } else if(e.getSource() == Main.btn2){
            Main.option = 2;
            screenCheck();
        } else if(e.getSource() == Main.btn3){
            Main.option = 3;
            screenCheck();
        } else if(e.getSource() == Main.btn4){
            Main.option = 4;
            screenCheck();
        } else if(e.getSource() == Main.btn5){
            Main.option = 5;
            screenCheck();
        } else if(e.getSource() == Main.btn6){
            Main.option = 6;
            screenCheck();
        } else if(e.getSource() == Inv.equipBtn){
            Inv.updateEquipped();
        } else if(e.getSource() == Shops.sellOneBtn){
            Shops.sellOne();
        } else if(e.getSource() == Shops.sellFiveBtn){
            Shops.sellFive();
        } else if(e.getSource() == Shops.sellTenBtn){
            Shops.sellTen();
        } else if(e.getSource() == Shops.sellAllBtn){
            Shops.sellAll();
        }
    }
    
    void screenCheck(){
        if(Main.screen.equals("mainMenu")){
            mainMenuOptions();
        }else if(Main.screen.equals("campMenu")){
            campMenuOptions();
        }else if(Main.screen.equals("exploreMenu")){
            exploreMenuOptions();
        }else if(Main.screen.equals("exploreMenu2")){
            exploreMenu2Options();
        }else if(Main.screen.equals("restMenu")){
            restMenuOptions();
        }else if(Main.screen.equals("statsMenu")){
            statsMenuOptions();
        }else if(Main.screen.equals("townMenu")){
            townMenuOptions();
        }else if(Main.screen.equals("shopsMenu")){
            shopsMenuOptions();
        }else if(Main.screen.equals("sewerMenu")){
            sewerMenuOptions();
        }else if(Main.screen.equals("muggerMenu")){
            muggerMenuOptions();
        }else if(Main.screen.equals("muggerMenu2")){
            muggerMenu2Options();
        }else if(Main.screen.equals("genStoreMenu")){
            genStoreMenuOptions();
        }else if(Main.screen.equals("genBuyMenu")){
            genBuyMenuOptions();
        }else if(Main.screen.equals("genSellMenu")){
            genSellMenuOptions();
        }else if(Main.screen.equals("genSellMenu2")){
            genSellMenu2Options();
        }else if(Main.screen.equals("bsMenu")){
            bsMenuOptions();
        }else if(Main.screen.equals("bsArmorBuyMenu")){
            bsArmorBuyMenuOptions();
        }else if(Main.screen.equals("bsWeaponBuyMenu")){
            bsWeaponBuyMenuOptions();
        }else if(Main.screen.equals("bsCraftingMenu")){
            bsCraftingMenuOptions();
        }else if(Main.screen.equals("msMenu")){
            msMenuOptions();
        }else if(Main.screen.equals("msItemBuyMenu")){
            msItemBuyMenuOptions();
        }else if(Main.screen.equals("msSpellBuyMenu")){
            msSpellBuyMenuOptions();
        }else if(Main.screen.equals("msCraftingMenu")){
            msCraftingMenuOptions();
        }else if(Main.screen.equals("apocMenu")){
            apocMenuOptions();
        }else if(Main.screen.equals("apocBuyPotionMenu")){
            apocBuyPotionMenuOptions();
        }else if(Main.screen.equals("apocBuyPotionMenu2")){
            apocBuyPotionMenu2Options();
        }else if(Main.screen.equals("apocBuyItemMenu")){
            apocBuyItemMenuOptions();
        }else if(Main.screen.equals("apocCraftingMenu")){
            apocCraftingMenuOptions();
        }else if(Main.screen.equals("chapelMenu")){
            chapelMenuOptions();
        }else if(Main.screen.equals("tavernMenu")){
            tavernMenuOptions();
        }else if(Main.screen.equals("orderDrinkMenu")){
            orderDrinkMenuOptions();
        }else if(Main.screen.equals("diceGameMenu")){
            diceGameMenuOptions();
        }else if(Main.screen.equals("combatMenu")){
            combatMenuOptions();
        }else if(Main.screen.equals("offSpellMenu")){
            offSpellMenuOptions();
        }else if(Main.screen.equals("defSpellMenu")){
            defSpellMenuOptions();
        }else if(Main.screen.equals("postCombat")){
            postCombatOptions();
        }else if(Main.screen.equals("invMenu")){
            invMenuOptions();
        }else if(Main.screen.equals("invMenu2")){
            invMenu2Options();
        }else if(Main.screen.equals("invArmor")){
            invArmorOptions();
        }else if(Main.screen.equals("invWeapon")){
            invWeaponOptions();
        }else if(Main.screen.equals("invOffhand")){
            invOffhandOptions();
        }else if(Main.screen.equals("invTool")){
            invToolOptions();
        }else if(Main.screen.equals("invPotion")){
            invPotionOptions();
        }else if(Main.screen.equals("invFood")){
            invFoodOptions();
        }else if(Main.screen.equals("invGeneral")){
            invGeneralOptions();
        }else if(Main.screen.equals("dataMenu")){
            dataMenuOptions();
        }else if(Main.screen.equals("levelUpMenu")){
            levelUpMenuOptions();
        }else if(Main.screen.equals("trollMenu")){
            trollMenuOptions();
        }else if(Main.screen.equals("postCombatTrollMenu")){
            postCombatTrollMenuOptions();
        }else if(Main.screen.equals("riverMenu")){
            riverMenuOptions();
        } else if(Main.screen.equals("graveyardMenu")){
            graveyardMenuOptions();
        } else if(Main.screen.equals("graveMenu")){
            graveMenuOptions();
        } else if(Main.screen.equals("graveMenu2")){
            graveMenu2Options();
        }
    }
    
    void mainMenuOptions(){
        if(Main.option == 1){
            Main.newGame();
        }else if(Main.option == 2){
            Main.loadGame();
        }
    }
    
    //CAMP MENUS
    
    void campMenuOptions(){
        switch(Main.option){
            case 1: Explore.exploreMenu(); break;
            case 2: Inv.invMenu(); break;
            case 3: Camp.restMenu(); break;
            case 4: Camp.statsMenu(); break;
            case 5: Save.dataMenu(); break;
            case 6: Camp.debugAction(); break;
        }
    }
    
    
    void restMenuOptions(){
        switch(Main.option){
            case 1: Camp.heal(); break;
            case 2: Camp.meditate(); break;
            case 3: Camp.replenish(); break;
            case 4: Camp.campMenu(); break;
        }
    }
    
    void statsMenuOptions(){
        switch(Main.option){
            case 1: stats.showStats(); break;
            case 2: stats.showLegacy(); break;
            case 3: stats.showActiveBuffs(); break;
            case 4: stats.showActiveEffects(); break;
            case 5: stats.showProgress(); break;
            case 6: Camp.campMenu(); break;
        }
    }
    
    //EXPLORE MENUS
    
    void exploreMenuOptions(){
        switch(Main.option){
            case 1: Camp.campMenu(); break;
            case 2: Town.eventProcChance(); break;
            case 3: Forest.eventSelect(); break;
            case 4: River.riverMenu(); break;
            case 5: Mountain.eventSelect(); break;
            case 6: Graveyard.graveyardMenu(); break;
        }
    }
    
    void exploreMenu2Options(){
        switch(Main.option){
            case 1: Explore.exploreMenu(); break;
            case 2: JOptionPane.showMessageDialog(Main.window, "This zone is currently inactive. It will be activated in a later version."); break;
            case 3: JOptionPane.showMessageDialog(Main.window, "This zone is currently inactive. It will be activated in a later version."); break;
            case 4: JOptionPane.showMessageDialog(Main.window, "This zone is currently inactive. It will be activated in a later version."); break;
            case 5: JOptionPane.showMessageDialog(Main.window, "This zone is currently inactive. It will be activated in a later version."); break;
            case 6: JOptionPane.showMessageDialog(Main.window, "This zone is currently inactive. It will be activated in a later version."); break;
        }
    }
    
    void riverMenuOptions(){
        switch(Main.option){
            case 1: River.trollToll(); break;
            case 2: River.eventSelect(); break;
            case 3: Explore.exploreMenu(); break;
            case 4: Camp.campMenu(); break;
        }
    }
    
    void graveyardMenuOptions(){
        switch(Main.option){
            case 1: Crypt.cryptEntrance(); break;
            case 2: Graveyard.eventSelect(); break;
            case 3: Explore.exploreMenu(); break;
            case 4: Camp.campMenu(); break;
        }
    }
    
    void graveMenuOptions(){
        switch(Main.option){
            case 1: Graveyard.freshGraveResult(1); break;
            case 2: Graveyard.freshGraveResult(0); break;
        }
    }
    
    void graveMenu2Options(){
        if(stats.cryptFound == true){
            switch(Main.option){
                case 1: Graveyard.graveyardMenu(); break;
                case 2: Explore.exploreMenu(); break;
                case 3: Camp.campMenu(); break;
            }
        }else{
            switch(Main.option){
                case 1: Explore.exploreMenu(); break;
                case 2: Camp.campMenu(); break;
            }
        }
    }
    
    //TROLL TOLL MENUS
    
    void trollMenuOptions(){
        switch(Main.option){
            case 1: River.trollToll(1); break;
            case 2: River.trollToll(2); break;
            case 3: River.trollToll(3); break;
            case 4: Explore.exploreMenu(); break;
        }
    }
    
    void trollMenu2Options(){
        switch(Main.option){
            case 1: Explore.exploreMenu2(); break;
            case 2: Explore.exploreMenu(); break;
        }
    }
    
    //TOWN MENUS
    
    void townMenuOptions(){
        switch(Main.option){
            case 1: Shops.shopsMenu(); break;
            case 2: Town.chapelMenu(); break;
            case 3: Town.tavernMenu(); break;
            case 4: Sewers.sewerMenu(); break;
            case 5: Camp.campMenu(); break;
        }
    }
    
    void shopsMenuOptions(){
        switch(Main.option){
            case 1: Shops.genStoreMenu(); break;
            case 2: Shops.blacksmithMenu(); break;
            case 3: Shops.magicStoreMenu(); break;
            case 4: Shops.apothecaryMenu(); break;
            case 5: Town.townMenu(); break;
            case 6: Camp.campMenu(); break;
        }
    }
    
    void sewerMenuOptions(){
        switch(Main.option){
            case 1: Sewers.eventSelect(); break;
            case 2: Town.eventProcChance();
        }
    }
    
    //MUGGER MENUS
    
    void muggerMenuOptions(){
        switch(Main.option){
            case 1: Town.muggerEvent(1); break;
            case 2: Town.muggerEvent(2); break;
            case 3: Town.muggerEvent(3); break;
        }
    }
    
    void muggerMenu2Options(){
        switch(Main.option){
            case 1: Town.townMenu(); break;
        }
    }
    
    //GENERAL STORE MENUS
    
    void genStoreMenuOptions(){
        switch(Main.option){
            case 1: Shops.genBuyMenu(); break;
            case 2: Shops.genSellMenu(); break;
            case 3: Town.eventProcChance(); break;
        }
    }
    
    void genBuyMenuOptions(){
        switch(Main.option){
            case 1: Shops.genBuy(1); break;
            case 2: Shops.genBuy(2); break;
            case 3: Shops.genBuy(3); break;
            case 4: Shops.genBuy(4); break;
            case 5: Shops.genBuy(5); break;
            case 6: Shops.genStoreMenu(); break;
        }
    }
    
    void genSellMenuOptions(){
        switch(Main.option){
            case 1: Shops.sell(1); break;
            case 2: Shops.sell(2); break;
            case 3: Shops.sell(3); break;
            case 4: Shops.sell(4); break;
            case 5: Shops.genSellMenu2(); break;
            case 6: Shops.genStoreMenu(); break;
        }
    }
    
    void genSellMenu2Options(){
        switch(Main.option){
            case 1: Shops.sell(5); break;
            case 2: Shops.sell(6); break;
            case 3: Shops.sell(7); break;
            case 4: Shops.genSellMenu(); break;
            case 5: Shops.genStoreMenu(); break;
        }
    }
    
    //BLACKSMITH MENUS
    
    void bsMenuOptions(){
        switch(Main.option){
            case 1: Shops.bsArmorBuyMenu(); break;
            case 2: Shops.bsWeaponBuyMenu(); break;
            case 3: Crafting.bsCraftingMenu(); break;
            case 4: Town.eventProcChance(); break;
        }
    }
    
    void bsArmorBuyMenuOptions(){
        switch(Main.option){
            case 1: Shops.bsArmorBuy(1); break;
            case 2: Shops.bsArmorBuy(2); break;
            case 3: Shops.bsArmorBuy(3); break;
            case 4: Shops.bsArmorBuy(4); break;
            case 5: Shops.bsArmorBuy(5); break;
            case 6: Shops.blacksmithMenu(); break;
        }
    }
    
    void bsWeaponBuyMenuOptions(){
        switch(Main.option){
            case 1: Shops.bsWeaponBuy(1); break;
            case 2: Shops.bsWeaponBuy(2); break;
            case 3: Shops.bsWeaponBuy(3); break;
            case 4: Shops.bsWeaponBuy(4); break;
            case 5: Shops.bsWeaponBuy(5); break;
            case 6: Shops.blacksmithMenu(); break;
        }
    }
    
    void bsCraftingMenuOptions(){
        switch(Main.option){
            case 1: Crafting.materialCheck(Crafting.wolfHideArmorRecipe); break;
            case 2: Crafting.materialCheck(Crafting.turtleShieldRecipe); break;
            case 3: Crafting.materialCheck(Crafting.turtleArmorRecipe); break;
            case 4: Crafting.materialCheck(Crafting.trollBiteGauntletRecipe); break;
            case 5: ; break;
            case 6: Shops.blacksmithMenu(); break;
        }
    }
    
    //MAGIC SHOP MENUS
    
    void msMenuOptions(){
        switch(Main.option){
            case 1: Shops.msItemBuyMenu(); break;
            case 2: Shops.msSpellBuyMenu(); break;
            case 3: Crafting.msCraftingMenu(); break;
            case 4: Town.eventProcChance(); break;
        }
    }
    
    void msItemBuyMenuOptions(){
        switch(Main.option){
            case 1: Shops.msItemBuy(1); break;
            case 2: Shops.msItemBuy(2); break;
            case 3: Shops.msItemBuy(3); break;
            case 4: Shops.msItemBuy(4); break;
            case 5: Shops.magicStoreMenu(); break;
        }
    }
    
    void msSpellBuyMenuOptions(){
        
    }
    
    void msCraftingMenuOptions(){
        switch(Main.option){
            case 1: Crafting.materialCheck(Crafting.voodooDollRecipe); break;
            case 2: Crafting.materialCheck(Crafting.dollOfRebirthRecipe); break;
            case 3: ; break;
            case 4: ; break;
            case 5: ; break;
            case 6: Shops.magicStoreMenu(); break;
        }
    }
    
    //APOTHECARY MENUS
    
    void apocMenuOptions(){
        switch(Main.option){
            case 1: Shops.apocBuyPotionMenu(); break;
            case 2: Shops.apocBuyItemMenu(); break;
            case 3: Crafting.apocCraftingMenu(); break;
            case 4: Town.eventProcChance(); break;
        }
    }
    
    void apocBuyPotionMenuOptions(){
        switch(Main.option){
            case 1: Shops.apocBuyPotion(1); break;
            case 2: Shops.apocBuyPotion(2); break;
            case 3: Shops.apocBuyPotion(3); break;
            case 4: Shops.apocBuyPotion(4); break;
            case 5: Shops.apocBuyPotionMenu2(); break;
            case 6: Shops.apothecaryMenu(); break;
        }  
    }
    
    void apocBuyPotionMenu2Options(){
        switch(Main.option){
            case 1: Shops.apocBuyPotion(5); break;
            case 2: Shops.apocBuyPotion(6); break;
            case 3: Shops.apocBuyPotionMenu(); break;
            case 5: Shops.apothecaryMenu(); break;
        }
    }
    
    void apocBuyItemMenuOptions(){
        switch(Main.option){
            case 1: Shops.apocBuyItem(1); break;
            case 2: Shops.apocBuyItem(2); break;
            case 3: Shops.apocBuyItem(3); break;
            case 4: Shops.apothecaryMenu(); break;
        }
    }
    
    void apocCraftingMenuOptions(){
        switch(Main.option){
            case 1: Crafting.materialCheck(Crafting.naturesFuryRecipe); break;
            case 2: Crafting.materialCheck(Crafting.lingeringHealthPotionRecipe); break;
            case 3: Crafting.materialCheck(Crafting.lingeringManaPotionRecipe); break;
            case 4: ; break;
            case 5: ; break;
            case 6: Shops.apothecaryMenu(); break;
        }
    }
    
    //CHAPEL + TAVERN MENUS
    
    void chapelMenuOptions(){
        switch(Main.option){
            case 1: Town.chapelPray(); break;
            case 2: Town.chapelCure(); break;
            case 3: Town.eventProcChance(); break;
        }
    }
    
    void tavernMenuOptions(){
        switch(Main.option){
            case 1: Town.orderDrinkMenu(); break;
            case 2: Town.orderRound(); break;
            case 3: Town.diceGameMenu(); break;
            case 4: Town.eventProcChance(); break; 
        }
    }
    
    void orderDrinkMenuOptions(){
        switch(Main.option){
            case 1: Town.orderDrink(1); break;
            case 2: Town.orderDrink(2); break;
            case 3: Town.orderDrink(3); break;
            case 4: Town.orderDrink(4); break;
            case 5: Town.orderRound(); break;
            case 6: Town.tavernMenu(); break;
        }
    }
    
    void diceGameMenuOptions(){
        switch(Main.option){
            case 1: Town.playDiceGame(10); break;
            case 2: Town.playDiceGame(50); break;
            case 3: Town.playDiceGame(100); break;
            case 4: Town.tavernMenu(); break;
        }
    }
    
    //COMBAT MENUS
    
    void combatMenuOptions(){
        switch(Main.option){
            case 1: Combat.myAttack(); break;
            case 2: Combat.myBlock(); break;
            case 3: Combat.runAway(); break;
            case 4: Combat.offSpellMenu(); break;
            case 5: Combat.defSpellMenu(); break;
            case 6: Combat.useItem(); break;
        }
    }
    
    void offSpellMenuOptions(){
        switch(Main.option){
            case 1: //Fireball
                Combat.castSpell(1, 1);
                break;
            case 2: //Magic Missile
                Combat.castSpell(1, 2);
                break;
            case 3: //No Spell
                Combat.combatMenu();
                break;
        }
    }
    
    void defSpellMenuOptions(){
        switch(Main.option){
            case 1: //Mage Armor
                Combat.castSpell(2, 1);
                break;
            case 2: //Heal
                Combat.castSpell(2, 2);
                break;
            case 3: //No Spell
                Combat.combatMenu();
                break;
        }
    }
    
    void postCombatOptions(){
        switch(Main.option){
            case 1:
                if(Main.zone == "Forest"){
                    Explore.exploreMenu();
                }else if(Main.zone == "River"){
                    Explore.exploreMenu();
                }else if(Main.zone == "Mountain"){
                    Explore.exploreMenu();
                }else if(Main.zone == "Graveyard"){
                    Explore.exploreMenu();
                }else if(Main.zone == "Town"){
                    Town.townMenu();
                }else if(Main.zone == "Sewers"){
                    Sewers.sewerMenu();
                }else if(Main.zone == "Port"){
                    Port.eventProcChance();
                }else if(Main.zone == "Plains"){
                    Explore.exploreMenu2();
                }else if(Main.zone == "Swamp"){
                    Explore.exploreMenu2();
                }else if(Main.zone == "Desert"){
                    Explore.exploreMenu2();
                }else if(Main.zone == "Dark Forest"){
                    Explore.exploreMenu2();
                }else{
                    JOptionPane.showMessageDialog(Main.window, "== BUG! =="
                            + "\n" + "Post Combat Option 1"
                            + "\n" + "Zone: " + Main.zone +""
                            + "\n" + "REPORT ME!");
                    Camp.campMenu();
                }
                break;
            case 2: Camp.campMenu(); break;
        }
    }
    
    void postCombatTrollMenuOptions(){
        switch(Main.option){
            case 1: Explore.exploreMenu2(); break;
            case 2: Explore.exploreMenu(); break;
            case 3: Camp.campMenu(); break;
        }
    }
    
    //INVENTORY MENUS
    
    void invMenuOptions(){
        switch(Main.option){
            case 1: Inv.invArmor(); break;
            case 2: Inv.invWeapon(); break;
            case 3: Inv.invOffhand(); break;
            case 4: Inv.invTool(); break;
            case 5: Inv.invMenu2(); break;
            case 6: Camp.campMenu(); break;
        }
    }
    
    void invMenu2Options(){
        switch(Main.option){
            case 1: Inv.invPotion(); break;
            case 2: Inv.invFood(); break;
            case 3: Inv.invGeneral(); break;
            case 4: Crafting.campCraftingMenu(); break;
            case 5: Inv.invMenu(); break;
            case 6: Camp.campMenu(); break;
        }
    }
    
    void invArmorOptions(){
        switch(Main.option){
            case 1: Inv.equip(); break;
            case 2: Inv.invMenu(); break;
            case 3: Camp.campMenu(); break;
        }
    }
    
    void invWeaponOptions(){
        switch(Main.option){
            case 1: Inv.equip(); break;
            case 2: Inv.invMenu(); break;
            case 3: Camp.campMenu(); break;
        }
    }
    
    void invOffhandOptions(){
        switch(Main.option){
            case 1: Inv.equip(); break;
            case 2: Inv.invMenu(); break;
            case 3: Camp.campMenu(); break;
        }
    }
    
    void invToolOptions(){
        switch(Main.option){
            case 1: Inv.invMenu(); break;
            case 2: Camp.campMenu(); break;
        }
    }
    
    void invPotionOptions(){
        switch(Main.option){
            case 1: Inv.invMenu(); break;
            case 2: Camp.campMenu(); break;
        }
    }
    
    void invFoodOptions(){
        switch(Main.option){
            case 1: Inv.invMenu(); break;
            case 2: Camp.campMenu(); break;
        }
    }
    
    void invGeneralOptions(){
        switch(Main.option){
            case 1: Inv.invMenu(); break;
            case 2: Camp.campMenu(); break;
        }
    }
    
    //DATA MENUS
    
    void dataMenuOptions(){
        switch(Main.option){
            case 1: Save.saveGame(); break;
            case 2: Save.loadGame(); break;
            case 3: Save.newGameCheck(); break;
            case 4: Camp.campMenu(); break;
        }
    }
    
    void levelUpMenuOptions(){
        switch(Main.option){
            case 1: stats.levelUp(1); break;
            case 2: stats.levelUp(2); break;
            case 3: stats.levelUp(3); break;
            case 4: stats.levelUp(4); break;
            case 5: stats.levelUp(5); break;
            case 6: stats.levelUp(6); break;
        }
    }
    
}
