package com.elemengine.graphics.texts;

public class FontSet
{
	private Font[] fonts;
	public static final int NORMAL = 0;
	public static final int ITALIC = 1;
	public static final int BOLD = 2;
	public static final int ITALIC_BOLD = 3;

	public FontSet(String path, String fontFilesExpression)
	{
		fonts = new Font[4];
		var splitted = fontFilesExpression.split(";");
		for(var font : splitted)
		{
			Font tmp = new Font(path + "/" + font);
			fonts[tmp.fontType()] = tmp;
		}
	}

	public Font get(int type)
	{
		return fonts[type] == null ? fonts[NORMAL] : fonts[type];
	}
}
