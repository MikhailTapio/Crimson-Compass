package crimsonfluff.crimsoncompass.init;

import crimsonfluff.crimsoncompass.CrimsonCompass;
import crimsonfluff.crimsoncompass.items.compass_death;
import crimsonfluff.crimsoncompass.items.compass_structure;
import net.minecraft.item.Item;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class itemsInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CrimsonCompass.MOD_ID);

    // Items
    //public static final RegistryObject<Item> COMPASS_MULTIPASS = ITEMS.register("compass_multi", compassmulti::new);

    public static final RegistryObject<Item> COMPASS_PILLAGER_OUTPOST = ITEMS.register("compass_pillager", ()-> new compass_structure(Structure.PILLAGER_OUTPOST));
    public static final RegistryObject<Item> COMPASS_MINESHAFT = ITEMS.register("compass_mineshaft", ()-> new compass_structure(Structure.MINESHAFT));
    public static final RegistryObject<Item> COMPASS_WOODLAND_MANSION = ITEMS.register("compass_mansion", ()-> new compass_structure(Structure.WOODLAND_MANSION));
    public static final RegistryObject<Item> COMPASS_JUNGLE_PYRAMID = ITEMS.register("compass_junglemid", ()-> new compass_structure(Structure.JUNGLE_PYRAMID));
    public static final RegistryObject<Item> COMPASS_DESERT_PYRAMID = ITEMS.register("compass_desertmid", ()-> new compass_structure(Structure.DESERT_PYRAMID));
    public static final RegistryObject<Item> COMPASS_BASTION_REMNANT = ITEMS.register("compass_bastion", ()-> new compass_structure(Structure.BASTION_REMNANT));
    public static final RegistryObject<Item> COMPASS_BURIED_TREASURE = ITEMS.register("compass_treasure", ()-> new compass_structure(Structure.BURIED_TREASURE));
    public static final RegistryObject<Item> COMPASS_END_CITY = ITEMS.register("compass_end_city", ()-> new compass_structure(Structure.END_CITY));
    public static final RegistryObject<Item> COMPASS_FORTRESS = ITEMS.register("compass_fortress", ()-> new compass_structure(Structure.FORTRESS));
    public static final RegistryObject<Item> COMPASS_IGLOO = ITEMS.register("compass_igloo", ()-> new compass_structure(Structure.IGLOO));
    public static final RegistryObject<Item> COMPASS_MONUMENT = ITEMS.register("compass_monument", ()-> new compass_structure(Structure.MONUMENT));
    public static final RegistryObject<Item> COMPASS_NETHER_FOSSIL = ITEMS.register("compass_fossil", ()-> new compass_structure(Structure.NETHER_FOSSIL));
    public static final RegistryObject<Item> COMPASS_OCEAN_RUIN = ITEMS.register("compass_ocean_ruin", ()-> new compass_structure(Structure.OCEAN_RUIN));
    public static final RegistryObject<Item> COMPASS_RUINED_PORTAL = ITEMS.register("compass_ruined_portal", ()-> new compass_structure(Structure.RUINED_PORTAL));
    public static final RegistryObject<Item> COMPASS_SHIPWRECK = ITEMS.register("compass_shipwreck", ()-> new compass_structure(Structure.SHIPWRECK));
    public static final RegistryObject<Item> COMPASS_STRONGHOLD = ITEMS.register("compass_stronghold", ()-> new compass_structure(Structure.STRONGHOLD));
    public static final RegistryObject<Item> COMPASS_SWAMP_HUT = ITEMS.register("compass_swamp_hut", ()-> new compass_structure(Structure.SWAMP_HUT));
    public static final RegistryObject<Item> COMPASS_VILLAGE = ITEMS.register("compass_village", ()-> new compass_structure(Structure.VILLAGE));

    public static final RegistryObject<Item> COMPASS_DEATH = ITEMS.register("compass_death", compass_death::new);
}
