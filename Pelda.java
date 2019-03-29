import org.bukkit.Material;

public class Pelda extends Component {
    Pelda(Location origin, Vector width, Vector length, Material material) {
        super(origin, width, length, material,
                new String[] {
                        "XXXXXXXXXX",
                        "XXXXXXXXXX",
                        "XXXXXXXXXX",
                        "XXXXXXXXXX",
                        "XXXXXXXXXX",
                        "XXXXXXXXXX",
                        "XXXXXXXXXX",
                        "XXXXXXXXXX",
                        "XXXXXXXXXX",
                        "XXXXXXXXXX",
                },
                new String[] {
                        "*+++>++  ^",
                        "  +   + [X]",
                        "  A   V  v ",
                        "  +   +",
                        "  ++<++",
                        "       ",
                        "    M  ",
                        "   3*E ",
                        "    W  ",
                },
                new String[] {
                        "         ",
                        "         *"
                });
    }
}
