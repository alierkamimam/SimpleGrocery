Feature:
  Scenario:
    Given Register new a client with "xxx" and "xxx@gmail.com", then verify status code 201
    When Get all products, then verify status code 200
    And Create a new cart, then verify status code 201
    And Add three items 5477, 5478, 4875 to Cart, then verify status code 201
    Then Check if the products you added to the cart are correct, then verify status code 200
    And Let's cancel purchasing a product and remove it from the cart, then verify status code 204
    Then Check if the product you removed from the cart are correct
    And Create an new order with name "xxx", then verify status code 201
    Then Check the order "xxx" have created, then verify status code 200
    And Delete an order, then verify status code 204




