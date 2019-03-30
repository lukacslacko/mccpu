import org.bukkit.Material;

public class Inc extends Component {
    Inc(Location origin, Vector width, Vector length, Material material) {
        super(origin, width, length, material,
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
}
