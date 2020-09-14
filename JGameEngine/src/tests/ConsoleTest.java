package tests;

import game.mysql.Mysql;

public class ConsoleTest
{
	public static void main(String[] args)
	{
		Mysql.connect("sakila", "13Mysql53");

		Mysql.disconnect();
	}
}
