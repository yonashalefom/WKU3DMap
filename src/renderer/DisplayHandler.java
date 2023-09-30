package renderer;

import org.lwjgl.*;
import org.lwjgl.opengl.*;

public class DisplayHandler {
    private static int width = 1920;
    private static int height = 1080;
    private static int framesPerSecond = 120;

    private static long lastFrameTime;
    private static float delta;


    public static void createDisplay() {
        //OpenGL version
        ContextAttribs applicationAttributes = new ContextAttribs(3, 2)
                .withForwardCompatible(true)
                .withProfileCore(true);

        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.create(new PixelFormat(), applicationAttributes);
            Display.setTitle("Visit the glimpse of Wolkite University Virtually");
        } catch (LWJGLException e) {
            System.out.println("Problem occurred while creating the display");
        }
        GL11.glViewport(0, 0, width, height); // where to render the game
        lastFrameTime = getCurrentTime();
    }

    public static void updateDisplay() {
        Display.sync(framesPerSecond); // sync the game to run at a study frames per second
        Display.update();
        long currentFrameTime = getCurrentTime();
        delta = (currentFrameTime - lastFrameTime) / 1000f;
        lastFrameTime = currentFrameTime;
    }

    public static float getFrameTimeSeconds() {
        return delta;
    }

    public static void closeDisplay() {
        Display.destroy(); //closes the display
    }

    private static long getCurrentTime() {
        return Sys.getTime() * 1000 / Sys.getTimerResolution();
    }
}

