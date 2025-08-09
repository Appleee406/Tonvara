package Tonvara;

import Tonvara.content.*;
import Tonvara.content.blocks.*;
import arc.util.*;
import mindustry.mod.*;

public class Tonvara extends Mod{

    public Tonvara(){
        Log.info("Loaded Tonvara constructor.");
    }

    @Override
    public void loadContent(){
        TO_Sounds.load();
        TO_Attributes.load();
        TO_UnitTypes.load();
        TO_Liquids.load();
        TO_Items.load();

        /* Blocks */
        TO_StorageAndCoresBlocks.load();
        TO_ConduitBlocks.load();
        TO_CrafterBlocks.load();
        TO_DistributionBlocks.load();
        TO_ProductionBlocks.load();
        TO_EnvironmentBlocks.load();
        TO_DefenseBlocks.load();

        TO_Planets.load();
        //TO_Sectors.load();
        //TO_TechTree.load();
    }
}
