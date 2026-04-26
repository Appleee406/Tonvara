package Tonvara.content;

import arc.graphics.*;
import mindustry.type.*;

public class TO_Items {
    public static Item
    // resources
    wood, stone, stoneBrick, hematite, iron, silver,

    // ammo
    arrow;

    public static void load(){

        // resources begin
        wood = new Item("wood", Color.valueOf("8f4905")){{
            flammability = 0.7f;
        }};

        stone = new Item("stone", Color.darkGray);
        stoneBrick = new Item("stone-brick", Color.gray);
        hematite = new Item("hematite", Color.valueOf("ac8675"));
        iron = new Item("iron", Color.gray);
        silver = new Item("silver", Color.valueOf("c9c0bb"));
        // resources end

        // ammo begin
        arrow = new Item("arrow", Color.valueOf("8f4905")){{
            flammability = 0.4f;
        }};
        // ammo end
    }
}
