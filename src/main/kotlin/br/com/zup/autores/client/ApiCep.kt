package br.com.zup.autores.client

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client

@Client("https://viacep.com.br/ws")
interface ApiCep {
    @Get("/{cep}/json")
    fun buscaEndereco(@PathVariable cep: String): HttpResponse<EnderecoResponse>
}