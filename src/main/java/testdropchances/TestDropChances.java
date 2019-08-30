package testdropchances;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class TestDropChances extends JavaPlugin {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("testdropchances")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("You must be in-game to use this command.");
                return true;
            }
            Player player = (Player) sender;

            if (args.length == 0) {
                return false;
            }

            if (args.length == 1) {
                Float chance = parseChance(sender, args[0], "");
                if (chance == null) {
                    return true;
                }

                spawnTestMob(player, chance, chance, chance, chance, chance, chance);
                return true;
            }

            if (args.length == 6) {
                Float main = parseChance(sender, args[0], "main hand ");
                Float off = parseChance(sender, args[1], "off hand ");
                Float boots = parseChance(sender, args[2], "boots ");
                Float leggings = parseChance(sender, args[3], "leggings ");
                Float chest = parseChance(sender, args[4], "chestplate ");
                Float helmet = parseChance(sender, args[5], "helmet ");
                if (main == null || off == null || boots == null || leggings == null || chest == null || helmet == null) {
                    return true;
                }

                spawnTestMob(player, main, off, boots, leggings, chest, helmet);
                return true;
            }

        }
        return false;
    }

    private void spawnTestMob(Player player, Float main, Float off, Float boots, Float leggings, Float chest, Float helmet) {
        WitherSkeleton mob = player.getWorld().spawn(player.getLocation(), WitherSkeleton.class);
        mob.setHealth(1.0);
        // Picking up items changes drop chances.
        mob.setCanPickupItems(false);
        mob.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_SWORD));
        mob.getEquipment().setItemInMainHandDropChance(main);
        mob.getEquipment().setItemInOffHand(new ItemStack(Material.SHIELD));
        mob.getEquipment().setItemInOffHandDropChance(off);
        mob.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
        mob.getEquipment().setBootsDropChance(boots);
        mob.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
        mob.getEquipment().setLeggingsDropChance(leggings);
        mob.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
        mob.getEquipment().setChestplateDropChance(chest);
        mob.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
        mob.getEquipment().setHelmetDropChance(helmet);
        player.sendMessage(ChatColor.GOLD + "Low health test mob spawned.");
        player.sendMessage(ChatColor.GOLD + "Main, off hand drop chances: " + String.format("%.4f, %.4f.", main, off));
        player.sendMessage(ChatColor.GOLD + "Boots, leggings, chestplate, helmet drop chances: " +
                           String.format("%.4f, %.4f, %.4f, %.4f.", boots, leggings, chest, helmet));
    }

    private Float parseChance(CommandSender sender, String arg, String purpose) {
        Float chance = null;
        try {
            chance = Float.parseFloat(arg);
            if (chance < 0.0f || chance > 1.0f) {
                chance = null;
            }
        } catch (NumberFormatException ex) {
        }

        if (chance == null) {
            sender.sendMessage(ChatColor.RED + "The " + purpose + "drop chance must be a floating point number in the range [0.0,1.0].");
        }
        return chance;
    }
}
