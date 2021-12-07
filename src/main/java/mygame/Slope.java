package main.java.mygame;

import com.jme3.asset.AssetManager;
import com.jme3.asset.TextureKey;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.texture.Texture;

/**
 *
 * @author balanton
 */
public class Slope {
    private Material material;
    private final Box PLANE;

    Slope(AssetManager assetManager, Node rootNode, BulletAppState bulletAppState) {
        PLANE = new Box(15f, 0.1f, 10f);
        PLANE.scaleTextureCoordinates(new Vector2f(3, 6));
        
        initMaterials(assetManager);
        initPhysics(rootNode, bulletAppState);
    }

    public Box getPLANE() {
        return PLANE;
    }
    
    private void initMaterials(AssetManager assetManager) {
        material = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        TextureKey key = new TextureKey("Textures/floor.jpg");
        key.setGenerateMips(true);
        Texture texture = assetManager.loadTexture(key);
        texture.setWrap(Texture.WrapMode.Repeat);
        material.setTexture("ColorMap", texture);
    }

    private void initPhysics(Node rootNode, BulletAppState bulletAppState) {
        Geometry slopeGeometry = new Geometry("Floor", PLANE);
        slopeGeometry.setMaterial(material);
        Quaternion roll = new Quaternion();
        roll.fromAngleAxis(FastMath.PI/6, new Vector3f(0,0,1));
        slopeGeometry.setLocalRotation(roll);
        slopeGeometry.setLocalTranslation(22.95f, 7.5f, 0);
        rootNode.attachChild(slopeGeometry);

        RigidBodyControl floorRigidBodyControl = new RigidBodyControl(0.0f);
        slopeGeometry.addControl(floorRigidBodyControl);
        bulletAppState.getPhysicsSpace().add(floorRigidBodyControl);
    }
}
