
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
import javax.swing.filechooser.*;

public class Save {
    
    static String path;
    static File file;
    static String fileContents="";
    static String loadData = "";
    static Scanner in;
    static Scanner fileIn = null;
    static PrintWriter fileOut = null;
    static JFileChooser chooser = new JFileChooser();
    static FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", "txt");
    
    static String loadPhase = "";
    
    protected static void dataMenu(){
        Main.screen = "dataMenu";
        Main.btn1.setText("Save Game");
        Main.btn2.setText("Load Game");
        Main.btn3.setText("New Game");
        Main.btn4.setText("Nevermind");
        Main.btn5.setText(" ");
        Main.btn6.setText(" ");
    }
    
    protected static void saveGame(){
        chooser.setFileFilter(filter);
        int choice;
        choice = chooser.showSaveDialog(Main.window);
        if(choice == JFileChooser.APPROVE_OPTION){
            path = chooser.getSelectedFile().getName();
        }else{
            return;
        }
        if(path.endsWith(".txt")==false){
            path = path + ".txt";
        }
        file = new File(chooser.getCurrentDirectory(), path);
        try {
            if(file.createNewFile()==false){
                int choice2;
                choice2 = JOptionPane.showConfirmDialog(chooser, "A file named " + path + " already exists, do you wish to overwrite? (Y/N)", "File Already Exists!", JOptionPane.YES_NO_OPTION);
                if(choice2 == JOptionPane.YES_OPTION){
                    fileOut = new PrintWriter(new FileWriter(file));
                }else{
                    return;
                }
            }else{
                fileOut = new PrintWriter(new FileWriter(file));
            }
            fileOut.println("~Character");
            //save name and gender
            fileOut.println(stats.name);
            fileOut.println(stats.isFemale);
            //save level / exp
            fileOut.println(stats.level + " " + stats.lvlPoints + " " + stats.myEXP);
            //save current Gold / lifetime Gold
            fileOut.println(stats.myGP[0] + " " + stats.myGP[1]);
            //save current HP / current MP
            fileOut.println(stats.myHP[0]);
            fileOut.println(stats.myMP[0]);
            //save base stats
            fileOut.println(stats.strength[0]);
            fileOut.println(stats.body[0]);
            fileOut.println(stats.speed[0]);
            fileOut.println(stats.intel[0]);
            fileOut.println(stats.will[0]);
            //save inventory
            saveInv();
            //save buffs and effects
            saveBuffs();
            //save legacy stats
            saveLegacy();
            //save progress
            saveProgress();
            //close file
            fileOut.close();
            JOptionPane.showMessageDialog(Main.window,"Your game was successfully saved to: " + file.getName());
            dataMenu();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(Main.window,"There was an error saving your game. Please try again."
                    + "\n" + ex);
            dataMenu();
        }
    }
    
    private static void saveInv(){
        //save Equipped
        fileOut.println("~Equip");
        fileOut.println(Inv.equippedNum[0]
                + " " + Inv.equippedNum[1]
                + " " + Inv.equippedNum[2]);
        //save Armor
        fileOut.println("~Armor");
        for(int i = 0; i < Inv.armorName.size(); i ++){
                fileOut.print(Inv.armorNum.get(i)[0]);
                if(i < Inv.armorName.size() - 1){
                    fileOut.print(" ");
                }
            }
        fileOut.println();
        //save Weapons
        fileOut.println("~Weapon");
        for(int i = 0; i < Inv.weaponName.size(); i ++){
            fileOut.print(Inv.weaponNum.get(i)[0]);
            if(i < Inv.weaponName.size() - 1){
                fileOut.print(" ");
            }
        }
        fileOut.println();
        //save Offhands
        fileOut.println("~Offhand");
        for(int i = 0; i < Inv.offhandName.size(); i ++){
            fileOut.print(Inv.offhandNum.get(i)[0]);
            if(i < Inv.offhandName.size() - 1){
                fileOut.print(" ");
            }
        }
        fileOut.println();
        //save Tools
        fileOut.println("~Tool");
        for(int i = 0; i < Inv.toolName.size(); i ++){
            fileOut.print(Inv.toolNum.get(i)[0]);
            if(i < Inv.toolName.size() - 1){
                fileOut.print(" ");
            }
        }
        fileOut.println();
        //save Food
        fileOut.println("~Food");
        for(int i = 0; i < Inv.foodName.size(); i ++){
            fileOut.print(Inv.foodNum.get(i)[0]);
            if(i < Inv.foodName.size() - 1){
                fileOut.print(" ");
            }
        }
        fileOut.println();
        //save Potions
        fileOut.println("~Potion");
        for(int i = 0; i < Inv.potionName.size(); i ++){
            fileOut.print(Inv.potionNum.get(i)[0]);
            if(i < Inv.potionName.size() - 1){
                fileOut.print(" ");
            }
        }
        fileOut.println();
        //save General
        fileOut.println("~General");
        for(int i = 0; i < Inv.generalName.size(); i ++){
            fileOut.print(Inv.generalNum.get(i)[0]);
            if(i < Inv.generalName.size() - 1){
                fileOut.print(" ");
            }
        }
        fileOut.println();
    }
    
