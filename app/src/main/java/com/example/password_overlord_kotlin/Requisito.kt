package com.example.password_overlord_kotlin

/**
 * Representa um requisito de validação de senha.
 *
 * @property mensagemErro Mensagem a ser exibida caso a validação falhe.
 * @property validacao Função lambda que recebe a senha e retorna true se ela for válida para este requisito.
 */
data class Requisito(
    val mensagemErro: String,
    val validacao: (String) -> Boolean
)

/**
 * Função utilitária para verificar se uma String contém pelo menos um emoji.
 * Percorre os code points da string procurando por intervalos comuns de emojis na tabela Unicode.
 */
fun String.contemEmoji(): Boolean {
    var i = 0
    while (i < this.length) {
        val codePoint = this.codePointAt(i)
        // Verifica se o code point está dentro das faixas comuns de emojis e símbolos gráficos
        if (codePoint in 0x1F600..0x1F64F || // Emoticons (😄, 😆, etc.)
            codePoint in 0x1F300..0x1F5FF || // Símbolos e Pictogramas Diversos
            codePoint in 0x1F680..0x1F6FF || // Símbolos de Transporte e Mapa
            codePoint in 0x1F900..0x1F9FF || // Símbolos e Pictogramas Adicionais (🤖, 🦄, etc.)
            codePoint in 0x1FA70..0x1FAFF || // Símbolos Estendidos-A
            codePoint in 0x2600..0x26FF   || // Símbolos Diversos (☀, ☁, ☄, etc.)
            codePoint in 0x2700..0x27BF      // Dingbats (✂, ✈, ✉, etc.)
        ) {
            return true
        }
        // Avança o índice considerando caracteres representados por pares de surrogates
        i += Character.charCount(codePoint)
    }
    return false
}

/**
 * Retorna a lista ordenada com todos os requisitos que a senha deve cumprir.
 */
fun obterListaRequisitos(): List<Requisito> {
    return listOf(
        // --- REQUISITOS OBRIGATÓRIOS ---
        
        Requisito(
            mensagemErro = "A senha deve ter no mínimo 5 caracteres."
        ) { senha ->
            senha.length >= 5
        },
        
        Requisito(
            mensagemErro = "A senha deve conter pelo menos uma letra maiúscula."
        ) { senha ->
            senha.any { it.isUpperCase() }
        },
        
        Requisito(
            mensagemErro = "A senha deve conter pelo menos um número."
        ) { senha ->
            senha.any { it.isDigit() }
        },
        
        Requisito(
            mensagemErro = "A senha deve conter a palavra 'overlord' (caso insensível)."
        ) { senha ->
            senha.contains("overlord", ignoreCase = true)
        },
        
        Requisito(
            mensagemErro = "A senha deve conter o ano '2026'."
        ) { senha ->
            senha.contains("2026")
        },
        
        Requisito(
            mensagemErro = "A senha deve conter pelo menos um emoji (ex: 😄, 🔒)."
        ) { senha ->
            senha.contemEmoji()
        },
        
        // --- VALIDAÇÕES EXTRAS CRIATIVAS ---
        
        Requisito(
            mensagemErro = "Extra 1: A senha não pode conter espaços em branco."
        ) { senha ->
            !senha.contains(" ")
        },
        
        Requisito(
            mensagemErro = "Extra 2: A senha deve conter pelo menos um caractere especial (ex: !, @, #, $, %, etc.)."
        ) { senha ->
            // Consideramos caracteres especiais aqueles que não são letras, nem dígitos e nem espaço
            senha.any { !it.isLetterOrDigit() && !it.isWhitespace() && !it.isHighSurrogate() && !it.isLowSurrogate() }
        },
        
        Requisito(
            mensagemErro = "Extra 3: A senha deve terminar com um ponto final ('.')."
        ) { senha ->
            senha.endsWith(".")
        }
    )
}
