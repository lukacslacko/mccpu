package cpu.components.display;

import cpu.Component;
import cpu.Location;
import cpu.Vector;
import cpu.blocks.Repeater;
import cpu.blocks.Solid;
import cpu.blocks.Wire;
import cpu.components.infra.Bundle;
import cpu.components.infra.Connector;
import cpu.components.infra.PatternMatcher;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;

public class DisplayRow extends Component {
    private final String name;
    private final int width;

    public DisplayRow(String name, int width, Material material) {
        assert width > 1;

        this.name = name;
        this.width = width;
        int h = 2 * width - 2;
        HalfBit8 firstByte = null;
        for (int i = 0; i < width/2; ++i) {
            Pixel8 pixels = new Pixel8(name + "/pixels" + i, material);
            pixels.shift(new Vector(0, 0, 4*i));

            Connector connectorA = new Connector(
                    name + "/connectorA" + i, Material.GREEN_WOOL, 8,
                    3, 2, 4*i, 4*i, h - 4*i, 3);
            connectorA.shift(connectorA.getMarker("output").to(pixels.getMarker("inputA")));
            Connector connectorB = new Connector(
                    name + "/connectorB" + i, Material.YELLOW_WOOL, 8,
                    3, 2, 4*i+2, 4*i+2, h - 4*i - 2, 3);
            connectorB.shift(connectorB.getMarker("output").to(pixels.getMarker("inputB")));

            HalfBit8 bitsA = new HalfBit8(name + "/halfbit8A" + i, material);
            bitsA.shift(bitsA.getMarker("output").to(connectorA.getMarker("input")));
            HalfBit8 bitsB = new HalfBit8(name + "/halfbit8B" + i, material);
            bitsB.shift(bitsB.getMarker("output").to(bitsA.getMarker("output")).plus(new Vector(0, 0, 4)));

            if (i == 0) {
                firstByte = bitsA;
            }

            merge(pixels);
            merge(connectorA);
            merge(connectorB);
            merge(bitsA);
            merge(bitsB);
        }

        PatternMatcher firstWriteMatcher = null;
        for (int i = 0; i < width; ++i) {
            PatternMatcher writeMatcher = new PatternMatcher(name + "/write_matcher" + i, 7, 64 + i, Material.GREEN_WOOL);
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
        }

        Bundle writeAddressBundle = new Bundle(name + "/write_address_bundle", 8, 4 * width + 12, 0, 0, 13, Material.YELLOW_WOOL);
        writeAddressBundle.shift(writeAddressBundle.getMarker("input").to(firstWriteMatcher.getMarker("input")).plus(new Vector(0, -1, -1)));
        merge(writeAddressBundle);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String kind() {
        return "display_row" + width;
    }
}
