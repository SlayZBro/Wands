package me.slayz.wands;

import net.minecraft.server.v1_14_R1.PacketPlayOutWorldParticles;
import net.minecraft.server.v1_14_R1.Particles;
import org.apache.commons.lang.Validate;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.Hash;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;

public class UseWand implements Listener {

    public static HashMap<Player,HashMap<String,Integer>> cooldown = new HashMap<>();

    @EventHandler
    public void use(PlayerInteractEvent e){
        if(e.getItem() != null && e.getItem().hasItemMeta() && e.getItem().getItemMeta().getDisplayName() != null){
            Player p = e.getPlayer();
            String x = e.getItem().getItemMeta().getDisplayName();
            HashMap<String,Integer> hash = null;

            if(cooldown.containsKey(p))
                hash = cooldown.get(p);
            else
                hash = new HashMap<>();

            if(x.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',Main.instance.getConfig().getString("names.blue")))) {
                if(check(p,"blue")) {
                    drawLine(p.getLocation().add(0, 1.5, 0), p.getTargetBlock(null, 20).getLocation(), 1, "blue");
                    hitPlayer(p,"blue");
//                    hash.put("blue",1);

                    cooldown.put(p, hash);

                }
            }

            else if(x.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',Main.instance.getConfig().getString("names.light")))) {
                if(check(p,"light")) {
                    drawLine(p.getLocation().add(0, 1.5, 0), p.getTargetBlock(null, 20).getLocation(), 1, "light");
                    hitPlayer(p,"light");
//                    hash.put("lime",1);

                    cooldown.put(p, hash);
                }
            }

            else if(x.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',Main.instance.getConfig().getString("names.lime")))) {
                if(check(p,"lime")) {
                    drawLine(p.getLocation().add(0, 1.5, 0), p.getTargetBlock(null, 20).getLocation(), 1, "lime");
                    hitPlayer(p,"lime");
                    hash.put("lime",8);
                    cooldown.put(p, hash);
                }
            }
            else if(x.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',Main.instance.getConfig().getString("names.red")))) {
                if(check(p,"red")) {
                    drawLine(p.getLocation().add(0, 1.5, 0), p.getTargetBlock(null, 20).getLocation(), 1, "red");
                    hitPlayer(p,"red");
                    hash.put("red",1);
                    cooldown.put(p, hash);
                }
            }
            else if(x.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',Main.instance.getConfig().getString("names.green")))) {
                if(check(p,"green")) {
                    drawLine(p.getLocation().add(0, 1.5, 0), p.getTargetBlock(null, 20).getLocation(), 1, "green");
                    hitPlayer(p,"green");
                    hash.put("green",10);
                    cooldown.put(p, hash);
                }
            }
            else if(x.equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',Main.instance.getConfig().getString("names.pink")))) {
                if(check(p,"pink")) {
                    drawLine(p.getLocation().add(0, 1.5, 0), p.getTargetBlock(null, 20).getLocation(), 1, "pink");
                    hitPlayer(p,"pink");
                    hash.put("pink",120);
                    cooldown.put(p, hash);
                }
            }
        }
    }

