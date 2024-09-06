package bl.maths

class Vec3
{
	var x = 0f
	var y = 0f
	var z = 0f

	operator fun get (i:Int) = when (i)
	{
		0 -> x
		1 -> y
		2 -> z
		else -> throw IndexOutOfBoundsException()
	}

	operator fun set (i:Int, v:Float)
	{
		when (i)
		{
			0 -> x = v
			1 -> y = v
			2 -> z = v
			else -> throw IndexOutOfBoundsException()
		}
	}
}








