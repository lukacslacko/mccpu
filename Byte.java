import org.bukkit.Material;

public class Byte implements Renderer {
    private Location origin;
    private Vector width, length;
    private Material material, outMaterial, inMaterial;

    Byte(Location origin, Vector width, Vector length, Material material, Material outMaterial, Material inMaterial) {
        this.origin = origin;
        this.width = width;
        this.length = length;
        this.material = material;
        this.outMaterial = outMaterial;
        this.inMaterial = inMaterial;
    }

    private Location loc(int w, int l, int above) {
        return origin.shifted(width.times(w)).shifted(length.times(l)).above(above);
    }


    @Override
    public void render(RenderTarget target) {
        int outConnLength = 3;
        int inConnLength = 5;
        int lengthStart = -4;
        int lengthEnd = 8;
        for (int i = 0; i < 8; ++i) {
            new Bit(origin.above(2*i), width, length, material).render(target);
        }
        Ladder read = new Ladder(loc(5, -2, 0), length, 8, material);
        read.render(target);
        Ladder write = new Ladder(loc(2, -2, 0), length, 8, material);
        write.render(target);
        Ladder reset = new Ladder(loc(-2, 0, 0), width, 8, material);
        reset.render(target);
        Bundle outputConnector = new Bundle(loc(6, 5, 0), loc(6 + outConnLength, 5, 0), 8, "outconn", outMaterial);
        outputConnector.render(target);
        Bundle output = new Bundle(loc(7 + outConnLength, lengthEnd, 0), loc(7 + outConnLength, lengthStart, 0), 8, "out", outMaterial);
        output.render(target);
        Bundle inputConnector = new Bundle(loc(-1 - inConnLength, 5, 0), loc(-1, 5, 0), 8, "inconn", inMaterial);
        inputConnector.render(target);
        Bundle input = new Bundle(loc(-2 - inConnLength, lengthStart, 0), loc(-2 - inConnLength, lengthEnd, 0), 8, "in", inMaterial);
        input.render(target);
    }
}
