package Tonvara.maps.planet;

import arc.graphics.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import arc.util.noise.*;

import mindustry.*;
import mindustry.ai.*;
import mindustry.content.*;
import mindustry.core.*;
import mindustry.game.*;
import mindustry.maps.generators.*;
import mindustry.type.*;
import mindustry.world.*;

import static Tonvara.content.blocks.TO_EnvironmentBlocks.*;

public class TonvaraPlanetGenerator extends PlanetGenerator{
    public float heightScl = 0.5f, heightPow = 3f, heightMult = 1.3f, octaves = 8, persistence = 0.7f;

    public static float arkThresh = 0.28f, arkScl = 0.83f;
    public static int arkSeed = 7, arkOct = 2;
    public static float redThresh = 3.1f, noArkThresh = 0.3f;
    public static float airThresh = 0.13f, airScl = 14;

    BaseGenerator basegen = new BaseGenerator();
    Block[] terrain = {Blocks.grass, Blocks.water, Blocks.water, Blocks.grass, Blocks.water};

    {
        baseSeed = 15;
        defaultLoadout = Loadouts.basicBastion;
    }

    @Override
    public float getHeight(Vec3 position){
        if(getBlock(position) == Blocks.stone) return Mathf.pow(rawHeight(position), heightPow) * heightMult;
        return 0;
    }

    @Override
    public void getColor(Vec3 position, Color out){
        Block block = getBlock(position);
        out.set(block.mapColor).a(1f - block.albedo);
    }

    @Override
    public float getSizeScl(){
        return 2000 * 1.07f * 6f / 5f;
    }

    float rawHeight(Vec3 position){
        return Simplex.noise3d(seed, octaves, persistence, 1f/heightScl, 10f + position.x, 10f + position.y, 10f + position.z);
    }

    float rawTemp(Vec3 position){
        return position.dst(0, 0, 1)*2.2f - Simplex.noise3d(seed, 6, 0.54f, 1.4f, 10f + position.x, 10f + position.y, 10f + position.z) * 2.5f;
    }

    Block getBlock(Vec3 position){
        float px = position.x, py = position.y, pz = position.z;

        float ice = rawTemp(position);
        float height = rawHeight(position);
        height = Mathf.clamp(height);

        Block result = terrain[Mathf.clamp((int)(height * terrain.length), 0, terrain.length - 1)];

        if(ice < redThresh - noArkThresh && Ridged.noise3d(seed + arkSeed, px + 2f, py + 8f, pz + 1f, arkOct, arkScl) > arkThresh){
            result = Blocks.water;
        }

        if(ice > redThresh + 0.23f){
            result = Blocks.stone;
        }else if(ice > redThresh - 0.56f){
            result = Blocks.grass;
        }else if(ice > redThresh - 0.7){
            result = Blocks.sand;
        }

        return result;
    }

    @Override
    public void genTile(Vec3 position, TileGen tile){
        tile.floor = getBlock(position);

        tile.block = tile.floor.asFloor().wall;

        if(Ridged.noise3d(seed + 1, position.x, position.y, position.z, 2, airScl) > airThresh){
            tile.block = Blocks.air;
        }

        if(Ridged.noise3d(seed + 2, position.x, position.y + 4f, position.z, 3, 6f) > 0.6){
            tile.floor = Blocks.water;
        }
    }

    protected float noiseOct(float x, float y, double octaves, double falloff, double scl) {
        Vec3 v = sector.rect.project(x, y).scl(5);
        return Simplex.noise3d(seed, octaves, falloff, 1 / scl, v.x, v.y, v.z);
    }

