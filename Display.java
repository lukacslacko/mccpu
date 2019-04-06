import org.bukkit.Material;

public class Display extends Component {
    private int w, h;

    Display(Location origin, Coordinates coordinates, Material material, int w, int h) {
        super(origin, coordinates);
        for (int i = 0; i < w/2; ++i) {
            for (int j = 0; j < h; ++j) {
                add(new Pixel(loc(0, i*4, j*2), coordinates, material));
            }
        }
        this.w = w;
        this.h = h;
    }

    @Override
    public void render(RenderTarget target) {
        super.render(target);
        for (int i = 0; i < 2*w; ++i) {
            target.setAir(loc(13, i, 0));
            target.setLamp(loc(13, i, 2*h));
        }
    }
}
