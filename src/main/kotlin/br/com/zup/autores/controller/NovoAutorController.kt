package br.com.zup.autores.controller

import br.com.zup.autores.dto.AutorRequest
import br.com.zup.autores.repository.AutorRepository
import io.micronaut.core.annotation.Introspected
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.validation.Valid


@Validated
@Controller("/api/autores")
class NovoAutorController(val autorRepository: AutorRepository) {

    @Post
    fun cadastro(@Body @Valid request:AutorRequest):HttpResponse<Any>{
        val novoAutor = request.toModel()
        autorRepository.save(novoAutor)
        val uri = UriBuilder.of("/api/autores/{id}").expand(mutableMapOf(Pair("id", novoAutor.id)))
        return HttpResponse.created(uri)
    }
}