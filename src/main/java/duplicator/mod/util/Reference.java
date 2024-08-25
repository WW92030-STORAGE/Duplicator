package duplicator.mod.util;

import duplicator.mod.blocks.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.HashSet;

public class Reference {
    public static final String MODID = "duplicator";
    public static final String NAME = "DUPLICATOR";
    public static final String VERSION = "1.0.0";

    public static HashSet<Block> blacklist = new HashSet<Block>();

    public static void initBlackList() {
        blacklist.add(ModBlocks.DUPLICATOR.get());
        blacklist.add(Blocks.IRON_BLOCK);
        blacklist.add(Blocks.IRON_ORE);
        blacklist.add(Blocks.EMERALD_BLOCK);
        blacklist.add(Blocks.EMERALD_ORE);
    }
}
