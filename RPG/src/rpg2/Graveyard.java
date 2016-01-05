
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

public class Graveyard {
    
    protected static void graveyardMenu(){
        if(stats.cryptFound == true){
            Main.zone = "Graveyard";
            Main.screen = "graveyardMenu";
            Main.output.append("Now that you've discovered the crypt, you may travel to it directly or explore randomly." + "\n");
            Main.output.append("If you explore randomly, you may still encounter the crypt." + "\n");
            Main.output.append("What would you like to do?");
            Main.output.append("\n" + "\n");
            Main.btn1.setText("Go to Crypt");
            Main.btn2.setText("Explore Graveyard");
            Main.btn3.setText("Return to Crossroads");
            Main.btn4.setText("Return to Camp");
            Main.btn5.setText(" ");
            Main.btn6.setText(" ");
        }else{
            eventSelect();
        }
    }
    
    protected static void eventSelect(){
        Main.zone = "Graveyard";
        Main.event = Main.gen.nextInt(10)+1;
        switch(Main.event){
            case 1: shamblingZombie(); break;
            case 2: skele(); break;
            case 3: freshGrave(); break;
            case 4: graverobber(); break;
            case 5: scavenger(); break;
            case 6: ancientMemorial(); break;
            case 7: Crypt.cryptEntrance(); break;
            case 8: funeral(); break;
            case 9: seeBC(); break;
            case 10: noEvent(); break;
        }
    }
    
    static void shamblingZombie(){
        Main.output.append("You hear a horrible moaning as a zombie shambles right towards you!");
        Main.output.append("\n" + "\n");
        new Enemy("shamblingZombie");
        Combat.combatMenu();
    }
    
    static void skele(){
        Main.output.append("You hear the rattling of bones approaching, and find yourself assaulted by a skeleton!");
        Main.output.append("\n" + "\n");
        new Enemy("skeleton");
        Combat.combatMenu();
    }
    
    static void freshGrave(){
        Main.output.append("You come across a fresh grave, the dirt still fairly loose." + "\n");
        if(Inv.toolNum.get(2)[0] >= 1){
            Main.zone = "Graveyard";
            Main.screen = "graveMenu";
            Main.output.append("You could easily dig up this grave with your " + Inv.toolName.get(2) + " if you wanted." + "\n");
            Main.output.append("However, you also aren't sure if you are comfortable with the idea of graverobbing." + "\n");
            Main.output.append("What would you like to do?");
            Main.output.append("\n" + "\n");
            Main.btn1.setText("Rob Grave");
            Main.btn2.setText("Walk Away");
            Main.btn3.setText(" ");
            Main.btn4.setText(" ");
            Main.btn5.setText(" ");
            Main.btn6.setText(" ");
        }else{
            Main.output.append("Perhaps if you had a shovel, you could search it for treasure...");
            Main.output.append("\n" + "\n");
        }
    }
    
