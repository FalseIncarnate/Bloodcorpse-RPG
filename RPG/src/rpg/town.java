package rpg;

/**
 * @author Bloodcorpse
 */
import java.util.*;

public class town {
    
    static String option;
    static int event;
    static inventory inv;
    
    //TOWN METHODS
    
    protected static void townCenter() {
        System.out.println("\n");
        System.out.println("The town seems bustling with activity. You spot numerous places of interest.");
        System.out.println("Where would you like to go?");
        System.out.println(" 1. General Store       2. Chapel");
        System.out.println(" 3. Tavern              4. Blacksmith");
        System.out.println(" 5. Mystic Emporium     6. Return to Camp");
        option = RPG.in.nextLine();
        if (option.equals("1")) {
            generalStore();
        } else if (option.equals("2")) {
            chapel();
        } else if (option.equals("3")) {
            tavern();
        } else if (option.equals("4")) {
            blacksmith();
        } else if (option.equals("5")) {
            magicShop();
        } else if (option.equals("6")) {
            RPG.camp();
        } else {
            townCenter();
        }
    }

    private static void generalStore() {
        System.out.println("\n");
        System.out.println("The shopkeeper greets you as soon as you walk in the door.");
        System.out.println("\"Welcome, welcome! Feel free to browse, everything has a price!\" he babbles excitedly.");
        System.out.println(" 1. Buy     2. Sell");
        System.out.println(" 3. Leave");
        option = RPG.in.nextLine();
        if (option.equals("1")) {
            genBuy();
        } else if (option.equals("2")) {
            genSell();
        } else if (option.equals("3")) {
            RPG.town();
        } else {
            generalStore();
        }
    }

    private static void chapel() {
        System.out.println("\n");
        System.out.println("The chapel is quiet as you enter. What would you like to do?");
        System.out.println("Your Gold: " + RPG.myGP);
        System.out.println(" 1. Pray        2. Cure (25 GP)");
        System.out.println(" 3. Leave");
        option = RPG.in.nextLine();
        if(option.equals("1")){
            System.out.println("You beseech the gods for their favor as you pray at the altar.");
            System.out.println("As you leave, you feel as if you've been granted a divine blessing.");
            event = RPG.gen.nextInt(4) + 1;
            buff.buffDur[event][1] = buff.buffDur[event][0];
            buff.startBuff();
            RPG.town();
        }else if(option.equals("2")){
            if(RPG.myGP >= 25){
                RPG.myGP -= 25;
                System.out.println("You pay the priest and receive divine cleansing. All negative effects have been purged!");
                buff.purgeEffects();
            }else{
                System.out.println("You are unable to afford the cost of receiving a cure at this time.");
            }
            RPG.town();
        }else if(option.equals("3")){
            RPG.town();
        }else{
            chapel();
        }
    }

    private static void tavern() {
        System.out.println("\n");
        System.out.println("The tavern is fairly crowded as you enter.");
        System.out.println("Your Gold: " + RPG.myGP);
        System.out.println(" 1. Have a drink (5 GP)     2. Buy a round of drinks (50 GP)");
        System.out.println(" 3. Play dice (10 GP)       4. Leave");
        option = RPG.in.nextLine();
        if(option.equals("1")) {
            System.out.println("You sit down at a table and call over a barmaid to order a drink.");
            if(RPG.myGP >= 5){
                RPG.myGP -= 5;
                System.out.println("You pay the barmaid and she brings you a drink. You enjoy it, then relax a little before leaving.");
            }else{
                System.out.println("You realize you can't afford the drink. You leave, embarrassed.");
            }
            RPG.town();
        }else if(option.equals("2")) {
            System.out.println("You walk up to the bar, and call over the bartender to order a round of drinks.");
            if(RPG.myGP >= 50){
                RPG.myGP -= 50;
                System.out.println("You pay the bartender and he pours some drinks. You raise your mug with the other patrons, and down it quickly.");
                System.out.println("You find yourself back at camp a while later, rather drunk. You don't remember how many drinks you had.");
                buff.buffDur[5][1] = buff.buffDur[5][0];
                buff.startBuff();
                RPG.camp();
            }else{
                System.out.println("You realize you can't afford the round of drinks. You leave, embarrassed.");
                RPG.town();
            }
        }else if(option.equals("3")) {
            System.out.println("You approach the table where some patrons are playing dice. They invite you to join.");
            if(RPG.myGP >= 10){
                RPG.myGP -= 10;
                System.out.println("You place 10 gold on the table, and they roll the dice.");
                diceGame();
                tavern();
            }else{
                System.out.println("You can't afford the wager, so you politely decline and leave the table.");
                tavern();
            }
        }else if(option.equals("4")) {
            RPG.town();
        }else{
            tavern();
        }
    }

