package com.solar.system;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.solar.system.Constants;

import Models.Body;

public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Body sun;
	long lastTime, time;
	Stage stage;
	TextButton button;
	TextButton.TextButtonStyle textButtonStyle;
	BitmapFont font;
	Skin skin;
	TextureAtlas buttonAtlas;
	boolean paused = false;

	public long randomLong(){
		return 1000000000 + (long) (Math.random() * (2147483647 - 1000000000));
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		sun = new Body(new Vector2(Constants.centerX, Constants.centerY), "sun", 0);
		Body mercury = new Body("mercury", 70, sun, randomLong());
		Body venus = new Body("venus", 114, sun, randomLong());
		Body earth = new Body("earth", 158, sun, randomLong());
		Body mars = new Body("mars", 202, sun, randomLong());
		Body jupiter = new Body("jupiter", 246, sun, randomLong());
		Body saturn = new Body("saturn", 290,sun, randomLong());
		Body uranus = new Body("uranus", 336, sun, randomLong());
		Body neptune = new Body("neptune", 380, sun, randomLong());//44

		Body moon1 = new Body("moon1", 19, earth, randomLong());
		Body moon2 = new Body("moon2", 19, mars, randomLong());
		Body moon3 = new Body("moon3", 20, mars, randomLong());
		Body moon4 = new Body("moon4", 21, jupiter, randomLong());
		Body moon5 = new Body("moon5", 22, jupiter, randomLong());
		Body moon6 = new Body("moon6", 23, jupiter, randomLong());
		Body moon7 = new Body("moon7", 24, jupiter, randomLong());
		Body moon8 = new Body("moon8", 21, neptune, randomLong());
		Body moon9 = new Body("moon9", 23, neptune, randomLong());
		Body moon10 = new Body("moon10", 24, neptune, randomLong());
		Body moon11 = new Body("moon11", 25, neptune, randomLong());
		Body moon12 = new Body("moon1", 26, neptune, randomLong());
		lastTime = TimeUtils.millis();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		font = new BitmapFont();
		skin = new Skin();
		buttonAtlas = new TextureAtlas(Constants.uiPath + "button.atlas");
		skin.addRegions(buttonAtlas);
		textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.up = skin.getDrawable("button_up");
		textButtonStyle.down = skin.getDrawable("button_down");
		button = new TextButton("Button", textButtonStyle);
		button.addListener( new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y){
				paused=!paused;
			}
		});
		stage.addActor(button);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(22/255f, 22/255f, 91/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		long delta = TimeUtils.nanoTime() - lastTime;
		if (!paused)
		{
			time += delta;
			sun.draw(batch, time);
		}
		else{
			sun.draw(batch,time);
		}
		lastTime = TimeUtils.nanoTime();
		batch.end();
		stage.draw();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		sun.dispose();
	}
}
