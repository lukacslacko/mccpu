import org.bukkit.Material;

public class Ladder implements Renderer {
    private Location bottom;

    private Vector direction;

    private int numWires;

    private Material material;

    Ladder(Location bottom, Vector direction, int numWires, Material material) {
        this.bottom = bottom;
        this.direction = direction;
        this.numWires = numWires;
        this.material = material;
    }

    @Override
    public void render(RenderTarget target) {
        for (int i = 0; i < numWires; ++i) {
            target.setTopSlab(bottom.above(2*i));
            target.setWire(bottom.above(2*i+1));
            target.setTopSlab(bottom.shifted(direction.times(-1)).above(2*i+1));
            target.setWire(bottom.shifted(direction.times(-1)).above(2));
            target.setBlock(bottom.above(2*i).shifted(direction), material);
            target.setRepeater(bottom.above(2*i+1).shifted(direction), Utils.facing(direction));
        }
    }
}
