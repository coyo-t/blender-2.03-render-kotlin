package bl

import kotlin.math.max
import kotlin.math.min


// afbreek: abort

class RE
{

	var activeCamera: Camera? = null

	var R = Render()

	var rectz: UIntArray? = null

	fun initRender ()
	{
		val isPano = R.r.mode.contains(RenderData.Mode.Panorama)

		if (isPano)
		{
			if (R.r.mode.contains(RenderData.Mode.Borders))
				throw IllegalArgumentException("No border allowed for Panorama")

			if (R.r.yparts > 1)
				throw IllegalArgumentException("No Y-Parts allowed for Panorama")
		}

		if (R.r.xparts*R.r.yparts > 64)
		{
			throw IllegalArgumentException("No more than 64 parts")
		}



	}

	fun render ()
	{
		var part:RenderPart? = null

		if (rectz != null)
			rectz = null

		var fields = 1

		var parts = R.r.partCount

		if (RenderData.Mode.FieldRendering in R.r.mode)
		{
			fields = 2

		}
	}

	val allparts = Array(65) { IntArray(4) }

	fun initParts ()
	{
		val border = R.r.border
		val rectx = R.rectx
		val recty = R.recty

		val xminb:Int
		val yminb:Int
		val xmaxb:Int
		val ymaxb:Int


		val borderMode = RenderData.Mode.Borders in R.r.mode
		if (borderMode)
		{
			xminb = max(border.xmin * rectx, 0f).toInt()
			yminb = max(border.ymin * recty, 0f).toInt()
			xmaxb = min(border.xmax * rectx, rectx.toFloat()).toInt()
			ymaxb = min(border.ymax * recty, recty.toFloat()).toInt()
		}
		else
		{
			xminb = 0
			yminb = 0
			xmaxb = rectx
			ymaxb = recty
		}

		// voor border
		var xparts = R.r.xparts
		var yparts = R.r.yparts

		// array leegmaken
		for (nr in 0..<R.r.partCount)
		{
			allparts[nr][0] = -1
		}

		var xpart = rectx / xparts
		var ypart = recty / yparts

		if (borderMode)
		{
			// zoveel parts in border
			xparts = min(xparts, (xmaxb-xminb-1)/xpart+1)
			yparts = min(yparts, (ymaxb-yminb-1)/ypart+1)

			xpart = (xmaxb-xminb)/xparts
			ypart = (ymaxb-yminb)/yparts
		}

		val isPano = RenderData.Mode.Panorama in R.r.mode

		for (nr in 0..<xparts*yparts)
		{
			val p = allparts[nr]
			if (isPano)
			{
				p[0] = 0
				p[1] = 0
				p[2] = rectx
				p[3] = recty
			}
			else
			{
				val xd = nr % xparts
				val yd = (nr - xd) / xparts

				p[0] = xminb + xd * xpart
				p[1] = yminb + yd * ypart
				p[2] = if (xd < xparts - 1) p[0] + xpart else xmaxb
				p[3] = if (yd < yparts - 1) p[1] + ypart else ymaxb

				if (p[2] - p[0] <= 0) p[0] = -1
				if (p[3] - p[1] <= 0) p[0] = -1
			}
		}
	}

}


