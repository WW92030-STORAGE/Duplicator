package duplicator.mod.blocks;

import java.util.Random;

import duplicator.mod.Main;
import duplicator.mod.init.BlockInit;
import duplicator.mod.init.ItemInit;
import duplicator.mod.util.IModel;
import duplicator.mod.util.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDuplicator extends Block implements IModel {
	public BlockDuplicator(String name, Material mat, CreativeTabs tab) {
		super(mat);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(tab);
		setHarvestLevel("pickaxe", 1);
		setHardness(1F);
		setResistance(10F);
		setLightLevel(0F);
		setLightOpacity(255);
		
		BlockInit.BLOCKS.add(this);
		ItemInit.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}
	
	@Override
	public boolean isTranslucent(IBlockState state) {
		return true;
	}
	
	@Override
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}
	
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
		super.onBlockAdded(world, pos, state);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		world.scheduleUpdate(new BlockPos(x, y, z), this, this.tickRate(world));
	}
	
	public static BlockPos x(int x, int y, int z) {
		return new BlockPos(x, y, z);
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random random) {
		super.updateTick(world, pos, state, random);
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		if ((Math.random() < 0.005)) {
			if (!world.isRemote) {
				
				IBlockState seed = world.getBlockState(x(x, y + 1, z));
				Block b = seed.getBlock();
				System.out.println(b);
				if (Reference.blacklist.contains(b)) return;
				
				int dx = 0;
				int dz = 0;
				System.out.println("DUPLICATING BLOCK...");
				for (int i = 0; i < 625; i++) {
					dx = dz = 0;
					while (dx == 0 && dz == 0) {
						dx = (int)(Math.random() * 5) - 2;
						dz = (int)(Math.random() * 5) - 2;
					}
					Block target = world.getBlockState(x(x + dx, y, z + dz)).getBlock();
					if (target == Blocks.AIR) {
						System.out.println("ATTEMPT " + i + " : PLACED BLOCK AT " + (x + dx) + " " + (z + dz));
						world.setBlockState(x(x + dx, y, z + dz), seed);
						break;
					}
				}
				
			}
		}
		world.scheduleUpdate(new BlockPos(x, y, z), this, this.tickRate(world));
	}
}
