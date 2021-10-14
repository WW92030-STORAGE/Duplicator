package duplicator.mod.util;

import java.util.HashSet;

import duplicator.mod.init.BlockInit;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class Reference {
	public static final String MODID = "duplicator";
	public static final String NAME = "Orespawn-esque Duplicator Mod";
	public static final String VERSION = "1.0.0";
	public static final String COMMON = "duplicator.mod.proxy.CommonProxy";
	public static final String CLIENT = "duplicator.mod.proxy.ClientProxy";
	
	public static final double DEG = 180.0 / Math.PI;
	public static final double TAU = 2.0 * Math.PI;
	public static final double EPSILON = 0.000000001;
	
	public static double atan(double dx, double dy) {
		double res = Math.atan2(dx, dy);
	//	if (dx < 0) res += Math.PI;
		res = (res % TAU) + 10 * TAU;
		return res % TAU;
	}
	
	public static HashSet<Block> blacklist = new HashSet<Block>();
	
	public static void initBlackList() {
		blacklist.add(BlockInit.DUPLICATOR);
		blacklist.add(Blocks.IRON_BLOCK);
	}
}
