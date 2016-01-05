package rpg;

/**
 * @author Bloodcorpse
 */
import java.util.*;

public class mob {

    static Random gen = new Random();
    static String mobName;
    static int mobMaxHP;
    static int mobCurHP;
    static int mobMaxDmg;
    static int mobArmor;
    static int mobEffect;       //Effect to inflict (-1 if no effect)
    static int mobEffectChance; //Percent chance to inflict effect
    static int mobBuff;         //Buff to inflict (-1 if no buff)
    static int mobBuffChance;   //Percent chance to inflict buff
    static int mobGP;           //Max gold drop
    static int mobXP;
    static String mobDropName;  //Name of loot item dropped
    static int mobDropNum;      //Array index of loot item
    static int mobDropChance;   //Chance to drop loot item
    
    static boolean mobAlive;
    

    public mob(String type) {
        if (type.equals("goblin")) {
            goblin();
        } else if (type.equals("wolf")) {
            wolf();
        } else if (type.equals("mugger")) {
            mugger();
        } else if (type.equals("snapping turtle")){
            snapTurtle();
        } else if (type.equalsIgnoreCase("troll")){
            troll();
        } else if (type.equalsIgnoreCase("hermit")){
            hermit();
        } else if (type.equalsIgnoreCase("yeti")){
            yeti();
        }
    }

    private void goblin() {
        mobName = "Goblin";
        mobMaxHP = 50;
        mobCurHP = 50;
        mobMaxDmg = 10;
        mobArmor = 0;
        mobEffect = -1;
        mobEffectChance = 0;
        mobBuff = -1;
        mobBuffChance = 0;
        mobGP = gen.nextInt(15) + 1;
        mobXP = 5;
        mobDropName = "Nothing";
        mobDropNum = -1;
        mobDropChance = 0;
        mobAlive = true;
    }
    
    private void wolf() {
        mobName = "Wolf";
        mobMaxHP = 75;
        mobCurHP = 75;
        mobMaxDmg = 15;
        mobArmor = 0;
        mobEffect = 0;
        mobEffect = 5;
        mobBuff = -1;
        mobBuffChance = 0;
        mobGP = gen.nextInt(15) + 1;
        mobXP = 10;
        mobDropName = "Wolf Pelt";
        mobDropNum = 0;
        mobDropChance = 25;
        mobAlive = true;
    }

    private void mugger() {
        mobName = "Mugger";
        mobMaxHP = 75;
        mobCurHP = 75;
        mobMaxDmg = 15;
        mobArmor = 1;
        mobEffect = -1;
        mobEffectChance = 0;
        mobBuff = -1;
        mobBuffChance = 0;
        mobGP = gen.nextInt(25) + 1;
        mobXP = 15;
        mobDropName = "Nothing";
        mobDropNum = -1;
        mobDropChance = 0;
        mobAlive = true;
    }
    
    private void snapTurtle() {
        mobName = "Snapping Turtle";
        mobMaxHP = 50;
        mobCurHP = 50;
        mobMaxDmg = 10;
        mobArmor = 7;
        mobEffect = -1;
        mobEffectChance = 0;
        mobBuff = 6;
        mobBuffChance = 10;
        mobGP = gen.nextInt(25) + 1;
        mobXP = 15;
        mobDropName = "Turtle Shell";
        mobDropNum = 3;
        mobDropChance = 10;
        mobAlive = true;
    }
    
    private void troll() {
        mobName = "Troll";
        mobMaxHP = 100;
        mobCurHP = 100;
        mobMaxDmg = 20;
        mobArmor = 2;
        mobEffect = -1;
        mobEffectChance = 0;
        mobBuff = -1;
        mobBuffChance = 0;
        mobGP = gen.nextInt(50) + 1;
        mobXP = 25;
        mobDropName = "Troll Tooth";
        mobDropNum = 4;
        mobDropChance = 10;
        mobAlive = true;
    }
    
    private void hermit() {
        mobName = "Crazy Hermit";
        mobMaxHP = 110;
        mobCurHP = 110;
        mobMaxDmg = 20;
        mobArmor = 5;
        mobEffect = -1;
        mobEffectChance = 0;
        mobBuff = -1;
        mobBuffChance = 0;
        mobGP = gen.nextInt(50) + 1;
        mobXP = 30;
        mobDropName = "Nothing";
        mobDropNum = -1;
        mobDropChance = 0;
        mobAlive = true;
    }
    private void yeti(){
        mobName = "Yeti";
        mobMaxHP = 125;
        mobCurHP = 125;
        mobMaxDmg = 25;
        mobArmor = 7;
        mobEffect = -1;
        mobEffectChance = 0;
        mobBuff = -1;
        mobBuffChance = 25;
        mobGP = gen.nextInt(50) + 1;
        mobXP = 25;
        mobDropName = "Yeti Fur";
        mobDropNum = 11;
        mobDropChance = 10;
        mobAlive = true;
    }

    protected void checkHP(Boolean block) {
        buff.checkBuff();
        if(buff.effectActive[6] == true && block == false && gen.nextInt(100) < 20){
            block = true;
        }
        if (mobCurHP > 0) {
            mobAttack(block);
        } else {
            mobDeath();
        }
    }

    private void mobAttack(Boolean block) {
        System.out.print("The " + mobName + " swings at you. ");
        if (block == true) {
            System.out.println("You block the attack!");
        } else {
            int mobDmg = gen.nextInt(mobMaxDmg + 1);
            if (mobDmg == 0) {
                System.out.println("The attack misses!");
            } else {
                mobDmg -= RPG.augArmor;
                if (mobDmg <= 0) {
                    System.out.println("The attack bounces off your armor!");
                } else {
                    System.out.println("The attack deals " + mobDmg + " damage!");
                    RPG.curHP -= mobDmg;
                    inflictEffect();
                }
            }
        }
    }
    
    private void inflictEffect(){
        if(mobEffect != -1){
            if((gen.nextInt(100) < mobEffectChance) && buff.effectActive[mobEffect] == false){
                buff.effectActive[mobEffect] = true;
                System.out.println("You've been afflicted with " + buff.effectName[mobEffect] + "!");
                buff.startBuff();
            }
        }
        if(mobBuff != -1){
            if((gen.nextInt(100) < mobBuffChance)){
                buff.buffDur[mobBuff][1] = buff.buffDur[mobBuff][0];
                System.out.println("You've been afflicted with " + buff.buffName[mobBuff] + "!");
                buff.startBuff();
            }
        }
    }

    private void mobDeath() {
        System.out.println("\n" + "The " + mobName + " has been defeated!");
        RPG.lootGP = gen.nextInt(mobGP) + 1;
        System.out.println("You loot " + RPG.lootGP + " gold pieces from the " + mobName + "'s corpse!");
        RPG.myGP += RPG.lootGP;
        if(mobDropNum != -1){
            lootDrop();
        }
        System.out.println("You earn " + mobXP + " experience from your battle!");
        RPG.myXP += mobXP;
        mobAlive = false;
    }
    
    private void lootDrop(){
        if(gen.nextInt(100)< mobDropChance){
            System.out.println("You loot one " + mobDropName + " from the " + mobName + "'s corpse!");
            inventory.generalNum[mobDropNum] += 1;
        }
    }
}
