package common.maths

import kotlin.math.absoluteValue
import kotlin.math.sqrt

private fun Normalise (n:FloatArray):Float
{
	val d = n[0] * n[0] + n[1] * n[1] + n[2] * n[2]

	// FLT_EPSILON is too large! A larger value causes normalise
	// errors in a scaled down utah teapot
	if (d > 0.0000000000001)
	{
		val fd = sqrt(d)

		n[0] /= fd
		n[1] /= fd
		n[2] /= fd
		return fd
	}
	else
	{
		n[2] = 0f
		n[1] = 0f
		n[0] = 0f
		return 0f
	}
}

class Matrix3
{

	internal val entries = Array(3) { FloatArray(3) }

	operator fun get (i:Int) = entries[i]

	fun setIdentity ()
	{
		for (i in 0..2)
		{
			for (j in 0..2)
			{
				entries[i][j] = if (i == j) 1f else 0f
			}
		}
	}

	fun normalize012 ()
	{
		Normalise(this[0])
		Normalise(this[1])
		Normalise(this[2])
	}



}

class Matrix4
{

	internal val entries = Array(4) { FloatArray(4) }

	operator fun get (i:Int) = entries[i]

	fun setIdentity ()
	{
		for (i in 0..3)
		{
			for (j in 0..3)
			{
				entries[i][j] = if (i == j) 1f else 0f
			}
		}
	}


	fun swap (i0:Int, j0:Int, i1:Int, j1:Int)
	{
		val temp = this[i0][j0]
		this[i0][j0] = this[i1][j1]
		this[i1][j1] = temp
	}

}


/** copy from m1 to m2 */
fun MTC_Mat4CpyMat4 (from: Matrix4, to: Matrix4)
{
	for (i in 0..3)
	{
		from[i].copyInto(to[i])
	}
}

fun MTC_Mat4MulMat4 (m1: Matrix4, m2: Matrix4, m3: Matrix4)
{
	// matrix product: c[j][k] = a[j][i].b[i][k]

	m1[0][0] = m2[0][0]*m3[0][0] + m2[0][1]*m3[1][0] + m2[0][2]*m3[2][0] + m2[0][3]*m3[3][0];
	m1[0][1] = m2[0][0]*m3[0][1] + m2[0][1]*m3[1][1] + m2[0][2]*m3[2][1] + m2[0][3]*m3[3][1];
	m1[0][2] = m2[0][0]*m3[0][2] + m2[0][1]*m3[1][2] + m2[0][2]*m3[2][2] + m2[0][3]*m3[3][2];
	m1[0][3] = m2[0][0]*m3[0][3] + m2[0][1]*m3[1][3] + m2[0][2]*m3[2][3] + m2[0][3]*m3[3][3];

	m1[1][0] = m2[1][0]*m3[0][0] + m2[1][1]*m3[1][0] + m2[1][2]*m3[2][0] + m2[1][3]*m3[3][0];
	m1[1][1] = m2[1][0]*m3[0][1] + m2[1][1]*m3[1][1] + m2[1][2]*m3[2][1] + m2[1][3]*m3[3][1];
	m1[1][2] = m2[1][0]*m3[0][2] + m2[1][1]*m3[1][2] + m2[1][2]*m3[2][2] + m2[1][3]*m3[3][2];
	m1[1][3] = m2[1][0]*m3[0][3] + m2[1][1]*m3[1][3] + m2[1][2]*m3[2][3] + m2[1][3]*m3[3][3];

	m1[2][0] = m2[2][0]*m3[0][0] + m2[2][1]*m3[1][0] + m2[2][2]*m3[2][0] + m2[2][3]*m3[3][0];
	m1[2][1] = m2[2][0]*m3[0][1] + m2[2][1]*m3[1][1] + m2[2][2]*m3[2][1] + m2[2][3]*m3[3][1];
	m1[2][2] = m2[2][0]*m3[0][2] + m2[2][1]*m3[1][2] + m2[2][2]*m3[2][2] + m2[2][3]*m3[3][2];
	m1[2][3] = m2[2][0]*m3[0][3] + m2[2][1]*m3[1][3] + m2[2][2]*m3[2][3] + m2[2][3]*m3[3][3];

	m1[3][0] = m2[3][0]*m3[0][0] + m2[3][1]*m3[1][0] + m2[3][2]*m3[2][0] + m2[3][3]*m3[3][0];
	m1[3][1] = m2[3][0]*m3[0][1] + m2[3][1]*m3[1][1] + m2[3][2]*m3[2][1] + m2[3][3]*m3[3][1];
	m1[3][2] = m2[3][0]*m3[0][2] + m2[3][1]*m3[1][2] + m2[3][2]*m3[2][2] + m2[3][3]*m3[3][2];
	m1[3][3] = m2[3][0]*m3[0][3] + m2[3][1]*m3[1][3] + m2[3][2]*m3[2][3] + m2[3][3]*m3[3][3];

}

