package cpu.components.infra;

import cpu.Component;
import cpu.Location;
import cpu.blocks.*;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;

public class PatternMatcher extends Component {
    private final String name;

    public PatternMatcher(String name, int height, int pattern, Material material) {
        this.name = name;
        for (int i = 0; i < 8; ++i) {
            int y = 2*i+1;

            if (i < height) {
                add(new Location(1, y - 1, y), new Solid(material));
                add(new Location(2, y - 1, y), new Solid(material));
                add(new Location(2, y, y), new Wire());
                if (i > 0) {
                    add(new Location(2, y - 2, y - 1), new Solid(material));
                    add(new Location(2, y - 1, y - 1), new Wire());
                }
                if ((pattern & (1 << i)) > 0) {
                    add(new Location(1, y, y), new RedstoneWallTorch(BlockFace.EAST));
                } else {
                    add(new Location(1, y, y), new Repeater(BlockFace.WEST));
                }

                if (i == 0) {
                    add(new Location(3, y - 1, 1), new TopSlab());
                    add(new Location(3, y, 1), new Repeater(BlockFace.WEST));
                    add(new Location(4, y, 1), new Solid(material));
                    add(new Location(4, y, 2), new RedstoneWallTorch(BlockFace.SOUTH));
                }
            }

            add(new Location(4, y-1, 3), i == 0 ? new Solid(material) : new TopSlab());
            add(new Location(4, y, 3), new Wire());
            add(new Location(5, y-1, 3), new Solid(material));
            add(new Location(5, y, 3), new Repeater(BlockFace.WEST));
            if (i < 7) {
                add(new Location(4, y, 4), new TopSlab());
                add(new Location(4, y + 1, 4), new Wire());
            }
        }
        setMarker("input", new Location(0, 0, 0));
        setMarker("output", new Location(5, 0, 3));
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String kind() {
        return "PatternMatcher";
    }
}
