package bl

import kotlin.math.floor
import kotlin.math.min


//#region types

// Threshold for a 'full' pixel: pixels with alpha above this level are
// considered opaque This is the decimal value for 0xFFF0 / 0xFFFF
const val RE_FULL_COLOUR_FLOAT = 0.9998f
// Threshold for an 'empty' pixel: pixels with alpha above this level are
// considered completely transparent. This is the decimal value
// for 0x000F / 0xFFFF
const val RE_EMPTY_COLOUR_FLOAT = 0.0002f
// A 100% pixel. Sometimes, seems to be too little.... Hm.......
const val RE_UNITY_COLOUR_FLOAT = 1.0f
// A 0% pixel. I wonder how 0 the 0.0 is...
const val RE_ZERO_COLOUR_FLOAT = 0.0f

// threshold for alpha
const val RE_FULL_ALPHA_FLOAT = 0.9998f

// Same set of defines for shorts
const val RE_FULL_COLOUR_SHORT = 0xFFF0
const val RE_EMPTY_COLOUR_SHORT = 0x0000

// Default gamma. For most CRTs, gamma ranges from 2.2 to 2.5 (Foley), so
// 2.35 seems appropriate enough. Experience teaches a different number
// though. Old blender: 2.0. It  might be nice to make this a slider
const val RE_DEFAULT_GAMMA = 2.0f

// This 400 is sort of based on the number of intensity levels needed for
// the typical dynamic range of a medium, in this case CRTs. (Foley)
// (Actually, it says the number should be between 400 and 535.)
const val RE_GAMMA_TABLE_SIZE = 400

var RE_FLOAT_COLOUR_CLIPPING = false
var RE_ALPHA_CLIPPING = false

private inline fun tryClipFCol (flag: Boolean, c: Float, maxv:Float= RE_FULL_COLOUR_FLOAT) =
	if (flag && c >= RE_FULL_COLOUR_FLOAT) maxv
	else c

class FloatCol (
	var r:Float,
	var g:Float,
	var b:Float,
	var a:Float,
)
{
	fun copyTo (dst: FloatCol)
	{
		dst.r = r
		dst.g = g
		dst.b = b
		dst.a = a
	}
}

class IntCol (
	var r:UInt,
	var g:UInt,
	var b:UInt,
	var a:UInt,
)

class ShortCol (
	var r:UShort,
	var g:UShort,
	var b:UShort,
	var a:UShort,
)
{
	fun copyTo (dst: ShortCol)
	{
		dst.r = r
		dst.g = g
		dst.b = b
		dst.a = a
	}
}

class ByteCol (
	var r:UByte,
	var g:UByte,
	var b:UByte,
	var a:UByte,
)
{
	fun copyTo (dst: ByteCol)
	{
		dst.r = r
		dst.g = g
		dst.b = b
		dst.a = a
	}
}


//#endregion


/**
 * Samples pixel, bring your own R.osa setting
 */
fun addToSampCol (sampcol:Array<ShortCol>, shortcol:Array<ShortCol>, mask:Int, osaNr:Int):Int
{
	var retval = osaNr

	var a = 0
	var i = 0
	while (a < osaNr)
	{
		val sa = sampcol[i]
		if ((mask and (1 shl a)) == 0)
			addAlphaUnderShort(sa, shortcol[i])
		if (sa.a > 0xFFF0u)
			retval--
		i++
		a++
	}
	return retval
}



/**
 * Halo-add pixel, bring your own R.osa setting, and add factor
 */
fun addAddSampColF (
	sampvec:Array<FloatCol>,
	source:Array<FloatCol>,
	mask:Int,
	osaNr:Int,
	addfac:UByte)
{
	var a = 0
	var i = 0
	while (a < osaNr)
	{
		if ((mask and (1 shl a)) != 0)
			addalphaAddfacFloat(sampvec[i], source[i], addfac)
		i++
		a++
	}
}


/**
 * Alpha undersamples pixel, bring your own R.osa setting
 */
fun addUnderSampColF (
	sampvec:Array<FloatCol>,
	source:Array<FloatCol>,
	mask:Int,
	osaNr:Int,
):Int
{
	var retval = osaNr

	var a = 0
	var i = 0
	while (a < osaNr)
	{
		val sa = sampvec[i]
		if ((mask and (1 shl a)) != 0)
			addAlphaUnderFloat(sa, source[i])
		if (sa.a > RE_FULL_COLOUR_FLOAT)
			retval--
		a++
		i++
	}

	return retval
}

/**
 * Alpha oversample pixel, bring your own R.osa setting
 */
