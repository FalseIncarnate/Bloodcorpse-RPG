
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

public class Explore {
    
    protected static void exploreMenu(){
        Main.zone = "Explore1";
        Main.screen = "exploreMenu";
        Main.btn1.setText("Camp");
        Main.btn2.setText("Town");
        Main.btn3.setText("Forest");
        Main.btn4.setText("River");
        Main.btn5.setText("Mountain");
        Main.btn6.setText("Graveyard");
        Main.output.setText("You stand at a complex cross-roads." + "\n");
        Main.output.append("A sign post near each path announces where that path leads." + "\n");
        Main.output.append("Where would you like to go?");
        Main.output.append("\n" + "\n");
    }
    
    protected static void exploreMenu2(){
        Main.zone = "Explore2";
        Main.screen = "exploreMenu2";
        Main.btn1.setText("Cross Bridge");
        Main.btn2.setText("Port");
        Main.btn3.setText("Plains");
        Main.btn4.setText("Swamp");
        Main.btn5.setText("Desert");
        Main.btn6.setText("Dark Forest");
        Main.output.setText("You find yourself in the middle of a second cross-roads." + "\n");
        Main.output.append("Signs are posted near each path to provide their destination." + "\n");
        Main.output.append("You recall that across the bridge lies your camp and other places." + "\n");
        Main.output.append("Where would you like to go?");
        Main.output.append("\n" + "\n");
    }
    
}
