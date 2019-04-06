import org.bukkit.Material;

class Comparator extends Component {
    private Xor inXor;
    private Transmitter8 eqTrans, gtTrans, ltTrans;

    Comparator(Location origin, Coordinates coordinates, Material material) {
        super(origin, coordinates);
        Xor[] xors = new Xor[8];
        Not[] nots = new Not[8];
        for (int i = 0; i < 8; ++i) {
            xors[i] = new Xor(origin.above(2 * i), coordinates, material);
            add(xors[i]);
            nots[i] = new Not(origin.above(2*i), coordinates, material);
            nots[i].shift(nots[i].input().to(xors[i].output()));
            add(nots[i]);
        }

        eqTrans = new Transmitter8(origin, coordinates, material);
        eqTrans.shift(eqTrans.inputGate().to(nots[0].output()));
        add(eqTrans);

        gtTrans = new Transmitter8(origin, coordinates.rotate(1), material);
        gtTrans.shift(gtTrans.inputGate().to(nots[0].input()));
        gtTrans.shift(gtTrans.pos(0, 3, 0));
        add(gtTrans);

        add(new Bundle(gtTrans.loc(2, -3, 0), gtTrans.loc(2, -1, 0), 8, "", material));
        add(new Bundle(xors[0].loc(-1, 1, 0), xors[0].loc(-2, 1, 0), 8, "", Wire.BLUE));
        add(new Bundle(xors[0].loc(-2, 2, 0), xors[0].loc(-2, 6, 0), 8, "", Wire.BLUE));

        ltTrans = new Transmitter8(origin, coordinates.mirror().rotate(-1), material);
        ltTrans.shift(ltTrans.inputGate().to(nots[0].input()));
        ltTrans.shift(ltTrans.pos(0, 3, 0));
        add(ltTrans);

        add(new Bundle(ltTrans.loc(2, -3, 0), ltTrans.loc(2, -1, 0), 8, "", material));
        add(new Bundle(xors[0].loc(5, 1, 0), xors[0].loc(6, 1, 0), 8, "", Wire.BLUE));
        add(new Bundle(xors[0].loc(6, 2, 0), xors[0].loc(6, 6, 0), 8, "", Wire.BLUE));

        inXor = xors[0];
    }

    @Override
    public void render(RenderTarget target) {
        super.render(target);
    }

    Location inputA() { return inXor.inputA(); }
    Location inputB() { return inXor.inputB(); }
    Location outGt() { return gtTrans.output(); }
    Location outEq() { return eqTrans.output(); }
    Location outLt() { return ltTrans.output(); }
}
