package cpu.components;

import cpu.Template;
import org.bukkit.Material;

public class Not extends Template {
    public Not(String name, Material material) {
        super(name, "not", material,
                new String[] {"X XX", " XX "},
                new String[] {"X + ", " + X"});
    }
}
