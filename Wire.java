import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.max;

class Wire extends Renderer {
    static final Material BLUE = Material.BLUE_WOOL;
    static final Material YELLOW = Material.YELLOW_WOOL;
    static final Material PINK = Material.PINK_WOOL;
    static final Material LIGHT_BLUE = Material.LIGHT_BLUE_WOOL;
    static final Material LIGHT_GREEN = Material.LIME_WOOL;
    static final Material GREEN = Material.GREEN_WOOL;
    static final Material RED = Material.RED_WOOL;
    static final Material ORANGE = Material.ORANGE_WOOL;
    static final Material PURPLE = Material.PURPLE_WOOL;
    static final Material BROWN = Material.BROWN_WOOL;

    private Location from, to;
    private String id;
    private Material material;

    private String deferredMessage = "";

    // Locations where repeaters need to be added due to intersecting wires.
    private List<Location> repeaters = new ArrayList<>();

    // Locations which need to be left out due to an intersecting wire.
    private List<Location> holes = new ArrayList<>();

    // Locations where slabs need to be added due to an intersection.
    private List<Location> slabs = new ArrayList<>();

    // Locations which need to be raised due to an intersection.
    private List<Location> raised = new ArrayList<>();

    // Wire connecting from and to, inclusive both.
    Wire(Location from, Location to, String id, Material material) {
        super(from, new Coordinates(from.to(to), from.to(to).rotate()));
        this.from = from;
        this.to = to;
        this.id = id;
        this.material = material;
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
            Location put = location;
            if (raised.contains(location)) {
                put = location.above(1);
            }
            // When crossing over two wires with one block gap, intermediate slabs block transferring the signal in
            // the crossed wire.
            if (slabs.contains(location) && !raised.contains(location)) {
                target.setTopSlab(put);
            } else {
                target.setBlock(put, material);
            }
            if (repeaters.contains(location)) {
                strength = 0;
            }
            if (strength == 0) {
                target.setRepeater(put.above(1), Utils.facingXZ(dx, dz));
                strength = 15;
            } else {
                target.setWire(put.above(1));
                --strength;
            }
        }
    }

    int getY() {
        return from.getY();
    }

    // The other wire must pass orthogonal to this one, at the same y coordinate. This function causes holes, repeaters,
    // slabs and raised blocks adde as necessary to both wires.
    void intersectWith(Wire other) {
        if (other.getY() != getY()) {
            deferredMessage += " intersection: other (" + other.getId() + ") y = " + other.getY() + " this y = " + getY();
        }
        if (from.getX() == to.getX()) {
            Location intersection = new Location(from.getX(), getY(), other.from.getZ());
            raised.add(intersection.shifted(new Vector(0, 0, -1)));
            raised.add(intersection.shifted(new Vector(0, 0, 0)));
            raised.add(intersection.shifted(new Vector(0, 0, 1)));
            slabs.add(intersection.shifted(new Vector(0, 0, -2)));
            slabs.add(intersection.shifted(new Vector(0, 0, 2)));
            repeaters.add(intersection);
            other.holes.add(intersection);
            other.repeaters.add(intersection.shifted(new Vector(Integer.compare(other.to.getX(), intersection.getX()), 0, 0)));
        } else {
            Location intersection = new Location(other.from.getX(), getY(), from.getZ());
            raised.add(intersection.shifted(new Vector(-1, 0, 0)));
            raised.add(intersection.shifted(new Vector(0, 0, 0)));
            raised.add(intersection.shifted(new Vector(1, 0, 0)));
            slabs.add(intersection.shifted(new Vector(-2, 0, 0)));
            slabs.add(intersection.shifted(new Vector(2, 0, 0)));
            repeaters.add(intersection);
            other.holes.add(intersection);
            other.repeaters.add(intersection.shifted(new Vector(0, 0, Integer.compare(other.to.getZ(), intersection.getZ()))));
        }
    }

    String getId() {
        return id;
    }
}
