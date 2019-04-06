import org.bukkit.Material;

class Inc extends Template {
    Inc(Location origin, Coordinates coordinates, Material material) {
        super(origin, coordinates, material,
                new String[] {
                        "    X",
                        "X-+[X",
                        "-   X",
                        "+   X",
                        "+   X",
                        "XXX X",
                        "X X  ",
                        "X X X",
                        "XX XX",
                        "X   X",
                        "X   X",
                        "     ",
                        "XXXXX",
                        "    X"
                },
                new String[] {
                        "    +",
                        "++- +",
                        "+   +",
                        "X   +",
                        "-   +",
                        "+>+[X",
                        "V + v",
                        "+ X +",
                        "++v++",
                        "+   +",
                        "X   X",
                        "v   v",
                        "+++++",
                        "    +"
                });
    }

    Location input() {
        return loc(4, -1, 0);
    }

    Location output() {
        return loc(4, 13, 0);
    }
}
