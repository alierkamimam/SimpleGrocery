Feature:

  Scenario:

  Scenario Outline:
    Given It brins the duty pharmacy list of "<ilce>" and "<il>", then verify status code is <200>
    Examples:
      | ilce    | il     | 200
      | haymana | ankara | 200
      | mamak   | ankara | 200
      | konya   | meram  | 200