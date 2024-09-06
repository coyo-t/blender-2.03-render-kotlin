package bl

import common.maths.rctf
import java.util.*

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

	val partCount get() = xparts * yparts
}