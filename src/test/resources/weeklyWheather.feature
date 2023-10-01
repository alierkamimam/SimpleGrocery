Feature:

  Scenario:

  Scenario Outline:
    Given It brings the weekly weather forecast of the "<city>" according to the given "<language>", then verify the <200>

    Examples:
      | city     | language | 200 |
      | London   | en       | 200 |
      | Istanbul | tr       | 200 |
      | Berlin   | de       | 200 |
