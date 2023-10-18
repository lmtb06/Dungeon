package com.dungeondevs.dungeongame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
;
import com.dungeondevs.dungeongame.tiled_elements.Room;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	/**
	 * Il n'y a pour le moment qu'une salle à afficher, le donjon et les autres salles viendront après
	 */
	private Room salle;


	@Override
	public void create () {

		batch = new SpriteBatch();
		//this.salle = new Room();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		//batch.draw(img, 0, 0);
		salle.render();
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
