import org.bukkit.Material;

class Comparator extends Component {
    private Xor inXor;
    private Transmitter8 eqTrans, gtTrans, ltTrans;
    private Material material;

    Comparator(Location origin, Coordinates coordinates, Material material) {
        super(origin, coordinates);
        this.material = material;
        Xor[] xors = new Xor[8];
        Passthrough4[] gtPass = new Passthrough4[8];
        Passthrough4[] ltPass = new Passthrough4[8];
        for (int i = 0; i < 8; ++i) {
            xors[i] = new Xor(origin.above(2 * i), coordinates, material);
            add(xors[i]);
            gtPass[i] = new Passthrough4(origin.above(2*i), coordinates.rotate(1), material);
            gtPass[i].shift(gtPass[i].input().to(xors[i].output().shifted(xors[i].pos(0, -1, 0))));
            add(gtPass[i]);
            ltPass[i] = new Passthrough4(origin.above(2*i), coordinates.rotate(-1), material);
            ltPass[i].shift(ltPass[i].input().to(xors[i].output().shifted(xors[i].pos(0, -1, 0))));
            add(ltPass[i]);
        }

        eqTrans = new Transmitter8(origin, coordinates, material);
        eqTrans.shift(eqTrans.inputGate().to(xors[0].output()));
        add(eqTrans);

        gtTrans = new Transmitter8(origin, coordinates.rotate(1), material);
        gtTrans.shift(gtTrans.inputGate().to(gtPass[0].output()));
        add(gtTrans);

        add(new Bundle(xors[0].loc(-1, 1, 0), xors[0].loc(-2, 1, 0), 8, "", Wire.BLUE));
        add(new Bundle(xors[0].loc(-2, 2, 0), xors[0].loc(-2, 8, 0), 8, "", Wire.BLUE));

        ltTrans = new Transmitter8(origin, coordinates.mirror().rotate(-1), material);
        ltTrans.shift(ltTrans.inputGate().to(ltPass[0].output()));
        add(ltTrans);

        add(new Bundle(xors[0].loc(5, 1, 0), xors[0].loc(6, 1, 0), 8, "", Wire.BLUE));
        add(new Bundle(xors[0].loc(6, 2, 0), xors[0].loc(6, 8, 0), 8, "", Wire.BLUE));
        inXor = xors[0];
    }

    @Override
    public void render(RenderTarget target) {
        super.render(target);
        target.setWireBlock(eqTrans.loc(0, 8, 0), material);
        target.setWireBlock(eqTrans.loc(-1, 8, 0), material);
        target.setBlock(eqTrans.loc(-2, 8, 0), material);
        target.setRedstoneTorch(eqTrans.loc(-2, 8, 1));
    }

    Location inputA() { return inXor.inputA(); }
    Location inputB() { return inXor.inputB(); }
    Location outGt() { return gtTrans.output(); }
    Location outEq() { return eqTrans.output(); }
    Location outLt() { return ltTrans.output(); }
}
