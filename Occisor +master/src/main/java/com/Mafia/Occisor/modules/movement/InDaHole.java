package Mafia.Occisor.modules.movement;

import Mafia.Occisor.Occisor;
import Maifa.Occisor.entity.UpdateEvent;
import Maifa.Occisor.module.ModuleCategory;
import Maifa.Occisor.module.annotation.ModuleManifest;
import Mafia.Occisor.setting.annotation.Mode;
import Mafia.Occisor.setting.annotation.Setting;
import Mafia.Occisor.event.Subscribe;
import Mafia.Occisor.event.types.EventType;
import com.Mafia.Occisor.modules.Module;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class InDaHole extends Module {

    @Setting("mode")
    @Mode({"HoleTP", "ReverseStep"})
    public String mode = "ReverseStep";
    private final double[] oneblockPositions = {0.42D, 0.75D};
    private int packets;
    private boolean jumped = false;

    public class InDaHole extends Module {

    @Subscribe
    public void onUpdate(UpdateEvent event) {
        if (mc.world == null || mc.player == null || Mafia.Occisor.managers.moduleManager.getModule("Speed").isEnabled())
            return;

        if (mode.equals("ReverseStep")) {
            if (mc.player.isInLava() || mc.player.isInWater()) return;
            if (mc.player.onGround) {
                --mc.player.motionY;
            }
        }

        if (mode.equals("HoleTP")) {
            if (event.getType() == EventType.POST)
                if (!mc.player.onGround) {
                    if (mc.gameSettings.keyBindJump.isKeyDown())
                        jumped = true;
                } else jumped = false;
            if (!jumped && mc.player.fallDistance < 0.5 && isInHole() && mc.player.posY - getNearestBlockBelow() <= 1.125 && mc.player.posY - getNearestBlockBelow() <= 0.95 && !isOnLiquid() && !isInLiquid()) {
                if (!mc.player.onGround) {
                    this.packets++;
                }
                if (!mc.player.onGround && !mc.player.isInsideOfMaterial(Material.WATER) && !mc.player.isInsideOfMaterial(Material.LAVA) && !mc.gameSettings.keyBindJump.isKeyDown() && !mc.player.isOnLadder() && this.packets > 0) {
                    final BlockPos blockPos = new BlockPos(mc.player.posX, mc.player.posY, mc.player.posZ);
                    for (double position : this.oneblockPositions) {
                        mc.player.connection.sendPacket(new CPacketPlayer.Position(blockPos.getX() + 0.5f, mc.player.posY - position, blockPos.getZ() + 0.5f, true));
                    }
                    mc.player.setPosition(blockPos.getX() + 0.5f, getNearestBlockBelow() + 0.1, blockPos.getZ() + 0.5f);
                    this.packets = 0;
                }
            }
        }
    }

    private boolean isInHole() {
        final BlockPos blockPos = new BlockPos(mc.player.posX, mc.player.posY, mc.player.posZ);
        final IBlockState blockState = mc.world.getBlockState(blockPos);
        return this.isBlockValid(blockState, blockPos);
    }

    private double getNearestBlockBelow() {
        for (double y = mc.player.posY; y > 0; y -= 0.001) {
            if (!(mc.world.getBlockState(new BlockPos(mc.player.posX, y, mc.player.posZ)).getBlock() instanceof BlockSlab) && mc.world.getBlockState(new BlockPos(mc.player.posX, y, mc.player.posZ)).getBlock().getDefaultState().getCollisionBoundingBox(mc.world, new BlockPos(0, 0, 0)) != null) {
                return y;
            }
        }
        return -1;
    }

    private boolean isBlockValid(final IBlockState blockState, final BlockPos blockPos) {
        if (blockState.getBlock() != Blocks.AIR) {
            return false;
        }
        if (mc.player.getDistanceSq(blockPos) < 1.0) {
            return false;
        }
        if (mc.world.getBlockState(blockPos.up()).getBlock() != Blocks.AIR) {
            return false;
        }
        if (mc.world.getBlockState(blockPos.up(2)).getBlock() != Blocks.AIR) {
            return false;
        }
        return isBedrockHole(blockPos) || isObbyHole(blockPos) || isBothHole(blockPos) || isElseHole(blockPos);
    }

    private boolean isObbyHole(BlockPos blockPos) {
        final BlockPos[] touchingBlocks = {blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()};
        for (final BlockPos touching : touchingBlocks) {
            final IBlockState touchingState = mc.world.getBlockState(touching);
            if (touchingState.getBlock() == Blocks.AIR || touchingState.getBlock() != Blocks.OBSIDIAN)
                return false;
        }
        return true;
    }

    private boolean isBedrockHole(BlockPos blockPos) {
        final BlockPos[] touchingBlocks = {blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()};
        for (final BlockPos touching : touchingBlocks) {
            final IBlockState touchingState = mc.world.getBlockState(touching);
            if (touchingState.getBlock() == Blocks.AIR || touchingState.getBlock() != Blocks.BEDROCK)
                return false;
        }
        return true;
    }

    private boolean isBothHole(BlockPos blockPos) {
        final BlockPos[] touchingBlocks = {blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()};
        for (final BlockPos touching : touchingBlocks) {
            final IBlockState touchingState = mc.world.getBlockState(touching);
            if (touchingState.getBlock() == Blocks.AIR || (touchingState.getBlock() != Blocks.BEDROCK && touchingState.getBlock() != Blocks.OBSIDIAN))
                return false;
        }
        return true;
    }

    private boolean isElseHole(BlockPos blockPos) {
        final BlockPos[] touchingBlocks = {blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down()};
        for (final BlockPos touching : touchingBlocks) {
            final IBlockState touchingState = mc.world.getBlockState(touching);
            if (touchingState.getBlock() == Blocks.AIR || !touchingState.isFullBlock())
                return false;
        }
        return true;
    }

    private boolean isOnLiquid() {
        final double y = mc.player.posY - 0.03;
        for (int x = MathHelper.floor(mc.player.posX); x < MathHelper.ceil(mc.player.posX); ++x) {
            for (int z = MathHelper.floor(mc.player.posZ); z < MathHelper.ceil(mc.player.posZ); ++z) {
                final BlockPos pos = new BlockPos(x, MathHelper.floor(y), z);
                if (mc.world.getBlockState(pos).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isInLiquid() {
        final double y = mc.player.posY + 0.01;
        for (int x = MathHelper.floor(mc.player.posX); x < MathHelper.ceil(mc.player.posX); ++x) {
            for (int z = MathHelper.floor(mc.player.posZ); z < MathHelper.ceil(mc.player.posZ); ++z) {
                final BlockPos pos = new BlockPos(x, (int) y, z);
                if (mc.world.getBlockState(pos).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
}