    private static void blacksmith() {
        System.out.println("\n");
        System.out.println("The blacksmith looks up from his anvil at you expectantly.");
        System.out.println("\"What can aye do ye fer, me boy o'?\" he asks in his outrageously thick accent.");
        System.out.println(" 1. Buy Armor           2. Buy Weapon");
        System.out.println(" 3. Make Special Gear   4. Nothing.");
        option = RPG.in.nextLine();
        if (option.equals("1")) {
            bsArmor();
        } else if (option.equals("2")) {
            bsWeapon();
        }else if(option.equals("3")){
            bsCreate();
        }else if (option.equals("4")) {
            RPG.town();
        } else {
            blacksmith();
        }
    }

    private static void magicShop() {
        System.out.println("\n");
        System.out.println("\"Welcome to the Mystic Emporium! Quality goods for the discerning mage!\" The wizard claims.");
        System.out.println(" 1. Buy Items       2. Create Items");
        System.out.println(" 3. Leave");
        option = RPG.in.nextLine();
        if(option.equals("1")){
            msBuy();
        }else if(option.equals("2")){
            msCreate();
        }else if(option.equals("3")){
            RPG.town();
        }else{
            magicShop();
        }
        
    }

    protected static void muggerEvent() {
        System.out.println("\n");
        System.out.println("A mugger steps out from an alley and approaches you, knife drawn!");
        System.out.println("\"Hand over 35 gold and no one gets hurt!\" he threatens.");
        System.out.println("What do you do?");
        System.out.println(" 1. Comply (-35 GP)       2. Resist");
        option = RPG.in.nextLine();
        if (option.equals("1")) {
            if (RPG.myGP >= 35) {
                System.out.println("You surrender 35 gold to the mugger and he slinks back into the alley.");
                RPG.myGP -= 35;
                RPG.town();
            } else {
                System.out.println("You offer " + RPG.myGP + " to the mugger. He eyes you suspiciously, then attacks!");
                RPG.enemy = new mob("mugger");
                RPG.mobFight();
            }
        } else if (option.equals("2")) {
            RPG.enemy = new mob("mugger");
            RPG.mobFight();
        } else {
            muggerEvent();
        }
        RPG.town();
    }

    //END TOWN METHODS
    
    //STORE METHODS
    
    private static void genBuy() {
        System.out.println("\n");
        System.out.println("\"Looking to buy? What can I interest you in?\"");
        System.out.println("Your Gold: " + RPG.myGP);
        System.out.println(" 1. Trail Mix (5 GP)      2. Cooked Fish (10 GP)");
        System.out.println(" 3. Fishing Pole (20 GP)   4. Pickaxe (35 GP)");
        System.out.println(" 5. Shovel (50 GP)          6. Nothing");
        option = RPG.in.nextLine();
        if (option.equals("1")) {
            if (RPG.myGP >= 5) {
                RPG.myGP -= 5;
                inv.foodNum[0] += 1;
                System.out.println("You purchased a bag of Trail Mix.");
                genBuy();
            } else {
                System.out.println("You realize you can't afford that.");
                genBuy();
            }
        } else if (option.equals("2")) {
            if (RPG.myGP >= 10) {
                RPG.myGP -= 10;
                inv.foodNum[1] += 1;
                System.out.println("You purchased some Cooked Fish.");
                genBuy();
            } else {
                System.out.println("You realize you can't afford that.");
                genBuy();
            }
        } else if (option.equals("3")) {
            if (RPG.myGP >= 20) {
                RPG.myGP -= 20;
                inv.toolNum[0] += 1;
                System.out.println("You purchased a Fishing Pole.");
                genBuy();
            } else {
                System.out.println("You realize you can't afford that.");
                genBuy();
            }
        } else if (option.equals("4")) {
            if (RPG.myGP >= 35) {
                RPG.myGP -= 35;
                inv.toolNum[1] += 1;
                System.out.println("You purchased a Pickaxe.");
                genBuy();
            } else {
                System.out.println("You realize you can't afford that.");
                genBuy();
            }
        } else if (option.equals("5")) {
            if (RPG.myGP >= 50) {
                RPG.myGP -= 50;
                inv.toolNum[2] += 1;
                System.out.println("You purchased a Shovel.");
                genBuy();
            } else {
                System.out.println("You realize you can't afford that.");
                genBuy();
            }
        } else if (option.equals("6")) {
            generalStore();
        } else {
            genBuy();
        }
    }

