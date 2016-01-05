
package rpg2;

/**
 * @author Bloodcorpse
 * Version 1.3
 */

import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Town {
    
    protected static void eventProcChance(){
        Main.zone = "Town";
        Main.event = Main.gen.nextInt(50);
        if(Main.event == 25){
            muggerEvent();
        }else{
            townMenu();
        }
    }
    
    protected static void townMenu(){
        Main.zone = "Town";
        Main.output.setText("You stand in the plaza at the Town Center." + "\n");
        Main.output.append("You spot numerous places of interest you could visit." + "\n");
        Main.output.append("Where would you like to go?" + "\n");
        Main.screen = "townMenu";
        Main.btn1.setText("Shops");
        Main.btn2.setText("Chapel");
        Main.btn3.setText("Tavern");
        Main.btn4.setText("Explore Sewers");
        Main.btn5.setText("Return to Camp");
        Main.btn6.setText(" ");
    }
    
    protected static void chapelMenu(){
        Main.output.setText("The chapel is quiet as you enter, in reverence of the Divines." + "\n");
        Main.output.append("The altar of the Divines stands before you, awaiting the prayers of the faithful." + "\n");
        Main.output.append("The Chapel Healer roams about, ready to heal your ailments for a 25 Gold donation to the Chapel." + "\n");
        Main.screen = "chapelMenu";
        Main.btn1.setText("Pray for Blessing");
        Main.btn2.setText("Cure Ailments");
        Main.btn3.setText("Leave");
        Main.btn4.setText(" ");
        Main.btn5.setText(" ");
        Main.btn6.setText(" ");
    }
    
    protected static void chapelPray(){
        Main.output.append("\n");
        Main.output.append("You pray at the altar for the Divines to grant you their blessings on your adventures." + "\n");
        int prayer = Main.gen.nextInt(20);
        switch(prayer){
            case 2:     //Divine Protection
                Main.output.append("You are briefly surrounded by a faint light as you pray. As it fades, you feel protected by a Divine!" + "\n");
                Effects.myBuffData.get(1)[1] = Effects.myBuffData.get(1)[2];
                break;
            case 4:     //Divine Might
                Main.output.append("You are briefly surrounded by a faint light as you pray. As it fades, a Divine blesses your Strength!." + "\n");
                Effects.myEffectData.get(0)[14] = true;
                Effects.myEffectData.get(1)[14] = false;
                Effects.myEffectData.get(2)[14] = false;
                break;
            case 6:     //Divine Protection
                Main.output.append("You are briefly surrounded by a faint light as you pray. As it fades, you feel protected by a Divine!" + "\n");
                Effects.myBuffData.get(1)[1] = Effects.myBuffData.get(1)[2];
                break;
            case 7:     //Divine Protection
                Main.output.append("You are briefly surrounded by a faint light as you pray. As it fades, you feel protected by a Divine!" + "\n");
                Effects.myBuffData.get(1)[1] = Effects.myBuffData.get(1)[2];
                break;
            case 9:     //Divine Insight
                Main.output.append("You are briefly surrounded by a faint light as you pray. As it fades, a Divine blesses your Intel!" + "\n");
                Effects.myEffectData.get(0)[14] = false;
                Effects.myEffectData.get(1)[14] = true;
                Effects.myEffectData.get(2)[14] = false;
                break;
            case 13:    //Divine Protection
                Main.output.append("You are briefly surrounded by a faint light as you pray. As it fades, you feel protected by a Divine!" + "\n");
                Effects.myBuffData.get(1)[1] = Effects.myBuffData.get(1)[2];
                break;
            case 14:     //Divine Haste
                Main.output.append("You are briefly surrounded by a faint light as you pray. As it fades, a Divine blesses your Speed!" + "\n");
                Effects.myEffectData.get(0)[14] = false;
                Effects.myEffectData.get(1)[14] = false;
                Effects.myEffectData.get(2)[14] = true;
                break;
            case 17:    //Divine Protection
                Main.output.append("You are briefly surrounded by a faint light as you pray. As it fades, you feel protected by a Divine!" + "\n");
                Effects.myBuffData.get(1)[1] = Effects.myBuffData.get(1)[2];
                break;
            case 19:    //Divine Cleansing
                Main.output.append("You are briefly surrounded by a faint light as you pray. As it fades, your ailments are cured by a Divine!" + "\n");
                Effects.cureAll();
                break;
            default:    //No Effect
                Main.output.append("You pray, but it seems as though the Divines aren't listening. They must be busy dealing with business elsewhere." + "\n");
                break;
        }
        Effects.playerBuffUpdate(true);
    }
    
    protected static void chapelCure(){
        Main.output.append("\n");
        if(stats.myGP[0] >= 25){
            stats.myGP[0] -= 25;
            Main.output.append("You pay the Chapel Healer, who places her hands on you." + "\n");
            Main.output.append("A faint glow surrounds her hands as she cleanses your afflictions with holy powers." + "\n");
            Effects.cureAll();
        }else{
            Main.output.append("You can't afford the services of the Chapel Healer." + "\n");
        }
    }
    
    protected static void tavernMenu(){
        Main.output.setText("The tavern is fairly crowded as you enter." + "\n");
        Main.output.append("You spy an open seat at one of the tables where you could order a drink." + "\n");
        Main.output.append("You also could squeeze in at the bar to order a round, which would cost about 75 GP." + "\n");
        Main.output.append("In the corner, you also notice some men placing bets on a dice game." + "\n");
        Main.screen = "tavernMenu";
        Main.btn1.setText("Order a Drink");
        Main.btn2.setText("Buy a Round");
        Main.btn3.setText("Gamble");
        Main.btn4.setText("Leave");
        Main.btn5.setText(" ");
        Main.btn6.setText(" ");
    }
    
    protected static void orderDrinkMenu(){
        Main.screen="orderDrinkMenu";
        Main.output.setText("You take a seat at an unoccupied table and flag down one of the barmaids." + "\n");
        if(stats.isFemale=false){
            Main.output.append("\"Hey there sugar, what can I get for you?\" she asks with a smile.");
        }else{
            Main.output.append("\"Hey there sweetie, looking for a drink?\" she asks with a smile.");
        }
        Main.output.append("\"We have Cheap Wine, Mead, Cider, and our house specialty: Dragonsbreath Ale.\"");
        Main.output.append("Cheap wine costs " + Inv.foodNum.get(5)[2] + " GP." + "\n");
        Main.output.append("Mead costs " + Inv.foodNum.get(6)[2] + " GP." + "\n");
        Main.output.append("Cider costs " + Inv.foodNum.get(7)[2] + " GP." + "\n");
        Main.output.append("Dragonsbreath Ale costs " + Inv.foodNum.get(8)[2] + " GP." + "\n");
        Main.output.append("You could also order a round of drinks for 75 GP." + "\n");
        Main.btn1.setText("Cheap Wine");
        Main.btn2.setText("Mead");
        Main.btn3.setText("Cider");
        Main.btn4.setText("Dragonsbreath Ale");
        Main.btn5.setText("Order A Round");
        Main.btn6.setText("No Thanks");
    }
    
    protected static void orderDrink(int drinkChoice){
        switch(drinkChoice){
            case 1: //Cheap Wine
                if(stats.myGP[0] >= Inv.foodNum.get(5)[2]){
                    Main.output.append("You purchase an " + Inv.foodName.get(5) + "." + "\n");
                    stats.myGP[0] -= Inv.foodNum.get(5)[2];
                    Inv.foodNum.get(5)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.foodName.get(5) + ": You can't afford this." + "\n");
                } break;
            case 2: //Mead
                if(stats.myGP[0] >= Inv.foodNum.get(6)[2]){
                    Main.output.append("You purchase an " + Inv.foodName.get(6) + "." + "\n");
                    stats.myGP[0] -= Inv.foodNum.get(6)[2];
                    Inv.foodNum.get(6)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.foodName.get(6) + ": You can't afford this." + "\n");
                } break;
            case 3: //Cider
                if(stats.myGP[0] >= Inv.foodNum.get(7)[2]){
                    Main.output.append("You purchase an " + Inv.foodName.get(7) + "." + "\n");
                    stats.myGP[0] -= Inv.foodNum.get(7)[2];
                    Inv.foodNum.get(7)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.foodName.get(7) + ": You can't afford this." + "\n");
                } break;
            case 4: //Dragonsbreath Ale
                if(stats.myGP[0] >= Inv.foodNum.get(8)[2]){
                    Main.output.append("You purchase an " + Inv.foodName.get(8) + "." + "\n");
                    stats.myGP[0] -= Inv.foodNum.get(8)[2];
                    Inv.foodNum.get(8)[0] += 1;
                    Main.updateLabels();
                }else{
                    Main.output.append(Inv.foodName.get(8) + ": You can't afford this." + "\n");
                } break;
        }
        Main.updateLabels();
    }
    
    protected static void orderRound(){
        if(stats.myGP[0] >= 75){
                    stats.myGP[0] -= 75;
                    Main.output.append("You order a round of drinks for yourself and several nearby patrons, and pay the 75 GP." + "\n");
                    Main.output.append("You raise your mug in a toast with your fellow drinkers, and then take a big swig..." + "\n");
                    Main.event = Main.gen.nextInt(4) + 1;
                    switch(Main.event){
                        case 1: //Blackout to Camp
                            Camp.specialCampMenu(1);
                            break;
                        case 2: //Bar Brawl
                            Main.output.setText("While enjoying yourself at the Tavern over a round of drinks, a few patrons begin to get rowdy." + "\n");
                            Main.output.append("A number of patrons begin to bicker and fight, and a bar brawl quickly breaks out." + "\n");
                            Main.output.append("A Drunken Patron smashes a bottle and threatens you with it in their drunken rage!");
                            Main.output.append("\n" + "\n");
                            new Enemy("drunkenPatron");
                            Combat.combatMenu();
                            break;
                        case 3: //Enjoy Self
                            Main.output.setText("You enjoy your drink, as well as the ones that your new friends order for you." + "\n");
                            Main.output.append("Everyone in the Tavern seems exceptionally happy, and a number of patrons begin to sing drunkenly." + "\n");
                            Main.output.append("Eventually the party winds down, and you excuse yourself politely. You thoroughly enjoyed yourself though!");
                            Main.output.append("\n" + "\n");
                            break;
                        case 4: //Blackout to Sewers
                            Sewers.specialSewerMenu(1);
                            break;
                    }
                }else{
                    Main.output.append("You don't have enough to pay for a round of drinks." + "\n");
                }
        Main.updateLabels();
    }
    
    protected static void diceGameMenu(){
        Main.screen = "diceGameMenu";
        Main.output.setText("Approaching the table, the men invite you to join their game." + "\n");
        Main.output.append("They explain the rules of their dice game, and they seem rather simple:" + "\n");
        Main.output.append("Once you've place a bet, they will roll the dice. Your payout depends on the result of the roll." + "\n");
        Main.output.append("If your roll is double 6's, you win 2.5x your wager!" + "\n");
        Main.output.append("If your roll is doubles, you win 1.5x your wager." + "\n");
        Main.output.append("If your roll adds up to 6 exactly, you win back your wager." + "\n");
        Main.output.append("Otherwise, you lose your wager." + "\n");
        Main.output.append("Do you want to place a wager and roll the dice?");
        Main.output.append("\n" + "\n");
        Main.btn1.setText("Wager 10 GP");
        Main.btn2.setText("Wager 50 GP");
        Main.btn3.setText("Wager 100 GP");
        Main.btn4.setText("Nevermind");
        Main.btn5.setText(" ");
        Main.btn6.setText(" ");
    }
    
    protected static void playDiceGame(int wager){
        if(stats.myGP[0] >= wager){
            Main.output.append("You place your wager of " + wager + " on the table, and they roll the dice." + "\n");
            stats.myGP[0] -= wager;
            stats.wagersPlaced += 1;
            int dice1, dice2, sum;
            dice1 = Main.gen.nextInt(6)+1;
            dice2 = Main.gen.nextInt(6)+1;
            sum = dice1 + dice2;
            Main.output.append("You roll " + dice1 + " and " + dice2 + " for a total of " + sum + "." + "\n");
            if(dice1==dice2 && sum==12){
                stats.lootGP = (int)(wager * 2.5);
                Main.output.append("You rolled double " + dice1 + "'s! You win " + stats.lootGP + "!");
                stats.myGP[0] += stats.lootGP; stats.myGP[1] += stats.lootGP;
            }else if(dice1 == dice2 && sum != 12){
                stats.lootGP = (int)(wager * 1.5);
                Main.output.append("You rolled double " + dice1 + "'s! You win " + stats.lootGP + "!");
                stats.myGP[0] += stats.lootGP; stats.myGP[1] += stats.lootGP;
            }else if(sum==6){
                stats.lootGP = (int)(wager);
                Main.output.append("You rolled a total of " + sum + "! You win " + stats.lootGP + "!");
                stats.myGP[0] += stats.lootGP; stats.myGP[1] += stats.lootGP;
            }else{
                Main.output.append("You lost your wager. Better luck next time!");
            }
        }else{
            Main.output.append("You don't have that much to wager!");
        }
        Main.updateLabels();
        Main.output.append("\n" + "\n");
    }
    
    protected static void muggerEvent(){
        Main.output.setText("A mugger steps out from an alley and approaches you with his knife drawn!" + "\n");
        Main.output.append("\"Hand over 35 gold and no one gets hurt!\" he threatens." + "\n");
        Main.output.append("What do you do?");
        Main.screen = "muggerMenu";
        Main.btn1.setText("Comply");
        Main.btn2.setText("Resist");
        Main.btn3.setText("Talk");
        Main.btn4.setText(" ");
        Main.btn5.setText(" ");
        Main.btn6.setText(" ");
    }
    
    protected static void muggerEvent(int choice){
        Main.output.append("\n" + "\n");
        switch(choice){
            case 1: //Comply
                if(stats.myGP[0] >= 35){
                    Main.output.append("You surrender 35 gold to the mugger and he slinks back into the alley.");
                    stats.myGP[0] -= 35;
                }else if(stats.myGP[0] == 0){
                    Main.output.append("You admit to the mugger you have no money, and show him your empty coinpurse as proof." + "\n");
                    Main.output.append("The mugger stares at you, struggling to keep a straight face. He bursts out laughing, pointing at you." + "\n");
                    Main.output.append("\"HA HA HA! Maybe YOU should be the one doing the mugging, eh? Ah, hilarious...\"" + "\n");
                    Main.output.append("The mugger wanders off, still laughing at you.");
                }else{
                    Main.output.append("You surrender " + stats.myGP[0] + " gold to the mugger." + "\n");
                    Main.output.append("You explain that was the last of your gold, and show him your empty coinpurse." + " \n");
                    Main.output.append("Grumbling, the mugger wanders off. \"Leave it to me to find the poor marks...\"");
                    stats.myGP[0] = 0;
                }
                Main.updateLabels();
                Main.screen = "muggerMenu2";
                Main.btn1.setText("Continue to Town.");
                Main.btn2.setText(" ");
                Main.btn3.setText(" ");
                Main.btn4.setText(" ");
                Main.btn5.setText(" ");
                Main.btn6.setText(" ");
                break;
            case 2: //Resist
                new Enemy("mugger");
                Combat.combatMenu();
                break;
            case 3: //Talk
                int i;
                if(stats.myGP[0] >= 15){
                    i = Main.gen.nextInt(4);
                }else{
                    i = Main.gen.nextInt(3);
                }
                switch(i){
                    case 0: //Persuade
                        Main.output.append("You attempt to persuade the mugger to let you go, and encourage him to reconsider his life of crime." + "\n");
                        Main.output.append("He thanks you for your encouragement and heads home.");
                        Main.screen = "muggerMenu2";
                        Main.btn1.setText("Continue to Town.");
                        Main.btn2.setText(" ");
                        Main.btn3.setText(" ");
                        Main.btn4.setText(" ");
                        Main.btn5.setText(" ");
                        Main.btn6.setText(" ");
                        break;
                    case 1: //Failed Persuade
                        Main.output.append("You attempt to persuade the mugger to let you go, and encourage him to reconsider his life of crime." + "\n");
                        Main.output.append("\"OH SHUT UP ALREADY! You sound like my ex-wife! That's it, you asked for it!\"" + "\n");
                        Main.output.append("The mugger interrupts you, clearly angry. Suddenly, he attacks!");
                        Main.output.append("\n" + "\n");
                        new Enemy("mugger");
                        Combat.combatMenu();
                        break;
                    case 2: //Dialog
                        Main.output.append("You ask the mugger why he would want to live this kind of life." + "\n");
                        Main.output.append("He shrugs before responding. \"There ain't no rest for the wicked, money don't grow on trees.\"" + "\n");
                        Main.output.append("\"I've got bills to pay, I got mouths to feed. Ain't nothing in this world for free.\"");
                        break;
                    case 3: //Reduced Price
                        Main.output.append("You attempt to persuade the mugger to accept a smaller amount."  + "\n");
                        Main.output.append("Eventually, you settle on handing over 15 gold, and he lets you go.");
                        stats.myGP[0] -= 15;
                        Main.updateLabels();
                        Main.screen = "muggerMenu2";
                        Main.btn1.setText("Continue to Town.");
                        Main.btn2.setText(" ");
                        Main.btn3.setText(" ");
                        Main.btn4.setText(" ");
                        Main.btn5.setText(" ");
                        Main.btn6.setText(" ");
                        break;
                }
        }
    }
    
}
