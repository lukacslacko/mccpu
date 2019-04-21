package cpu.components.display;

import cpu.Component;
import cpu.Vector;
import org.bukkit.Material;

public class Pixel8 extends Component {
    private final String name;

    public Pixel8(String name, Material material) {
        this.name = name;
        for (int i = 0; i < 8; ++i) {
            Pixel pixel = new Pixel(name + "/" + i, material);
            pixel.shift(new Vector(0, 2*i, 0));
            merge(pixel);
            if (i == 0) {
                inheritMarkers(pixel);
            }
        }
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String kind() {
        return "pixel8";
    }
}
