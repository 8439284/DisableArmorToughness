package org.ajls.disableArmorToughness;

import org.ajls.disableArmorToughness.utils.PlayerU;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;
import org.ajls.disableArmorToughness.DamageCalculation;

import static org.ajls.disableArmorToughness.DamageCalculation.*;


public class MyListener implements Listener {
//    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
//    public void onEntityDamageDisabled(EntityDamageEvent event) {
////        return;
//        EntityDamageEvent.DamageCause damageCause = event.getCause();
////        if (damageCause == EntityDamageEvent.DamageCause.) {}
//        double armorReducedDamage = event.getOriginalDamage(EntityDamageEvent.DamageModifier.ARMOR);
////        Bukkit.broadcastMessage(String.valueOf(-armorReducedDamage));
////        event.setDamage(EntityDamageEvent.DamageModifier.ARMOR, );
//        double damage = event.getDamage();
//        double finalDamage = event.getFinalDamage();
//        double calculatedDamage = damage;
////        event.getCause();
//        Entity entity = event.getEntity();
////        Entity damagerEntity = event.getDamager();
//        if (entity instanceof Player) {
//            Player player = (Player) entity;
//            String coloredString = "";
//            Bukkit.broadcastMessage("###################################");
//            Bukkit.broadcastMessage(player.getName());
//            Bukkit.broadcastMessage("original damage: " +damage);
//
////            Bukkit.broadcastMessage();
//            double calculatedDamageReducedByArmorWithoutArmorToughness = 0;
//            if (!(damageCause == EntityDamageEvent.DamageCause.FALL|| damageCause == EntityDamageEvent.DamageCause.FIRE_TICK || damageCause == EntityDamageEvent.DamageCause.DROWNING || damageCause == EntityDamageEvent.DamageCause.MAGIC || damageCause == EntityDamageEvent.DamageCause.FLY_INTO_WALL || damageCause == EntityDamageEvent.DamageCause.FREEZE)) {  // || damageCause == EntityDamageEvent.DamageCause.FIRE
//                double armorPoints = 0;
//                if (player.getAttribute(Attribute.GENERIC_ARMOR) != null) {
//                    armorPoints = player.getAttribute(Attribute.GENERIC_ARMOR).getValue();
//                }
////            Bukkit.broadcastMessage("AP: " + armorPoints);
//
//                double calculatedDamageWithoutArmorToughness = calculatedDamage;
//                double armorToughness = 0;
//                if (player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS) != null) {
//                    armorToughness = player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).getValue();
//                }
//                double cappedArmorToughness = armorToughness;
//                if (armorToughness > 20) {
//                    cappedArmorToughness = 20;
//                }
//                double reducedArmorPoints = armorPoints - 4 * damage / (cappedArmorToughness + 8) ;
//                if (reducedArmorPoints <= armorPoints/5) {
//                    reducedArmorPoints = armorPoints/5;
//                }
//                double calculatedDamageReducedByArmor;
//                if (reducedArmorPoints >= 25) {
//                    calculatedDamageReducedByArmor = calculatedDamage;
//                    calculatedDamage = 0;
//                } else {
//                    calculatedDamageReducedByArmor = calculatedDamage * reducedArmorPoints / 25;
//                    calculatedDamage -= calculatedDamageReducedByArmor;
////                    calculatedDamage -= calculatedDamage * reducedArmorPoints / 25;
//                }
//                Bukkit.broadcastMessage("Damage after Armor: " + calculatedDamage);
//                //damageReducedByArmor
//                //double calculatedDamageReducedByArmor = damage - calculatedDamage;
//                Bukkit.broadcastMessage(ChatColor.YELLOW + "Damage reduced by Armor: " + calculatedDamageReducedByArmor);
//                Bukkit.broadcastMessage("Original damage reduced by armor: " + String.valueOf(-armorReducedDamage));
//                coloredString = "";
//                if (Math.abs(calculatedDamageReducedByArmor + armorReducedDamage) >= 0.00001) {
//                    coloredString = "" + ChatColor.RED;
//                }
//                Bukkit.broadcastMessage(coloredString + "OFFSET: " + (calculatedDamageReducedByArmor + armorReducedDamage));
//
//                if (armorPoints >= 25) {
//                    calculatedDamageReducedByArmorWithoutArmorToughness = calculatedDamageWithoutArmorToughness;
//                    calculatedDamageWithoutArmorToughness = 0;
//                } else {
//                    calculatedDamageReducedByArmorWithoutArmorToughness = calculatedDamageWithoutArmorToughness * armorPoints / 25;
//                    calculatedDamageWithoutArmorToughness -= calculatedDamageReducedByArmorWithoutArmorToughness;
//                }
//                Bukkit.broadcastMessage(ChatColor.BLUE + "Damage protected without armor toughness(without pro and res) " + (calculatedDamageReducedByArmorWithoutArmorToughness));
//                Bukkit.broadcastMessage(ChatColor.YELLOW + "Damage after Armor Without ArmorToughness: " + calculatedDamageWithoutArmorToughness);
//                Bukkit.broadcastMessage(ChatColor.BLUE + "Damage saved from plugin(without pro and res) " + (calculatedDamage - calculatedDamageWithoutArmorToughness));
//            }
//
//
//            int playerEPF = PlayerU.getPlayerEPF(player);  //org.ajls.lib.utils.PlayerU.
//            if (playerEPF > 25) {
//                calculatedDamage = 0;
//            }
//            else {
//                calculatedDamage -= calculatedDamage * playerEPF / 25;
//            }
//            Bukkit.broadcastMessage("Damage after Protection: " + calculatedDamage);
//
//            int resistanceAmplifier = -1;
//            if (player.getPotionEffect(PotionEffectType.RESISTANCE) != null) {
//                resistanceAmplifier = player.getPotionEffect(PotionEffectType.RESISTANCE).getAmplifier();
//            }
//            Bukkit.broadcastMessage("Resistance Amplifier: " + resistanceAmplifier);
//            if (resistanceAmplifier >= 4) {
//                calculatedDamage = 0;
//            }
//            else {
//                calculatedDamage -= calculatedDamage * (resistanceAmplifier + 1) / 5;
//            }
//            Bukkit.broadcastMessage("Damage after Resistance: " + calculatedDamage);
//            double damageAfterResistance = calculatedDamage;
//
//
//            Bukkit.broadcastMessage("original final damage: " +finalDamage);
//            coloredString = "";
//            if (Math.abs(calculatedDamage-finalDamage) >= 0.00001 ) {
//                coloredString = "" + ChatColor.RED;
//            }
//            Bukkit.broadcastMessage(coloredString + "Damage offset: " + (calculatedDamage-finalDamage));
//
////            if (calculatedDamage-finalDamage != 0) {
////                Bukkit.broadcastMessage(ChatColor.RED + "THERE IS OFFSET");
////            }
//
//
////            event.setDamage(EntityDamageEvent.DamageModifier.ARMOR, -calculatedDamageReducedByArmorWithoutArmorToughness);
//
//
//
////            event.setDamage(0.00001);
////            org.ajls.lib.utils.PlayerU.addHealth(player, -calculatedDamage);
//
//        }
//    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityDamage(EntityDamageEvent event) {
        EntityDamageEvent.DamageCause damageCause = event.getCause();
        double damageReducedByArmor = event.getOriginalDamage(EntityDamageEvent.DamageModifier.ARMOR);
        double damage = event.getDamage();
        double calculatedDamage = damage;
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            Player player = (Player) entity;
            double calculatedDamageReducedByArmorWithoutArmorToughness = 0;
            if (!(damageCause == EntityDamageEvent.DamageCause.FALL|| damageCause == EntityDamageEvent.DamageCause.FIRE_TICK || damageCause == EntityDamageEvent.DamageCause.DROWNING || damageCause == EntityDamageEvent.DamageCause.MAGIC || damageCause == EntityDamageEvent.DamageCause.FLY_INTO_WALL || damageCause == EntityDamageEvent.DamageCause.FREEZE)) {  // || damageCause == EntityDamageEvent.DamageCause.FIRE
                double armorPoints = 0;
                if (player.getAttribute(Attribute.GENERIC_ARMOR) != null) {
                    armorPoints = player.getAttribute(Attribute.GENERIC_ARMOR).getValue();
                }
                double calculatedDamageWithoutArmorToughness = calculatedDamage;
                if (armorPoints >= 25) {
                    calculatedDamageReducedByArmorWithoutArmorToughness = calculatedDamageWithoutArmorToughness;
                    calculatedDamageWithoutArmorToughness = 0;
                } else {
                    calculatedDamageReducedByArmorWithoutArmorToughness = calculatedDamageWithoutArmorToughness * armorPoints / 25;
                    calculatedDamageWithoutArmorToughness -= calculatedDamageReducedByArmorWithoutArmorToughness;
                }
//                Bukkit.broadcastMessage(ChatColor.GREEN + "Simplified code armor damage protected: " + calculatedDamageReducedByArmorWithoutArmorToughness);
                event.setDamage(EntityDamageEvent.DamageModifier.ARMOR, -calculatedDamageReducedByArmorWithoutArmorToughness);
            }
        }
        return;
//        double damage = event.getDamage();
//        double finalDamage = event.getFinalDamage();
//        double calculatedDamage = damage;
//        Entity entity = event.getEntity();
//        if (entity instanceof Player) {
//            Player player = (Player) entity;
//            calculatedDamage = calculateArmor(player, calculatedDamage, true);
//            calculatedDamage = calculateEPF(player, calculatedDamage);
//            calculatedDamage = calculateResistance(player, calculatedDamage);
//            event.setDamage(calculatedDamage);
//        }
    }
}
