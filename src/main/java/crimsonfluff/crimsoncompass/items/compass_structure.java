package crimsonfluff.crimsoncompass.items;

import crimsonfluff.crimsoncompass.CrimsonCompass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.server.ServerWorld;

import java.util.List;

public class compass_structure extends Item {
    Structure st;

    public compass_structure(Structure st) {
        super(new Properties().group(CrimsonCompass.TAB).maxStackSize(1));
        this.st = st;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote) {
            playerIn.sendStatusMessage(new TranslationTextComponent("tip." + CrimsonCompass.MOD_ID + ".searching"), true);
            BlockPos blockPos = ((ServerWorld) worldIn).getChunkProvider().getChunkGenerator().func_235956_a_((ServerWorld) worldIn, this.st, playerIn.getPosition(), CrimsonCompass.CONFIGURATION.searchRadius.get(), false);

            if (blockPos != null) {
                ItemStack stack = playerIn.getHeldItem(handIn);

                stack.getOrCreateTag().putIntArray("pos", new int[] {blockPos.getX(), blockPos.getY(), blockPos.getZ()});
                playerIn.sendStatusMessage(new TranslationTextComponent("tip." + CrimsonCompass.MOD_ID + ".found"), true);
                playerIn.playSound(SoundEvents.ENTITY_VILLAGER_YES, SoundCategory.PLAYERS, 1f, 1f);

            } else {
                playerIn.sendStatusMessage(new TranslationTextComponent("tip." + CrimsonCompass.MOD_ID + ".notfound").mergeStyle(TextFormatting.GRAY), true);
                playerIn.playSound(SoundEvents.ENTITY_VILLAGER_NO, SoundCategory.PLAYERS, 1f, 1f);
            }
        }

        return new ActionResult<>(ActionResultType.SUCCESS, playerIn.getHeldItem(handIn));
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (stack.hasTag() && stack.getTag().contains("pos")) {
            tooltip.add(new TranslationTextComponent("tip." + CrimsonCompass.MOD_ID + ".found"));

            if (Minecraft.getInstance().gameSettings.advancedItemTooltips) {
                int[] xyz = stack.getTag().getIntArray("pos");

                tooltip.add(new StringTextComponent("Location x: " + xyz[0] + ", z: " + xyz[2]));
            }
        } else
            tooltip.add(new TranslationTextComponent("tip." + CrimsonCompass.MOD_ID + ".notfound").mergeStyle(TextFormatting.GRAY));

    }
}
