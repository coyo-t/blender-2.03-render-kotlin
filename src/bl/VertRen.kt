package bl

class VertRen
{
	val co = Vec3f()
	val n = Vec3f()
	val ho = f4array()
	var orco:FloatArray? = null
	var sticky:FloatArray? = null
	// smooth vert, only used during initrender
	var svert:Any? = null

	var clip:Short = 0
	// texofs= flag
	var texofs:Short = 0
}