package cpu.components.infra;

import cpu.Location;
import cpu.Template;
import org.bukkit.Material;

public class Transmitter extends Template {
    public Transmitter(String name, Material material, boolean isTop) {
        super(name, "transmitter", material,
                new String[] {
                        "  X  ",
                        "  X  ",
                        "  X  ",
                        "X X  ",
                        "X -  ",
                        "X +  ",
                        "XXVXX",
                        "  + X",
                        "  - X",
                        "  X X",
                        "  XXX",
                        "+XXXX",
                },
                new String[] {
                        "  +  ",
                        "  +  ",
                        "  +  ",
                        "+ +  ",
                        "+ +  ",
                        "+ X  ",
                        "+>X++",
                        "  X +",
                        "  + +",
                        "  X]V",
                        "  V+U",
                        " +)>X",
                },
                new String[] {
                        "  X  ",
                        "  X  ",
                        "  X  ",
                        "X X  ",
                        "X -  ",
                        "X +  ",
                        "X V  ",
                        "X +  ",
                        "X -  ",
                        "X X  ",
                        "XXX  ",
                        "XXXX+",
                },
                new String[] {
                        "  +  ",
                        "  +  ",
                        "  +  ",
                        "+ +  ",
                        "+ +  ",
                        "+ X  ",
                        "+ X  ",
                        "+ X  ",
                        "+ +  ",
                        "V[X  ",
                        "U+V  ",
                        "X<(+ ",
                }, isTop ? new String[] {
                        "     ",
                        "     ",
                        "     ",
                        "     ",
                        "     ",
                        "  +  ",
                        "  +  ",
                        "  +  ",
                        "     ",
                        "     ",
                        "     ",
                        "+    "}
                        : null);
        setMarker("input", new Location(2, 0, -1));
        setMarker("data", new Location(0, 0, -1));
        setMarker("output", new Location(0, 3, 11));
    }
}
