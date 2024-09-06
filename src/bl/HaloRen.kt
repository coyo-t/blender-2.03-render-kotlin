package bl

import bl.material.Material
import bl.maths.Vec3

class HaloRen
{
	var alfa = 0f
	var xs = 0f
	var ys = 0f
	var rad = 0f
	var radsq = 0f
	var sin = 0f
	var cos = 0f
	val co = Vec3()
	val no = Vec3()

	var zs = 0u
	var zd = 0u
	var zBufDist = 0u // depth in the z-buffer coordinate system */

	var miny = 0
	var maxy = 0;

	var hard = 0
	var b = 0
	var g = 0
	var r = 0

	var starpoints = 0
	var add = 0
	var type = 0
	var tex = 0

	var linec = 0
	var ringc = 0
	var seed = 0

	var flarec = 0
	var hasize = 0f
	var pixels = 0
	var lay = 0
	var mat:Material? = null
}
