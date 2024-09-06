package bl.material

import bl.MTex
import java.util.*

class Material
{
//	ID id;

	// lay: voor dynamics
	var colormodel = 0
	var lay = 1
	var r = 0.8f
	var g = 0.8f
	var b = 0.8f
	var specr = 1f
	var specg = 1f
	var specb = 1f
	var mirr = 1f
	var mirg = 1f
	var mirb = 1f
	var ambr = 0f
	var ambb = 0f
	var ambg = 0f

	var amb = 0.5f
	var emit = 0f
	var ang = 0f
	var spectra = 0f
	var alpha = 1f
	var ref = 0.8f
	var spec = 0.5f
	var zoffs = 0f
	var add = 0f
	var kfac = 0f // for transparent solids
	var har = 0
	var seed1 = 0
	var seed2 = 0

	var mode = EnumSet.of(
		MaterialMode.Traceable,
		MaterialMode.Shadow,
	)
	var mode2 = 0 // even more material settings :)
	var flarec = 1
	var starc = 4
	var linec = 12
	var ringc = 4

	// haloSize
	var hasize = 0.5f


	var flaresize = 1f
	var subsize = 1f
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