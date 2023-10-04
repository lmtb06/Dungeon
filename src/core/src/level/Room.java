package level;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import level.tiles.Floor;
import level.tiles.Tile;

public class Room {

    /**
     *  tableau en deux dimensions des cases
     */
    private Tile[][] tableauCases;

    /**
     * Listes de liens vers les autres Rooms
     */
    private Room[] portes;

    /**
     * Taille d'une tuile
     */
    private int tileSize;

    private TiledMap map;
    private OrthogonalTiledMapRenderer omr;

    private OrthographicCamera oc;


    public Room(){
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("maps/test2.tmx");

        omr = new OrthogonalTiledMapRenderer(map);
        oc = new OrthographicCamera();

        omr.setView(oc);




        /**this.tableauCases = new Tile[7][5];

        for (int i  = 0; i < this.tableauCases.length; i++){
            for (int j  = 0; j < this.tableauCases[i].length; j++){
                tableauCases[i][j] = new Floor();
            }
        }**/
    }

    public void render() {
        omr.render();
        /**for (int i  = 0; i < this.tableauCases.length; i++){
            for (int j  = 0; j < this.tableauCases[i].length; j++){
                tableauCases[i][j].render(batch,i,j);
            }
        }**/
    }
}
