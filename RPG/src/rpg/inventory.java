package rpg;

/**
 * @author Bloodcorpse
 */
import java.util.*;

public class inventory {

    static String equipped[] = {"Nothing", "Nothing", "Nothing"};
    static final String armorInv[] = {"Leather Armor", "Chainmail Armor", "Plate Armor", "Crystal Armor", "Wolf Hide Armor", "Turtle Armor"};
    static int armorNum[] = new int[armorInv.length];
    static final String weaponInv[] = {"Knife", "Spear", "Axe", "Sword", "Troll Bite Gauntlet"};
    static int weaponNum[] = new int[weaponInv.length];
    static final String offhandInv[] = {"Shield", "Spell Tome", "Off-hand Dagger", "Turtle Shield", "Voodoo Doll"};
    static int offhandNum[] = new int[offhandInv.length];
    static final String toolInv[] = {"Fishing Pole", "Pickaxe", "Shovel", "Doll of Rebirth"};
    static int toolNum[] = new int[toolInv.length];
    static final String foodInv[] = {"Trail Mix", "Cooked Fish", "Jar of Honey", "Tasty Berries"};
    static int foodNum[] = new int[foodInv.length];
    static final String potionInv[] = {"Weak Healing Potion", "Weak Mana Potion", "Healing Potion", "Mana Potion", "Potent Healing Potion", "Potent Mana Potion", "Nature's Fury"};
    static int potionNum[] = new int[potionInv.length];
    static final String generalInv[] = {"Wolf Pelt", "Wooden Doll", "Soggy Boot", "Turtle Shell", "Troll Tooth", "Heartbloom", "Mageflower", "Venom Vine", "Metallite Ore", "Crystal Shard", "Shiny Rocks", "Yeti Fur"};
    static final int generalVal[] = {25, 10, 10, 20, 30, 5, 5, 15, 10, 40, 5, 25};
    static int generalNum[] = new int[generalInv.length];
    
    static int armorVal = 0;
    static int weaponVal = 0;
    static int offhandVal = 0;

    public inventory() {
        for (int i = 0; i < armorNum.length; i++) {
            armorNum[i] = 0;
        }
        for (int i = 0; i < weaponNum.length; i++) {
            weaponNum[i] = 0;
        }
        for (int i = 0; i < offhandNum.length; i++) {
            offhandNum[i] = 0;
        }
        for (int i = 0; i < toolNum.length; i++) {
            toolNum[i] = 0;
        }
        for (int i = 0; i < foodNum.length; i++) {
            foodNum[i] = 0;
        }
        for (int i = 0; i < potionNum.length; i++) {
            potionNum[i] = 0;
        }
    }

    protected static void showFullInv() {
        System.out.println("\n");
        System.out.println("Equipped Items:");
        System.out.println("--------------------------");
        System.out.println("Armor:      " + equipped[0]);
        System.out.println("Main Hand:  " + equipped[1]);
        System.out.println("Off Hand:   " + equipped[2]);
        System.out.println("Your Inventory:");
        System.out.println("--------------------------");
        System.out.println("Armor:");
        for (int i = 0; i < armorNum.length; i++) {
            if (armorNum[i] > 0) {
                System.out.println(armorInv[i] + " x" + armorNum[i]);
            }
        }
        System.out.print("\n");
        System.out.println("Weapons:");
        for (int i = 0; i < weaponNum.length; i++) {
            if (weaponNum[i] > 0) {
                System.out.println(weaponInv[i] + " x" + weaponNum[i]);
            }
        }
        System.out.print("\n");
        System.out.println("Off-Hand Items:");
        for (int i = 0; i < offhandNum.length; i++) {
            if (offhandNum[i] > 0) {
                System.out.println(offhandInv[i] + " x" + offhandNum[i]);
            }
        }
        System.out.print("\n");
        System.out.println("Tools:");
        for (int i = 0; i < toolNum.length; i++) {
            if (toolNum[i] > 0) {
                System.out.println(toolInv[i] + " x" + toolNum[i]);
            }
        }
        System.out.print("\n");
        System.out.println("Food:");
        for (int i = 0; i < foodNum.length; i++) {
            if (foodNum[i] > 0) {
                System.out.println(foodInv[i] + " x" + foodNum[i]);
            }
        }
        System.out.print("\n");
        System.out.println("Potions:");
        for (int i = 0; i < potionNum.length; i++) {
            if (potionNum[i] > 0) {
                System.out.println(potionInv[i] + " x" + potionNum[i]);
            }
        }
        System.out.print("\n");
        System.out.println("General Items:");
        for (int i = 0; i < generalNum.length; i++) {
            if (generalNum[i] > 0) {
                System.out.println(generalInv[i] + " x" + generalNum[i]);
            }
        }
        useInventory();
    }

