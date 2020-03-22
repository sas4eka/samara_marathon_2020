import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Renderer {
    static void render(String testName, List<Point> points) throws IOException {
        int n = 1001;
        BufferedImage image = new BufferedImage(n, n, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                image.setRGB(i, j, 0xFFFFFF);
            }
        }
        for (Point pt : points) {
            image.setRGB(pt.x, pt.y, 0x000000);
        }
        ImageIO.write(image, "png", new File("img/" + testName + ".png"));
    }
}
