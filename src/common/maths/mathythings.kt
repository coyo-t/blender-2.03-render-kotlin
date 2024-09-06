package common.maths

import kotlin.math.max
import kotlin.math.min

fun f3array () = floatArrayOf(0f, 0f, 0f)
fun f4array () = floatArrayOf(0f, 0f, 0f, 0f)

fun mat3x3f () = Array(3) { f3array() }
fun mat4x4f () = Array(4) { f4array() }



fun clamp (x:Int, minv:Int, maxv:Int)
	= max(minv, min(x, maxv))

fun clamp (x:Float, minv:Float, maxv:Float)
	= max(minv, min(x, maxv))