    private static void useInventory() {
        System.out.println("\n");
        System.out.println("What would you like to do?");
        System.out.println(" 1. Equip Armor       2. Equip Weapon");
        System.out.println(" 3. Equip Off-hand    4. Consume Food");
        System.out.println(" 5. Close Inventory");
        RPG.option = RPG.in.nextLine();
        if (RPG.option.equals("1")) {
            equipArmor();
        } else if (RPG.option.equals("2")) {
            equipWeapon();
        } else if (RPG.option.equals("3")) {
            equipOffhand();
        } else if (RPG.option.equals("4")) {
            useFood();
        } else if (RPG.option.equals("5")) {
            RPG.camp();
        } else {
            useInventory();
        }
    }

    //ITEM USE METHODS
    
    private static void useFood() {
        System.out.println("\n");
        System.out.println("You aren't hungry right now.");
        useInventory();
    }

    protected static void combatInv() {
        System.out.println("\n");
        System.out.println("Potions:");
        System.out.println(" 1. Weak Healing Potion (" + potionNum[0] + ")       2. Weak Mana Potion (" + potionNum[1] + ")");
        System.out.println(" 3. Healing Potion (" + potionNum[2] + ")            4. Mana Potion (" + potionNum[3] + ")");
        System.out.println(" 5. Potent Healing Potion (" + potionNum[4] + ")     6. Potent Mana Potion (" + potionNum[5] + ")");
        System.out.println(" 7. Nature's Fury (" + potionNum[6] + ")             8. Nothing");
        RPG.option = RPG.in.nextLine();
        if (RPG.option.equals("1")) {
            if (potionNum[0] > 0) {
                if ((RPG.maxHP - RPG.curHP) > 25) {
                    System.out.println("You use a " + potionInv[0] + ", and regain 25 health!");
                    RPG.curHP += 25;
                } else {
                    System.out.println("You use a " + potionInv[0] + ", and regain " + (RPG.maxHP - RPG.curHP) + " health. You are fully healed!");
                    RPG.curHP = RPG.maxHP;
                }
                potionNum[0] -= 1;
                RPG.enemy.checkHP(false);
            } else {
                System.out.println("You don't have a " + potionInv[0] + " to use!");
                combatInv();
            }
        } else if (RPG.option.equals("2")) {
            if (potionNum[1] > 0) {
                if ((RPG.maxMP - RPG.curMP) > 25) {
                    System.out.println("You use a " + potionInv[1] + ", and regain 25 mana!");
                    RPG.curMP += 25;
                } else {
                    System.out.println("You use a " + potionInv[1] + ", and regain " + (RPG.maxMP - RPG.curMP) + " mana. You are now at full mana!");
                    RPG.curMP = RPG.maxMP;
                }
                potionNum[1] -= 1;
                RPG.enemy.checkHP(false);
            } else {
                System.out.println("You don't have a " + potionInv[1] + " to use!");
                combatInv();
            }
        } else if (RPG.option.equals("3")) {
            if (potionNum[2] > 0) {
                if ((RPG.maxHP - RPG.curHP) > 75) {
                    System.out.println("You use a " + potionInv[2] + ", and regain 75 health!");
                    RPG.curHP += 75;
                } else {
                    System.out.println("You use a " + potionInv[2] + ", and regain " + (RPG.maxHP - RPG.curHP) + " health. You are fully healed!");
                    RPG.curHP = RPG.maxHP;
                }
                potionNum[2] -= 1;
                RPG.enemy.checkHP(false);
            } else {
                System.out.println("You don't have a " + potionInv[2] + " to use!");
                combatInv();
            }
        } else if (RPG.option.equals("4")) {
            if (potionNum[3] > 0) {
                if ((RPG.maxMP - RPG.curMP) > 50) {
                    System.out.println("You use a " + potionInv[3] + ", and regain 50 mana!");
                    RPG.curMP += 50;
                } else {
                    System.out.println("You use a " + potionInv[3] + ", and regain " + (RPG.maxMP - RPG.curMP) + " mana. You are now at full mana!");
                    RPG.curMP = RPG.maxMP;
                }
                potionNum[3] -= 1;
                RPG.enemy.checkHP(false);
            } else {
                System.out.println("You don't have a " + potionInv[3] + " to use!");
                combatInv();
            }
        } else if (RPG.option.equals("5")) {
            if (potionNum[4] > 0) {
                if ((RPG.maxHP - RPG.curHP) > 100) {
                    System.out.println("You use a " + potionInv[4] + ", and regain 125 health!");
                    RPG.curHP += 125;
                } else {
                    System.out.println("You use a " + potionInv[4] + ", and regain " + (RPG.maxHP - RPG.curHP) + " health. You are fully healed!");
                    RPG.curHP = RPG.maxHP;
                }
                potionNum[4] -= 1;
                RPG.enemy.checkHP(false);
            } else {
                System.out.println("You don't have a " + potionInv[4] + " to use!");
                combatInv();
            }
        } else if (RPG.option.equals("6")) {
            if (potionNum[5] > 0) {
                if ((RPG.maxMP - RPG.curMP) > 75) {
                    System.out.println("You use a " + potionInv[5] + ", and regain 75 mana!");
                    RPG.curMP += 75;
                } else {
                    System.out.println("You use a " + potionInv[5] + ", and regain " + (RPG.maxMP - RPG.curMP) + " mana. You are now at full mana!");
                    RPG.curMP = RPG.maxMP;
                }
                potionNum[5] -= 1;
                RPG.enemy.checkHP(false);
            } else {
                System.out.println("You don't have a " + potionInv[5] + " to use!");
                combatInv();
            }
        } else if (RPG.option.equals("7")) {
            if (potionNum[6] > 0) {
                System.out.println("You splash a " + potionInv[6] + " at the " + mob.mobName + ", dealing 50 damage!");
                mob.mobCurHP -= 50;
                potionNum[5] -= 1;
                RPG.enemy.checkHP(false);
            } else {
                System.out.println("You don't have a " + potionInv[6] + " to use!");
                combatInv();
            }
        }else if (RPG.option.equals("8")) {
            return;
        } else {
            combatInv();
        }
    }

