package cpu.components;

import cpu.Template;
import org.bukkit.Material;

public class IncPiece extends Template {
    public IncPiece(String name, Material material) {
        super(name, "inc piece", material,
                new String[] {
                        "     X",
                        "XX-+[X",
                        "-    X",
                        "++   X",
                        " +   X",
                        " XXX X",
                        " X X  ",
                        " X X X",
                        " XX XX",
                        " X   X",
                        " X   X",
                        "      ",
                        " XXXXX",
                        "     X"
                },
                new String[] {
                        "     +",
                        "+<+- +",
                        "+    +",
                        "XX   +",
                        " -   +",
                        " +>+[X",
                        " V + v",
                        " + X +",
                        " ++v++",
                        " +   +",
                        " X   X",
                        " v   v",
                        " +++++",
                        "     +"
                });
    }
}
