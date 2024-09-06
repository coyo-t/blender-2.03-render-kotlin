import java.util.*


class ListBase
{
	var first:Any? = null
	var last:Any? = null
}

class Tex
{
//	ID id;

	var noisesize = 0f
	var turbul = 0f
	var bright = 0f
	var contrast = 0f
	var rfac = 0f
	var gfac = 0f
	var bfac = 0f
	var filtersize = 0f
	var noisedepth = 0
	var noisetype = 0

	var imaflag = 0
	var flag = 0
	var type = 0
	var stype = 0

	var cropxmin = 0f
	var cropymin = 0f
	var cropxmax = 0f
	var cropymax = 0f
	var xrepeat = 0
	var yrepeat = 0
	var extend = 0
	var len = 0
	var frames = 0
	var offset = 0
	var sfra = 0
	var fie_ima = 0
	var norfac = 0f
	var nor:FloatArray? = null

//	Ipo *ipo;
//	Image *ima;
//	PluginTex *plugin;
//	ColorBand *coba;
//	EnvMap *env;

//	short fradur[4][2];

}

class MTex {

	var texco = 0
	var mapto = 0
	var maptoneg = 0
	var blendtype = 0
	var `object`:Any? = null
	var tex:Tex? = null

	var projx = 0
	var projy = 0
	var projz = 0
	var mapping = 0
	val ofs = Vec3f()
	val size = Vec3f()

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

class Material
{
//	ID id;

	// lay: voor dynamics
	var colormodel = 0
	var lay = 0
	var r = 0f
	var g = 0f
	var b = 0f
	var specr = 0f
	var specg = 0f
	var specb = 0f
	var mirr = 0f
	var mirg = 0f
	var mirb = 0f
	var ambr = 0f
	var ambb = 0f
	var ambg = 0f

	var amb = 0f
	var emit = 0f
	var ang = 0f
	var spectra = 0f
	var alpha = 0f
	var ref = 0f
	var spec = 0f
	var zoffs = 0f
	var add = 0f
	var kfac = 0f // for transparent solids
	var har = 0
	var seed1 = 0
	var seed2 = 0

	var mode = 0
	var mode2 = 0 // even more material settings :)
	var flarec = 0
	var starc = 0
	var linec = 0
	var ringc = 0;
	var hasize = 0f
	var flaresize = 0f
	var subsize = 0f
	var flareboost = 0f

	// onderstaand is voor buttons en render
	var rgbsel = 0
	var texact = 0
	var pr_type = 0
	var septex = 0
	var pr_back = 0
	var pr_lamp = 0

	var pad1 = 0
	var texco = 0
	var mapto = 0

	var mtex = arrayOfNulls<MTex>(8)
//	Ipo *ipo;
	var ren:Material? = null

	// dynamic properties
	var friction = 0f
	var fh = 0f
	var reflect = 0f
	var fhdist = 0f
	var xyfrict = 0f
	var dynamode = 0
	var pad = 0

//	ScriptLink scriptlink;
}

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

class MFace
{
	var v1 = 0
	var v2 = 0
	var v3 = 0
	var v4 = 0
	var puno = 0
	var mat_nr = 0
	var edcode = 0
	var flag = 0
}

class TFace
{
	// als je dit wijzigt: ook fie set_correct_uv editmesh.c,
	// ook andere plekken maken gebruik van de lengte van dit blok
	var uv = Array(4) { FloatArray(2) }
	var col = UIntArray(4)
	var no = IntArray(3)
	var flag = 0
	var transp = 0
	var mode = 0
	var tile = 0

	var pad = 0


	var tpage:Any? = null
	var clut:Any? = null

}

class VlakRen
{
	var v1:VertRen? = null
	var v2:VertRen? = null
	var v3:VertRen? = null
	var v4:VertRen? = null
	val n = Vec3f()
	var len = 0f
	var mat:Material? = null
	var mface:MFace? = null
	var tface:TFace? = null
	var vcol:UInt? = null
	var snproj = 0
	var puno = 0
	var flag = 0
	var ec = 0
	var lay = 0
}


class World
{
//	ID id;

	var colormodel = 0
	var totex = 0
	var texact = 0
	var mistype = 0


	var horr = 0f
	var horg = 0f
	var horb = 0f
	var hork = 0f

	var zenr = 0f
	var zeng = 0f
	var zenb = 0f
	var zenk = 0f

	var ambr = 0f
	var ambg = 0f
	var ambb = 0f
	var ambk = 0f


	var fastcol = 0u
	var exposure = 0f
	var gravity = 0f
	var skytype:Short = 0
	var mode:Short = 0

	var misi = 0f
	var miststa = 0f
	var mistdist = 0f
	var misthi = 0f

	var starr = 0f
	var starg = 0f
	var starb = 0f
	var stark = 0f
	var starsize = 0f
	var starmindist = 0f
	var stardist = 0f
	var starcolnoise = 0f

	var dofsta:Short = 0
	var dofend:Short = 0
	var dofmin:Short = 0
	var dofmax:Short = 0

//	Ipo *ipo;
//	MTex *mtex[8];
//
//	ScriptLink scriptlink;
}

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
	var vno:Vec3f? = null
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
//	VertRen **blove;
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

class RenderData
{
	//* hoe gaat tijd gedefinieerd worden?
	var cfra = 0
	var sfra = 0
	var efra = 0 // plaatjes
	var images = 0
	var framapto = 0
	var flag = 0

	var ctime = 0f // hiermee rekenen?
	var framelen = 0f
	var blurfac = 0f

	var size = 0
	var maximsize = 0	// size in %, max in Kb

	// uit buttons

	// The desired number of pixels in the x, y direction
	var xsch = 0
	var ysch = 0

	// Adjustment factors for the aspect ratio
	var xasp = 0
	var yasp = 0

	// How many parts to use in x, y
	var xparts = 0
	var yparts = 0

	val safety = rctf()
	val border = rctf()

	var winpos = 0
	var planes = 0
	var imtype = 0
	var bufflag = 0
	var quality = 0
	var scemode = 0

	enum class Mode
	{
		DoOversample,
		DoShadow,
		Gamma,
		EdgeShading,
		FieldRendering,
		// Disables time difference in field calculations
		DisableFieldTimeDifference,
		Borders,
		Panorama,
		Crop,
		// odd field first rendering
		OddFieldFirst,
		MotionBlur,
	}

	// just a small aside: the short
	// that stores 'mode' is used as bitfield vector. Currently, up to 12
	// bits are used. this may lead to problems on platforms with 8 bit
	// shorts... needs to migrate to bitfields...
	val mode = EnumSet.noneOf(Mode::class.java)

	var alphamode = 0
	var dogamma = 0
	var osa = 0

	var frs_sec = 0
	var edgeint = 0

//	char backbuf[160], pic[160], ftype[160], movie[160]
	var backbuf = ""
	var pic = ""
	var ftype = ""
	var movie = ""


}



fun main ()
{
	println("Hello World!")
}
