package bl

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
	var ren: Material? = null

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