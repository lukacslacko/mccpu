package cpu.components.memory.bank;

import cpu.Component;
import cpu.Location;
import cpu.Vector;
import cpu.blocks.Solid;
import cpu.components.infra.Connector;
import cpu.components.memory.ByteRow;
import org.bukkit.Material;

public class Adapter extends Component {
    private final String name;

    public Adapter(String name, int bytes, Material material, Material readMaterial, Material writeMaterial) {
        this.name = name;

        ByteRow row = new ByteRow(bytes, name + "/bytes", material, readMaterial, writeMaterial);
        merge(row);

        Blocker8 resetInput = new Blocker8(name + "/blocker", writeMaterial);
        resetInput.flipX();
        resetInput.rotateAround(Location.origin(), 3);
        resetInput.shift(resetInput.getMarker("output").to(row.getMarker("input")));
        merge(resetInput);

        Connector resetWire = new Connector(name + "/reset_wire", material, 1, 0, 1, 3, 4, 0, 1);
        resetWire.shift(resetWire.getMarker("input").to(resetInput.getMarker("block")));
        merge(resetWire);

        Connector resetInputWire = new Connector(name + "/reset_input_wire", material, 1, 0, 0, 5, 0, 0, 1);
        resetInputWire.shift(resetInputWire.getMarker("output").to(resetWire.getMarker("input")).plus(new Vector(0, 0, -1)));
        merge(resetInputWire);

        setMarker("reset", resetInputWire.getMarker("input"));
        setMarker("input", resetInput.getMarker("input"));
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String kind() {
        return "memory_row_adapter";
    }
}
