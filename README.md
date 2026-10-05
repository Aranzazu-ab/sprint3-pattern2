# Pattern Builder

A small Java project that builds restaurant menus step by step.
It uses the **Builder pattern** with a **Fluent** style and a **progressive interface**.

## What is this project?

A menu can have:

- An entrant (optional)
- A main course (required)
- A dessert **or** a coffee (optional, never both)
- A drink (optional)

Any dish can be **vegan** and/or **gluten free**.
The main course can also have a **supplement** (for example, an extra side dish).

## Main ideas

### Builder pattern
We build a `Menu` step by step and call `build()` at the end.
This is easier to read than a constructor with many parameters.

### Fluent style
Each method returns the next step, so we can chain the calls:

```java
Menu menu = new MenuBuilder()
        .withMainCourse("Main course 1")
        .withDessert("Dessert 1")
        .withDrink("Drink 1")
        .build();
```

### Progressive interface
After each step, you can only call the methods that make sense next.
If you call a method in the wrong order, the code **does not compile**.

Examples of what is **not allowed**:

```java
// Does not compile: you cannot add a dessert before the main course
new MenuBuilder().withDessert("Dessert 1");

// Does not compile: you cannot choose a dessert and a coffee
new MenuBuilder()
        .withMainCourse("Main course 1")
        .withDessert("Dessert 1")
        .withCoffee("Coffee 1");

// Does not compile: you cannot add the dessert twice
new MenuBuilder()
        .withMainCourse("Main course 1")
        .withDessert("Dessert 1")
        .withDessert("Dessert 2");
```

## Project structure

| File | What it does |
|------|--------------|
| `Menu` | The final product. It stores the entrant, main course, dessert, coffee and drink. |
| `Dish` | One dish. It stores the name, vegan, gluten free and supplement. |
| `MenuBuilder` | Builds the menu. It has one private inner class for each step. |
| `StartStep` | First step: choose an entrant or a main course. |
| `EntrantStep` | After the entrant: set vegan / gluten free, or go to the main course. |
| `MainCourseStep` | After the main course: set properties, add a supplement, choose dessert, coffee or drink, or build. |
| `DrinkStep` | After the dessert or coffee: choose a drink or build. |
| `BuildStep` | Last step: only `build()`. |

## Flow of the steps

```
StartStep
  |-- withEntrant ----> EntrantStep --- withMainCourse ---+
  |                      (isVegan, isGlutenFree)           |
  |-- withMainCourse ---------------------------------> MainCourseStep
                                                          |
              withDessert / withCoffee --> DrinkStep ---- withDrink --> BuildStep --> build()
              withDrink -----------------------------------------------> BuildStep
              build() (menu without dessert and drink)
```

## Examples

**Executive menu**

```java
Menu executiveMenu = new MenuBuilder()
        .withEntrant("Entrant 1")
            .isVegan()
            .isGlutenFree()
        .withMainCourse("Main course 1")
            .withSuplement("Supplement 1")
        .withDessert("Dessert 1")
        .withDrink("Drink 1")
        .build();
```

**Kids menu** (main course, dessert and drink)

```java
Menu kidsMenu = new MenuBuilder()
        .withMainCourse("Main course 2")
        .withDessert("Dessert 2")
        .withDrink("Drink 2")
        .build();
```

**Half menu** (main course and drink, with a coffee)

```java
Menu halfMenu = new MenuBuilder()
        .withMainCourse("Main course 3")
            .isVegan()
        .withCoffee("Coffee 1")
        .withDrink("Drink 3")
        .build();
```

## How the Builder roles map to this project

| Classic Builder role | In this project |
|----------------------|-----------------|
| Product | `Menu` (and `Dish`) |
| Concrete builder | `MenuBuilder` |
| Abstract builder | Not used. The step interfaces limit the methods instead. |
| Director | Not used. The client chooses the order of the steps. |
| Client | The unit tests |

## SOLID principles

- **Single Responsibility:** each class has one job.
- **Interface Segregation:** each step is a small interface with only the methods it needs.
- **Dependency Inversion:** the client works with interfaces, not with the inner classes.

## Tests

The tests use **JUnit 5**. They build different menus and check the result.

To run them with Maven:

```
mvn test
```

The order rules (dessert before main course, dessert and coffee together, etc.) are not tested with JUnit, because that code does not compile. This is the goal of the progressive interface.

## Requirements

