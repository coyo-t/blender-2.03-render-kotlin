package bl

import bl.lamp.LampRen
import scene.LampNode
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
		// for border
		var xparts = R.r.xparts
		var yparts = R.r.yparts

		// array leegmaken
		// clear array
		for (nr in 0..<R.r.partCount)
		{
			allparts[nr][0] = -1
		}

		var xpart = rectx / xparts
		var ypart = recty / yparts

		if (borderMode)
		{
			// zoveel parts in border
			// "So many parts in border"??
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

	// return 0 (false) als geen goede part
	// return false if its a bad part
	fun setpart (nr: Int): Boolean
	{
		val pr = allparts[nr]
		if (pr[0] == -1)
			return false

		R.xstart = pr[0] - R.afmx
		R.ystart = pr[1] - R.afmy
		R.xend = pr[2] - R.afmx
		R.yend = pr[3] - R.afmy
		R.rectx = R.xend - R.xstart
		R.recty = R.yend - R.ystart

		return true
	}

	// TODO: has this been ported correctly?
	fun addparttorect (nr:Int, part:RenderPart)
	{
		// de juiste offset in rectot
		val pr = allparts[nr]
		val rectx = R.rectx
		var rt = pr[1] * rectx + pr[0]
		val prect = part.rect
		var rp = 0
		val len = pr[2]- pr[0]
		val heigth = pr[3]- pr[1]

		for (y in 0..<heigth)
		{
			prect.copyInto(R.rectot!!, rt, 0, len)
			rt += rectx
			rp += len
		}
	}

	fun addLamp (lamp:LampNode)
	{
		val lar = LampRen()
		R.la += lar




	}

}


