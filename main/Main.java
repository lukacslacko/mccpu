import cpu.Location;
import cpu.Vector;
import cpu.components.Example;
import cpu.components.Inc;
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
        Inc inc = new Inc("inc", 8, Material.BLUE_WOOL);
        inc.rotateAround(Location.origin(), -2);
        inc.put(svg);
        try {
            FileUtils.writeStringToFile(new File("main.svg"), svg.getSvg(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
