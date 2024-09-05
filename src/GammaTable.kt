

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
fun addAddSampColF (s:FloatArray, d:FloatArray, m:Int, osa:Int, add:UByte)
{
}


/**
 * Alpha undersamples pixel, bring your own R.osa setting
 */
fun addUnderSampColF (sampcol:FloatArray, dest:FloatArray, mask:Int, osaNr:Int):Int
{
	TODO()
}


/**
 * Alpha oversample pixel, bring your own R.osa setting
 */
fun addOverSampColF(sampcol:FloatArray, dest:FloatArray, mask:Int, osaNr:Int)
{
}

/**
 * Convert a series of oversampled pixels into a single pixel.
 * (float vecs to float vec)
 */
fun sampleFloatColV2FloatColV(sample:FloatArray, dest:FloatArray, osaNr:Int)
{
}

/**
 * Convert a series of oversampled pixels into a single pixel. Uses R.osa to
 * count the length! (short vecs to short vec)
 */
fun sampleShortColV2ShortColV(sample:UShortArray, dest:UShortArray, osaNr:Int)
{
}

/**
 * Take colour <bron>, and apply it to <doel> using the alpha value of
 * <bron>.
 * @param doel
 * @param bron
 */
fun addAlphaOverShort (doel:UShortArray, bron:UShortArray)
{
}

/**
 * Take colour <bron>, and apply it to <doel> using the alpha value of
 * <doel>.
 * @param doel
 * @param bron
 */
fun addAlphaUnderShort(doel:UShortArray, bron:UShortArray)
{
}


/**
 * Alpha-over blending for floats.
 */
fun addAlphaOverFloat(dest:FloatArray, source:FloatArray)
{
}

/**
 * Alpha-under blending for floats.
 */
fun addAlphaUnderFloat(dest:FloatArray, source:FloatArray)
{
}


/**
 * Write a 16-bit-colour colour vector to a 8-bit-colour colour vector.
 */
fun cpShortColV2CharColV(source:UShortArray, dest:UByteArray)
{
}

/**
 * Write a 8-bit-colour colour vector to a 16-bit-colour colour vector.
 */
fun cpCharColV2ShortColV(source:UByteArray, dest:UShortArray)
{
}

/**
 * Write a 32-bit-colour colour vector to a 8-bit-colour colour vector.
 */
fun cpIntColV2CharColV(source:UIntArray, dest:UByteArray)
{
}

/**
 * Write a floating-point-colour colour vector to a 8-bit-colour colour
 * vector. Clip colours to [0, 1].
 */
fun cpFloatColV2CharColV (source:FloatArray, dest:UByteArray)
{
}

/**
 * Cpoy a 8-bit-colour vector to floating point colour vector.
 */
fun cpCharColV2FloatColV (source:UByteArray, dest:FloatArray)
{
}

/**
 * Cpoy a 16-bit-colour vector to floating point colour vector.
 */
fun cpShortColV2FloatColV (source:UShortArray, dest:FloatArray)
{
}

/**
 * Copy a float-colour colour vector.
 */
fun cpFloatColV(source:FloatArray, dest:FloatArray)
{
}


/**
 * Copy a 16-bit-colour colour vector.
 */
fun cpShortColV(source:UShortArray, dest:UShortArray)
{
}

/**
 * Copy an 8-bit-colour colour vector.
 */
fun cpCharColV (source:UByteArray, dest:UByteArray)
{
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
 * Same for floats
 */
fun addalphaAddfacFloat(dest:FloatArray, source:FloatArray, addfac:UByte)
{
	TODO()
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


