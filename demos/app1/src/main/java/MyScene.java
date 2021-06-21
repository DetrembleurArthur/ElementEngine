import com.elemengine.graphics.rendering.SpriteSheet;
import com.elemengine.graphics.shapes.Rectangle;
import com.elemengine.registry.Registry;
import com.elemengine.sys.Scene2D;
import com.elemengine.sys.Window;
import com.elemengine.utils.Colors;


public class MyScene extends Scene2D
{
    Rectangle rectangle;
    SpriteSheet spriteSheet;

    @Override
    public void load()
    {
        spriteSheet = new SpriteSheet(Registry.getTexture("blob.png"), 2, 2, 3);

        rectangle = new Rectangle();
        rectangle.setFillColor(Colors.LIME);
        rectangle.setSize(150, 150);
        rectangle.setCenterOrigin();
        rectangle.setPosition(Window.WINDOW.get2DWorldCenter(getCamera2d()));
        rectangle.sprites_c().addSpriteSheet("blob-idle", spriteSheet);
        rectangle.sprites_c().setCurrent("blob-idle");
        rectangle.sprites_c().setSpeedAnimation(250);

        getLayoutMap().create("blob", 0, defaultRenderer).put("blob", rectangle);
    }

    @Override
    public void update(double v)
    {

    }

    @Override
    public void render(double v)
    {

    }

    @Override
    public void close()
    {

    }
}
