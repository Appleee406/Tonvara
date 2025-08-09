package Tonvara.content.blocks;

import Tonvara.content.TO_Items;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;

import static mindustry.type.ItemStack.with;

public class TO_DefenseBlocks {
    static Block
    /** Walls */
    woodWall, stoneWall, stoneWallLarge, brickWall, brickWallLarge;

    public static void load(){
        short wallHealthMultiplier = 4;

        woodWall = new Wall("wood-wall"){{
            requirements(Category.defense, with(TO_Items.wood, 6));
            health = 45 * wallHealthMultiplier;
        }};

        stoneWall = new Wall("stone-wall"){{
            requirements(Category.defense, with(TO_Items.stone, 6));
            health = 55 * wallHealthMultiplier;
        }};

        stoneWallLarge = new Wall("stone-wall-large"){{
            requirements(Category.defense, ItemStack.mult(stoneWall.requirements, 4));
            health = stoneWall.health * wallHealthMultiplier;
            size = 2;
        }};

        brickWall = new Wall("brick-wall"){{
            requirements(Category.defense, with(TO_Items.stoneBrick, 6));
            health = 90 * wallHealthMultiplier;
        }};

        brickWallLarge = new Wall("brick-wall-large"){{
            requirements(Category.defense, ItemStack.mult(brickWall.requirements, 4));
            health = brickWall.health * wallHealthMultiplier;
            size = 2;
        }};
    }
}
