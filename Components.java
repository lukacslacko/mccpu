import org.bukkit.Material;

import java.util.Map;
import java.util.TreeMap;

public class Components {

    public static final Vector NORTH = new Vector(0, 0, -1);
    public static final Vector SOUTH = new Vector(0, 0, 1);
    public static final Vector EAST = new Vector(1, 0, 0);
    public static final Vector WEST = new Vector(-1, 0, 0);

    public static final String[] COMPONENTS = {"Inc", "Pelda"};

    public static void component(String name, Location origin, String width, String length, String color, RenderTarget target) {
        Map<String, Vector> directions = new TreeMap<>();
        directions.put("north", NORTH);
        directions.put("south", SOUTH);
        directions.put("east", EAST);
        directions.put("west", WEST);

        Map<String, Material> materials = new TreeMap<>();
        materials.put("blue", Wire.BLUE);
        materials.put("yellow", Wire.YELLOW);

        if (name.equals("Inc")) {
            new Inc(
                    origin,
                    directions.getOrDefault(width, NORTH),
                    directions.getOrDefault(length, WEST),
                    materials.getOrDefault(color, Wire.BLUE)).render(target);
        }
        if (name.equals("Pelda")) {
            new Pelda(
                    origin,
                    directions.getOrDefault(width, NORTH),
                    directions.getOrDefault(length, WEST),
                    materials.getOrDefault(color, Wire.BLUE)).render(target);
        }
    }
}
