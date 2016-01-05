package rpg;

/**
 * @author Bloodcorpse
 */
import java.util.*;

public class river {
    static String option;
    static int event;
    static inventory inv;
    
    protected static void goFishing(){
        System.out.println("\n");
        System.out.println("You find what appears to be an exceptional fishing spot!");
        if(inv.toolNum[0]>0){
            System.out.println("You cast your line and attempt to catch something...");
            event = RPG.gen.nextInt(3);
            if(event==0){
                System.out.println("You hook something, but it slips free before you manage to reel it in.");
            }else if(event==1){
                System.out.println("You catch a tasty looking fish, and quickly build a fire to cook it with.");
                inv.foodNum[1] += 1;
            }else if(event==2){
                System.out.println("You reel in a " + inv.generalInv[2] + "... It doesn't look wearable.");
                inv.generalNum[2] += 1;
            }
        }else{
            System.out.println("You don't have a " + inv.toolInv[0] + " to make use of this find.");
        }
    }
    
    protected static void snappingTurtle(){
        System.out.println("\n");
        System.out.println("A large snapping sound catches your attention as a Snapping Turtle attempting to bite you!");
        RPG.enemy = new mob("snapping turtle");
        RPG.mobFight();
    }
    
    protected static void trollEvent() {
        System.out.println("\n");
        System.out.println("You find an old bridge over the river. When you try to cross it, a large troll blocks your way!");
        System.out.println("\"This my bridge! You pay toll!\" he shouts.");
        System.out.println("What do you do?");
        System.out.println(" 1. Pay Toll (-50 GP)       2. Fight");
        System.out.println(" 3. Run Away");
        option = RPG.in.nextLine();
        if (option.equals("1")) {
            if (RPG.myGP >= 50) {
                System.out.println("You decide to pay the Troll Toll. He flashes an ugly smile and returns to his home beneath the bridge.");
                System.out.println("You cross the bridge, 50 gold lighter.");
                RPG.myGP -= 50;
            } else {
                System.out.println("You offer " + RPG.myGP + " to the troll. He greedily snatches it, and stares at you.");
                System.out.println("After a long pause, the troll decides to be let you pass.");
                RPG.myGP = 0;
            }
        } else if (option.equals("2")) {
            System.out.println("You refuse to pay. The troll becomes angry.");
            System.out.println("\"You gotta pay the Troll Toll!\"");
            RPG.enemy = new mob("troll");
            RPG.mobFight();
        } else if(option.equals("3")){
            System.out.println("You decide to return from whence you came, leaving the troll to his bridge.");
        }else {
            trollEvent();
        }
    }
    
}
