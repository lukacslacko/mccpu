import org.bukkit.Material;

public class Bit implements Renderer {
    private Location origin;
    private Vector width, length;
    private Material material;

    Bit(Location origin, Vector width, Vector length, Material material) {
        this.origin = origin;
        this.width = width;
        this.length = length;
        this.material = material;
    }

    private Location loc(int w, int l, int above) {
        return origin.shifted(width.times(w)).shifted(length.times(l)).above(above);
    }

    @Override
    public void render(RenderTarget target) {
        target.setBlock(loc(0, 0, 0), material);
        target.setWire(loc(0, 0, 1));
        target.setBlock(loc(1, 0, 0), material);
        target.setRepeater(loc(1, 0, 1), Utils.facing(width));
        target.setBlock(loc(2, 0, 0), material);
        // TODO finish
    }
}
