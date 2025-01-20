package net.mana7na.tutorialmod.component;

import net.mana7na.tutorialmod.TutorialMod;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
//    COORDINATES　を呼び出すと、BlockPos、ブロックの座標を取得できる
    public static final ComponentType<BlockPos> COORDINATES =
            register("coordinates",builder -> builder.codec(BlockPos.CODEC));

    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderUnaryOperator){
//        modの名前とか使うやつを読んで、
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(TutorialMod.MOD_ID,name),
//                Componentを追加する、、？
//                これを呼び出すときに入れたものを入れる、クラス宣言時の(~,~)みたいなやつ
                builderUnaryOperator.apply(ComponentType.builder()).build());
    }

    public static void registerDataComponentTypes(){
        TutorialMod.LOGGER.info("Registering Data Component Types for "+ TutorialMod.MOD_ID);
    }
}
