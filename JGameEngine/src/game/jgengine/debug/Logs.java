package game.jgengine.debug;

import java.sql.Date;
import java.time.Instant;

public class Logs
{
	public static void print(String message)
	{
		System.err.println(Date.from(Instant.now()).toString() + " >> " + message);
	}
}