- Java 11 or higher
- Maven
- JUnit 5


# Observer Pattern

A small Java project that shows how the **Observer pattern** works.

A stock agent tells many stock broker agencies when the stock market goes **up** or **down**.

## What is the Observer pattern?

The Observer pattern is used when one object (the **Observable**) needs to tell many other objects (the **Observers**) that something has changed.

Think of a YouTube channel: you subscribe, and the channel tells you when there is a new video. You do not need to check every day.

- The Observable keeps a list of observers.
- When its state changes, it notifies all of them.
- Each observer decides what to do with the notification.

The Observable does not know the details of its observers. It only knows that they all have an `update` method. This keeps the code simple and easy to change.

## Classes

| Class / Interface | Role | What it does |
|---|---|---|
| `Observer` | Interface | Defines the `update(String message)` method. |
| `StockAgent` | Observable | Keeps the list of observers and notifies them when the market changes. |
| `StockBrokerAgency` | Concrete Observer | Receives the message and prints it with its own name. |

### `StockAgent` methods

- `addObserver(Observer observer)`: subscribes an observer.
- `removeObserver(Observer observer)`: removes an observer.
- `notifyObservers(String message)`: sends the message to all observers.
- `stockMarketUp(double value)`: the market goes up and all observers are notified.
- `stockMarketDown(double value)`: the market goes down and all observers are notified.

## How it works

1. An agency subscribes to the agent with `addObserver`.
2. The market changes with `stockMarketUp` or `stockMarketDown`.
3. The agent creates a message and calls `notifyObservers`.
4. Each observer runs `update` and prints the message.

## Example

```java
StockAgent agent = new StockAgent();

Observer alpha = new StockBrokerAgency("Alpha Brokers");
Observer zenith = new StockBrokerAgency("Zenith Investments");

agent.addObserver(alpha);
agent.addObserver(zenith);

agent.stockMarketUp(150.75);
agent.stockMarketDown(145.50);
```

Output:

```
Alpha Brokers received notification: Stock market went UP to 150.75
Zenith Investments received notification: Stock market went UP to 150.75
Alpha Brokers received notification: Stock market went DOWN to 145.50
Zenith Investments received notification: Stock market went DOWN to 145.50
```

## Tests

The tests use **JUnit 5**. They check that:

- Many observers can subscribe to the agent.
- The market going up and down creates the correct messages.
- Observers receive and print the expected messages.
- A removed observer does not receive new notifications.

To check what is printed, the tests send `System.out` to a buffer and compare it with the expected text.

## Note about decimals

The numbers are formatted with `String.format(Locale.US, "%.2f", value)`.

- `%.2f` always shows 2 decimals (`145.50`).
- `Locale.US` always uses a dot as the decimal separator (`150.75`), so the output is the same on every computer.

## Project structure

```
level2/
  Observer.java
  StockAgent.java
  StockBrokerAgency.java
level2Test/
  StockAgentTest.java
```

## Requirements

- Java 17 or newer (Java 11 also works)
- JUnit 5

# Pattern Decorator

A small Java project that builds customized Bubble Teas.
It uses the **Decorator pattern** to add ingredients to a drink dynamically.

## What is this project?

A Bubble Tea always starts with a **base**:

| Base | Price  |
|------|--------|
| Latte (`LatteBase`) | 3.50 € |
| Matcha (`MatchaBase`) | 4.00 € |
| Tea (`TeaBase`) | 3.00 € |

After that, you can add as many **extras** as you want:

| Extra | Price |
|-------|-------|
| Ice (`Ice`) | +0.25 € |
| Sugar (`Sugar`) | +0.30 € |
| Tapioca (`Tapioca`) | +0.50 € |
| Flavor (`Flavor`) | +0.60 € for each flavor |

Each extra changes both the **cost** and the **description** of the drink.
If you add two flavors, the cost increases by 1.20 €.

## Main ideas

### Decorator pattern
A decorator **wraps** another Bubble Tea and adds something to it.
The wrapped object and the decorator share the same interface, so we can wrap a decorator with another decorator as many times as we want.

Without this pattern, we would need one subclass for every possible combination
(`LatteWithTapiocaAndIce`, `MatchaWithSugarAndTapioca`, ...). With the Decorator, we only need **one class per ingredient**.

### Composition instead of inheritance
We do not extend a drink to add features. We **wrap** it:

