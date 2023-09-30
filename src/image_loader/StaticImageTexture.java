package image_loader;

import org.lwjgl.util.vector.Vector2f;

/**
 * Created by Yonas Halefom on 1/22/2017.
 */
public class StaticImageTexture {
    private int texture;
    private Vector2f position;
    private Vector2f scale;

    public StaticImageTexture(int texture, Vector2f position, Vector2f scale) {
        this.texture = texture;
        this.position = position;
        this.scale = scale;
    }

    public int getTexture() {
        return texture;
    }

    public Vector2f getPosition() {
        return position;
    }

    public Vector2f getScale() {
        return scale;
    }
}
