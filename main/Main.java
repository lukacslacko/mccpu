import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        SVGRenderTarget svg = new SVGRenderTarget(30, 6, 18, 1, 0.9f);
        //SVGRenderTarget svg = new SVGRenderTarget(36, 0, 0, 0, 0.5f);
        new Inc(new Location(0, 0, 0), new Vector(1, 0, 0), new Vector(0, 0, 1), Wire.BLUE).render(svg);
        try {
            FileUtils.writeStringToFile(new File("main.svg"), svg.getSVG(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
