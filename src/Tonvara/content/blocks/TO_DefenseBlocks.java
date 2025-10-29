package Tonvara.content.blocks;

import Tonvara.content.TO_Items;
import Tonvara.content.TO_Sounds;
import mindustry.content.Fx;
import mindustry.entities.bullet.*;
import mindustry.graphics.Pal;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;
import mindustry.world.blocks.defense.Wall;
import mindustry.world.blocks.defense.turrets.*;

import static mindustry.type.ItemStack.with;

public class TO_DefenseBlocks {
    public static Block
    // Walls
    woodWall, stoneWall, stoneWallLarge, brickWall, brickWallLarge,

    // Turrets
    crossbow;

    public static void load(){
        short wallHealthMultiplier = 4;

        // Walls begin
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

        // Walls end

        // Turrets begin
        crossbow = new ItemTurret("crossbow"){{
            requirements(Category.turret, with(TO_Items.stone, 20, TO_Items.wood, 30));
            ammo(
                    TO_Items.arrow, new BasicBulletType(2.7f, 10f){{
                        width = 5f;
                        height = 14f;
                        lifetime = 85f;
                        ammoMultiplier = 1;

                        hitEffect = despawnEffect = Fx.hitBulletColor;
                        hitColor = backColor = trailColor = Pal.copperAmmoBack;
                        frontColor = Pal.siliconAmmoFront;
                    }},
                    TO_Items.stone, new BasicBulletType(2.4f, 7f){{
                        width = 8f;
                        height = 8;
                        lifetime = 85f;
                        ammoMultiplier = 1;

                        hitEffect = despawnEffect = Fx.hitBulletColor;
                        hitColor = backColor = trailColor = frontColor = Pal.siliconAmmoFront;
                    }}
            );

            size = 2;
            recoil = 1f;
            targetAir = false;
            reload = 70f;
            range = 240f;
            inaccuracy = 1f;
            shootCone = 10f;
            scaledHealth = 110;
            shootSound = TO_Sounds.arrow;
            limitRange(0f);
        }};

        // Turrets end
    }
}
