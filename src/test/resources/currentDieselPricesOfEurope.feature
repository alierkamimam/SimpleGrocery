Feature:

  Scenario Outline:

    Given It brings the diesel prices of "<city>" and "<district>"

    Examples:
      | city   | district         |
      | gent   | wondelgem        |
      | gent   | afsnee           |
      | gent   | desteldonk       |
      | gent   | gentbrugge       |
      | gent   | ledeberg         |
      | gent   | mariakerke       |
      | berlin | mitte            |
      | berlin | neukölln         |
      | berlin | spandau          |
      | berlin | treptow-Köpenick |