import org.bukkit.Material;

public class Passthrough4 extends Template {
    Passthrough4(Location origin, Coordinates coordinates, Material material) {
        super(origin, coordinates, material,
                new String[] {
                        "X",
                        "X",
                        "X",
                        "X",
                },
                new String[] {
                        "+",
                        "+",
                        "+",
                        "+",
                });
    }

    Location input() {
        return loc(0, -1, 0);
    }

    Location output() {
        return loc(0, 3, 0);
    }
}
