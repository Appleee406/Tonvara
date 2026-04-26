package Tonvara.content.blocks;

import Tonvara.content.TO_Items;
import Tonvara.content.TO_Liquids;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.gen.Sounds;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.draw.DrawBlurSpin;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawMulti;

import static mindustry.type.ItemStack.with;

public class TO_PowerBlocks {
    public static Block steamEngine;

    public static void load(){
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
            powerProduction = 350f * (1f / 60f);

            hasItems = false;
            hasLiquids = true;
            consumeLiquid(TO_Liquids.steam, 10f / 60f);
            liquidCapacity = 10f;

            scaledHealth = 30f;
            size = 3;

            ambientSound = Sounds.loopHum;
            ambientSoundVolume = 0.05f;

            generateEffect = Fx.drillSteam;
            effectChance = 0.02f;
            drawer = new DrawMulti(new DrawDefault(), new DrawBlurSpin("-rotator", 6f){{
                blurThresh = 0.01f;
            }});
        }};
    }
}
