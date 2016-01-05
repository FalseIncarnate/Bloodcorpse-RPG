
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

public class Main {

    static Random gen = new Random();
    static Scanner in = new Scanner(System.in);
    static int option;
    
    static JFrame window = new JFrame("Bloodcorpse's RPG (v1.4)");
    static JTextArea output;
    static JScrollPane scroll;
    static JButton btn1, btn2, btn3, btn4, btn5, btn6;
    static JPanel optionsPanel = new JPanel();
    static JPanel btnPanel1 = new JPanel();
    static JPanel btnPanel2 = new JPanel();
    static JPanel statsPanel = new JPanel();
    static JLabel statName, statHP, statMP, statStr, statInt, statWil;
    static JLabel statBod, statSpd, statGP, statLvl, statXP, statArmor;
    
    static Handler h = new Handler();
    static Handler2 h2 = new Handler2();
    static String screen ="";
    static int event = 0;
    static String zone ="";
    
    public static void main(String[] args){
        buildGUI();
        Inv.start();
        Crafting.setRecipes();
        Shops.buildGUI();
        Effects.start();
        mainMenu();
    }
    
    protected static void buildGUI(){
        //Declare and initialize GridBagConstraints variable
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        //Set program to end when window closes
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set window layout to GridBagLayout
        window.setLayout(new GridBagLayout());
        //Build components
        buildOutput();
        buildOptionsPanel();
        buildStatsPanel();
        //Add output to window at grid (0,0)
        c.gridx = 0; c.gridy = 0; window.add(scroll, c);
        //Add optionsPanel to window at grid (0,1)
        c.gridx = 0; c.gridy = 1; window.add(optionsPanel, c);
        //Add statsPanel to window at grid (1,0)
        c.gridx = 1; c.gridy = 0; window.add(statsPanel, c);
        //Display window
        window.pack();
        window.setMinimumSize(window.getSize());
        window.setVisible(true);
    }
    
