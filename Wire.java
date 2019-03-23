import static java.lang.Math.abs;
import static java.lang.Math.max;

class Wire implements Renderer {
    private Location from, to;
    private String id;

    Wire(Location from, Location to, String id) {
        this.from = from;
        this.to = to;
        this.id = id;
    }

    @Override
    public void render(RenderTarget target) {
        int dx = to.getX() - from.getX();
        int dz = to.getZ() - from.getZ();
        int len = max(abs(dx), abs(dz));
        int sx = Integer.compare(dx, 0);
        int sz = Integer.compare(dz, 0);
        for (int i = 0; i < len; ++i) {
            Location location = new Location(from.getX() + sx * i, from.getY(), from.getZ() + sz * i);
            target.setBlock(location);
            if (i % 16 == 0) {
                target.setRepeater(location.above(1), Utils.facingXZ(dx, dz));
            } else {
                target.setWire(location.above(1));
            }
        }
    }
}
