package cpu.components.infra;

import cpu.Component;
import cpu.Location;
import cpu.blocks.Repeater;
import cpu.blocks.Solid;
import cpu.blocks.Wire;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;

public class Bundle extends Component {
    private final String name;
    private final int bits;
    private final int finalPower;

    // Del fele (pozitiv z irany) all.
    public Bundle(String name, int bits, int length, int initialPower, int minimumOutputPower, int minimumIntermediatePower, Material material) {
        this.name = name;
        this.bits = bits;
        int power = initialPower;
        for (int i = 0; i < bits; ++i) {
            power = initialPower;
            for (int z = 0; z < length; ++z) {
                boolean needsRepeater = false;
                if (power < minimumIntermediatePower) needsRepeater = true;
                if (z == length - 2 && power <= minimumOutputPower) needsRepeater = true;
                if (needsRepeater) power = 16;
                power -= 1;
                add(new Location(0, 2*i, z), new Solid(material));
                add(new Location(0, 2*i+1, z), needsRepeater ? new Repeater(BlockFace.NORTH) : new Wire());
            }
        }
        this.finalPower = power;
        setMarker("input", new Location(0, 0, -1));
        setMarker("output", new Location(0, 0, length-1));
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String kind() {
        return "bundle" + bits;
    }

    public int finalPower() { return finalPower; }
}
