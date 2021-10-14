package duplicator.mod.init;

import java.util.ArrayList;
import java.util.List;

import duplicator.mod.blocks.BlockDuplicator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockInit {
public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block DUPLICATOR = new BlockDuplicator("duplicator", Material.IRON, CreativeTabs.REDSTONE);
}
