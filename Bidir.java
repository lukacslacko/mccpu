import org.bukkit.Material;

class Bidir implements Renderer {
    private Bundle a, b;

    // Location of the bottom wire.
    Bidir(Location from, Location to, Vector shift, int numWires, String id, Material material) {
        a = new Bundle(from, to, numWires, id + "_a", material);
        b = new Bundle(to.shifted(shift), from.shifted(shift), numWires, id + "_b", material);
    }

    void intersectWith(Bundle bundle) {
        a.intersectWith(bundle);
        b.intersectWith(bundle);
    }

    void intersectWith(Bidir bidir) {
        bidir.intersectWith(a);
        bidir.intersectWith(b);
    }

    @Override
    public void render(RenderTarget target) {
        a.render(target);
        b.render(target);
    }
}
