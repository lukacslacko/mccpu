import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.max;

class Wire implements Renderer {
    private Location from, to;
    private String id;

    private String deferredMessage = "";

    // Locations where repeaters need to be added due to intersecting wires passing just below.
    private List<Location> repeaters = new ArrayList<>();

    Wire(Location from, Location to, String id) {
        this.from = from;
        this.to = to;
        this.id = id;
    }

    @Override
    public void render(RenderTarget target) {
        if (!deferredMessage.isEmpty()) {
            target.message(id + deferredMessage);
        }
        int dx = to.getX() - from.getX();
        int dz = to.getZ() - from.getZ();
        if (dx * dz != 0) {
            target.message(id + ": dx = " + dx + " dz = " + dz);
        }
        if (to.getY() != from.getY()) {
            target.message(id + ": " + from + " -> " + to);
        }
        int len = max(abs(dx), abs(dz));
        int sx = Integer.compare(dx, 0);
        int sz = Integer.compare(dz, 0);
        int strength = 0;
        for (int i = 0; i < len; ++i) {
            Location location = new Location(from.getX() + sx * i, from.getY(), from.getZ() + sz * i);
            target.setBlock(location);
            if (repeaters.contains(location)) {
                strength = 0;
            }
            if (strength == 0) {
                target.setRepeater(location.above(1), Utils.facingXZ(dx, dz));
                strength = 15;
            } else {
                target.setWire(location.above(1));
                --strength;
            }
        }
    }

    private int getY() {
        return from.getY();
    }

    // The other wire must pass orthogonal to this one, just below it. This function returns the two pieces of the
    // other wire, this one splits that into.
    List<Wire> intersectWithWireBelow(Wire below) {
        List<Wire> result = new ArrayList<>();
        if (below.getY() + 1 != getY()) {
            deferredMessage += " intersection: below (" + below.getId() + ") y = " + below.getY() + " this y = " + getY();
        }
        if (from.getX() == to.getX()) {
            Location intersection = new Location(from.getX(), getY(), below.from.getZ());
            repeaters.add(intersection);
            result.add(
                    new Wire(
                            below.from,
                            intersection.shifted(
                                    new Vector(Integer.compare(below.from.getX(), intersection.getX()), -1, 0)),
                            below.id + "/" + id));
            result.add(
                    new Wire(
                            intersection.shifted(
                                    new Vector(Integer.compare(below.to.getX(), intersection.getX()), -1, 0)),
                            below.to,
                            below.id + "\\" + id));
        } else {
            Location intersection = new Location(below.from.getX(), getY(), from.getZ());
            repeaters.add(intersection);
            result.add(
                    new Wire(
                            below.from,
                            intersection.shifted(
                                    new Vector(0, -1, Integer.compare(below.from.getZ(), intersection.getZ()))),
                            below.id + "/" + id));
            result.add(
                    new Wire(
                            intersection.shifted(
                                    new Vector(0, -1, Integer.compare(below.to.getZ(), intersection.getZ()))),
                            below.to,
                            below.id + "\\" + id));
        }
        return result;
    }

    String getId() {
        return id;
    }
}
