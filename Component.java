import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

class Component extends Renderer {
    private List<Renderer> renderers = new ArrayList<>();

    Component(Location origin, Coordinates coordinates) {
        super(origin, coordinates);
    }

    @Override
    void shift(Vector v) {
        super.shift(v);
        for (Renderer renderer : renderers) {
            renderer.shift(v);
        }
    }

    @Override
    void rotate(int quarters) {
        super.rotate(quarters);
        for (Renderer renderer : renderers) {
            renderer.rotate(quarters);
        }
    }

    void add(Renderer renderer) {
        renderers.add(renderer);
    }

    @Override
    public void render(RenderTarget target) {
        for (Renderer renderer : renderers) {
            renderer.render(target);
        }
    }
}
