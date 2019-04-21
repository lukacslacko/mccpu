package cpu.components.arithmetic;

import cpu.Component;
import cpu.Vector;
import org.bukkit.Material;

public class Dec extends Component {
    private final String name;
    private final int bits;

    public Dec(String name, int bits, Material material) {
        this.name = name;
        this.bits = bits;
        for (int i = 0; i < bits; ++i) {
            Not not = new Not(name + "/not in " + i, material);
            not.shift(new Vector(0, 2*i, 0));
            merge(not);
            if (i == 0) {
                setMarker("input", not.getMarker("input"));
                setMarker("not in output", not.getMarker("output"));
            }
        }
        Inc inc = new Inc(name + "/inc", bits, material);
        inc.shift(inc.getMarker("input").to(getMarker("not in output")));
        merge(inc);
        for (int i = 0; i < bits; ++i) {
            Not not = new Not(name + "/not out " + i, material);
            not.shift(not.getMarker("input").to(inc.getMarker("output")));
            not.shift(new Vector(0, 2*i, 0));
            merge(not);
            if (i == 0) {
                setMarker("output", not.getMarker("output"));
            }
        }
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String kind() {
        return "Dec" + bits;
    }
}