```java
BubbleTea tea = new LatteBase();
tea = new Tapioca(tea);
tea = new Sugar(tea);
tea = new Flavor(tea, "Strawberry");
tea = new Flavor(tea, "Mango");

System.out.println(tea.getDescription());
// Latte Bubble Tea + Tapioca + Sugar + Flavor: Strawberry + Flavor: Mango

System.out.println(tea.getCost());
// 3.50 + 0.50 + 0.30 + 0.60 + 0.60 = 5.50
```

### Delegation
Each decorator calls the wrapped tea, then adds its own part.
When we call `getCost()`, the call goes **inside** the layers until it reaches the base.
Then the values are added **on the way back**:

```
Flavor(Mango).getCost()
 └─ Flavor(Strawberry).getCost()
     └─ Sugar.getCost()
         └─ Tapioca.getCost()
             └─ LatteBase.getCost()  → 3.50
             ← 3.50 + 0.50 = 4.00
         ← 4.00 + 0.30 = 4.30
     ← 4.30 + 0.60 = 4.90
 ← 4.90 + 0.60 = 5.50
```

## Project structure

| File | What it does |
|------|--------------|
| `BubbleTea` | Interface. It declares `getDescription()` and `getCost()`. |
| `LatteBase` | Base drink. Description `Latte Bubble Tea`, cost 3.50. |
| `MatchaBase` | Base drink. Description `Matcha Bubble Tea`, cost 3.20. |
| `TeaBase` | Base drink. Description `Tea Bubble Tea`, cost 3.00. |
| `BubbleTeaDecorator` | Abstract class. It implements `BubbleTea`, stores the wrapped tea and delegates both methods to it. |
| `Ice` | Decorator. Adds ` + Ice` and 0.25 €. |
| `Sugar` | Decorator. Adds ` + Sugar` and 0.30 €. |
| `Tapioca` | Decorator. Adds ` + Tapioca` and 0.50 €. |
| `Flavor` | Decorator. Stores the flavor name. Adds ` + Flavor: <name>` and 0.60 €. |


## Flow of the wrapping

```
LatteBase  ──►  Tapioca(LatteBase)  ──►  Sugar(Tapioca(...))  ──►  Flavor(Sugar(...))  ──► ...
 (base)            (decorator)              (decorator)               (decorator)
```

Any decorator can wrap any `BubbleTea`, so the order and the number of extras are free.
The order only changes the text of the description. The final cost is always the same.

## Examples

**Simple tea with ice**

```java
BubbleTea tea = new Ice(new TeaBase());
// Description: Tea Bubble Tea + Ice
// Cost: 3.00 + 0.25 = 3.25
```

**Matcha with sugar and tapioca**

```java
BubbleTea tea = new MatchaBase();
tea = new Sugar(tea);
tea = new Tapioca(tea);
// Description: Matcha Bubble Tea + Sugar + Tapioca
// Cost: 3.20 + 0.30 + 0.50 = 4.00
```

**Latte with two flavors**

```java
BubbleTea tea = new LatteBase();
tea = new Flavor(tea, "Strawberry");
tea = new Flavor(tea, "Mango");
// Description: Latte Bubble Tea + Flavor: Strawberry + Flavor: Mango
// Cost: 3.50 + 0.60 + 0.60 = 4.70
```

## How the Decorator roles map to this project

| Classic Decorator role | In this project |
|------------------------|-----------------|
| Component | `BubbleTea` |
| Concrete component | `LatteBase`, `MatchaBase`, `TeaBase` |
| Decorator | `BubbleTeaDecorator` |
| Concrete decorator | `Ice`, `Sugar`, `Tapioca`, `Flavor` |
| `operation()` | `getDescription()` and `getCost()` |
| Client | The unit tests |

## SOLID principles

- **Single Responsibility:** each class has one job (one base or one ingredient).
- **Open/Closed:** we can add a new ingredient (for example `Cream`) without changing any existing class.
- **Liskov Substitution:** a decorator can be used anywhere a `BubbleTea` is expected.
- **Dependency Inversion:** the decorators depend on the `BubbleTea` interface, not on concrete bases.

## Tests

The tests use **JUnit 5**. They build different Bubble Teas and check the description and the cost.

Costs are `double` values, so the tests compare them with a small margin (`delta`):

```java
assertEquals(5.50, tea.getCost(), 0.001);
```

To run them with Maven:

```
mvn test
```

## Requirements

- Java 11 or higher
- Maven
- JUnit 5

