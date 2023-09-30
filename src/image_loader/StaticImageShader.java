package image_loader;

import org.lwjgl.util.vector.Matrix4f;
import shaders.ShaderMainHandler;

/**
 * Created by Yonas Halefom on 1/22/2017.
 */
public class StaticImageShader extends ShaderMainHandler {
    private static final String VERTEX_FILE = "C:\\Users\\hp\\Desktop\\Personal Website Development\\Personal Data & Projects\\Projects\\GRAPHICS PROJECTS\\BACKUPS\\The Last Backup\\untitled1\\src\\image_loader\\guiVertexShader.txt";
    private static final String FRAGMENT_FILE = "C:\\Users\\hp\\Desktop\\Personal Website Development\\Personal Data & Projects\\Projects\\GRAPHICS PROJECTS\\BACKUPS\\The Last Backup\\untitled1\\src\\image_loader\\gurFragmentShader.txt";

    private int location_transformationMatrix;

    public StaticImageShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    protected void loadTransformation(Matrix4f matrix) {
        super.loadMatrix(location_transformationMatrix, matrix);
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
    }
}
