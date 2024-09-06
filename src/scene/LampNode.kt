package scene


class LampNode : Node()
{
	var colMode = 0
	var totTex = 0

	var r = 0f
	var g = 0f
	var b = 0f
	var k = 0f

	var energy = 0f
	var distance = 0f
	var spotSize = 0f
	var spotBlend = 0f

	var haint = 0f

	var att1 = 0f
	var att2 = 0f

	var bufsize = 0
	var samp = 0

	var clipsta = 0f
	var clipend = 0f

	var shadspotsize = 0f

	var bias = 0f
	var soft = 0f

	// texact is for buttons
	var texact = 0
	var shadhalostep = 0

	var negative = false

//	MTex *mtex[8];
//	Ipo *ipo;
//
//	ScriptLink scriptlink;

}