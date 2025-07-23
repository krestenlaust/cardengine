package cardengine;

/**
 * A place where cards can be stored
 */
public interface CardField {

    public void PutCard(Card card);

    public Card GetCard();
}