    @Override
    public void generate() {
        this.tiles = tiles;
        this.sector = sector;
        this.rand.setSeed(sector.id);

        TileGen gen = new TileGen();
        for (int y = 0; y < tiles.height; y++) {
            for (int x = 0; x < tiles.width; x++) {
                gen.reset();
                Vec3 position = this.sector.rect.project(x / tiles.width, y / tiles.height);

                genTile(position, gen);
                tiles.set(x, y, new Tile(x, y, gen.floor, gen.overlay, gen.block));
            }
        }

        class Room {
            int x, y, radius;
            ObjectSet<Room> connected = new ObjectSet<>();

            Room(int x, int y, int radius) {
                this.x = x;
                this.y = y;
                this.radius = radius;
                connected.add(this);
            }

            void connect(Room to) {
                if (connected.contains(to)) return;

                connected.add(to);

                int nscl = rand.random(20, 60);
                int stroke = rand.random(4, 12);

                brush(pathfind(x, y, to.x, to.y, tile -> (tile.solid() ? 5 : 0) + noiseOct(tile.x, tile.y, 1, 1, 1 / nscl * 60) * 60, Astar.manhattan), stroke);
            }
        }

        cells(4);
        distort(10f, 12f);

        width = tiles.width;
        height = tiles.height;

        float constraint = 1.3f;
        float radius = width / 2 / Mathf.sqrt3;
        int rooms = rand.random(2, 5);
        Seq<Room> roomseq = new Seq<>();

        for (int i=0; i < rooms; i++) {
            Tmp.v1.trns(rand.random(360), rand.random(radius / constraint));
            float rx = (float) Math.floor(width / 2 + Tmp.v1.x);
            float ry = (float) Math.floor(height / 2 + Tmp.v1.y);
            float maxrad = radius - Tmp.v1.len();
            float rrad = (float) Math.floor(Math.min(rand.random(9, maxrad / 2), 30));

            roomseq.add(new Room((int) rx, (int) ry, (int) rrad));
        }

        Room spawn = null;
        Seq<Room> enemies = new Seq<>();
        int enemySpawns = rand.random(1, Math.max((int) Math.floor(this.sector.threat * 4), 1));

        int offset = rand.nextInt(360);
        float length = width / 2.55f - rand.random(12, 23);
        int angleStep = 5;
        int waterCheckRad = 5;
        for (int i=0; i < 360; i += angleStep) {
            int angle = offset + i;
            int cx = (int) Math.floor(width / 2 + Angles.trnsx(angle, length));
            int cy = (int) Math.floor(height / 2 + Angles.trnsy(angle, length));

            int waterTiles = 0;

            for (int rx = -waterCheckRad; rx <= waterCheckRad; rx++) {
                for (int ry = -waterCheckRad; ry <= waterCheckRad; ry++) {
                    Tile tile = tiles.get(cx + rx, cy + ry);

                    if (tile == null || tile.floor().liquidDrop != null) {
                        waterTiles++;
                    }
                }
            }

            if (waterTiles <= 4 || (i + angleStep >= 360)) {
                spawn = new Room(cx, cy, rand.random(10, 18));
                roomseq.add(spawn);

                for (int j=0; j < enemySpawns; j++) {
                    float enemyOffset = rand.range(60);

                    Tmp.v1.set(cx - width / 2, cy - height / 2).rotate(180 + enemyOffset).add(width / 2, this.height / 2);
                    Room espawn = new Room((int) Math.floor(Tmp.v1.x), (int) Math.floor(Tmp.v1.y), rand.random(10, 16));
                    roomseq.add(espawn);
                    enemies.add(espawn);
                };

                break;
            }

        }
        for (Room room : roomseq) {
            erase(room.x, room.y, room.radius);
        }

        int connections = rand.random(Math.max(rooms - 1, 1), rooms + 3);
        for (int i=0; i < connections; i++) {
            roomseq.random(rand).connect(roomseq.random(rand));
        }

        for (Room room : roomseq) {
            spawn.connect(room);
        }

        cells(1);
        distort(10, 6);

        inverseFloodFill(tiles.getn(spawn.x, spawn.y));

        Seq<Block> ores = Seq.with(oreHematite);
        float poles = Math.abs(this.sector.tile.v.y);
        float nmag = .5f;
        float scl = 1;
        float addscl = 1.3f;

        if (Simplex.noise3d(seed, 2, .5f, scl, this.sector.tile.v.x + 1, this.sector.tile.v.y, this.sector.tile.v.z) * nmag + poles > .5f * addscl) {
            ores.add(floorSmoothStone);
        }

        FloatSeq frequencies = new FloatSeq();
        for (int i=0; i < ores.size; i++) {
            frequencies.add(rand.random(-.1f, .01f) - i * .01f + poles * .04f);
        }

        pass((x, y) -> {
            if (!floor.asFloor().hasSurface()) return;

            int offsetX = x - 4, offsetY = y + 23;
            for (int i = ores.size - 1; i >= 0; i--) {
                Block entry = ores.get(i);
                float freq = frequencies.get(i);

                if (Math.abs(.5f - noiseOct(offsetX, offsetY + i * 999, 2, .7f, (40 + i * 2))) > .22f + i * .01f &&
                        Math.abs(.5f - noiseOct(offsetX, offsetY - i * 999, 1, 1, (30 + i * 4))) > .37f + freq) {
                    ore = entry;
                    break;
                }
            }
        });

        trimDark();
        median(2);
        tech();

        float difficulty = this.sector.threat;

        Schematics.placeLaunchLoadout(spawn.x, spawn.y);

        for (Room espawn : enemies) {
            tiles.getn(espawn.x, espawn.y).setOverlay(Blocks.spawn);
        }

        GameState state = Vars.state;

        if (this.sector.hasEnemyBase()) {
            basegen.generate(tiles, enemies.map(r -> tiles.getn(r.x, r.y)), tiles.get(spawn.x, spawn.y), state.rules.waveTeam, this.sector, difficulty);

            state.rules.attackMode = this.sector.info.attack = true;
        } else {
            state.rules.winWave = this.sector.info.winWave = (int) (10 + 5 * Math.max(difficulty * 10, 1));
        }

        float waveTimeDec = .4f;

        state.rules.waveSpacing = Mathf.lerp(60 * 65 * 2, 60 * 60 * 1, (float) Math.floor(Math.max(difficulty - waveTimeDec, 0) / .8f));
        state.rules.waves = this.sector.info.waves = true;
        state.rules.enemyCoreBuildRadius = 480;

        state.rules.spawns = Waves.generate(difficulty, new Rand(), state.rules.attackMode);
    }
}