package cpu.components.memory.bank;

import cpu.Component;
import cpu.Location;
import cpu.Vector;
import org.bukkit.Material;

public class Blocker8 extends Component {
    private final String name;

    public Blocker8(String name, Material material) {
        this.name = name;
        for (int i = 0; i < 8; ++i) {
            Blocker blocker = new Blocker(name + i, material);
            blocker.shift(new Vector(0, 2*i, 0));
            if (i == 0) inheritMarkers(blocker);
            merge(blocker);
        }
        remove(new Location(5, 0, 0));
        remove(new Location(5, 15, 0));
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String kind() {
        return "blocker8";
    }
}