    //END ITEM USE METHODS
    
    //EQUIP METHODS
    
    private static void equipArmor() {
        System.out.println("\n");
        System.out.println("Which armor would you like to wear?");
        for (int i = 0; i < armorInv.length; i++) {
            System.out.print(" " + (i + 1) + ". " + armorInv[i]);
            if (equipped[0].equals(armorInv[i])) {
                System.out.println(" (Currently Equipped)");
            } else {
                System.out.println(" x" + armorNum[i]);
            }
        }
        System.out.println(" 7. No Armor");
        System.out.println(" 8. Nevermind");
        RPG.option = RPG.in.nextLine();
        if (RPG.option.equals("1")) {
            if (armorNum[0] > 0) {
                if (equipped[0].equals("Nothing")) {
                    System.out.println("You equip your " + armorInv[0] + ".");
                    equipped[0] = armorInv[0];
                    armorVal = 2;
                } else if (equipped[0].equals(armorInv[0])) {
                    System.out.println("You are already wearing your " + armorInv[0] + "!");
                } else {
                    System.out.println("You replace your " + equipped[0] + " with " + armorInv[0] + ".");
                    equipped[0] = armorInv[0];
                    armorVal = 2;
                }
                effectCheck();
                RPG.updateStats();
            } else {
                System.out.println("You can't equip what you don't have!");
            }
            useInventory();
        } else if (RPG.option.equals("2")) {
            if (armorNum[1] > 0) {
                if (equipped[0].equals("Nothing")) {
                    System.out.println("You equip your " + armorInv[1] + ".");
                    equipped[0] = armorInv[1];
                    armorVal = 5;
                } else if (equipped[0].equals(armorInv[1])) {
                    System.out.println("You are already wearing your " + armorInv[1] + "!");
                } else {
                    System.out.println("You replace your " + equipped[0] + " with " + armorInv[1] + ".");
                    equipped[0] = armorInv[1];
                    armorVal = 5;
                }
                effectCheck();
                RPG.updateStats();
            } else {
                System.out.println("You can't equip what you don't have!");
            }
            useInventory();
        } else if (RPG.option.equals("3")) {
            if (armorNum[2] > 0) {
                if (equipped[0].equals("Nothing")) {
                    System.out.println("You equip your " + armorInv[2] + ".");
                    equipped[0] = armorInv[2];
                    armorVal = 10;
                } else if (equipped[0].equals(armorInv[2])) {
                    System.out.println("You are already wearing your " + armorInv[2] + "!");
                } else {
                    System.out.println("You replace your " + equipped[0] + " with " + armorInv[2] + ".");
                    equipped[0] = armorInv[2];
                    armorVal = 10;
                }
                effectCheck();
                RPG.updateStats();
            } else {
                System.out.println("You can't equip what you don't have!");
            }
            useInventory();
        } else if (RPG.option.equals("5")) {
            if (armorNum[4] > 0) {
                if (equipped[0].equals("Nothing")) {
                    System.out.println("You equip your " + armorInv[4] + ".");
                    equipped[0] = armorInv[4];
                    armorVal = 7;
                } else if (equipped[0].equals(armorInv[4])) {
                    System.out.println("You are already wearing your " + armorInv[4] + "!");
                } else {
                    System.out.println("You replace your " + equipped[0] + " with " + armorInv[4] + ".");
                    equipped[0] = armorInv[4];
                    armorVal = 7;
                }
                effectCheck();
                RPG.updateStats();
            } else {
                System.out.println("You can't equip what you don't have!");
            }
            useInventory();
        } else if (RPG.option.equals("6")) {
            if (armorNum[5] > 0) {
                if (equipped[0].equals("Nothing")) {
                    System.out.println("You equip your " + armorInv[5] + ".");
                    equipped[0] = armorInv[5];
                    armorVal = 13;
                } else if (equipped[0].equals(armorInv[5])) {
                    System.out.println("You are already wearing your " + armorInv[5] + "!");
                } else {
                    System.out.println("You replace your " + equipped[0] + " with " + armorInv[5] + ".");
                    equipped[0] = armorInv[5];
                    armorVal = 13;
                }
                effectCheck();
                RPG.updateStats();
            } else {
                System.out.println("You can't equip what you don't have!");
            }
            useInventory();
        } else if (RPG.option.equals("7")) {
            if (equipped[0].equals("Nothing")) {
                System.out.println("You weren't wearing any armor to begin with!");
            } else {
                System.out.println("You remove your " + equipped[0] + ".");
                equipped[0] = "Nothing";
                armorVal = 0;
            }
            effectCheck();
            RPG.updateStats();
            useInventory();
        } else if (RPG.option.equals("8")) {
            useInventory();
        } else {
            equipArmor();
        }
    }