    private static void genSell() {
        System.out.println("\n");
        System.out.println("\"What were you thinking of selling?\"");
        System.out.println(" 1. " + inv.generalInv[0]);
        System.out.println(" 2. " + inv.generalInv[1]);
        System.out.println(" 3. " + inv.generalInv[2]);
        System.out.println(" 4. " + inv.generalInv[3]);
        System.out.println(" 5. " + inv.generalInv[4]);
        System.out.println(" 6. " + inv.generalInv[5]);
        System.out.println(" 7. " + inv.generalInv[6]);
        System.out.println(" 8. " + inv.generalInv[7]);
        System.out.println(" 9. " + inv.generalInv[8]);
        System.out.println(" 10. " + inv.generalInv[9]);
        System.out.println(" 11. " + inv.generalInv[10]);
        System.out.println(" 12. " + inv.generalInv[11]);
        System.out.println(" 13. Nevermind");
        option = RPG.in.nextLine();
        if(option.equals("1")){
            sellNum(0);
        }else if(option.equals("2")){
            sellNum(1);
        }else if(option.equals("3")){
            sellNum(2);
        }else if(option.equals("4")){
            sellNum(3);
        }else if(option.equals("5")){
            sellNum(4);
        }else if(option.equals("6")){
            sellNum(5);
        }else if(option.equals("7")){
            sellNum(6);
        }else if(option.equals("8")){
            sellNum(7);
        }else if(option.equals("9")){
            sellNum(8);
        }else if(option.equals("10")){
            sellNum(9);
        }else if(option.equals("11")){
            sellNum(10);
        }else if(option.equals("12")){
            sellNum(11);
        }else if(option.equals("13")){
            generalStore();
        }else{
            genSell();
        }
    }
    
    private static void sellNum(int index){
        if(inv.generalNum[index]==0){
            System.out.println("You don't have any " + inv.generalInv[index] + " to sell.");
        }else{
            System.out.println("You have " + inv.generalNum[index] + " " + inv.generalInv[index] + " available.");
            System.out.println("The merchant offers " + inv.generalVal[index] + " gold each. How many would you like to sell?");
            System.out.println(" 1. None (0)            2. One (1)");
            System.out.println(" 3. Five (5)            4. Ten (10)");
            System.out.println(" 5. Twenty-Five (25)    6. All (" + inv.generalNum[index] + ")");
            option = RPG.in.nextLine();
            if(option.equals("1")){
                System.out.println("You decline the offer.");
            }else if(option.equals("2")){
                RPG.lootGP = inv.generalVal[index] * 1;
                System.out.println("You agree to sell 1 for " + RPG.lootGP + " gold.");
                inv.generalNum[index] -= 1;
                RPG.myGP += RPG.lootGP;
            }else if(option.equals("3")){
                if(inv.generalNum[index] >=5){
                    RPG.lootGP = inv.generalVal[index] * 5;
                    System.out.println("You agree to sell 5 for " + RPG.lootGP + " gold.");
                    inv.generalNum[index] -= 5;
                    RPG.myGP += RPG.lootGP;
                }else{
                    System.out.println("You don't have 5 to sell.");
                }
            }else if(option.equals("4")){
                if(inv.generalNum[index] >=10){
                    RPG.lootGP = inv.generalVal[index] * 10;
                    System.out.println("You agree to sell 10 for " + RPG.lootGP + " gold.");
                    inv.generalNum[index] -= 10;
                    RPG.myGP += RPG.lootGP;
                }else{
                    System.out.println("You don't have 10 to sell.");
                }
            }else if(option.equals("5")){
                if(inv.generalNum[index] >=25){
                    RPG.lootGP = inv.generalVal[index] * 25;
                    System.out.println("You agree to sell 25 for " + RPG.lootGP + " gold.");
                    inv.generalNum[index] -= 25;
                    RPG.myGP += RPG.lootGP;
                }else{
                    System.out.println("You don't have 25 to sell.");
                }
            }else if(option.equals("6")){
                RPG.lootGP = inv.generalVal[index] * inv.generalNum[index];
                System.out.println("You agree to sell " + inv.generalNum[index] + " for " + RPG.lootGP + " gold.");
                inv.generalNum[index] = 0;
                RPG.myGP += RPG.lootGP;
            }
        }
        genSell();
    }

