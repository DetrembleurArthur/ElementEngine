import com.elemengine.exceptions.SysException;
import com.elemengine.graphics.loaders.TextureLoader;
import com.elemengine.sys.Application;
import com.elemengine.sys.Window;
import com.elemengine.utils.FileUtil;
import org.joml.Vector4f;

public class Main extends Application
{
    public static void main(String[] args) throws SysException
    {
        new Main().run();
    }

    @Override
    public void loadResources()
    {
        Window.WINDOW.setClearColor(new Vector4f(0, 0.8f, 0.8f, 1));
        Window.WINDOW.setSize(Window.getScreenSize().mul(0.9f));
        Window.WINDOW.maintainSizeRatio(false);
        Window.WINDOW.setResizeable(true);
        Window.WINDOW.center();

        TextureLoader.loadDir(FileUtil.getEngineFile("sprites/").getPath());
        setFramerateLimit(60);
        addScene("DEMO", new MyScene());
        setCurrentScene("DEMO");
    }
}
