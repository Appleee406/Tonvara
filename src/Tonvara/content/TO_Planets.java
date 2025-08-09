package Tonvara.content;

import Tonvara.content.blocks.TO_StorageAndCoresBlocks;
import Tonvara.maps.planet.TonvaraPlanetGenerator;
import arc.graphics.Color;
import mindustry.content.Planets;
import mindustry.game.Team;
import mindustry.graphics.Pal;
import mindustry.graphics.g3d.HexMesh;
import mindustry.graphics.g3d.HexSkyMesh;
import mindustry.graphics.g3d.MultiMesh;
import mindustry.type.Planet;
import mindustry.world.meta.Env;

public class TO_Planets {
    public static Planet tonvara;

    public static void load(){
        tonvara = new Planet("tonvara", Planets.sun, 0.9f, 4){{
            generator = new TonvaraPlanetGenerator();
            meshLoader = () -> new HexMesh(this, 6);
            alwaysUnlocked = true;
            iconColor = Color.forest;

            rotateTime = 30 * 24; // 30 minutes in one day
            orbitTime = rotateTime * 50; // 50 days in one year

            cloudMeshLoader = () -> new MultiMesh(
                    new HexSkyMesh(this, 1, 0.6f, 0.16f, 5, Color.white.cpy().lerp(Pal.plastanium, 0.55f).a(0.75f), 2, 0.45f, 1f, 0.41f)
            );
            atmosphereColor = Color.valueOf("00541f");
            atmosphereRadIn = 0.02f;
            atmosphereRadOut = 0.35f;
            landCloudColor =  Color.valueOf("7a7666");

            defaultEnv = Env.terrestrial | Env.groundOil | Env.groundWater | Env.oxygen;
            orbitSpacing = 10f;
            startSector = 12;
            totalRadius += 2.6f;

            ruleSetter = r -> {
              r.waveTeam = Team.sharded;
              r.fog = true;
            };
            showRtsAIRule = true;

            campaignRuleDefaults.fog = true;
            campaignRuleDefaults.sectorInvasion = true;
            campaignRuleDefaults.randomWaveAI = true;
            campaignRuleDefaults.rtsAI = true;

            allowWaveSimulation = true;
            allowLaunchToNumbered = false;
            allowLaunchLoadout = true;

            enemyCoreSpawnReplace = true;
            enemyBuildSpeedMultiplier = 0.8f;

            prebuildBase = false;
            defaultCore = TO_StorageAndCoresBlocks.coreCapsule;
        }};
    }
}














