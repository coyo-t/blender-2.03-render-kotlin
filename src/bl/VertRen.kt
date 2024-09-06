package bl

import bl.maths.Vec3

class VertRen
{
	val co = Vec3()
	val n = Vec3()
	val ho = f4array()
	var orco:FloatArray? = null
	var sticky:FloatArray? = null
	// smooth vert, only used during initrender
	var svert:Any? = null

	var clip:Short = 0
	// texofs= flag
	var texofs:Short = 0
}