fun addOverSampColF (
	sampvec:Array<FloatCol>,
	source:Array<FloatCol>,
	mask:Int,
	osaNr:Int)
{

	var a = 0
	var i = 0
	while (a < osaNr)
	{
		if ((mask and (1 shl a)) != 0)
			addAlphaOverFloat(sampvec[i], source[i])
		a++
		i++
	}
}

/**
 * Convert a series of oversampled pixels into a single pixel.
 * (float vecs to float vec)
 */
fun sampleFloatColV2FloatColV(sample:Array<FloatCol>, dest:Array<FloatCol>, osaNr:Int)
{
	TODO()
}

/**
 * Convert a series of oversampled pixels into a single pixel. Uses R.osa to
 * count the length! (short vecs to short vec)
 */
fun sampleShortColV2ShortColV(sample:Array<ShortCol>, dest:Array<ShortCol>, osaNr:Int)
{
	TODO()
//	uint intcol [4] = { 0 };
//	var scol = sample
//	var a = 0
//
//	for (a= 0; a < osaNr; a++, scol += 4)
//	{
//		intcol[0] += scol[0];
//		intcol[1] += scol[1];
//		intcol[2] += scol[2];
//		intcol[3] += scol[3];
//	}
//
//	/* Now normalise the integrated colour. It is guaranteed */
//	/* to be correctly bounded.                              */
//	dest[0] = intcol[0] / osaNr;
//	dest[1] = intcol[1] / osaNr;
//	dest[2] = intcol[2] / osaNr;
//	dest[3] = intcol[3] / osaNr;
}

/**
 * Take colour <bron>, and apply it to <doel> using the alpha value of
 * <bron>.
 */
fun addAlphaOverShort (doel: ShortCol, bron: ShortCol)
{
	if (doel.a.toUInt()==0u || bron.a>=0xFFF0u)
	{
		// is getest, scheelt veel
		bron.copyTo(doel)
		return
	}

	val mul = 0xFFFFu-bron.a

	doel.r = min(((mul*doel.r) shr 16)+bron.r, 0xFFF0u).toUShort()
	doel.g = min(((mul*doel.g) shr 16)+bron.g, 0xFFF0u).toUShort()
	doel.b = min(((mul*doel.b) shr 16)+bron.b, 0xFFF0u).toUShort()
	doel.a = min(((mul*doel.a) shr 16)+bron.a, 0xFFF0u).toUShort()
}

/**
 * Take colour <bron>, and apply it to <doel> using the alpha value of
 * <doel>.
 *
 * vult bron onder doel in met alpha van doel
 */
fun addAlphaUnderShort(doel: ShortCol, bron: ShortCol)
{

	if(doel.a >= 0xFFF0u)
		return

	// is getest, scheelt veel
	if (doel.a.toUInt() == 0u)
	{
		bron.copyTo(doel)
		return
	}

	val mul = 0xFFFFu-doel.a

	doel.r = min(((mul*bron.r) shr 16)+doel.r, 0xFFF0u).toUShort()
	doel.g = min(((mul*bron.g) shr 16)+doel.g, 0xFFF0u).toUShort()
	doel.b = min(((mul*bron.b) shr 16)+doel.b, 0xFFF0u).toUShort()
	doel.a = min(((mul*bron.a) shr 16)+doel.a, 0xFFF0u).toUShort()

}


/**
 * Alpha-over blending for floats.
 */
fun addAlphaOverFloat (dest: FloatCol, source: FloatCol)
{
	/* d = s + (1-alpha_s)d*/

	/* I may want to disable this clipping */
	if (RE_FLOAT_COLOUR_CLIPPING)
	{
		if (source.a > RE_FULL_COLOUR_FLOAT)
		{
			// is getest, scheelt veel
			dest.r = source.r
			dest.g = source.g
			dest.b = source.b
			dest.a = source.a
			return
		}
	}

	val mul = 1 - source.a

	dest.r = tryClipFCol(RE_FLOAT_COLOUR_CLIPPING, (mul * dest.r) + source.r)
	dest.g = tryClipFCol(RE_FLOAT_COLOUR_CLIPPING, (mul * dest.g) + source.g)
	dest.b = tryClipFCol(RE_FLOAT_COLOUR_CLIPPING, (mul * dest.b) + source.b)
	dest.a = tryClipFCol(RE_ALPHA_CLIPPING, (mul * dest.a) + source.a)
}

/**
 * Alpha-under blending for floats.
 */