    private static void equipWeapon() {
        System.out.println("\n");
        System.out.println("Which weapon would you like to wield?");
        for (int i = 0; i < weaponInv.length; i++) {
            System.out.print(" " + (i + 1) + ". " + weaponInv[i]);
            if (equipped[1].equals(weaponInv[i])) {
                System.out.println(" (Currently Equipped)");
            } else {
                System.out.println(" x" + weaponNum[i]);
            }
        }
        System.out.println(" 6. No Weapon");
        System.out.println(" 7. Nevermind");
        RPG.option = RPG.in.nextLine();
        if (RPG.option.equals("1")) {
            if (weaponNum[0] > 0) {
                if (equipped[1].equals("Nothing")) {
                    System.out.println("You equip your " + weaponInv[0] + ".");
                    equipped[1] = weaponInv[0];
                } else if (equipped[1].equals(weaponInv[0])) {
                    System.out.println("You are already wielding your " + weaponInv[0] + "!");
                } else {
                    System.out.println("You replace your " + equipped[1] + " with " + weaponInv[0] + ".");
                    equipped[1] = weaponInv[0];
                }
                weaponVal = 3;
                effectCheck();
                RPG.updateStats();
            } else {
                System.out.println("You can't equip what you don't have!");
            }
            useInventory();
        } else if (RPG.option.equals("2")) {
            if (weaponNum[1] > 0) {
                if (equipped[1].equals("Nothing")) {
                    System.out.println("You equip your " + weaponInv[1] + ".");
                    equipped[1] = weaponInv[1];
                } else if (equipped[1].equals(weaponInv[1])) {
                    System.out.println("You are already wielding your " + weaponInv[1] + "!");
                } else {
                    System.out.println("You replace your " + equipped[1] + " with " + weaponInv[1] + ".");
                    equipped[1] = weaponInv[1];
                }
                weaponVal = 6;
                effectCheck();
                RPG.updateStats();
            } else {
                System.out.println("You can't equip what you don't have!");
            }
            useInventory();
        } else if (RPG.option.equals("3")) {
            if (weaponNum[2] > 0) {
                if (equipped[1].equals("Nothing")) {
                    System.out.println("You equip your " + weaponInv[2] + ".");
                    equipped[1] = weaponInv[2];
                } else if (equipped[1].equals(weaponInv[2])) {
                    System.out.println("You are already wielding your " + weaponInv[2] + "!");
                } else {
                    System.out.println("You replace your " + equipped[1] + " with " + weaponInv[2] + ".");
                    equipped[1] = weaponInv[2];
                }
                weaponVal = 10;
                effectCheck();
                RPG.updateStats();
            } else {
                System.out.println("You can't equip what you don't have!");
            }
            useInventory();
        } else if (RPG.option.equals("4")) {
            if (weaponNum[3] > 0) {
                if (equipped[1].equals("Nothing")) {
                    System.out.println("You equip your " + weaponInv[3] + ".");
                    equipped[1] = weaponInv[3];
                } else if (equipped[1].equals(weaponInv[3])) {
                    System.out.println("You are already wielding your " + weaponInv[3] + "!");
                } else {
                    System.out.println("You replace your " + equipped[1] + " with " + weaponInv[3] + ".");
                    equipped[1] = weaponInv[3];
                }
                weaponVal = 15;
                effectCheck();
                RPG.updateStats();
            } else {
                System.out.println("You can't equip what you don't have!");
            }
            useInventory();
        } else if (RPG.option.equals("5")) {
            if (weaponNum[4] > 0) {
                if (equipped[1].equals("Nothing")) {
                    System.out.println("You equip your " + weaponInv[4] + ".");
                    equipped[1] = weaponInv[4];
                } else if (equipped[1].equals(weaponInv[4])) {
                    System.out.println("You are already wielding your " + weaponInv[4] + "!");
                } else {
                    System.out.println("You replace your " + equipped[1] + " with " + weaponInv[4] + ".");
                    equipped[1] = weaponInv[4];
                }
                weaponVal = 20;
                effectCheck();
                RPG.updateStats();
            } else {
                System.out.println("You can't equip what you don't have!");
            }
            useInventory();
        } else if (RPG.option.equals("6")) {
            if (equipped[1].equals("Nothing")) {
                System.out.println("You weren't wielding a weapon to begin with!");
            } else {
                System.out.println("You put away your " + equipped[1] + ".");
                equipped[1] = "Nothing";
            }
            weaponVal = 0;
            effectCheck();
            RPG.updateStats();
            useInventory();
        } else if (RPG.option.equals("7")) {
            useInventory();
        } else {
            equipWeapon();
        }
    }

