//@@author olsonwangyj
package ezmealplan.command.checkers;

import ezmealplan.exceptions.EZMealPlanException;
import ezmealplan.exceptions.InvalidIngredientFormatException;
import ezmealplan.exceptions.MissingIngKeywordException;
import ezmealplan.exceptions.MissingIngredientException;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsumeChecker extends Checker {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String ING = "/ing";
    private static final String CONSUME = "consume";

    public ConsumeChecker(String userInputText) {
        this.userInput = userInputText.trim();
        this.lowerCaseInput = userInputText.toLowerCase();
    }

    @Override
    public void check() throws EZMealPlanException {
        logger.fine("Checking '" + userInput + "' for consume command errors.");
        checkIngExists();
        checkIngredientExists();
        checkIngredientFormat();
        setPassed(true);
    }

    /**
     * Checks whether the '/ing' keyword exists in the consume command input.
     *
     * @throws MissingIngKeywordException if '/ing' is not found.
     */
    private void checkIngExists() throws MissingIngKeywordException {
        if (!lowerCaseInput.contains(ING)) {
            String message = "Triggers MissingIngKeywordException()!";
            logger.warning(message);
            throw new MissingIngKeywordException("CONSUME");
        }
    }

    /**
     * Checks that there is non-empty ingredient information provided after the '/ing' keyword.
     *
     * @throws MissingIngredientException if no ingredient details are found.
     */
    private void checkIngredientExists() throws MissingIngredientException {
        int afterIngIndex = lowerCaseInput.indexOf(ING) + ING.length();
        String ingredients = userInput.substring(afterIngIndex).trim();
        if (ingredients.isEmpty()) {
            String message = "Triggers MissingIngredientException()!";
            logger.warning(message);
            throw new MissingIngredientException(CONSUME);
        }
    }

    private boolean hasParentheses(String input) {
        return input.contains("(") && input.contains(")");
    }

    /**
     * Validates the format of each ingredient token, if it contains parentheses.
     * Expected format: "IngredientName ($Price)", where Price is in 2d.p.
     *
     * @throws InvalidIngredientFormatException if any ingredient does not match the expected format.
     */
    private void checkIngredientFormat() throws InvalidIngredientFormatException {
        int afterIngIndex = lowerCaseInput.indexOf(ING) + ING.length();
        String ingredients = userInput.substring(afterIngIndex).trim();
        String splitRegex = "\\s*,\\s*";
        String[] ingredientArray = ingredients.split(splitRegex);

        String matchingRegex = "^([\\S\\s]+)\\s*\\((-?\\d+(\\.\\d*)?)\\)$";
        Pattern ingredientPattern = Pattern.compile(matchingRegex);
        for (String ingredient : ingredientArray) {
            ingredient = ingredient.trim();
            Matcher ingredientMatcher = ingredientPattern.matcher(ingredient);
            if (hasParentheses(ingredient) && !ingredientMatcher.matches()) {
                String message = "Triggers InvalidIngredientFormatException() for token: " + ingredient;
                logger.warning(message);
                throw new InvalidIngredientFormatException();
            }
        }
    }
}