fun addAlphaUnderFloat(dest: FloatCol, source: FloatCol)
{
	/* I may want to disable this clipping */
	if (RE_FLOAT_COLOUR_CLIPPING)
	{
		if (dest.a >= RE_FULL_COLOUR_FLOAT)
			return
		if (-RE_EMPTY_COLOUR_FLOAT < dest.a && dest.a < RE_EMPTY_COLOUR_FLOAT)
		{   /* is getest, scheelt veel */
			dest.r = source.r
			dest.g = source.g
			dest.b = source.b
			dest.a = source.a
			return
		}

	}
	val mul = 1 - dest.a
	dest.r = tryClipFCol(RE_FLOAT_COLOUR_CLIPPING, (mul * source.r) + dest.r)
	dest.g = tryClipFCol(RE_FLOAT_COLOUR_CLIPPING, (mul * source.g) + dest.g)
	dest.b = tryClipFCol(RE_FLOAT_COLOUR_CLIPPING, (mul * source.b) + dest.b)
	dest.a = tryClipFCol(RE_ALPHA_CLIPPING, (mul * source.a) + dest.a)
}


/**
 * Write a 16-bit-colour colour vector to a 8-bit-colour colour vector.
 */
fun cpShortColV2CharColV (source: ShortCol, dest: ByteCol)
{
	dest.r = (source.r.toUInt() shr 8).toUByte()
	dest.g = (source.g.toUInt() shr 8).toUByte()
	dest.b = (source.b.toUInt() shr 8).toUByte()
	dest.a = (source.a.toUInt() shr 8).toUByte()
}

/**
 * Write a 8-bit-colour colour vector to a 16-bit-colour colour vector.
 */
fun cpCharColV2ShortColV(source: ByteCol, dest: ShortCol)
{
	dest.r = (source.r.toUInt() shl 8).toUShort()
	dest.g = (source.g.toUInt() shl 8).toUShort()
	dest.b = (source.b.toUInt() shl 8).toUShort()
	dest.a = (source.a.toUInt() shl 8).toUShort()
}

/**
 * Write a 32-bit-colour colour vector to a 8-bit-colour colour vector.
 */
fun cpIntColV2CharColV(source: IntCol, dest: ByteCol)
{
	dest.r = (source.r shr 24).toUByte()
	dest.g = (source.g shr 24).toUByte()
	dest.b = (source.b shr 24).toUByte()
	dest.a = (source.a shr 24).toUByte()
}

/**
 * Write a floating-point-colour colour vector to a 8-bit-colour colour
 * vector. Clip colours to [0, 1].
 */
fun cpFloatColV2CharColV (source: FloatCol, dest: ByteCol)
{
	// can't this be done more efficient? hope the conversions are correct...

	dest.r =
		if (source.r < 0) 0u
		else if (source.r > 1) 255u
		else (source.r * 255).toUInt().toUByte()

	dest.g =
		if (source.g < 0) 0u
		else if (source.g > 1) 255u
		else (source.g * 255).toUInt().toUByte()

	dest.b =
		if (source.b < 0) 0u
		else if (source.b > 1) 255u
		else (source.b * 255).toUInt().toUByte()

	dest.a =
		if (source.a < 0) 0u
		else if (source.a > 1) 255u
		else (source.a * 255).toUInt().toUByte()
}

/**
 * Cpoy a 8-bit-colour vector to floating point colour vector.
 */
fun cpCharColV2FloatColV (source: ByteCol, dest: FloatCol)
{
	dest.r = (source.r.toFloat() / 255f)
	dest.g = (source.g.toFloat() / 255f)
	dest.b = (source.b.toFloat() / 255f)
	dest.a = (source.a.toFloat() / 255f)
}

/**
 * Cpoy a 16-bit-colour vector to floating point colour vector.
 */
fun cpShortColV2FloatColV (source: ShortCol, dest: FloatCol)
{
	dest.r = source.r.toFloat() / 65535f
	dest.g = source.g.toFloat() / 65535f
	dest.b = source.b.toFloat() / 65535f
	dest.a = source.a.toFloat() / 65535f
}

/**
 * Copy a float-colour colour vector.
 */
fun cpFloatColV(source: FloatCol, dest: FloatCol)
{
	source.copyTo(dest)
}


/**
 * Copy a 16-bit-colour colour vector.
 */
fun cpShortColV(source: ShortCol, dest: ShortCol)
{
	source.copyTo(dest)
}

/**
 * Copy an 8-bit-colour colour vector.
 */
fun cpCharColV (source: ByteCol, dest: ByteCol)
{
	source.copyTo(dest)
}

/**
 * Add a fraction of <source> to <dest>. Result ends up in <dest>.
 * The internal calculation is done with floats.
 *
 * col(dest)   = (1 - alpha(source)*(1 - addfac)) * dest + source
 * alpha(dest) = alpha(source) + alpha (dest)
 */
