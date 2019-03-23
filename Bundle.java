import java.util.ArrayList;
import java.util.List;

class Bundle implements Renderer {
    private List<Wire> wires;
    private String id;

    private String deferredMessages = "";

    // From and to are for the bottom wire.
    Bundle(Location from, Location to, int numWires, String id) {
        this.id = id;
        wires = new ArrayList<>();
        for (int i = 0; i < numWires; ++i) {
            wires.add(new Wire(from.above(2*i), to.above(2*i), id + "_" + i));
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

    void intersectWith(Bundle below) {
        if (wires.size() != below.wires.size()) {
            deferredMessages += " this " + id + " has " + wires.size() + " wires and " + below.id + " has " + below.wires.size();
        }
        for (int i = 0; i < wires.size(); ++i) {
            wires.get(i).intersectWith(below.wires.get(i));
        }
    }
}
