package Tonvara.content.blocks;

import Tonvara.content.TO_Items;
import Tonvara.content.TO_Liquids;
import Tonvara.content.TO_Sounds;
import arc.struct.Seq;
import mindustry.content.Liquids;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.type.LiquidStack;
import mindustry.world.Block;
import mindustry.world.blocks.power.ConsumeGenerator;
import multicraft.IOEntry;
import multicraft.MultiCrafter;
import multicraft.Recipe;

import static mindustry.type.ItemStack.with;

public class TO_PowerBlocks {
    public static Block steamEngine, boiler;

    public static void load(){
        boiler = new MultiCrafter("boiler"){{
            requirements(Category.crafting, with(
                    TO_Items.stoneBrick, 20,
                    TO_Items.iron, 10,
                    TO_Items.silver, 10
            ));
            researchCost = with(
                    TO_Items.stoneBrick, 200,
                    TO_Items.iron, 70,
                    TO_Items.silver, 70
            );

            maxEfficiency = 0.4f;
            liquidCapacity = 40f;
            scaledHealth = 30f;
            size = 2;

            ambientSound = TO_Sounds.boiling;
            ambientSoundVolume = 0.05f;

            resolvedRecipes = Seq.with(
                    new Recipe(
                            new IOEntry(
                                    Seq.with(),
                                    Seq.with(LiquidStack.with(Liquids.water, 10f / 60f)), 0f, 1
                            ),
                            new IOEntry(
                                    Seq.with(),
                                    Seq.with(LiquidStack.with(TO_Liquids.steam, 10f / 60f))
                            ), 1.5f * 60f
                    )
            );
        }};

        steamEngine = new ConsumeGenerator("steam-engine"){{
            requirements(Category.power, with(
                    TO_Items.stoneBrick, 20,
                    TO_Items.iron, 50,
                    TO_Items.silver, 40
            ));
            researchCost = with(
                    TO_Items.stoneBrick, 200,
                    TO_Items.iron, 130,
                    TO_Items.silver, 200
            );

            hasLiquids = true;
            consumeLiquid(TO_Liquids.steam, 0.1f);
            powerProduction = 350f * (1f / 60f);

            scaledHealth = 30f;
            size = 2;

            ambientSound = Sounds.loopSmelter;
            ambientSoundVolume = 0.05f;
        }};
    }
}
