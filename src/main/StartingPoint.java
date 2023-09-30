package main;

import image_loader.StaticImageRenderer;
import _mainController.MainController;
import mainModel.CameraHandler;
import mainModel.LightHandler;
import mainModel.MovableObject;
import image_loader.StaticImageTexture;
import mainModel.TransformableModel;
import models.TexturedModel;
import models.UntexturedModel;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import renderer.*;
import main_land.Land;
import object_texture_holders.ModelTexture;
import object_texture_holders.LandTexture;
import object_texture_holders.LandTextureMixer;

import java.util.ArrayList;
import java.util.List;

public class StartingPoint extends MainController {
    public static MovableObject movableObject;

    public static void main(String[] args) {
        DisplayHandler.createDisplay(); //Opens the display
        ToVAOLoader toVAOLoader = new ToVAOLoader();

        LandTexture backgroundTexture = new LandTexture(toVAOLoader.loadTexture("textures/Grass"));
        LandTexture redTexture = new LandTexture(toVAOLoader.loadTexture("textures/Ground4"));
        LandTexture greenTexture = new LandTexture(toVAOLoader.loadTexture("textures/Ground1"));
        LandTexture blueTexture = new LandTexture(toVAOLoader.loadTexture("textures/asphalt"));

        LandTextureMixer landTextureMixer = new LandTextureMixer(backgroundTexture, redTexture, greenTexture, blueTexture);
        LandTexture blendMap = new LandTexture(toVAOLoader.loadTexture("textures/blendMap"));

        //region Raw Models
        //region Navigation
        UntexturedModel navSpaceship = OBJLoader.loadObjModel("models/Spaceship", toVAOLoader);
        TexturedModel navArrowTexturedModel = new TexturedModel(navSpaceship, new ModelTexture(toVAOLoader.loadTexture("textures/arrowTexture")));
        ModelTexture navArrowTextureMain = navArrowTexturedModel.getTexture();
        navArrowTextureMain.setShineDamper(1);
        navArrowTextureMain.setReflectivity(0.001f);
        //endregion

        //region CCI Building 1
        UntexturedModel cciBuilding = OBJLoader.loadObjModel("models/cciBuildingFinalobj", toVAOLoader);
        TexturedModel cciTexturedModel = new TexturedModel(cciBuilding, new ModelTexture(toVAOLoader.loadTexture("textures/cciTexture")));
        ModelTexture cciTextureMain = cciTexturedModel.getTexture();
        cciTextureMain.setShineDamper(40);
        cciTextureMain.setReflectivity(0.1f);
        //endregion

        //region Building Launch
        UntexturedModel laungh = OBJLoader.loadObjModel("models/launghFinal", toVAOLoader);
        TexturedModel launghTexturedModel = new TexturedModel(laungh, new ModelTexture(toVAOLoader.loadTexture("textures/launghTexture")));
        ModelTexture launghModelTextureMain = launghTexturedModel.getTexture();
        launghModelTextureMain.setShineDamper(40);
        launghModelTextureMain.setReflectivity(0.1f);
        //endregion

        //region Wall
        UntexturedModel wall = OBJLoader.loadObjModel("models/wall", toVAOLoader);
        TexturedModel wallTexturedModel = new TexturedModel(wall, new ModelTexture(toVAOLoader.loadTexture("textures/wallTexture")));
        ModelTexture wallModelTextureMain = wallTexturedModel.getTexture();
        wallModelTextureMain.setShineDamper(40);
        wallModelTextureMain.setReflectivity(0.1f);
        //endregion

        //region Library
        UntexturedModel library = OBJLoader.loadObjModel("models/Library", toVAOLoader);
        TexturedModel libraryTexturedModel = new TexturedModel(library, new ModelTexture(toVAOLoader.loadTexture("textures/libraryTexture")));
        ModelTexture libraryModelTextureMain = libraryTexturedModel.getTexture();
        libraryModelTextureMain.setShineDamper(40);
        libraryModelTextureMain.setReflectivity(0.1f);
        //endregion

        //region Library
        UntexturedModel ater = OBJLoader.loadObjModel("models/ater", toVAOLoader);
        TexturedModel aterTexturedModel = new TexturedModel(ater, new ModelTexture(toVAOLoader.loadTexture("textures/ater_Texture")));
        ModelTexture aterTextureMain = libraryTexturedModel.getTexture();
        aterTextureMain.setShineDamper(40);
        aterTextureMain.setReflectivity(0.1f);
        //endregion

        //region Trees
        //Tree 1
        UntexturedModel tree1 = OBJLoader.loadObjModel("models/tree", toVAOLoader);
        TexturedModel treeTexturedModel = new TexturedModel(tree1, new ModelTexture(toVAOLoader.loadTexture("textures/Tree1Texture")));
        ModelTexture treeTextureMain = libraryTexturedModel.getTexture();
        treeTextureMain.setShineDamper(40);
        treeTextureMain.setReflectivity(0.1f);

        //Tree 2
        UntexturedModel tree2 = OBJLoader.loadObjModel("models/tree2", toVAOLoader);
        TexturedModel tree2TexturedModel = new TexturedModel(tree2, new ModelTexture(toVAOLoader.loadTexture("textures/Tree1Texture")));
        ModelTexture tree2TextureMain = libraryTexturedModel.getTexture();
        tree2TextureMain.setShineDamper(40);
        tree2TextureMain.setReflectivity(0.1f);

        //Tree 3
        UntexturedModel walkWayTree = OBJLoader.loadObjModel("models/walkWayTree", toVAOLoader);
        TexturedModel walkWayTreeTexturedModel = new TexturedModel(walkWayTree, new ModelTexture(toVAOLoader.loadTexture("textures/Tree1Texture")));
        ModelTexture walkWayTreeTextureMain = libraryTexturedModel.getTexture();
        walkWayTreeTextureMain.setShineDamper(40);
        walkWayTreeTextureMain.setReflectivity(0.1f);

        //endregion

        //region Walkway
        UntexturedModel walkway = OBJLoader.loadObjModel("models/walkway", toVAOLoader);
        TexturedModel walkwayTexturedModel = new TexturedModel(walkway, new ModelTexture(toVAOLoader.loadTexture("textures/waj")));
        ModelTexture walkwayTextureMain = libraryTexturedModel.getTexture();
        walkwayTextureMain.setShineDamper(40);
        walkwayTextureMain.setReflectivity(0.1f);
        //endregion

        //region To Wku
        UntexturedModel toWku = OBJLoader.loadObjModel("models/ToWku", toVAOLoader);
        TexturedModel toWkuTexturedModel = new TexturedModel(toWku, new ModelTexture(toVAOLoader.loadTexture("textures/ToWkuTexture")));
        ModelTexture toWkuTextureMain = libraryTexturedModel.getTexture();
        toWkuTextureMain.setShineDamper(40);
        toWkuTextureMain.setReflectivity(0.1f);
        //endregion

        //region Main Gate
        UntexturedModel mainGate = OBJLoader.loadObjModel("models/MainGate", toVAOLoader);
        TexturedModel mainGateModel = new TexturedModel(mainGate, new ModelTexture(toVAOLoader.loadTexture("textures/MainGateTexture1")));
        ModelTexture mainGateMain = libraryTexturedModel.getTexture();
        mainGateMain.setShineDamper(40);
        mainGateMain.setReflectivity(0.1f);
        //endregion

        //region Directions
        UntexturedModel directions = OBJLoader.loadObjModel("models/Directions", toVAOLoader);
        TexturedModel directionsModel = new TexturedModel(directions, new ModelTexture(toVAOLoader.loadTexture("textures/DirectionsTexture")));
        ModelTexture directionsMain = libraryTexturedModel.getTexture();
        directionsMain.setShineDamper(40);
        directionsMain.setReflectivity(0.1f);
        //endregion

        //region Directions
        UntexturedModel centerCafe = OBJLoader.loadObjModel("models/CenterCafe", toVAOLoader);
        TexturedModel centerCafeModel = new TexturedModel(centerCafe, new ModelTexture(toVAOLoader.loadTexture("textures/CenterCafeTexture2")));
        ModelTexture centerCafeMain = libraryTexturedModel.getTexture();
        centerCafeMain.setShineDamper(40);
        centerCafeMain.setReflectivity(0.1f);
        //endregion
        //endregion

        //region Transformable Objects
        TransformableModel cciBuildingTransformableModel = new TransformableModel(cciTexturedModel, new Vector3f(-435, 0, -100), 0, 90.0f, 0, 0.1f);
        TransformableModel cciBuildingTransformableModel2 = new TransformableModel(cciTexturedModel, new Vector3f(-443.40015f, 0, -159.79965f), 0, 147.1996f, 0, 0.1f);
        TransformableModel cciBuildingTransformableModel3 = new TransformableModel(cciTexturedModel, new Vector3f(-329.29816f, 0, -154.89967f), 0, 33.099926f, 0, 0.1f);
        TransformableModel cciBuildingTransformableModel4 = new TransformableModel(cciTexturedModel, new Vector3f(-481.60284f, 0, -132.49985f), 0, 146.40054f, 0, 0.1f);
        TransformableModel cciBuildingTransformableModel5 = new TransformableModel(cciTexturedModel, new Vector3f(-513.7043f, 0, -184.30301f), 0, 147.70062f, 0, 0.1f);
        TransformableModel cciBuildingTransformableModel6 = new TransformableModel(cciTexturedModel, new Vector3f(-270.48996f, 0, -192.80353f), 0, 215.70477f, 0, 0.1f);
        TransformableModel launghTransformableModel = new TransformableModel(launghTexturedModel, new Vector3f(-395.80182f, 0, -220.60043f), 0, 91.49998f, 0, 0.7f);
        TransformableModel libraryTransformableModel = new TransformableModel(libraryTexturedModel, new Vector3f(-503.00357f, 0, -368.70197f), 0, -1.2992013f, 0, 0.013f);
        TransformableModel libraryTransformableModel1 = new TransformableModel(libraryTexturedModel, new Vector3f(-440.79977f, 0, -378.60257f), 0, -1.2992013f, 0, 0.013f);
        TransformableModel CenterCafeTransformableModel = new TransformableModel(centerCafeModel, new Vector3f(-400.69733f, 0, -390.50262f), 0, -1.2992013f, 180, 0.035f);
        TransformableModel libraryTransformableModel3 = new TransformableModel(libraryTexturedModel, new Vector3f(-294.29083f, 0, -366.50183f), 0, -1.2992013f, 0, 0.013f);
        TransformableModel libraryTransformableModel4 = new TransformableModel(libraryTexturedModel, new Vector3f(-353.19443f, 0, -377.90253f), 0, -1.2992013f, 0, 0.013f);
        TransformableModel aterTransformableModel = new TransformableModel(toWkuTexturedModel, new Vector3f(-380.99884f, 0, -5.0999575f), 0, -90, 180, 0.02f);
        TransformableModel mainGateTransformableModel = new TransformableModel(mainGateModel, new Vector3f(-397.29984f, 0, -6.0999565f), 0, -180, 180, 0.018f);
        TransformableModel directionsModelTransformableModel = new TransformableModel(directionsModel, new Vector3f(-392.29953f, 0, -168.30104f), 0, -180, 180, 0.008f);

        //region Walls
        int wallLeftInitialLocation = -418;
        ArrayList<TransformableModel> wallsLeft = new ArrayList();
        for (int i = 0; i < 36; i++, wallLeftInitialLocation -= 10) {
            wallsLeft.add(new TransformableModel(wallTexturedModel, new Vector3f(wallLeftInitialLocation, 0, -5), 0, 0, 0, 1));
        }

        int wallRithtInitialLocation = -376;
        ArrayList<TransformableModel> wallsRight = new ArrayList();
        for (int i = 0; i < 36; i++, wallRithtInitialLocation += 10) {

            wallsLeft.add(new TransformableModel(wallTexturedModel, new Vector3f(wallRithtInitialLocation, 0, -5), 0, 0, 0, 1));
        }
        //endregion
        //region walkway ater
        int aterRithtInitialLocation = -3;
        int aterLeftInitialLocation = -3;
        float rotationY = 0;
        ArrayList<TransformableModel> aterRight = new ArrayList();
        for (int i = 0; i < 97; i++) {
//            if (i == 50) {
//                rotationY = -53.79975f;
//            }
            if (i == 80) {
                aterLeftInitialLocation -= 12;
            }
            if (i < 49) {
                aterRithtInitialLocation -= 3;
            } else if (i > 50) {
                aterLeftInitialLocation -= 3;
            }


            if (i < 50) {
                aterRight.add(new TransformableModel(aterTexturedModel, new Vector3f(-382, 0, aterRithtInitialLocation), 0, rotationY, 180, 0.02f));
            } else if (i > 50) {
                aterRight.add(new TransformableModel(aterTexturedModel, new Vector3f(-411.7007f, 0, aterLeftInitialLocation), 0, rotationY, 180, 0.02f));
            }

        }
        //endregion
        //region trees
        ArrayList<TransformableModel> trees = new ArrayList();
        for (int i = 0; i < 50; i++) {
            if (i < 25) {
                trees.add(new TransformableModel(treeTexturedModel, new Vector3f(random(-365, -301), 0, random(-55, -101)), 0, 0, 180, 0.02f));
            } else if (i > 24) {
                trees.add(new TransformableModel(tree2TexturedModel, new Vector3f(random(-365, -301), 0, random(-55, -101)), 0, 0, 180, 0.02f));
            }

        }
        //endregion
        //region walkway
        int initialZLoc = 8;
        ArrayList<TransformableModel> walkways = new ArrayList();
        for (int i = 0; i < 16; i++, initialZLoc -= 6) {
            if (true) {
                trees.add(new TransformableModel(walkwayTexturedModel, new Vector3f(-369.79816f, 0, initialZLoc), 0, 0, 180, 0.02f));
            }
        }
        //endregion
        //region walkway Trees
        int initialZLoc1 = -18;
        ArrayList<TransformableModel> walkwayTrees = new ArrayList();
        for (int i = 0; i < 20; i++, initialZLoc1 -= 15) {
            if (i < 10) {
                trees.add(new TransformableModel(walkWayTreeTexturedModel, new Vector3f(-380.3988f, 0, initialZLoc1), 0, 0, 180, 0.01f));
            } else if (i > 9) {
                if (i == 10) {
                    initialZLoc1 = -18;
                }
                trees.add(new TransformableModel(walkWayTreeTexturedModel, new Vector3f(-413.3988f, 0, initialZLoc1), 0, 0, 180, 0.01f));
            }
        }
        //endregion
        //endregion

        //region Camera Target
        MovableObject cameraTarget = new MovableObject(navArrowTexturedModel, new Vector3f(-396.50186f, 2, -1.59995f), 0, 0, 0, 0.001f);
        movableObject = cameraTarget;
        //endregion

        //region Light
        LightHandler lightHandler = new LightHandler(new Vector3f(3000, 3000, 3000), new Vector3f(1, 1, 1));
        //endregion

        //region Land
        Land land = new Land(0, 0, toVAOLoader, landTextureMixer, blendMap, "textures/heightMap");
        Land land2 = new Land(-1, 0, toVAOLoader, landTextureMixer, blendMap, "textures/heightmap");
        //endregion

        //region Static Image On Screen
        List<StaticImageTexture> staticTextOnDisplayArea = new ArrayList<>();
//        StaticImageTexture staticImageTexture = new StaticImageTexture(toVAOLoader.loadTexture("textures/Wall"), new Vector2f(0.5f, 0.5f), new Vector2f(0.25f, 0.25f));
//        staticTextOnDisplayArea.add(staticImageTexture);
        StaticImageRenderer staticImageRenderer = new StaticImageRenderer(toVAOLoader);
        //endregion

        //region Camera
        CameraHandler cameraHandler = new CameraHandler(cameraTarget);
        //endregion

        //region Renderer
        MainRenderer renderer = new MainRenderer();
        //endregion

        while (!Display.isCloseRequested()) {
            if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
                System.out.println("called left!");
                launghTransformableModel.increaseRotation(0, 0.9f, 0);
                System.out.println("Rotation X" + launghTransformableModel.getRotX());
                System.out.println("Rotation Y" + launghTransformableModel.getRotY());
                System.out.println("Rotation Z" + launghTransformableModel.getRotZ());
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
                System.out.println("called right!");
                launghTransformableModel.increaseRotation(0, -0.9f, 0);
                System.out.println("Rotation X" + launghTransformableModel.getRotX());
                System.out.println("Rotation Y" + launghTransformableModel.getRotY());
                System.out.println("Rotation Z" + launghTransformableModel.getRotZ());
            }

            //region TransformableModel Movement
            if (Keyboard.isKeyDown(Keyboard.KEY_T)) {
                launghTransformableModel.setPosition(new Vector3f(launghTransformableModel.getPosition().getX(), launghTransformableModel.getPosition().getY(), launghTransformableModel.getPosition().getZ() - 0.1f));
                System.out.println("----------------Movement-----------------");
                System.out.println("Position X: " + launghTransformableModel.getPosition().getX());
                System.out.println("Position Z: " + launghTransformableModel.getPosition().getZ());
                System.out.println("-----------------------------------------");
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_G)) {
                launghTransformableModel.setPosition(new Vector3f(launghTransformableModel.getPosition().getX(), launghTransformableModel.getPosition().getY(), launghTransformableModel.getPosition().getZ() + 0.1f));
                System.out.println("----------------Movement-----------------");
                System.out.println("Position X: " + launghTransformableModel.getPosition().getX());
                System.out.println("Position Z: " + launghTransformableModel.getPosition().getZ());
                System.out.println("-----------------------------------------");
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_F)) {
                launghTransformableModel.setPosition(new Vector3f(launghTransformableModel.getPosition().getX() - 0.1f, launghTransformableModel.getPosition().getY(), launghTransformableModel.getPosition().getZ()));
                System.out.println("----------------Movement-----------------");
                System.out.println("Position X: " + launghTransformableModel.getPosition().getX());
                System.out.println("Position Z: " + launghTransformableModel.getPosition().getZ());
                System.out.println("-----------------------------------------");
            }
            if (Keyboard.isKeyDown(Keyboard.KEY_H)) {
                launghTransformableModel.setPosition(new Vector3f(launghTransformableModel.getPosition().getX() + 0.1f, launghTransformableModel.getPosition().getY(), launghTransformableModel.getPosition().getZ()));
                System.out.println("----------------Movement-----------------");
                System.out.println("Position X: " + launghTransformableModel.getPosition().getX());
                System.out.println("Position Z: " + launghTransformableModel.getPosition().getZ());
                System.out.println("-----------------------------------------");
            }
            //endregion

            //game logic
            cameraHandler.move();
            cameraTarget.move();


            renderer.processLand(land);
            renderer.processLand(land2);
            renderer.processTransformableObject(cciBuildingTransformableModel);
            renderer.processTransformableObject(cciBuildingTransformableModel2);
            renderer.processTransformableObject(cciBuildingTransformableModel3);
            renderer.processTransformableObject(cciBuildingTransformableModel4);
            renderer.processTransformableObject(cciBuildingTransformableModel5);
            renderer.processTransformableObject(cciBuildingTransformableModel6);
            renderer.processTransformableObject(cameraTarget);
            renderer.processTransformableObject(launghTransformableModel);
            renderer.processTransformableObject(libraryTransformableModel);
            renderer.processTransformableObject(libraryTransformableModel1);
            renderer.processTransformableObject(CenterCafeTransformableModel);
            renderer.processTransformableObject(libraryTransformableModel3);
            renderer.processTransformableObject(libraryTransformableModel4);
            renderer.processTransformableObject(aterTransformableModel);
            renderer.processTransformableObject(mainGateTransformableModel);
            renderer.processTransformableObject(directionsModelTransformableModel);

            //region Main Outer Ater
            for (TransformableModel transformableModel : wallsLeft) {
                renderer.processTransformableObject(transformableModel);
            }

            for (TransformableModel transformableModel : wallsRight) {
                renderer.processTransformableObject(transformableModel);
            }
            //endregion

            //region Inner Ater
            for (TransformableModel transformableModel : aterRight) {
                renderer.processTransformableObject(transformableModel);
            }
            //endregion
            //
            // region Inner Ater
            for (TransformableModel transformableModel : trees) {
                renderer.processTransformableObject(transformableModel);
            }
            //endregion

            // region Inner Ater
            for (TransformableModel transformableModel : walkways) {
                renderer.processTransformableObject(transformableModel);
            }
            //endregion

            // region Inner Ater
            for (TransformableModel transformableModel : walkwayTrees) {
                renderer.processTransformableObject(transformableModel);
            }
            //endregion

            renderer.render(lightHandler, cameraHandler);
            staticImageRenderer.render(staticTextOnDisplayArea);
            DisplayHandler.updateDisplay(); //Updates the display
        }
//        staticImageRenderer.cleanUp();
        renderer.cleanUp();
        toVAOLoader.cleanUP(); // Cleanup the toVAOLoader (all the VAOs and VBOs that is created) once the game is closed
        DisplayHandler.closeDisplay(); // If the loop is break, then the display will be closed
    }

    public static int random(int m, int n) {
        return (int) (Math.random() * (n - m + 1)) + m;
    }
}
