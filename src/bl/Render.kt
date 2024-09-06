package bl

import bl.vlakren.VlakRen

class Render
{
	val co = Vec3f()

	val lo = Vec3f()
	val gl = Vec3f()
	val uv = Vec3f()
	val ref = Vec3f()
	val orn = Vec3f()
	val winco = Vec3f()
	val sticky = Vec3f()
	val vcol = Vec3f()

	var itot = 0f
	var i = 0f
	var ic = 0f
	var rgb = 0f
	var norm = 0f

	val vn = Vec3f()
	val view = Vec3f()
	var vno: Vec3f? = null
	val refcol = f4array()

	val grvec = Vec3f()
	var inprz = 0f
	var inprh = 0f

	val imat = mat3x3f()

	val viewmat = mat4x4f()
	val viewinv = mat4x4f()
	val persmat = mat4x4f()
	val persinv = mat4x4f()
	val winmat = mat4x4f()

	var flag = 0
	var osatex = 0
	var osa = 0
	var rt = 0

	// Screen sizes and positions, in pixels
	var xstart = 0
	var xend = 0
	var ystart = 0
	var yend = 0
	var afmx = 0
	var afmy = 0
	var rectx = 0 // Picture width - 1, normally xend - xstart.
	var recty = 0 // picture height - 1, normally yend - ystart.

	// Distances and sizes in world coordinates
	var near = 0f // near clip distance
	var far = 0f // far clip distance
	var ycor = 0f
	var zcor = 0f
	var pixsize = 0f
	var viewfac = 0f

	var r = RenderData()
	var wrld = World()
	var parts = ListBase()

	var totvlak = 0
	var totvert = 0
	var tothalo = 0
	var totlamp = 0

	val vlr = mutableListOf<VlakRen>()
	var vlaknr = 0

	val mat = mutableListOf<Material>()
	val matren = mutableListOf<Material>()

//	VlakRen **blovl;
//	bl.VertRen **blove;
//	HaloRen **bloha;

	var rectaccu: UIntArray? = null
	//* z buffer: distance buffer
	var rectz: UIntArray? = null
	var rectf1: UIntArray? = null
	var rectf2: UIntArray? = null

	// z buffer: face index buffer, recycled as colour buffer! */
	var rectot:UIntArray? = null
	var rectspare:UIntArray? = null

	// for 8 byte systems!
	var rectdaps:Long? = null

	var win = 0
	var winpos = 0
	var winx = 0
	var winy = 0
	var winxof = 0
	var winyof = 0
	var winpop = 0
	var displaymode = 0
	var sparex = 0
	var sparey = 0

}