    private static void bsArmor() {
        System.out.println("\n");
        System.out.println("\"Without me armor, ye look no scarier than me Nanny!\"");
        System.out.println("Your Gold: " + RPG.myGP);
        System.out.println(" 1. Shield (25 GP)                  2. Leather armor (50 GP)");
        System.out.println(" 3. Chainmail armor (100 GP)        4. Plate armor (250 GP)");
        System.out.println(" 5. Crystal Armor (500 GP)          6. Nothing");
        option = RPG.in.nextLine();
        if (option.equals("1")) {
            if (RPG.myGP >= 25) {
                RPG.myGP -= 25;
                inv.offhandNum[0] += 1;
                System.out.println("You purchased a Shield.");
                bsArmor();
            } else {
                System.out.println("You realize you can't afford that.");
                bsArmor();
            }
        } else if (option.equals("2")) {
            if (RPG.myGP >= 50) {
                RPG.myGP -= 50;
                inv.armorNum[0] += 1;
                System.out.println("You purchased a set of Leather Armor.");
                bsArmor();
            } else {
                System.out.println("You realize you can't afford that.");
                bsArmor();
            }
        } else if (option.equals("3")) {
            if (RPG.myGP >= 100) {
                RPG.myGP -= 100;
                inv.armorNum[1] += 1;
                System.out.println("You purchased a set of Chainmail Armor.");
                bsArmor();
            } else {
                System.out.println("You realize you can't afford that.");
                bsArmor();
            }
        } else if (option.equals("4")) {
            if (RPG.myGP >= 250) {
                RPG.myGP -= 250;
                inv.armorNum[2] += 1;
                System.out.println("You purchased a set of Plate Armor.");
                bsArmor();
            } else {
                System.out.println("You realize you can't afford that.");
                bsArmor();
            }
        } else if (option.equals("5")) {
            if (RPG.myGP >= 500) {
                RPG.myGP -= 500;
                inv.armorNum[3] += 1;
                System.out.println("You purchased a set of Crystal Armor.");
                bsArmor();
            } else {
                System.out.println("You realize you can't afford that.");
                bsArmor();
            }
        } else if (option.equals("6")) {
            blacksmith();
        } else {
            bsArmor();
        }
    }

    private static void bsWeapon() {
        System.out.println("\n");
        System.out.println("\"You ne'er hurt anyone wieldin' anything other than one o' me weap'ns!\"");
        System.out.println("Your Gold: " + RPG.myGP);
        System.out.println(" 1. Knife (5 GP)               2. Spear (15 GP)");
        System.out.println(" 3. Axe (30 GP)                4. Sword (50 GP)");
        System.out.println(" 5. Off-hand Dagger (5 GP)     6. Nothing");
        option = RPG.in.nextLine();
        if (option.equals("1")) {
            if (RPG.myGP >= 5) {
                RPG.myGP -= 5;
                inv.weaponNum[0] += 1;
                System.out.println("You purchased a Knife.");
                bsWeapon();
            } else {
                System.out.println("You realize you can't afford that.");
                bsWeapon();
            }
        } else if (option.equals("2")) {
            if (RPG.myGP >= 15) {
                RPG.myGP -= 15;
                inv.weaponNum[1] += 1;
                System.out.println("You purchased a Spear.");
                bsWeapon();
            } else {
                System.out.println("You realize you can't afford that.");
                bsWeapon();
            }
        } else if (option.equals("3")) {
            if (RPG.myGP >= 30) {
                RPG.myGP -= 30;
                inv.weaponNum[2] += 1;
                System.out.println("You purchased an Axe.");
                bsWeapon();
            } else {
                System.out.println("You realize you can't afford that.");
                bsWeapon();
            }
        } else if (option.equals("4")) {
            if (RPG.myGP >= 50) {
                RPG.myGP -= 50;
                inv.weaponNum[3] += 1;
                System.out.println("You purchased a Sword.");
                bsWeapon();
            } else {
                System.out.println("You realize you can't afford that.");
                bsWeapon();
            }
        } else if (option.equals("5")) {
            if (RPG.myGP >= 5) {
                RPG.myGP -= 5;
                inv.offhandNum[2] += 1;
                System.out.println("You purchased an Off-hand Dagger.");
                bsWeapon();
            } else {
                System.out.println("You realize you can't afford that.");
                bsWeapon();
            }
        } else if (option.equals("6")) {
            blacksmith();
        } else {
            bsWeapon();
        }
    }
    
