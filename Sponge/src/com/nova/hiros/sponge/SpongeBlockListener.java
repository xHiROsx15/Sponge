package com.nova.hiros.sponge;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class SpongeBlockListener implements Listener
{
  private final Sponge plugin;

  public SpongeBlockListener(Sponge plugin)
  {
    this.plugin = plugin;
  }

  public void onBlockFromTo(BlockFromToEvent event)
  {
    World world = event.getBlock().getWorld();
    Block blockFrom = event.getBlock();
    Block blockTo = event.getToBlock();
    int sx = blockTo.getX();
    int sy = blockTo.getY();
    int sz = blockTo.getZ();
    boolean isLava = (blockFrom.getTypeId() == 10) || (blockFrom.getTypeId() == 11);
    boolean isWater = (blockFrom.getTypeId() == 8) || (blockFrom.getTypeId() == 9);
    if (((this.plugin.lavaSponge) && (isLava)) || ((this.plugin.waterSponge) && (isWater)))
    {
      for (int cx = -this.plugin.spongeRadius; cx <= this.plugin.spongeRadius; cx++)
      {
        for (int cy = -this.plugin.spongeRadius; cy <= this.plugin.spongeRadius; cy++)
        {
          for (int cz = -this.plugin.spongeRadius; cz <= this.plugin.spongeRadius; cz++)
            if (world.getBlockTypeIdAt(sx + cx, sy + cy, sz + cz) == 19)
            {
              event.setCancelled(true);
              return;
            }
        }
      }
    }
  }

  public void onBlockPlace(BlockPlaceEvent event)
  {
    Block blockPlaced = event.getBlock();
    World world = blockPlaced.getWorld();
    if (blockPlaced.getTypeId() == 19)
    {
      int sx = blockPlaced.getX();
      int sy = blockPlaced.getY();
      int sz = blockPlaced.getZ();
      for (int cx = -this.plugin.spongeRadius; cx <= this.plugin.spongeRadius; cx++)
      {
        for (int cy = -this.plugin.spongeRadius; cy <= this.plugin.spongeRadius; cy++)
        {
          for (int cz = -this.plugin.spongeRadius; cz <= this.plugin.spongeRadius; cz++)
          {
            int id = world.getBlockTypeIdAt(sx + cx, sy + cy, sz + cz);
            if (((this.plugin.lavaSponge) && ((id == 10) || (id == 11))) || ((this.plugin.waterSponge) && ((id == 8) || (id == 9))))
              world.getBlockAt(sx + cx, sy + cy, sz + cz).setTypeId(0);
          }
        }
      }
    }
  }
}