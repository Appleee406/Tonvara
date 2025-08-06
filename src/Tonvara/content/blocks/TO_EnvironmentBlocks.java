package Tonvara.content.blocks;

import Tonvara.content.TO_Items;
import mindustry.content.Liquids;
import mindustry.content.StatusEffects;
import mindustry.graphics.CacheLayer;
import mindustry.world.Block;
import mindustry.world.blocks.environment.Floor;
import mindustry.world.blocks.environment.OreBlock;
import mindustry.world.blocks.environment.StaticWall;
import mindustry.world.blocks.environment.TreeBlock;
import mindustry.world.meta.Attribute;

public class TO_EnvironmentBlocks {
    public static Block

    /** Walls */
    tree, bigTree, largeTree,

    /** Floor */
    smoothStoneFloor, floorStoneWater,

    /** Ores */
    oreHematite;

    public static void load(){
        tree = new StaticWall("tree"){{
            variants = 3;
            itemDrop = TO_Items.wood;
            attributes.set(Attribute.get("wood"), 1f);
        }};

        bigTree = new TreeBlock("big-tree");
        largeTree = new TreeBlock("large-tree"){{variants = 2;}};

        smoothStoneFloor = new Floor("smooth-stone-floor"){{
            itemDrop = TO_Items.stone;
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

        oreHematite = new OreBlock("ore-hematite", TO_Items.hematite){{
            oreDefault = true;
            oreThreshold = 0.81f;
            oreScale = 23.47619f;
        }};
    }
}
