package my.NewSnake.Tank.module.modules.COMBAT;

import my.NewSnake.Tank.friend.FriendManager;
import my.NewSnake.Tank.module.Module;
import my.NewSnake.Tank.module.modules.COMBAT.aura.Switch2;
import my.NewSnake.Tank.option.Option;
import my.NewSnake.Tank.option.OptionManager;
import my.NewSnake.event.EventTarget;
import my.NewSnake.event.events.Render3DEvent;
import my.NewSnake.event.events.TickEvent;
import my.NewSnake.event.events.UpdateEvent;
import my.NewSnake.utils.ClientUtils;
import my.NewSnake.utils.StateManager;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumParticleTypes;

@Module.Mod
public class Aura extends Module {
   public double degrees;
   @Option.Op(
      min = 0.1D,
      max = 20.0D,
      increment = 1.0D
   )
   public static double pitchdiff;
   @Option.Op(
      min = 0.0D,
      max = 100.0D,
      increment = 1.0D
   )
   public double Ticks = 0.0D;
   public static boolean SetupTick;
   @Option.Op
   private boolean attackFriends;
   @Option.Op
   private boolean armorCheck;
   @Option.Op
   private boolean Monstros;
   @Option.Op
   public static boolean ESPTarget;
   @Option.Op
   public static boolean Rotaçao;
   public boolean Particulas = true;
   private Switch2 switchMode = new Switch2("Switch2", true, this);
   @Option.Op
   public static boolean Bypass;
   @Option.Op(
      min = 0.0D,
      max = 8.0D,
      increment = 0.1D
   )
   public static double Radius;
   @Option.Op
   private boolean Players = true;
   @Option.Op(
      min = 0.1D,
      max = 12.0D,
      increment = 0.1D
   )
   private double range = 6.2D;
   @Option.Op
   public boolean criticals;
   @Option.Op
   private boolean noswing;
   private boolean jumpNextTick;
   @Option.Op
   public static boolean Circulo;
   @Option.Op
   private boolean Moradores;
   @Option.Op
   private boolean Animals;
   @Option.Op(
      min = 0.1D,
      max = 20.0D,
      increment = 1.0D
   )
   public static double yawdiff;
   @Option.Op
   public boolean AutoBlock = true;
   @Option.Op
   public static boolean Normal;

   private void updateSuffix() {
      if ((Boolean)this.switchMode.getValue()) {
         this.setSuffix("§aNormal");
      }

   }

   public void disable() {
      StateManager.setOffsetLastPacketAura(false);
      super.disable();
   }

   @EventTarget
   private void onUpdate(UpdateEvent var1) {
      this.switchMode.onUpdate(var1);
   }

   @EventTarget
   private void onTick(TickEvent var1) {
      this.updateSuffix();
   }

   public boolean isEntityValid(Entity var1) {
      if (var1 instanceof EntityLivingBase) {
         EntityLivingBase var2 = (EntityLivingBase)var1;
         if (!ClientUtils.player().isEntityAlive() || !var2.isEntityAlive() || (double)var2.getDistanceToEntity(ClientUtils.player()) > (ClientUtils.player().canEntityBeSeen(var2) ? this.range : 3.0D)) {
            return false;
         }

         if ((double)var2.ticksExisted < this.Ticks) {
            return false;
         }

         if (this.Players && var2 instanceof EntityPlayer) {
            EntityPlayer var3 = (EntityPlayer)var2;
            if (FriendManager.isFriend(var3.getName()) && !this.attackFriends || this.armorCheck && var3 != null) {
               return false;
            }

            return true;
         }

         if (this.Monstros && (var2 instanceof EntityMob || var2 instanceof EntityGhast || var2 instanceof EntityDragon || var2 instanceof EntityWither || var2 instanceof EntitySlime || var2 instanceof EntityWolf && ((EntityWolf)var2).getOwner() != ClientUtils.player())) {
            return true;
         }

         if (this.Animals && var2 instanceof EntityGolem) {
            return true;
         }

         if (this.Animals && (var2 instanceof EntityAnimal || var2 instanceof EntitySquid)) {
            return true;
         }

         if (this.Animals && var2 instanceof EntityBat) {
            return true;
         }

         if (this.Moradores && var2 instanceof EntityVillager) {
            return true;
         }
      }

      return false;
   }

   public void attack(EntityLivingBase var1, boolean var2) {
      this.swingItem();
      float var3 = EnchantmentHelper.func_152377_a(ClientUtils.player().getHeldItem(), var1.getCreatureAttribute());
      boolean var4 = ClientUtils.player().fallDistance > 0.0F && !ClientUtils.player().onGround && !ClientUtils.player().isOnLadder() && !ClientUtils.player().isInWater() && !ClientUtils.player().isPotionActive(Potion.blindness) && ClientUtils.player().ridingEntity == null;
      ClientUtils.packet(new C02PacketUseEntity(var1, C02PacketUseEntity.Action.ATTACK));
      if (var2 || var4) {
         ClientUtils.player().onCriticalHit(var1);
      }

      if (var3 > 0.0F) {
         ClientUtils.player().onEnchantmentCritical(var1);
      }

      if (this.Particulas) {
         ClientUtils.mc().effectRenderer.emitParticleAtEntity(var1, EnumParticleTypes.CRIT);
         ClientUtils.mc().effectRenderer.emitParticleAtEntity(var1, EnumParticleTypes.CRIT_MAGIC);
      }

   }

   public void postInitialize() {
      OptionManager.getOptionList().add(this.switchMode);
      this.updateSuffix();
      super.postInitialize();
   }

   public Aura() {
      Normal = true;
   }

   private void swingItem() {
      if (!this.noswing) {
         ClientUtils.player().swingItem();
      }

   }

   @EventTarget
   private void onRender3D(Render3DEvent var1) {
      this.switchMode.VAPO(var1);
      this.switchMode.VAPO2(var1);
   }

   public void attack(EntityLivingBase var1) {
      this.attack(var1, this.criticals);
   }
}
