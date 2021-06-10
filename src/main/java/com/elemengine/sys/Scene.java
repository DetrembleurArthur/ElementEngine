package com.elemengine.sys;

import com.elemengine.entity.Dynamic;
import com.elemengine.event.handler.EventCollector;
import com.elemengine.event.handler.annotations.OnEvent;
import com.elemengine.graphics.camera.Camera;
import com.elemengine.graphics.rendering.LayoutMap;
import com.elemengine.graphics.rendering.Renderer;
import com.elemengine.graphics.shaders.Shader;

public abstract class Scene implements Manageable, ResourcesManageable
{
    protected final EventCollector eventCollector;
    protected Camera camera;
    protected Renderer defaultRenderer;
    private Application.Signal signal;
	private final LayoutMap layoutMap;

	public Scene()
    {
        eventCollector = new EventCollector(this);
        buildCamera();
        defaultRenderer = new Renderer(Shader.DEFAULT, camera);
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
}
