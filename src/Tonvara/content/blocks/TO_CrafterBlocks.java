package Tonvara.content.blocks;

import Tonvara.content.TO_Items;
import Tonvara.content.TO_Liquids;

import arc.graphics.Color;
import arc.struct.Seq;
import mindustry.content.Fx;
import mindustry.content.Items;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.draw.*;
import multicraft.IOEntry;
import multicraft.MultiCrafter;
import multicraft.Recipe;

import static mindustry.type.ItemStack.with;

public class TO_CrafterBlocks {
    public static Block woodOilExtractor, smelter, ammoAssemblerT1;

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

                hasLiquids = hasItems = true;
                outputsLiquid = true;
            }};

        smelter = new MultiCrafter("smelter"){{
            requirements(Category.crafting, with(
                    TO_Items.wood, 30,
                    TO_Items.stone, 50
            ));
            researchCost = with(
                    TO_Items.wood, 300,
                    TO_Items.stone, 300
            );

            itemCapacity = 10;
            scaledHealth = 65f;
            size = 2;

            ambientSound = Sounds.smelter;
            ambientSoundVolume = 0.07f;

            craftEffect = Fx.coalSmeltsmoke;
            drawer = new DrawMulti(new DrawDefault(), new DrawFlame(Color.valueOf("ffef99")));

            resolvedRecipes = Seq.with(
                    new Recipe(
                            new IOEntry(
                                    Seq.with(ItemStack.with(TO_Items.stone, 3, TO_Items.wood, 4)),
                                    Seq.with()
                            ),
                            new IOEntry(
                                    Seq.with(ItemStack.with(TO_Items.stoneBrick, 1)),
                                    Seq.with()
                            ), 60f
                    ),
                    new Recipe(
                            new IOEntry(
                                    Seq.with(ItemStack.with(TO_Items.stone, 3, Items.coal, 1)),
                                    Seq.with()
                            ),
                            new IOEntry(
                                    Seq.with(ItemStack.with(TO_Items.stoneBrick, 1)),
                                    Seq.with()
                            ), 0.75f * 60f
                    ),
                    new Recipe(
                            new IOEntry(
                                    Seq.with(ItemStack.with(TO_Items.hematite, 3, Items.coal, 2)),
                                    Seq.with()
                            ),
                            new IOEntry(
                                    Seq.with(ItemStack.with(TO_Items.iron, 1)),
                                    Seq.with()
                            ), 1.5f * 60f
                    )
            );
        }};

        ammoAssemblerT1 = new MultiCrafter("ammo-assembler-t1"){{
            requirements(Category.crafting, with(
                    TO_Items.wood, 20,
                    TO_Items.stone, 40,
                    TO_Items.stoneBrick, 20
            ));
            researchCost = with(
                    TO_Items.wood, 100,
                    TO_Items.stone, 210,
                    TO_Items.stoneBrick, 50
            );

            itemCapacity = 15;
            scaledHealth = 55f;
            size = 2;

            ambientSound = Sounds.machine;
            ambientSoundVolume = 0.1f;
            craftEffect = Fx.formsmoke;

            resolvedRecipes = Seq.with(
                    new Recipe(
                            new IOEntry(
                                    Seq.with(ItemStack.with(TO_Items.wood, 1, TO_Items.stone, 1)),
                                    Seq.with()
                            ),
                            new IOEntry(
                                    Seq.with(ItemStack.with(TO_Items.arrow, 2)),
                                    Seq.with()
                            ), 0.8f * 60f
                    )
            );
        }};
    }
}
