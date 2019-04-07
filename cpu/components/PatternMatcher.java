package cpu.components;

import cpu.Component;
import cpu.Location;
import cpu.blocks.Solid;
import cpu.blocks.TopSlab;
import cpu.blocks.Wire;
import org.bukkit.Material;

public class PatternMatcher extends Component {
    private final String name;

    public PatternMatcher(String name, int height, int pattern, Material material) {
        this.name = name;
        for (int i = 0; i < height; ++i) {
            add(new Location(0, 2*i-1, 0), new TopSlab());
            add(new Location(0, 2*i-1, 1), new TopSlab());
            add(new Location(0, 2*i, 1), new Wire());
            if (i < height - 1) {
                add(new Location(0, 2*i, 2), new TopSlab());
                add(new Location(0, 2*i+1, 2), new Wire());
            } else {
                add(new Location(1, 2*i-1, 1), new Solid(material));
            }
        }
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
