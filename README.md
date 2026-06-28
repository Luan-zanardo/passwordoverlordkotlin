# 🔒 Senha Overlord - Validador de Senhas para Android

Este é um projeto desenvolvido em **Kotlin** para a plataforma **Android** utilizando o **Android Studio**. O objetivo do aplicativo é validar uma senha inserida pelo usuário contra uma lista ordenada de requisitos rigorosos e criativos impostos pelo "Overlord", exibindo o primeiro erro encontrado ou uma mensagem de sucesso caso todas as regras sejam atendidas.

O projeto foi projetado com uma arquitetura simples e limpa, ideal para estudantes que estão aprendendo desenvolvimento Android nativo, utilizando apenas recursos básicos do Android SDK (Views tradicionais via XML e Activity) sem frameworks ou bibliotecas adicionais complexas.

---

## 🛠️ Funcionalidades e Regras de Validação

A engine de validação percorre sequencialmente uma lista de requisitos. O processo é interrompido no **primeiro erro encontrado**, exibindo a mensagem correspondente em vermelho (`❌`). Se a senha passar por todas as regras, uma mensagem de sucesso verde (`✅`) é exibida.

### Requisitos Obrigatórios:
1. **Comprimento Mínimo:** A senha deve conter pelo menos 5 caracteres.
2. **Letra Maiúscula:** Deve conter pelo menos uma letra maiúscula.
3. **Número:** Deve conter pelo menos um dígito numérico.
4. **Palavra Secreta:** Deve conter a palavra `"overlord"` (caso insensível, ex: `OverLord`, `OVERLORD`).
5. **Ano Futuro:** Deve conter o ano `"2026"`.
6. **Emojis:** Deve conter pelo menos um caractere de emoji (ex: `😄`, `🔒`).

### Validações Extras Criativas:
7. **Sem Espaços:** A senha não pode conter nenhum espaço em branco.
8. **Caractere Especial:** Deve conter pelo menos um caractere especial padrão (ex: `!`, `@`, `#`, `$`, `%`, etc., desconsiderando letras, números, espaços e emojis).
9. **Finalização:** A senha deve obrigatoriamente terminar com um ponto final (`.`).

*Exemplo de senha válida:* **`Overlord2026!😊.`**

---

## 📂 Estrutura do Código

Para manter o projeto simples e fácil de entender, o código foi organizado nos seguintes arquivos separados:

*   **[Requisito.kt](file:///C:/Users/luanz/AndroidStudioProjects/passwordoverlordkotlin/app/src/main/java/com/example/password_overlord_kotlin/Requisito.kt)**: Contém a definição da `data class` **`Requisito`** (que possui a mensagem de erro e a lambda de validação), a função utilitária de análise Unicode para emojis, e a lista de requisitos em ordem de verificação.
*   **[MainActivity.kt](file:///C:/Users/luanz/AndroidStudioProjects/passwordoverlordkotlin/app/src/main/java/com/example/password_overlord_kotlin/MainActivity.kt)**: Controlador da tela do aplicativo. Faz a associação com o layout XML, escuta os cliques do botão e executa a lógica sequencial da engine de validação.
*   **[activity_main.xml](file:///C:/Users/luanz/AndroidStudioProjects/passwordoverlordkotlin/app/src/main/res/layout/activity_main.xml)**: Layout visual estruturado com `ScrollView` e containers elegantes para renderizar o título do app, campo de texto de senha (`EditText`), botão de validação (`Button`) e o resultado em tempo real (`TextView`).
*   **Estilização e Drawables**:
    *   [edit_text_background.xml](file:///C:/Users/luanz/AndroidStudioProjects/passwordoverlordkotlin/app/src/main/res/drawable/edit_text_background.xml): Arredondamento e bordas para o campo de input.
    *   [button_background.xml](file:///C:/Users/luanz/AndroidStudioProjects/passwordoverlordkotlin/app/src/main/res/drawable/button_background.xml): Efeito gradiente moderno e efeito de clique (ripple).
    *   [result_background.xml](file:///C:/Users/luanz/AndroidStudioProjects/passwordoverlordkotlin/app/src/main/res/drawable/result_background.xml): Bordas e cor de fundo neutra para o box de resultado.
*   **[ExampleUnitTest.kt](file:///C:/Users/luanz/AndroidStudioProjects/passwordoverlordkotlin/app/src/test/java/com/example/password_overlord_kotlin/ExampleUnitTest.kt)**: Testes unitários JUnit criados para validar individualmente o sucesso e a falha específica de cada regra definida no validador.

---

## 🚀 Como Executar o Projeto

1. Abra o **Android Studio**.
2. Clique em **File > Open** e selecione a pasta raiz deste repositório (`passwordoverlordkotlin`).
3. Aguarde o Gradle sincronizar as dependências do projeto.
4. Conecte um dispositivo Android físico (com a Depuração USB ativa) ou inicie um Emulador.
5. Clique no botão **Run** (ícone de play verde) na barra superior ou pressione `Shift + F10` para compilar e instalar o app no celular.

### Executando os Testes Unitários
Para validar que todas as regras de senha estão funcionando perfeitamente sem rodar o app no celular:
1. No painel lateral esquerdo do Android Studio, vá em `app > java > com.example.password_overlord_kotlin (test)`.
2. Clique com o botão direito em **[ExampleUnitTest](file:///C:/Users/luanz/AndroidStudioProjects/passwordoverlordkotlin/app/src/test/java/com/example/password_overlord_kotlin/ExampleUnitTest.kt)** e selecione **Run 'ExampleUnitTest'**.
3. Alternativamente, você pode rodar o seguinte comando no terminal do Android Studio para executar os testes via Gradle:
   ```bash
   ./gradlew testDebugUnitTest
   ```
