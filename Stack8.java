import org.bukkit.Material;

class Stack8 extends Component {
    @FunctionalInterface
    interface Constructor {
        public Renderer create(Location origin, Coordinates coordinates, Material material);
    }

    Stack8(Location origin, Coordinates coordinates, Material material, Constructor constructor) {
        super(origin, coordinates);
        for (int i = 0; i < 8; ++i) {
            add(constructor.create(origin.above(2*i), coordinates, material));
        }
    }
}
