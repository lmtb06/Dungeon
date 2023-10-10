package level;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;

public class Level {
    private World world;
    private OrthographicCamera camera;

    public Level(OrthographicCamera camera) {
        this.camera = camera;
    }

    public void update(float delta) {
        camera.update();
    }

    public void render() {
    }
}
