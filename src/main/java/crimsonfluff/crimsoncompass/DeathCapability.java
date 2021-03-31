package crimsonfluff.crimsoncompass;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;

public class DeathCapability {
    private int[] xyz = new int[]{0};

    public DeathCapability() { }

    public void copyFrom(DeathCapability source) {
        xyz = source.xyz;
    }

    public void deathPosWrite(BlockPos pos) {
        this.xyz = new int[] {pos.getX(), pos.getY(), pos.getZ()};
    }

    public int[] deathPosRead() {
        return new int[] {xyz[0], xyz[1], xyz[2]};
    }

    public void saveNBTData(CompoundNBT compound) {
        compound.putIntArray("deathpos", xyz);
    }

    public void loadNBTData(CompoundNBT compound) {
        xyz = compound.getIntArray("deathpos");
    }
}
