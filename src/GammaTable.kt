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

inline fun tryClipFCol (flag: Boolean, c: Float) =
	if (flag && c >= RE_FULL_COLOUR_FLOAT)
		RE_UNITY_COLOUR_FLOAT
	else c

class FloatCol (
	var r:Float,
	var g:Float,
	var b:Float,
	var a:Float,
)

class ShortCol (
	var r:UShort,
	var g:UShort,
	var b:UShort,
	var a:UShort,
)

class ByteCol (
	var r:UByte,
	var g:UByte,
	var b:UByte,
	var a:UByte,
)


//#endregion


/**
 * Samples pixel, depending on R.osa setting
 */
fun addtosampcol(sampcol:UShortArray, shortcol:UShortArray, mask:Int):Int
{
	TODO()
}



/**
 * Samples pixel, bring your own R.osa setting
 */
fun addToSampCol(sampcol:UShortArray, shortcol:UShortArray, mask:Int, osaNr:Int):Int
{
	TODO()
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
fun sampleFloatColV2FloatColV(sample:FloatArray, dest:FloatArray, osaNr:Int)
{
	TODO()
}

/**
 * Convert a series of oversampled pixels into a single pixel. Uses R.osa to
 * count the length! (short vecs to short vec)
 */
fun sampleShortColV2ShortColV(sample:UShortArray, dest:UShortArray, osaNr:Int)
{
	TODO()
}

/**
 * Take colour <bron>, and apply it to <doel> using the alpha value of
 * <bron>.
 */
fun addAlphaOverShort (doel:UShortArray, bron:UShortArray)
{
	TODO()
}

/**
 * Take colour <bron>, and apply it to <doel> using the alpha value of
 * <doel>.
 */
fun addAlphaUnderShort(doel:UShortArray, bron:UShortArray)
{
	TODO()
}


/**
 * Alpha-over blending for floats.
 */
fun addAlphaOverFloat (dest:FloatCol, source:FloatCol)
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
fun addAlphaUnderFloat(dest:FloatCol, source:FloatCol)
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
fun cpShortColV2CharColV(source:UShortArray, dest:UByteArray)
{
	TODO()
}

/**
 * Write a 8-bit-colour colour vector to a 16-bit-colour colour vector.
 */
fun cpCharColV2ShortColV(source:UByteArray, dest:UShortArray)
{
	TODO()
}

/**
 * Write a 32-bit-colour colour vector to a 8-bit-colour colour vector.
 */
fun cpIntColV2CharColV(source:UIntArray, dest:UByteArray)
{
	TODO()
}

/**
 * Write a floating-point-colour colour vector to a 8-bit-colour colour
 * vector. Clip colours to [0, 1].
 */
fun cpFloatColV2CharColV (source:FloatArray, dest:UByteArray)
{
	TODO()
}

/**
 * Cpoy a 8-bit-colour vector to floating point colour vector.
 */
fun cpCharColV2FloatColV (source:UByteArray, dest:FloatArray)
{
	TODO()
}

/**
 * Cpoy a 16-bit-colour vector to floating point colour vector.
 */
fun cpShortColV2FloatColV (source:UShortArray, dest:FloatArray)
{
	TODO()
}

/**
 * Copy a float-colour colour vector.
 */
fun cpFloatColV(source:FloatArray, dest:FloatArray)
{
	TODO()
}


/**
 * Copy a 16-bit-colour colour vector.
 */
fun cpShortColV(source:UShortArray, dest:UShortArray)
{
	TODO()
}

/**
 * Copy an 8-bit-colour colour vector.
 */
fun cpCharColV (source:UByteArray, dest:UByteArray)
{
	TODO()
}

/**
 * Add a fraction of <source> to <dest>. Result ends up in <dest>.
 * The internal calculation is done with floats.
 *
 * col(dest)   = (1 - alpha(source)*(1 - addfac)) * dest + source
 * alpha(dest) = alpha(source) + alpha (dest)
 */
fun addalphaAddfacShort(dest:UShortArray, source:UShortArray, addfac:UByte)
{
	TODO()
}


/**
	Same for floats
*/
fun addalphaAddfacFloat(dest:FloatCol, source:FloatCol, addfac:UByte)
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
	c = (m * dest.r) + source.r
	dest.r = if (RE_FLOAT_COLOUR_CLIPPING && c >= RE_FULL_COLOUR_FLOAT)
		RE_FULL_COLOUR_FLOAT
	else c

	c = (m * dest.g) + source.g
	dest.g = if (RE_FLOAT_COLOUR_CLIPPING && c >= RE_FULL_COLOUR_FLOAT)
		RE_FULL_COLOUR_FLOAT
		else c

	c = (m * dest.b) + source.b
	dest.b = if (RE_FLOAT_COLOUR_CLIPPING && c >= RE_FULL_COLOUR_FLOAT)
		RE_FULL_COLOUR_FLOAT
		else c

	c = dest.a + source.a
	dest.a = if (RE_ALPHA_CLIPPING && c >= RE_FULL_COLOUR_FLOAT)
		RE_FULL_COLOUR_FLOAT
		else c

}

/**
 * Add two halos. Result ends up in <dest>. This should be the
 * addition of two light sources. So far, I use normal alpha-under blending here.
 * The internal calculation is done with floats. The add-factors have to be
 * compensated outside this routine.
 * col(dest)   = s + (1 - alpha(s))d
 * alpha(dest) = alpha(s) + (1 - alpha(s))alpha (d)
 */
fun addHaloToHaloShort (dest:UShortArray, source:UShortArray)
{
	TODO()
}

/**
 * Initialise the gamma lookup tables
 */
fun makeGammaTables (gamma:Float)
{
	TODO()
}

/**
 * Returns true if the table is initialised, false otherwise
 */
fun gammaTableIsInitialised ()
{
	TODO()
}

/**
 * Apply gamma correction on col
 */
fun gammaCorrect (col:Float):Float
{
	TODO()
}


/**
 * Apply inverse gamma correction on col
 */
fun invGammaCorrect (col:Float):Float
{
	TODO()
}


