package fr.soraxdubbing.saostats.commands;

import app.ashcon.intake.Command;
import app.ashcon.intake.bukkit.parametric.annotation.Sender;
import de.tr7zw.nbtapi.NBTItem;
import fr.soraxdubbing.saostats.ItemInformations;
import fr.soraxdubbing.saostats.module.annoted.Hand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DamageCommand {

    @Command(
            aliases = "max",
            desc = "Set the max damage of an item",
            perms = "SAOStats.stats.damage.max",
            usage = "[max]"
    )
    public void max(@Sender Player player, @Hand ItemStack item, Double max) {
        NBTItem nbtItem = new NBTItem(item);

        ItemInformations itemInformations = new ItemInformations();
        if(nbtItem.hasKey("ItemInformations")){
            itemInformations = nbtItem.getObject("ItemInformations", ItemInformations.class);
        }

        if(itemInformations.hasDoubleAttribute("damage.max")){
            itemInformations.removeDoubleAttribute("damage.max");
        }
        itemInformations.addDoubleAttribute("damage.max", max);
        player.sendMessage("§aVous avez défini le max de dégâts de l'item en main à " + max);

        nbtItem.setObject("ItemInformations", itemInformations);
        nbtItem.getItem();
        player.getInventory().setItemInMainHand(nbtItem.getItem());
    }

    @Command(
            aliases = "min",
            desc = "Set the min damage of an item",
            perms = "SAOStats.stats.damage.min",
            usage = "[min]"
    )
    public void min(@Sender Player player, @Hand ItemStack item, Double min) {
        NBTItem nbtItem = new NBTItem(item);

        ItemInformations itemInformations = new ItemInformations();
        if(nbtItem.hasKey("ItemInformations")){
            itemInformations = nbtItem.getObject("ItemInformations", ItemInformations.class);
        }

        if(itemInformations.hasDoubleAttribute("damage.min")){
            itemInformations.removeDoubleAttribute("damage.min");
        }

        itemInformations.addDoubleAttribute("damage.min", min);
        player.sendMessage("§aVous avez défini le min de dégâts de l'item en main à " + min);

        nbtItem.setObject("ItemInformations", itemInformations);
        nbtItem.getItem();
        player.getInventory().setItemInMainHand(nbtItem.getItem());
    }
}
