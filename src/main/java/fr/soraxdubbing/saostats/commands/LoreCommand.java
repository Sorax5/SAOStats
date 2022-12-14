package fr.soraxdubbing.saostats.commands;

import app.ashcon.intake.Command;
import app.ashcon.intake.bukkit.parametric.annotation.Sender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class LoreCommand {

    @Command(
            aliases = "set",
            desc = "Set the lore of an item",
            perms = "SAOStats.lore.set",
            usage = "[line] [lore]"
    )
    public void set(@Sender Player player, int a, String b) {
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = item.getItemMeta();

        List<String> lore = itemMeta.getLore();
        if (lore == null) {
            lore = new ArrayList<>();
        }

        try{
            lore.set(a,b);
        }
        catch (IndexOutOfBoundsException e){
            lore.add(b);
        }
        player.sendMessage("§aLa ligne " + a + " a été définie à " + b);

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
    }

    @Command(
            aliases = "add",
            desc = "Add a line to the lore of an item",
            perms = "SAOStats.lore.add",
            usage = "[lore]"
    )
    public void add(@Sender Player player, String b) {
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = item.getItemMeta();

        List<String> lore = itemMeta.getLore();
        if (lore == null) {
            lore = new ArrayList<>();
        }
        lore.add(b);
        player.sendMessage("§a La ligne " + b + " a été ajoutée");

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
    }

    @Command(
            aliases = "remove",
            desc = "Remove a line from the lore of an item",
            perms = "SAOStats.lore.remove",
            usage = "[line]"
    )
    public void remove(@Sender Player player, int b) {
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item.getType().isAir()) {
            player.sendMessage("§cVous devez tenir un item en main !");
            return;
        }

        ItemMeta itemMeta = item.getItemMeta();

        List<String> lore = itemMeta.getLore();
        if (lore == null) {
            lore = new ArrayList<>();
        }

        try{
            lore.remove(b);
            player.sendMessage("§aLa ligne " + b + " a été supprimée");
        }
        catch (IndexOutOfBoundsException e){
            player.sendMessage("§cThis line doesn't exist !");
        }

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
    }

    @Command(
            aliases = "clear",
            desc = "remove all the lore of an item",
            perms = "SAOStats.lore.clear",
            usage = "[line]"
    )
    public void clear(@Sender Player player) {
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item.getType().isAir()) {
            player.sendMessage("§cVous devez tenir un item en main !");
            return;
        }

        ItemMeta itemMeta = item.getItemMeta();

        itemMeta.setLore(new ArrayList<>());
        player.sendMessage("§aLe lore a été supprimée");
        item.setItemMeta(itemMeta);
    }
}
