import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
//        SVGRenderTarget svg = new SVGRenderTarget(30, 18, 1);
        SVGRenderTarget svg = new SVGRenderTarget(12, 0, 0);
        new CPU(new Location(0, 0, 0)).render(svg);
        try {
            FileUtils.writeStringToFile(new File("main.svg"), svg.getSVG(), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
