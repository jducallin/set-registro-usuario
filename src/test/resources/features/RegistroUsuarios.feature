@SETDEPRUEBA
Feature: Registro de Usuarios

  Background:
    Given que abro el navegador y visito la pagina web "http://www.automationpractice.pl/index.php"
@PRUEBA1
  Scenario: Verificar el botón "Sign In"
    When observo el botón de inicio de sesión
    Then debería ver el boton con el texto "Sign in" en la página

@PRUEBA2
  Scenario: Verificar la presencia del botón Create Account
    When hago clic en el boton con el texto "Sign in"
    Then el botón Create Account "SubmitCreate" debería estar presente en la página

@PRUEBA3
  Scenario: Verificar mensaje de error por campo Email vacio
    When hago clic en el boton con el texto "Sign in"
    And hago clic en el botón Create an account "SubmitCreate"
    Then deberia de arrojar mensaje de error "Invalid email address."

@PRUEBA4
  Scenario: Verificar email inválido
    When hago clic en el boton con el texto "Sign in"
    And ingreso correo electrónico "enyel@" en el campo Email address
    And  hago clic en el botón Create an account "SubmitCreate"
    Then deberia de arrojar mensaje de error "Invalid email address."


@PRUEBA5
  Scenario: Verificar email válido
    When hago clic en el boton con el texto "Sign in"
    And ingreso correo electrónico valido "llin@gmail.com" en el campo Email address
    And hago clic en el botón Create an account "SubmitCreate"
    Then debería ver el siguiente el mensaje de Información personal "YOUR PERSONAL INFORMATION"

@PRUEBA6
  Scenario: Verificar registro incompleto
    When hago clic en el boton con el texto "Sign in"
    And ingreso correo electrónico valido "llyn@gmail.com" en el campo Email address
    And hago clic en el botón Create an account "SubmitCreate"
    And selecciono el género masculino
    And ingreso "Juan" en el campo first name
    And ingreso "Torres" en el campo last name
    And hago clic en el botón Register "submitAccount"
    Then debería ver un mensaje de error "There is 1 error"

@PRUEBA7
  Scenario: Verificar registro exitoso
    When hago clic en el boton con el texto "Sign in"
    And ingreso correo electrónico valido "llyn@gmail.com" en el campo Email address
    And hago clic en el botón Create an account "SubmitCreate"
    And selecciono el género masculino
    And ingreso "Carlos" en el campo first name
    And ingreso "Torres" en el campo last name
    And ingreso "cuenta123" en el campo password
    And hago clic en el botón Register "submitAccount"
    Then debería ver el mensaje "My account"
