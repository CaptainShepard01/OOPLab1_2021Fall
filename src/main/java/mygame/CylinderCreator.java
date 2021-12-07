package main.java.mygame;

import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Cylinder;
import com.jme3.texture.Texture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author balanton
 */
public class CylinderCreator {
    private final Node rootNode;
    private final BulletAppState bulletAppState;
    private Material material;
    private final List<RigidBodyControl> movableRigidBodyes = new ArrayList<>();
    private final Cylinder CYLINDER;

    CylinderCreator(AssetManager assetManager, Node rootNode, BulletAppState bulletAppState) {
        this.rootNode = rootNode;
        this.bulletAppState = bulletAppState;
        CYLINDER = new Cylinder(32, 32, 0.6f, 2f, true);

        initMaterials(assetManager);
    }

    private void initMaterials(AssetManager assetManager) {
        material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        TextureKey key = new TextureKey("Textures/cylinder.jpg");
        key.setGenerateMips(true);
        Texture texture = assetManager.loadTexture(key);
        material.setTexture("ColorMap", texture);
    }

    public Cylinder getCYLINDER() {
        return CYLINDER;
    }

    private RigidBodyControl initCylinder(Vector3f position) {
        Random rand = new Random();

        Geometry cylinderGeometry = new Geometry();
        cylinderGeometry.setMesh(CYLINDER);
        cylinderGeometry.setMaterial(material);
        RigidBodyControl cylinderRigidBody = new RigidBodyControl(5 + rand.nextInt(4));
        cylinderGeometry.addControl(cylinderRigidBody);
        cylinderRigidBody.setPhysicsLocation(position);

        bulletAppState.getPhysicsSpace().add(cylinderRigidBody);
        rootNode.attachChild(cylinderGeometry);
        return cylinderRigidBody;
    }

    protected void createCylinder(Camera camera, Box plane) {
        RigidBodyControl cylinderRigidBody = initCylinder(Physics.calcPosition(camera, plane));
        movableRigidBodyes.add(cylinderRigidBody);
    }
}


