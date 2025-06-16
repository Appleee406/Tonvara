package Tonvara.content.blocks;

import Tonvara.content.TO_Items;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.liquid.*;

import static mindustry.type.ItemStack.with;

public class TO_ConduitBlocks {
    public static Block woodConduit, liquidRouter, liquidJunction, bridgeConduit, barrel;

    public static void load() {
        woodConduit = new Conduit("wood-conduit"){{
           requirements(Category.liquid, with(TO_Items.wood, 2));
           researchCost =  with(TO_Items.wood, 20);

           liquidCapacity = 3f;
           health = 15;
        }};

        liquidRouter = new LiquidRouter("liquid-router"){{
            requirements(Category.liquid, with(TO_Items.wood, 2, TO_Items.stone, 2));
            researchCost =  with(TO_Items.wood, 20, TO_Items.stone, 30);

            liquidCapacity = 10f;
            solid = false;
            underBullets = true;
        }};

        liquidJunction = new LiquidJunction("liquid-junction"){{
            requirements(Category.liquid, with(TO_Items.wood, 2, TO_Items.stone, 2));
            researchCost =  with(TO_Items.wood, 20, TO_Items.stone, 30);

            solid = false;
        }};

        bridgeConduit = new LiquidBridge("bridge-conduit"){{
            requirements(Category.liquid, with(TO_Items.wood, 2, TO_Items.stone, 2));
            researchCost =  with(TO_Items.wood, 40, TO_Items.stone, 37);

            arrowSpacing = 6f;
            range = 4;
            liquidCapacity = 10f;
            hasPower = false;
            fadeIn = false;
            moveArrows = true;
        }};

        barrel = new LiquidRouter("barrel"){{
            requirements(Category.liquid, with(TO_Items.wood, 20, TO_Items.stone, 20));
            researchCost =  with(TO_Items.wood, 150, TO_Items.stone, 100);

            liquidCapacity = 370f;
            size = 2;
            solid = true;
        }};
    }
}
