package rpg;

/**
 * @author Bloodcorpse
 */
import java.util.*;

public class forest {
    static int event;
    static inventory inv;
    
    protected static void goblin(){
        System.out.println("\n");
        System.out.println("A goblin bursts from the undergrowth and attacks!");
        RPG.enemy = new mob("goblin");
        RPG.mobFight();
    }
    
    protected static void wolf(){
        System.out.println("\n");
        System.out.println("You hear a loud growling behind you. You turn around just before a wolf attacks you!");
        RPG.enemy = new mob("wolf");
        RPG.mobFight();
    }
    
    protected static void findGold(){
        System.out.println("\n");
        RPG.lootGP = RPG.gen.nextInt(10) + 1;
        System.out.println("You find a small bag of gold hidden in a hollow log, holding " + RPG.lootGP + " gold!");
        RPG.myGP += RPG.lootGP;
    }
    
    protected static void noEvent(){
        System.out.println("\n");
        System.out.println("You walk through the forest, listening to the songbirds for a while.");
        System.out.println("After a while, you head back.");
    }
    
    protected static void findCampsite(){
        System.out.println("\n");
        System.out.println("You find an abandoned campsite, and search for anything that was left behind.");
        event = RPG.gen.nextInt(5);
        if(event==0){
            System.out.println("You found a " + inv.foodInv[2] + " inside a discarded sack!");
            inv.foodNum[2] += 1;
        }else if(event==1){
            System.out.println("You found a " + inv.potionInv[1] + " in a broken box!");
            inv.potionNum[1] += 1;
        }else if(event==2){
            System.out.println("You found a " + inv.generalInv[1] + " hidden under some old rags!");
            inv.generalNum[1] += 1;
        }else if(event==3){
            RPG.lootGP = RPG.gen.nextInt(15) + 1;
            System.out.println("You found a small bag of gold, holding " + RPG.lootGP + " gold!");
            RPG.myGP += RPG.lootGP;
        }else if(event==4){
            System.out.println("You didn't manage to find anything worth taking.");
        }
    }
    
    protected static void findHerbs(){
        System.out.println("You find a small clearing with various plants growing in it.");
        event = RPG.gen.nextInt(5);
        switch(event){
            case 0:
                System.out.println("You find and pick some Heartbloom, an herb commonly used for healing!");
                inv.generalNum[5] += 1;
                break;
            case 1:
                System.out.println("You find and pick some Mageflower, an herb known for it's magical properties!");
                inv.generalNum[6] += 1;
                break;
            case 2:
                System.out.println("You find and pick some Tasty Berries!");
                inv.foodNum[3] += 1;
                break;
            case 3:
                System.out.println("You aren't able to find any special plants here.");
                break;
            case 4:
                System.out.println("You carefully collect some Venom Vine, a rather dangerous herb.");
                inv.generalNum[7] += 1;
                break;
        }
    }
    
}
