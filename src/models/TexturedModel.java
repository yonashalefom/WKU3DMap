package models;

import object_texture_holders.ModelTexture;

/**
 * Created by Yonas Halefom on 1/18/2017.
 */
public class TexturedModel {

    private UntexturedModel untexturedModel;
    private ModelTexture texture;

    public TexturedModel(UntexturedModel model, ModelTexture texture) {
        this.untexturedModel = model;
        this.texture = texture;
    }

    public UntexturedModel getUntexturedModel() {
        return untexturedModel;
    }

    public ModelTexture getTexture() {
        return texture;
    }
}
