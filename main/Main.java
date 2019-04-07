import cpu.Location;
import cpu.Vector;
import cpu.components.Example;
import cpu.components.Not;
import cpu.components.PatternMatcher;
import cpu.render.SVG;
import org.apache.commons.io.FileUtils;
import org.bukkit.Material;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        SVG svg = new SVG(30, 6, 18, 1, 0.9f);
        Example example = new Example("main", Material.BLUE_WOOL);
        Example example2 = new Example("main2", Material.YELLOW_WOOL);
        example2.rotateAround(Location.origin(), 1);
        example2.shift(new Vector(16, 0, 0));
        example.merge(example2);
        example.rotateAround(Location.origin(), 1);
        example.put(svg);
        try {
            FileUtils.writeStringToFile(new File("main.svg"), svg.getSvg(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
