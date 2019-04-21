package cpu.components.display;

import cpu.Component;
import cpu.Vector;
import org.bukkit.Material;

public class HalfBit8 extends Component {
    private final String name;

    public HalfBit8(String name, Material material) {
        this.name = name;
        for (int i = 0; i < 8; ++i) {
            HalfBit halfBit = new HalfBit(name + "/" + i, material);
            halfBit.shift(new Vector(0, 2*i, 0));
            merge(halfBit);
            if (i == 0) {
                inheritMarkers(halfBit);
            }
        }
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String kind() {
        return "halfbyte";
    }
}
