package hu.daniels.libgdx.firsttry;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class MyGdxGame extends ApplicationAdapter {
//    private static final int DIAMETER = 100;

    private long prev = System.currentTimeMillis();
    private int frameCounter;
    private double frameRateSum;
    private int counter;
    private Double averageFrameRate = 0d;

    private SpriteBatch batch;
    private Texture img;
    private BitmapFont font;

    @Override
    public void create () {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        font = new BitmapFont();
        font.setColor(Color.GREEN);
    }

	@Override
	public void render () {
        Gdx.graphics.getGL20().glClearColor(0.625f, 0.75f, 0.9375f, 1f);
        Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT);

        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        float diffX = (width / 2 - img.getWidth() / 2 - 15) * MathUtils.sin(MathUtils.PI * counter / 180);
        float diffY = (height / 2 - img.getHeight() / 2 - 15) * MathUtils.cos(MathUtils.PI * counter / 180);
        float x = width / 2 - img.getWidth() / 2 + diffX ;
        float y = height / 2 - img.getHeight() / 2 + diffY;
        counter++;

        long act = System.currentTimeMillis();
        if(frameCounter < 30) {
            frameCounter++;
            frameRateSum += 1000d / (act - prev);
        } else {
            averageFrameRate = frameRateSum / frameCounter;
            frameCounter = 0;
            frameRateSum = 0;
        }
		prev = act;

        batch.begin();
        batch.draw(img, x, y);
        font.draw(batch, "FPS: " + averageFrameRate.intValue(), 10, 30);
        batch.end();
	}

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        font.dispose();
    }
}
