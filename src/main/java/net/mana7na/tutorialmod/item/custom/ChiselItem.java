package net.mana7na.tutorialmod.item.custom;

import net.mana7na.tutorialmod.block.ModBlocks;
import net.mana7na.tutorialmod.component.ModDataComponentTypes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public class ChiselItem extends Item {
    private  static final Map<Block,Block> CHISEL_MAP =
            Map.of(
                    Blocks.STONE,Blocks.STONE_BRICKS,
                    Blocks.END_STONE,Blocks.END_STONE_BRICKS,
                    Blocks.OAK_LOG, ModBlocks.PINK_GARNET_BLOCK,
                    Blocks.GOLD_BLOCK,Blocks.NETHERITE_BLOCK

            );


    public ChiselItem(Settings settings){
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();

        if(CHISEL_MAP.containsKey(clickedBlock)){
            if (!world.isClient()){
                world.setBlockState(context.getBlockPos(), CHISEL_MAP.get(clickedBlock).getDefaultState());

                context.getStack().damage(1,((ServerWorld) world),((ServerPlayerEntity) context.getPlayer()),
                item -> context.getPlayer().sendEquipmentBreakStatus(item, EquipmentSlot.MAINHAND));

                world.playSound(null,context.getBlockPos(), SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.BLOCKS);
//データコンポーネント- 座標を保存
                context.getStack().set(ModDataComponentTypes.COORDINATES, context.getBlockPos());

//          nullを追加すればカスタムデータコンポーネント内のデータは消える
//          context.getStack().set(ModDataComponentTypes.COORDINATES, null);
            }
        }


        return ActionResult.SUCCESS;
    }
//プレイ中にツールチップに情報を追加できる
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if(Screen.hasShiftDown()){
            tooltip.add(Text.translatable("tooltip.tutorialmod.chisel.shift_down"));
        }else{
            tooltip.add(Text.translatable("tooltip.tutorialmod.chisel"));
        }
//もし座標を保存できていたら
        if (stack.get(ModDataComponentTypes.COORDINATES) != null ){
            tooltip.add(Text.literal("Last Block Changed at "+ stack.get(ModDataComponentTypes.COORDINATES)));
        }

        super.appendTooltip(stack, context, tooltip, type);
    }
}
