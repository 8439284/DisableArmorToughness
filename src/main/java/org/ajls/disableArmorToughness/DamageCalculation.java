package org.ajls.disableArmorToughness;

import org.ajls.lib.utils.PlayerU;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class DamageCalculation {
    public static double calculateResistance(Player player, double damage) {
        double calculatedDamage = damage;
        int resistanceAmplifier = 0;
        if (player.getPotionEffect(PotionEffectType.RESISTANCE) != null) {
            resistanceAmplifier = player.getPotionEffect(PotionEffectType.RESISTANCE).getAmplifier();
        }
        if (resistanceAmplifier >= 5) {
            calculatedDamage = 0;
        }
        else {
            calculatedDamage -= calculatedDamage * resistanceAmplifier / 5;
        }
        return calculatedDamage;
    }


    public static double calculateArmor(Player player, double damage, boolean considerArmorToughness) {
        double calculatedDamage = damage;
        double armorPoints = 0;
        if (player.getAttribute(Attribute.GENERIC_ARMOR) != null) {
            armorPoints = player.getAttribute(Attribute.GENERIC_ARMOR).getValue();
        }
        double armorPointsBeforeToughnessReduction = armorPoints;
        if (considerArmorToughness) {
            armorPoints = calculateArmorPointsAfterArmorToughnessReduction(player, armorPointsBeforeToughnessReduction, calculatedDamage);
//            double armorToughness = 0;
//            if (player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS) != null) {
//                armorToughness = player.getAttribute(Attribute.GENERIC_ARMOR).getValue();
//            }
////            double cappedArmorToughness = armorToughness;
////            if (armorToughness > 20) {
////                cappedArmorToughness = 20;
////            }
//            if (armorToughness > 20) {
//                armorToughness = 20;
//            }
//            armorPoints = armorPoints - armorPoints * 4 * damage / (armorToughness + 8) ;
//            if (armorPointsBeforeToughnessReduction/5 > armorPoints) {
//                armorPoints = armorPointsBeforeToughnessReduction/5;
//            }
        }
        if (armorPoints >= 25) {
            calculatedDamage = 0;
        } else {
            calculatedDamage -= calculatedDamage * armorPoints / 25;
        }
        return calculatedDamage;
    }

    public static double calculateArmor(Player player, double damage) {
        return calculateArmor(player, damage, true);
    }

    public static double calculateArmorPointsAfterArmorToughnessReduction(Player player, double armorPoints, double damage) {
        double calculatedArmorPoints = armorPoints;
        double armorToughness = 0;
        if (player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS) != null) {
            armorToughness = player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).getValue();
        }
//            double cappedArmorToughness = armorToughness;
//            if (armorToughness > 20) {
//                cappedArmorToughness = 20;
//            }
        if (armorToughness > 20) {
            armorToughness = 20;
        }
        calculatedArmorPoints = calculatedArmorPoints - 4 * damage / (armorToughness + 8) ;
        if (armorPoints/5 > calculatedArmorPoints) {
            calculatedArmorPoints = armorPoints/5;
        }
        return calculatedArmorPoints;
    }

    public static double calculateEPF(Player player, double damage) {
        double calculatedDamage = damage;
        int playerEPF = PlayerU.getPlayerEPF(player);  //org.ajls.lib.utils.PlayerU.
        if (playerEPF > 25) {
            calculatedDamage = 0;
        }
        else {
            calculatedDamage -= calculatedDamage * playerEPF / 25;
        }
        return calculatedDamage;
    }
}
