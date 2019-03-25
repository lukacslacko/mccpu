import org.bukkit.Material;

public class Byte implements Renderer {
    private Location origin;
    private Vector width, length;
    private Material material, outMaterial, inMaterial, writeMaterial, readMaterial, resetMaterial ,readyMaterial, readSelectorMaterial, writeSelectorMaterial;
    private int numAddressWires, address;
    private static final int LENGTH_START = -5;
    private static final int LENGTH_END = 8;

    Byte(
            Location origin,
            Vector width,
            Vector length,
            int numAddressWires,
            int address,
            Material material,
            Material outMaterial,
            Material inMaterial,
            Material writeMaterial,
            Material readMaterial,
            Material resetMaterial,
            Material readyMaterial,
            Material readSelectorMaterial,
            Material writeSelectorMaterial) {
        this.origin = origin;
        this.width = width;
        this.length = length;
        this.numAddressWires = numAddressWires;
        this.address = address;
        this.material = material;
        this.outMaterial = outMaterial;
        this.inMaterial = inMaterial;
        this.writeMaterial = writeMaterial;
        this.readMaterial = readMaterial;
        this.resetMaterial = resetMaterial;
        this.readyMaterial = readyMaterial;
        this.readSelectorMaterial = readSelectorMaterial;
        this.writeSelectorMaterial = writeSelectorMaterial;
    }

    static int length() {
        return LENGTH_END - LENGTH_START + 1;
    }

    private Location loc(int w, int l, int above) {
        return origin.shifted(width.times(w)).shifted(length.times(l)).above(above);
    }


    @Override
    public void render(RenderTarget target) {
        int outConnLength = 4;
        int inConnLength = 3;
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
        Bundle output = new Bundle(loc(7 + outConnLength, LENGTH_END, 0), loc(7 + outConnLength, LENGTH_START, 0), 8, "out", outMaterial);
        output.render(target);
        Bundle inputConnector = new Bundle(loc(-1 - inConnLength, 5, 0), loc(-1, 5, 0), 8, "inconn", inMaterial);
        inputConnector.render(target);
        Bundle input = new Bundle(loc(-2 - inConnLength, LENGTH_START, 0), loc(-2 - inConnLength, LENGTH_END, 0), 8, "in", inMaterial);
        input.render(target);

        target.setRepeaterBlock(loc(-2, 1, 0), resetMaterial, Utils.facing(length.times(-1)));
        target.setWireBlock(loc(-2, 2, 0), resetMaterial);
        target.setWireBlock(loc(-2, 3, -1), resetMaterial);
        Bundle resetBundle = new Bundle(loc(-1, LENGTH_START, -2), loc(-1, LENGTH_END, -2), 1, "rst", resetMaterial);
        resetBundle.render(target);

        target.setRepeaterBlock(loc(1, -2, 0), writeMaterial, Utils.facing(width));
        target.setWireBlock(loc(-1, -2, 0), writeMaterial);
        target.setWireBlock(loc(-2, -2, -1), writeMaterial);
        target.setWireBlock(loc(-3, -2, -2), writeMaterial);
        target.setWireBlock(loc(-4, -2, -2), writeMaterial);
        target.setWireBlock(loc(-5, -2, -2), writeMaterial);
        target.setBlock(loc(0, -3, 1), writeMaterial);
        target.setStickyPiston(loc(0, -4, 1), Utils.facing(length.times(-1)));
        target.setWireBlock(loc(1, -4, 0), writeMaterial);
        target.setWireBlock(loc(2, -4, -1), writeMaterial);
        Bundle writeBundle = new Bundle(loc(3, LENGTH_START, -2), loc(3, LENGTH_END, -2), 1, "w", writeMaterial);
        writeBundle.render(target);

        target.setRepeaterBlock(loc(6, -2, 0), readMaterial, Utils.facing(width.times(-1)));
        target.setWireBlock(loc(8, -2, 0), readMaterial);
        target.setWireBlock(loc(9, -2, -1), readMaterial);
        target.setWireBlock(loc(10, -2, -2), readMaterial);
        target.setWireBlock(loc(11, -2, -2), readMaterial);
        target.setBlock(loc(7, -1, 1), readMaterial);
        target.setStickyPiston(loc(7, 0, 1), Utils.facing(length));
        target.setWireBlock(loc(7, 1, 0), readMaterial);
        target.setWireBlock(loc(7, 2, -1), readMaterial);
        Bundle readBundle = new Bundle(loc(8, LENGTH_START, -2), loc(8, LENGTH_END, -2), 1, "r", readMaterial);
        readBundle.render(target);

        Bundle readyBundle = new Bundle(loc(5, LENGTH_END, -2), loc(5, LENGTH_START, -2), 1, "rdy", readyMaterial);
        readyBundle.render(target);

        Selector readSelector = new Selector(
                loc(-9, LENGTH_START + 3, 0), length, width, numAddressWires, address, readSelectorMaterial);
        readSelector.render(target);
        Bundle readAddress = new Bundle(loc(-9, LENGTH_START, 0), loc(-9, LENGTH_END, 0), numAddressWires, "raddr", readSelectorMaterial);
        readAddress.render(target);

        Selector writeSelector = new Selector(
                loc(15, LENGTH_START + 3, 0), length, width.times(-1), numAddressWires, address, writeSelectorMaterial);
        writeSelector.render(target);
        Bundle writeAddress = new Bundle(loc(15, LENGTH_START, 0), loc(15, LENGTH_END, 0), numAddressWires, "waddr", writeSelectorMaterial);
        writeAddress.render(target);
    }
}
