package renderer;

import mainModel.CameraHandler;
import mainModel.TransformableModel;
import mainModel.LightHandler;
import models.TexturedModel;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import shaders.ShaderTextBinder;
import shaders.LandShader;
import main_land.Land;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yonas Halefom on 1/20/2017.
 */
public class MainRenderer {

    private static final float FOV = 70;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000;

    private static final float RED = 0.5f;
    private static final float GREEN = 0.5f;
    private static final float BLUE = 0.5f;

    private Matrix4f projectionMatrix;

    private ShaderTextBinder shader = new ShaderTextBinder();
    private TransformableModelRenderer renderer;

    private LandRenderer terrainRenderer;
    private LandShader landShader = new LandShader();

    private Map<TexturedModel, List<TransformableModel>> entities = new HashMap<>();
    private List<Land> lands = new ArrayList<>();

    public MainRenderer() {
        enableCulling();
        createProjectionMatrix();
        renderer = new TransformableModelRenderer(shader, projectionMatrix);
        terrainRenderer = new LandRenderer(landShader, projectionMatrix);
    }

    public static void enableCulling() {
//        GL11.glEnable(GL11.GL_CULL_FACE); //Stop triangles from getting rendered that are facing away from the camera
//        GL11.glCullFace(GL11.GL_BACK); //Which triangle part to cull is it back or front
    }

    public static void disableCulling() {
//        GL11.glDisable(GL11.GL_CULL_FACE);
    }

    public void render(LightHandler sun, CameraHandler cameraHandler) {
        prepare();
        shader.start();
        shader.loadSkyColour(RED, GREEN, BLUE);
        shader.loadLight(sun);
        shader.loadViewMatrix(cameraHandler);
        renderer.render(entities);
        shader.stop();
        landShader.start();
        landShader.loadSkyColour(RED, GREEN, BLUE);
        landShader.loadLight(sun);
        landShader.loadViewMatrix(cameraHandler);
        terrainRenderer.render(lands);
        landShader.stop();
        lands.clear();
        entities.clear();
    }

    public void processLand(Land land) {
        lands.add(land);
    }

    public void processTransformableObject(TransformableModel transformableModel) {
        TexturedModel entityModel = transformableModel.getModel();
        List<TransformableModel> batch = entities.get(entityModel);
        if (batch != null) {
            batch.add(transformableModel);
        } else {
            List<TransformableModel> newBatch = new ArrayList<>();
            newBatch.add(transformableModel);
            entities.put(entityModel, newBatch);
        }
    }

    public void cleanUp() {
        shader.cleanUp();
        landShader.cleanUp();
    }

    /**
     * This method is called once per every frame and it will prepare open gl to render the game
     */
    public void prepare() {
        GL11.glEnable(GL11.GL_DEPTH_TEST); //tests which triangle is above which triangle
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT); // Clear the color from the last frame
        GL11.glClearColor(RED, GREEN, BLUE, 1);
    }

    private void createProjectionMatrix() {
        float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
        float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
        float x_scale = y_scale / aspectRatio;
        float frustum_length = FAR_PLANE / NEAR_PLANE;

        projectionMatrix = new Matrix4f();
        projectionMatrix.m00 = x_scale;
        projectionMatrix.m11 = y_scale;
        projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
        projectionMatrix.m23 = -1;
        projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
        projectionMatrix.m33 = 0;
    }
}
