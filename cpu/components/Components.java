package cpu.components;

import cpu.Component;
import cpu.Location;
import cpu.components.arithmetic.Dec;
import cpu.components.arithmetic.Inc;
import cpu.components.display.DisplayRow;
import cpu.components.memory.Bit8;
import cpu.components.memory.ByteRow;
import cpu.render.Minecraft;
import org.bukkit.Material;

public class Components {
    public enum Kind {
        EXAMPLE, INC8, DEC8, BYTE, BYTE4, BYTE64, DISPLAY16x8;
    }

    private static void move(Component component, Location location, int quarters, Minecraft minecraft) {
        component.rotateAround(Location.origin(), quarters);
        component.shift(Location.origin().to(location));
        component.put(minecraft);
    }

    public static void place(Kind kind, Location location, int quarters, Material material, Minecraft minecraft) {
        switch (kind) {
            case DISPLAY16x8:
                move(new DisplayRow("mccpu", 16, material), location, quarters, minecraft);
                return;
            case BYTE4:
                move(new ByteRow(4,"mccpu", material, Material.GREEN_WOOL, Material.YELLOW_WOOL), location, quarters, minecraft);
                return;
            case BYTE64:
                move(new ByteRow(64,"mccpu", material, Material.GREEN_WOOL, Material.YELLOW_WOOL), location, quarters, minecraft);
                return;
            case BYTE:
                move(new Bit8("mccpu", material), location, quarters, minecraft);
                return;
            case EXAMPLE:
                move(new Example("mccpu", material), location, quarters, minecraft);
                return;
            case INC8:
                move(new Inc("mccpu", 8, material), location, quarters, minecraft);
                return;
            case DEC8:
                move(new Dec("mccpu", 8, material), location, quarters, minecraft);
                return;
            default: throw new IllegalArgumentException("Cannot place " + kind);
        }
    }
}
