package rpg;

/**
 * @author Bloodcorpse
 */
import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class save {
    static Boolean fromMenu = false;
    static String path;
    static File file;
    static String fileContents="";
    static Scanner in = new Scanner(System.in);
    static Scanner fileIn = null;
    static PrintWriter fileOut = null;
    static JFileChooser chooser = new JFileChooser();
    static FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file", "txt");
    static inventory inv;
    
    protected static void menu(){
        fromMenu = true;
        System.out.println("\n");
        System.out.println("What would you like to do?");
        System.out.println(" 1. Save Game       2. Load Game");
        System.out.println(" 3. Return to Game");
        String response = in.nextLine();
        if(response.equals("1")){
            saveGame();
        }else if(response.equals("2")){
            loadGame();
        }else if(response.equals("3")){
            RPG.camp();
        }else{
            menu();
        }
    }
    
    protected static void loadGame(){
        chooser.setFileFilter(filter);
        int choice;
        choice = chooser.showOpenDialog(null);
        if(choice == JFileChooser.APPROVE_OPTION){
            file = chooser.getSelectedFile();
        }else{
            if(fromMenu == true){
                menu();
            }else{
                RPG.mainMenu();
            }
        }
        try {
            fileIn = new Scanner(file);
            RPG.name = fileIn.nextLine();
            RPG.lvl = Integer.parseInt(fileIn.nextLine());
            RPG.myXP = Integer.parseInt(fileIn.nextLine());
            RPG.myGP = Integer.parseInt(fileIn.nextLine());
            RPG.curHP = Integer.parseInt(fileIn.nextLine());
            RPG.curMP = Integer.parseInt(fileIn.nextLine());
            RPG.str = Integer.parseInt(fileIn.nextLine());
            RPG.body = Integer.parseInt(fileIn.nextLine());
            RPG.speed = Integer.parseInt(fileIn.nextLine());
            RPG.intel = Integer.parseInt(fileIn.nextLine());
            RPG.will = Integer.parseInt(fileIn.nextLine());
            loadInv();
            loadBuffs();
            System.out.println("\n");
            System.out.println(RPG.name + " was loaded successfully!");
            buff.startBuff();
            RPG.updateStats();
            RPG.camp();
        } catch (FileNotFoundException ex) {
            System.out.println("There was an error loading the save. Check if the file " + file.getName() + " exists and try again.");
            loadGame();
        }
    }
    
    protected static void saveGame(){
        chooser.setFileFilter(filter);
        int choice;
        choice = chooser.showSaveDialog(null);
        if(choice == JFileChooser.APPROVE_OPTION){
            path = chooser.getSelectedFile().getName();
        }else{
            menu();
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
                    menu();
                }
            }else{
                fileOut = new PrintWriter(new FileWriter(file));
            }
            fileOut.println(RPG.name);
            fileOut.println(RPG.lvl);
            fileOut.println(RPG.myXP);
            fileOut.println(RPG.myGP);
            fileOut.println(RPG.curHP);
            fileOut.println(RPG.curMP);
            fileOut.println(RPG.str);
            fileOut.println(RPG.body);
            fileOut.println(RPG.speed);
            fileOut.println(RPG.intel);
            fileOut.println(RPG.will);
            saveInv();
            saveBuffs();
            fileOut.close();
            System.out.println("Your game was successfully saved to: " + file.getName());
            menu();
        } catch (IOException ex) {
            System.out.println("There was an error saving your game. Please try again.");
            saveGame();
        }
    }
    
    private static void saveInv(){
        for(int i = 0; i < inv.armorNum.length; i ++){
                fileOut.print(inv.armorNum[i]);
                if(i<inv.armorNum.length - 1){
                    fileOut.print(" ");
                }
            }
            fileOut.println();
            for(int i = 0; i < inv.weaponNum.length; i ++){
                fileOut.print(inv.weaponNum[i]);
                if(i<inv.weaponNum.length - 1){
                    fileOut.print(" ");
                }
            }
            fileOut.println();
            for(int i = 0; i < inv.offhandNum.length; i ++){
                fileOut.print(inv.offhandNum[i]);
                if(i<inv.offhandNum.length - 1){
                    fileOut.print(" ");
                }
            }
            fileOut.println();
            for(int i = 0; i < inv.toolNum.length; i ++){
                fileOut.print(inv.toolNum[i]);
                if(i<inv.toolNum.length - 1){
                    fileOut.print(" ");
                }
            }
            fileOut.println();
            for(int i = 0; i < inv.foodNum.length; i ++){
                fileOut.print(inv.foodNum[i]);
                if(i<inv.foodNum.length - 1){
                    fileOut.print(" ");
                }
            }
            fileOut.println();
            for(int i = 0; i < inv.potionNum.length; i ++){
                fileOut.print(inv.potionNum[i]);
                if(i<inv.potionNum.length - 1){
                    fileOut.print(" ");
                }
            }
            fileOut.println();
            for(int i = 0; i < inv.generalNum.length; i ++){
                fileOut.print(inv.generalNum[i]);
                if(i<inv.generalNum.length - 1){
                    fileOut.print(" ");
                }
            }
            fileOut.println();
    }
    
    private static void saveBuffs(){
        for(int i = 0; i < 50; i++){
                fileOut.print(buff.buffDur[i][1]);
                if(i<49){
                    fileOut.print(" ");
                }
            }
            fileOut.println();
            for(int i = 0; i < 50; i++){
                if(buff.effectActive[i] == true){
                    fileOut.print("T");
                }else{
                    fileOut.print("F");
                }
                if(i<49){
                    fileOut.print(" ");
                }
            }
            fileOut.println();
    }
    
    private static void loadInv(){
        for(int i = 0; i < inv.armorNum.length; i ++){
                inv.armorNum[i] = fileIn.nextInt();
            }
        for(int i = 0; i < inv.weaponNum.length; i ++){
            inv.weaponNum[i] = fileIn.nextInt();
        }
        for(int i = 0; i < inv.offhandNum.length; i ++){
            inv.offhandNum[i] = fileIn.nextInt();
        }
        for(int i = 0; i < inv.toolNum.length; i ++){
            inv.toolNum[i] = fileIn.nextInt();
        }
        for(int i = 0; i < inv.foodNum.length; i ++){
            inv.foodNum[i] = fileIn.nextInt();
        }
        for(int i = 0; i < inv.potionNum.length; i ++){
            inv.potionNum[i] = fileIn.nextInt();
        }
        for(int i = 0; i < inv.generalNum.length; i ++){
            inv.generalNum[i] = fileIn.nextInt();
        }
    }
    
    private static void loadBuffs(){
        for(int i = 0; i < 50; i++){
                buff.buffDur[i][1] = fileIn.nextInt();
            }
        for(int i = 0; i < 50; i++){
            if(fileIn.next().equals("T")){
                buff.effectActive[i] = true;
            }else{
                buff.effectActive[i] = false;
            }
        }
    }
    
}
