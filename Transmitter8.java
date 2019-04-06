import org.bukkit.Material;

public class Transmitter8 extends Component {
    private Transmitter[] transmitters;

    Transmitter8(Location origin, Coordinates coordinates, Material material) {
        super(origin, coordinates);
        transmitters = new Transmitter[4];
        for (int i = 0; i < 4; ++i) {
            transmitters[i] = new Transmitter(origin.above(4*i), coordinates, material);
            add(transmitters[i]);
        }
    }

    @Override
    public void render(RenderTarget target) {
        super.render(target);
        target.setWire(output().above(1));
        target.setWire(loc(2, 2, 16));
        target.setWire(loc(2, 3, 16));
        target.setWire(loc(2, 4, 16));
    }

    Location inputGate() { return transmitters[0].inputGate(); }
    Location inputData() { return transmitters[0].inputData(); }
    Location output() { return transmitters[3].output(); }
}
