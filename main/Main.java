import cpu.Component;
import cpu.Location;
import cpu.components.display.DisplayRow;
import cpu.components.memory.bank.Adapter;
import cpu.render.SVG;
import org.apache.commons.io.FileUtils;
import org.bukkit.Material;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SVG svg = new SVG(30, 6, 18, 1, 0.9f);
        //SVG svg = new SVG(36, 0, 0, 1, 0.5f);
        /*
        Compound compound = new Compound("main");
        Not a = new Not("a", Material.BLUE_WOOL);
        Not b = new Not("b", Material.YELLOW_WOOL);
        Not c = new Not("c", Material.GREEN_WOOL);
        b.rotateAround(b.getMarker("input"), 1);
        b.shift(b.getMarker("input").to(a.getMarker("output")));
        c.shift(c.getMarker("input").to(b.getMarker("output")));
        compound.merge(a);
        compound.merge(b);
        compound.merge(c);
        compound.put(svg);
        */
        Component component = new DisplayRow("main", 4, Material.BLUE_WOOL);
        component.rotateAround(Location.origin(), 0);
        component.put(svg);
        try {
            FileUtils.writeStringToFile(new File("main.svg"), svg.getSvg(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
