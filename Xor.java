import org.bukkit.Material;

class Xor extends Template {
    Xor(Location origin, Coordinates coordinates, Material material) {
        super(origin, coordinates, material,
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
                        "  X  "
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
                        "  +  ",
                });
    }

    Location inputA() {
        return loc(0, -1, 0);
    }

    Location inputB() {
        return loc(4, -1, 0);
    }

    Location output() {
        return loc(2, 11, 0);
    }
}
