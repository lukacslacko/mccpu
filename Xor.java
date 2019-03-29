import org.bukkit.Material;

public class Xor extends Component {
    public Xor(Location origin, Vector width, Vector length, Material material) {
        super(origin, width, length, material,
                new String[] {
                        "X X",
                        "X X",
                        "   ",
                        "   ",
                        "X X",
                        "   ",
                        " X ",
                        " X "

                },
                new String[] {
                        "+ +",
                        "+ +",
                        "XXX",
                        "TXT",
                        "+T+",
                        "X X",
                        "T+T",
                        " + "
                },
                new String[] {
                        "   ",
                        "   ",
                        "*+*",
                        " + ",
                        "   ",
                        "+  +"
                });
    }
}
