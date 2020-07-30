import game.jgengine.binding.Property;

public class ConsoleTest
{
	public static class MyObject
	{
		private int myNumber;
		private String name;

		public Property<Integer> MyNumber = new Property<>()
		{
			@Override
			public Integer getValue()
			{
				return myNumber;
			}

			@Override
			public void setValue(Integer value)
			{
				myNumber = value;
			}
		};

		public Property<String> Name = new Property<String>()
		{
			@Override
			protected void setValue(String value)
			{
				name = value;
			}

			@Override
			public String getValue()
			{
				return name;
			}
		};

		public MyObject(int n, String na)
		{
			myNumber = n;
			name = na;
		}


		@Override
		public String toString()
		{
			return Integer.toString(myNumber) + " === " + name;
		}
	}

	public static void main(String[] args)
	{
		MyObject o = new MyObject(0, "");
		MyObject oo = new MyObject(0, "");

		o.MyNumber.bind(oo.Name, n -> "[" + n + "]");

		System.out.println(o + " " + oo);

		o.MyNumber.set(777);

		System.out.println(o + " " + oo);
	}
}
