Feature: registra tarefas

  Scenario: registra nova tarefa com status OPEN
    Given registro nova tarefa
    """
      {"title":"Hello World!","description":"inicio teste", "status":"OPEN"}
      """
    Then pega status
    And retorna codigo 201

  Scenario: registra nova tarefa com status CLOSED
    Given registro nova tarefa
    """
      {"title":"Hello World!2","description":"inicio teste2", "status":"CLOSED"}
      """
    Then pega status de erro
    And retorna codigo 400


  Scenario: deve conter titulo
    Given registro nova tarefa
    """
      {"title":,"description":"inicio teste4", "status":"OPEN"}
      """
    Then pega status de erro
    And retorna codigo 400

  Scenario: registra nova tarefa e adiciona descricao
    Given registro nova tarefa
    """
      {"title":"Hello World!5","description":"", "status":"OPEN"}
      """
    When cadastrado no banco de dados pega id
    Then PUT descricao
    """
      {"title":"Hello World!5","description":"inicio teste5", "status":"OPEN"}
      """
    And retorna codigo 200
