package cpu.components.arithmetic;

import cpu.Location;
import cpu.Template;
import org.bukkit.Material;

public class Not extends Template {
    public Not(String name, Material material) {
        super(name, "not", material,
                new String[] {"X", "X", "X", "X"},
                new String[] {"+", "X", "v", "+"});
        setMarker("input", new Location(0, 0, -1));
        setMarker("output", new Location(0, 0, 3));
    }
}
