import game.jgengine.binding.FloatProperty;
import game.jgengine.binding.IntegerProperty;
import game.jgengine.binding.NumberProperty;
import game.jgengine.binding.Property;
import game.jgengine.debug.Logs;

import java.util.ArrayList;

public class ConsoleTest
{
	static ArrayList<Float> intList = new ArrayList<>();
	static FloatProperty ListSUM = new FloatProperty()
	{
		@Override
		protected void setValue(Float value)
		{
			intList.add(value);
		}

		@Override
		public Float getValue()
		{
			float sum = 0;
			for(var i : intList)
				sum += i;
			return sum;
		}
	};

	static float avg = 0;
	static FloatProperty Avg = new FloatProperty()
	{
		@Override
		protected void setValue(Float value)
		{
			avg = value;
		}

		@Override
		public Float getValue()
		{
			return avg;
		}
	};

	public static void main(String[] args)
	{
		ListSUM.bind(Avg, value -> value / intList.size());

		Logs.print(ListSUM + " => " + Avg);

		ListSUM.set(5f);
		ListSUM.set(20f);
		ListSUM.set(12f);
		ListSUM.set(3f);

		Logs.print(ListSUM + " => " + Avg);
	}
}
