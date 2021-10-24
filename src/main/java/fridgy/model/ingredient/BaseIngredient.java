package fridgy.model.ingredient;

import static fridgy.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import fridgy.model.base.Eq;

/**
 * Represents a Base ingredient to be used by Recipe.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class BaseIngredient implements Eq {
    public static final String MESSAGE_CONSTRAINTS =
        "Base Ingredient should have the format of <Name> <Quantity>";

    // Identity fields
    private final Name name;
    private final Quantity quantity;

    /**
     * Constructs a base ingredient reference with the given name and quantity.
     */
    public BaseIngredient(Name name, Quantity quantity) {
        requireAllNonNull(name, quantity);
        this.name = name;
        this.quantity = quantity;
    }


    public Name getName() {
        return name;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    /**
     * Returns true if both ingredients have the same name.
     * This defines a weaker notion of equality between two ingredients.
     */
    public boolean isSameIngredient(BaseIngredient otherBaseIngredient) {
        if (otherBaseIngredient == this) {
            return true;
        }

        return otherBaseIngredient != null
                && otherBaseIngredient.getName().equals(getName());
    }

    @Override
    public boolean isSame(Eq other) {
        if (other == this) {
            return true;
        }
        if (other instanceof BaseIngredient) {
            return isSameIngredient((BaseIngredient) other);
        }
        return false;
    }

    /**
     * Returns true if both ingredients have the same identity and data fields.
     * This defines a stronger notion of equality between two ingredients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof BaseIngredient)) {
            return false;
        }

        BaseIngredient otherBaseIngredient = (BaseIngredient) other;
        return otherBaseIngredient.getName().equals(getName())
                && otherBaseIngredient.getQuantity().equals(getQuantity());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, quantity);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Quantity: ")
                .append(getQuantity());

        return builder.toString();
    }

}
