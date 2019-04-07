package cpu.components;

import cpu.Template;
import org.bukkit.Material;

public class Example extends Template {
    public Example(String name, Material material) {
        super(name, "example", material,
                new String[] {
                        "XXXXXXXXXXXXX",
                        "XXXXXXXXXXXXX",
                        "XXXXXXXXXXXXX",
                        "XXXXXXXXXXXXX",
                        "XXXXXXXXXXXXX",
                        "-------------",
                },
                new String[] {
                        "++++>)+O    ",
                        "  ? ^ +   M  ",
                        "  A[X]V  E*3 ",
                        "  + v U   W  ",
                        "  +(<++      ",
                });
    }
}
