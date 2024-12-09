package org.ajls.disableArmorToughness.utils;

import org.ajls.lib.utils.ItemStackU;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

public class PlayerU {
    public static int getPlayerEPF(Player player) {
//        player.getInventory().getArmorContents();
        EntityEquipment entityEquipment = player.getEquipment();
        ItemStack helmet = entityEquipment.getHelmet();
        ItemStack chestplate = entityEquipment.getChestplate();
        ItemStack leggings = entityEquipment.getLeggings();
        ItemStack boots = entityEquipment.getBoots();
        ItemStack[] equipments = {helmet, chestplate, leggings, boots};
        int EPF = 0;
        for (int i = 0; i < equipments.length; i++) {
            ItemStack equipment = equipments[i];
            if (equipment == null) continue;
            EPF += ItemStackU.getEnchantmentLevel(equipment, Enchantment.PROTECTION);
        }
//        EPF *= 4;
        return EPF;
    }
}
