package Tonvara.content;

import arc.graphics.Color;
import mindustry.content.StatusEffects;
import mindustry.type.Liquid;

public class TO_Liquids {
    public static Liquid woodOil, steam;

    public static void load(){
        woodOil = new Liquid("wood-oil", Color.valueOf("be8113")){{
            viscosity = 0.65f;
            flammability = 0.35f;
            effect = StatusEffects.slow;
        }};

        steam = new Liquid("steam", Color.lightGray){{
            gas = true;
            temperature = 0.6f;
            heatCapacity = 0.2f;
        }};
    }
}
