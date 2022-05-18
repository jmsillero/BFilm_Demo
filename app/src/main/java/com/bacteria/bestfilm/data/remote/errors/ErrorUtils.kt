package com.bacteria.bestfilm.data.remote.errors

import com.bacteria.bestfilm.data.remote.ServiceGenerator
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

object ErrorUtils {
    fun parseError(response: Response<*>): ErrorBody {
        val converter: Converter<ResponseBody, ErrorBody> = ServiceGenerator.buildRetrofit()
            .responseBodyConverter(ErrorBody::class.java, arrayOfNulls<Annotation>(0))
        val error: ErrorBody = try {
            converter.convert(response.errorBody())!!
        } catch (e: IOException) {
            return ErrorBody()
        }
        return error
    }
}
