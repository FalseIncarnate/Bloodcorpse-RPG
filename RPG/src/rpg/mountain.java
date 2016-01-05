package rpg;

/**
 * @author Bloodcorpse
 */
import java.util.*;

public class mountain {
    static String option;
    static int event;
    static inventory inv;
    
    protected static void findOre(){
        System.out.println("\n");
        System.out.println("You find what appears to be a rich ore vein!");
        if(inv.toolNum[1]>0){
            System.out.println("You attempt to mine something from the vein...");
            event = RPG.gen.nextInt(4);
            if(event==0){
                System.out.println("You accidentally pulverize the ore beyond use, and leave empty-handed.");
            }else if(event==1){
                System.out.println("You manage to extract some " + inv.generalInv[8] + " from the vein.");
                inv.generalInv[8] += 1;
            }else if(event==2){
                System.out.println("You find some " + inv.generalInv[10] + "... They might be worth something?");
                inv.generalNum[10] += 1;
            }else if(event==3){
                System.out.println("You find a rare " + inv.generalInv[9] + "!");
                inv.generalNum[9] += 1;
            }
        }else{
            System.out.println("You don't have a " + inv.toolInv[1] + " to make use of this find.");
        }
    }
    
    protected static void crazyHermit(){
        System.out.println("\n");
        System.out.println("You encounter a small cave in the mountainside.");
        System.out.println("Upon further investigation, you find it houses a hermit! He seems rather off though...");
        event = RPG.gen.nextInt(6);
        switch(event){
            case 0:
                System.out.println("The hermit shrieks that you are 'commie scum' and attacks you in his rage!");
                new mob("hermit");
                RPG.mobFight();
                break;
            case 1:
                System.out.println("The hermit lectures you about the importance of capitalism and gives you 50 gold!");
                RPG.myGP += 50;
                break;
            case 2:
                System.out.println("The hermit shakes his fist while meditating. You decide not to interrupt and leave.");
                break;
            case 3:
                System.out.println("The hermit mutters about a 'Techno-Viking'. You have no idea what he is talking about.");
                break;
            case 4:
                System.out.println("The hermit mutters about a 'Scene Kid'. You have no idea what he is talking about.");
                break;
            case 5:
                System.out.println("The hermit mutters about a 'Dubya'. You have no idea what he is talking about.");
                break;
        }
    }
    
    protected static void yeti(){
        System.out.println("\n");
        System.out.println("You encounter a fearsome Yeti while exploring the snow-capped mountains.");
        new mob("yeti");
        RPG.mobFight();
    }
    
    protected static void landslide(){
        System.out.println("\n");
        System.out.println("While hiking through the mountain passes, you hear a growing rumble from high above you.");
        System.out.println("You scramble back just in time to avoid getting buried beneath the landslide that pours over the cliff.");
        System.out.println("You decide to head back as the landslide continues to grow.");
    }
    
}
