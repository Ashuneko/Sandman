package ashuneko.sandman;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public final class Sandman extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
        BukkitScheduler scheduler = getServer().getScheduler();

// Schedule a task to be executed every 1 tick
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {

// Code to be executed every tick
                for (Player pl : Bukkit.getServer().getOnlinePlayers()) {
                    for (Entity e : pl.getNearbyEntities(0.1, 0.1, 0.1)) {
                        if (((e instanceof FallingBlock &&  ((FallingBlock) e).getBlockData().getMaterial() == Material.SAND))) {
                            if (pl.hasMetadata("sandman")){
                            e.remove(); }

                        }
                    }
                }
            }
        }, 0L, 1L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    //Passive 1
    public void bah(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Block b = p.getTargetBlockExact(4);
        if (p.getInventory().getItemInMainHand().getItemMeta().getLore().contains(ChatColor.WHITE + "Right-Click " + ChatColor.GOLD + "» " + ChatColor.GRAY + "Sandman")) {
            if (e.getAction() == Action.LEFT_CLICK_BLOCK && b.getType() == Material.SAND) {
                b.breakNaturally();
            }
        }
    }

    @EventHandler
    //Primary
    public void event(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (p.getInventory().getItemInMainHand().getItemMeta().getLore().contains(ChatColor.WHITE + "Right-Click " + ChatColor.GOLD + "» " + ChatColor.GRAY + "Sandman")) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

                //top layer
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(-1, 14, -1), Material.SAND.createBlockData());
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(1, 14, 0), Material.SAND.createBlockData());
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(1, 14, 1), Material.SAND.createBlockData());
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(1, 14, -1), Material.SAND.createBlockData());
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(0, 14, 0), Material.SAND.createBlockData());
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(0, 14, 1), Material.SAND.createBlockData());
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(0, 14, -1), Material.SAND.createBlockData());
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(-1, 14, 0), Material.SAND.createBlockData());
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(-1, 14, 1), Material.SAND.createBlockData());
                //middle layer
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(-1, 12, 1), Material.SAND.createBlockData());
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(-1, 12, 0), Material.SAND.createBlockData());
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(-1, 12, -1), Material.SAND.createBlockData());
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(1, 12, 1), Material.SAND.createBlockData());
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(1, 12, 0), Material.SAND.createBlockData());
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(1, 12, -1), Material.SAND.createBlockData());
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(0, 12, 1), Material.SAND.createBlockData());
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(0, 12, 0), Material.SAND.createBlockData());
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(0, 12, -1), Material.SAND.createBlockData());
                //bottom layer
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(-1, 10, -1), Material.SAND.createBlockData());
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(-1, 10, 0), Material.SAND.createBlockData());
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(-1, 10, 1), Material.SAND.createBlockData());
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(0, 10, -1), Material.SAND.createBlockData());
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(0, 10, 0), Material.SAND.createBlockData());
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(0, 10, 1), Material.SAND.createBlockData());
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(1, 10, -1), Material.SAND.createBlockData());
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(1, 10, 0), Material.SAND.createBlockData());
                p.getWorld().spawnFallingBlock(p.getLocation().clone().add(1, 10, 1), Material.SAND.createBlockData());

            }


        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (label.equalsIgnoreCase("Sandman")) {
            ArrayList<String> lore = new ArrayList<>();
            ItemStack i = new ItemStack(Material.WOODEN_SHOVEL);
            ItemMeta im = i.getItemMeta();
            ;
            im.setDisplayName(ChatColor.YELLOW + "Sandman");
            lore.add(ChatColor.WHITE + "Right-Click " + ChatColor.GOLD + "» " + ChatColor.GRAY + "Sandman");
            im.setLore(lore);
            i.setItemMeta(im);
            p.getInventory().addItem(i);
            p.setMetadata("sandman",new FixedMetadataValue(this,"ftay"));
        }


        return false;
    }

    @EventHandler
    public void preventPath(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Block b = p.getTargetBlockExact(4);
        if (!p.getInventory().getItemInMainHand().getItemMeta().getLore().contains(ChatColor.WHITE + "Right-Click " + ChatColor.GOLD + "» " + ChatColor.GRAY + "Sandman")) {
            return;
        }
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && b.getType() == Material.GRASS_BLOCK) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void spawnHusk(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (p.getInventory().getItemInMainHand().getItemMeta().getLore().contains(ChatColor.WHITE + "Right-Click " + ChatColor.GOLD + "» " + ChatColor.GRAY + "Sandman")) {
            if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {


            }
        }
    }
}