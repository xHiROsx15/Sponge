package com.nova.hiros.sponge;

import java.io.File;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Sponge extends JavaPlugin
{

  public static Server server;
  int spongeRadius;
  boolean waterSponge;
  boolean lavaSponge;
  public static iProperty Settings;
  public static String directory = "Sponge" + File.separator;

  public void onEnable()
  {
    PluginDescriptionFile pdfFile = getDescription();
    System.out.println(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled by Joshcvb!");
    setup();
    getServer().getPluginManager().registerEvents(new SpongeBlockListener(this), this);
    getServer().getPluginManager().registerEvents(new SpongeBlockListener(this), this);
    getServer().getPluginManager().registerEvents(new SpongeBlockListener(this), this);
  }

  public void onDisable()
  {
    System.out.println("see ya!");
  }

  public boolean isDebugging(Player player)
  {
    //if (this.debugees.containsKey(player)) {
      //return ((Boolean)this.debugees.get(player)).booleanValue();
    //}
    return false;
  }

  public void setup()
  {
    new File(directory).mkdir();
    Settings = new iProperty(directory + "config.yml");
    this.waterSponge = Settings.getBoolean("water-sponge-working", false);
    this.spongeRadius = (Math.max(1, Settings.getInt("sponge-radius", 3)) - 1);
    this.lavaSponge = Settings.getBoolean("lava-sponge-working", true);
  }
}