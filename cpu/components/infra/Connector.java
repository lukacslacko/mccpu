package cpu.components.infra;

import cpu.Component;
import cpu.Location;
import cpu.Vector;
import org.bukkit.Material;

public class Connector extends Component {
    private final String name;
    private final int bits;

    public Connector(String name, Material material, int bits,
                     int zigQuarters, int zagQuarters,
                     int zig, int zag, int zug,
                     int minimumOutputPower) {
        this.name = name;
        this.bits = bits;
        Bundle bundleZig = new Bundle(name + "/a", bits, zig, 0, 1, 1, material);
        bundleZig.rotateAround(bundleZig.getMarker("input"), zigQuarters);
        Bundle bundleZag = new Bundle(name + "/b", bits, zag,  bundleZig.finalPower(), 1, 1, material);
        bundleZag.shift(bundleZag.getMarker("input").to(bundleZig.getMarker("output")));
        bundleZag.rotateAround(bundleZag.getMarker("input"), zagQuarters);
        Bundle bundleZug = new Bundle(name + "/c", bits, zug,  bundleZag.finalPower(), minimumOutputPower, 1, material);
        bundleZug.shift(bundleZug.getMarker("input").to(bundleZag.getMarker("output")));
        bundleZug.rotateAround(bundleZug.getMarker("input"), zigQuarters);
        merge(bundleZig);
        merge(bundleZag);
        merge(bundleZug);
        setMarker("input", bundleZig.getMarker("input"));
        setMarker("output", bundleZug.getMarker("output"));
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String kind() {
        return "connector" + bits;
    }
}
