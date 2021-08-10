package br.com.zup.autores.controller

import br.com.zup.autores.dto.AutorResponse
import br.com.zup.autores.repository.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get


@Controller("api/autores")
class BuscaDetalheAutorController(val autorRepository: AutorRepository) {

    @Get
    fun lista():HttpResponse<List<AutorResponse>> {
        val autores = autorRepository.findAll()
        val autoresResponse = autores.map { autor -> AutorResponse(autor) }
        return HttpResponse.ok(autoresResponse)
    }
}