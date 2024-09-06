package bl.material

enum class MaterialColModel
{
	RGB,
	CMYK,
	YUV,
	HSV,
}

enum class MaterialMode
{

	Traceable,
	Shadow,
	Shadeless,
	Wire,
	VertexCol,
	Halo,
	ZTRA,
	VertexColP,
	InvertZ,
	HaloRings,
	Env,
	HaloLines,
	OnlyShadow,
	HaloXAlpha,
	Star,
	FaceTexture,
	HaloTex,
	HaloPointNormal,
	NoMist,
	HaloShade,
	HaloFlare,
}