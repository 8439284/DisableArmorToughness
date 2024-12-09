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
    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onEntityDamageDisabled(EntityDamageEvent event) {
//        return;
        EntityDamageEvent.DamageCause damageCause = event.getCause();
//        if (damageCause == EntityDamageEvent.DamageCause.) {}
        double armorReducedDamage = event.getOriginalDamage(EntityDamageEvent.DamageModifier.ARMOR);
//        Bukkit.broadcastMessage(String.valueOf(-armorReducedDamage));
//        event.setDamage(EntityDamageEvent.DamageModifier.ARMOR, );
        double damage = event.getDamage();
        double finalDamage = event.getFinalDamage();
        double calculatedDamage = damage;
//        event.getCause();
        Entity entity = event.getEntity();
//        Entity damagerEntity = event.getDamager();
        if (entity instanceof Player) {
            Player player = (Player) entity;
            Bukkit.broadcastMessage("###################################");
            Bukkit.broadcastMessage(player.getName());
            Bukkit.broadcastMessage("original damage: " +damage);

//            Bukkit.broadcastMessage();


            double armorPoints = 0;
            if (player.getAttribute(Attribute.GENERIC_ARMOR) != null) {
                armorPoints = player.getAttribute(Attribute.GENERIC_ARMOR).getValue();
            }
//            Bukkit.broadcastMessage("AP: " + armorPoints);

            double calculatedDamageWithoutArmorToughness = calculatedDamage;
            double armorToughness = 0;
            if (player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS) != null) {
                armorToughness = player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).getValue();
            }
            double cappedArmorToughness = armorToughness;
            if (armorToughness > 20) {
                cappedArmorToughness = 20;
            }
            double reducedArmorPoints = armorPoints - 4 * damage / (cappedArmorToughness + 8) ;
            if (reducedArmorPoints <= armorPoints/5) {
                reducedArmorPoints = armorPoints/5;
            }
            if (reducedArmorPoints >= 25) {
                calculatedDamage = 0;
            } else {
                calculatedDamage -= calculatedDamage * reducedArmorPoints / 25;
            }
            Bukkit.broadcastMessage("Damage after Armor: " + calculatedDamage);
            //damageReducedByArmor
            double calculatedDamageReducedByArmor = damage - calculatedDamage;
            Bukkit.broadcastMessage(ChatColor.YELLOW + "Damage reduced by Armor: " + calculatedDamageReducedByArmor);
            Bukkit.broadcastMessage("Original damage reduced by armor: " + String.valueOf(-armorReducedDamage));
            String coloredString = "";
            if (Math.abs(calculatedDamageReducedByArmor + armorReducedDamage) >= 0.00001) {
                coloredString = "" + ChatColor.RED;
            }
            Bukkit.broadcastMessage(coloredString + "OFFSET: " + (calculatedDamageReducedByArmor + armorReducedDamage));

            if (armorPoints >= 25) {
                calculatedDamageWithoutArmorToughness = 0;
            } else {
                calculatedDamageWithoutArmorToughness -= calculatedDamageWithoutArmorToughness * armorPoints / 25;
            }
            Bukkit.broadcastMessage(ChatColor.YELLOW + "Damage after Armor Without ArmorToughness: " + calculatedDamageWithoutArmorToughness);

            int playerEPF = PlayerU.getPlayerEPF(player);  //org.ajls.lib.utils.PlayerU.
            if (playerEPF > 25) {
                calculatedDamage = 0;
            }
            else {
                calculatedDamage -= calculatedDamage * playerEPF / 25;
            }
            Bukkit.broadcastMessage("Damage after Protection: " + calculatedDamage);

            int resistanceAmplifier = 0;
            if (player.getPotionEffect(PotionEffectType.RESISTANCE) != null) {
                resistanceAmplifier = player.getPotionEffect(PotionEffectType.RESISTANCE).getAmplifier();
            }
            Bukkit.broadcastMessage("Resistance Amplifier: " + resistanceAmplifier);
            if (resistanceAmplifier >= 4) {
                calculatedDamage = 0;
            }
            else {
                calculatedDamage -= calculatedDamage * (resistanceAmplifier + 1) / 5;
            }
            Bukkit.broadcastMessage("Damage after Resistance: " + calculatedDamage);
            double damageAfterResistance = calculatedDamage;


            Bukkit.broadcastMessage("original final damage: " +finalDamage);
            coloredString = "";
            if (Math.abs(calculatedDamage-finalDamage) >= 0.00001 ) {
                coloredString = "" + ChatColor.RED;
            }
            Bukkit.broadcastMessage(coloredString + "Damage offset: " + (calculatedDamage-finalDamage));

//            if (calculatedDamage-finalDamage != 0) {
//                Bukkit.broadcastMessage(ChatColor.RED + "THERE IS OFFSET");
//            }
            event.setDamage(0.00001);
//            event.setDamage(0.00001);
//            org.ajls.lib.utils.PlayerU.addHealth(player, -calculatedDamage);

        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onEntityDamage(EntityDamageEvent event) {
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
