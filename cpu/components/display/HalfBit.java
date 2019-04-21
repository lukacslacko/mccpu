package cpu.components.display;

import cpu.Location;
import cpu.Template;
import org.bukkit.Material;

public class HalfBit extends Template {
    public HalfBit(String name, Material material) {
        super(name, "halfbit", material,
                new String[] {
                        "  X     ",
                        "  X     ",
                        "  X     ",
                        "  X     ",
                        "  XX-   ",
                },
                new String[] {
                        "  +     ",
                        "  +     ",
                        " XVXXXX ",
                        "  +   X ",
                        "  +++XXX",
                },
                new String[] {
                        "        ",
                        "        ",
                        " +X>X]+ ",
                        "      V ",
                        "     +>+",
                });
        setMarker("input", new Location(2, 0, 0));
        setMarker("write", new Location(0, 1, 2));
        setMarker("output", new Location(7, 1, 4));
    }
}
