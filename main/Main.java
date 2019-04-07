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
        new PatternMatcher("match", 8, 11, Material.BLUE_WOOL).put(svg);
        try {
            FileUtils.writeStringToFile(new File("main.svg"), svg.getSvg(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
