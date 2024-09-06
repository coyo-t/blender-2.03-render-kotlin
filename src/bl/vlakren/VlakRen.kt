package bl.vlakren

import bl.*
import java.util.*
import kotlin.math.absoluteValue

class VlakRen
{
	var v1: VertRen? = null
	var v2: VertRen? = null
	var v3: VertRen? = null
	var v4: VertRen? = null
	val n = Vec3f()
	var len = 0f
	var mat: Material? = null
	var mface: MFace? = null
	var tface: TFace? = null
	var vcol:UInt? = null
	var snproj = 0
	var puno = 0
	var flag = EnumSet.noneOf(VlakRenFlag::class.java)
	var ec = 0
	var lay = 0

	fun setNormalFlags ()
	{
		if (flag.contains(VlakRenFlag.NoPointNormalFlip))
		{
			// render normaal flippen, wel niet zo netjes, maar anders
			// dan moet de render() ook over...
			n.x = -n.x
			n.y = -n.y
			n.z = -n.z
		}
		else
		{
			val vec_x = v1!!.co.x
			val vec_y = v1!!.co.y
			val vec_z = v1!!.co.z

			if ((vec_x * n.x + vec_y*n.y + vec_z * n.z) < 0)
			{
				puno = puno.inv()
				n.x = -n.x
				n.y = -n.y
				n.z = -n.z
			}
		}
		val xn = n.x.absoluteValue
		val yn = n.y.absoluteValue
		val zn = n.z.absoluteValue
		snproj = if (zn >= xn && zn >= yn)
			0
		else if (yn >= xn && yn >= zn)
			1
		else
			2
	}
}