    static void freshGraveResult(int choice){
         if(choice == 1){
            //rob grave
            Main.output.append("Moral or not, there's treasure to be had! You quickly set about digging up the grave." + "\n");
            Main.event = Main.gen.nextInt(5) + 1;
            switch(Main.event){
                case 1:
                    Main.output.append("You unearth a rather expensive looking casket, and quickly pry it open." + "\n");
                    Main.output.append("Inside, you find a bag with 50 gold pieces and ");
                    int gem = Main.gen.nextInt(7) + 1;
                    switch(gem){
                        case 1: //ruby
                            Main.output.append("a " + Inv.generalName.get(15) + "!");
                            Inv.generalNum.get(15)[0] += 1; break;
                        case 2: //emerald
                            Main.output.append("an " + Inv.generalName.get(16) + "!");
                            Inv.generalNum.get(16)[0] += 1; break;
                        case 3: //saphire
                            Main.output.append("a " + Inv.generalName.get(17) + "!");
                            Inv.generalNum.get(17)[0] += 1; break;
                        case 4: //diamond
                            Main.output.append("a " + Inv.generalName.get(18) + "!");
                            Inv.generalNum.get(18)[0] += 1; break;
                        case 5: //ruby
                            Main.output.append("a " + Inv.generalName.get(15) + "!");
                            Inv.generalNum.get(15)[0] += 1; break;
                        case 6: //emerald
                            Main.output.append("an " + Inv.generalName.get(16) + "!");
                            Inv.generalNum.get(16)[0] += 1; break;
                        case 7: //saphire
                            Main.output.append("a " + Inv.generalName.get(17) + "!");
                            Inv.generalNum.get(17)[0] += 1; break;
                    }
                    stats.myGP[0] += 50; stats.myGP[1] += 50;
                    Main.output.append("\n" + "\n");
                    graveMenu2();
                    break;
                case 2:
                    Main.output.append("You dig for a while, yet are unable to uncover anything except more dirt." + "\n");
                    Main.output.append("Apparenly, this grave was left empty to throw off would-be graverobbers!");
                    graveMenu2();
                    Main.output.append("\n" + "\n");
                    break;
                case 3:
                    Main.output.append("As you unearth the casket, you are startled by a loud moaning from within it!" + "\n");
                    Main.output.append("Suddenly, a Shambling Zombie forces it's way out of the casket and attacks you!");
                    Main.output.append("\n" + "\n");
                    new Enemy("shamblingZombie");
                    Combat.combatMenu();
                    break;
                case 4:
                    Main.output.append("You unearth a rather ordinary casket, and quickly pry it open." + "\n");
                    stats.lootGP = Main.gen.nextInt(16) + 30;
                    Main.output.append("Inside, you find a bag with " + stats.lootGP + " gold pieces!");
                    stats.myGP[0] += stats.lootGP; stats.myGP[1] += stats.lootGP;
                    Main.output.append("\n" + "\n");
                    graveMenu2();
                    break;
                case 5:
                    Main.output.append("You unearth a rather shoddy casket, and quickly pry it open." + "\n");
                    Main.output.append("Inside, you find a " + Inv.generalName.get(19) + "!");
                    Inv.generalNum.get(19)[0] += 1;
                    Main.output.append("\n" + "\n");
                    graveMenu2();
                    break;
            }
         }else{
            //walk away
            Main.output.append("You decide to leave the grave alone, and walk away before you change your mind." + "\n");
            Main.output.append("You feel like you made a good choice, allowing the recently departed to rest in peace." + "\n");
            Main.output.append("\n" + "\n");
            graveMenu2();
         }
    }
    
    static void graveMenu2(){
        Main.updateLabels();
        Main.zone = "Graveyard";
        Main.screen = "graveMenu2";
        if(stats.cryptFound == true){
            Main.btn1.setText("Stay in Graveyard");
            Main.btn2.setText("Return to Crossroads");
            Main.btn3.setText("Return to Camp");
            Main.btn4.setText(" ");
            Main.btn5.setText(" ");
            Main.btn6.setText(" ");
        }else{
            Main.btn1.setText("Continue Exploring");
            Main.btn2.setText("Return to Camp");
            Main.btn3.setText(" ");
            Main.btn4.setText(" ");
            Main.btn5.setText(" ");
            Main.btn6.setText(" ");
        }
    }
    
    static void graverobber(){
        Main.output.append("You hear the sounds of something shoveling the dirt behind a nearby headstone." + "\n");
        Main.output.append("When you investigate, a graverobber swings at you with his shovel!" + "\n");
        Main.output.append("\"This is my find! I'll kill you before you can lay a hand on this loot!\"");
        Main.output.append("\n" + "\n");
        new Enemy("graverobber");
        Combat.combatMenu();
    }
    
    static void scavenger(){
        Main.output.append("You hear the sound of something gnawing nearby, and then a terrifying growling!" + "\n");
        Main.output.append("Out of the fog leaps a scavenger, hungry for your flesh and blood!");
        Main.output.append("\n" + "\n");
        new Enemy("scavenger");
        Combat.combatMenu();
    }
    
    static void ancientMemorial(){
        
    }
    
    static void funeral(){
        Main.output.append("You happen across a funeral being held in the graveyard." + "\n");
        Main.output.append("You offer your condolences to the grieving family, and kindly leave them in peace.");
        Main.output.append("\n" + "\n");
    }
    
    static void seeBC(){
        Main.output.append("While exploring the graveyard, you spot a shadowy figure through the fog ahead." + "\n");
        Main.output.append("As you draw closer, you notice the figure is clothed in a dark, hooded robe." + "\n");
        Main.output.append("You stop, and the figure slowly glances over it's shoulder to look you over." + "\n");
        Main.output.append("After a moment, the figure turns and slowly walks away into the fog, as you hear a voice call back to you." + "\n");
        Main.output.append("\"Enjoy my game while you can, mortal, for you may find your end soon...\"" + "\n");
        Main.output.append("Could the rumors be true? Was that Bloodcorpse? ");
        Main.output.append("\n" + "\n");
    }
    
    static void noEvent(){
        Main.output.append("You wander through the graveyard, making your way through the eerie fog." + "\n");
        Main.output.append("You soon find yourself back at the gate to the graveyard, emptyhanded but unharmed.");
        Main.output.append("\n" + "\n");
    }
    
}
