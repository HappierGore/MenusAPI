package com.happiergore.menusapi.Utils;

import de.tr7zw.nbtapi.NBTItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author HappierGore
 */
public class ItemUtils {

    private final TextUtils text = new TextUtils();

    public ItemStack generateItem(Map<Enchantment, Integer> enchantments, Material material, String displayName, List<String> lore, List<ItemFlag> flags) {
        ItemMeta itemMeta = Bukkit.getItemFactory().getItemMeta(material);

        if (enchantments != null) {
            enchantments.forEach((enchant, level) -> {
                itemMeta.addEnchant(enchant, level, true);
            });
        }

        if (displayName != null) {
            itemMeta.setDisplayName(text.parseColor(displayName));
        }

        if (lore != null) {
            itemMeta.setLore(text.parseColor(lore));
        }

        if (flags != null) {
            flags.forEach(flag -> {
                itemMeta.addItemFlags(flag);
            });
        }

        ItemStack newItem = new ItemStack(material);
        newItem.setItemMeta(itemMeta);
        return newItem;
    }

    //*********************
    //      Helper
    //*********************
    /**
     * Removes all exceptions into exceptions list to the NBT provided
     *
     * @param nbtItem The NBT container of the item.
     * @param exceptions A list with all exceptions to ignore
     */
    public static void filterNBT(NBTItem nbtItem, List<String> exceptions) {
        List<String> cloneNBT = new ArrayList<>();
        cloneNBT.addAll(nbtItem.getKeys());

        cloneNBT.forEach(key -> {
            if (exceptions.contains(key)) {
                nbtItem.removeKey(key);
            }
        });
    }

    /**
     * Compare two items using NBT containers
     *
     * @param item1 Item 1
     * @param item2 Item 2
     * @param NBTExceptions exceptions
     * @return boolean
     */
    public boolean compareItems(ItemStack item1, ItemStack item2, List<String> NBTExceptions) {
        if (NBTExceptions == null) {
            NBTExceptions = new ArrayList<>();
        }

        NBTItem nbtItem1 = new NBTItem(item1);
        filterNBT(nbtItem1, NBTExceptions);
        String strNBTItem1 = NBTItem.convertItemtoNBT(nbtItem1.getItem()).toString().replace("tag:{},", "");

        NBTItem nbtItem2 = new NBTItem(item2);
        filterNBT(nbtItem2, NBTExceptions);
        String strNBTItem2 = NBTItem.convertItemtoNBT(nbtItem2.getItem()).toString().replace("tag:{},", "");

        return strNBTItem1.equals(strNBTItem2);
    }

    /**
     * If the item has displayname, will be remove it.
     *
     * @param item The item to remove displayName
     */
    public void removeDisplayName(ItemStack item) {
        ItemMeta itemMeta = item.hasItemMeta() ? item.getItemMeta() : null;
        if (itemMeta == null) {
            return;
        }
        if (itemMeta.hasDisplayName()) {
            itemMeta.setDisplayName(null);
        }
        item.setItemMeta(itemMeta);
    }

    /**
     * If the item has lore, will be remove it.
     *
     * @param item The item to remove lore
     */
    public void removeLore(ItemStack item) {
        ItemMeta itemMeta = item.hasItemMeta() ? item.getItemMeta() : null;
        if (itemMeta == null) {
            return;
        }
        if (itemMeta.hasLore()) {
            itemMeta.setLore(null);
        }
        item.setItemMeta(itemMeta);
    }

    /**
     * If the item has enchantments, will be remove it.
     *
     * @param item The item to remove enchantments
     */
    public void removeEnchantments(ItemStack item) {
        ItemMeta itemMeta = item.hasItemMeta() ? item.getItemMeta() : null;
        if (itemMeta == null) {
            return;
        }
        ItemMeta itemResult = itemMeta.clone();
        if (itemMeta.hasEnchants()) {
            itemMeta.getEnchants().keySet().forEach(key -> {
                itemResult.removeEnchant(key);
            });
        }
        item.setItemMeta(itemResult);
    }
}
