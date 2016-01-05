package rpg;

/**
 * @author Bloodcorpse
 * Version: 0.6
 */
import java.util.*;

public class RPG {

    static Random gen = new Random();
    static Scanner in = new Scanner(System.in);
    static String name;
    static String option;
    static int event;
    static int maxHP = 100;
    static int curHP = 100;
    static int maxMP = 50;
    static int curMP = 50;
    static int myXP = 0;
    static int myArmor = 0;
    static int myGP = 100;
    static int lootGP = 0;
    static int lvl = 1;
    static int str = 1;     //physical damage
    static int body = 1;    //hp
    static int speed = 1;   //evade and dbl attack
    static int intel = 1;   //magic damage
    static int will = 1;    //mp
    static int augArmor;
    static int augStr;
    static int augSpeed;
    static int augIntel;
    static int dmgBonus = 0;
    static int spellDmgBonus = 0;
    static mob enemy;
    static buff buff = new buff();
    static inventory inv = new inventory();

    public static void main(String[] args) {
        augArmor = myArmor;
        augStr = str;
        augSpeed = speed;
        augIntel = intel;
        mainMenu();
    }
    
    protected static void mainMenu(){
        System.out.println("    ~Bloodcorpse's RPG~   ");
        System.out.println("     Adventure Awaits!    ");
        System.out.println("==========================");
        System.out.println(" 1. New Game        2. Load Game");
        option = in.nextLine();
        if(option.equals("1")){
            gameStart();
        }else if(option.equals("2")){
            save.loadGame();
        }else{
            mainMenu();
        }
    }

    private static void gameStart() {
        System.out.println("What is your name adventurer?");
        name = in.nextLine();
        while (true) {
            System.out.println("Are you sure you want to be called " + name + "? (y/n)");
            if (in.nextLine().equalsIgnoreCase("Y")) {
                break;
            } else {
                System.out.println("What is your name adventurer?");
                name = in.nextLine();
            }
        }

        System.out.println("\n");
        System.out.println("Welcome, " + name + " to your adventuring camp!");
        System.out.println("Before you begin, would you like to see helpful instructions? (y/n)");
        if (in.nextLine().equalsIgnoreCase("Y")) {
            help();
        } else {
            camp();
        }
    }

