import java.util.ArrayList;
import java.util.List;

class Bundle implements Renderer {
    private List<Wire> wires;

    // From and to are for the bottom wire.
    Bundle(Location from, Location to, int numWires, String id) {
        wires = new ArrayList<>();
        for (int i = 0; i < numWires; ++i) {
            wires.add(new Wire(from.above(2*i), to.above(2*i), id + "_" + i));
        }
    }

    @Override
    public void render(RenderTarget target) {
        for (Wire wire : wires) {
            wire.render(target);
        }
    }
}
