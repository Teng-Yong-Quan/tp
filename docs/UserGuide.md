# User Guide

## Introduction

EZMealPlan is a CLI-based system that helps users to plan their meals. Users can view a list of pre-created meals in the main
recipes list, filter them by meal name, ingredients and cost according to their personal preferences, and add them 
into their personal wishlist. Users can also create their own meals which will be added into the recipes list and remove meals from their personal wishlist and the main recipes list.
users can also manage their own fridge inventory with the inventory list checking what are the missing ingredients for their meals.

## Quick Start

1. Ensure that you have Java `17` or above installed in your Computer.
**Mac users**: Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).
2. Download the latest version of `ezmealplan.jar` file from [here](https://github.com/AY2425S2-CS2113-F14-4/tp/releases).
3. Copy the file to the folder you want to use as the _home folder_ for your EZMealPlan.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar ezmealplan.jar` command
to run the application. The app will contain some preset meals.
5. Type the command in the command box and press <kbd>Enter</kbd> to execute it. e.g. typing `wishlist` and pressing 
<kbd>Enter</kbd> will open the wishlist window. Some example commands you can try:

`recipes` : Lists all meals from the recipes list.

`create /mname meal test /ing ingA(1), ingB(2), ingC(3)` : Creates a meal called `meal test` with the following 
ingredients with their respective costs: `ingA ($1.00), ingB ($2.00) and ingC ($3.00)`

`remove 3` : Removes the 3rd meal shown in the user's wishlist.

`clear` : Deletes all meals in the user's wishlist.

`bye` : Saves the meals in the recipes list and the wishlist respectively and exits the app.

Refer to the [Features](https://ay2425s2-cs2113-f14-4.github.io/tp/UserGuide.html#features) below for details of each
command.


## 📋 Lists you will manage

In EZMealPlan, there are three main lists you will interact with:

### 🍽 Recipes List

This is the primary list where all the meals are stored. It comes pre-populated with 100 meals, but you can also add 
new meals with the `create` command.

- Add a meal: `create`
- View recipes list: `recipes`
- View a filtered recipes list: `filter`
- Delete a meal: `delete`

### ⭐ Wishlist

This is a secondary list where you can add and remove your favourite meals. By using the `select` 
command, you can select a meal from the recipes list and add it to your wishlist. The app can look through this 
list to `recommend` you certain meals for you to prepare.

- Add a meal to wishlist: `select`
- View wishlist: `wishlist`
- Recommend a meal: `recommend`
- Remove a meal from wishlist: `remove`

### 🧾 Inventory List
This inventory list is where you can view te ingredients that you currently own at home, helping you to know what meals
you can prepare.

- Add an ingredient to inventory: `buy`
- View inventory: `inventory`
- Consume an ingredient from inventory: `consume`

The specific features and syntax are elaborated below.

## Features 

[!NOTE]
Notes about the command format:

* Words in `UPPER_CASE` are the parameters to be supplied by the user.
E.g. in `filter /mname MEAL_NAME`, `MEAL_NAME` is a parameter, which can be used as in `filter /mname roti 
prata`.

* Items in square brackets are optional.
e.g. `select 1 [/mcost 3]` can be used as `select 1 /mcost 3` or as `select 1`.

* Extraneous parameters for commands that do not take in parameters (such as `wishlist`, `recipes`, `bye` and `clear`) will be ignored.
e.g. if the command specifies `bye 123`, it will be interpreted as `bye`.

* The command inputs are case-insensitive. The meal(s) will be sorted alphabetically by the meal name irrespective of the letter casings in both recipes list and the user's wishlist. The ingredient(s) in each meal will also be sorted in the same
manner.

* Whitespaces around parentheses and commas, as well as trailing whitespaces, are ignored, but spaces in meal and 
  ingredient names are preserved.  

### Viewing help: `help`

This command prints the description, respective sample input(s) and sample output(s) of a command that 
the user has doubts with.

Syntax:
```
    help COMMAND_KEYWORDS
```  
Example code:
```
    help wishlist
```
Sample Output

![helpCommandWorkingSample.png](diagrams/helpCommandWorkingSample.png)

* The list of `COMMAND_KEYWORD` includes `bye`,`clear`,`create`,`delete`,`filter`,`help`,`recipes`,`remove`,`select`,
`view`, `wishlist`, `buy`, `consume`, `inventory`, and `recommend`.

### Creating a new meal: `create`

This command creates a new meal with the relevant ingredients and adds the meal into the recipes list.

Syntax:
```
    create /mname MEAL_NAME /ing INGREDIENT_1_NAME (INGREDIENT_1_COST)[, INGREDIENT_2_NAME (INGREDIENT_2_COST), ...]
```  
Example code:
```
    create /mname A_test_Meal /ing A(1.5), B(1.5)
```
Sample Output

![creatCommandWorkingSample.png](diagrams/creatCommandWorkingSample.png)

* The ingredient cost such as `INGREDIENT_1_COST` must be enclosed within `()` and parsable into a `double`.
* The order of the ingredients does not matter. For example, the following code has the same effect:

```
create /mname A_test_Meal /ing B(1.5), A(1.5)
```

* To create a meal that contains more than 1 ingredient, `,` is needed to separate each ingredient.
* Specifications of creating a meal:
  1. **The price of every ingredient must not be negative.**
  2. **Each meal should not have multiple ingredients with the same ingredient name.**
  3. **Duplicate meals are not allowed.**
     * Meals that contain the <ins>exact same set of ingredients</ins> (ignoring both ingredient and meal prices) should
     have <ins>different meal names</ins>.
     * Meals that contain <ins>different sets of ingredients</ins> (ignoring both ingredient and meal prices) can have
     the <ins>same meal name</ins> (optional).
     * To check for any existing meal before creating a new meal: you may use the `recipes` or `filter /mname` command to 
     find meals having the _same meal name that you intend to use_, followed by the `view` or `filter /ing` command to 
     check for _the list of ingredients in the meal(s) having the same meal name_ or _identify the meals having the same 
     set of ingredients._

Example of Usage:

Let A, B and C be ingredients. Let Meal_No. be meal name.

`create /mname Meal_1 /ing A(1.5), B(1.5)`

**Allowed** subsequent `create` commands:

`create /mname Meal_1 /ing A(2)`

`create /mname Meal_1 /ing A(2), C(1)`

`create /mname Meal_2 /ing B(1), A(1.5)` **ETC.**


**Invalid** subsequent `create` commands:

`create /mname Meal_1 /ing A(1.5), B(1.5)`

`create /mname Meal_1 /ing B(1), A(2)`

### Displaying the Recipes List: `recipes`

This command prints the list of meals that is in the recipes list (i.e. the global list of meals).

Syntax:
```
    recipes
```
Example code:
```
    recipes
```
Sample output:

![recipesCommandWorkingSample.png](diagrams/recipesCommandWorkingSample.png)

### Filtering the Recipes List: `filter`

This command allows the user to filter the Recipes List. The filter conditions can be either the meal's name,
ingredients, or total cost. This is chosen via the `/mname`, `/ing` or `/mname` tags.

Syntax:
```
    filter /mcost MEAL_COST
    filter /ing INGREDIENT_1_NAME[, INGREDIENT_2_NAME, ...]
    filter /mname MEAL_NAME
```
Example code:
```
    filter /mcost 5.50
    filter /ing Chicken
    filter /mname Chicken Rice
```
Sample output:

![filterCommandWorkingSample.png](diagrams/filterCommandWorkingSample.png)

### Deleting a meal from the Recipes List: `delete`

This command allows the user to delete a meal from the Recipes List.

Syntax:
```
    delete INDEX_NUMBER
```
Example code:
```
    delete 87
```
Sample output:

![deleteCommandWorkingSample.png](diagrams/deleteCommandWorkingSample.png)

* If the user deletes a meal from the Recipes List that is also in their Wishlist, then the meal is removed from
  their Wishlist as well.

### Viewing details about a meal: `view`

This command allows the user to view the details of a meal (e.g. name, ingredients, cost breakdown) from the Recipes
List or Wishlist. This is chosen via the `/r` or `/w` tag.

Syntax:
```
    view /r INDEX_NUMBER
    view /w INDEX_NUMBER
```
Example code:
```
    view /r 1
```
Sample output:

![UpdatedViewCommandWorkingSample.png](diagrams/UpdatedViewCommandWorkingSample.png)

### Adding a meal into to the Wishlist: `select`

This command allows user to select a recipe from the Recipes list and add it to their Wishlist. This command has 
two modes: 
* **Normal mode**: Select by a meal's index in the Recipes List.
  * This is done using `select INDEX_NUMBER`
* **Filtered mode**: Select by a meal's index as it appears in a filtered section of the Recipes List.
  * This is done by inserting the filter condition tag (`/mname`, `/ing`, or `/mcost`), e.g. `select INDEX_NUMBER 
    /ing INGREDIENT_1_NAME[, INGREDIENT_2_NAME, ...]`.
  * For example, `select 1 /ing Chicken` means to filter Recipes List by all meals with the Chicken ingredient, and 
    then selecting the first meal.

Syntax:
```
    select INDEX_NUMBER
    select INDEX_NUMBER /mname MEAL_NAME
    select INDEX_NUMBER /ing INGREDIENT_1_NAME[, INGREDIENT_2_NAME, ...]
    select INDEX_NUMBER /mcost MEAL_COST
```
Example code:
```
    select 20
    select 1 /ing Chicken
```
Sample output:

![selectCommandWorkingSample.png](diagrams/selectCommandWorkingSample.png)

* To view the filter section before selecting, use the `filter` command with the same filtering conditions.

### Displaying the Wishlist: `wishlist`

This command prints the list of meals in the wishlist (i.e. the list of the user's favourite meals).

Syntax:
```
    wishlist
```
Example code:
```
    wishlist
```
Sample output:

![wishlistCommandWorkingSample.png](diagrams/wishlistCommandWorkingSample.png)

### Removing a meal from the Recipes List: `remove`

This command allows user to remove a recipe from their Wishlist.

Syntax:
```
    remove INDEX_NUMBER
```
Example code:
```
    remove 2
```
Sample output:

![removeCommandWorkingSample.png](diagrams/removeCommandWorkingSample.png)

### Clearing all meals from the Wishlist: `clear`

This command allows the user to remove all the meals from the wishlist.

Syntax:
```
    clear
```
Example code:
```
    clear
```
Sample output:

![clearCommandWorkingSample.png](diagrams/clearCommandWorkingSample.png)

### Adding ingredients into the Inventory: `buy`

This command allows the user to add ingredients into the inventory.

Syntax:
```
    buy /ing INGRIDIENT_1_NAME (INGRIDIENT_1_PRICE)[, INGRIDIENT_2_NAME (INGREDIENT_2_PRICE), ...]
```
Example code:
```
    buy /ing Chicken(1),fish(1)
```
Sample output:

![updateBuyCommandWorkingSample.png](diagrams/updateBuyCommandWorkingSample.png)

### Displaying the Inventory: `inventory`

This command prints the list of ingredients currently in the user's inventory (i.e. ingredients in the user's fridge 
or kitchen)

Syntax:
```
    inventory
```
Example code:
```
    inventory
```
Sample output:

![inventoryCommandWorkingSample.png](diagrams/inventoryCommandWorkingSample.png)

### Removing ingredients from the Inventory: `consume`

This command allows the user to remove ingredients from the inventory.

Syntax:
```
    consume /ing INGRIDIENT_NAME
```
Example code:
```
    consume /ing fish
```
Sample output:

![updatedConsumeCommandWorkingSample.png](diagrams/updatedConsumeCommandWorkingSample.png)

### Recommending a meal: `recommend`

This command recommends the user with a meal containing the specified ingredient, for the user to prepare. It will 
also display the missing ingredients that need to be bought.

This command looks through the Wishlist to recommend a meal; if no meals are found, then it will recommend a meal 
from the Recipes List

Syntax:
```
    recommend /ing INGREDIENT
```
Example code:
```
    recommend /ing Minced Pork
```
Sample output:

![recommendCommandWorkingSample.png](diagrams/recommendCommandWorkingSample.png)

### Exiting the application: `bye`

This command saves the contents of the three lists on to disk and terminates the application gracefully.
It prints a goodbye message to indicate that the session is closing.

Syntax:
```
    bye
```  
Example code:
```
    bye
```
Sample output:

![byeCommandWorkingSample.png](diagrams/byeCommandWorkingSample.png)


## Command Summary

* Get help `help COMMAND_NAME`
* Create meal: `create /mname MEAL_NAME /ing INGREDIENT1 (COST1)[, INGREDIENT2 (COST2), ...]`
* View Recipe List: `recipes`
* Filter Recipes List: `filter /mcost MEAL_COST` or `filter /ing INGREDIENT1[, INGREDIENT2, ...]` or `filter /mname 
MEAL_NAME`
* Delete meal: `delete INDEX`
* View meal details: `view /r INDEX` or `view /w INDEX`
* Select meal into Wishlist: `select INDEX` or `select INDEX /FILTER_METHOD FILTER_INPUT`
* View Wishlist: `wishlist`
* Remove from Wishlist: `remove INDEX`
* Clear Wishlist: `clear`
* Buy ingredient: `buy /ing INGREDIENT_1_NAME (INGREDIENT_1_COST)[, INGREDIENT_2_NAME (INGREDIENT_2_COST), ...]`
* View Inventory: `inventory`
* Consume ingredient: `consume /ing INGREDIENT_NAME`
* Get recommendation: `recommend /ing INGREDIENT_NAME`
* Exit program `bye`
