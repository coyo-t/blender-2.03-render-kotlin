package bl

class RenderPart
{

	var prev:RenderPart? = null
	var next:RenderPart? = null

	val rect = UIntArray(4)

	var x = 0
	var y = 0
}