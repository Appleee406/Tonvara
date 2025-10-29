package Tonvara.content;

import arc.struct.Seq;
import mindustry.content.Items;
import mindustry.game.Objectives.*;

import static Tonvara.content.blocks.TO_ProductionBlocks.*;
import static Tonvara.content.blocks.TO_DistributionBlocks.*;
import static Tonvara.content.blocks.TO_StorageAndCoresBlocks.*;
import static Tonvara.content.blocks.TO_DefenseBlocks.*;
import static Tonvara.content.blocks.TO_CrafterBlocks.*;
import static Tonvara.content.blocks.TO_ConduitBlocks.*;

import static mindustry.content.TechTree.*;

public class TonvaraTechTree {
    public static void load(){
        TO_Planets.tonvara.techTree = nodeRoot("tonvara", coreCapsule, () -> {
            node(woodConveyor, () -> {
               node(junction, () -> {
                   node(woodRouter, () -> {
                       node(bridge, () -> {});
                       node(sorter, () -> {
                           node(invertSorter);
                           node(overflowGate, () -> {
                               node(underflowGate);
                           });
                       });
                   });
               });
            });

            node(woodConduit, Seq.with(new Research(woodOilExtractor)), () -> {
                node(liquidJunction, () -> {
                    node(bridgeConduit);
                    node(woodRouter, () -> {
                        node(barrel);
                    });
                });
            });

            node(sawmill, () -> {
                node(basicDrill);
            });

            node(ammoAssemblerT1, Seq.with(new Research(TO_Items.stone), new Research(TO_Items.wood)), () -> {

            });
            node(woodOilExtractor, Seq.with(new Research(TO_Items.stone), new Research(TO_Items.wood)), () -> {
                node(smelter);
            });

            node(crossbow, Seq.with(new Research(TO_Items.stone), new Research(TO_Items.wood)), () -> {

            });

            node(woodWall, Seq.with(new Research(TO_Items.wood)), () -> {
                node(stoneWall, () -> {
                    node(stoneWallLarge);
                    node(brickWall, () -> {
                        node(brickWallLarge);
                    });
                });
            });

            nodeProduce(TO_Items.wood, () -> {
                nodeProduce(TO_Items.stone, () -> {
                    nodeProduce(TO_Items.hematite, () -> {
                        nodeProduce(TO_Items.iron, () -> {});
                    });
                    nodeProduce(TO_Items.stoneBrick, () -> {});
                    nodeProduce(TO_Liquids.woodOil, () -> {});
                    nodeProduce(Items.coal, () -> {});
                });
                nodeProduce(TO_Items.arrow, () -> {});
            });
        });
    }
}
