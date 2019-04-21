package cpu.components.memory;

import cpu.Component;
import cpu.Vector;
import org.bukkit.Material;

public class Bit8 extends Component {
    private final String name;

    public Bit8(String name, Material material) {
        this.name = name;
        for (int i = 0; i < 8; ++i) {
            Bit bit = new Bit(name + "/" + i, material);
            bit.shift(new Vector(0, 2*i, 0));
            merge(bit);
            if (i == 0) inheritMarkers(bit);
        }
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String kind() {
        return "byte";
    }
}
