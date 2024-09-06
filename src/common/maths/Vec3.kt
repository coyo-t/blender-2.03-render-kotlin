package common.maths

import kotlin.math.sqrt

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

	fun normalize ():Float
	{
		var d = x * x + y * y + z * z

		// FLT_EPSILON is too large! A larger value causes normalise
		// errors in a scaled down utah teapot
		if (d > 0.0000000000001)
		{
			val fd = sqrt(d)
			d = 1 / fd
			x *= d
			y *= d
			z *= d
			return fd
		}
		else
		{
			x = 0f
			y = 0f
			z = 0f
			return 0f
		}
	}
}








