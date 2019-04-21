package cpu.components.arithmetic;

import cpu.Location;
import cpu.Template;
import org.bukkit.Material;

public class Xor extends Template {
    public Xor(String name, Material material) {
        super(name, "xor", material,
                new String[] {
                        "X   X",
                        "X   X",
                        "X   X",
                        "  X  ",
                        "  X  ",
                        "X   X",
                        "XX XX",
                        "X   X",
                        "  X  ",
                        "  X  ",
                        "  X  ",
                },
                new String[] {
                        "+   +",
                        "+   +",
                        "+   +",
                        "X]+[X",
                        "v + v",
                        "+ X +",
                        "++v++",
                        "+   +",
                        "X]+[X",
                        "  +  ",
                        "  +  ",
                });
        setMarker("inputA", new Location(0, 0, -1));
        setMarker("inputB", new Location(4, 0, -1));
        setMarker("output", new Location(2, 0, 10));
    }
}
