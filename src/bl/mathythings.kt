package bl
fun f3array () = floatArrayOf(0f, 0f, 0f)
fun f4array () = floatArrayOf(0f, 0f, 0f, 0f)

fun mat3x3f () = Array(3) { f3array() }
fun mat4x4f () = Array(4) { f4array() }



class Vec3f
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

class rctf
{
	var xmin = 0f
	var xmax = 0f
	var ymin = 0f
	var ymax = 0f
}