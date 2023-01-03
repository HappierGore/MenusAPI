package com.happiergore.menusapi;

import com.happiergore.menusapi.Utils.ConsoleUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
    @Override
    public void onEnable() {
        new ConsoleUtils().infoMsg("&aMenus API Loaded Successfully");
    }

}
