package Tonvara.content.blocks;

import Tonvara.content.TO_Items;
import Tonvara.content.TO_UnitTypes;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.meta.BuildVisibility;

import static mindustry.type.ItemStack.*;

public class TO_StorageAndCoresBlocks {
    public static Block
    /** Cores */
    coreCapsule;

    public static void load(){
        coreCapsule = new CoreBlock("core-capsule"){{
            requirements(Category.effect, BuildVisibility.coreZoneOnly,
                    with(TO_Items.stone, 800, TO_Items.stoneBrick, 600));

            alwaysUnlocked = true;
            isFirstTier = true;
            unitType = TO_UnitTypes.engineer;

            health = 1500;
            itemCapacity = 3300;
            size = 3;
            buildCostMultiplier = 2f;

            unitCapModifier = 6;
        }};
    }
}
