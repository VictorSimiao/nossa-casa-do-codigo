package br.com.zup.autores.repository

import br.com.zup.autores.model.Autor
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface AutorRepository:JpaRepository<Autor, Long> {
}