    static void saveBuffs(){
        //save Buffs
        fileOut.println("~Buffs");
        for(int i = 0; i < Effects.myBuffData.size(); i++){
            fileOut.print(Effects.myBuffData.get(i)[1]);
            if(i < Effects.myBuffData.size() - 1){
                fileOut.print(" ");
            }
        }
        fileOut.println();
        //save Effects
        fileOut.println("~Effects");
        for(int i = 0; i < Effects.myEffectData.size(); i++){
            fileOut.print(Effects.myEffectData.get(i)[13]);
            if(i < Effects.myEffectData.size() - 1){
                fileOut.print(" ");
            }
        }
        fileOut.println();
    }
    
    static void saveLegacy(){
        fileOut.println("~Legacy");
        fileOut.println(stats.myKills);
        fileOut.println(stats.timesFled);
        fileOut.println(stats.tollsPaid);
        fileOut.println(stats.wagersPlaced);
        fileOut.println(stats.timesRezzed);
        fileOut.println(stats.gravesRobbed);
        fileOut.println(stats.cryptDepth);
    }
    
    static void saveProgress(){
        fileOut.println("~Progress");
        fileOut.println(stats.bridgeFound);
        fileOut.println(stats.cryptFound);
    }
    
    static void loadGame(){
        chooser.setFileFilter(filter);
        int choice;
        choice = chooser.showOpenDialog(null);
        if(choice == JFileChooser.APPROVE_OPTION){
            file = chooser.getSelectedFile();
        }else{
            return;
        }
        try {
            fileIn = new Scanner(file);
            loadPhase = "CHARACTER";
            if(fileIn.nextLine().equals("~Character")){
                //load name and gender
                loadPhase = "BASIC INFO";
                stats.name = fileIn.nextLine();
                stats.isFemale = fileIn.nextBoolean(); fileIn.nextLine();
                //load level and experience
                loadPhase = "LEVEL / EXP";
                stats.level = Integer.parseInt(fileIn.next());
                stats.lvlPoints = Integer.parseInt(fileIn.next());
                stats.myEXP = Integer.parseInt(fileIn.next());
                fileIn.nextLine();
                //load current Gold / lifetime Gold
                loadPhase = "GOLD";
                stats.myGP[0] = Integer.parseInt(fileIn.next());
                stats.myGP[1] = Integer.parseInt(fileIn.next());
                fileIn.nextLine();
                //load current HP / current MP
                loadPhase = "CURRENT HP / MP";
                stats.myHP[0] = Integer.parseInt(fileIn.nextLine());
                stats.myMP[0] = Integer.parseInt(fileIn.nextLine());
                //load base stats
                loadPhase = "STATS";
                stats.strength[0] = Integer.parseInt(fileIn.nextLine());
                stats.body[0] = Integer.parseInt(fileIn.nextLine());
                stats.speed[0] = Integer.parseInt(fileIn.nextLine());
                stats.intel[0] = Integer.parseInt(fileIn.nextLine());
                stats.will[0] = Integer.parseInt(fileIn.nextLine());
            }else{
                throw new Exception("Invalid Formatting");
            }
            //load inventory
            loadInv();
            //load buffs and effects
            loadBuffs();
            //load legacy stats
            loadLegacy();
            //load progress
            loadProgress();
            //equip gear
            Inv.loadEquipped();
            //update stats and effects
            Effects.playerBuffUpdate(true);
            stats.updateStats();
            //load complete
            JOptionPane.showMessageDialog(Main.window, stats.name + " was loaded successfully!");
            //return to camp
            Camp.campMenu();
        } catch (Exception ex) {
            if(ex.getMessage()=="Invalid Formatting"){
                //Bad data formatting, abort load
                JOptionPane.showMessageDialog(Main.window, "The formatting of the file is incorrect. Loading has been aborted."
                        + "\n" + "Encountered during loading phase: " + loadPhase);
            }else if(ex.getMessage()=="Input Mismatch"){
                //Bad or missing data value, abort load
                JOptionPane.showMessageDialog(Main.window, "There was an invalid value encountered in the file. Loading has been aborted."
                        + "\n" + "Encountered during loading phase: " + loadPhase);
            }else{
                //other error, abort load
                JOptionPane.showMessageDialog(Main.window, "There was an error loading the save. Check if the file " + file.getName() + " exists and try again."
                        + "\n" + "Error: " + ex);
            }
            return;
        }
    }
    