fun MTC_Mat4MulVecfl (mat: Matrix4, vec: Vec3)
{
	val x = vec.x
	val y = vec.y
	val z = vec.z
	vec.x=x*mat[0][0] + y*mat[1][0] + mat[2][0]*z + mat[3][0]
	vec.y=x*mat[0][1] + y*mat[1][1] + mat[2][1]*z + mat[3][1]
	vec.z=x*mat[0][2] + y*mat[1][2] + mat[2][2]*z + mat[3][2]
}

fun MTC_Mat3MulVecfl (mat: Matrix3, vec: Vec3)
{
	val x=vec.x
	val y=vec.y
	val z=vec.z
	vec[0]= x*mat[0][0] + y*mat[1][0] + mat[2][0]*z
	vec[1]= x*mat[0][1] + y*mat[1][1] + mat[2][1]*z
	vec[2]= x*mat[0][2] + y*mat[1][2] + mat[2][2]*z
}

fun MTC_Mat4Invert (inverse: Matrix4, mat: Matrix4):Boolean
{
	inverse.setIdentity()

	/* Copy original matrix so we don't mess it up */
	val tempmat = Matrix4()
	MTC_Mat4CpyMat4(mat, tempmat)

	for (i in 0..3)
	{
		/* Look for row with max pivot */
		var max = tempmat[i][i].absoluteValue;
		var maxj = i
		for (j in (i+1)..<4)
		{
			val jj = tempmat[j][i].absoluteValue
			if (jj > max)
			{
				max = jj
				maxj = j
			}
		}

		/* Swap rows if necessary */
		if (maxj != i)
		{
			for (k in 0..3)
			{
				tempmat.swap(i, k, maxj, k)
				inverse.swap(i, k, maxj, k)
			}
		}

		var temp = tempmat[i][i]
		// No non-zero pivot
		if (temp == 0f)
			return false
		for (k in 0..3)
		{
			tempmat[i][k] /= temp
			inverse[i][k] /= temp
		}
		for (j in 0..3)
		{
			if (j == i) continue
			temp = tempmat[j][i]
			for (k in 0..3)
			{
				tempmat[j][k] -= tempmat[i][k]*temp;
				inverse[j][k] -= inverse[i][k]*temp;
			}
		}
	}
	return true
}

fun MTC_Mat3CpyMat4 (m1: Matrix3, m2: Matrix4)
{

	m1[0][0]= m2[0][0]
	m1[0][1]= m2[0][1]
	m1[0][2]= m2[0][2]

	m1[1][0]= m2[1][0]
	m1[1][1]= m2[1][1]
	m1[1][2]= m2[1][2]

	m1[2][0]= m2[2][0]
	m1[2][1]= m2[2][1]
	m1[2][2]= m2[2][2]
}

fun MTC_Mat3CpyMat3 (from: Matrix3, to: Matrix3)
{
	for (i in 0..2)
	{
		from[i].copyInto(to[i])
	}
}

