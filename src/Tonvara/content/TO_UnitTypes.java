package Tonvara.content;

import mindustry.ai.types.BuilderAI;
import mindustry.content.Fx;
import mindustry.entities.bullet.BasicBulletType;
import mindustry.gen.MechUnit;
import mindustry.type.UnitType;
import mindustry.type.Weapon;

public class TO_UnitTypes {
    public static UnitType
    /** Core units */
    engineer;

    public static void load(){
        engineer = new UnitType("engineer") {{
            aiController = BuilderAI::new;
            constructor = MechUnit::create;

            isEnemy = false;
            alwaysUnlocked = true;

            flying = false;
            canBoost = true;

            boostMultiplier = 0.9f;
            speed = 0.6f;
            rotateSpeed = 5f;

            mineWalls = mineFloor = true;

            health = 430f;
            armor = 3f;

            mechFrontSway = 0.55f;
            hitSize = 9f;

            mineSpeed = 2.5f;
            buildSpeed = 1f;

            itemCapacity = 30;
            mineTier = 1;

            weapons.add(new Weapon("artillery"){{
                reload = 23f;
                top = false;
                ejectEffect = Fx.casing1;

                x = 7.3f;
                y = 3f;

                bullet = new BasicBulletType(2.3f, 12){{
                    width = 8f;
                    height = 8f;
                    lifetime = 60f;
                    shootEffect = Fx.shootSmall;
                    smokeEffect = Fx.shootSmallSmoke;
                    buildingDamageMultiplier = 0.01f;
                }};
            }});
        }};
    }
}