    private static void help() {
        System.out.println("\n");
        System.out.println("    Help / Instructions   ");
        System.out.println("--------------------------");
        System.out.println("Bloodcorpse's RPG uses text commands to perform actions.");
        System.out.println("Available choices will be selectable by inputting the corresponding number.");
        System.out.println("Sometimes you will be prompted to confirm a decision; Y will confirm, N will cancel.");
        System.out.println("While adventuring, you may get attacked by the creatures of the lands.");
        System.out.println("The Attack option attempts to strike the foe with a melee attack for physical damage.");
        System.out.println("Physical damage is lowered by armor, and can be blocked with the Block option.");
        System.out.println("If you are fast enough, sometimes you can actually hit your foe twice in one attack!");
        System.out.println("The Spells option will open your spellbook and list the mana costs of spells you know.");
        System.out.println("You may only cast a spell if you have enough mana for it.");
        System.out.println("Offensive spells deal magical damage, which ignores armor. Defensive spells can heal and defend you.");
        System.out.println("If you feel overwhelmed in a fight, you may try to flee with the Run option.");
        System.out.println("The fight is over when you flee, defeat the foe, or are defeated yourself.");
        System.out.println("If you defeat a foe, you will receive loot from their body. If you flee, you get nothing.");
        System.out.println("Should you be defeated, your adventure will end and the game will close.");
        System.out.println("Rumor has it that special items can restore your mortal coil should you perish while holding one...");
        System.out.println("You can heal your injuries in your camp, as well as rest to regain mana.");
        System.out.println("The local town has stores that will buy loot and sell items for the discerning adventurer.");
        System.out.println("You can view this information again by typing 'Help' at camp. Good luck!");

        //delay for reading
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("" + e);
        }
        camp();
    }

    private static void debug(){
        System.out.println("\n");
        System.out.println("=========================");
        System.out.println("      - Debug Info -     ");
        System.out.println("=========================");
        System.out.println("Version 0.6");
        System.out.println("# of Armors: " + inv.armorInv.length);
        System.out.println("# of Weapons: " + inv.weaponInv.length);
        System.out.println("# of Off-Hands: " + inv.offhandInv.length);
        System.out.println("# of Tools: " + inv.toolInv.length);
        System.out.println("# of Foods: " + inv.foodInv.length);
        System.out.println("# of Potions: " + inv.potionInv.length);
        System.out.println("# of General Items: " + inv.generalInv.length);
        System.out.println("Forest Mob: Goblin");
        System.out.println("Forest Mob: Wolf");
        System.out.println("Town Mob: Mugger");
        System.out.println("River Mob: Snapping Turtle");
        System.out.println("River Mob: Troll");
        //delay for reading
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("" + e);
        }
        camp();
    }
    
    protected static void camp() {
        System.out.println("\n");
        System.out.println("You are standing in your camp. A ring of wooden spikes defend your campfire and bedroll. You feel safe here.");
        System.out.println("What would you like to do?");
        System.out.println(" 1. Stats       2. Inventory");
        System.out.println(" 3. Rest        4. Heal");
        System.out.println(" 5. Explore     6. Save/Load");
        option = in.nextLine();
        if (option.equalsIgnoreCase("help")) {
            help();
        } else if (option.equalsIgnoreCase("debug")) {
            debug();
        } else if (option.equals("1")) {
            stats();
        } else if (option.equals("2")) {
            inv.showFullInv();
            camp();
        } else if (option.equals("3")) {
            rest();
        } else if (option.equals("4")) {
            heal();
        } else if (option.equals("5")) {
            explore();
        } else if (option.equals("6")) {
            save.menu();
        } else {
            camp();
        }
    }

    //CAMP ACTION METHODS
    
    private static void stats() {
        System.out.println("\n");
        System.out.println("Your stats:");
        System.out.println("--------------------------");
        System.out.println("Level: " + lvl);
        System.out.println("Health: " + curHP + "/" + maxHP);
        System.out.println("Mana: " + curMP + "/" + maxMP);
        System.out.println("--------------------------");
        System.out.println("Strength: " + augStr + " (" + buff.strBuff + " from effects)");
        System.out.println("Speed: " + augSpeed + " (" + buff.speedBuff + " from effects)");
        System.out.println("Body: " + body);
        System.out.println("Intel: " + augIntel + " (" + buff.intelBuff + " from effects)");
        System.out.println("Will: " + will);
        System.out.println("--------------------------");
        System.out.println("Gold Pieces: " + myGP);
        System.out.println("Experience: " + myXP + "    Next Level at: " + (lvl * 100));
        System.out.println("Armor: " + augArmor + " (" + buff.armorBuff + " from effects)");
        //delay for reading
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("" + e);
        }
        buff.listActive();
        camp();
    }

    private static void rest() {
        if (curMP < maxMP) {
            System.out.println("You rest, and regain " + (maxMP - curMP) + " mana.");
            System.out.println("You are now at full mana!");
            curMP = maxMP;
        } else {
            System.out.println("You rest, and after a while you get bored.");
        }
        camp();
    }

    private static void heal() {
        if (curHP < maxHP) {
            System.out.println("You tend to your wounds, and regain " + (maxHP - curHP) + " health.");
            System.out.println("You are now at full health!");
            curHP = maxHP;
        } else {
            System.out.println("You don't require healing, so you decide not to waste supplies.");
        }
        camp();
    }

    private static void explore() {
        System.out.println("\n");
        System.out.println("You stand in a massive cross-roads. You wonder who designed this mess of an intersection.");
        System.out.println("Where would you like to go?");
        System.out.println(" 1. Camp       2. Town");
        System.out.println(" 3. Forest     4. River");
        System.out.println(" 5. Mountain   6. Graveyard");
        option = in.nextLine();
        if (option.equals("1")) {
            camp();
        } else if (option.equals("2")) {
            town();
        } else if (option.equals("3")) {
            forest();
        } else if (option.equals("4")) {
            river();
        } else if (option.equals("5")) {
            mountain();
        } else if (option.equals("6")) {
            graveyard();
        } else {
            explore();
        }
    }

    //END CAMP ACTION METHODS
    
    //COMBAT METHODS
    
    private static void checkMyHP() {
        System.out.println("You collapse to the ground from your injuries.");
        if(curHP <=0 && inv.toolNum[3] >= 1){
            System.out.println();
            System.out.println("It seems as though the story of " + name + " ends here as you feel your life slip away.");
            System.out.println("Just before you black out, you notice your Doll of Rebirth glowing brightly...");
            System.out.println("\n");
            //pause for dramatic effect
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("" + e);
            }
            inv.toolNum[3] -= 1;
            curHP = 1; curMP = 0; myXP = 0;
            System.out.println("Your story continues, and you have been reborn at your camp.");
            camp();
        }else if(curHP <= 0 && inv.toolNum[3] == 0) {
            System.out.println("\n");
            System.out.println("The story of " + name + " ends here as your life seeps away.");
            System.out.println("\n");
            System.out.println("==========================");
            System.out.println("        -GAME OVER-       ");
            System.out.println("==========================");
            System.exit(0);
        }
    }

    private static int attack() {
        System.out.print("You swing at your foe. ");
        int dmg = gen.nextInt((augStr * 10) + 1);
        if (dmg == 0) {
            System.out.println("You miss!");
        } else {
            dmg += dmgBonus;
            dmg -= enemy.mobArmor;
            if (dmg <= 0) {
                System.out.println("Your attack bounces off the enemy!");
                dmg = 0;
            } else {
                System.out.println("You deal " + dmg + " damage!");
            }
        }
        if (gen.nextInt(100) <= speed) {
            System.out.print("Your speed allows you to strike a second time! ");
            int dmg2 = gen.nextInt((augStr * 10) + 1);
            if (dmg2 == 0) {
                System.out.println("You miss!");
            } else {
                dmg2 += dmgBonus;
                dmg2 -= enemy.mobArmor;
                if (dmg2 <= 0) {
                    System.out.println("Your attack bounces off the enemy!");
                } else {
                    System.out.println("You deal " + dmg2 + " damage!");
                    dmg += dmg2;
                }
            }
        }
        return dmg;
    }

    private static void spells() {
        System.out.println("You open your spellbook. What type of spell do you want to cast?");
        System.out.println(" 1. Offensive Spells        2. Defensive Spells");
        System.out.println(" 3. Close book");
        option = in.nextLine();
        if (option.equals("1")) {
            offSpell();
        } else if (option.equals("2")) {
            defSpell();
        } else if (option.equals("3")) {
            return;
        } else {
            spells();
        }
    }

    private static void offSpell() {
        System.out.println("You flip to your offensive spells section. These spells look painful.");
        System.out.println("Your Mana: " + curMP + "/" + maxMP);
        System.out.println(" 1. Fireball (10 MP)       2. Magic Missiles (20 MP)");
        System.out.println(" 3. Nevermind");
        option = in.nextLine();
        if (option.equals("1")) {
            if (curMP >= 10) {
                curMP -= 10;
                int dmg = 5 + (augIntel * 5) + spellDmgBonus;
                System.out.println("You conjure forth a Fireball and hurl it at your foe, dealing " + dmg + " damage!");
                enemy.mobCurHP -= dmg;
                enemy.checkHP(false);
            } else {
                System.out.println("Your mana is too drained to cast Fireball.");
                offSpell();
            }
        } else if (option.equals("2")) {
            if (curMP >= 20) {
                curMP -= 20;
                int dmg = 15 + (augIntel * 5) + spellDmgBonus;
                System.out.println("You fire Magic Missiles at your foe, dealing " + dmg + " damage!");
                enemy.mobCurHP -= dmg;
                enemy.checkHP(false);
            } else {
                System.out.println("Your mana is too drained to cast Magic Missiles.");
                offSpell();
            }
        } else if (option.equals("3")) {
            spells();
        } else {
            offSpell();
        }
    }

    private static void defSpell() {
        System.out.println("You flip to your defensive spells section. These spells look helpful.");
        System.out.println("Your Mana: " + curMP + "/" + maxMP);
        System.out.println(" 1. Mage Armor (10 MP)       2. Heal (20 MP)");
        System.out.println(" 3. Nevermind");
        option = in.nextLine();
        if (option.equals("1")) {
            if (curMP >= 10) {
                System.out.println("You recite the incantation to cast Mage Armor. You feel more protected.");
                curMP -= 10;
                buff.buffDur[0][1] += buff.buffDur[0][0];
                buff.startBuff();
                enemy.checkHP(false);
            } else {
                System.out.println("Your mana is too drained to cast Mage Armor.");
                defSpell();
            }
        } else if (option.equals("2")) {
            if (curMP >= 10) {
                System.out.println("You cast a Healing spell on yourself. Magical energy mends some of your wounds!");
                curMP -= 20;
                int heal = 15 + (augIntel * 10) + spellDmgBonus;
                if (maxHP - curHP > heal) {
                    curHP += heal;
                    System.out.println("You recover " + heal + " health! (" + curHP + "/" + maxHP + ")");
                    enemy.checkHP(false);
                } else {
                    System.out.println("You recover " + (maxHP - curHP) + " health, and are fully healed!");
                    curHP = maxHP;
                    enemy.checkHP(false);
                }
            } else {
                System.out.println("Your mana is too drained to cast Heal.");
                defSpell();
            }
        } else if (option.equals("3")) {
            spells();
        } else {
            defSpell();
        }
    }

    private static void run() {
        System.out.println("You dash away from the fight, and eventually return to camp.");
        buff.checkBuff();
        camp();
    }

    protected static void updateStats() {
        myArmor = inv.armorVal;
        augArmor = myArmor + buff.armorBuff;
        if(augArmor<0){
            augArmor = 0;
        }
        augStr = str + buff.strBuff;
        augSpeed = speed + buff.speedBuff;
        augIntel = intel + buff.intelBuff;
        maxHP = 50 + (body * 50);
        maxMP = 25 + (will * 25);
        dmgBonus = inv.weaponVal + buff.dmgBuff;
        spellDmgBonus = buff.spellDmgBuff;
    }

    //END COMBAT METHODS
    
    //EXPLORE LOCATION METHODS
    
    protected static void forest() {
        event = gen.nextInt(6);
        if (event == 0) {
            forest.goblin();
        } else if (event == 1) {
            forest.wolf();
        } else if (event == 2) {
            forest.findGold();
        } else if (event == 3) {
            forest.noEvent();
        } else if (event == 4) {
            forest.findCampsite();
        } else if(event == 5){
            forest.findHerbs();
        }
        explore();
    }

    protected static void town() {
        if (gen.nextInt(50) == 25) {
            town.muggerEvent();
        } else {
            town.townCenter();
        }
    }

    private static void river() {
        event = gen.nextInt(3);
        if (event == 0) {
            river.goFishing();
        }else if(event==1){
            river.snappingTurtle();
        }else if(event==2){
            river.trollEvent();
        }
        explore();
    }

    private static void mountain() {
        event = gen.nextInt(4);
        if (event == 0) {
            mountain.findOre();
        }else if(event==1){
            mountain.crazyHermit();
        }else if(event==2){
            mountain.yeti();
        }else if(event==3){
            mountain.landslide();
        }
        explore();
    }

    private static void graveyard() {
        System.out.println("\n");
        System.out.println("The eerie graveyard unsettles you deeply. You run back to camp in fear.");
        camp();
    }

    //END EXPLORE LOCATION METHODS
    
    //MOB FIGHT METHODS
    
    protected static void mobFight() {
        while (enemy.mobAlive == true) {
            System.out.println("\n");
            System.out.println("A " + enemy.mobName + " stands before you, ready to fight!");
            System.out.println("Your Health: " + curHP + "/" + maxHP + "    Enemy Health: " + enemy.mobCurHP + "/" + enemy.mobMaxHP);
            System.out.println("Your Mana:   " + curMP + "/" + maxMP);
            System.out.println("What do you do?");
            System.out.println(" 1. Attack     2. Block");
            System.out.println(" 3. Spells     4. Items");
            System.out.println(" 5. Run");
            option = in.nextLine();
            if (option.equals("1")) {
                enemy.mobCurHP -= attack();
                enemy.checkHP(false);
            } else if (option.equals("2")) {
                System.out.println("You prepare to block the next attack.");
                enemy.checkHP(true);
            } else if (option.equals("3")) {
                spells();
            } else if (option.equals("4")) {
                inv.combatInv();
            } else if (option.equals("5")) {
                run();
            }
            checkMyHP();
        }
        enemy = null;
        if (myXP >= (lvl * 100)) {
            levelUp();
        }
    }

    //END MOB FIGHT METHODS
    
    //LEVEL UP!
    
    private static void levelUp() {
        myXP -= (lvl * 100);
        lvl++;
        System.out.println("\n");
        System.out.println("You have leveled up! You are now level " + lvl + "!");
        int lvlPts = 3;
        while (lvlPts > 0) {
            System.out.println("Pick a stat to increase! (Points remaining: " + lvlPts + ")");
            System.out.println(" 1. Strength    (" + str + "/50)");
            System.out.println(" 2. Body        (" + body + "/50)");
            System.out.println(" 3. Speed       (" + speed + "/50)");
            System.out.println(" 4. Intel       (" + intel + "/50)");
            System.out.println(" 5. Will        (" + will + "/50)");
            System.out.println(" 6. Skip point allocation. (Will abandon unused points!)");
            option = in.nextLine();
            if (option.equals("1")) {
                if (str < 50) {
                    str++;
                    System.out.println("Your Strength stat is now at " + str + "!");
                    lvlPts--;
                } else {
                    System.out.println("Your Strength stat is already maxed at 50!");
                }
            } else if (option.equals("2")) {
                if (body < 50) {
                    body++;
                    System.out.println("Your Body stat is now at " + body + "!");
                    lvlPts--;
                } else {
                    System.out.println("Your Body stat is already maxed at 50!");
                }
            } else if (option.equals("3")) {
                if (speed < 50) {
                    speed++;
                    System.out.println("Your Speed stat is now at " + speed + "!");
                    lvlPts--;
                } else {
                    System.out.println("Your Speed stat is already maxed at 50!");
                }
            } else if (option.equals("4")) {
                if (intel < 50) {
                    intel++;
                    System.out.println("Your Intel stat is now at " + intel + "!");
                    lvlPts--;
                } else {
                    System.out.println("Your Intel stat is already maxed at 50!");
                }
            } else if (option.equals("5")) {
                if (will < 50) {
                    will++;
                    System.out.println("Your Will stat is now at " + will + "!");
                    lvlPts--;
                } else {
                    System.out.println("Your Will stat is already maxed at 50!");
                }
            } else if (option.equals("6")) {
                System.out.println("Are you sure you want to discard your remaining points? (y/n)");
                if (in.nextLine().equals("y")) {
                    lvlPts = 0;
                }
            }
        }
        updateStats();
        curHP = maxHP;
        curMP = maxMP;
    }
    
    //END LEVEL UP!
    
}
