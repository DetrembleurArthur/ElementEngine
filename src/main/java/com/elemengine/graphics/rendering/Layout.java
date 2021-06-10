package com.elemengine.graphics.rendering;

import com.elemengine.utils.DynamicLaterList;

public class Layout extends DynamicLaterList implements Comparable<Layout>
{
    private int priority;
    private boolean visible;
    private final String id;

    public Layout(int priority, String id)
    {
        this.priority = priority;
        visible = true;
        this.id = id;
    }

    public int getPriority()
    {
        return priority;
    }

    public void setPriority(int priority)
    {
        this.priority = priority;
    }

    public String getId()
    {
        return id;
    }

    public boolean isVisible()
    {
        return visible;
    }

    public void setVisible(boolean visible)
    {
        this.visible = visible;
    }

    public boolean equals(String id)
    {
        return id.equals(this.id);
    }

    @Override
    public int compareTo(Layout o)
    {
        return Integer.compare(this.priority, o.getPriority());
    }
}
