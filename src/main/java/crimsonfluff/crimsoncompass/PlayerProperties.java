package crimsonfluff.crimsoncompass;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class PlayerProperties {
    @CapabilityInject(DeathCapability.class)
    public static Capability<DeathCapability> PLAYER_DEATH;
}
