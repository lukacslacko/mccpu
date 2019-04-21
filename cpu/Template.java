package cpu;

import cpu.blocks.*;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;

public class Template extends Component {
    private final String name, kind;

    // A layersben jobbra: pozitiv x, lefele: pozitiv z.
    public Template(String name, String kind, Material material, String[]... layers) {
        this.name = name;
        this.kind = kind;
        for (int h = 0; h < layers.length; ++h) {
            if (layers[h] == null) continue;
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
            case '<': add(location, new Repeater(BlockFace.EAST)); return;
            case '>': add(location, new Repeater(BlockFace.WEST)); return;
            case 'A': add(location, new Repeater(BlockFace.SOUTH)); return;
            case 'V': add(location, new Repeater(BlockFace.NORTH)); return;
            case '3': add(location, new StickyPiston(BlockFace.EAST)); return;
            case 'E': add(location, new StickyPiston(BlockFace.WEST)); return;
            case 'W': add(location, new StickyPiston(BlockFace.SOUTH)); return;
            case 'M': add(location, new StickyPiston(BlockFace.NORTH)); return;
            case '*': add(location, new RedstoneTorch()); return;
            case '[': add(location, new RedstoneWallTorch(BlockFace.WEST)); return;
            case ']': add(location, new RedstoneWallTorch(BlockFace.EAST)); return;
            case '^': add(location, new RedstoneWallTorch(BlockFace.NORTH)); return;
            case 'v': add(location, new RedstoneWallTorch(BlockFace.SOUTH)); return;
            case ')': add(location, new SubtractComparator(BlockFace.WEST)); return;
            case '(': add(location, new SubtractComparator(BlockFace.EAST)); return;
            case 'U': add(location, new SubtractComparator(BlockFace.NORTH)); return;
            case '?': add(location, new SubtractComparator(BlockFace.SOUTH)); return;
            case 'O': add(location, new Solid(Material.REDSTONE_LAMP)); return;
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
