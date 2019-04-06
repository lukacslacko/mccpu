import org.bukkit.Material;

public class Bit extends Renderer {
    private Vector width, length;
    private Material material;

    Bit(Location origin, Coordinates coordinates, Material material) {
        super(origin, coordinates);
        this.width = coordinates.at(1, 0);
        this.length = coordinates.at(0, 1);
        this.material = material;
    }

    @Override
    public void render(RenderTarget target) {
        target.setWireBlock(loc(0, 0, 0), material);
        target.setRepeaterBlock(loc(1, 0, 0), material, Utils.facing(width));
        target.setWireBlock(loc(2, 0, 0), material);
        target.setRepeaterBlock(loc(0, 1, 0), material, Utils.facing(length));
        target.setWireBlock(loc(0, 2, 0), material);
        target.setWireBlock(loc(0, 3, 0), material);
        target.setStickyPiston(loc(0, 4, 1), Utils.facing(length.times(-1)));
        target.setBlock(loc(0, 5, 1), material);
        target.setRepeaterBlock(loc(1, 5, 0), material, Utils.facing(width));
        target.setRepeaterBlock(loc(2, 5, 0), material, Utils.facing(width));
        target.setRepeaterBlock(loc(2, 4, 0), material, Utils.facing(length));
        target.setWireBlock(loc(2, 1, 0), material);
        target.setBlock(loc(2, 2, 1), material);
        target.setWallTorch(loc(2, 3, 1), Utils.facing(length.times(-1)));
        target.setWireBlock(loc(3, 5, 0), material);
        target.setWireBlock(loc(4, 5, 0), material);
        target.setWireBlock(loc(5, 0, 0), material);
        target.setWireBlock(loc(5, 1, 0), material);
        target.setWireBlock(loc(5, 2, 0), material);
        target.setStickyPiston(loc(5, 3, 1), Utils.facing(length.times(-1)));
        target.setBlock(loc(5, 4, 1), material);
    }
}
