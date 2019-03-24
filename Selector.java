import org.bukkit.Material;

public class Selector implements Renderer {
    // The bottom wire position of the selector.
    private Location bottom;

    // The direction of the wire.
    private Vector wire;

    // The direction to the side, orthogonal to the wire, where the selector is.
    private Vector side;

    private int numWires;

    // The binary pattern to select.
    private int pattern;

    private Material material;

    Selector(Location bottom, Vector wireDirection, Vector sideDirection, int numWires, int pattern, Material material) {
        this.bottom = bottom;
        wire = wireDirection;
        side = sideDirection;
        this.numWires = numWires;
        this.pattern = pattern;
        this.material = material;
    }

    @Override
    public void render(RenderTarget target) {
        Location location = new Location(bottom).above(-1);
        for (int i = 0; i < numWires; ++i) {
            target.setBlock(location.shifted(side), material);

            if ((pattern & (1 << i)) == 0) {
                target.setWallTorch(location.shifted(side).above(1), Utils.facing(side.times(-1)));
            } else {
                target.setRepeater(location.shifted(side).above(1), Utils.facing(side));
            }

            target.setBlock(location.shifted(side.times(2)), material);
            target.setWire(location.shifted(side.times(2)).above(1));
            target.setBlock(location.shifted(side.times(2)).shifted(wire).above(1), material);
            target.setWire(location.shifted(side.times(2)).shifted(wire).above(2));
            location = location.shifted(wire.times(2)).above(2);
        }
        target.setWallTorch(bottom.above(1).shifted(wire.times(2)).shifted(side.times(3)), Utils.facing(side.times(-1)));
    }
}
