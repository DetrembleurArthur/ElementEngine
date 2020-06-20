package game.jgengine.debug;

import java.sql.Date;
import java.time.Instant;
import java.util.Calendar;

public class Logs
{
	public static <T> void print(T message)
	{
		System.err.println("[DEBUG] "+Calendar.getInstance().getTime() + " >> " + message);
	}
}
