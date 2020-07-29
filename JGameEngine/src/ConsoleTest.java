import game.jgengine.binding.Listener;
import game.jgengine.binding.NotifyProperty;
import game.jgengine.binding.Property;

public class ConsoleTest
{
	public static class MyObject
	{
		private int number;
		private int numberf;

		public MyObject(int n, int nf)
		{
			number = n;
			numberf = nf;
		}

		public int getNumber()
		{
			return number;
		}

		public void setNumber(int number)
		{
			this.number = number;
		}

		public int getNumberf()
		{
			return numberf;
		}

		public void setNumberf(int numberf)
		{
			this.numberf = numberf;
		}

		@Override
		public String toString()
		{
			return number + " : " + numberf;
		}
	}

	public static void main(String[] args)
	{
		NotifyProperty<MyObject> obj1 = new NotifyProperty<>(new MyObject(0, 0));
		NotifyProperty<MyObject> obj = new NotifyProperty<>(new MyObject(0, 0));

		obj.bindIntField("number", obj1, "number", a -> a*5);

		System.out.println(obj.get() + " => " + obj1.get());

		obj1.setFieldByName("number", 3);

		System.out.println(obj.get() + " => " + obj1.get());
	}
}