    private static void buildOutput(){
        //Initialize output, set to non-editable, set word-wrapping, add JScrollPane
        output = new JTextArea(25,50);
        output.setEditable(false);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        scroll = new JScrollPane(output, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }
    
    private static void buildOptionsPanel(){
        Dimension btnDim = new Dimension(100, 30);
        //Initialize buttons, set minimum size
        btn1 = new JButton("New Game");
        btn1.setSize(btnDim);
        btn2 = new JButton("Load Game");
        btn2.setSize(btnDim);
        btn3 = new JButton(" ");
        btn3.setSize(btnDim);
        btn4 = new JButton(" ");
        btn4.setSize(btnDim);
        btn5 = new JButton(" ");
        btn5.setSize(btnDim);
        btn6 = new JButton(" ");
        btn6.setSize(btnDim);
        //Add ActionListeners to buttons
        btn1.addActionListener(h); btn2.addActionListener(h); btn3.addActionListener(h);
        btn4.addActionListener(h); btn5.addActionListener(h); btn6.addActionListener(h);
        //Set btnPanel1 and btnPanel2 layouts to BoxLayout (Horizontal)
        btnPanel1.setLayout(new BoxLayout(btnPanel1, BoxLayout.X_AXIS));
        btnPanel2.setLayout(new BoxLayout(btnPanel2, BoxLayout.X_AXIS));
        //Add btn1, btn2, and btn3 to btnPanel1
        btnPanel1.add(btn1); btnPanel1.add(btn2); btnPanel1.add(btn3);
        //Add btn4, btn5, and btn6 to btnPanel2
        btnPanel2.add(btn4); btnPanel2.add(btn5); btnPanel2.add(btn6);
        //Set optionsPanel layout to BoxLayout (Vertical)
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        //Add btnPanel1 and btnPanel2 to optionsPanel
        optionsPanel.add(btnPanel1); optionsPanel.add(btnPanel2);
    }
    
    private static void buildStatsPanel(){
        //Initialize stats labels
        statName = new JLabel(); statLvl = new JLabel(); statXP = new JLabel();
        statHP = new JLabel(); statMP = new JLabel(); statStr = new JLabel();
        statInt = new JLabel(); statBod = new JLabel(); statWil = new JLabel();
        statSpd = new JLabel(); statGP = new JLabel(); statArmor = new JLabel();
        //Set statsPanel layout to BoxLayout (Vertical)
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        //Add stats labels to statsPanel
        statsPanel.add(statName); statsPanel.add(statLvl); statsPanel.add(statXP);
        statsPanel.add(statHP); statsPanel.add(statMP);
        statsPanel.add(statStr); statsPanel.add(statInt); statsPanel.add(statBod);
        statsPanel.add(statWil); statsPanel.add(statSpd); statsPanel.add(statGP);
        statsPanel.add(statArmor);
    }
    
    protected static void mainMenu() {
        output.setText("       ~Bloodcorpse's RPG~      " + "\n");
        output.append("        Adventure Awaits!       " + "\n");
        output.append("================================" + "\n");
        output.append("\n");
        
        screen = "mainMenu";
        btn1.setText("New Game");
        btn2.setText("Load Game");
        btn3.setText(" ");
        btn4.setText(" ");
        btn5.setText(" ");
        btn6.setText(" ");
    }
    
    static void newGame(){
        stats.resetStats();
        Inv.resetInv();
        Effects.restart();
        Object[] choices = {"Male", "Female"};
        int i = JOptionPane.showOptionDialog(window, "Are you a Male or a Female?", null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, null);
        if(i == JOptionPane.YES_OPTION){ //Man
            stats.isFemale = false;
            stats.name = JOptionPane.showInputDialog(window, "What is your name, adventurer?");
            if(stats.name != null && stats.name.length() > 0){
                updateLabels();
                Camp.campMenu();
            }
        }else if(i == JOptionPane.NO_OPTION){ //Woman
            stats.isFemale = true;
            stats.name = JOptionPane.showInputDialog(window, "What is your name, adventurer?");
            if(stats.name != null && stats.name.length() > 0){
                updateLabels();
                Camp.campMenu();
            }
        }
    }
    
    static void loadGame(){
        Save.loadGame();
    }
    
    static void updateLabels(){
        stats.updateStats();
        statName.setText(stats.name);
        statLvl.setText("Level: "+ stats.level);
        statXP.setText("EXP: " + stats.myEXP);
        statHP.setText("HP: " + stats.myHP[0] + "/" + stats.myHP[1]);
        statMP.setText("MP: " + stats.myMP[0] + "/" + stats.myMP[1]);
        statStr.setText("Strength: " + stats.strength[2] + " ("+ stats.strength[1]+")");
        statInt.setText("Intel: " + stats.intel[2] + " ("+ stats.intel[1]+")");
        statBod.setText("Body: " + stats.body[2] + " ("+ stats.body[1]+")");
        statWil.setText("Will: " + stats.will[2] + " ("+ stats.will[1]+")");
        statSpd.setText("Speed: " + stats.speed[2] + " ("+ stats.speed[1]+")");
        statGP.setText("Gold: " + stats.myGP[0]);
        statArmor.setText("Armor: " + stats.myArmor[2] + " ("+ stats.myArmor[1]+")");
        window.pack();
        window.setMinimumSize(window.getSize());
        stats.healthCheck();
    }
    
    static void gameOver(){
        output.setText("You collapse to the ground from your injuries." + "\n");
        output.append("The story of " + stats.name + " ends here." + "\n");
        output.append("As the last of your energy seeps away, you reflect on your life." + "\n");
        output.append("=======================================================" + "\n");
        output.append("Final Level: " + stats.level + "\n");
        output.append("Total Gold collected: " + stats.myGP[1] + "\n");
        output.append("Enemies slain: " + stats.myKills + "\n");
        output.append("Number of fights ran away from: " + stats.timesFled + "\n");
        output.append("Killed by the " + Enemy.enemyName + "\n");
        
        screen = "mainMenu";
        Main.btn1.setText("New Game");
        Main.btn2.setText("Load Game");
        Main.btn3.setText(" ");
        Main.btn4.setText(" ");
        Main.btn5.setText(" ");
        Main.btn6.setText(" ");
    }
    
}
