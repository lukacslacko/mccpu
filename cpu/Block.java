package cpu;

import cpu.render.Minecraft;
import cpu.render.SVG;

abstract public class Block {
    @Override
    public String toString() {
        return "Block " + describe();
    }

    abstract protected String describe();

    abstract public void put(Location location, SVG svg);
    abstract public void put(Location location, Minecraft minecraft);

    public void rotate(int quarters) {}
}
