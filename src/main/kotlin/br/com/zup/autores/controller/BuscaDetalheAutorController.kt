package br.com.zup.autores.controller

import br.com.zup.autores.dto.AutorResponse
import br.com.zup.autores.repository.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import javax.transaction.Transactional


@Controller("api/autores")
class BuscaDetalheAutorController(val autorRepository: AutorRepository) {

    @Get
    @Transactional
    fun lista(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {
        if (email.isEmpty()) {
            val autores = autorRepository.findAll()
            val autoresResponse = autores.map { autor -> AutorResponse(autor) }
            return HttpResponse.ok(autoresResponse)
        }
        val possivelAutor = autorRepository.findByEmail(email)
        if (possivelAutor.isEmpty) {
            return HttpResponse.notFound()
        }
        val autor = possivelAutor.get()
        return HttpResponse.ok(AutorResponse(autor))
    }
}