fun addalphaAddfacShort(doel: ShortCol, bron: ShortCol, addfac:UByte)
{

	/* 1. copy bron straight away if doel has zero alpha */
	if (doel.a.toUInt() == 0u)
	{
		bron.copyTo(doel)
		return
	}

	// Addfac is a number between 0 and 1: rescale */
	// final target is to diminish the influence of dest when addfac rises */
	val m = 1 - bron.a.toFloat() * ((255f - addfac.toFloat()) / 255f) // weiging factor of destination */

	/* blend colours*/

	doel.r = floor(min(m * doel.r.toFloat() + bron.r.toFloat(), 65535f)).toUInt().toUShort()
	doel.g = floor(min(m * doel.g.toFloat() + bron.g.toFloat(), 65535f)).toUInt().toUShort()
	doel.b = floor(min(m * doel.b.toFloat() + bron.b.toFloat(), 65535f)).toUInt().toUShort()
	doel.a = floor(min(m * doel.a.toFloat() + bron.a.toFloat(), 65535f)).toUInt().toUShort()
}


/**
	Same for floats
*/
fun addalphaAddfacFloat(dest: FloatCol, source: FloatCol, addfac:UByte)
{
	var c = 0f // intermediate colour

	// 1. copy source straight away if dest has zero alpha
	// 2. copy dest straight away if dest has full alpha
	// I am not sure whether (2) is correct. It seems to
	// me that this should not happen if float colours
	// aren't clipped at 1.0 .
	// I'll keep the code, but disabled....
	if (dest.a < RE_EMPTY_COLOUR_FLOAT)
	{
		dest.r = source.r
		dest.g = source.g
		dest.b = source.b
		dest.a = source.a
		return
	}

	// Addfac is a number between 0 and 1: rescale
	// final target is to diminish the influence of dest when addfac rises
	// weiging factor of destination
	val m = 1 - (source.a * ((255f - addfac.toFloat()) / 255f))

	// blend colours
	dest.r = tryClipFCol(RE_FLOAT_COLOUR_CLIPPING, (m * dest.r) + source.r, RE_FULL_COLOUR_FLOAT)
	dest.g = tryClipFCol(RE_FLOAT_COLOUR_CLIPPING, (m * dest.g) + source.g, RE_FULL_COLOUR_FLOAT)
	dest.b = tryClipFCol(RE_FLOAT_COLOUR_CLIPPING, (m * dest.b) + source.b, RE_FULL_COLOUR_FLOAT)
	dest.a = tryClipFCol(RE_ALPHA_CLIPPING, dest.a + source.a, RE_FULL_COLOUR_FLOAT)
}

/**
 * Add two halos. Result ends up in <dest>. This should be the
 * addition of two light sources. So far, I use normal alpha-under blending here.
 * The internal calculation is done with floats. The add-factors have to be
 * compensated outside this routine.
 * col(dest)   = s + (1 - alpha(s))d
 * alpha(dest) = alpha(s) + (1 - alpha(s))alpha (d)
 */
fun addHaloToHaloShort (d: ShortCol, s: ShortCol)
{
	/*  float m; */ /* weiging factor of destination */

	/* 1. copy <s> straight away if <d> has zero alpha */
	if (d.a.toUInt() == 0u)
	{
		s.copyTo(d)
		return
	}

	/* 2. halo blending  */
	/* no blending, just add */

	/* One thing that may happen is that this pixel is over-saturated with light - */
	/* i.e. too much light comes out, and the pixel is clipped. Currently, this    */
	/* leads to artifacts such as overproportional undersampling of background     */
	/* colours.                                                                    */
	/* Compensating for over-saturation:                                           */
	/* - increase alpha                                                            */
	/* - increase alpha and rescale colours                                        */

	/* let's try alpha increase and clipping */

	/* calculate how much rescaling we need */
	var rescale = 1f
	var c = 0f

	c = s.r.toFloat() + d.r.toFloat()
	d.r = if (c > 65535)
	{
		rescale *= c / 65535f
		65535u
	}
	else floor(c).toUInt().toUShort()

	c = s.g.toFloat() + d.g.toFloat()
	d.g = if (c > 65535)
	{
		rescale *= c / 65535f
		65535u
	}
	else floor(c).toUInt().toUShort()

	c = s.b.toFloat() + d.b.toFloat()
	d.b = if (c > 65535)
	{
		rescale *= c / 65535f
		65535u
	}
	else floor(c).toUInt().toUShort()

	/* a bit too hefty I think */
	c = (s.a.toFloat() + d.a.toFloat()) * rescale
	d.a = if (c > 65535f) 65535u else floor(c).toUInt().toUShort()
}




