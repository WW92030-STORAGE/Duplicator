package duplicator.mod.blocks;

import duplicator.mod.util.Reference;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class DuplicatorBlock extends Block {

    Block reference0 = Blocks.TORCH;
    public DuplicatorBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos blockPos, Player player,
                                 InteractionHand hand, BlockHitResult blockHitResult) {
        // Server: Main Hand & Off Hand
        // Client: Main Hand & Off Hand

        // player.sendSystemMessage(Component.literal("!!!"));

        return super.use(state, level, blockPos, player, hand, blockHitResult);
    }

    public static BlockPos x(int x, int y, int z) {
        return new BlockPos(x, y, z);
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource rs) {
        super.tick(state, world, pos, rs);
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        int rng = rs.nextInt(world.getGameRules().getInt(GameRules.RULE_RANDOMTICKING));
        System.out.println(rng);
        if (rng > 1) return;

        if (world.getLevel().isClientSide()) { // this can't happen
            System.out.println("LOL");
            return;
        }

        System.out.println(world.toString() + " - " + x + " " + y + " " + z);
        System.out.println("DUPLICATING BLOCK...");

        BlockState seed = world.getBlockState(x(x, y + 1, z));
        Block b = seed.getBlock();
        if (Reference.blacklist.contains(b)) return;

        int dz = 0;
        int dx = 0;

        for (int i = 0; i < 625; i++) {
            dx = dz = 0;
            while (dx == 0 && dz == 0) {
                dx = (int)(Math.random() * 5) - 2;
                dz = (int)(Math.random() * 5) - 2;
            }
            Block target = world.getBlockState(x(x + dx, y, z + dz)).getBlock();
            if (target == Blocks.AIR) {
                System.out.println("ATTEMPT " + i + " : PLACED BLOCK AT " + (x + dx) + " " + (z + dz));
                world.setBlockAndUpdate(x(x + dx, y, z + dz), seed);
                break;
            }
        }
    }
}

