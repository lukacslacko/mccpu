package cpu.components.memory;

import cpu.Location;
import cpu.Template;
import org.bukkit.Material;

public class Bit extends Template {
    public Bit(String name, Material material) {
        super(name, "bit", material,
                new String[] {
                        "  X               ",
                        "  X            X  ",
                        "  X            X  ",
                        "  X            X  ",
                        "  XX-         -X  ",
                },
                new String[] {
                        "  +               ",
                        "  +            +  ",
                        "  VXXXX  XXXXXXA  ",
                        "  +   X  X     +  ",
                        "  +++XXXXXXXXX++  ",
                },
                new String[] {
                        "                  ",
                        "                  ",
                        "  X>X]+  X++++<X  ",
                        "      V  v        ",
                        "     +>X]++X]+    ",
                }
                );
        setMarker("input", new Location(2, 0, 0));
        setMarker("output", new Location(15, 0, 1));
        setMarker("write", new Location(1, 1, 2));
        setMarker("read", new Location(16, 1, 2));
    }


}
