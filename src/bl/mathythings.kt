package bl
fun f3array () = floatArrayOf(0f, 0f, 0f)
fun f4array () = floatArrayOf(0f, 0f, 0f, 0f)

fun mat3x3f () = Array(3) { f3array() }
fun mat4x4f () = Array(4) { f4array() }


class rctf
{
	var xmin = 0f
	var xmax = 0f
	var ymin = 0f
	var ymax = 0f
}