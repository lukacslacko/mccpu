class Bidir implements Renderer {
    private Bundle a, b;

    // Location of the bottom wire.
    Bidir(Location from, Location to, Vector shift, int numWires, String id) {
        a = new Bundle(from, to, numWires, id + "_a");
        b = new Bundle(to.shifted(shift), from.shifted(shift), numWires, id + "_b");
    }

    @Override
    public void render(RenderTarget target) {
        a.render(target);
        b.render(target);
    }
}
