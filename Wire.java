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

    // Locations which need to be left out due to an intersecting wire passing just above.
    private List<Location> holes = new ArrayList<>();

    // Wire connecting from and to, inclusive both.
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
        for (int i = 0; i <= len; ++i) {
            Location location = new Location(from.getX() + sx * i, from.getY(), from.getZ() + sz * i);
            if (holes.contains(location)) {
                strength = 0;
                continue;
            }
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

    // The other wire must pass orthogonal to this one, just below it. This function causes holes and repeaters added
    // as necessary to both this wire and the one below.
    void intersectWithWireBelow(Wire below) {
        if (below.getY() + 1 != getY()) {
            deferredMessage += " intersection: below (" + below.getId() + ") y = " + below.getY() + " this y = " + getY();
        }
        if (from.getX() == to.getX()) {
            Location intersection = new Location(from.getX(), getY(), below.from.getZ());
            repeaters.add(intersection);
            below.holes.add(intersection.above(-1));
            below.repeaters.add(intersection.shifted(new Vector(Integer.compare(below.to.getX(), intersection.getX()), -1, 0)));
        } else {
            Location intersection = new Location(below.from.getX(), getY(), from.getZ());
            repeaters.add(intersection);
            below.holes.add(intersection.above(-1));
            below.repeaters.add(intersection.shifted(new Vector(0, -1, Integer.compare(below.to.getZ(), intersection.getZ()))));
        }
    }

    String getId() {
        return id;
    }
}
