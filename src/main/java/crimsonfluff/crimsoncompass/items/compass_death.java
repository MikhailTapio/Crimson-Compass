package crimsonfluff.crimsoncompass.items;

import crimsonfluff.crimsoncompass.CrimsonCompass;
import crimsonfluff.crimsoncompass.PlayerProperties;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class compass_death extends Item {
    public compass_death() { super(new Item.Properties().group(CrimsonCompass.TAB).maxStackSize(1)); }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!worldIn.isRemote) {
            ItemStack stack = playerIn.getHeldItem(handIn);

            playerIn.getCapability(PlayerProperties.PLAYER_DEATH,null).ifPresent(cap -> {
                stack.getOrCreateTag().putIntArray("pos", cap.deathPosRead());

                //playerIn.sendStatusMessage(new TranslationTextComponent("tip." + CrimsonCompass.MOD_ID + ".found"), true);
                playerIn.playSound(SoundEvents.ENTITY_VILLAGER_YES, SoundCategory.PLAYERS, 1f, 1f);
            });
        }

        return new ActionResult<>(ActionResultType.SUCCESS, playerIn.getHeldItem(handIn));
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (stack.hasTag() && stack.getTag().contains("pos")) {
            if (Minecraft.getInstance().gameSettings.advancedItemTooltips) {
                int[] xyz = stack.getTag().getIntArray("pos");

                tooltip.add(new StringTextComponent("Location x: " + xyz[0] + ", y: " + xyz[1] + ", z: " + xyz[2]));
            }
        } else
            tooltip.add(new TranslationTextComponent("tip." + CrimsonCompass.MOD_ID + ".nodeath").mergeStyle(TextFormatting.GRAY));

    }
}
