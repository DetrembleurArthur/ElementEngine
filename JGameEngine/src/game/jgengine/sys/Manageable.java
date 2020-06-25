package game.jgengine.sys;

public interface Manageable
{
	void load();
	void update(double dt);
	void render(double dt);
	void close();
}