    static void loadInv() throws Exception{
        try{
            loadPhase = "EQUIP";
            if(fileIn.nextLine().equals("~Equip")){
                //load equipped
                Inv.equippedNum[0] = fileIn.nextInt();
                Inv.equippedNum[1] = fileIn.nextInt();
                Inv.equippedNum[2] = fileIn.nextInt();
                fileIn.nextLine();
            }else{
                throw new Exception("Invalid Formatting");
            }
            loadPhase = "ARMOR";
            if(fileIn.nextLine().equals("~Armor")){
                //load armor
                for(int i = 0; i < Inv.armorName.size(); i++){
                    Inv.armorNum.get(i)[0] = fileIn.nextInt();
                }
                fileIn.nextLine();
            }else{
                throw new Exception("Invalid Formatting");
            }
            loadPhase = "WEAPON";
            if(fileIn.nextLine().equals("~Weapon")){
                //load weapons
                for(int i = 0; i < Inv.weaponName.size(); i++){
                    Inv.weaponNum.get(i)[0] = fileIn.nextInt();
                }
                fileIn.nextLine();
            }else{
                throw new Exception("Invalid Formatting");
            }
            loadPhase = "OFFHAND";
            if(fileIn.nextLine().equals("~Offhand")){
                //load offhands
                for(int i = 0; i < Inv.offhandName.size(); i++){
                    Inv.offhandNum.get(i)[0] = fileIn.nextInt();
                }
                fileIn.nextLine();
            }else{
                throw new Exception("Invalid Formatting");
            }
            loadPhase = "TOOL";
            if(fileIn.nextLine().equals("~Tool")){
                //load tools
                for(int i = 0; i < Inv.toolName.size(); i++){
                    Inv.toolNum.get(i)[0] = fileIn.nextInt();
                }
            fileIn.nextLine();
            }else{
                throw new Exception("Invalid Formatting");
            }
            loadPhase = "FOOD";
            if(fileIn.nextLine().equals("~Food")){
                //load food
                for(int i = 0; i < Inv.foodName.size(); i++){
                    Inv.foodNum.get(i)[0] = fileIn.nextInt();
                }
                fileIn.nextLine();
            }else{
                throw new Exception("Invalid Formatting");
            }
            loadPhase = "POTIONS";
            if(fileIn.nextLine().equals("~Potion")){
                //load potions
                for(int i = 0; i < Inv.potionName.size(); i++){
                    Inv.potionNum.get(i)[0] = fileIn.nextInt();
                }
                fileIn.nextLine();
            }else{
                throw new Exception("Invalid Formatting");
            }
            loadPhase = "GENERAL";
            if(fileIn.nextLine().equals("~General")){
                //load general
                for(int i = 0; i < Inv.generalName.size(); i++){
                    Inv.generalNum.get(i)[0] = fileIn.nextInt();
                }
                fileIn.nextLine();
            }else{
                throw new Exception("Invalid Formatting");
            }
        }catch(Exception ex){
            if(ex.getMessage()=="Invalid Formatting"){
                //JOptionPane.showMessageDialog(Main.window, "The formatting of the file is incorrect.");
                throw new Exception("Invalid Formatting");
            }else{
                //Data type mismatch, throw exception to be caught in loadGame()
                throw new InputMismatchException("Input Mismatch");
            }
        }
    }
    
