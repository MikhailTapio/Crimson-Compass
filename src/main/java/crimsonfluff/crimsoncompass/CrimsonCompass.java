package crimsonfluff.crimsoncompass;

import crimsonfluff.crimsoncompass.init.itemsInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(CrimsonCompass.MOD_ID)
public class CrimsonCompass {
    public static final String MOD_ID = "crimsoncompass";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
    public static final ConfigBuilder CONFIGURATION = new ConfigBuilder();

    public CrimsonCompass() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CONFIGURATION.COMMON);

        itemsInit.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public static final ItemGroup TAB = new ItemGroup(CrimsonCompass.MOD_ID) {
        @OnlyIn(Dist.CLIENT)
        @Override
        public ItemStack createIcon() { return new ItemStack(itemsInit.COMPASS_MONUMENT.get()); }
    };

    private void setup(final FMLCommonSetupEvent event) {
        registerCapabilities();
    }

    @SubscribeEvent
    public void onEntityDeath(LivingDeathEvent event) {
        if (event.getEntity().getEntityWorld() instanceof ServerWorld) {
            if (event.getEntity() instanceof PlayerEntity) {
                PlayerEntity playerIn = (PlayerEntity) event.getEntity();

                //playerIn.sendStatusMessage(new StringTextComponent("HA HA. You died: " + playerIn.getPosition()), false);

                playerIn.getCapability(PlayerProperties.PLAYER_DEATH,null).ifPresent(cap -> {
                    cap.deathPosWrite(playerIn.getPosition());
                });
            }
        }
    }

    private static void registerCapabilities(){
        CapabilityManager.INSTANCE.register(DeathCapability.class, new Capability.IStorage<DeathCapability>() {
            @Override
            public void readNBT(Capability<DeathCapability> capability, DeathCapability deathCapability, Direction direction, INBT inbt) {
                throw new UnsupportedOperationException();
            }

            @Override
            public INBT writeNBT(Capability<DeathCapability> capability, DeathCapability deathCapability, Direction direction) {
                throw new UnsupportedOperationException();
            }

        }, () -> {
            throw new UnsupportedOperationException();
        });
    }

    @SubscribeEvent
    public void onAttachCapabilitiesEvent(AttachCapabilitiesEvent<Entity> event){
        if (event.getObject() instanceof PlayerEntity) {
            if (!event.getObject().getCapability(PlayerProperties.PLAYER_DEATH).isPresent()) {
                event.addCapability(new ResourceLocation(CrimsonCompass.MOD_ID, ""), new PropertiesDispatcher());
            }
        }
    }

// if Player dies then copy from OldPlayer to NewPlayer(clone)
// TODO: use PlayerEvent.PlayerRespawnEvent ?
    @SubscribeEvent
    public void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            LazyOptional<DeathCapability> capability = event.getOriginal().getCapability(PlayerProperties.PLAYER_DEATH);

            capability.ifPresent(oldPlayer -> {
                event.getPlayer().getCapability(PlayerProperties.PLAYER_DEATH).ifPresent(newPlayer -> {
                    newPlayer.copyFrom(oldPlayer);

                    if (CONFIGURATION.compassUponDeath.get()) {
                        ItemStack COMPASS = new ItemStack(itemsInit.COMPASS_DEATH.get());
                        COMPASS.getOrCreateTag().putIntArray("pos", oldPlayer.deathPosRead());

                        event.getPlayer().addItemStackToInventory(COMPASS);
                    }
                });
            });
        }
    }
}
