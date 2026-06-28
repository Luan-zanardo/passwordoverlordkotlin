# 🔒 Validador de Senha Overlord

Aplicativo Android desenvolvido em **Kotlin** para validar uma senha inserida pelo usuário contra uma lista de requisitos definidos. O validador percorre a lista em ordem e interrompe o processo no **primeiro erro encontrado**, exibindo a respectiva mensagem em vermelho. Se todas as regras forem satisfeitas, exibe uma mensagem de sucesso em verde.

---

## 🛠️ Regras de Validação Implementadas

O aplicativo valida as seguintes regras sequencialmente:

1. **Mínimo de 5 caracteres**
2. **Pelo menos uma letra maiúscula**
3. **Pelo menos um número**
4. **Conter a palavra "overlord"** (sem diferenciar maiúsculas/minúsculas)
5. **Conter o ano "2026"**
6. **Conter pelo menos um emoji** (ex: 😊)
7. **Não conter espaços em branco** (Extra 1)
8. **Conter pelo menos um caractere especial** (ex: @, #, $, %) (Extra 2)
9. **Terminar com ponto final "."** (Extra 3)

---

## 📂 Arquivos do Projeto

- **[Requisito.kt](file:///C:/Users/luanz/AndroidStudioProjects/passwordoverlordkotlin/app/src/main/java/com/example/password_overlord_kotlin/Requisito.kt)**: Definição da classe de requisito e da lista com as 9 regras.
- **[MainActivity.kt](file:///C:/Users/luanz/AndroidStudioProjects/passwordoverlordkotlin/app/src/main/java/com/example/password_overlord_kotlin/MainActivity.kt)**: Lógica do clique do botão e controle da tela.
- **[activity_main.xml](file:///C:/Users/luanz/AndroidStudioProjects/passwordoverlordkotlin/app/src/main/res/layout/activity_main.xml)**: Design visual do aplicativo com EditText, Button e TextView.
- **[ExampleUnitTest.kt](file:///C:/Users/luanz/AndroidStudioProjects/passwordoverlordkotlin/app/src/test/java/com/example/password_overlord_kotlin/ExampleUnitTest.kt)**: Testes automatizados para cada uma das regras de validação.

---

## 🚀 Como Executar

1. Abra a pasta raiz do projeto no **Android Studio**.
2. Conecte um dispositivo Android ou emulador.
3. Clique em **Run** (`Shift + F10`) para rodar o aplicativo no celular.

Para rodar os testes unitários:
```bash
./gradlew testDebugUnitTest
```
