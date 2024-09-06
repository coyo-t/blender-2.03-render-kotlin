package bl

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