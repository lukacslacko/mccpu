package cpu.components.memory;

import cpu.Component;
import cpu.Location;
import cpu.Vector;
import cpu.blocks.Repeater;
import cpu.blocks.Solid;
import cpu.blocks.Wire;
import cpu.components.infra.Bundle;
import cpu.components.infra.PatternMatcher;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;

public class ByteRow extends Component {
    private final int bytes;
    private final String name;

    public ByteRow(int bytes, String name, Material material, Material readMaterial, Material writeMaterial) {
        assert bytes > 0;
        this.bytes = bytes;
        this.name = name;
        Bit8 firstByte = null;
        PatternMatcher firstWriteMatcher = null;
        PatternMatcher firstReadMatcher = null;
        for (int i = 0; i < bytes; ++i) {
            Bit8 bit8 = new Bit8(name + "/" + i, material);
            bit8.shift(new Vector(0, 0, 4*i));
            merge(bit8);
            if (i == 0) firstByte = bit8;
        }
        for (int i = 0; i < bytes; ++i) {
            PatternMatcher writeMatcher = new PatternMatcher(name + "/write_matcher" + i, 7, 64 + i, writeMaterial);
            writeMatcher.shift(writeMatcher.getMarker("output").to(firstByte.getMarker("write")).plus(new Vector(0, 0, 4*i)));
            merge(writeMatcher);
            if (i == 0) firstWriteMatcher = writeMatcher;

            Location reset = writeMatcher.getMarker("output");
            add(reset.shifted(new Vector(-2, -1, 0)), new Solid(material));
            add(reset.shifted(new Vector(-2, 0, 0)), new Repeater(BlockFace.WEST));
            add(reset.shifted(new Vector(-3, -1, 0)), new Solid(material));
            add(reset.shifted(new Vector(-3, 0, 0)), new Wire());
            add(reset.shifted(new Vector(-4, -1, 0)), new Solid(material));
            add(reset.shifted(new Vector(-4, 0, 0)), new Repeater(BlockFace.WEST));

            PatternMatcher readMatcher = new PatternMatcher(name + "/read_matcher" + i, 7, 64 + i, readMaterial);
            readMatcher.flipX();
            readMatcher.shift(readMatcher.getMarker("output").to(firstByte.getMarker("read")).plus(new Vector(0, 0, 4*i)));
            merge(readMatcher);
            if (i == 0) firstReadMatcher = readMatcher;
        }
        Bundle writeAddressBundle = new Bundle(name + "/write_address_bundle", 8, 4 * bytes + 12, 0, 0, 13, writeMaterial);
        writeAddressBundle.shift(writeAddressBundle.getMarker("input").to(firstWriteMatcher.getMarker("input")).plus(new Vector(0, -1, -1)));
        merge(writeAddressBundle);

        Bundle readAddressBundle = new Bundle(name + "/read_address_bundle", 7, 4 * bytes + 12, 0, 0, 13, readMaterial);
        readAddressBundle.shift(readAddressBundle.getMarker("input").to(firstReadMatcher.getMarker("input")).plus(new Vector(0, 1, -1)));
        merge(readAddressBundle);

        setMarker("write_all", writeAddressBundle.getMarker("input"));
        setMarker("write_address", writeAddressBundle.getMarker("input").above(2));
        setMarker("write_allow", writeAddressBundle.getMarker("input").above(14));

        setMarker("read_address", readAddressBundle.getMarker("input"));
        setMarker("read_allow", readAddressBundle.getMarker("input").above(12));

        setMarker("input", firstByte.getMarker("input"));
        setMarker("output", firstByte.getMarker("output"));
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String kind() {
        return "" + bytes + "bytes";
    }
}
