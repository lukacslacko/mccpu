package cpu.components.infra;

import cpu.Location;
import cpu.Template;
import org.bukkit.Material;

public class Crossing extends Template {
    public Crossing(String name, Material material) {
        super(name, "crossing", material,
                new String[] {
                        "   X   ",
                        "   X   ",
                        "X-   -X",
                        "   X   ",
                        "   X   ",
                },
                new String[] {
                        "   +   ",
                        "   A   ",
                        "++XXX++",
                        "   +   ",
                        "   +   ",
                },
                new String[] {
                        "       ",
                        "       ",
                        "   >   ",
                        "       ",
                        "       ",
                });
        setMarker("inputA", new Location(6, 0, 2));
        setMarker("outputA", new Location(3, 0, 0));
        setMarker("inputB", new Location(0, 0, 2));
        setMarker("inputA", new Location(3, 0, 4));

    }
}