fun MTC_Mat3MulMat3 (m1: Matrix3, m3: Matrix3, m2: Matrix3)
{
	/* be careful about this rewrite... */
	    /* m1[i][j] = m2[i][k]*m3[k][j], args are flipped! */
	m1[0][0]= m2[0][0]*m3[0][0] + m2[0][1]*m3[1][0] + m2[0][2]*m3[2][0];
	m1[0][1]= m2[0][0]*m3[0][1] + m2[0][1]*m3[1][1] + m2[0][2]*m3[2][1];
	m1[0][2]= m2[0][0]*m3[0][2] + m2[0][1]*m3[1][2] + m2[0][2]*m3[2][2];

	m1[1][0]= m2[1][0]*m3[0][0] + m2[1][1]*m3[1][0] + m2[1][2]*m3[2][0];
	m1[1][1]= m2[1][0]*m3[0][1] + m2[1][1]*m3[1][1] + m2[1][2]*m3[2][1];
	m1[1][2]= m2[1][0]*m3[0][2] + m2[1][1]*m3[1][2] + m2[1][2]*m3[2][2];

	m1[2][0]= m2[2][0]*m3[0][0] + m2[2][1]*m3[1][0] + m2[2][2]*m3[2][0];
	m1[2][1]= m2[2][0]*m3[0][1] + m2[2][1]*m3[1][1] + m2[2][2]*m3[2][1];
	m1[2][2]= m2[2][0]*m3[0][2] + m2[2][1]*m3[1][2] + m2[2][2]*m3[2][2];

}

fun MTC_Mat4Ortho (mat: Matrix4)
{
	var len = Normalise(mat[0])
	if(len!=0f) mat[0][3]/= len
	len=Normalise(mat[1])
	if(len!=0f) mat[1][3]/= len
	len=Normalise(mat[2])
	if(len!=0f) mat[2][3]/= len
}

fun MTC_Mat4Mul3Vecfl (mat: Matrix4, vec: Vec3)
{
	/* vec = mat^T dot vec !!! or vec a row, then vec = vec dot mat*/

	val x= vec.x
	val y= vec.y
	val z= vec.z
	vec.x= x*mat[0][0] + y*mat[1][0] + mat[2][0]*z
	vec.y= x*mat[0][1] + y*mat[1][1] + mat[2][1]*z
	vec.z= x*mat[0][2] + y*mat[1][2] + mat[2][2]*z
}

fun MTC_Mat4One (mat: Matrix4)
{
	mat.setIdentity()
}

fun MTC_Mat3Inv (m1: Matrix3, m2: Matrix3)
{
	/* eerst adjoint */
	MTC_Mat3Adj(m1,m2)

	/* dan det oude mat! */
	var det= (
		+m2[0][0]* (m2[1][1]*m2[2][2] - m2[1][2]*m2[2][1])
		-m2[1][0]* (m2[0][1]*m2[2][2] - m2[0][2]*m2[2][1])
		+m2[2][0]* (m2[0][1]*m2[1][2] - m2[0][2]*m2[1][1])
	)

	det = if(det==0f) 1f else 1/det

	for (a in 0..2)
	{
		for(b in 0..2)
		{
			m1[a][b]*=det
		}
	}
}

fun MTC_Mat3Adj (m1: Matrix3, m: Matrix3)
{
	m1[0][0]=m[1][1]*m[2][2]-m[1][2]*m[2][1];
	m1[0][1]= -m[0][1]*m[2][2]+m[0][2]*m[2][1];
	m1[0][2]=m[0][1]*m[1][2]-m[0][2]*m[1][1];

	m1[1][0]= -m[1][0]*m[2][2]+m[1][2]*m[2][0];
	m1[1][1]=m[0][0]*m[2][2]-m[0][2]*m[2][0];
	m1[1][2]= -m[0][0]*m[1][2]+m[0][2]*m[1][0];

	m1[2][0]=m[1][0]*m[2][1]-m[1][1]*m[2][0];
	m1[2][1]= -m[0][0]*m[2][1]+m[0][1]*m[2][0];
	m1[2][2]=m[0][0]*m[1][1]-m[0][1]*m[1][0];
}

fun MTC_Mat3One (m: Matrix3)
{
	m.setIdentity()
}



