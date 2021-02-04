package me.slayz.wands.commands;

import me.slayz.wands.Main;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GetWand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender instanceof Player && commandSender.isOp()){

            Player p = (Player) commandSender;

            if(args.length==1){

                if(args[0].equalsIgnoreCase("blue")){
                    getWand(p,"Blue");
                }else if(args[0].equalsIgnoreCase("light"))
                    getWand(p,"Light");
                else if(args[0].equalsIgnoreCase("lime"))
                    getWand(p,"Lime");
                else if(args[0].equalsIgnoreCase("red"))
                    getWand(p,"Red");
                else if(args[0].equalsIgnoreCase("green"))
                    getWand(p,"Green");
                else if(args[0].equalsIgnoreCase("pink"))
                    getWand(p,"Pink");
                else
                    p.sendMessage(ChatColor.RED+"/getwand [blue/light/lime/red/green/pink]");


            }else
                p.sendMessage(ChatColor.RED+"/getwand [blue/light/lime/red/green/pink]");

        }else{
            commandSender.sendMessage(ChatColor.RED+"You can't perform this command.");
        }


        return true;
    }

    public void getWand(Player p,String name){
        ItemStack item = null;


        switch(name){
            case "Blue":
                item = new ItemStack(Material.BLUE_DYE);
                break;
            case "Light":
                item = new ItemStack(Material.LIGHT_BLUE_DYE);
                break;
            case "Lime":
                item = new ItemStack(Material.LIME_DYE);
                break;
            case "Red":
                item = new ItemStack(Material.RED_DYE);
                break;
            case "Green":
                item = new ItemStack(Material.GREEN_DYE);
                break;
            case "Pink":
                item = new ItemStack(Material.PINK_DYE);
                break;

        }

        name = ChatColor.translateAlternateColorCodes('&', Main.instance.getConfig().getString("names."+name.toLowerCase()));


        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
        p.getInventory().addItem(item);



    }
}
