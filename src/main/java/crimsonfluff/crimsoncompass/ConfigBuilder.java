package crimsonfluff.crimsoncompass;
import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigBuilder {
    public final ForgeConfigSpec COMMON;

    public ForgeConfigSpec.IntValue searchRadius;
    public ForgeConfigSpec.BooleanValue compassUponDeath;


    public ConfigBuilder() {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("Crimson Compass");
        builder.push("General");

        searchRadius = builder
            .comment("Search radius?  Default: 100 chunks  (higher values may cause fps/tps issues)")
            .defineInRange("searchRadius", 100,1,200);

        compassUponDeath = builder
            .comment("Should player respawn with a death compass?")
            .define("compassUponDeath", false);

        COMMON = builder.build();
    }
}
