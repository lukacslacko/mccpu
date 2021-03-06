public class CPU extends Renderer {
    private Location start;

    public CPU(Location start) {
        super(start, Coordinates.NW());
    }

    @Override
    public void render(RenderTarget target) {
        /*
        for (int i = 0; i < 16; ++i) {
            Vector wire = new Vector(1, 0, 0);
            Bundle bundle = new Bundle(start.shifted(wire.times(10*i)), start.shifted(wire.times(10*(i+1)-1)), 4, "bundle", Wire.BLUE);
            Selector selector = new Selector(start.shifted(wire.times(10*i+2)), wire, new Vector(0, 0, 1), 4, i, Wire.BLUE);
            bundle.render(target);
            selector.render(target);
        }
        Bundle b1 = new Bundle(start.shifted(new Vector(0, 0, 5)), start.shifted(new Vector(0, 0, 25)), 4, "a", Wire.LIGHT_GREEN);
        Bidir b2 = new Bidir(start.shifted(new Vector(-10, 0, 15)), start.shifted(new Vector(10, 0, 15)), new Vector(0, 0, 2), 4, "a", Wire.PINK);
        b2.intersectWith(b1);
        b1.render(target);
        b2.render(target);
        */
        Vector width = new Vector(1, 0, 0);
        Vector length = new Vector(0, 0, -1);
        for (int i = 0; i < 16; ++i) {
            new Byte(
                    loc(0, i * Byte.length(), 0),
                    coordinates,
                    4,
                    i,
                    Wire.LIGHT_BLUE,
                    Wire.LIGHT_GREEN,
                    Wire.YELLOW,
                    Wire.RED,
                    Wire.GREEN,
                    Wire.PINK,
                    Wire.ORANGE,
                    Wire.PURPLE,
                    Wire.BROWN).render(target);
        }
    }
}
