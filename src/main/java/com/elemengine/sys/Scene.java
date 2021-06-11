package com.elemengine.sys;

import com.elemengine.debug.Log;
import com.elemengine.entity.Dynamic;
import com.elemengine.event.handler.EventCollector;
import com.elemengine.event.handler.annotations.OnEvent;
import com.elemengine.graphics.camera.Camera;
import com.elemengine.graphics.camera.Camera2D;
import com.elemengine.graphics.camera.OrthoProjectionSettings;
import com.elemengine.graphics.rendering.LayoutMap;
import com.elemengine.graphics.rendering.Renderer;
import com.elemengine.graphics.shaders.Shader;
import com.elemengine.graphics.shapes.Rectangle;
import com.elemengine.registry.Registry;
import org.joml.Vector2f;

public abstract class Scene implements Manageable
{
    protected final EventCollector eventCollector;
    protected Camera camera;
    protected Camera2D bgCamera;
    protected Camera2D guiCamera;
    protected Renderer bgRenderer;
    protected Renderer defaultRenderer;
    protected Renderer guiRenderer;
    private Application.Signal signal;
	private final LayoutMap layoutMap;

	public Scene()
    {
        eventCollector = new EventCollector(this);
        buildCamera();
        guiCamera = new Camera2D(new OrthoProjectionSettings(Window.WINDOW));
        bgCamera = new Camera2D(new OrthoProjectionSettings(Window.WINDOW));
        defaultRenderer = new Renderer(Shader.DEFAULT, camera);
        bgRenderer = new Renderer(Shader.DEFAULT, bgCamera);
        guiRenderer = new Renderer(Shader.DEFAULT, guiCamera);
        layoutMap = new LayoutMap();
    }



    protected abstract void buildCamera();

    public Camera getCamera()
    {
        return camera;
    }

    public Renderer getDefaultRenderer()
    {
        return defaultRenderer;
    }

    public void draw(Dynamic gobject)
    {
        gobject.draw(defaultRenderer);
    }

    public void draw(Renderer renderer, Dynamic gobject)
    {
        gobject.draw(renderer);
    }

    @OnEvent(OnEvent.Types.WINDOW_RESIZED)
    final public void windowResizedEventHandler(int width, int height)
    {
        Window.WINDOW.aspectRatioUpdateViewport(width, height);
    }

    public EventCollector getEventCollector()
    {
        return eventCollector;
    }

    public void setSignal(Application.Signal signal)
    {
        this.signal = signal;
    }

    public Application.Signal getSignal()
    {
        return signal;
    }

    public LayoutMap getLayoutMap()
    {
        return layoutMap;
    }

    public Camera2D getGuiCamera()
    {
        return guiCamera;
    }

    public Renderer getGuiRenderer()
    {
        return guiRenderer;
    }


    private void loadLayouts()
    {
        layoutMap.create("BACKGROUND", Integer.MIN_VALUE, bgRenderer);
        layoutMap.create("INSTANCES", 0, defaultRenderer);
        layoutMap.create("GUI", Integer.MAX_VALUE, guiRenderer);
    }

    public void innerLoad()
    {
        loadLayouts();
    }

    public void innerUpdate()
    {
        layoutMap.run();
    }

    public void innerRender()
    {
        layoutMap.draw();
    }

    public void innerClose()
    {
        layoutMap.destroy();
    }

    public void activeArrow()
    {
        Rectangle arrowLeft = new Rectangle(Registry.getTexture("arrow.png"));
        arrowLeft.setCenterOrigin();
        arrowLeft.setRotation(180);
        arrowLeft.getSize().mul(0.1f, 0.1f, 1f);
        arrowLeft.setCenterOrigin();
        arrowLeft.setTopLeftPosition(new Vector2f(10, 10));
        arrowLeft.events_c().onMouseLeftButtonReleased(sender -> getSignal().previous = true);

        Rectangle arrowRight = new Rectangle(Registry.getTexture("arrow.png"));
        arrowRight.getSize().mul(0.1f, 0.1f, 1f);
        arrowRight.setTopRightPosition(new Vector2f(getGuiCamera().getOrthoProjSettings().getRight() - 10, 10));
        arrowRight.events_c().onMouseLeftButtonReleased(sender -> getSignal().next = true);

        getLayoutMap().put("GUI", arrowLeft, arrowRight);
    }
}
