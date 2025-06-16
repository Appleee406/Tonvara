package Tonvara.content.blocks;

import Tonvara.content.TO_Items;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.distribution.*;

import static mindustry.type.ItemStack.*;

public class TO_DistributionBlocks {
    public static Block woodConveyor, woodRouter, junction, sorter, invertSorter, overflowGate, underflowGate, bridge;

    public static void load(){
        woodConveyor = new Conveyor("wood-conveyor"){{
            requirements(Category.distribution, with(TO_Items.wood, 1));
            researchCost = with(TO_Items.wood, 3);

            health = 5;
            speed = 0.03f;
            displayedSpeed = speed * 120;
        }};

        woodRouter = new Router("wood-router"){{
            requirements(Category.distribution, with(TO_Items.wood, 2));
            researchCost = with(TO_Items.wood, 5);

            health = 10;
        }};

        junction = new Junction("junction"){{
            requirements(Category.distribution, with(TO_Items.wood, 2, TO_Items.stone, 1));
            researchCost = with(TO_Items.wood, 5, TO_Items.stone, 3);

            health = 10;
        }};

        bridge = new BufferedItemBridge("bridge"){{
            requirements(Category.distribution, with(TO_Items.wood, 5, TO_Items.stone, 5));
            researchCost = with(TO_Items.wood, 10, TO_Items.stone, 10);

            health = 35;
            fadeIn = moveArrows = false;
            range = 5;
            speed = 74f;
            arrowSpacing = 6f;
            bufferCapacity = 14;
        }};

        sorter = new Sorter("sorter"){{
            requirements(Category.distribution, with(
                    TO_Items.wood, 2,
                    TO_Items.stone, 1
            ));
            researchCost = with(
                    TO_Items.wood, 5,
                    TO_Items.stone, 7
                    );

            health = 30;
        }};

        invertSorter = new Sorter("inverted-sorter"){{
            requirements(Category.distribution, with(
                    TO_Items.wood, 2,
                    TO_Items.stone, 1
            ));
            researchCost = with(
                    TO_Items.wood, 5,
                    TO_Items.stone, 7
            );

            invert = true;
            health = 30;
        }};

        overflowGate = new OverflowGate("overflow-gate"){{
            requirements(Category.distribution, with(
                    TO_Items.wood, 2,
                    TO_Items.stone, 1
            ));
            researchCost = with(
                    TO_Items.wood, 5,
                    TO_Items.stone, 7
            );

            health = 30;
        }};

        underflowGate = new OverflowGate("underflow-gate"){{
            requirements(Category.distribution, with(
                    TO_Items.wood, 2,
                    TO_Items.stone, 1
            ));
            researchCost = with(
                    TO_Items.wood, 5,
                    TO_Items.stone, 7
            );

            invert = true;
            health = 30;
        }};
    }
}
