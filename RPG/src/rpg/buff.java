package rpg;

/**
 * @author Bloodcorpse
 */
import java.util.*;

public class buff {

    static int armorBuff = 0;
    static int strBuff = 0;
    static int speedBuff = 0;
    static int intelBuff = 0;
    static int dmgBuff = 0;
    static int spellDmgBuff = 0;
    static int xpGain = 0;
    static String buffName[] = new String[50];
    static int buffData[][] = new int[50][7];
    static int buffDur[][] = new int[50][2];
    static boolean effectActive[] = new boolean[50];
    static String effectName[] = new String [50];
    static int effectData[][] = new int[50][7];
    
    /* Data Key:            Duration Key:
     * 0 = str              0 = length
     * 1 = speed            1 = remaining
     * 2 = intel
     * 3 = armor
     * 4 = damage
     * 5 = spell damage
     * 6 = exp gain
     */

    public buff() {
        //BUFFS
        buffName[0] = "Mage Armor";
        buffData[0][0] = 0; buffData[0][1] = 0; buffData[0][2] = 0; buffData[0][3] = 2; buffData[0][4] = 0; buffData[0][5] = 0; buffData[0][6] = 0;
        buffDur[0][0] = 5; buffDur[0][1] = 0;
        buffName[1] = "Divine Protection";
        buffData[1][0] = 0; buffData[1][1] = 0; buffData[1][2] = 0; buffData[1][3] = 5; buffData[1][4] = 0; buffData[1][5] = 0; buffData[1][6] = 0;
        buffDur[1][0] = 10; buffDur[1][1] = 0;
        buffName[2] = "Divine Might";
        buffData[2][0] = 2; buffData[2][1] = 0; buffData[2][2] = 0; buffData[2][3] = 0; buffData[2][4] = 0; buffData[2][5] = 0; buffData[2][6] = 0;
        buffDur[2][0] = 10; buffDur[2][1] = 0;
        buffName[3] = "Divine Insight";
        buffData[3][0] = 0; buffData[3][1] = 0; buffData[3][2] = 2; buffData[3][3] = 0; buffData[3][4] = 0; buffData[3][5] = 0; buffData[3][6] = 0;
        buffDur[3][0] = 10; buffDur[3][1] = 0;
        buffName[4] = "Divine Haste";
        buffData[4][0] = 0; buffData[4][1] = 2; buffData[4][2] = 0; buffData[4][3] = 0; buffData[4][4] = 0; buffData[4][5] = 0; buffData[4][6] = 0;
        buffDur[4][0] = 10; buffDur[4][1] = 0;
        buffName[5] = "Drunkeness";
        buffData[5][0] = 1; buffData[5][1] = 0; buffData[5][2] = -1; buffData[5][3] = 0; buffData[5][4] = 0; buffData[5][5] = 0; buffData[5][6] = 0;
        buffDur[5][0] = 10; buffDur[5][1] = 0;
        buffName[6] = "Punctured Armor";
        buffData[6][0] = 0; buffData[6][1] = 0; buffData[6][2] = 0; buffData[6][3] = -2; buffData[6][4] = 0; buffData[6][5] = 0; buffData[6][6] = 0;
        buffDur[6][0] = 4; buffDur[6][1] = 0;
        buffName[7] = "Chilled";
        buffData[7][0] = 0; buffData[7][1] = -2; buffData[7][2] = 0; buffData[7][3] = 0; buffData[7][4] = 0; buffData[7][5] = 0; buffData[7][6] = 0;
        buffDur[7][0] = 3; buffDur[7][1] = 0;
        //END BUFFS
        
        //EFFECTS (CURSES / EQUIP BONUSES)
        effectName[0] = "Mind Fog";
        effectData[0][0] = 0; effectData[0][1] = 0; effectData[0][2] = -1; effectData[0][3] = 0; effectData[0][4] = 0; effectData[0][5] = 0; effectData[0][6] = 0;
        effectName[1] = "Off-hand: Shield";
        effectData[1][0] = 0; effectData[1][1] = 0; effectData[1][2] = 0; effectData[1][3] = 2; effectData[1][4] = 0; effectData[1][5] = 0; effectData[1][6] = 0;
        effectName[2] = "Off-hand: Spell Tome";
        effectData[2][0] = 0; effectData[2][1] = 0; effectData[2][2] = 0; effectData[2][3] = 0; effectData[2][4] = 0; effectData[2][5] = 10; effectData[2][6] = 0;
        effectName[3] = "Off-hand: Dagger";
        effectData[3][0] = 0; effectData[3][1] = 0; effectData[3][2] = 0; effectData[3][3] = 0; effectData[3][4] = 2; effectData[3][5] = 0; effectData[3][6] = 0;
        effectName[4] = "Off-hand: Turtle Shield";
        effectData[4][0] = 0; effectData[4][1] = 0; effectData[4][2] = 0; effectData[4][3] = 5; effectData[4][4] = 0; effectData[4][5] = 0; effectData[4][6] = 0;
        effectName[5] = "Off-hand: Voodoo Doll";
        effectData[5][0] = 0; effectData[5][1] = 0; effectData[5][2] = 0; effectData[5][3] = 0; effectData[5][4] = 0; effectData[5][5] = 20; effectData[5][6] = 0;
        effectName[6] = "Turtle's Defense";
        effectData[6][0] = 0; effectData[6][1] = 0; effectData[6][2] = 0; effectData[6][3] = 0; effectData[6][4] = 0; effectData[6][5] = 0; effectData[6][6] = 0;
        //END EFFECTS
    }

