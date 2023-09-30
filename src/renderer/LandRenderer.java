package renderer;

import models.UntexturedModel;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import shaders.LandShader;
import main_land.Land;
import object_texture_holders.LandTextureMixer;
import helper.CalculationHandler;

import java.util.List;

public class LandRenderer {
    private LandShader shader;

    public LandRenderer(LandShader shader, Matrix4f projectionMatrix) {
        this.shader = shader;
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.connectTextureUnits();
        shader.stop();
    }

    public void render(List<Land> lands) {
        for (Land land : lands) {
            prepareTerrain(land);
            loadModelMatrix(land);
            GL11.glDrawElements(GL11.GL_TRIANGLES, land.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0 /*Where in the data to start rendering | 0 manse from the beginning*/);
            unbindTexturedModel();
        }
    }

    private void prepareTerrain(Land land) {
        UntexturedModel untexturedModel = land.getModel();

        GL30.glBindVertexArray(untexturedModel.getVaoID()); //bind the VAO that we want to use
        GL20.glEnableVertexAttribArray(0); // Enable the attribute list 0 of the VAO created
        GL20.glEnableVertexAttribArray(1); // Enable the attribute list 1 of the VAO created
        GL20.glEnableVertexAttribArray(2); // Enable the attribute list 2 of the VAO created

        bindTextures(land);

        //Load the shine settings
        shader.loadShineVariables(1, 0);


    }

    private void bindTextures(Land land){
        LandTextureMixer texturePack = land.getTexturePack();
        //bind the cube texture
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturePack.getBackgroundTexture().getTextureID());
        GL13.glActiveTexture(GL13.GL_TEXTURE1);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturePack.getRedTexture().getTextureID());
        GL13.glActiveTexture(GL13.GL_TEXTURE2);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturePack.getGreenTexture().getTextureID());
        GL13.glActiveTexture(GL13.GL_TEXTURE3);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturePack.getBlueTexture().getTextureID());
        GL13.glActiveTexture(GL13.GL_TEXTURE4);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, land.getBlendMap().getTextureID());
    }

    private void unbindTexturedModel() {
        //Unbind all
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    private void loadModelMatrix(Land land) {
        Matrix4f transformationMatrix = CalculationHandler.createTransformationMatrix(new Vector3f(land.getX(), 0, land.getZ()), 0, 0, 0, 1);
        shader.loadTransformationMatrix(transformationMatrix);
    }
}