    static void loadBuffs()throws Exception{
        try{
            loadPhase = "BUFFS";
            if(fileIn.nextLine().equals("~Buffs")){
                loadData = fileIn.nextLine();
                in = new Scanner(loadData);
                for(int i = 0; i < Effects.myBuffData.size(); i++){
                    if(in.hasNext()){
                        Effects.myBuffData.get(i)[1] = in.nextInt();
                    }else{
                        Effects.myBuffData.get(i)[1] = 0;
                    }
                }
            }else{
                throw new Exception("Invalid Formatting");
            }
            loadPhase = "EFFECTS";
            if(fileIn.nextLine().equals("~Effects")){
                loadData = fileIn.nextLine();
                in = new Scanner(loadData);
                for(int i = 0; i < Effects.myEffectData.size(); i++){
                    if(in.hasNext() == true){
                        Effects.myEffectData.get(i)[13] = in.nextBoolean();
                    }else{
                        Effects.myEffectData.get(i)[13] = false;
                    }
                }
            }else{
                throw new Exception("Invalid Formatting");
            }
        }catch(Exception ex){
            if(ex.getMessage()=="Invalid Formatting"){
                //Bad data formatting, throw exception to be caught in loadGame()
                throw new Exception("Invalid Formatting");
            }else{
                //Data type mismatch, throw exception to be caught in loadGame()
                throw new Exception("Input Mismatch");
            }
        }
    }
    
    static void loadLegacy() throws Exception{
        try{
            loadPhase = "LEGACY";
            if(fileIn.nextLine().equals("~Legacy")){
                stats.myKills = Integer.parseInt(fileIn.nextLine());
                stats.timesFled = Integer.parseInt(fileIn.nextLine());
                stats.tollsPaid = Integer.parseInt(fileIn.nextLine());
                stats.wagersPlaced = Integer.parseInt(fileIn.nextLine());
                //added in v1.4
                stats.timesRezzed = Integer.parseInt(fileIn.nextLine());
                stats.gravesRobbed = Integer.parseInt(fileIn.nextLine());
                stats.cryptDepth = Integer.parseInt(fileIn.nextLine());
            }else{
                throw new Exception("Invalid Formatting");
            }
        }catch(Exception ex){
            if(ex.getMessage()=="Invalid Formatting"){
                //Bad data formatting, throw exception to be caught in loadGame()
                throw new Exception("Invalid Formatting");
            }else{
                //Data type mismatch, throw exception to be caught in loadGame()
                throw new Exception("Input Mismatch");
            }
        }
    }
    
    static void loadProgress()throws Exception{
        try{
            loadPhase = "PROGRESS";
            if(fileIn.nextLine().equals("~Progress")){
                stats.bridgeFound = fileIn.nextBoolean();
                stats.cryptFound = fileIn.nextBoolean();
                fileIn.nextLine();
            }else{
                throw new Exception("Invalid Formatting");
            }
        }catch(Exception ex){
            if(ex.getMessage()=="Invalid Formatting"){
                //Bad data formatting, throw exception to be caught in loadGame()
                throw new Exception("Invalid Formatting");
            }else{
                //Data type mismatch, throw exception to be caught in loadGame()
                throw new Exception("Input Mismatch");
            }
        }
    }
    
    static void newGameCheck(){
        int confirm = JOptionPane.showConfirmDialog(Main.window, "Are you sure you wish to start a new game?" + "\n" + "This character will be lost!", null, JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.YES_OPTION){
            Main.mainMenu();
            Main.newGame();
        }
    }
    
}
