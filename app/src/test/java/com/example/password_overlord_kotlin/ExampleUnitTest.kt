package com.example.password_overlord_kotlin

import org.junit.Test
import org.junit.Assert.*

/**
 * Testes unitários para validar os requisitos da senha.
 * Verifica as validações obrigatórias e extras em diferentes cenários de entrada.
 */
class ExampleUnitTest {

    private val requisitos = obterListaRequisitos()

    // Helper para rodar a engine e retornar a mensagem de erro ou null se passar
    private fun validar(senha: String): String? {
        for (req in requisitos) {
            if (!req.validacao(senha)) {
                return req.mensagemErro
            }
        }
        return null
    }

    @Test
    fun senhaCorreta_DevePassar() {
        // Atende a todas as regras: min 5 chars, maiúscula, número, "overlord", "2026", emoji, sem espaços, especial, termina com ponto
        val resultado = validar("Overlord2026!😊.")
        assertNull("A senha correta deveria passar sem erros", resultado)
    }

    @Test
    fun senhaCurta_DeveFalhar() {
        val resultado = validar("O2.") // Menos de 5 caracteres (tamanho 3)
        assertEquals("A senha deve ter no mínimo 5 caracteres.", resultado)
    }

    @Test
    fun senhaSemMaiuscula_DeveFalhar() {
        val resultado = validar("overlord2026!😊.")
        assertEquals("A senha deve conter pelo menos uma letra maiúscula.", resultado)
    }

    @Test
    fun senhaSemNumero_DeveFalhar() {
        val resultado = validar("OverlordXXXX!😊.")
        assertEquals("A senha deve conter pelo menos um número.", resultado)
    }

    @Test
    fun senhaSemOverlord_DeveFalhar() {
        val resultado = validar("SenhaForte2026!😊.")
        assertEquals("A senha deve conter a palavra 'overlord' (caso insensível).", resultado)
    }

    @Test
    fun senhaSemAno2026_DeveFalhar() {
        val resultado = validar("Overlord2025!😊.")
        assertEquals("A senha deve conter o ano '2026'.", resultado)
    }

    @Test
    fun senhaSemEmoji_DeveFalhar() {
        val resultado = validar("Overlord2026!.")
        assertEquals("A senha deve conter pelo menos um emoji (ex: 😄, 🔒).", resultado)
    }

    @Test
    fun senhaComEspaco_DeveFalhar() {
        val resultado = validar("Overlord 2026!😊.")
        assertEquals("Extra 1: A senha não pode conter espaços em branco.", resultado)
    }

    @Test
    fun senhaSemEspecial_DeveFalhar() {
        // Removemos o "!" - restando apenas letras, números, emoji e ponto final.
        // Mas espere, o ponto final '.' é um caractere especial!
        // Então para falhar na validação especial e não na do ponto final, a senha teria de não ter outro especial e não ter o ponto final, mas se não tiver o ponto final falhará no ponto final.
        // Vamos testar: se a senha for "Overlord2026😊.", ela tem o '.' que é especial.
        // Se a senha for "Overlord2026😊", ela falhará na regra do caractere especial ou do ponto final?
        // A regra do especial diz: "deve conter pelo menos um caractere especial". O '.' conta como especial, mas se não terminar com '.', ela falha no ponto final.
        // Então se passarmos "Overlord2026😊.", ela tem o '.' que é um especial, então ela passará na regra do especial, mas passará também na regra do ponto final.
        // O que acontece se a senha for "Overlord2026😊" (sem ponto e sem especial)? Ela falhará na regra do especial! Porque o emoji não conta como especial na nossa regra (filtramos emojis), e não há outro especial.
        val resultado = validar("Overlord2026😊")
        assertEquals("Extra 2: A senha deve conter pelo menos um caractere especial (ex: !, @, #, $, %, etc.).", resultado)
    }

    @Test
    fun senhaSemPontoFinal_DeveFalhar() {
        val resultado = validar("Overlord2026!😊")
        assertEquals("Extra 3: A senha deve terminar com um ponto final ('.').", resultado)
    }
}