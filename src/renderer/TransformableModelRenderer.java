package renderer;

import mainModel.TransformableModel;
import models.UntexturedModel;
import models.TexturedModel;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.Matrix4f;
import shaders.ShaderTextBinder;
import object_texture_holders.ModelTexture;
import helper.CalculationHandler;

import java.util.List;
import java.util.Map;

/**
 * This class renders the model from the VAO
 */
public class TransformableModelRenderer {
    private ShaderTextBinder shader;

    public TransformableModelRenderer(ShaderTextBinder shader, Matrix4f projectionMatrix) {
        this.shader = shader;
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    public void render(Map<TexturedModel, List<TransformableModel>> entities) {
        for (TexturedModel model : entities.keySet()) {
            prepareTexturedModel(model);
            List<TransformableModel> batch = entities.get(model);
            for (TransformableModel transformableModel : batch) {
                prepareInstance(transformableModel);
                GL11.glDrawElements(GL11.GL_TRIANGLES /*What to render*/, model.getUntexturedModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0 /*Where in the data to start rendering | 0 manse from the beginning*/);
            }
            unbindTexturedModel();
        }
    }

    private void prepareTexturedModel(TexturedModel model) {
        UntexturedModel untexturedModel = model.getUntexturedModel();

        GL30.glBindVertexArray(untexturedModel.getVaoID()); //bind the VAO that we want to use
        GL20.glEnableVertexAttribArray(0); // Enable the attribute list 0 of the VAO created
        GL20.glEnableVertexAttribArray(1); // Enable the attribute list 1 of the VAO created
        GL20.glEnableVertexAttribArray(2); // Enable the attribute list 2 of the VAO created

        ModelTexture texture = model.getTexture();
        if (texture.isHasTransparency()) {
            MainRenderer.disableCulling();
        }
        shader.loadFakeLightingVariable(texture.isUseFakeLighting());

        //Load the shine settings
        shader.loadShineVariables(texture.getShineDamper(), texture.getReflectivity());

        //bind the cube texture
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getID());
    }

    private void unbindTexturedModel() {
        MainRenderer.enableCulling();
        //Unbind all
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    private void prepareInstance(TransformableModel transformableModel) {
        Matrix4f transformationMatrix = CalculationHandler.createTransformationMatrix(transformableModel.getPosition(), transformableModel.getRotX(), transformableModel.getRotY(), transformableModel.getRotZ(), transformableModel.getScale());
        shader.loadTransformationMatrix(transformationMatrix);
    }
}
