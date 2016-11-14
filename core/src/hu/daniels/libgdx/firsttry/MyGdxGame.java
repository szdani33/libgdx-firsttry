package hu.daniels.libgdx.firsttry;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	private long prev = System.currentTimeMillis();
    int counter = 0;
    double frameRateSum = 0;

	@Override
	public void render () {

        Gdx.graphics.getGL20().glClearColor(0.625f, 0.75f, 0.9375f, 1f);
        Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT);

        long act = System.currentTimeMillis();
        if(counter < 60) {
            counter++;
            frameRateSum += 1000d / (act - prev);
        } else {
            Double averageFrameRate = frameRateSum / counter;
            System.out.println("average speed: " + averageFrameRate.intValue() + " FPS");
            counter = 0;
            frameRateSum = 0;
        }
		prev = act;
	}
}
