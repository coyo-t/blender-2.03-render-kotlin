package bl

import common.maths.Vec3

class MTex {

	var texco = 0
	var mapto = 0
	var maptoneg = 0
	var blendtype = 0
	var `object`:Any? = null
	var tex: Tex? = null

	var projx = 0
	var projy = 0
	var projz = 0
	var mapping = 0
	val ofs = Vec3()
	val size = Vec3()

	var texflag = 0
	var colormodel = 0
	var r = 0f
	var g = 0f
	var b = 0f
	var k = 0f
	var def_var = 0f

	var colfac = 0f
	var norfac = 0f
	var varfac = 0f

}