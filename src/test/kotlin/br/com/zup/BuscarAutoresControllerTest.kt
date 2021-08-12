package br.com.zup

import br.com.zup.autores.client.EnderecoResponse
import br.com.zup.autores.dto.AutorResponse
import br.com.zup.autores.model.Autor
import br.com.zup.autores.model.Endereco
import br.com.zup.autores.repository.AutorRepository
import io.micronaut.http.HttpResponse.ok
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import javax.inject.Inject

@MicronautTest
class BuscarAutoresControllerTest {

    /**
     * Impontando o client
     */
    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    /**
     * Injetando o Repository
     */
    @field:Inject
    lateinit var autorRepository: AutorRepository

    /**
     * Antes de começar o teste salvar o autor no BD
     */
    lateinit var autor:Autor
    @BeforeEach
    internal fun setup() {
        val enderecoResponse = EnderecoResponse("Santa Lucia", "Vista Linda", "ES")
        val endereco = Endereco(enderecoResponse, "29708120", "323")
         autor = Autor("Victor", "victor.sreis@hotmail.com", "Treinamento Pesado", endereco)
        autorRepository.save(autor)
    }

    /**
     * Limpar no banco depois de ter realizado o teste
     */
    @AfterEach
    internal fun tearDown(){
        autorRepository.deleteAll()
    }

    /**
     * Monatando o senario
     */
    @Test
    fun deveRetornarDetalhesDeUmAutor() {
        //Cliente faz uma requisição para essa URI e me devolve um objeto AutorResponse
        var response =
            client.toBlocking().exchange("/api/autores?email=${autor.email}", AutorResponse::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body())
        assertEquals(autor.nome, response.body()!!.nome)
        assertEquals(autor.email, response.body()!!.email)
        assertEquals(autor.descricao, response.body()!!.descricao)
    }
}