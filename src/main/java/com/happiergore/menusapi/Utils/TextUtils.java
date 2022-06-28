package com.happiergore.menusapi.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bukkit.ChatColor;

/**
 *
 * @author HappierGore
 */
public final class TextUtils {

    public String parseColor(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public List<String> parseColor(List<String> text) {
        List result = new ArrayList<>();
        for (String line : text) {
            result.add(this.parseColor(line));
        }
        return result;
    }

    public int getOneNumber(String text) {
        String result = "0";
        for (String entry : text.split("")) {
            try {
                Integer.parseInt(entry);
                result += entry;
            } catch (NumberFormatException ex) {
            }
        }
        return Integer.parseInt(result);
    }

    public String rawText(String coloredText) {
        Set<String> exceptions = new HashSet<>();
        exceptions.add("§1");
        exceptions.add("§2");
        exceptions.add("§3");
        exceptions.add("§4");
        exceptions.add("§5");
        exceptions.add("§6");
        exceptions.add("§7");
        exceptions.add("§8");
        exceptions.add("§9");
        exceptions.add("§a");
        exceptions.add("§b");
        exceptions.add("§c");
        exceptions.add("§d");
        exceptions.add("§e");
        exceptions.add("§f");
        exceptions.add("§l");
        exceptions.add("§o");
        exceptions.add("§m");
        exceptions.add("§n");
        exceptions.add("§k");
        String result = coloredText;
        for (String exception : exceptions) {
            result = result.replaceAll(exception, "");
        }
        return result;
    }
}
