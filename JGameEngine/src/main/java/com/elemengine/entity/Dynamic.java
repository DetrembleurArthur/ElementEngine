package com.elemengine.entity;

public interface Dynamic extends Runnable, Drawable, Destroyable
{
	@Override
	default void run() {}
}
