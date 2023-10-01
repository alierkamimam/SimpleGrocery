Feature:

  Scenario Outline: Verify the IBAN validation

    Given "<iban>" is valid, then verify the status code 200

    Examples:
      | iban                        |
      | DE89370400440532013000      |
      | BG18RZBB91550123456789      |
      | HR1723600001101234565       |
      | LT601010012345678901        |
      | FR7630006000011234567890189 |
      | IT60X0542811101000000123456 |

