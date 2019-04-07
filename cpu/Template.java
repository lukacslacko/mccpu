package cpu;

import cpu.blocks.*;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;

public class Template extends Component {
    private final String name, kind;

    public Template(String name, String kind, Material material, String[]... layers) {
        this.name = name;
        this.kind = kind;
        for (int h = 0; h < layers.length; ++h) {
            for (int l = 0; l < layers[h].length; ++l) {
                for (int w = 0; w < layers[h][l].length(); ++w) {
                    Location location = new Location(w, h, l);
                    char c = layers[h][l].charAt(w);
                    try {
                        addChar(location, material, c);
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("Template " + describe(), e);
                    }
                }
            }
        }
    }

    private void addChar(Location location, Material material, char c) {
        switch (c) {
            case ' ': add(location, new Air()); return;
            case 'X': add(location, new Solid(material)); return;
            case '+': add(location, new Wire()); return;
            case '-': add(location, new TopSlab()); return;
            case '>': add(location, new Repeater(BlockFace.EAST)); return;
            case '<': add(location, new Repeater(BlockFace.WEST)); return;
            case 'A': add(location, new Repeater(BlockFace.SOUTH)); return;
            case 'V': add(location, new Repeater(BlockFace.NORTH)); return;
            default: throw new IllegalArgumentException("Ismeretlen betu: '" + c + "'");
        }
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String kind() {
        return kind;
    }
}
