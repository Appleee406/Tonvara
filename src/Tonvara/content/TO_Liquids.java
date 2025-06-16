package Tonvara.content;

import arc.graphics.Color;
import mindustry.content.StatusEffects;
import mindustry.type.Liquid;

public class TO_Liquids {
    public static Liquid woodOil;

    public static void load(){
        woodOil = new Liquid("wood-oil", Color.valueOf("be8113")){{
            viscosity = 0.65f;
            flammability = 0.20f;
            effect = StatusEffects.slow;
        }};
    }
}