    private static void bsCreate(){
        System.out.println("\n");
        System.out.println("\"Oi, ye want something special? Bring me the materials and I can make it!\" he boasts.");
        System.out.println("The blacksmith proudly shows you some different plans he can create from various items.");
        System.out.println(" 1. Wolf Hide Armor (5 Wolf Pelt, 15 GP)");
        System.out.println(" 2. Turtle Shield (5 Turtle Shell, 25 GP)");
        System.out.println(" 3. Turtle Armor (15 Turtle Shell, 2 Wolf Pelt, 50 GP)");
        System.out.println(" 4. Troll Bite Gauntlet (10 Troll Tooth, 1 Wolf Pelt, 25 GP)");
        System.out.println(" 5. Nevermind");
        option = RPG.in.nextLine();
        if(option.equals("1")){
            if(inv.generalNum[0] >= 5 && RPG.myGP >=15){
                inv.generalNum[0] -= 5;
                RPG.myGP -=15;
                inv.armorNum[4] += 1;
                System.out.println("You hand over the materials and gold, and the Blacksmith gets to work.");
                System.out.println("After a while, he hands you your finished set of Wolf Hide Armor!");
                bsCreate();
            }else{
                System.out.println("You don't have everything required to make this.");
                bsCreate();
            }
        }else if(option.equals("2")){
            if(inv.generalNum[3] >= 5 && RPG.myGP >=25){
                inv.generalNum[3] -= 5;
                RPG.myGP -=25;
                inv.offhandNum[3] += 1;
                System.out.println("You hand over the materials and gold, and the Blacksmith gets to work.");
                System.out.println("After a while, he hands you your finished Turtle Shield!");
                bsCreate();
            }else{
                System.out.println("You don't have everything required to make this.");
                bsCreate();
            }
        }else if(option.equals("3")){
            if(inv.generalNum[0] >= 2 && inv.generalNum[3] >= 15 && RPG.myGP >=50){
                inv.generalNum[0] -= 2;
                inv.generalNum[3] -= 15;
                RPG.myGP -=50;
                inv.armorNum[5] += 1;
                System.out.println("You hand over the materials and gold, and the Blacksmith gets to work.");
                System.out.println("After a while, he hands you your finished set of Turtle Armor!");
                bsCreate();
            }else{
                System.out.println("You don't have everything required to make this.");
                bsCreate();
            }
        }else if(option.equals("4")){
            if(inv.generalNum[0] >= 1 && inv.generalNum[4] >= 10 && RPG.myGP >=25){
                inv.generalNum[0] -= 1;
                inv.generalNum[4] -= 10;
                RPG.myGP -=25;
                inv.weaponNum[4] += 1;
                System.out.println("You hand over the materials and gold, and the Blacksmith gets to work.");
                System.out.println("After a while, he hands you your finished Troll Bite Gauntlet!");
                bsCreate();
            }else{
                System.out.println("You don't have everything required to make this.");
                bsCreate();
            }
        }else if(option.equals("5")){
            blacksmith();
        }else{
            bsCreate();
        }
    }

