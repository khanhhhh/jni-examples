#include<cstdlib>
#include<cstring>
#include<string>
#include<vector>
#include<stdexcept>
class Matrix {
	private:
		int height;
		int width;
		double *data;
	public:
		Matrix(): height(0), width(0), data(nullptr) {}
		Matrix(int height, int width): height(height), width(width) {
			data = (double*)std::malloc(height*width*sizeof(double));
		}
		Matrix(int height, int width, const std::vector<double>& vec): height(height), width(width) {
			data = (double*)std::malloc(height*width*sizeof(double));
			std::memcpy(data, vec.data(), height*width*sizeof(double));
		}
		Matrix(const Matrix& m): height(m.height), width(m.width) {
			data = (double*)std::malloc(height*width*sizeof(double));
			std::memcpy(data, m.data, height*width*sizeof(double));
		}
		inline double& get(int h, int w) {
			return data[h*width + w];
		}
		inline double get(int h, int w) const {
			return data[h*width + w];
		}
		inline int getHeight() const {return height;}
		inline int getWidth() const {return width;}
	public:
		static Matrix addition(Matrix m1, Matrix m2) {
			if ((m1.height != m2.height) or (m1.height != m2.height)) {
				throw std::runtime_error("Addition wrong dimension!");
			} else {
				Matrix out(m1.height, m1.width);
				for (int h=0; h<out.height; h++)
				for (int w=0; w<out.width; w++)
					out.get(h, w) = m1.get(h,w) + m2.get(h,w);
				return out;
			}
		}
		static Matrix multiplication(Matrix m1, Matrix m2) {
			if (m1.width != m2.height) {
				throw std::runtime_error("Multiplication wrong dimension!");
			} else {
				Matrix out(m1.height, m2.width);
				for (int h=0; h<out.height; h++)
				for (int w=0; w<out.width; w++) {
					double value = 0;
					for (int x=0; x<m1.width; x++)
						value += m1.get(h,x) * m2.get(x,w);
					out.get(h, w) = value;
				}
				return out;
			}
		}
};
namespace std {
		string to_string(Matrix m) {
			string out;
			for (int h=0; h<m.getHeight(); h++) {
			for (int w=0; w<m.getWidth(); w++) {
				out += "\t";
				out += to_string(m.get(h, w));
			}
			out += "\n";
			}
			return out;
		}
}
/*
#include<iostream>
#include<random>
#include<ctime>
int main() {
	int size = 500;
	std::default_random_engine gen(1234);
	std::uniform_real_distribution<double> dist(0,1);

	std::vector<double> s1(size*size);
	std::vector<double> s2(size*size);

	for (int i=0; i<size*size; i++) {
		s1[i] = dist(gen);
		s2[i] = dist(gen);
	}

	Matrix m1(size, size, s1);
	Matrix m2(size, size, s2);

	std::clock_t tic = std::clock();

	Matrix m3 = Matrix::multiplication(m1,m2);

	std::clock_t toc = std::clock();

	std::cout<<"Elapsed time: "<< static_cast<double>(1000*(toc-tic))/CLOCKS_PER_SEC << " ms" << std::endl;;
}
*/
#include"CMatrix.h"
inline jlong pointer2jlong(Matrix *m) {
	return reinterpret_cast<jlong>(m);
}
inline Matrix *jlong2pointer(jlong l) {
	return reinterpret_cast<Matrix*>(l);
}

JNIEXPORT jlong JNICALL Java_CMatrix_newMatrix(JNIEnv *env, jclass thisobj, jint jheight, jint jwidth, jdoubleArray jdata) {
	int height = static_cast<int>(jheight);
	int width = static_cast<int>(jwidth);
	std::vector<double> vec(height*width);
	jdouble *jvec = env->GetDoubleArrayElements(jdata, nullptr);
	for (int i=0; i<height*width; i++)
		vec[i] = static_cast<double>(jvec[i]);
	Matrix *out = new Matrix(height, width, vec);
	env->ReleaseDoubleArrayElements(jdata, jvec, 0);
	return pointer2jlong(out);
}
JNIEXPORT void JNICALL Java_CMatrix_delMatrix(JNIEnv *env, jclass thisobj, jlong jref) {
	delete jlong2pointer(jref);
}
JNIEXPORT jint JNICALL Java_CMatrix_getHeightMatrix(JNIEnv *env, jclass thisobj, jlong jref) {
	Matrix *ref = jlong2pointer(jref);
	int height = ref->getHeight();
	return static_cast<jint>(height);
}
JNIEXPORT jint JNICALL Java_CMatrix_getWidthMatrix(JNIEnv *env, jclass thisobj, jlong jref) {
	Matrix *ref = jlong2pointer(jref);
	int width = ref->getWidth();
	return static_cast<jint>(width);
}
JNIEXPORT jlong JNICALL Java_CMatrix_additionMatrix(JNIEnv *env, jclass thisobj, jlong jref1, jlong jref2) {
	Matrix *m1 = jlong2pointer(jref1);
	Matrix *m2 = jlong2pointer(jref2);
	Matrix m3 = Matrix::addition(*m1, *m2);
	return pointer2jlong(new Matrix(m3));
}
JNIEXPORT jlong JNICALL Java_CMatrix_multiplicationMatrix(JNIEnv *env, jclass thisobj, jlong jref1, jlong jref2) {
	Matrix *m1 = jlong2pointer(jref1);
	Matrix *m2 = jlong2pointer(jref2);
	Matrix m3 = Matrix::multiplication(*m1, *m2);
	return pointer2jlong(new Matrix(m3));
}

