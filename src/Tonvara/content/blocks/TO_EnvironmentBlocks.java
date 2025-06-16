package Tonvara.content.blocks;

import Tonvara.content.TO_Items;
import mindustry.content.Liquids;
import mindustry.content.StatusEffects;
import mindustry.graphics.CacheLayer;
import mindustry.world.Block;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.environment.StaticWall;
import mindustry.world.blocks.environment.TreeBlock;
import mindustry.world.meta.Attribute;

public class TO_EnvironmentBlocks {
    public static Block

    /** Walls */
    tree, bigTree, largeTree,

    /** Floor */
    stoneFloor, floorStoneWater;

    public static void load(){
        tree = new StaticWall("tree"){{
            variants = 3;
            itemDrop = TO_Items.wood;
            attributes.set(Attribute.get("wood"), 1f);
        }};

        bigTree = new TreeBlock("big-tree");
        largeTree = new TreeBlock("large-tree"){{variants = 2;}};

        stoneFloor = new Floor("smooth-stone-floor"){{
            itemDrop = TO_Items.stone;
            attributes.set(Attribute.get("stone"), 0.75f);

            variants = 3;
        }};

        floorStoneWater = new Floor("stone-water-floor") {{
            speedMultiplier = 0.5f;
            variants = 0;
            status = StatusEffects.wet;
            statusDuration = 90f;
            liquidDrop = Liquids.water;
            isLiquid = true;
            cacheLayer = CacheLayer.water;
            albedo = 0.9f;
            supportsOverlay = true;
        }};
    }
}
