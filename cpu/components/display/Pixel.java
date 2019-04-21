package cpu.components.display;

import cpu.Location;
import cpu.Template;
import org.bukkit.Material;

public class Pixel extends Template {
    public Pixel(String name, Material material) {
        super(name, "pixel", material,
                new String[] {
                        "    -XXXXXX X ",
                        "XXXXXXXXX X X ",
                        " -XXXXXXXXX X ",
                        "XXXXXXXXX X X ",
                },
                new String[] {
                        "    *+)>+>>X]O",
                        "+>>>>>++X]+X]O",
                        " *+)++>>+>>X]O",
                        "+>>+>>>+X]+X]O",
                },
                new String[] {
                        "             O",
                        "             O",
                        "             O",
                        "             O",
                });
        setMarker("inputA", new Location(-1, 0, 1));
        setMarker("inputB", new Location(-1, 0, 3));
    }
}
