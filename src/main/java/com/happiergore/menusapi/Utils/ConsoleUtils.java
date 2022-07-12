package com.happiergore.menusapi.Utils;

import java.util.List;

/**
 *
 * @author HappierGore
 */
public class ConsoleUtils {

    private final TextUtils textUtils = new TextUtils();

    public static final String PLUGIN_NAME = "MenusAPI";

    public void errorMsg(String msg) {
        String err = "[" + PLUGIN_NAME + "] &c[Error]:&r ";
        System.out.println(textUtils.parseColor(err + msg));
    }

    public void warnMsg(String msg) {
        String warn = "[" + PLUGIN_NAME + "] &6[Warning]:&r ";
        System.out.println(textUtils.parseColor(warn + msg));
    }

    public void infoMsg(String msg) {
        String info = "[" + PLUGIN_NAME + "] [Info]:&r ";
        System.out.println(textUtils.parseColor(info + msg));
    }

    public void normalMsg(String msg) {
        String info = "[" + PLUGIN_NAME + "] ";
        System.out.println(textUtils.parseColor(info + msg));
    }

    public void normalMsg(List<String> msg) {
        String info = "[" + PLUGIN_NAME + "] ";
        System.out.println(textUtils.parseColor(info + String.join("\n", msg)));
    }

    public void loggerMsg(List<String> msg) {
        String info = "\n&3------------------------- §3 " + PLUGIN_NAME + " - Logger §3--------------------------\n";
        msg.add("&3---------------------------------------------------------------------------");
        System.out.println(textUtils.parseColor(info + String.join("\n", msg)));
    }

}
