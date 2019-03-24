import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

class Bundle implements Renderer {
    private List<Wire> wires;
    private String id;

    private String deferredMessages = "";

    // From and to are for the bottom wire.
    Bundle(Location from, Location to, int numWires, String id, Material material) {
        this.id = id;
        wires = new ArrayList<>();
        for (int i = 0; i < numWires; ++i) {
            wires.add(new Wire(from.above(2*i), to.above(2*i), id + "_" + i, material));
        }
    }

    @Override
    public void render(RenderTarget target) {
        if (!deferredMessages.isEmpty()) {
            target.message(id + ":" + deferredMessages);
        }
        for (Wire wire : wires) {
            wire.render(target);
        }
    }

    void intersectWith(Bundle other) {
        for (Wire wire : wires) {
            for (Wire other_wire : other.wires) {
                if (wire.getY() == other_wire.getY()) {
                    wire.intersectWith(other_wire);
                }
            }
        }
    }
}
