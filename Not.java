import org.bukkit.Material;

public class Not extends Template {
    Not(Location origin, Coordinates coordinates, Material material) {
        super(origin, coordinates, material,
                new String[] {
                        "X",
                        " ",
                        " ",
                        "X",
                },
                new String[] {
                        "+",
                        "X",
                        "v",
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
