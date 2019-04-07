package cpu.components;

import cpu.Component;
import cpu.Location;
import cpu.render.Minecraft;
import org.bukkit.Material;

public class Components {
    public enum Kind {
        EXAMPLE, INC8;
    }

    private static void move(Component component, Location location, int quarters, Minecraft minecraft) {
        component.rotateAround(Location.origin(), quarters);
        component.shift(Location.origin().to(location));
        component.put(minecraft);
    }

    public static void place(Kind kind, Location location, int quarters, Material material, Minecraft minecraft) {
        switch (kind) {
            case EXAMPLE:
                move(new Example("mccpu", material), location, quarters, minecraft);
                return;
            case INC8:
                move(new Inc("mccpu", 8, material), location, quarters, minecraft);
                return;
            default: throw new IllegalArgumentException("Cannot place " + kind);
        }
    }
}
