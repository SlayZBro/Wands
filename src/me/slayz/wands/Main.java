package me.slayz.wands;

import me.slayz.wands.commands.GetWand;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main instance = null;

    @Override
    public void onEnable() {
        setInstance(this);
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"Wands plugin has been enabled");
        getCommand("getwand").setExecutor(new GetWand());
        getServer().getPluginManager().registerEvents(new UseWand(),this);

        UseWand.run();

        getConfig().options().copyDefaults(true);
        getConfig().addDefault("names.blue","&9Blue Wand");
        getConfig().addDefault("names.light","&bLight Wand");
        getConfig().addDefault("names.lime","&aLime Wand");
        getConfig().addDefault("names.red","&4Red Wand");
        getConfig().addDefault("names.green","&2Green Wand");
        getConfig().addDefault("names.pink","&dPink Wand");
        saveConfig();




    }

    public static void setInstance(Main m){
        instance = m;
    }
}
