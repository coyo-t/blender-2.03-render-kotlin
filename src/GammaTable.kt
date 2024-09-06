import kotlin.math.floor
import kotlin.math.pow

class GammaTable
{
	/* These indicate the status of the gamma lookup table --------------------- */
	val gamma_range_table = FloatArray(RE_GAMMA_TABLE_SIZE + 1)
	val gamfactor_table = FloatArray(RE_GAMMA_TABLE_SIZE)
	val inv_gamma_range_table = FloatArray(RE_GAMMA_TABLE_SIZE + 1)
	val inv_gamfactor_table = FloatArray(RE_GAMMA_TABLE_SIZE)
	val colour_domain_table = FloatArray(RE_GAMMA_TABLE_SIZE + 1)
	var colour_step = 0f
	var inv_colour_step = 0f
	var valid_gamma = 0f
	var valid_inv_gamma = 0f
	var gamma_table_initialised = false


	fun gammaTableIsInitialised () = gamma_table_initialised

	/**
	 * Apply gamma correction on col
	 */
	fun gammaCorrect (col:Float):Float
	{
		// Clip to range [0,1]: outside, just do the complete calculation.
		// We may have some performance problems here. Stretching up the LUT
		// may help solve that, by exchanging LUT size for the interpolation.
		val i = floor(col * inv_colour_step).toInt()
		return if (i < 0 || i >= RE_GAMMA_TABLE_SIZE)
			col.pow(valid_gamma)
		else
			gamma_range_table[i] + ((col - colour_domain_table[i]) * gamfactor_table[i])
	}

	fun invGammaCorrect (col: Float): Float
	{
		val i = floor(col * inv_colour_step).toInt()
		return if (i < 0 || i >= RE_GAMMA_TABLE_SIZE)
			col.pow(valid_inv_gamma)
		else
			inv_gamma_range_table[i] + ((col - colour_domain_table[i]) * inv_gamfactor_table[i])
	}

	fun makeGammaTables (gamma: Float)
	{
		valid_gamma = gamma
		valid_inv_gamma = (1.0 / gamma).toFloat()
		colour_step = (1.0 / RE_GAMMA_TABLE_SIZE).toFloat()
		inv_colour_step = RE_GAMMA_TABLE_SIZE.toFloat()

		/* We could squeeze out the two range tables to gain some memory.        */
		/* we need two tables: one forward, one backward */

		for (i in 0..<RE_GAMMA_TABLE_SIZE)
		{
			colour_domain_table[i] = i * colour_step
			gamma_range_table[i] = colour_domain_table[i].pow(valid_gamma)
			inv_gamma_range_table[i] = colour_domain_table[i].pow(valid_inv_gamma)
		}

		//* The end of the table should match 1.0 carefully. In order to avoid    */
		//* rounding errors, we just set this explicitly. The last segment may    */
		//* have a different lenght than the other segments, but our              */
		//* interpolation is insensitive to that.                                 */
		colour_domain_table[RE_GAMMA_TABLE_SIZE] = 1f
		gamma_range_table[RE_GAMMA_TABLE_SIZE] = 1f
		inv_gamma_range_table[RE_GAMMA_TABLE_SIZE] = 1f

		// To speed up calculations, we make these calc factor tables. They are  */
		// multiplication factors used in scaling the interpolation.             */
		for (i in 0..<RE_GAMMA_TABLE_SIZE)
		{
			gamfactor_table[i] = (inv_colour_step * (gamma_range_table[i + 1] - gamma_range_table[i]))
			inv_gamfactor_table[i] = (inv_colour_step * (inv_gamma_range_table[i + 1] - inv_gamma_range_table[i]))
		}

		gamma_table_initialised = true
	}
}
