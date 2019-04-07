package cpu.components;

import cpu.Location;
import cpu.render.Minecraft;
import org.bukkit.Material;

public class Components {
    public enum Kind {
        EXAMPLE;
    }
    public static void place(Kind kind, Location location, int quarters, Material material, Minecraft minecraft) {
        switch (kind) {
            case EXAMPLE:
                Example example = new Example("mccpu", material);
                example.shift(Location.origin().to(location));
                example.rotateAround(location, quarters);
                example.put(minecraft);
                return;
            default: throw new IllegalArgumentException("Cannot place " + kind);
        }
    }
}
