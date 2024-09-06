enum class EnvType
{
	Cube,
	Plane,
	Sphere,
}

enum class EnvSType
{
	Static,
	Anim,
	Load,
}

class EnvMap {
	var `object`: Any? = null
//	Image *ima;		/* type ENV_LOAD */
//	Image *cube[6];		/* these images are dynamic, not part of the main struct */

	val imat = mat4x4f()
	var type = EnvType.Cube
	var stype = EnvSType.Static
	var clipsta = 0.1f
	var clipend = 100f
	var notlay = false
	var cuberes = 100
	var ok = 0
	var lastframe = 0
}