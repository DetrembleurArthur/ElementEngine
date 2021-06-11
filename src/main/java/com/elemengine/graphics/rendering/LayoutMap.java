package com.elemengine.graphics.rendering;

import com.elemengine.debug.Log;
import com.elemengine.entity.Dynamic;
import com.elemengine.utils.LaterList;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LayoutMap implements Dynamic
{
    private final LaterList<Layout> layouts;

    public LayoutMap()
    {
        layouts = new LaterList<>();
    }

    private void sort()
    {
        Collections.sort(layouts);
    }

    public LayoutMap create(String layoutId, int priority, Renderer renderer)
    {
        layouts.addLater(new Layout(priority, layoutId, renderer));
        layouts.sync();
        sort();
        return this;
    }

    public LayoutMap reorder(String layoutId, int priority)
    {
        var layout = getLayout(layoutId);
        layout.setPriority(priority);
        sort();
        return this;
    }

    public LayoutMap swap(String layoutId1, String layoutId2)
    {
        var l1 = getLayout(layoutId1);
        var l2 = getLayout(layoutId2);
        int priority = l1.getPriority();
        l1.setPriority(l2.getPriority());
        l2.setPriority(priority);
        sort();
        return this;
    }

    public LayoutMap put(String layoutId, Dynamic entity)
    {
        getLayout(layoutId).addLater(entity);
        return this;
    }

    public LayoutMap put(String layoutId, Dynamic... entities)
    {
        getLayout(layoutId).addLater(List.of(entities));
        return this;
    }

    public LayoutMap put(String layoutId, ArrayList<Dynamic> entities)
    {
        getLayout(layoutId).addLater(entities);
        return this;
    }

    public Layout getLayout(String id)
    {
        final Layout[] layout = new Layout[1];
        layouts.forEach(dynamics -> {
            if (dynamics.equals(id))
                layout[0] = dynamics;
        });
        return layout[0];
    }

    public ArrayList<Layout> getLayouts()
    {
        return layouts;
    }

    public void removeLayout(String layoutId)
    {
        var layout = getLayout(layoutId);
        if(layout != null)
        {
            layout.destroy();
            layouts.removeLater(layout);
        }
    }

    public void removeDynamic(Dynamic dynamic, String layoutId)
    {
        getLayout(layoutId).removeLater(dynamic);
    }

    @Override
    public void destroy()
    {
        layouts.forEach(Layout::destroy);
        layouts.clear();
    }

    @Override
    public void draw()
    {
        layouts.forEach(dynamics -> dynamics.draw(dynamics.getRenderer()));
    }

    @Override
    public void draw(Renderer renderer)
    {
        layouts.forEach(dynamics -> dynamics.draw(renderer));
    }

    @Override
    public void run()
    {
        layouts.forEach(Layout::run);
        layouts.sync();
    }
}