    private static void msBuy(){
        System.out.println("Your Gold: " + RPG.myGP);
        System.out.println(" 1. Weak Health Potion (25 GP)      2. Weak Mana Potion (25 GP)");
        System.out.println(" 3. Health Potion (50 GP)           4. Mana Potion (50GP)");
        System.out.println(" 5. Potent Health Potion (100 GP)   6. Potent Mana Potion (100 GP)");
        System.out.println(" 7. Spell Tome (25 GP)              8. Leave Shop");
        option = RPG.in.nextLine();
        if (option.equals("1")) {
            if (RPG.myGP >= 25) {
                RPG.myGP -= 25;
                inv.potionNum[0] += 1;
                System.out.println("You purchased a Weak Health Potion.");
                System.out.println("You now have " + inv.potionNum[0] + ".");
                msBuy();
            } else {
                System.out.println("You realize you can't afford that.");
                msBuy();
            }
        } else if (option.equals("2")) {
            if (RPG.myGP >= 25) {
                RPG.myGP -= 25;
                inv.potionNum[1] += 1;
                System.out.println("You purchased a Weak Mana Potion.");
                System.out.println("You now have " + inv.potionNum[1] + ".");
                msBuy();
            } else {
                System.out.println("You realize you can't afford that.");
                msBuy();
            }
        } else if (option.equals("3")) {
            if (RPG.myGP >= 50) {
                RPG.myGP -= 50;
                inv.potionNum[2] += 1;
                System.out.println("You purchased a Health Potion.");
                System.out.println("You now have " + inv.potionNum[2] + ".");
                msBuy();
            } else {
                System.out.println("You realize you can't afford that.");
                msBuy();
            }
        } else if (option.equals("4")) {
            if (RPG.myGP >= 50) {
                RPG.myGP -= 50;
                inv.potionNum[3] += 1;
                System.out.println("You purchased a Mana Potion.");
                System.out.println("You now have " + inv.potionNum[3] + ".");
                msBuy();
            } else {
                System.out.println("You realize you can't afford that.");
                msBuy();
            }
        } else if (option.equals("5")) {
            if (RPG.myGP >= 100) {
                RPG.myGP -= 100;
                inv.potionNum[4] += 1;
                System.out.println("You purchased a Potent Health Potion.");
                System.out.println("You now have " + inv.potionNum[4] + ".");
                msBuy();
            } else {
                System.out.println("You realize you can't afford that.");
                msBuy();
            }
        } else if (option.equals("6")) {
            if (RPG.myGP >= 100) {
                RPG.myGP -= 100;
                inv.potionNum[5] += 1;
                System.out.println("You purchased a Potent Mana Potion.");
                System.out.println("You now have " + inv.potionNum[5] + ".");
                msBuy();
            } else {
                System.out.println("You realize you can't afford that.");
                msBuy();
            }
        } else if (option.equals("7")) {
            if (RPG.myGP >= 25) {
                RPG.myGP -= 25;
                inv.offhandNum[1] += 1;
                System.out.println("You purchased a Spell Tome.");
                msBuy();
            } else {
                System.out.println("You realize you can't afford that.");
                msBuy();
            }
        } else if (option.equals("8")) {
            magicShop();
        } else {
            msBuy();
        }
    }
    
