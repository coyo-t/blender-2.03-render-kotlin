package bl

// afbreek: abort

class RE
{

	var activeCamera: Camera? = null

	var R = Render()

	fun initRender ()
	{
	}

	fun setWindowClip (mode:Int, jmode:Int)
	{
	}

	fun animRender ()
	{
	}

	fun exitRenderStuff ()
	{
	}

	fun freeRenderData ()
	{
	}

	fun freeFilterMask ()
	{
	}

	fun holoView ()
	{
		if (activeCamera == null)
		{
			return
		}
	}

	fun initFilterMask ()
	{
	}

	fun initRenderData ()
	{
	}

	fun jitterate1 (jit1:Float?, jit2:Float?, num:Int, rad1:Float)
	{
	}

	fun jitterate2 (jit1:Float?, jit2:Float?, num:Int, rad2:Float)
	{
	}

	fun makeExistingFile (name:String)
	{
	}

	fun makeStars (wire:Boolean)
	{
	}

	fun screenDump ()
	{
	}

	fun writeImage (name:String)
	{
	}

}