    private static void equipOffhand() {
        System.out.println("\n");
        System.out.println("Which Off-hand item would you like to wear?");
        for (int i = 0; i < offhandInv.length; i++) {
            System.out.print(" " + (i + 1) + ". " + offhandInv[i]);
            if (equipped[2].equals(offhandInv[i])) {
                System.out.println(" (Currently Equipped)");
            } else {
                System.out.println(" x" + offhandNum[i]);
            }
        }
        System.out.println(" 6. No Off-hand item");
        System.out.println(" 7. Nevermind");
        RPG.option = RPG.in.nextLine();
        if (RPG.option.equals("1")) {
            if (offhandNum[0] > 0) {
                if (equipped[2].equals("Nothing")) {
                    System.out.println("You equip your " + offhandInv[0] + ".");
                    equipped[2] = offhandInv[0];
                } else if (equipped[2].equals(offhandInv[0])) {
                    System.out.println("You are already holding your " + offhandInv[0] + "!");
                } else {
                    System.out.println("You replace your " + equipped[2] + " with " + offhandInv[0] + ".");
                    equipped[2] = offhandInv[0];
                }
                effectCheck();
                RPG.updateStats();
            } else {
                System.out.println("You can't equip what you don't have!");
            }
            useInventory();
        }else if (RPG.option.equals("2")) {
            if (offhandNum[1] > 0) {
                if (equipped[2].equals("Nothing")) {
                    System.out.println("You equip your " + offhandInv[1] + ".");
                    equipped[2] = offhandInv[1];
                } else if (equipped[2].equals(offhandInv[1])) {
                    System.out.println("You are already holding your " + offhandInv[1] + "!");
                } else {
                    System.out.println("You replace your " + equipped[2] + " with " + offhandInv[1] + ".");
                    equipped[2] = offhandInv[1];
                }
                effectCheck();
                RPG.updateStats();
            } else {
                System.out.println("You can't equip what you don't have!");
            }
            useInventory();
        }else if (RPG.option.equals("3")) {
            if (offhandNum[2] > 0) {
                if (equipped[2].equals("Nothing")) {
                    System.out.println("You equip your " + offhandInv[2] + ".");
                    equipped[2] = offhandInv[2];
                } else if (equipped[2].equals(offhandInv[2])) {
                    System.out.println("You are already holding your " + offhandInv[2] + "!");
                } else {
                    System.out.println("You replace your " + equipped[2] + " with " + offhandInv[2] + ".");
                    equipped[2] = offhandInv[2];
                }
                effectCheck();
                RPG.updateStats();
            } else {
                System.out.println("You can't equip what you don't have!");
            }
            useInventory();
        }else if (RPG.option.equals("4")) {
            if (offhandNum[3] > 0) {
                if (equipped[2].equals("Nothing")) {
                    System.out.println("You equip your " + offhandInv[3] + ".");
                    equipped[2] = offhandInv[3];
                } else if (equipped[2].equals(offhandInv[3])) {
                    System.out.println("You are already holding your " + offhandInv[3] + "!");
                } else {
                    System.out.println("You replace your " + equipped[2] + " with " + offhandInv[3] + ".");
                    equipped[2] = offhandInv[3];
                }
                effectCheck();
                RPG.updateStats();
            } else {
                System.out.println("You can't equip what you don't have!");
            }
            useInventory();
        }else if (RPG.option.equals("5")) {
            if (offhandNum[4] > 0) {
                if (equipped[2].equals("Nothing")) {
                    System.out.println("You equip your " + offhandInv[4] + ".");
                    equipped[2] = offhandInv[4];
                } else if (equipped[2].equals(offhandInv[4])) {
                    System.out.println("You are already holding your " + offhandInv[4] + "!");
                } else {
                    System.out.println("You replace your " + equipped[2] + " with " + offhandInv[4] + ".");
                    equipped[2] = offhandInv[4];
                }
                effectCheck();
                RPG.updateStats();
            } else {
                System.out.println("You can't equip what you don't have!");
            }
            useInventory();
        }else if (RPG.option.equals("6")) {
            if (equipped[2].equals("Nothing")) {
                System.out.println("You weren't holding an Off-hand item to begin with!");
            } else {
                System.out.println("You put away your " + equipped[2] + ".");
                equipped[2] = "Nothing";
            }
            effectCheck();
            RPG.updateStats();
            useInventory();
        } else if (RPG.option.equals("7")) {
            useInventory();
        } else {
            equipArmor();
        }
    }
    
    //END EQUIP METHODS
    
    protected static void effectCheck(){
        buff.effectActive[1] = false;
        buff.effectActive[2] = false;
        buff.effectActive[3] = false;
        buff.effectActive[4] = false;
        buff.effectActive[5] = false;
        buff.effectActive[6] = false;
        if(equipped[0].equals(armorInv[5])){
            buff.effectActive[6] = true;
        }
        if(equipped[2].equals(offhandInv[0])){
            buff.effectActive[1] = true;
        }else if(equipped[2].equals(offhandInv[1])){
            buff.effectActive[2] = true;
        }else if(equipped[2].equals(offhandInv[2])){
            buff.effectActive[3] = true;
        }else if(equipped[2].equals(offhandInv[3])){
            buff.effectActive[4] = true;
        }else if(equipped[2].equals(offhandInv[4])){
            buff.effectActive[5] = true;
        }
        buff.startBuff();
    }

}
