package br.com.zup.autores.controller

import br.com.zup.autores.client.ApiCep
import br.com.zup.autores.dto.AutorRequest
import br.com.zup.autores.repository.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid


@Validated
@Controller("/api/autores")
class NovoAutorController(val autorRepository: AutorRepository, val apiCep: ApiCep) {

    @Post
    @Transactional
    fun cadastro(@Body @Valid request:AutorRequest):HttpResponse<Any>{

        val enderecoResponse = apiCep.buscaEndereco(request.cep)

        val novoAutor = request.toModel(enderecoResponse.body()!!)

        autorRepository.save(novoAutor)

        val uri = UriBuilder.of("/api/autores/{id}").expand(mutableMapOf(Pair("id", novoAutor.id)))

        return HttpResponse.created(uri)
    }
}