    private static void msCreate(){
        System.out.println("\n");
        System.out.println("\"Looking for something a bit more potent, huh? Great! Let me show you what I can make!\"");
        System.out.println("The wizard seems extremely excited to show you a tome of different things he can create with the right reagents.");
        System.out.println(" 1. Health Potion (2 Heartbloom, 1 Jar of Honey, 10 GP)");
        System.out.println(" 2. Mana Potion (2 Mageflower, 1 Jar of Honey, 10 GP)");
        System.out.println(" 3. Nature's Fury (5 Venom Vine, 50 GP)");
        System.out.println(" 4. Voodoo Doll (1 Wolf Pelt, 1 Wooden Doll, 1 Mageflower, 1 Venom Vine, 50 GP)");
        System.out.println(" 5. Doll of Rebirth (1 Wooden Doll, 25 Heartbloom, 20 Mageflower, 5 Venom Vine, 250 GP)");
        System.out.println(" 6. Nevermind");
        option = RPG.in.nextLine();
        if(option.equals("1")){
            if(inv.generalNum[5] >= 2 && inv.foodNum[2] >=1 && RPG.myGP >=10){
                inv.generalNum[5] -= 2;
                inv.foodNum[2] -=1;
                RPG.myGP -=10;
                inv.potionNum[2] += 1;
                System.out.println("You hand over the materials and gold, and the wizard gets to work.");
                System.out.println("After a few moments, the wizard hands you a Health Potion!");
                System.out.println("You now have " + inv.potionNum[2] + ".");
                msCreate();
            }else{
                System.out.println("You don't have everything required to make this.");
                msCreate();
            }
        }else if(option.equals("2")){
            if(inv.generalNum[6] >= 2 && inv.foodNum[2] >=1 && RPG.myGP >=10){
                inv.generalNum[6] -= 2;
                inv.foodNum[2] -=1;
                RPG.myGP -=10;
                inv.potionNum[3] += 1;
                System.out.println("You hand over the materials and gold, and the wizard gets to work.");
                System.out.println("After a few moments, the wizard hands you a Mana Potion!");
                System.out.println("You now have " + inv.potionNum[3] + ".");
                msCreate();
            }else{
                System.out.println("You don't have everything required to make this.");
                msCreate();
            }
        }else if(option.equals("3")){
            if(inv.generalNum[7] >= 5 && RPG.myGP >=50){
                inv.generalNum[7] -= 2;
                RPG.myGP -=50;
                inv.potionNum[6] += 1;
                System.out.println("You hand over the materials and gold, and the wizard gets to work.");
                System.out.println("After a few moments, the wizard hands you a Nature's Fury Potion!");
                System.out.println("You now have " + inv.potionNum[6] + ".");
                msCreate();
            }else{
                System.out.println("You don't have everything required to make this.");
                msCreate();
            }
        }else if(option.equals("4")){
            if(inv.generalNum[0] >= 1 && inv.generalNum[1] >= 1 && inv.generalNum[6] >= 1 && inv.generalNum[7] >=1 && RPG.myGP >=50){
                inv.generalNum[0] -= 1;
                inv.generalNum[1] -= 1;
                inv.generalNum[6] -= 1;
                inv.generalNum[7] -= 1;
                RPG.myGP -=50;
                inv.offhandNum[4] += 1;
                System.out.println("You hand over the materials and gold, and the wizard gets to work.");
                System.out.println("After a few moments, the wizard hands you a Voodoo Doll!");
                msCreate();
            }else{
                System.out.println("You don't have everything required to make this.");
                msCreate();
            }
        }else if(option.equals("5")){
            if(inv.generalNum[1] >= 1 && inv.generalNum[5] >= 25 && inv.generalNum[6] >= 20 && inv.generalNum[7] >= 5 && RPG.myGP >=250){
                inv.generalNum[1] -= 1;
                inv.generalNum[5] -= 25;
                inv.generalNum[6] -= 20;
                inv.generalNum[7] -= 5;
                RPG.myGP -=250;
                inv.toolNum[3] += 1;
                System.out.println("You hand over the materials and gold, and the wizard gets to work.");
                System.out.println("After a few moments, the wizard hands you a Doll of Rebirth!");
                System.out.println("\"As long as you are carrying one of these, Death cannot claim you. The Doll won't be so lucky though...\"");
                msCreate();
            }else{
                System.out.println("You don't have everything required to make this.");
                msCreate();
            }
        }else if(option.equals("6")){
            magicShop();
        }else{
            msCreate();
        }
    }
    
    //END STORE METHODS
    
    //MISC METHODS
    
    private static void diceGame(){
        int dice1, dice2, sum;
        dice1 = RPG.gen.nextInt(6)+1;
        dice2 = RPG.gen.nextInt(6)+1;
        sum = dice1 + dice2;
        System.out.println("Die 1: " + dice1 + "    Die 2: "+ dice2);
        if(dice1 == dice2 && sum == 12){
            RPG.lootGP = 25;
            System.out.println("You rolled double 6's! You won " + RPG.lootGP + " gold!");
            RPG.myGP += RPG.lootGP;
        }else if(dice1 == dice2 && sum != 12){
            RPG.lootGP = 15;
            System.out.println("You rolled doubles! You won " + RPG.lootGP + " gold!");
            RPG.myGP += RPG.lootGP;
        }else if(sum == 6){
            RPG.lootGP = 10;
            System.out.println("Your dice roll adds up to 6. You break even.");
            RPG.myGP += RPG.lootGP;
        }else{
            System.out.println("Your dice roll adds up to " + sum +". You lose your wager.");
        }
    }
    
    //END MISC METHODS
    
}
