package br.com.zup.autores.model

import br.com.zup.autores.client.EnderecoResponse
import javax.persistence.Embeddable

@Embeddable
class Endereco(enderecoResponse: EnderecoResponse, val cep:String, val numero:String) {
    val logradouro = enderecoResponse.logradouro
    val bairro = enderecoResponse.bairro
    val uf = enderecoResponse.uf
}