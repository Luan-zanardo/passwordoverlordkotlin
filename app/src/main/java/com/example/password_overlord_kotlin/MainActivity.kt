package com.example.password_overlord_kotlin

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity

/**
 * MainActivity do projeto Validador de Senha Overlord.
 * Implementa uma interface simples baseada em XML e valida a senha informada
 * contra uma lista de requisitos definidos na classe Requisito.
 */
class MainActivity : ComponentActivity() {

    // Declaração dos componentes da tela que serão inicializados no onCreate
    private lateinit var etPassword: EditText
    private lateinit var btnValidate: Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Define o layout XML como a visualização desta Activity
        setContentView(R.layout.activity_main)

        // Inicializa os componentes encontrando-os pelo ID definido no XML
        etPassword = findViewById(R.id.etPassword)
        btnValidate = findViewById(R.id.btnValidate)
        tvResult = findViewById(R.id.tvResult)

        // Obtém a lista com todas as regras de validação definidas no arquivo Requisito.kt
        val listaRequisitos = obterListaRequisitos()

        // Define a ação executada ao clicar no botão "Validar Senha"
        btnValidate.setOnClickListener {
            val senhaInserida = etPassword.text.toString()

            // Executa a engine de validação: percorre a lista em ordem
            var erroEncontrado: String? = null
            
            for (requisito in listaRequisitos) {
                // Se um requisito falhar, salvamos a mensagem de erro correspondente e interrompemos a busca
                if (!requisito.validacao(senhaInserida)) {
                    erroEncontrado = requisito.mensagemErro
                    break
                }
            }

            // Exibe o resultado apropriado no TextView
            if (erroEncontrado != null) {
                // Caso alguma validação falhe: exibe o erro em vermelho
                tvResult.text = "❌ $erroEncontrado"
                tvResult.setTextColor(Color.parseColor("#DC2626")) // Vermelho vibrante (Red 600)
            } else {
                // Se passar em todas as validações: exibe sucesso em verde
                tvResult.text = "✅ Sucesso! A senha atende a todos os requisitos do Overlord."
                tvResult.setTextColor(Color.parseColor("#16A34A")) // Verde vibrante (Green 600)
            }
        }
    }
}