package br.com.zup.autores.controller

import br.com.zup.autores.dto.AutorResponse
import br.com.zup.autores.repository.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Put

@Controller("api/autores/{id}")
class AtualizaAutorController(val autorRepository: AutorRepository) {

    @Put
    fun atualiza(@PathVariable id: Long, descricao: String): HttpResponse<Any> {
        val possivelAutor = autorRepository.findById(id)
        if (possivelAutor.isEmpty) {
            return HttpResponse.notFound()
        }
        val autor = possivelAutor.get()
        autor.descricao = descricao
        autorRepository.update(autor)
        return HttpResponse.ok(AutorResponse(autor))
    }
}