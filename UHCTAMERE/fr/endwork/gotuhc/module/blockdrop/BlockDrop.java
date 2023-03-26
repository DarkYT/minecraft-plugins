package fr.endwork.gotuhc.module.blockdrop;

import lombok.Data;
import org.bukkit.inventory.ItemStack;

@Data
public class BlockDrop {

  private final ItemStack item;
  private final boolean override;

}
