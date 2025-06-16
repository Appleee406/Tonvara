package Tonvara.content.blocks;

import Tonvara.content.*;

import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.production.Drill;
import mindustry.world.blocks.production.WallCrafter;
import mindustry.world.meta.Attribute;

import static mindustry.type.ItemStack.*;

public class TO_ProductionBlocks {
    public static Block sawmill, basicDrill;

    public static void load(){
        sawmill = new WallCrafter("sawmill"){{
            requirements(Category.production, with(
                    TO_Items.wood, 10,
                    TO_Items.stone, 5
            ));
            researchCost = with(
                    TO_Items.wood, 10,
                    TO_Items.stone, 10
            );

            consumeLiquid(TO_Liquids.woodOil, 0.03f).boost();

            itemCapacity = 3;
            health = 60;
            drillTime = 120f;
            size = 1;
            output = TO_Items.wood;
            attribute = Attribute.get("wood");
            fogRadius = 2;
            ambientSound = TO_Sounds.chainsaw;
            ambientSoundVolume = 0.03f;
        }};

        basicDrill = new Drill("basic-drill"){{
            requirements(Category.production, with(
                    TO_Items.wood, 20,
                    TO_Items.stone, 10
            ));
            researchCost = with(
                    TO_Items.wood, 40,
                    TO_Items.stone, 20
            );

            // consumeItem(TO_Items.wood, 1);
            consumeLiquid(TO_Liquids.woodOil, 0.013f).boost();

            tier = 1;
            drillTime = 600;
            size = 2;
        }};
    }
}
