package Tonvara.content.blocks;

import Tonvara.content.TO_Items;
import Tonvara.content.TO_Liquids;

import arc.graphics.Color;
import mindustry.content.Fx;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.draw.*;

import static mindustry.type.ItemStack.with;

public class TO_CrafterBlocks {
    public static Block woodOilExtractor, brickFactory;

    public static void load(){
        woodOilExtractor = new GenericCrafter("wood-oil-extractor"){{
                requirements(Category.crafting, with(
                        TO_Items.wood, 40,
                        TO_Items.stone, 30
                ));
                researchCost = with(
                        TO_Items.wood, 110,
                        TO_Items.stone, 65
                );

                drawer = new DrawMulti(
                        new DrawLiquidTile(TO_Liquids.woodOil),
                        new DrawRegion("-top"),
                        new DrawDefault()
                );

                consumeItem(TO_Items.wood, 2);
                outputLiquid = new LiquidStack(TO_Liquids.woodOil, 4f / 60f);

                itemCapacity = 5;
                liquidCapacity = 5f;
                craftTime = 0.6f * 60f;
                scaledHealth = 50;
                size = 2;

                rotateDraw = false;
                hasLiquids = hasItems = true;
                outputsLiquid = true;
            }};

        brickFactory = new GenericCrafter("brick-factory"){{
            requirements(Category.crafting, with(
                    TO_Items.wood, 30,
                    TO_Items.stone, 50
            ));
            researchCost = with(
                    TO_Items.wood, 300,
                    TO_Items.stone, 300
            );

            consumeItems(with(TO_Items.stone, 3, TO_Items.wood, 1));
            outputItem = new ItemStack(TO_Items.stoneBrick, 1);

            craftEffect = Fx.coalSmeltsmoke;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("ffef99")));

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.07f;

            itemCapacity = 10;
            craftTime = 65f;
            scaledHealth = 65f;
            size = 2;

            rotateDraw = false;
            hasLiquids = false;
            hasItems = true;
        }};
    }
}
