package cpu.blocks;

import cpu.Block;
import cpu.Location;
import cpu.render.Minecraft;
import cpu.render.SVG;

public class ErrorBlock extends Block {
    @Override
    protected String describe() {
        return "error";
    }

    @Override
    protected boolean same(Block other) {
        return other instanceof ErrorBlock;
    }

    @Override
    public void put(Location location, SVG svg) {
        svg.append(location, svg.cuboid(location, 0.2f, 0.8f, 0.2f, 0.8f, 0.2f, 0.8f, "red"));
    }

    @Override
    public void put(Location location, Minecraft minecraft) {

    }
}
