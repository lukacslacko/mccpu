package cpu.components.memory.bank;

import cpu.Location;
import cpu.Template;
import org.bukkit.Material;

public class Blocker extends Template {
    public Blocker(String name, Material material) {
        super(name, "blocker", material,
                new String[] {
                        "     +      ",
                        "     -      ",
                        "     X      ",
                        "XXXX XXX X  ",
                },
                new String[] {
                        "     -      ",
                        "     +      ",
                        "     V      ",
                        "+>+X]++X]+  ",
                });
        setMarker("block", new Location(5, 0, 1));
        setMarker("input", new Location(0, 0, 3));
        setMarker("output", new Location(10, 0, 3));

    }
}
