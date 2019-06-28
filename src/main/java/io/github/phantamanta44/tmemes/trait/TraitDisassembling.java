package io.github.phantamanta44.tmemes.trait;

import mekanism.api.Coord4D;
import mekanism.common.OreDictCache;
import mekanism.common.item.ItemAtomicDisassembler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.modifiers.IToolMod;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.tools.modifiers.ModHarvestSize;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TraitDisassembling extends ModifierTrait {

    private final Set<UUID> lock;

    public TraitDisassembling() {
        super("meme-disassemble", 0xe81cca);
        this.lock = new HashSet<>();
    }

    @Override
    public boolean canApplyTogether(IToolMod o) {
        return !(o instanceof TraitDisassembling || o instanceof ModHarvestSize);
    }

    @Override
    public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase player, boolean wasEffective) {
        // is the player ever not an EntityPlayer?
        if (player instanceof EntityPlayer && !lock.contains(player.getPersistentID())) {
            RayTraceResult trace = doRayTrace(state, pos, player);
            ItemStack broken = state.getBlock().getPickBlock(state, trace, world, pos, (EntityPlayer)player);
            boolean shouldContinue = false;
            for (String id : OreDictCache.getOreDictName(broken)) {
                if (id.startsWith("ore") || id.equals("logWood")) {
                    shouldContinue = true;
                    break;
                }
            }
            if (shouldContinue) {
                lock.add(player.getPersistentID());
                Coord4D coord = new Coord4D(pos, world);
                ItemAtomicDisassembler.Finder finder
                        = new ItemAtomicDisassembler.Finder((EntityPlayer)player, broken, coord, trace);
                for (Coord4D block : finder.calc()) {
                    if (ToolHelper.isBroken(tool))
                        break;
                    if (!block.equals(coord))
                        ToolHelper.breakExtraBlock(tool, world, (EntityPlayer)player, block.getPos(), pos);
                }
                lock.remove(player.getPersistentID());
            }
        }
    }

    // From Mekanism's ItemAtomicDisassembler
    private static RayTraceResult doRayTrace(IBlockState state, BlockPos pos, EntityLivingBase player) {
        Vec3d positionEyes = player.getPositionEyes(1.0F);
        Vec3d playerLook = player.getLook(1.0F);
        double blockReachDistance = player.getAttributeMap().getAttributeInstance(EntityPlayer.REACH_DISTANCE).getAttributeValue();
        Vec3d maxReach = positionEyes.addVector(playerLook.x * blockReachDistance, playerLook.y * blockReachDistance, playerLook.z * blockReachDistance);
        RayTraceResult res = state.collisionRayTrace(player.world, pos, playerLook, maxReach);
        //noinspection ConstantConditions - idea thinks it's nonnull due to package level annotations, but it's not
        return res != null ? res : new RayTraceResult(RayTraceResult.Type.MISS, Vec3d.ZERO, EnumFacing.UP, pos);
    }

}
