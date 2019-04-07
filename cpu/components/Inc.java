package cpu.components;

import cpu.Component;
import cpu.Location;
import cpu.Vector;
import cpu.blocks.Solid;
import cpu.blocks.TopSlab;
import org.bukkit.Material;

public class Inc extends Component {
    private final String name;
    private final int bits;

    public Inc(String name, int bits, Material material) {
        this.name = name;
        this.bits = bits;
        for (int i = 0; i < bits; ++i) {
            IncPiece incPiece = new IncPiece("bit" + i, material);
            incPiece.shift(new Vector(0, 2*i, 0));
            merge(incPiece);
        }
        add(new Location(3, -1, 1), new TopSlab());
        remove(new Location(3, 2*bits-1, 1));
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String kind() {
        return "Inc" + bits;
    }
}