    public void drawLine(Location point1, Location point2, double space,String color) {
        World world = point1.getWorld();
        Validate.isTrue(point2.getWorld().equals(world), "Lines cannot be in different worlds!");
        double distance = point1.distance(point2);
        Vector p1 = point1.toVector();
        Vector p2 = point2.toVector();
        Vector vector = p2.clone().subtract(p1).normalize().multiply(space);
        double length = 0;
        for (; length < distance; p1.add(vector)) {

            if(color.equalsIgnoreCase("blue")){
                world.spawnParticle(Particle.REDSTONE, p1.toLocation(world), 0, 0, 0, 0, new Particle.DustOptions(Color.BLUE, 1));


//                world.spawnParticle(Particle.REDSTONE, p1.getX(), p1.getY(), p1.getZ(), 0,
//                        0.001, //red
//                        0, // green
//                        1, //blue
//                        1);
            }

            else if(color.equalsIgnoreCase("light"))
                world.spawnParticle(Particle.REDSTONE, p1.toLocation(world), 0, 0, 0, 0, new Particle.DustOptions(Color.AQUA, 1));

//                world.spawnParticle(Particle.REDSTONE, p1.getX(), p1.getY(), p1.getZ(), 0,
//                        0.0001, //red
//                        1, // green
//                        1, //blue
//                        1);
            else if(color.equalsIgnoreCase("green"))
                world.spawnParticle(Particle.REDSTONE, p1.toLocation(world), 0, 0, 0, 0, new Particle.DustOptions(Color.GREEN, 1));

//                world.spawnParticle(Particle.REDSTONE, p1.getX(), p1.getY(), p1.getZ(), 0,
//                        0.0001, //red
//                        1, // green
//                        0, //blue
//                        1);
            else if(color.equalsIgnoreCase("red"))
                world.spawnParticle(Particle.REDSTONE, p1.toLocation(world), 0, 0, 0, 0, new Particle.DustOptions(Color.RED, 1));

//                world.spawnParticle(Particle.REDSTONE, p1.getX(), p1.getY(), p1.getZ(), 0,
//                        1, //red
//                        0.0001, // green
//                        0, //blue
//                        1);

            else if(color.equalsIgnoreCase("lime"))
                world.spawnParticle(Particle.REDSTONE, p1.toLocation(world), 0, 0, 0, 0, new Particle.DustOptions(Color.LIME, 1));

//                world.spawnParticle(Particle.REDSTONE, p1.getX(), p1.getY(), p1.getZ(), 0,
//                        0, //red
//                        1, // green
//                        0.0001, //blue
//                        1);

            else if(color.equalsIgnoreCase("pink"))
                world.spawnParticle(Particle.REDSTONE, p1.toLocation(world), 0, 0, 0, 0, new Particle.DustOptions(Color.ORANGE, 1));

//                world.spawnParticle(Particle.REDSTONE, p1.getX(), p1.getY(), p1.getZ(), 0,
//                        Color.YELLOW.getRed(), //red
//                        Color.YELLOW.getGreen(), // green
//                        Color.YELLOW.getBlue(), //blue
//                        1);

            length += space;
        }
    }

    public static void run(){
        new BukkitRunnable(){

            @Override
            public void run(){

                for(Player p : cooldown.keySet()){
                    for(String x : cooldown.get(p).keySet())
                    if(cooldown.get(p).get(x)<=0)
                        cooldown.get(p).remove(x);
                    else {
                        HashMap<String,Integer> map = cooldown.get(p);
                        map.put(x,cooldown.get(p).get(x)-1);
                        cooldown.put(p, map);
                    }
                }

            }


        }.runTaskTimer(Main.instance,0,10);
    }

    public void hitPlayer(Player player, String x) {
        Entity entity = getNearestEntityInSight(player,20);
        if(entity!=null && entity instanceof LivingEntity) {

            LivingEntity target = (LivingEntity) entity;
            if(x.equals("blue")){
                if(!(target instanceof Player))
                    target.damage(2);

            }
            else if(x.equals("light")){
                if(!(target instanceof Player))
                    target.damage(4);
            }
            else if(x.equals("lime")) {
                if(target instanceof Player) {
                    if (target.getHealth() > 12)
                        target.setHealth(20);
                    else
                        target.setHealth(target.getHealth() + 8);
                }
            }
            else if(x.equals("red")){
                if(!(target instanceof Player)) {
                    target.damage(6);
                    target.setFireTicks(40);
                }
            }
            else if(x.equals("green")){
                if(!(target instanceof Player)) {

                    target.damage(5);
                    new FrozenEntity(Main.instance, target);
                }

            }
            else if(x.equals("pink")){
                target.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE,30*20,1));
                target.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,20*20,1));

            }


        }
    }


    public static Entity getNearestEntityInSight(Player player, int range) {
        ArrayList<Entity> entities = (ArrayList<Entity>) player.getNearbyEntities(range, range, range);
        ArrayList<Block> sightBlock = (ArrayList<Block>) player.getLineOfSight(null, range);
        ArrayList<Location> sight = new ArrayList<Location>();
        for (int i = 0;i<sightBlock.size();i++)
            sight.add(sightBlock.get(i).getLocation());
        for (int i = 0;i<sight.size();i++) {
            for (int k = 0; k < entities.size(); k++) {
                if (Math.abs(entities.get(k).getLocation().getX() - sight.get(i).getX()) < 1.3) {
                    if (Math.abs(entities.get(k).getLocation().getY() - sight.get(i).getY()) < 1.5) {
                        if (Math.abs(entities.get(k).getLocation().getZ() - sight.get(i).getZ()) < 1.3) {
                            return entities.get(k);
                        }

                    }
                }
            }
        }
        return null;
    }

    public boolean check(Player p,String x){
        if(cooldown.containsKey(p)){
            if(cooldown.get(p).containsKey(x)) {
                p.sendMessage(ChatColor.RED + "You can use this weapon in " + cooldown.get(p).get(x)/2 + " seconds.");
                return false;
            }
        }
        return true;
    }


}
