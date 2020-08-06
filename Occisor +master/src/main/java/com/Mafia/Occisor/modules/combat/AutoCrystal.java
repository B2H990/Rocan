package Mafia.Occisor.module.modules.combat;

import Mafia.Occisor.util.math.MathUtil

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.network.play.server.SPacketSpawnObject;
import net.minecraft.potion.Potion;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.Explosion;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class AutoCrystal extends Module {
/*
    @Clamp(minimum = "1", maximum = "13")
    @Mafia.Occisor.setting.annotation.Setting("EnemyRange")
    public float enemyRange = 13f;

    @Clamp(minimum = "1", maximum = "6")
    @Mafia.Occisor.setting.annotation.Setting("BreakRange")
    public float breakRange = 6f;

    @Clamp(minimum = "1", maximum = "6")
    @Mafia.Occisor.setting.annotation.Setting("PlaceRange")
    public float placeRange = 6f;

    @Clamp(maximum = "30")
    @Mafia.Occisor.setting.annotation.Setting("PlaceDelay")
    public int placeDelay = 0;

    @Clamp(maximum = "6")
    @Mafia.Occisor.setting.annotation.Setting("Wallrange")
    public float wallRange = 6f;

    @Clamp(minimum = "1", maximum = "100")
    @Mafia.Occisor.setting.annotation.Setting("APS")
    public int aps = 40;

    @Clamp(maximum = "36")
    @Mafia.Occisor.setting.annotation.Setting("MinDamage")
    public float minDmg = 8f;

    @Clamp(maximum = "36")
    @Mafia.Occisor.setting.annotation.Setting("FaceplaceHP")
    public float facePlace = 10f;

    @Clamp(minimum = "0", maximum = "100")
    @Mafia.Occisor.setting.annotation.Setting(ArmourFucker)
    public int MinarmourHP = 25f;

    @Clamp()
    @Mafia.Occisor.setting.annotation.Setting("MaxSelfDamage")
    public float maxSelfDamage = 8f;

    @Clamp(maximum = "15")
    @Mafia.Occisor.setting.annotation.Setting("HitAttempts")
    public int hitAttempts = 15;

    @Mafia.Occisor.setting.annotation.Setting("Place")
    public boolean place = true;

    @Mafia.Occisor.setting.annotation.Setting("pSilent")
    public boolean pSilent = true;

    @Mafia.Occisor.setting.annotation.Setting("AutoSwitch")
    public boolean autoSwitch = false;

    @Mafia.Occisor.setting.annotation.Setting("Announcer")
    public boolean announcer;

    @Mafia.Occisor.setting.annotation.Setting("Rainbow")
    public boolean rgb = true;

    @Mafia.Occisor.setting.annotation.Setting("Color")
    public Color color = new Color(255, 0, 0);

    @Mafia.Occisor.setting.annotation.Setting("DamageColor")
    public Color dmgColor = new Color(0, 0, 255);

    @Mafia.Occisor.setting.annotation.Setting("Multiplace")
    public boolean Multiplace = false;

    @Mafia.Occisor.setting.annotation.Setting("Offhandswing")
    public boolean OFF_HAND = true; */

    public void setup(){

    }


    //have not made all the settings yet
    //we need an arraylist
    //also it only swings w offhand as of now il make it be able to switch between the main and offhand later
    // make cool renders idk how
    //we also need to add the import (mathutil etc)

    private final StopwatchUtil stopwatch = new StopwatchUtil();
    private boolean switchCooldown;
    private BlockPos render;
    private EntityEnderCrystal lasthit;
    private int attempts;
    private String dmg;
    private final List<AutoCrystal.PlaceLocation> placeLocations = new CopyOnWriteArrayList<>();
    public Entity target = null;
    private Entity possibleTarget = null;

    @Subscribe
    public void onUpdate(UpdateEvent event) {
        if (event.getType() == EventType.PRE) {
            for (final Entity crystal : mc.world.loadedEntityList) {
                if (crystal instanceof EntityEnderCrystal) {
                    if (mc.player.getDistanceToEntity(crystal) <= breakRange) {
                        if (!mc.player.canEntityBeSeen(crystal) && mc.player.getDistanceToEntity(crystal) >= wallRange)
                            return;
                        if (attempts > hitAttempts) return;
                        if (StopwatchUtil.hasCompleted((1000 / aps))) {
                            mc.playerController.attackEntity(mc.player, crystal);
                            mc.player.swingArm(EnumHand.OFF_HAND);
                            stopwatch.reset();
                        }

                        if (lasthit == crystal) {
                            ++this.attempts;
                        } else {
                            this.attempts = 1;
                        }

                    }
                }
            }
        }

        int crystalSlot = (mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL) ? mc.player.inventory.currentItem : -1;
        if (crystalSlot == -1) {
            for (int l = 0; l < 9; ++l) {
                if (mc.player.inventory.getStackInSlot(l).getItem() == Items.END_CRYSTAL) {
                    crystalSlot = l;
                    break;
                }
            }
        }

        boolean offhand = false;
        if (mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
            offhand = true;
        } else if (crystalSlot == -1) {
            return;
        }


        BlockPos finalPos = null;
        final List<BlockPos> blocks = findCrystalBlocks();
        final List<Entity> entities = mc.world.playerEntities.stream().filter(entityPlayer -> entityPlayer != mc.player && entityPlayer.getEntityId() != -1488 && !Occisor.INSTANCE.friendManager.isFriend(entityPlayer.getName())).collect(Collectors.toList()); //we need a MCF
        double damage = 0.5;
        for (final Entity entity2 : entities) {
            if (((EntityLivingBase) entity2).getHealth() <= 0.0f || mc.player.getDistanceSqToEntity(entity2) > enemyRange * enemyRange)
                continue;
            for (final BlockPos blockPos : blocks) {
                final double d = calculateDamage(blockPos.getX() + 0.5, blockPos.getY() + 1, blockPos.getZ() + 0.5, entity2);
                final double self = calculateDamage(blockPos.getX() + 0.5, blockPos.getY() + 1, blockPos.getZ() + 0.5, mc.player);
                final double b = entity2.getDistanceSq(blockPos);
                if ((!canBlockBeSeen(blockPos))
                        && mc.player.getDistanceSq(blockPos) > 25.0
                        || d < minDmg
                        && ((EntityLivingBase) entity2).getHealth() + ((EntityLivingBase) entity2).getAbsorptionAmount() > facePlace
                        || maxSelfDamage <= self
                        || self > d
                        || self >= mc.player.getHealth() + mc.player.getAbsorptionAmount()
                        || d <= damage) continue;
                damage = d;
                finalPos = blockPos;
            }
        }

        if (damage == 0.5) {
            render = null;
            dmg = null;
            return;
        }

        if (place) {
            if (!offhand && mc.player.inventory.currentItem != crystalSlot) {
                if (autoSwitch) {
                    mc.player.inventory.currentItem = crystalSlot;
                    switchCooldown = true;
                }
                return;
            }


            final RayTraceResult result = mc.world.rayTraceBlocks(new Vec3d(mc.player.posX, mc.player.posY + mc.player.getEyeHeight(), mc.player.posZ), new Vec3d(finalPos.getX() + 0.5, finalPos.getY() - 0.5, finalPos.getZ() + 0.5));
            final EnumFacing f = result == null || result.sideHit == null ? EnumFacing.UP : result.sideHit;
            if (switchCooldown) {
                switchCooldown = false;
                return;
            }

            if (placeRange > 0) {
                mc.player.connection.sendPacket(new CPacketPlayerTryUseItemOnBlock(finalPos, f, offhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
                this.placeLocations.add(new AutoCrystal.PlaceLocation(finalPos.getX(), finalPos.getY(), finalPos.getZ()));
                render = finalPos;
                if (this.possibleTarget != null) {
                    this.target = this.possibleTarget;
                    KillUtil.INSTANCE.addTarget(this.target);
                }
                render = finalPos;
                dmg = MathHelper.floor(damage) + "hp";
            }
        }
    }



    @Subscribe
    public void onPacket(final PacketEvent event) {
        if (event.getType() == EventType.POST && event.getPacket() instanceof SPacketSoundEffect) {
            final SPacketSoundEffect packet = (SPacketSoundEffect) event.getPacket();
            if (packet.getCategory() == SoundCategory.BLOCKS && packet.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE) {
                for (final Entity e : mc.world.loadedEntityList) {
                    if (e instanceof EntityEnderCrystal && e.getDistance(packet.getX(), packet.getY(), packet.getZ()) <= 6.0) {
                        e.setDead();
                    }
                }
            }
        }
    }

    @Subscribe
    public void onReceivePacket(final PacketEvent event) {
        if (event.getPacket() instanceof SPacketSpawnObject) {
            final SPacketSpawnObject packetSpawnObject = (SPacketSpawnObject) event.getPacket();
            if (event.getType() == EventType.PRE) {
                if (packetSpawnObject.getType() == 51) {
                    for (CaRewrite.PlaceLocation placeLocation : this.placeLocations) {
                        if (!placeLocation.placed && placeLocation.getDistance((int) packetSpawnObject.getX(), (int) packetSpawnObject.getY() - 1, (int) packetSpawnObject.getZ()) <= 1) {
                            placeLocation.placed = true;
                            if (pSilent && !mc.player.isPotionActive(MobEffects.WEAKNESS)) {
                                event.setCancelled(true);
                                CPacketUseEntity packetUseEntity = new CPacketUseEntity();
                                packetUseEntity.entityId = packetSpawnObject.getEntityID();
                                packetUseEntity.action = CPacketUseEntity.Action.ATTACK;

                                final float[] angle = MathUtil.calcAngle(mc.player.getPositionEyes(mc.getRenderPartialTicks()), new Vec3d(packetSpawnObject.getX() + 0.5, packetSpawnObject.getY() + 0.5, packetSpawnObject.getZ() + 0.5));
                                mc.player.connection.sendPacket(new CPacketAnimation(EnumHand.OFF_HAND));
                                mc.player.connection.sendPacket(new CPacketPlayer.Rotation(angle[0], angle[1], mc.player.onGround));
                                mc.player.connection.sendPacket(packetUseEntity);
                            }
                        }
                    }
                }
            }
        }
    }

    private static final class PlaceLocation extends Vec3i {
        private boolean placed = false;
        private PlaceLocation(int xIn, int yIn, int zIn) {
            super(xIn, yIn, zIn);
        }
    }


    private BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(mc.player.posX), Math.floor(mc.player.posY), Math.floor(mc.player.posZ));
    }

    private List<BlockPos> findCrystalBlocks() {
        final NonNullList<BlockPos> positions = NonNullList.create();
        positions.addAll(getSphere(getPlayerPos(), placeRange, (int) placeRange, false, true, 0).stream().filter(this::canPlaceCrystal).collect(Collectors.toList()));
        return positions;
    }

    public List<BlockPos> getSphere(final BlockPos loc, final float r, final int h, final boolean hollow, final boolean sphere, final int plus_y) {
        final List<BlockPos> circleblocks = new ArrayList<BlockPos>();
        final int cx = loc.getX();
        final int cy = loc.getY();
        final int cz = loc.getZ();
        for (int x = cx - (int) r; x <= cx + r; ++x) {
            for (int z = cz - (int) r; z <= cz + r; ++z) {
                for (int y = sphere ? (cy - (int) r) : cy; y < (sphere ? (cy + r) : ((float) (cy + h))); ++y) {
                    final double dist = (cx - x) * (cx - x) + (cz - z) * (cz - z) + (sphere ? ((cy - y) * (cy - y)) : 0);
                    if (dist < r * r && (!hollow || dist >= (r - 1.0f) * (r - 1.0f))) {
                        final BlockPos l = new BlockPos(x, y + plus_y, z);
                        circleblocks.add(l);
                    }
                }
            }
        }
        return circleblocks;
    }

    private float calculateDamage(double posX, double posY, double posZ, Entity entity) {
        float doubleExplosionSize = 12.0F;
        double distancedsize = entity.getDistance(posX, posY, posZ) / (double)doubleExplosionSize;
        Vec3d vec3d = new Vec3d(posX, posY, posZ);
        double blockDensity = (double)entity.world.getBlockDensity(vec3d, entity.getEntityBoundingBox());
        double v = (1.0D - distancedsize) * blockDensity;
        float damage = (float)((int)((v * v + v) / 2.0D * 7.0D * (double)doubleExplosionSize + 1.0D));
        double finald = 1.0D;
        if (entity instanceof EntityLivingBase) {
            finald = (double)this.getBlastReduction((EntityLivingBase)entity, this.getDamageMultiplied(damage), new Explosion(mc.world, (Entity)null, posX, posY, posZ, 6.0F, false, true));
        }

        return (float)finald;
    }

    public static float getBlastReduction(final EntityLivingBase entity, float damage, final Explosion explosion) {
        if (entity instanceof EntityPlayer) {
            final EntityPlayer ep = (EntityPlayer) entity;
            final DamageSource ds = DamageSource.causeExplosionDamage(explosion);
            damage = CombatRules.getDamageAfterAbsorb(damage, (float) ep.getTotalArmorValue(), (float) ep.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
            final int k = EnchantmentHelper.getEnchantmentModifierDamage(ep.getArmorInventoryList(), ds);
            final float f = MathHelper.clamp((float) k, 0.0f, 20.0f);
            damage *= 1.0f - f / 25.0f;
            if (entity.isPotionActive(Objects.requireNonNull(Potion.getPotionById(11)))) {
                damage -= damage / 4.0f;
            }
            return damage;
        }
        damage = CombatRules.getDamageAfterAbsorb(damage, (float) entity.getTotalArmorValue(), (float) entity.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
        return damage;
    }

    private static float getDamageMultiplied(final float damage) {
        final int diff = mc.world.getDifficulty().getDifficultyId();
        return damage * ((diff == 0) ? 0.0f : ((diff == 2) ? 1.0f : ((diff == 1) ? 0.5f : 1.5f)));
    }

    public static boolean canBlockBeSeen(final BlockPos blockPos) {
        return mc.world.rayTraceBlocks(new Vec3d(mc.player.posX, mc.player.posY + mc.player.getEyeHeight(), mc.player.posZ), new Vec3d(blockPos.getX(), blockPos.getY(), blockPos.getZ()), false, true, false) == null;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        target = null;
        render = null;
        possibleTarget = null;
        if (announcer) {
            Logger.printMessage("Goon Destroyer ON!", true);
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        target = null;
        possibleTarget = null;
        dmg = null;
        render = null;
        if (announcer) {
            Logger.printMessage("Goon Destroyer OFF!", true);
        }
    }
}

