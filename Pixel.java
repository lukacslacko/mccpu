import org.bukkit.Material;

public class Pixel extends Template {
    Pixel(Location origin, Coordinates coordinates, Material material) {
        super(origin, coordinates, material,
                new String[] {
                        "    -XXXXXX XO",
                        "XXXXXXXXX X XO",
                        " -XXXXXXXXX XO",
                        "XXXXXXXXX X XO",
                },
                new String[] {
                        "    *+)>+>>X]O",
                        "+>>>>>++X]+X]O",
                        " *+)++>>+>>X]O",
                        "+>>+>>>+X]+X]O",
                });
    }
}
