package Tonvara.content;

import arc.graphics.*;
import arc.struct.*;
import mindustry.content.Items;
import mindustry.type.*;

public class TO_Items {
    public static Item
    // resources
    wood, stone, stoneBrick, hematite, iron,

    // ammo
    arrow;

    public static void load(){

        // resources begin
        wood = new Item("wood", Color.valueOf("8f4905")){{
            flammability = 1.0f;
        }};

        stone = new Item("stone", Color.darkGray);
        stoneBrick = new Item("stone-brick", Color.gray);
        hematite = new Item("hematite", Color.valueOf("ac8675"));
        iron = new Item("iron", Color.gray);

        // resources end

        // ammo begin
        arrow = new Item("arrow", Color.valueOf("8f4905"));

        // ammo end
    }
}
