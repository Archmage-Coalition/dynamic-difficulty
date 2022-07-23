package net.eman3600.dynamic_difficulty.mixin;

import net.eman3600.dynamic_difficulty.init.DifficultyComponents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerMixin extends LivingEntity {

	protected PlayerMixin(EntityType<? extends LivingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Inject(method = "onDeath", at = @At("TAIL"))
	private void injectOnDeath(DamageSource source, CallbackInfo info) {
		PlayerEntity self = (PlayerEntity) (Object) this;
		DifficultyComponents.PLAYER_DIFFICULTY.get(self).add(-15);
	}
}
