package cpu.components;

import cpu.Template;
import org.bukkit.Material;

public class Example extends Template {
    public Example(String name, Material material) {
        super(name, "example", material,
                new String[] {
                        "XXXXXXXXXX",
                        "XXXXXXXXXX",
                        "XXXXXXXXXX",
                        "XXXXXXXXXX",
                        "XXXXXXXXXX",
                        "----------",
                },
                new String[] {
                        "++++>++   ",
                        "  +   +   ",
                        "  A   V   ",
                        "  +   +   ",
                        "  ++<++   ",
                });
    }
}
