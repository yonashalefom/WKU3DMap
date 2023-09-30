package image_loader;

import models.UntexturedModel;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import renderer.ToVAOLoader;
import helper.CalculationHandler;

import java.util.List;

/**
 * Created by Yonas Halefom on 1/22/2017.
 */
public class StaticImageRenderer {
    private final UntexturedModel quad;
    private StaticImageShader shader;

    public StaticImageRenderer(ToVAOLoader toVAOLoader) {
        float[] positions = {-1, 1, -1, -1, 1, 1, 1, -1};
        quad = toVAOLoader.loadToVAO(positions);
        shader = new StaticImageShader();
    }

    public void render(List<StaticImageTexture> quis) {
        shader.start();
        GL30.glBindVertexArray(quad.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        for (StaticImageTexture gui : quis) {
            GL13.glActiveTexture(GL13.GL_TEXTURE0);
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, gui.getTexture());
            Matrix4f matrix = CalculationHandler.createTransformationMatrix(gui.getPosition(), gui.getScale());
            shader.loadTransformation(matrix);
            GL11.glDrawArrays(GL11.GL_TRIANGLE_STRIP, 0, quad.getVertexCount());
        }
        GL11.glDisable(GL11.GL_BLEND);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
        shader.stop();
    }

    public void cleanUp() {
        shader.cleanUp();
    }
}