    protected static void checkBuff() {
        strBuff = 0;
        speedBuff = 0;
        intelBuff = 0;
        armorBuff = 0;
        dmgBuff = 0;
        spellDmgBuff = 0;
        xpGain = 0;
        for (int i = 0; i < buffName.length; i++) {
            if (buffDur[i][1] > 0) {
                buffDur[i][1] -= 1;
                if (buffDur[i][1] > 0) {
                    addBuff(i);
                } else {
                    System.out.println(buffName[i] + " wore off!");
                }
            }
        }
        RPG.updateStats();
    }

    public static void startBuff() {
        strBuff = 0;
        speedBuff = 0;
        intelBuff = 0;
        armorBuff = 0;
        dmgBuff = 0;
        spellDmgBuff = 0;
        xpGain = 0;
        for (int i = 0; i < buffName.length; i++) {
            if (buffDur[i][1] > 0) {
                addBuff(i);
            }
        }
        for (int i = 0; i < effectActive.length; i++){
            if(effectActive[i] == true){
                addEffect(i);
            }
        }
        RPG.updateStats();
    }

    private static void addBuff(int i) {
        strBuff += buffData[i][0];
        speedBuff += buffData[i][1];
        intelBuff += buffData[i][2];
        armorBuff += buffData[i][3];
        dmgBuff += buffData[i][4];
        spellDmgBuff += buffData[i][5];
        xpGain += buffData[i][6];
    }
    
    private static void addEffect(int i) {
        strBuff += effectData[i][0];
        speedBuff += effectData[i][1];
        intelBuff += effectData[i][2];
        armorBuff += effectData[i][3];
        dmgBuff += effectData[i][4];
        spellDmgBuff += effectData[i][5];
        xpGain += effectData[i][6];
    }
    
    protected static void purgeEffects(){
        for(int i = 0; i < effectActive.length; i++){
            effectActive[i]=false;
        }
        inventory.effectCheck();
        startBuff();
    }

    protected static void listActive(){
        System.out.println("\n");
        System.out.println("Active Buffs:");
        System.out.println("--------------------------");
        for(int i = 0; i < buffName.length; i++){
            if(buffDur[i][1] > 0){
                System.out.println(buffName[i] + ": " + buffDur[i][1] + " combat turns remaining");
            }
        }
        System.out.println("\n");
        System.out.println("Active Effects:");
        System.out.println("--------------------------");
        for(int i = 0; i < effectName.length; i++){
            if(effectActive[i] == true ){
                System.out.println(effectName[i]);
            }
        }
    }
}
