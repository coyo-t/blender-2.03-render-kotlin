package bl

import common.maths.Matrix4
import common.maths.Vec3

class ShadBuf
{

	var samp = 0
	var shadhalostep = 0

	val persmat = Matrix4()
	val viewmat = Matrix4()
	val winmat = Matrix4()
	val jit = Array(25) { FloatArray(2) }
	var d = 0f
	var far = 0f
	var pixsize = 0f
	var soft = 0f
	val co = Vec3()
	var size = 0
	var bias = 0
	var zbuf: ULongArray? = null
	var cbuf:UByteArray? = null

}