package shaders;

import mainModel.CameraHandler;
import mainModel.LightHandler;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import helper.CalculationHandler;

/**
 * Created by Yonas Halefom on 1/20/2017.
 */
public class LandShader extends ShaderMainHandler {
	private static final String VERTEX_FILE = "src/shaders/terrainVertexShader.txt";
	private static final String FRAGMENT_FILE = "src/shaders/terrainFragmentShader.txt";

	private int location_transformationMatrix;
	private int location_projectionMatrix;
	private int location_viewMatrix;
	private int location_lightPosition;
	private int location_lightColour;
	private int location_shineDamper;
	private int location_reflectivity;
	private int location_skyColour;
	private int location_backgroundTexture;
	private int location_redTexture;
	private int location_greenTexture;
	private int location_blueTexture;
	private int location_blendMap;


	public LandShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoordinates");
		super.bindAttribute(2, "normal");
	}

	@Override
	protected void getAllUniformLocations() {
		location_transformationMatrix = super.getUniformLocation("transformationMatrix");
		location_projectionMatrix = super.getUniformLocation("projectionMatrix");
		location_viewMatrix = super.getUniformLocation("viewMatrix");
		location_lightPosition = super.getUniformLocation("lightPosition");
		location_lightColour = super.getUniformLocation("lightColour");
		location_shineDamper = super.getUniformLocation("shineDamper");
		location_reflectivity = super.getUniformLocation("reflectivity");
		location_skyColour = super.getUniformLocation("skyColour");
		location_backgroundTexture = super.getUniformLocation("backgroundTexture");
		location_redTexture = super.getUniformLocation("redTextureMap");
		location_greenTexture = super.getUniformLocation("greenTextureMap");
		location_blueTexture = super.getUniformLocation("blueTextureMap");
		location_blendMap = super.getUniformLocation("blendMap");
	}

	public void connectTextureUnits(){
		super.loadInt(location_backgroundTexture, 0);
		super.loadInt(location_redTexture, 1);
		super.loadInt(location_greenTexture, 2);
		super.loadInt(location_blueTexture, 3);
		super.loadInt(location_blendMap, 4);
	}

	public void loadSkyColour(float r, float g, float b) {
		super.loadVector(location_skyColour, new Vector3f(r, g, b));
	}

	public void loadShineVariables(float damper, float reflectivity) {
		super.loadFloat(location_shineDamper, damper);
		super.loadFloat(location_reflectivity, reflectivity);
	}

	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(location_transformationMatrix, matrix);
	}

	public void loadLight(LightHandler lightHandler) {
		super.loadVector(location_lightPosition, lightHandler.getPosition());
		super.loadVector(location_lightColour, lightHandler.getColour());
	}

	public void loadViewMatrix(CameraHandler cameraHandler) {
		Matrix4f viewMartix = CalculationHandler.createViewMatrix(cameraHandler);
		super.loadMatrix(location_viewMatrix, viewMartix);
	}

	public void loadProjectionMatrix(Matrix4f projection) {
		super.loadMatrix(location_projectionMatrix, projection);
	}
}
