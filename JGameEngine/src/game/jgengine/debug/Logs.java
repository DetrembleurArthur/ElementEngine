package game.jgengine.debug;

import java.sql.Date;
import java.time.Instant;

public class Logs
{
	public static <T> void print(T message)
	{
		System.err.println("[DEBUG] "+Date.from(Instant.now()).toString() + " >> " + message);
	}
}
