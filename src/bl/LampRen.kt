package bl

import bl.maths.Matrix3
import bl.maths.Vec3

class LampRen
{

	var xs = 0f
	var ys = 0f
	var dist = 0f

	val co = Vec3()
	var type = 0
	var mode = 0
	var r = 0f
	var g = 0f
	var b = 0f
	var energy = 0f
	var haint = 0f
	var lay = 0
	var spotsi = 0f
	var spotbl = 0f
	val vec = Vec3()
	var xsp = 0f
	var ysp = 0f
	var distkw = 0f
	var inpr = 0f
	var halokw = 0f
	var halo = 0f
	var ld1 = 0f
	var ld2 = 0f
	var shb:ShadBuf? = null
	val imat = Matrix3()
	var spottexfac = 0f

	// sh_ means spot halo
	val sh_invcampos = Vec3()
	var sh_zfac = 0f

	var org:LampRen? = null
//	MTex *mtex[8];
}